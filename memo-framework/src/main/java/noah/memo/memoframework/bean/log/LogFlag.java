package noah.memo.memoframework.bean.log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 日志类型
 *
 * @author max
 * date 2017年5月10日 下午11:18:44
 */
public enum LogFlag {
	
	/**
	 * 请求日志
	 */
	REQUEST(1, "REQUEST"),
	/**
	 * 响应日志
	 */
	RESPONSE(2,"RESPONSE"),
	/**
	 * 异常日志
	 */
	EXCEPTION(3, "EXCEPTION"),
	/**
	 * 业务日志
	 */
	BUSINESS(4, "BUSINESS"),
	/**
	 * 内部api日志
	 */
	INNER_API(5, "INNER_API"),
	/**
	 * 其他日志
	 */
	ORTHER(0, "ORTHER");

	private int flag;
	private String name;

	private LogFlag(int flag, String name) {
		this.flag = flag;
		this.name = name;
	}

	public int getFlag() {
		return this.flag;
	}
	
	public String getName() {
		return name;
	}

	public static LogFlag get(int flag) {
		switch (flag) {
		case 1:
			return REQUEST;
		case 2:
			return RESPONSE;
		case 3:
			return EXCEPTION;
		case 4:
			return BUSINESS;
		case 0:
			return ORTHER;
		default:
			return ORTHER;
		}
	}
	
	
	public static String getAllJson() {
		LogFlag[] values = LogFlag.values();
		JsonArray arr = new JsonArray();
		JsonObject jsonObject = null;
		for(LogFlag sysName : values) {
			jsonObject = new JsonObject();
			jsonObject.addProperty("flag", sysName.getFlag());
			jsonObject.addProperty("name", sysName.getName());
			arr.add(jsonObject);
		}
		return arr.toString();
	}
	
}
