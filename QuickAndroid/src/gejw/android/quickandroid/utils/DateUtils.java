package gejw.android.quickandroid.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	/**
	 * 计算两个时间的时间差 返回相差天数
	 * 
	 * @param time1
	 * @param time2
	 * @param format
	 * @return
	 */
	public static long getTimeDifference(String time1, String time2,
			String format) {
		DateFormat df = new SimpleDateFormat(format);

		try {
			Date d1 = df.parse(time1);
			Date d2 = df.parse(time2);
			long diff = d1.getTime() - d2.getTime();
			long days = diff / (1000 * 60 * 60 * 24);
			days = days > 0 ? days : -days;
			return days;
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 计算两个时间的时间差 返回相差天数
	 * 
	 * @param time1
	 * @param time2
	 * @param format
	 * @return
	 */
	public static String DateChange(String time, String formatForm,String formatTo) {
		try {
			Date date  = new SimpleDateFormat(formatForm).parse(time);
			return  new SimpleDateFormat(formatTo).format(date);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * String转为date
	 * 
	 * @param formatstr
	 * @param time
	 * @return
	 */
	public static Date String2Date(String formatstr, String time) {
		DateFormat format = new SimpleDateFormat(formatstr);
		Date date = null;
		try {
			date = format.parse(time);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return date;
	}
}
