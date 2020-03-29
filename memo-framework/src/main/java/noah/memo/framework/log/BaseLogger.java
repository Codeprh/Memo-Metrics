package noah.memo.framework.log;

import noah.memo.framework.bean.DistributedContext;
import noah.memo.framework.bean.log.LogEvent;
import org.slf4j.MDC;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * elk日志基础类
 *
 * @author noah
 */
public class BaseLogger {

    // 分隔符
    public static final String PREFIX_SEP = ":";
    public static final String PREFIX_FIELD = "|";
    public static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");

    protected static void setMDC(LogEvent logEvent) {
        MDC.put("className", logEvent.className == null ? "" : logEvent.className);
        MDC.put("costTime", "" + logEvent.costTime);
        MDC.put("isErr", "" + logEvent.isErr);
        MDC.put("flag", "" + logEvent.flag);
        MDC.put("ip", logEvent.ip == null ? "" : logEvent.ip);
        MDC.put("methodName", logEvent.methodName == null ? "" : logEvent.methodName);
        MDC.put("logIp", logEvent.ip == null ? "" : logEvent.ip);
        MDC.put("errName", logEvent.errName == null ? "" : logEvent.errName);
        DistributedContext context = DistributedContext.getContext();
        MDC.put("logId", context == null || context.getLogId() == null ? "" : context.getLogId());
        MDC.put("clientIp", context == null || context.getClientIp() == null ? "" : context.getClientIp());
        MDC.put("clientType", context == null ? "0" : "" + context.getClientType());
        MDC.put("step", "" + context.getStepAndAdd());
        MDC.put("actionName", context == null || context.getActionName() == null ? "" : context.getActionName());
        MDC.put("restUrl", logEvent.restUrl == null ? "" : logEvent.restUrl);
    }

    protected static void setLogIp(LogEvent logEvent) {
        try {
            logEvent.ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
