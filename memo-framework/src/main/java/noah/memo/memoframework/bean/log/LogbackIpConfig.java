package noah.memo.memoframework.bean.log;

import ch.qos.logback.core.PropertyDefinerBase;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author noah
 */
public class LogbackIpConfig extends PropertyDefinerBase {
    @Override
    public String getPropertyValue() {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }
}


