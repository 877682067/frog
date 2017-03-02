package com.baoku.common.reflection.form;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.baoku.common.utils.DateUtils;

public class AnalysisRequestForm{

	 public static <T>  T analysisForm( Class<T> clazz, HttpServletRequest request) {
	    	T obj = null;
	    	Map<String,String> parameters = request.getParameterMap();
	    	try {
	            //根据 class生成对象
	    		obj = analysisObject(null,clazz,request);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	/*   	log.error("", e);*/
	        } 
	        return obj;
	    }
	    
	    public static <T> T analysisObject(String parentFields ,Class<T> clazz,  HttpServletRequest request) throws Exception
	    {
	    	
			T obj = (T) clazz.newInstance();
	        Field[] fileds = clazz.getDeclaredFields();
	        //遍历属性结合
	        for (Field f : fileds) {
	        	 //过滤被final、static修饰的成员变量
	            if ((f.getModifiers() & Modifier.FINAL) > 0
	                || (f.getModifiers() & Modifier.STATIC) > 0) {
	                continue;
	            }
	            String key = getKey(parentFields, f.getName());
	            //取消所有私有变量的限制
	            f.setAccessible(true);//取消Field的访问检查
	            Object value = null;
	            if(isSimpleField(f.getType()))
	            {
	            	value = requstToSimpleField(key,f,request);	
	            }
	            else
	            {	//处理集合
	            	 if(isCollection(f.getType()))
	                 {
	            		 value = AnalysisFormCollection.analysisRequstToCollection(key,f,request);
	                 }
	            	 else
	            	 {
	            		 value = analysisObject(key,f.getType(),  request);
	            	 }
	            }
	            f.set(obj, value);
	        }
	    	
	    	return obj;
	    }
	    /**
	     * 判断 类是否实现 collectio接口
	     * @param type
	     * @return
	     */
	    private static boolean isCollection(Class<?> type) {
	    	boolean isCollection = false;
	    	Class<?>[] interfaces = type.getInterfaces();
	    	for(Class<?> clazz :interfaces)
	    	{
	    		if(clazz == Collection.class)
	    		{
	    			isCollection = true;
	    			break;
	    		}
	    	}
			return isCollection;
		}

		//解析http报文中的对象  节后a.b.c
	   /* public  static Object analysisRequstToObject(String  parentFilds,Field now, HttpServletRequest request) throws Exception
	    {
	    	return analysisObject(parentFilds,now.getType(),request);
	    }*/
	    /**
	     * 获取当前属性在request中的Key
	     * @param parentFields  a.b.c
	     * @param now 当前 字段名
	     * @return
	     */
	    private static String getKey(String parentFields,String now)
	    {
	    	String key="";
	    	if(StringUtils.isNotBlank(parentFields))
	    	{
	    		key = parentFields;
	    		key +=".";
	    	}
	    	key += now;
	    	return key;
	    }
	    public static boolean isSimpleField(Class<?> fieldType)
	    {
	    	boolean isSimpleField = false;
		    if (String.class == fieldType) {
		    	isSimpleField = true;
		    } 
		    else if (long.class == fieldType || Long.class == fieldType) 
		    {
		    	isSimpleField = true;
		    } 
		    else if (float.class == fieldType || Float.class == fieldType) 
		    {
		    	isSimpleField = true;
		    } 
		    else if (double.class == fieldType || Double.class == fieldType) 
		    {
		    	isSimpleField = true;
		    } 
		    else if (int.class == fieldType || Integer.class == fieldType) 
		    {
		    	isSimpleField = true;
		    }
		    else if (boolean.class == fieldType || Boolean.class == fieldType) 
		    {
		    	isSimpleField = true;
		    }  
		    else if (Date.class == fieldType) 
		    {
		    	isSimpleField = true;
		    }
		    return isSimpleField;
	    }
	    
	    
	    public static Object requstToSimpleField(String key,Field now,  HttpServletRequest request) throws Exception
	    {
	    	 Class<?> fieldType = now.getType();
	         //获取属性的名字
	         String fieldName = now.getName();
	         //根据属性的名字从request中获取value
	         String paramVal = request.getParameter(key);
	         if(StringUtils.isBlank(paramVal))
		         	return null;
	         //如果是空不操作
	         
	         return getSimpleValue(fieldType, paramVal);
	    }

		public static Object getSimpleValue(Class<?> fieldType, Object paramVal) throws Exception {
			if(paramVal==null)
				return null;
			if (String.class == fieldType) {
	        	 return  paramVal;
	         } 
	         else if (long.class == fieldType || Long.class == fieldType) 
	         {
	             return NumberUtils.toLong(paramVal.toString());
	         } 
	         else if (float.class == fieldType || Float.class == fieldType) 
	         {
	        	 return  NumberUtils.toFloat(paramVal.toString());
	         } 
	         else if (double.class == fieldType || Double.class == fieldType) 
	         {
	        	 return NumberUtils.toDouble(paramVal.toString());
	         } 
	         else if (int.class == fieldType || Integer.class == fieldType) 
	         {
	        	 return NumberUtils.toInt(paramVal.toString());
	         }
	         else if (boolean.class == fieldType || Boolean.class == fieldType) 
	         {
	        	 return Boolean.valueOf(paramVal.toString());
	         }  
	         else if (Date.class == fieldType) {
	        	 if (long.class ==  paramVal.getClass() || Long.class ==  paramVal.getClass())
	        	 {
	        		 return new Date(Long.valueOf(paramVal.toString()));
	        	 }
	        	 
	        	 return DateUtils.convertStringToDate(paramVal.toString(),DateUtils.YYYYMMDDHHMMSSS);
	         }
	         return paramVal;
		}
}
