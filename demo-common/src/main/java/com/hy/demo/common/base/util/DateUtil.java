package com.hy.demo.common.base.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Locale;

/**
 * 日期格式转换，处理工具
 */
public final class DateUtil {

	private DateUtil() {
	}
	
	public static final String yy = "yy";
	public static final String yyyy = "yyyy";
	public static final String MM = "MM";
	public static final String dd = "dd";
	public static final String HH = "HH";
	public static final String mm = "mm";
	public static final String ss = "ss";
	public static final String SSS = "SSS";
	
	public static final String yyyy_MM = yyyy + "-" + MM;
	public static final String yyyy_MM_dd = yyyy_MM + "-" + dd;
	public static final String yyyy_MM_dd_HH = yyyy_MM_dd + " " + HH;
	public static final String yyyy_MM_dd_HH_mm = yyyy_MM_dd_HH + ":" + mm;
	public static final String yyyy_MM_dd_HH_mm_ss = yyyy_MM_dd_HH_mm + ":" + ss;
	public static final String yyyy_MM_dd_HH_mm_ss_SSS = yyyy_MM_dd_HH_mm_ss + "." + SSS;
	
	public static final String yyMM = yy + MM;
	public static final String yyMMdd = yyMM + dd;
	
	public static final String yyyyMM = yyyy + MM;
	public static final String yyyyMMdd = yyyyMM + dd;
	public static final String yyyyMMddHH = yyyyMMdd + HH;
	public static final String yyyyMMddHHmm = yyyyMMddHH + mm;
	public static final String yyyyMMddHHmmss = yyyyMMddHHmm + ss;
	public static final String yyyyMMddHHmmssSSS = yyyyMMddHHmmss + SSS;
	
	public static final ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
	public static final ZoneId zoneId = ZoneId.systemDefault();

	/**
	 * 转换
	 * @param date
	 * @return
	 */
	public static LocalDateTime convertLdt(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), zoneId);
    }
    
    public static Date convertDate(LocalDateTime ldt) {
        return Date.from(ldt.atZone(zoneId).toInstant());
    }
    
    public static long convertSecond(LocalDateTime ldt) {
        return ldt.toEpochSecond(zoneOffset);
    }
    
    public static long convertMilli(LocalDateTime ldt) {
    	return ldt.toInstant(zoneOffset).toEpochMilli();
    }
	
	/**
	 * now
	 * @return
	 */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
    
    public static long nowSecond() {
        return convertSecond(now());
    }

	public static int nowSecondInt() {
		return Integer.parseInt(String.valueOf(nowSecond()));
	}
    
    public static Long nowMilli() {
    	return convertMilli(now());
    }
    
    /**
     * format
     * @param ldt
     * @param pattern
     * @return
     */
    public static String format(LocalDateTime ldt, String pattern) {
    	return ldt.format(DateTimeFormatter.ofPattern(pattern));
    }
    
    public static String format(LocalDateTime ldt) {
    	return format(ldt, yyyy_MM_dd);
    }
    
    public static String format2(LocalDateTime ldt) {
    	return format(ldt, yyyy_MM_dd_HH_mm_ss);
    }
    
    /**
     * parse
     * @param str
     * @param pattern
     * @return
     */
    public static LocalDateTime parse(String str, String pattern) {
    	return LocalDateTime.parse(str, new DateTimeFormatterBuilder()
    			.appendPattern(pattern)
    			// 月
    			.parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
    			// 日
    			.parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
    			// 时
    			.parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
    			// 分
    			.parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
    			// 秒
    			.parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
    			.toFormatter(Locale.CHINA));
    }
    
    public static LocalDateTime parse(String str) {
    	return parse(str, yyyy_MM_dd);
    }
    
    public static LocalDateTime parse2(String str) {
    	return parse(str, yyyy_MM_dd_HH_mm_ss);
    }
    
    /**
     * 相差
     * @param ldt1
     * @param ldt2
     * @return
     */
    public static Duration between(LocalDateTime ldt1, LocalDateTime ldt2) {
    	return Duration.between(ldt1, ldt2);
    }
    
    
    public static void main(String[] args) {
    //	log.info("");
		/*
		LocalDateTime ldt = parse("2019-07-07", yyyy_MM_dd);
		System.out.println(ldt);
		String format = format(ldt, yyyy_MM_dd_HH_mm_ss_SSS);
		System.out.println(format);
		*/
	}
}
