package noah.memo.memoframework.bean;

import java.util.Objects;
import java.util.UUID;


/**
 * 分布式请求上下文
 *
 * @author noah
 */
public class DistributedContext {

    private static ThreadLocal<DistributedContext> threadLocal = new ThreadLocal<DistributedContext>();

    private DistributedContext(String clientIp) {
        this.logId = UUID.randomUUID().toString();
        this.clientIp = clientIp;
        setContext(this);
    }

    private DistributedContext(int clientType) {
        this.logId = UUID.randomUUID().toString();
        this.clientType = clientType;
        setContext(this);
    }

    private DistributedContext() {
        this.logId = UUID.randomUUID().toString();
        setContext(this);
    }

    public static DistributedContext newInstance() {
        DistributedContext context = new DistributedContext();
        return context;
    }

    public static DistributedContext newInstance(String logId, String platformKey) {
        DistributedContext context = new DistributedContext();
        context.logId = logId;
        context.platformKey = platformKey;
        return context;
    }

    public static DistributedContext newInstance(DistributedContext context) {
        DistributedContext logContext = new DistributedContext(0);
        logContext.logId = context.logId;
        logContext.step = context.step;
        setContext(logContext);
        return logContext;
    }

    public static void setContext(DistributedContext context) {
        threadLocal.set(context);
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static DistributedContext getContext() {
        DistributedContext context = threadLocal.get();
        if (context == null) {
            context = new DistributedContext();
            threadLocal.set(context);
            return context;
        }
        return context;
    }

    public static DistributedContext start(String clientIp) {
        return new DistributedContext(clientIp);
    }

    public static DistributedContext startWithLogId(String logId) {
        if (logId == null || Objects.equals(logId, "")) {
            return start();
        } else {
            DistributedContext context = new DistributedContext();
            context.logId = logId;
            setContext(context);
            return context;
        }
    }

    public static DistributedContext start() {
        return new DistributedContext();
    }


    /**
     * 开始时间
     */
    private long time;
    /**
     * log日志id
     */
    private String logId;
    /**
     * log顺序标识
     */
    private int step;
    /**
     * 请求Ip
     */
    private String clientIp;
    /**
     * 运营商key
     */
    private String platformKey;
    /**
     * 额外自定义对象
     */
    private Object extData;
    /**
     * 客户端类型{link ClientTypes}
     */
    private int clientType;

    /**
     * 接口名
     */
    private String actionName;
    /**
     * 请求数据的字符串
     */
    private String reqDataString;


    // ===============================================getter===============================================

    /**
     * @return the time
     */
    public long getTime() {
        return time;
    }

    /**
     * @return the logId
     */
    public String getLogId() {
        return logId;
    }

    /**
     * @return the step
     */
    public int getStep() {
        return step;
    }

    public int getStepAndAdd() {
        return ++step;
    }

    /**
     * @return the clientIp
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @return the platformKey
     */
    public String getPlatformKey() {
        return platformKey;
    }

    /**
     * @return the extData
     */
    public Object getExtData() {
        return extData;
    }

    /**
     * @return the clientType
     */
    public int getClientType() {
        return clientType;
    }

    /**
     * @return the actionName
     */
    public String getActionName() {
        return actionName;
    }
    // ===============================================setter===============================================

    /**
     * @param time the time to set
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * @param step the step to set
     */
    public void setStep(int step) {
        this.step = step;
    }

    /**
     * @param clientIp the clientIp to set
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * @param extData the extData to set
     */
    public void setExtData(Object extData) {
        this.extData = extData;
    }

    /**
     * @param clientType the clientType to set
     */
    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    /**
     * @param platformKey the platformKey to set
     */
    public void setPlatformKey(String platformKey) {
        this.platformKey = platformKey;
    }

    /**
     * @param actionName the actionName to set
     */
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public void setReqData(String actionName, int clientType, String platformKey) {
        this.actionName = actionName;
        this.clientType = clientType;
        this.platformKey = platformKey;
    }

    public String getReqDataString() {
        return reqDataString;
    }

    public void setReqDataString(String reqDataString) {
        this.reqDataString = reqDataString;
    }
}
