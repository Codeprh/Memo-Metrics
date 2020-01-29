package noah.memo.memoframework.bean.log;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;


/**
 * 日志实体类
 *
 * @author noah
 */
public class LogEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    public long time;

    /**
     * traceId，每个请求唯一
     */
    public String logId;

    public int step;

    /**
     * 产生日志的服务器IP
     */
    public String ip;

    /**
     * 用户请求ip
     */
    public String clientIp;

    /**
     * 日志所属系统
     */
    public String sysName;

    /**
     * 日志标识
     */
    public int flag;

    /**
     * 日志产生时间(yyyy-MM-dd hh:mm:ss)
     */
    public String createTime;

    /**
     * 产生日志的类
     */
    public String className;
    /**
     * 产生日志的方法
     */
    public String methodName;

    /**
     * 日志级别 INFO ERROR
     */
    public String level;

    /**
     * 日志信息
     */
    public String msg;

    /**
     * 一次执行耗时（用于打印响应日志）
     */
    public long costTime;

    /**
     * 是否异常
     */
    public boolean isErr;

    /**
     * 客户端类型{ClientTypes}
     */
    public int clientType;

    /**
     * 接口名
     */
    public String actionName;

    /**
     * api唯一名称， 实时流计算用
     */
    public String apiName;

    /**
     * 异常名
     */
    public String errName;

    /**
     * es数据id
     */
    public String eid;

    /**
     * 请求路径
     */
    public String restUrl;


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

