package common.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import common.constant.GlobalConstant;
import common.exception.ExceptionHandlerWrapper;

/**
 * 由其它 Controller 继承的基础 Controller
 * 
 * @author bianj
 * @version 1.0.0 2017-07-02
 */
public abstract class BaseController extends ExceptionHandlerWrapper {
	/** 返回登录页面 */
	protected static final String LOGIN_PAGE = GlobalConstant.LOGIN_PAGE;

	/** 返回主页面 */
	protected static final String INDEX_PAGE = GlobalConstant.INDEX_PAGE;

	/** 返回错误页面 */
	protected static final String ERROR_PAGE = GlobalConstant.ERROR_PAGE;

	/** 在请求中保存提示信息的 Key */
	protected static final String MSG_KEY = GlobalConstant.MSG_KEY;

	/** 在请求中保存错误信息的 Key */
	protected static final String ERROR_MSG_KEY = GlobalConstant.ERROR_MSG_KEY;

	/** 请求头，用于兼容IE浏览器，IE浏览器不支持JSON的请求头，会让浏览器下载返回的JSON数据 */
	protected static HttpHeaders headers = new HttpHeaders();

	static {
		headers.setContentType(MediaType.TEXT_HTML);
	}
}
