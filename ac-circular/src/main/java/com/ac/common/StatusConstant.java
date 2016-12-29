package com.ac.common;

public class StatusConstant {

	
	public static class Status
	{
		public static int DELET = -1;
		public static int SAVE =  1;
	}
	
	public static class CircularType
	{
		/**
		 * 促销
		 */
		public static int PROMOTION = 1;

		/**
		 * 业务
		 */
		public static int BUSINESS  = 2;
	}
	public static class AttachType
	{
		/**
		 * 	公告
		 */
		public static int CIRCULAR = 1;
		
	}
	
	
	public static class ValidType
	{		
		/**
		 * 校验表单
		 */
		public static String  VALIDATE = "validate";
		/**
		 * 取消绑定校验事件
		 */
		public static String  DETACH = "detach";
		/**
		 * //绑定校验事件
		 */
		public static String  ATTACH = "attach";
	}
	/**
	 * select2的数据获取类型
	 * @author swallow
	 *
	 */
	public  enum DataType
	{
		AJAX(1),SCRIPT(2);
		private int value;
		DataType(int type)
		{
			this.value =  type;
		}
	}
	/**
	 * 校验类型
	 * @author swallow
	 *
	 */
	public static class InputCustom
	{
		/**
		 *  ipv4 地址
		 */
		public static final String IPV4 = "ipv4";
		/**
		 *  url 地址，需以 http://、https:// 或 ftp:// 开头
		 */
		public static final String URL = "url";
		/**
		 * 日期及时间格式，格式为：YYYY/MM/DD hh:mm:ss AM|PM
		 */
		public static final String DATE_TIME_FORMAT = "dateTimeFormat";
		/**
		 * 日期格式，格式为 YYYY/MM/DD、YYYY/M/D、YYYY-MM-DD、YYYY-M-D
		 */
		public static final String DATE_FORMAT = "dateFormat";
		/**
		 * 日期，格式为 YYYY/MM/DD、YYYY/M/D、YYYY-MM-DD、YYYY-M-D
		 */
		public static final String DATE = "date";
		/**
		 * 数字
		 */
		public static final String NUMBER = "number";
		/**
		 * 整数
		 */
		public static final String INTEGER = "integer";
		/**
		 *  Email 地址
		 */
		public static final String EMAIL = "email";
		/**
		 * 电话号码
		 */
		public static final String PHONE = "phone";
		/**
		 * 数字和空格
		 */
		public static final String ONLY_NUMBERSP = "onlyNumberSp";
		/**
		 * 英文字母（大小写）和单引号(')
		 */
		public static final String ONLY_LETTERSP = "onlyLetterSp";
		/**
		 * 数字和英文字母
		 */
		public static final String ONLY_LETTER_NUMBER = "onlyLetterNumber";
		
		
	}
	
	
	
}
