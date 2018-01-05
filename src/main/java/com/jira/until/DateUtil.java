package com.jira.until;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期帮助类
 * @author Administrator
 *
 */
public class DateUtil {
	// 把date 类型的转化成字符串类型的
		public static String getStrFromDate(Date date, String format) {
			String str = null;
			SimpleDateFormat df = new SimpleDateFormat(format);
			str = df.format(date);
			return str;
		}

		// 把字符串类型的转化成Date类型的
		public static Date getDateFromStr(String str, String format) {
			Date date = null;
			SimpleDateFormat df = new SimpleDateFormat(format);
			try {
				date = df.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}

		// 把字符串转化为合适类型
		public static String getStrFromStr(String str, String format) {
			String newStr = null;
			Date date = null;
			SimpleDateFormat df = new SimpleDateFormat(format);
			try {
				date = df.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			newStr = df.format(date);
			return newStr;
		}

		// 把date转化为合适的类型
		public static Date getDateFromDate(Date date, String format) {
			Date newDate = null;
			String str = null;
			SimpleDateFormat df = new SimpleDateFormat(format);
			str = df.format(date);
			System.out.println(str);
			try {
				newDate = df.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return newDate;

		}

		// 本日早
		public static Date getTimesmorning() {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.MILLISECOND, 0);
			return cal.getTime();
		}

		// 本日晚
		public static Date getTimesnight() {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 24);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.MILLISECOND, 0);
			return cal.getTime();
		}

		// 本周最前
		public static Date getTimesWeekmorning() {
			Calendar cal = Calendar.getInstance();
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),
					cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			return cal.getTime();
		}

		// 本周最后
		public static Date getTimesWeekNight() {
			Calendar cal = Calendar.getInstance();
			cal.setTime(getTimesWeekmorning());
			cal.add(Calendar.DAY_OF_WEEK, 7);
			return cal.getTime();
		}

		// 本月最前
		public static Date getTimesMonthmorning() {
			Calendar cal = Calendar.getInstance();
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),
					cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			cal.set(Calendar.DAY_OF_MONTH,
					cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			return cal.getTime();
		}

		// 本月最后
		public static Date getTimesMonthNight() {
			Calendar cal = Calendar.getInstance();
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),
					cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			cal.set(Calendar.DAY_OF_MONTH,
					cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			cal.set(Calendar.HOUR_OF_DAY, 24);
			return cal.getTime();
		}
}
