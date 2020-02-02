package noah.memo.memogateway.gateway.filter.log;

import com.alibaba.fastjson.JSON;
import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.constants.ZuulConstants;
import com.netflix.zuul.constants.ZuulHeaders;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.util.HTTPRequestUtils;
import noah.memo.memoframework.bean.DistributedContext;
import noah.memo.memoframework.bean.log.LogEvent;
import noah.memo.memoframework.bean.log.LogFlag;
import noah.memo.memoframework.log.Logger;
import org.apache.commons.io.IOUtils;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTING_DEBUG_KEY;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.X_ZUUL_DEBUG_HEADER;

/**
 * @author noah
 */
public class LogPostFilter extends ZuulFilter {

    private static DynamicIntProperty INITIAL_STREAM_BUFFER_SIZE = DynamicPropertyFactory
            .getInstance()
            .getIntProperty(ZuulConstants.ZUUL_INITIAL_STREAM_BUFFER_SIZE, 8192);

    private ThreadLocal<byte[]> buffers = new ThreadLocal<byte[]>() {
        @Override
        protected byte[] initialValue() {
            return new byte[INITIAL_STREAM_BUFFER_SIZE.get()];
        }
    };

    private static DynamicBooleanProperty INCLUDE_DEBUG_HEADER = DynamicPropertyFactory
            .getInstance()
            .getBooleanProperty(ZuulConstants.ZUUL_INCLUDE_DEBUG_HEADER, false);

    private static DynamicBooleanProperty SET_CONTENT_LENGTH = DynamicPropertyFactory
            .getInstance()
            .getBooleanProperty(ZuulConstants.ZUUL_SET_CONTENT_LENGTH, false);

    private boolean useServlet31 = true;

    /**
     * 失败状态
     */
    public static final int FAIL_STATUS = -1;

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * responese日志打印
     *
     * @param isErr
     * @param errName
     * @param retStr
     * @param req
     */
    private void log(boolean isErr, String errName, String retStr, HttpServletRequest req) {
        try {

            DistributedContext distributedContext = DistributedContext.getContext();

            long reqTime = distributedContext.getTime();

            LogEvent logEvent = new LogEvent();
            logEvent.flag = LogFlag.RESPONSE.getFlag();
            logEvent.clientIp = req.getRemoteAddr();
            logEvent.costTime = System.currentTimeMillis() - reqTime;
            logEvent.msg = retStr;
            logEvent.isErr = isErr;
            String uri = req.getRequestURI();
            logEvent.restUrl = uri;

            logEvent.errName = errName;
            logEvent.step = distributedContext.getStep();

            Logger.info(logEvent);
            DistributedContext.remove();

        } catch (Exception e) {
            Logger.error("LogPostFilter[打印请求日志出错][原因=" + e.toString() + "]", e);
        }
    }

    @SuppressWarnings("serial")
    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.getResponse().setCharacterEncoding("UTF-8");
        boolean isErr = false;
        String errName = "";
        String retStr = "";

        // 异常
        Throwable ex = ctx.getThrowable();

        String callback = ctx.getRequest().getParameter("callback");

        // 先判断zuul是否有异常抛出
        if (ex != null) {

            errName = ex.getClass().getName();
            isErr = true;

            LinkedHashMap<String, Object> retMap = new LinkedHashMap<String, Object>();
            retMap.put("status", FAIL_STATUS);
            ZuulException zuulException = findZuulException(ex);
            zuulException.printStackTrace();

            Throwable causeEx = zuulException.getCause();

            if (causeEx != null) {
                //列举自定义异常
                retMap.put("msg", causeEx.toString());
            } else {
                retMap.put("msg", zuulException.toString());
            }
            retStr = JSON.toJSONString(retMap);
        } else {
            String sb = "";
            InputStream in = ctx.getResponseDataStream();
            try {
                sb = IOUtils.toString(in);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            Map<String, Object> map = null;
            boolean isJson = false;

            if (sb.length() > 0) {
                try {
                    map = JSON.parseObject(sb, Map.class);
                    isJson = true;
                } catch (Exception e) {
                    map = new LinkedHashMap<String, Object>();
                }
            }

            isErr = isJson && map.containsKey("exception");

            //异常
            if (isErr) {
                ctx.getResponse().setStatus(200);
                errName = map.get("exception").toString();
                LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();
                result.put("status", FAIL_STATUS);
                result.put("msg", map.get("exception") + ": " + map.get("message"));
                retStr = JSON.toJSONString(result);
            } else { // 正常
                if (isJson) {
                    retStr = JSON.toJSONString(map);
                } else {
                    retStr = sb.toString();
                }
            }
        }
        //传递参数给下一层调用
        try {

            if (callback != null && !"".equals(callback)) {
                retStr = callback + "(" + retStr + ")";
            }

            if (ex != null) {
                ctx.setResponseBody(retStr);
                try {
                    OutputStream outStream = ctx.getResponse().getOutputStream();
                    if (RequestContext.getCurrentContext().getResponseBody() != null) {
                        String body = RequestContext.getCurrentContext().getResponseBody();
                        writeResponse(
                                new ByteArrayInputStream(
                                        body.getBytes(ctx.getResponse().getCharacterEncoding())),
                                outStream);
                    }
                } catch (Exception e) {
                    Logger.error("LogPostFilter[打印请求日志出错][原因=" + e.toString() + "]", e);
                }
            } else {
                ctx.setResponseDataStream(new ByteArrayInputStream(retStr.getBytes()));
                addResponseHeaders();
                writeResponse();
            }
        } catch (Exception e) {
            Logger.error("LogPostFilter[打印请求日志出错][原因=" + e.toString() + "]", e);
        }

        HttpServletRequest req = ctx.getRequest();

        log(isErr, errName, retStr, req);

        return null;
    }

