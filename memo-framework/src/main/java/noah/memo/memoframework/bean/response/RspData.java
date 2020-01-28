package noah.memo.memoframework.bean.response;

import com.google.gson.JsonObject;

/**
 * 响应数据
 *
 * @author noah
 */
public class RspData {

    public RspData() {
        setRspStatus(RspStatus.SUCCESS);
    }

    public RspData(Object data) {
        this();
        this.data = data;
    }

    /**
     * 响应状态
     */
    public int status;

    /**
     * 响应状态描述
     */
    public String msg;

    /**
     * 响应具体数据
     */
    public Object data;


    /**
     * 返回一个成功的RspData
     *
     * @param data-具体响应数据
     * @return
     */
    public static RspData getSuccessRspData(Object data) {
        return new RspData(data);
    }

    /**
     * 返回一个无具体数据的成功的RspData<br/>
     *
     * @return
     */
    public static RspData getSuccessRspData() {
        return new RspData();
    }

    /**
     * 返回一个String类型成功的 rspDate
     *
     * @param data
     * @return
     */
    public static RspData getSuccessStrDate(String data) {
        return new RspData(data);
    }

    /**
     * 返回一个无具体数据的成功的RspData<br/>
     *
     * @return
     */
    public static RspData getSuccessRspData(String msg) {
        RspData rspData = new RspData();
        rspData.setRspStatus(RspStatus.SUCCESS, msg);
        return rspData;
    }

    /**
     * 返回一个失败的RspData<br/>
     * 默认为RspStatus.FAIL失败类型
     *
     * @param msg-自定义失败信息
     * @return
     */
    public static RspData getFailRspData(String msg) {
        RspData rspData = new RspData();
        rspData.setRspStatus(RspStatus.FAIL, msg);
        return rspData;
    }

    /**
     * 返回一个失败的RspData<br/>
     *
     * @param status-自定义状态
     * @param msg-自定义信息
     * @return
     */
    public static RspData getFailRspData(int status, String msg) {
        RspData rspData = new RspData();
        rspData.status = status;
        rspData.msg = msg;
        return rspData;
    }

    /**
     * 返回一个失败的RspData<br/>
     *
     * @param rspStatus-{link RspStatus}
     * @return
     */
    public static RspData getFailRspData(RspStatus rspStatus) {
        RspData rspData = new RspData();
        rspData.setRspStatus(rspStatus);
        return rspData;
    }

    /**
     * 返回一个自定义的RspData
     *
     * @param status-响应状态
     * @param msg-响应信息
     * @param data-具体响应数据
     * @return
     */
    public static RspData getRspData(int status, String msg, Object data) {
        RspData rspData = new RspData();
        rspData.status = status;
        rspData.msg = msg;
        rspData.data = data;
        return rspData;
    }

    /**
     * 设置响应状态（使用默认描述）
     *
     * @param rspStatus-{link RspStatus}
     */
    public void setRspStatus(RspStatus rspStatus) {
        status = rspStatus.getStatus();
        msg = rspStatus.getMsg();
    }

    /**
     * 设置响应状态（使用自定义描述）
     *
     * @param rspStatus-{link RspStatus}
     * @param msg-自定义描述
     */
    public void setRspStatus(RspStatus rspStatus, String msg) {
        status = rspStatus.getStatus();
        this.msg = msg;
    }

    /**
     * 给data增加属性，支持Number、String、Boolean、Character
     *
     * @param name
     * @param value
     */
    public void dataAddProperty(String name, Object value) {
        if (data == null) {
            data = new JsonObject();
        }
        JsonObject jsonObject = (JsonObject) data;
        if (value instanceof Number) {
            jsonObject.addProperty(name, (Number) value);
        } else if (value instanceof String) {
            jsonObject.addProperty(name, (String) value);
        } else if (value instanceof Boolean) {
            jsonObject.addProperty(name, (Boolean) value);
        } else if (value instanceof Character) {
            jsonObject.addProperty(name, (Character) value);
        }
    }

    @Override
    public String toString() {
        return "RspData{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
