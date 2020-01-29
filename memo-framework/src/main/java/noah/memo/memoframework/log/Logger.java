package noah.memo.memoframework.log;

import noah.memo.memoframework.bean.DistributedContext;
import noah.memo.memoframework.bean.log.LogEvent;
import noah.memo.memoframework.bean.log.LogFlag;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Logger extends BaseLogger {

    public static org.slf4j.Logger log = LoggerFactory.getLogger(Logger.class);

    public static void debug(String msg) {
        try {
            start();
            msg = msg == null ? "" : msg.replace(LINE_SEPARATOR, "<br/>");
            StackTraceElement stack[] = Thread.currentThread().getStackTrace();
            LogEvent logEvent = new LogEvent();
            logEvent.msg = msg;
            logEvent.flag = LogFlag.BUSINESS.getFlag();
            logEvent.className = stack[2].getClassName();
            logEvent.methodName = stack[2].getMethodName();
            setLogIp(logEvent);
            setMDC(logEvent);
            log.debug(logEvent.msg);
            MDC.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void info(String msg) {
        try {
            start();
            msg = msg == null ? "" : msg.replace(LINE_SEPARATOR, "<br/>");
            StackTraceElement stack[] = Thread.currentThread().getStackTrace();
            LogEvent logEvent = new LogEvent();
            logEvent.msg = msg;
            logEvent.flag = LogFlag.BUSINESS.getFlag();
            logEvent.className = stack[2].getClassName();
            logEvent.methodName = stack[2].getMethodName();
            setLogIp(logEvent);
            setMDC(logEvent);
            log.info(logEvent.msg);
            MDC.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void info(String msg, LogFlag logFlag) {
        try {
            msg = msg == null ? "" : msg.replace(LINE_SEPARATOR, "<br/>");
            StackTraceElement stack[] = Thread.currentThread().getStackTrace();
            LogEvent logEvent = new LogEvent();
            logEvent.msg = msg;
            logEvent.flag = logFlag.getFlag();
            logEvent.className = stack[2].getClassName();
            logEvent.methodName = stack[2].getMethodName();
            setLogIp(logEvent);
            setMDC(logEvent);
            log.info(logEvent.msg);
            MDC.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void info(String msg, LogFlag logFlag, long costTime) {
        try {
            msg = msg == null ? "" : msg.replace(LINE_SEPARATOR, "<br/>");
            StackTraceElement stack[] = Thread.currentThread().getStackTrace();
            LogEvent logEvent = new LogEvent();
            logEvent.msg = msg;
            logEvent.flag = logFlag.getFlag();
            logEvent.className = stack[2].getClassName();
            logEvent.methodName = stack[2].getMethodName();
            logEvent.costTime = costTime;
            setLogIp(logEvent);
            setMDC(logEvent);
            log.info(logEvent.msg);
            MDC.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void error(String msg, Throwable t) {
        try {
            StackTraceElement stack[] = Thread.currentThread().getStackTrace();
            LogEvent logEvent = new LogEvent();
            logEvent.msg = msg;
            logEvent.flag = LogFlag.EXCEPTION.getFlag();
            logEvent.className = stack[2].getClassName();
            logEvent.methodName = stack[2].getMethodName();
            logEvent.isErr = true;
            String[] errClassArr = t.getClass().toString().split(" ");
            if (errClassArr.length >= 2) {
                logEvent.errName = t.getClass().toString().split(" ")[1];
            } else {
                logEvent.errName = t.getClass().toString().split(" ")[0];
            }
            StringWriter writer = new StringWriter();
            t.printStackTrace(new PrintWriter(writer, true));
            logEvent.msg = msg == null ? "" : msg.replace(LINE_SEPARATOR, "<br/>") + writer.toString().replace(LINE_SEPARATOR, "<br/>");
            setLogIp(logEvent);
            setMDC(logEvent);
            log.error(logEvent.msg);
            MDC.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void error(Throwable t) {
        try {
            StackTraceElement stack[] = Thread.currentThread().getStackTrace();
            LogEvent logEvent = new LogEvent();
            logEvent.flag = LogFlag.EXCEPTION.getFlag();
            logEvent.className = stack[2].getClassName();
            logEvent.methodName = stack[2].getMethodName();
            logEvent.isErr = true;
            String[] errClassArr = t.getClass().toString().split(" ");
            if (errClassArr.length >= 2) {
                logEvent.errName = t.getClass().toString().split(" ")[1];
            } else {
                logEvent.errName = t.getClass().toString().split(" ")[0];
            }
            StringWriter writer = new StringWriter();
            t.printStackTrace(new PrintWriter(writer, true));
            logEvent.msg = writer.toString().replace(LINE_SEPARATOR, "<br/>");
            setLogIp(logEvent);
            setMDC(logEvent);
            log.error(logEvent.msg);
            MDC.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void info(String className, String methodName, String args, long costTime) {
        try {
            LogEvent logEvent = new LogEvent();
            logEvent.flag = LogFlag.INNER_API.getFlag();
            logEvent.msg = "args = " + args;
            logEvent.className = className;
            logEvent.methodName = methodName;
            logEvent.costTime = costTime;
            setLogIp(logEvent);
            setMDC(logEvent);
            log.info(logEvent.msg);
            MDC.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void info(LogEvent logEvent, long costTime, LogFlag logFlag) {
        try {
            logEvent.flag = logFlag.getFlag();
            logEvent.costTime = costTime;
            setLogIp(logEvent);
            setMDC(logEvent);
            log.info(logEvent.msg);
            MDC.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 开始计时(默认初始化Logger时会开始计时)
     */
    public static void start() {
        DistributedContext.getContext().setTime(System.currentTimeMillis());
    }

    /**
     * 日志打印耗时信息
     *
     * @param message - 信息前缀,比如为xx方法,则日志记录xx方法:耗时time
     */
    public static void timing(String message) {
        long time = System.currentTimeMillis() - DistributedContext.getContext().getTime();
        info(message + ":耗时" + time + "mm");
    }

    /**
     * 日志打印耗时信息(重置开始计时)
     *
     * @param message - 信息前缀,比如为xx方法,则日志记录xx方法:耗时time
     */
    public static void timingAndStart(String message) {
        long time = System.currentTimeMillis() - DistributedContext.getContext().getTime();
        DistributedContext.getContext().setTime(System.currentTimeMillis());
        info(message + ":耗时" + time + "mm");
    }

}