    /**
     * 重写zuul writeResponse
     *
     * @param zin
     * @param out
     * @throws Exception
     */
    private void writeResponse(InputStream zin, OutputStream out) throws Exception {
        byte[] bytes = buffers.get();
        int bytesRead = -1;
        while ((bytesRead = zin.read(bytes)) != -1) {
            out.write(bytes, 0, bytesRead);
        }
    }

    ZuulException findZuulException(Throwable throwable) {
        if (throwable.getCause() instanceof ZuulRuntimeException) {
            // this was a failure initiated by one of the local filters
            return (ZuulException) throwable.getCause().getCause();
        }

        if (throwable.getCause() instanceof ZuulException) {
            // wrapped zuul exception
            return (ZuulException) throwable.getCause();
        }

        if (throwable instanceof ZuulException) {
            // exception thrown by zuul lifecycle
            return (ZuulException) throwable;
        }

        // fallback, should never get here
        return new ZuulException(throwable, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
    }

    private void writeResponse() throws Exception {
        RequestContext context = RequestContext.getCurrentContext();
        // there is no body to send
        if (context.getResponseBody() == null
                && context.getResponseDataStream() == null) {
            return;
        }
        HttpServletResponse servletResponse = context.getResponse();
        // only set if not set
        if (servletResponse.getCharacterEncoding() == null) {
            servletResponse.setCharacterEncoding("UTF-8");
        }
        OutputStream outStream = servletResponse.getOutputStream();
        InputStream is = null;
        try {
            if (RequestContext.getCurrentContext().getResponseBody() != null) {
                String body = RequestContext.getCurrentContext().getResponseBody();
                writeResponse(
                        new ByteArrayInputStream(
                                body.getBytes(servletResponse.getCharacterEncoding())),
                        outStream);
                return;
            }
            boolean isGzipRequested = false;
            final String requestEncoding = context.getRequest()
                    .getHeader(ZuulHeaders.ACCEPT_ENCODING);

            if (requestEncoding != null
                    && HTTPRequestUtils.getInstance().isGzipped(requestEncoding)) {
                isGzipRequested = true;
            }
            is = context.getResponseDataStream();
            InputStream inputStream = is;
            if (is != null) {
                if (context.sendZuulResponse()) {
                    if (context.getResponseGZipped() && !isGzipRequested) {
                        final Long len = context.getOriginContentLength();
                        if (len == null || len > 0) {
                            try {
                                inputStream = new GZIPInputStream(is);
                            } catch (java.util.zip.ZipException ex) {
                                inputStream = is;
                            }
                        } else {
                            // Already done : inputStream = is;
                        }
                    } else if (context.getResponseGZipped() && isGzipRequested) {
                        servletResponse.setHeader(ZuulHeaders.CONTENT_ENCODING, "gzip");
                    }
                    writeResponse(inputStream, outStream);
                }
            }
        } finally {
            try {
                Object zuulResponse = RequestContext.getCurrentContext()
                        .get("zuulResponse");
                if (zuulResponse instanceof Closeable) {
                    ((Closeable) zuulResponse).close();
                }
                outStream.flush();
            } catch (IOException ex) {
            }
        }
    }

    private void addResponseHeaders() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletResponse servletResponse = context.getResponse();
        if (INCLUDE_DEBUG_HEADER.get()) {
            @SuppressWarnings("unchecked")
            List<String> rd = (List<String>) context.get(ROUTING_DEBUG_KEY);
            if (rd != null) {
                StringBuilder debugHeader = new StringBuilder();
                for (String it : rd) {
                    debugHeader.append("[[[" + it + "]]]");
                }
                servletResponse.addHeader(X_ZUUL_DEBUG_HEADER, debugHeader.toString());
            }
        }
        List<Pair<String, String>> zuulResponseHeaders = context.getZuulResponseHeaders();
        if (zuulResponseHeaders != null) {
            for (Pair<String, String> it : zuulResponseHeaders) {
                servletResponse.addHeader(it.first(), it.second());
            }
        }
        // Only inserts Content-Length if origin provides it and origin response is not
        // gzipped
        if (SET_CONTENT_LENGTH.get()) {
            Long contentLength = context.getOriginContentLength();
            if (contentLength != null && !context.getResponseGZipped()) {
                if (useServlet31) {
                    servletResponse.setContentLengthLong(contentLength);
                } else {
                    //Try and set some kind of content length if we can safely convert the Long to an int
                    if (isLongSafe(contentLength)) {
                        servletResponse.setContentLength(contentLength.intValue());
                    }
                }
            }
        }
    }

    private boolean isLongSafe(long value) {
        return value <= Integer.MAX_VALUE && value >= Integer.MIN_VALUE;
    }
}
