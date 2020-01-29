package noah.memo.memogateway.gateway.filter;

import com.dianping.cat.Cat;
import com.dianping.cat.Cat.Context;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import noah.memo.memoframework.cat.CatContext;
import noah.memo.memoframework.cat.CatHttpConstants;
import org.springframework.stereotype.Component;

/**
 * 借助Zuul Filter以跨进程边界方式传递CAT调用链上下文
 */
@Component
public class CatHeaderFilter extends ZuulFilter {

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        // 保存和传递CAT调用链上下文
        Context ctx = new CatContext();
        Cat.logRemoteCallClient(ctx);
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.addZuulRequestHeader(CatHttpConstants.CAT_HTTP_HEADER_ROOT_MESSAGE_ID, ctx.getProperty(Context.ROOT));
        requestContext.addZuulRequestHeader(CatHttpConstants.CAT_HTTP_HEADER_PARENT_MESSAGE_ID, ctx.getProperty(Context.PARENT));
        requestContext.addZuulRequestHeader(CatHttpConstants.CAT_HTTP_HEADER_CHILD_MESSAGE_ID, ctx.getProperty(Context.CHILD));
        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

}
