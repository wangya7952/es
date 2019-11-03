package com.lotgod.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.common.collect.Lists;
//import com.wuzhenpay.payment.utils.gwslogs.GwsLogger;

/**
 * 日期工具类
 */
public class DateUtil {


	// ==格式到年==
	/**
	 * 日期格式，年份，例如：2004，2008
	 */
	public static final String DATE_FORMAT_YYYY = "yyyy";

	// ==格式到年月 ==
	/**
	 * 日期格式，年份和月份，例如：200707，200808
	 */
	public static final String DATE_FORMAT_YYYYMM = "yyyyMM";

	/**
	 * 日期格式，年份和月份，例如：200707，2008-08
	 */
	public static final String DATE_FORMAT_YYYY_MM = "yyyy-MM";

	// ==格式到年月日==
	/**
	 * 日期格式，年月日，例如：050630，080808
	 */
	public static final String DATE_FORMAT_YYMMDD = "yyMMdd";

	/**
	 * 日期格式，年月日，用横杠分开，例如：06-12-25，08-08-08
	 */
	public static final String DATE_FORMAT_YY_MM_DD = "yy-MM-dd";

	/**
	 * 日期格式，年月日，例如：20050630，20080808
	 */
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

	/**
	 * 日期格式，年月日，用横杠分开，例如：2006-12-25，2008-08-08
	 */
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	/**
	 * 日期格式，年月日，例如：2016.10.05
	 */
	public static final String DATE_FORMAT_POINTYYYYMMDD = "yyyy.MM.dd";

	/**
	 * 日期格式，年月日，例如：2016年10月05日
	 */
	public static final String DATE_TIME_FORMAT_YYYY年MM月DD日 = "yyyy年MM月dd日";

	// ==格式到年月日 时分 ==

	/**
	 * 日期格式，年月日时分，例如：200506301210，200808081210
	 */
	public static final String DATE_FORMAT_YYYYMMDDHHmm = "yyyyMMddHHmm";

	/**
	 * 日期格式，年月日时分，例如：20001230 12:00，20080808 20:08
	 */
	public static final String DATE_TIME_FORMAT_YYYYMMDD_HH_MI = "yyyyMMdd HH:mm";

	public static final String DATE_TIME_FORMAT_YYYYMMDD_HH_MI_SS = "yyyyMMdd HH:mm:ss";

	public static final String DATE_TIME_FORMAT_HH_MI_SS = "HH:mm:ss";

	/**
	 * 日期格式，年月日时分，例如：2000-12-30 12:00，2008-08-08 20:08
	 */
	public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI = "yyyy-MM-dd HH:mm";

	// ==格式到年月日 时分秒==
	/**
	 * 日期格式，年月日时分秒，例如：20001230120000，20080808200808
	 */
	public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISS = "yyyyMMddHHmmss";

	/**
	 * 日期格式，年月日时分秒，年月日用横杠分开，时分秒用冒号分开 例如：2005-05-10 23：20：00，2008-08-08 20:08:08
	 */
	public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";

	// ==格式到年月日 时分秒 毫秒==
	/**
	 * 日期格式，年月日时分秒毫秒，例如：20001230120000123，20080808200808456
	 */
	public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS = "yyyyMMddHHmmssSSS";

	// ==特殊格式==
	/**
	 * 日期格式，月日时分，例如：10-05 12:00
	 */
	public static final String DATE_FORMAT_MMDDHHMI = "MM-dd HH:mm";

	/**
	 * 日期格式，月日时分，例如：12:00
	 */
	public static final String DATE_FORMAT_HHMM = "HH:mm";
	
