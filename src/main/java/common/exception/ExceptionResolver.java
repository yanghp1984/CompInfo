package common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import common.constant.GlobalConstant;

/**
 * SpringMVC 统一异常处理接口
 * 
 * @author bianj
 * @version 1.0.0 2017-07-10
 */
public class ExceptionResolver implements HandlerExceptionResolver {
	private static final Logger logger = LogManager.getLogger(ExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception ex) {
		logger.error(ex.getMessage(), ex);
		
		String exceptionMsg = null;
		if (ex != null) {
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
			exceptionMsg = sw.toString();
		}
		
		ModelAndView model = new ModelAndView(GlobalConstant.ERROR_PAGE);
		model.addObject(GlobalConstant.ERROR_MSG_KEY, "系统错误!");
		model.addObject("exceptionMsg", exceptionMsg);
		return model;
	}

}
