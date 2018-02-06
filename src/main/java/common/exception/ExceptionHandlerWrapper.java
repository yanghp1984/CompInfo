package common.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import common.bean.ResultJsonWrap;

/**
 * 统一异常处理的封装类
 * 
 * @author YH
 * @version 1.0.0 2018-01-09
 */
public abstract class ExceptionHandlerWrapper {
	private Logger logger = LogManager.getLogger(this.getClass());

	/**
	 * 默认的全局异常处理方法
	 * 
	 * @param ex
	 *            Exception
	 * @return 异常结果数据
	 */
	@ExceptionHandler(Exception.class)
	public ResultJsonWrap defaultErrorHandler(Exception ex) {
		logger.error(ex.getMessage(), ex);

		ResultJsonWrap result = new ResultJsonWrap();
		result.setErrorMsg(ex.getMessage(), ex);
		return result;
	}

	/**
	 * 运行时异常处理方法
	 * 
	 * @param ex
	 *            RuntimeException
	 * @return 异常结果数据
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResultJsonWrap runtimeExceptionHandler(RuntimeException ex) {
		logger.error(ex.getMessage(), ex);

		ResultJsonWrap result = new ResultJsonWrap();
		result.setErrorMsg(ex.getMessage(), ex);
		return result;
	}

	/**
	 * Bean Validation 字段验证的异常处理方法
	 * 
	 * @param ex
	 *            MethodArgumentNotValidException
	 * @return 异常结果数据
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResultJsonWrap methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		ResultJsonWrap result = new ResultJsonWrap();
		result.setErrorMsg(ex.getBindingResult().getFieldError().getDefaultMessage());
		return result;
	}

	/**
	 * Bean Binding 异常处理方法
	 * 
	 * @param ex
	 *            BindException
	 * @return 异常结果数据
	 */
	@ExceptionHandler(BindException.class)
	public ResultJsonWrap bindExceptionHandler(BindException ex) {
		ResultJsonWrap result = new ResultJsonWrap();
		result.setErrorMsg(ex.getBindingResult().getFieldError().getDefaultMessage());
		return result;
	}
}
