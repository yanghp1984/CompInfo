package common.constant;

/**
 * 全局常量
 * 
 * @author yanghp1984
 * @version 1.0.0 2017-12-18
 */
public class GlobalConstant {
	/** UTF-8 字符编码 */
	public static final String UTF8_ENCODE = "UTF-8";

	/** GBK 字符编码 */
	public static final String GBK_ENCODE = "GBK";

	/** 默认时区 */
	public static final String DEFAULT_TIMEZONE = "GMT+8";

	/** 日期格式 */
	public static final String DATE_PATTERN = "yyyy-MM-dd";

	/** 时间格式 */
	public static final String TIME_PATTERN = "HH:mm:ss";

	/** 简单日期格式 */
	public static final String SIMPLE_DATE_PATTERN = "yyyyMMdd";

	/** 简单时间格式 */
	public static final String SIMPLE_TIME_PATTERN = "HHmmss";

	/** 日期时间格式 */
	public static final String DATE_TIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;

	/** 简单日期时间格式 */
	public static final String SIMPLE_DATE_TIME_PATTERN = SIMPLE_DATE_PATTERN + " " + SIMPLE_TIME_PATTERN;

	/** 默认每页显示多少条记录 */
	public static final int DEFAULT_PAGE_SIZE = 20;

	/** 默认 HASH 散列计算算法 ，只支持 SHA-256, SHA-1, MD5 */
	public static final String HASH_COMPUTE_TYPE = "SHA-256";

	/** 登录页面 */
	public static final String LOGIN_PAGE = "login";

	/** 主页面 */
	public static final String INDEX_PAGE = "index";

	/** 错误页面 */
	public static final String ERROR_PAGE = "error";

	/** 在请求中保存提示信息的 Key */
	public static final String MSG_KEY = "msg";

	/** 在请求中保存错误信息的 Key */
	public static final String ERROR_MSG_KEY = "errorMsg";

	/** 在请求中保存返回路径的 Key */
	public static final String RETURN_PATH_KEY = "returnPath";

	/** 在请求中保存用户信息的 Key */
	public static final String LOGIN_USER_KEY = "loginUser";
}
