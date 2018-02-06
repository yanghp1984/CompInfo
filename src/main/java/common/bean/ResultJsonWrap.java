package common.bean;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 用于返回 JSON 结果数据的封装
 * 
 * @author bianj
 * @version 1.0.0 2017-07-15
 */
public class ResultJsonWrap extends ToStringBean {
	private static final long serialVersionUID = -3653950610409197916L;

	private boolean flag = true;
	private String code;
	private String msg = "调用成功";
	private Object data = "{}";
	private String exStackTrace;
	
	public void setErrorMsg(String msg) {
		this.flag = false;
		this.msg = msg;
	}
	
	public void setErrorMsg(String msg, Throwable ex) {
		this.flag = false;
		this.msg = msg;
		if (ex != null) {
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
			this.exStackTrace = sw.toString();
		}
	}
	
	/**
	 * 是否成功
	 * @return True 表示成功，False 表示失败。
	 */
	public boolean isFlag() {
		return flag;
	}
	
	/**
	 * 设置是否成功
	 * @param flag 是否成功
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	/**
	 * 获取成功或者错误的编码
	 * @return 成功或者错误的编码
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * 设置成功或者错误的编码
	 * @param code 成功或者错误的编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 获取调用接口返回的消息
	 * @return 调用接口返回的消息
	 */
	public String getMsg() {
		return msg;
	}
	
	/**
	 * 设置调用接口返回的消息
	 * @param msg 调用接口返回的消息
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * 获取返回的数据对象
	 * @return 返回的数据对象
	 */
	public Object getData() {
		return data;
	}
	
	/**
	 * 设置返回的数据对象
	 * @param data 返回的数据对象
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * 获取异常堆栈信息
	 * @return 异常堆栈信息
	 */
	public String getExStackTrace() {
		return exStackTrace;
	}
	
	/**
	 * 设置异常堆栈信息
	 * @param exStackTrace 异常堆栈信息
	 */
	public void setExStackTrace(String exStackTrace) {
		this.exStackTrace = exStackTrace;
	}
}
