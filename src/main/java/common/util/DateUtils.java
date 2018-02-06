package common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import common.constant.GlobalConstant;

/**
 * DataUtils, 日期时间工具类
 * 
 * @author bianj
 * @version 1.0.0 2014-12-29
 */
public class DateUtils {
	private static Logger logger = LogManager.getLogger(DateUtils.class);

	/**
	 * 转换 Date 类型为 String
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            日期字符串模式
	 * @return 日期的字符串表示
	 */
	public static String dateToString(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * String 转换为 Date 类型
	 * 
	 * @param value
	 *            String 类型的日期
	 * @param pattern
	 *            转换格式
	 * @return Date Date类型
	 */
	public static Date stringToDate(String value, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(value);
		} catch (Exception e) {
			logger.error("stringToDate ERROR: " + e.getStackTrace());
		}
		return date;
	}

	/**
	 * 获取时间相关的分钟数
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return 相差的分钟数
	 */
	public static long getTimeSpanMinutes(String startTime, String endTime) {
		long minutes = 0;
		if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
			String startStr = startTime.trim();
			if (startStr.length() < 8) {
				startStr += ":00";
			}

			String endStr = endTime.trim();
			if (endStr.length() < 8) {
				endStr += ":00";
			}

			Date startDate = stringToDate(startStr, GlobalConstant.TIME_PATTERN);
			Date endDate = stringToDate(endStr, GlobalConstant.TIME_PATTERN);
			long diff = 0;
			if (startDate.compareTo(endDate) < 0) {
				diff = endDate.getTime() - startDate.getTime();
			} else if (startDate.compareTo(endDate) > 0) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(endDate);
				calendar.add(Calendar.DATE, 1);
				diff = calendar.getTime().getTime() - startDate.getTime();
			}
			minutes = diff / (1000 * 60);
		}
		return minutes;
	}

	/**
	 * 判断当前时间是否在指定的时间区间内
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return True 表示是，False 表示否。
	 */
	public static boolean isAtInterval(String startTime, String endTime) {
		boolean flag = false;
		if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
			String startStr = startTime.trim();
			if (startStr.length() < 8) {
				startStr += ":00";
			}

			String endStr = endTime.trim();
			if (endStr.length() < 8) {
				endStr += ":00";
			}

			Date startDate = stringToDate(startStr, GlobalConstant.TIME_PATTERN);
			Date endDate = stringToDate(endStr, GlobalConstant.TIME_PATTERN);

			Date today = new Date();
			String todayStr = dateToString(today, GlobalConstant.DATE_PATTERN);
			Date startDateTime = stringToDate(todayStr + " " + startStr, GlobalConstant.DATE_TIME_PATTERN);
			Date endDateTime = stringToDate(todayStr + " " + endStr, GlobalConstant.DATE_TIME_PATTERN);

			if (startDate.compareTo(endDate) < 0) {
				if (today.compareTo(startDateTime) >= 0 && today.compareTo(endDateTime) <= 0) {
					flag = true;
				}
			} else if (startDate.compareTo(endDate) == 0) {
				flag = true;
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(startDateTime);
				calendar.add(Calendar.DATE, -1);
				if (today.compareTo(calendar.getTime()) >= 0 && today.compareTo(endDateTime) <= 0) {
					flag = true;
				}
			}
		}
		return flag;
	}

	// public static void main(String[] args) {
	// String startDate = "23:00";
	// String endDate = "08:00:30";
	// System.out.println(getTimeSpanMinutes(startDate, endDate));
	// System.out.println(isAtInterval(startDate, endDate));
	// }
}
