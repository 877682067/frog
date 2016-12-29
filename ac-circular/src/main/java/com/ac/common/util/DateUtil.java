package com.ac.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class DateUtil {
	private static Logger log = Logger.getLogger(DateUtil.class);
	private static  String  DEFAULT_FORMAT = "yyy-MM-dd hh:mm:ss";
	
	/**
	 * 字符串转换日期 (格式:yyy-MM-dd hh:mm:ss)
	 * @param dateStr
	 * @return
	 */
	public static Date formatDefaultDate(String dateStr) {

		return formatDate(dateStr,DEFAULT_FORMAT);
	}
	/**
	 * 字符串转换日期
	 * @param dateStr 日期字符串
	 * @param format 日期格式 (默认:yyy-MM-dd hh:mm:ss)
	 * @return
	 */
	public static Date formatDate(String dateStr,String  format)
	{
		if(StringUtils.isBlank(format))
		{
			format = DEFAULT_FORMAT;
		}
		DateFormat df = new SimpleDateFormat(format);
		Date retrn = null;
		try {
			retrn = df.parse(dateStr);
		} catch (ParseException e) {
			log.error(e);
		}
		return retrn;
	}

}
