package common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.StringConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import common.constant.GlobalConstant;

/**
 * JavaBean 工具类
 * 
 * @author yanghp1984
 * @version 1.0.0 2017-12-18
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class JavaBeanUtils {
	private static Logger logger = LogManager.getLogger(JavaBeanUtils.class);
	
	/** 空时间 */
	private static final String EMPTY_TIME = "00:00:00";
	
	static {
		// 添加 Date to String 的转换器
		ConvertUtils.convert(new Converter() {
			@Override
			public Object convert(Class type, Object value) {
				if (value != null && value instanceof Date) {
					String time = DateUtils.dateToString((Date) value, GlobalConstant.TIME_PATTERN);
					if (time.equals(EMPTY_TIME)) {
						return DateUtils.dateToString((Date) value, GlobalConstant.DATE_PATTERN);
					} else {
						return DateUtils.dateToString((Date) value, GlobalConstant.DATE_TIME_PATTERN);
					}
				}
				return new StringConverter().convert(type, value);
			}
		}, String.class);
		
		// 添加 String to Date 的转换器
		ConvertUtils.register(new Converter() {
			@Override
			public Object convert(Class type, Object value) {
				Object date = null;
				if (value != null) {
					if (value instanceof Date) {
						date = value;
					} else {
						String dateString = (String) value;
						if (dateString.length() > 10) {
							date = DateUtils.stringToDate(dateString, GlobalConstant.DATE_TIME_PATTERN);
						} else {
							date = DateUtils.stringToDate(dateString, GlobalConstant.DATE_PATTERN);
						}
					}
				}
				return date;
			}
		}, Date.class);
	}

	/**
	 * 将 JavaBean 转换成 Map
	 * 
	 * @param bean
	 * @return
	 */
	public static Map<String, String> beanToStringMap(Object bean) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			map = BeanUtils.describe(bean);
		} catch (Exception e) {
			logger.error("beanToStringMap ERROR: " + e.getMessage());
		}
		return map;
	}

	/**
	 * 将 JavaBean 转换成 Map
	 * 
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> beanToObjectMap(Object bean) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = PropertyUtils.describe(bean);
		} catch (Exception e) {
			logger.error("beanToObjectMap ERROR: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * 将 Map 转换成 JavaBean
	 * @param map
	 * @param bean
	 */
	public static void mapToBean(Map<String, ? extends Object> map, Object bean) {
		try {
			BeanUtils.populate(bean, map);
		} catch (Exception e) {
			logger.error("mapToBean ERROR: " + e.getMessage());
		}
	}
	
//	public static void main(String[] args) {
//		Date date = new Date();
//		String value = DateUtils.dateToString(date, GlobalConstant.DATE_TIME_PATTERN);
//		System.out.println(value);
//		System.out.println(DateUtils.stringToDate(value, GlobalConstant.DATE_TIME_PATTERN));
//	}
}