	/**
	 * 日期格式化工具类
	 * yyyy-MM-dd HH:mm:ss
	 */
	private static DateTimeFormatter df=DateTimeFormat.forPattern(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
	/* ************工具方法*************** */

	/**
	 * 日期格式化工具类
	 * yyyyMMddHHmmss
	 */
	private static DateTimeFormatter sft=DateTimeFormat.forPattern(DATE_TIME_FORMAT_YYYYMMDDHHMISS);
	
	/**
	 * 日期格式化工具类
	 * yyyyMMddHHmmssSSS
	 */
	private static DateTimeFormatter sfts=DateTimeFormat.forPattern(DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS);
	/**
	 * 获取某日期的年份
	 *
	 * @param date
	 * @return
	 */
	public static Integer getYear(Date date) {
		DateTime dateTime = new DateTime(date);
		return dateTime.getYear();
	}

	/**
	 * 获取某日期的月份
	 *
	 * @param date
	 * @return
	 */
	public static Integer getMonth(Date date) {
		DateTime dateTime = new DateTime(date);
		return dateTime.getMonthOfYear();

	}

	/**
	 * 获取某日期的日数
	 *
	 * @param date
	 * @return
	 */
	public static Integer getDay(Date date) {
		DateTime dateTime = new DateTime(date);
		return dateTime.getDayOfMonth();
	}

	/**
	 * 格式化Date时间
	 *
	 * @param time
	 *            Date类型时间
	 * @param timeFormat
	 *            String类型格式
	 * @return 格式化后的字符串
	 */
	public static String parseDateToStr(Date time, String timeFormat) {
		DateTime dateTime = new DateTime(time);
		return dateTime.toString(timeFormat);
	}

	/**
	 * 格式化Timestamp时间
	 *
	 * @param timestamp
	 *            Timestamp类型时间
	 * @param timeFormat
	 * @return 格式化后的字符串
	 */
	public static String parseTimestampToStr(Timestamp timestamp, String timeFormat) {
		DateTime dateTime = new DateTime(timestamp);
		return dateTime.toString(timeFormat);
	}

	public static String parseTimestampToStr(Integer timestamp, String timeFormat) {
		long time = timestamp * 1000L;
		return parseTimestampToStr(new Timestamp(time), timeFormat);
	}
	
	public static String parseTimestampToStr(Long timestamp, String timeFormat) {
		long time = timestamp * 1000L;
		return parseTimestampToStr(new Timestamp(time), timeFormat);
	}

	public static Date parseTimestampToDate(Integer timestamp, String timeFormat) throws ParseException {
		String strTime = parseTimestampToStr(timestamp, timeFormat);
		return parseStrToDate(strTime, timeFormat);
	}

	/**
	 * 根据时间获取时间戳
	 *
	 * @param time
	 * @param timeFormat
	 * @return
	 */
	public static Long parseStrToTimestamp(String time, String timeFormat) {
		try {
			if (StringUtils.isNotBlank(time)) {
				DateTimeFormatter format = DateTimeFormat.forPattern(timeFormat);
				DateTime dateTime = format.parseDateTime(time);
				return Long.parseLong(String.valueOf(dateTime.getMillis() / 1000));
			}
		} catch (Exception e) {
//			GwsLogger.error(e, e.getMessage());
		}
		return 0L;
	}


	/**
	 * 根据时间获取时间戳
	 *
	 * @param time
	 * @return
	 */
	public static Long getTimestamp(Date time) {
		if (null != time) {
			String str = parseDateToStr(time, DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
			return parseStrToTimestamp(str, DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
		}
		return 0L;
	}

	public static Date timestampToDate(Integer timestamp) {
		DateTime time = new DateTime(timestamp * 1000L);
		return time.toDate();
	}
	
	public static DateTime timestampToDate1(Integer timestamp) {
		DateTime time = new DateTime(timestamp * 1000L);
		return time;
	}

	/**
	 * 格式化Date时间
	 *
	 * @param time
	 *            Date类型时间
	 * @param timeFormat
	 *            String类型格式
	 * @param defaultValue
	 *            默认值为当前时间Date
	 * @return 格式化后的字符串
	 */
	public static String parseDateToStr(Date time, String timeFormat, final Date defaultValue) {
		if (null == time || StringUtils.isBlank(timeFormat)) {
			return null;
		}

		try {
			DateTime dateTime = new DateTime(time);
			return dateTime.toString(timeFormat);
		} catch (Exception e) {
			if (defaultValue != null) {
				return parseDateToStr(defaultValue, timeFormat);
			} else {
				return parseDateToStr(new Date(), timeFormat);
			}
		}
	}

	/**
	 * 格式化Date时间
	 *
	 * @param time
	 *            Date类型时间
	 * @param timeFormat
	 *            String类型格式
	 * @param defaultValue
	 *            默认时间值String类型
	 * @return 格式化后的字符串
	 */
	public static String parseDateToStr(Date time, String timeFormat, final String defaultValue) {
		try {
			DateTime dateTime = new DateTime(time);
			return dateTime.toString(timeFormat);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 格式化String时间
	 *
	 * @param time
	 *            String类型时间
	 * @param timeFormat
	 *            String类型格式
	 * @return 格式化后的Date日期
	 * @throws ParseException
	 */
	public static Date parseStrToDate(String time, String timeFormat) {
		try {
			if (time == null || "".equals(time)) {
				return null;
			}
			Date date = null;
			DateTimeFormatter format = DateTimeFormat.forPattern(timeFormat);
			DateTime dateTime = DateTime.parse(time, format);
			date = dateTime.toDate();
			return date;
		} catch (Exception e) {
			e.printStackTrace();
//			GwsLogger.error(e, e.getMessage());
		}
		return null;
	}

	/**
	 * 格式化String时间
	 *
	 * @param strTime
	 *            String类型时间
	 * @param timeFormat
	 *            String类型格式
	 * @param defaultValue
	 *            异常时返回的默认值
	 * @return
	 */
	public static Date parseStrToDate(String strTime, String timeFormat, Date defaultValue) {
		try {
			DateTimeFormatter fomat = DateTimeFormat.forPattern(timeFormat);
			DateTime dateTime = fomat.parseDateTime(strTime);
			return dateTime.toDate();
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 当strTime为2008-9时返回为2008-9-1 00:00格式日期时间，无法转换返回null.
	 *
	 * @param strTime
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDate(String strTime) throws ParseException {
		if (strTime == null || strTime.trim().length() <= 0) {
			return null;
		}

		Date date = null;
		List<String> list = new ArrayList<>(0);

		list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
		list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS);
		list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI);
		list.add(DATE_TIME_FORMAT_YYYYMMDD_HH_MI);
		list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISS);
		list.add(DATE_FORMAT_YYYY_MM_DD);
		list.add(DATE_FORMAT_YYYYMMDD);
		list.add(DATE_FORMAT_YYYY_MM);
		list.add(DATE_FORMAT_YYYYMM);
		list.add(DATE_FORMAT_YYYY);

		for (String format : list) {
			if (strTime.indexOf("-") > 0 && !format.contains("-")) {
				continue;
			}
			if (!strTime.contains("-") && format.indexOf("-") > 0) {
				continue;
			}
			if (strTime.length() > format.length()) {
				continue;
			}
			date = parseStrToDate(strTime, format);
			if (date != null) {
				break;
			}
		}
		return date;
	}

	/**
	 * 解析两个日期之间的所有月份
	 *
	 * @param beginDateStr
	 *            开始日期，yyyyMM
	 * @param endDateStr
	 *            结束日期，yyyyMM
	 *  DOTO 格式必须是年份和日期，不能多和少，否则会报错          
	 * @return yyyyMM日期集合
	 */
	public static List<String> getMonthListOfDate(String beginDateStr, String endDateStr) {
		// 指定要解析的时间格式
		DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM");
		// 返回的月份列表
		String sRet = "";

		// 定义一些变量
		DateTime beginDate = null;
		DateTime endDate = null;

		DateTime beginGC = null;
		DateTime endGC = null;
		List<String> list = new ArrayList<>();

		try {
			// 将字符串parse成日期
			beginDate = f.parseDateTime(beginDateStr);
			endDate = f.parseDateTime(endDateStr);

			// 设置日历
			beginGC = beginDate;

			endGC = endDate;

			// 直到两个时间相同
			while (beginGC.compareTo(endGC) <= 0) {
				sRet = beginGC.getYear() + "-" + beginGC.getMonthOfYear();
				list.add(sRet);
				// 以月为单位，增加时间
				beginGC = beginGC.plusMonths(1);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解析两个日期段之间的所有日期
	 *
	 * @param beginDateStr
	 *            开始日期 ，至少精确到yyyy-MM-dd
	 * @param endDateStr
	 *            结束日期 ，至少精确到yyyy-MM-dd
	 * @return yyyy-MM-dd日期集合
	 */
	public static List<String> getDayListOfDate(String beginDateStr, String endDateStr) {
		// 指定要解析的时间格式
		DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-dd");
		// 定义一些变量
		DateTime beginDate = null;
		DateTime endDate = null;

		DateTime beginGC = null;
		DateTime endGC = null;
		List<String> list = new ArrayList<>();

		try {
			// 将字符串parse成日期
			beginDate = f.parseDateTime(beginDateStr);
			endDate = f.parseDateTime(endDateStr);

			// 设置日历
			beginGC = beginDate;

			endGC = endDate;

			// 直到两个时间相同
			while (beginGC.compareTo(endGC) <= 0) {

				list.add(beginGC.toString(f));
				// 以日为单位，增加时间
				beginGC = beginGC.plusDays(1);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取当下年份指定前后数量的年份集合
	 *
	 * @param before
	 *            当下年份前年数
	 * @param behind
	 *            当下年份后年数
	 * @return 集合
	 */
	public static List<Integer> getYearListOfYears(int before, int behind) {
		if (before < 0 || behind < 0) {
			return null;
		}
		List<Integer> list = new ArrayList<Integer>();
		DateTime dateTime = new DateTime();
		int currYear = dateTime.getYear();

		int startYear = currYear - before;
		int endYear = currYear + behind;
		for (int i = startYear; i < endYear; i++) {
			list.add(i);
		}
		return list;
	}

	/**
	 * 获取当前日期是一年中第几周
	 *
	 * @param date
	 * @return
	 */
	public static Integer getWeekOfYear(Date date) {
		DateTime dateTime = new DateTime(date);
		return dateTime.getWeekOfWeekyear();
	}

	/**
	 * 获取某一年各星期的始终时间 实例：getWeekList(2016)，第52周(从2016-12-26至2017-01-01)
	 *
	 * @param year
	 * @return
	 */
	public static HashMap<Integer, String> getWeekTimeOfYear(int year) {
		HashMap<Integer, String> map = new LinkedHashMap<Integer, String>();
		int count = getWeekCountOfYear(year);

		DateTimeFormatter sdf = DateTimeFormat.forPattern("yyyy-MM-dd");
		String dayOfWeekStart = "";
		String dayOfWeekEnd = "";
		for (int i = 0; i < count; i++) {
			dayOfWeekStart = new DateTime(getFirstDayOfWeek(year, i+1)).toString(sdf);
			dayOfWeekEnd = new DateTime(getLastDayOfWeek(year, i+1)).toString(sdf);
			map.put(i+1, "第" + (i+1) + "周(从" + dayOfWeekStart + "至" + dayOfWeekEnd + ")");
		}
		return map;

	}

	/**
	 * 获取某一年的总周数
	 * 暂时有问题
	 * TODO 通过最后一天去获取某一年所有周数存在结果集为'1'的情况
	 * @param year
	 * @return
	 */
	public static Integer getWeekCountOfYear(int year) {
		DateTime dateTime = new DateTime(year, 12, 29, 0, 0, 0);
		return getWeekOfYear(dateTime.toDate());
	}

	/**
	 * 获取指定日期所在周的第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		DateTime dateTime = new DateTime(date);
		return dateTime.dayOfWeek().withMinimumValue().toDate();
	}

	/**
	 * 获取指定日期所在周的最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		DateTime dd = new DateTime(date);
		return dd.dayOfWeek().withMaximumValue().toDate();
	}

	/**
	 * 获取某年某周的第一天
	 *
	 * @param year
	 *            目标年份
	 * @param week
	 *            目标周数
	 * @return
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		DateTime dateTime = new DateTime(year, 1, 1, 0, 0, 0);
		dateTime = dateTime.withWeekOfWeekyear(week);
		return getFirstDayOfWeek(dateTime.toDate());
	}

	/**
	 * 获取某年某周的最后一天
	 *
	 * @param year
	 *            目标年份
	 * @param week
	 *            目标周数
	 * @return
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		DateTime dateTime = new DateTime(new Date());
		dateTime = dateTime.withWeekOfWeekyear(week);
		return getLastDayOfWeek(dateTime.toDate());
	}

	/**
	 * 获取某年某月的第一天
	 *
	 * @param year
	 *            目标年份
	 * @param month
	 *            目标月份
	 * @return
	 */
	public static Date getFirstDayOfMonth(int year, int month) {
		DateTime dateTime = new DateTime().withYear(year).withMonthOfYear(month);
		return dateTime.dayOfMonth().withMinimumValue().toDate();
	}

	/**
	 * 获取某年某月的最后一天
	 *
	 * @param year
	 *            目标年份
	 * @param month
	 *            目标月份
	 * @return
	 */
	public static Date getLastDayOfMonth(int year, int month) {
		DateTime dateTime = new DateTime().withYear(year).withMonthOfYear(month);
		return dateTime.dayOfMonth().withMaximumValue().toDate();
	}

	/**
	 * 获取某个日期为星期几
	 *
	 * @param date
	 * @return String "星期*"
	 */
	public static String getDayWeekOfDate1(Date date) {
		DateTime dateTime = new DateTime(date);
		return dateTime.dayOfWeek().getAsShortText(Locale.CHINESE);
	}

	/**
	 * 获得指定日期的星期几数
	 * 周一是一
	 * @param date
	 * @return int
	 */
	public static Integer getDayWeekOfDate2(Date date) {
		DateTime dateTime=new DateTime(date);
		return dateTime.getDayOfWeek();
	}

	public static boolean isValidDate(String strTime, String format) {
		Date date = parseStrToDate(strTime.trim(), format);
		return date != null;
	}

	/**
	 * 验证字符串是否为日期
	 * 验证格式:YYYYMMDD、YYYY_MM_DD、YYYYMMDDHHMISS、YYYYMMDD_HH_MI、YYYY_MM_DD_HH_MI、
	 * YYYYMMDDHHMISSSSS、YYYY_MM_DD_HH_MI_SS
	 *
	 * @param strTime
	 * @return null时返回false;true为日期，false不为日期
	 * @throws ParseException
	 */
	public static boolean validateIsDate(String strTime) throws ParseException {
		if (strTime == null || strTime.trim().length() <= 0) {
			return false;
		}

		Date date = null;
		List<String> list = new ArrayList<>(0);

		 list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
	        list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS);
	        list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI);
	        list.add(DATE_TIME_FORMAT_YYYYMMDD_HH_MI);
	        list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISS);
	        list.add(DATE_FORMAT_YYYY_MM_DD);
	        list.add(DATE_FORMAT_YYYYMMDD);

		for (String format : list) {
			if (strTime.indexOf("-") > 0 && !format.contains("-")) {
				continue;
			}
			if (!strTime.contains("-") && format.indexOf("-") > 0) {
				continue;
			}
			if (strTime.length() > format.length()) {
				continue;
			}
			date = parseStrToDate(strTime.trim(), format);
			if (date != null) {
				break;
			}
		}

		return date != null;
	}

	/**
	 * 将指定日期的时分秒格式为零
	 *
	 * @param date
	 * @return
	 */
	public static Date formatHhMmSsOfDate(Date date) {
		DateTime dateTime=new DateTime(date).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
		return dateTime.toDate();
	}

	/**
	 * 获得指定时间加减参数后的日期(不计算则输入0)
	 *
	 * @param date
	 *            指定日期
	 * @param year
	 *            年数，可正可负
	 * @param month
	 *            月数，可正可负
	 * @param day
	 *            天数，可正可负
	 * @param hour
	 *            小时数，可正可负
	 * @param minute
	 *            分钟数，可正可负
	 * @param second
	 *            秒数，可正可负
	 * @param millisecond
	 *            毫秒数，可正可负
	 * @return 计算后的日期
	 */
	public static Date addDate(Date date, int year, int month, int day, int hour, int minute, int second,
			int millisecond) {
		DateTime dateTime=new DateTime(date).plusYears(year).plusMonths(month).plusDays(day).plusHours(hour).plusMinutes(minute)
				.plusSeconds(second).plusMillis(millisecond);
		return dateTime.toDate();
	}

	/**
	 * 获得两个日期的时间戳之差
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Long getDistanceTimestamp(Date startDate, Date endDate) {
		return (endDate.getTime() - startDate.getTime() + 1000000) / (3600 * 24 * 1000);
	}

	/**
	 * 判断二个时间是否为同年同月
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Boolean compareIsSameMonth(Date date1, Date date2) {
		boolean flag = false;
		int year1 = getYear(date1);
		int year2 = getYear(date2);
		if (year1 == year2) {
			int month1 = getMonth(date1);
			int month2 = getMonth(date2);
			if (month1 == month2) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 获得两个时间相差距离多少天多少小时多少分多少秒
	 *
	 * @param one
	 *            时间参数 1 格式：1990-01-01 12:00:00
	 * @param two
	 *            时间参数 2 格式：2009-01-01 12:00:00
	 * @return long[] 返回值为：{天, 时, 分, 秒}
	 */
	public static long[] getDistanceTime(Date one, Date two) {
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			DateTime oneDate=new DateTime(one);
			DateTime twoDate=new DateTime(two);
			
			day=Math.abs(Days.daysBetween(oneDate, twoDate).getDays());
			
			hour=Math.abs(Hours.hoursBetween(oneDate, twoDate).getHours());
			
			min=Math.abs(Minutes.minutesBetween(oneDate, twoDate).getMinutes());
			
			sec=Math.abs(Seconds.secondsBetween(oneDate, twoDate).getSeconds());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new long[] { day, hour, min, sec };
	}

	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒
	 *
	 * @param str1
	 *            时间参数 1 格式：1990-01-01 12:00:00
	 * @param str2
	 *            时间参数 2 格式：2009-01-01 12:00:00
	 * @return String 返回值为：{天, 时, 分, 秒}
	 */
	public static long[] getDistanceTime(String str1, String str2) {
		
		DateTime one;
		DateTime two;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		one = df.parseDateTime(str1);
		two = df.parseDateTime(str2);
		
		day=Math.abs(Days.daysBetween(one, two).getDays());
		
		hour=Math.abs(Hours.hoursBetween(one, two).getHours());
		
		min=Math.abs(Minutes.minutesBetween(one, two).getMinutes());
		
		sec=Math.abs(Seconds.secondsBetween(one, two).getSeconds());
		
		return new long[] { day, hour, min, sec };
	}

	/**
	 * 两个时间之间相差距离多少天
	 *
	 * @param str1
	 *            时间参数 1：
	 * @param str2
	 *            时间参数 2：
	 * @return 相差天数
	 */
	public static Long getDistanceDays(String str1, String str2) throws Exception {
		DateTime one;
		DateTime two;
		long days = 0;
		try {
			one = df.parseDateTime(str1);
			two = df.parseDateTime(str2);
			days=Days.daysBetween(one, two).getDays();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return days;
	}

	/**
	 * 获取指定时间的那天 00:00:00.000 的时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getDayBeginTime(final Date date) {
		DateTime  time=new DateTime(date).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(000);
		return time.toDate();
	}

	/**
	 * 两个时间之间相差距离多少天
	 *
	 * @param ts1
	 *            时间参数 timestamp
	 * @param ts2
	 *            时间参数 timestamp
	 * @return 相差天数
	 */
	public static Long getDistanceDaysByTimestamp(Integer ts1, Integer ts2) {
		DateTime one;
		DateTime two;
		long days = 0;
		try {
			one = timestampToDate1(ts1);
			two = timestampToDate1(ts2);
			days=Days.daysBetween(one, two).getDays();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return days;
	}

	/**
	 * 获取指定时间的那天 23:59:59.999 的时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getDayEndTime(final Date date) {
		DateTime  time=new DateTime(date).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(999);
		return time.toDate();
	}

	/**
	 * 获取昨天的日期
	 *
	 * @return
	 */
	public static Date getYesterday() {
		return getDateRelativeToday(-1);
	}

	/**
	 * 获取日期相对于当天加减天数
	 *
	 * @param day
	 * @return
	 */
	public static Date getDateRelativeToday(Integer day) {
		DateTime dateTime=new DateTime().plusDays(day);
		return dateTime.toDate();
	}
	
	 /**
     * 获取以前的日期
     *
     * @param day
     * @return
     */
    public static Date addDay(Date date, Integer day) {
        DateTime dateTime=new DateTime(date);
        dateTime=dateTime.plusDays(day);
        return dateTime.toDate();
    }

	/**
	 * 生成时间表达式
	 *
	 * @param date
	 *            启动日期
	 * @return
	 */
	public static String toCron(Date date) {
		DateTime dateTime=new DateTime(date);
		return "" + dateTime.getSecondOfMinute() + " " 
				  + dateTime.getMinuteOfHour() + " " + dateTime.getHourOfDay() + " "
				  + dateTime.getDayOfMonth() + " " + dateTime.getMonthOfYear() + " ? " + dateTime.getYear();
	}

	public static String formatDate(Date date, String format) {
		try {
			if (format == null || "".equals(format)) {
				format = "yyyy-MM-dd";
			}
			
			if (date == null) {
				return "";
			} else {
				return new DateTime(date).toString(format);
			}
		} catch (Exception e) {
		}

		return null;
	}

	public static String formatDate(Integer unixSecond, String format) {
		DateTimeFormatter sdf = DateTimeFormat.forPattern(format);
		DateTime date =null;
		if (unixSecond != null) {
			date = new DateTime((long)unixSecond*1000L);
		}else{
			date = new DateTime();
		}
		return date.toString(sdf);
	}

	public static boolean compare(String time1, String time2, String timeFormat) {
		// 如果想比较日期则写成"yyyy-MM-dd"就可以了
		try {
			DateTimeFormatter sdf=DateTimeFormat.forPattern(timeFormat);
			// 将字符串形式的时间转化为Date类型的时间
			DateTime a = sdf.parseDateTime(time1);
			DateTime b = sdf.parseDateTime(time2);
			return a.isAfter(b);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * 获取时间戳，格式“20180420113136502”
	 *
	 * @return
	 */
	public static String getTimeStamp() {
		return formatDate(new Date(), DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS);
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDateStr(String dateFormat) {
		DateTimeFormatter fomate=DateTimeFormat.forPattern(dateFormat);
		return new DateTime().toString(fomate);
	}

	/**
	 * Date to LocalDateTime
	 *
	 * @param date
	 * @return
	 */
	public static LocalDateTime dateToLocalDateTime(Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(instant, zone);
	}

	/**
	 * Date to LocalDate
	 *
	 * @param date
	 * @return
	 */
	public static LocalDate dateToLocalDate(Date date) {
		LocalDateTime localDateTime = DateUtil.dateToLocalDateTime(date);
		return localDateTime.toLocalDate();
	}

	/**
	 * Date to LocalTime
	 *
	 * @param date
	 * @return
	 */
	public static LocalTime dateToLocalTime(Date date) {
		LocalDateTime localDateTime = DateUtil.dateToLocalDateTime(date);
		return localDateTime.toLocalTime();
	}

	/**
	 * LocalDateTime to Date
	 *
	 * @param localDateTime
	 * @return
	 */
	public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDateTime.atZone(zone).toInstant();
		return Date.from(instant);
	}

	/**
	 * LocalDate to Date
	 *
	 * @param localDate
	 * @return
	 */
	public static Date localDateToDate(LocalDate localDate) {
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
		return Date.from(instant);
	}

	public static String getSeqString() {
		DateTime dd=new DateTime();
		return dd.toString(sft);
	}

	/**
	 * 数据库中的时间 分钟
	 *
	 * @return
	 */
	public static String dateAddInteral(Integer createTime, Integer interalMinutes) {
		DateTime dd = conventLong3Date(Long.valueOf(createTime));
		dd=dd.plusMinutes(interalMinutes);
		return dd.toString(sft);

	}

	/**
	 * 将intdata类型转成日期类型
	 *
	 * @return
	 */
	public static Date conventLong2Date(Long payTime) {
		DateTime dateTime=new DateTime(payTime*1000L);
		return dateTime.toDate();
	}

	public static DateTime conventLong3Date(Long payTime) {
		DateTime dateTime=new DateTime(payTime*1000L);
		return dateTime;
	}
	
	public static List<LocalDateTime> getHoursOfDay(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		}
		
		// 设置时分秒为0
		localDateTime = localDateTime.with(LocalTime.MIN);

		ArrayList<LocalDateTime> hours = Lists.newArrayList();
		// 获取24小时时间
		for (int i = 0; i < 24; i++) {
			LocalDateTime temp = localDateTime.withHour(i);
			hours.add(temp);
		}
		return hours;
	}

	/**
	 * 获取两个日期之间的所有日期
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<LocalDateTime> getDaysBetween(LocalDateTime start, LocalDateTime end) {
		if (start == null || end == null) {
			return Collections.emptyList();
		}

		// 获取两个日期相差天数
		Long sub = ChronoUnit.DAYS.between(start, end);

		if (sub < 0L) {
			// 开始日期小于结束日期
			return Collections.emptyList();

		} else if (sub == 0L) {
			// 开始日期等于结束日期
			return Collections.singletonList(start);

		} else {
			// 结果集
			List<LocalDateTime> dates = Lists.newArrayList();
			// 遍历每个日期
			for (Long i = 0L; i <= sub; i++) {
				LocalDateTime localDate = start.plusDays(i);
				dates.add(localDate);
			}
			return dates;
		}
	}

	public static int localDateTimeToEpochSecond(LocalDateTime localDateTime) {
		Long epochSecond = localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
		return epochSecond.intValue();
	}

	public static LocalDateTime epochSecondToLocalDateTime(int seconds) {
		return Instant.ofEpochSecond(seconds).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	/**
	 * 是否在退款时间内
	 *
	 * @param payTime
	 * @param interalDay
	 * @return
	 */
	public static boolean refundBeyondTime(Integer payTime, Integer interalDay) throws Exception {

		DateTime payDate = conventLong3Date(Long.valueOf(payTime));
		if (payDate == null) {
			throw new Exception("日期不能为空");
		}
		DateTime payTimeCal = payDate.plusDays(interalDay);

		DateTime nowCal=new DateTime();

		return payTimeCal.isAfter(nowCal);
	}

	/**
	 * 获取前一天的00:00:00
	 *
	 * @return
	 */
	public static Integer getYesterdayTimestampMin() {
		LocalDateTime time = LocalDateTime.now().minusDays(1).with(LocalTime.MIN);
		return DateUtil.localDateTimeToEpochSecond(time);
	}

	/**
	 * 获取前一天的00:00:00
	 *
	 * @return
	 */
	public static Integer getYesterdayTimestampMin(Integer timestamp) {
		LocalDateTime localDateTime = DateUtil.epochSecondToLocalDateTime(timestamp).minusDays(1).with(LocalTime.MIN);
		return DateUtil.localDateTimeToEpochSecond(localDateTime);

	}

	/**
	 * 获取前一天的23:59:59
	 *
	 * @return
	 */
	public static Integer getYesterdayTimestampMax() {
		LocalDateTime time = LocalDateTime.now().minusDays(1).with(LocalTime.MAX);
		return DateUtil.localDateTimeToEpochSecond(time);
	}

	/**
	 * 指定时间加上任意天数
	 *
	 * @param dateTime
	 * @param date
	 * @return
	 */
	public static Date addDataExpireTime(Date dateTime, int date) {
		if (dateTime != null) {
			DateTime dd=new DateTime(dateTime).plusDays(date);
			return dd.toDate();
		}
		return null;
	}

	/**
	 * 计算date1到date2 之间相差的秒数
	 * 结果为绝对值，非负数
	 * @Title: calculateSecoundLimit   
	 * @param date1
	 * @param date2
	 * @return long      
	 * @throws
	 */
	public static long calculateSecoundLimit(Date date1,Date date2) {
		return Math.abs(date1.getTime()/1000 - date2.getTime()/1000);
	}

	/**
	 * 加减日期
	 * @Title: addDay   
	 * @param dateStr 日期
	 * @param day 天数
	 * @param timeFormat 日期格式
	 * @return String      
	 * @throws
	 */
	public static String addDay(String dateStr, int day, String timeFormat) {
		DateTimeFormatter format = DateTimeFormat.forPattern(timeFormat);
		DateTime dateTime = DateTime.parse(dateStr, format);
		DateTime targetDate = dateTime.plusDays(day);
		return targetDate.toString(format);
	}

	/**
	 * 检测是否指定的格式
	 * @Title: validate   
	 * @param strTime
	 * @param dateFormat
	 * @return boolean      
	 * @throws
	 */
	public static boolean validate(String strTime, String dateFormat) {
		DateTimeFormatter format = DateTimeFormat.forPattern(dateFormat);
		DateTime dateTime = null;
		try {
			dateTime = DateTime.parse(strTime, format);
		}catch(Exception e) {
			//格式错误解析失败
		}
		return dateTime != null;
	}
	
}
