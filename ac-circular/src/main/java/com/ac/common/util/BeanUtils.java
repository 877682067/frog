package com.ac.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.log4j.Logger;

public class BeanUtils {
	private  static Logger log = Logger.getLogger(BeanUtils.class);
	/**
     * 将HttpServletRequest中的参数反射至实体类
     * @param <T>
     * @param clazz
     * @param request
     * @return
     */
    public static <T>  T reflectParameter( Class<T> clazz, HttpServletRequest request) {
    	T obj = null;
    	try {
            //根据 class生成对象
        	obj = (T) clazz.newInstance();
            Field[] fileds = clazz.getDeclaredFields();
            //遍历属性结合
            for (Field f : fileds) {
                //过滤被final、static修饰的成员变量
                if ((f.getModifiers() & Modifier.FINAL) > 0
                    || (f.getModifiers() & Modifier.STATIC) > 0) {
                    continue;
                }
                //取消所有私有变量的限制
                f.setAccessible(true);//取消Field的访问检查
                //获取属性的类型,long/int/string....
                Class<?> fieldType = f.getType();
                //获取属性的名字
                String fieldName = f.getName();
                //根据属性的名字从request中获取value
                String paramVal = request.getParameter(fieldName);
                //如果是空不操作
                if(StringUtils.isBlank(paramVal))
                	continue;
                if (String.class == fieldType) {
                	f.set(obj, paramVal);
                } 
                else if (long.class == fieldType || Long.class == fieldType) 
                {
                    f.set(obj, NumberUtils.toLong(paramVal));
                } 
                else if (float.class == fieldType || Float.class == fieldType) 
                {
                    f.set(obj, NumberUtils.toFloat(paramVal));
                } 
                else if (double.class == fieldType || Double.class == fieldType) 
                {
                    f.set(obj, NumberUtils.toDouble(paramVal));
                } 
                else if (int.class == fieldType || Integer.class == fieldType) 
                {
                    f.set(obj, NumberUtils.toInt(paramVal));
                }
                else if (boolean.class == fieldType || Boolean.class == fieldType) 
                {
                    f.set(obj,Boolean.valueOf(paramVal));
                }  else if (Date.class == fieldType) {
                    f.set(obj, DateUtil.formatDefaultDate(paramVal));
                }
            }
        } catch (IllegalArgumentException e) {
        	log.error("", e);
        } catch (IllegalAccessException e) {
        	log.error("", e);
        } catch (InstantiationException e) {
        	log.error("", e);
		}
        return obj;
    }
    
    public static Map requestToMap( HttpServletRequest request)
    {
    	  Map properties = request.getParameterMap();  
    	    // 返回值Map  
    	    Map returnMap = new HashMap();  
    	    Iterator entries = properties.entrySet().iterator();  
    	    Map.Entry entry;  
    	    String name = "";  
    	    String value = "";  
    	    while (entries.hasNext()) {  
    	        entry = (Map.Entry) entries.next();  
    	        name = (String) entry.getKey();  
    	        Object valueObj = entry.getValue();  
    	        if(null == valueObj){  
    	            value = "";  
    	        }else if(valueObj instanceof String[]){  
    	            String[] values = (String[])valueObj;  
    	            for(int i=0;i<values.length;i++){  
    	                value = values[i] + ",";  
    	            }  
    	            value = value.substring(0, value.length()-1);  
    	        }else{  
    	            value = valueObj.toString();  
    	        }  
    	        returnMap.put(name, value);  
    	    }  
    	    return returnMap;  
    	
    }
    
	public  static Object getFieldValue(Object obj,String fieldName)
	{
		
		Object retn = null;
		
			for(Class cls = obj.getClass(); cls!=Object.class;cls = cls.getSuperclass())
			{
				Field field;
				try {
					field = cls.getDeclaredField(fieldName);
					if(field != null)
					{
						field.setAccessible(true);
						retn = field.get(obj);
						break;
					}
				} catch (Exception e) {
					//do nothing
				} 
			}
		return retn;
	}
	public static void setFieldValue(Object obj, String fieldName,
			String fieldValue) {
		Field field = BeanUtils.getField(obj, fieldName);
        if (field != null) {
           try {
               field.setAccessible(true);
               field.set(obj, fieldValue);
           } catch (IllegalArgumentException e) {
               e.printStackTrace();
           } catch (IllegalAccessException e) {
               e.printStackTrace();
           }
        }
		
	}
	
	  /**
     * 利用反射获取指定对象里面的指定属性
     * @param obj 目标对象
     * @param fieldName 目标属性
     * @return 目标字段
     */
    public static Field getField(Object obj, String fieldName) {
        Field field = null;
       for (Class<?> clazz=obj.getClass(); clazz != Object.class; clazz=clazz.getSuperclass()) {
           try {
               field = clazz.getDeclaredField(fieldName);
               break;
           } catch (NoSuchFieldException e) {
               //这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
           }
        }
        return field;
    }
    
    /**
     * 从json对象中获取 某个属性的值
     * @param field
     * @param obj
     * @return
     * @throws ClassNotFoundException
     */
    public static Object getFieldValueFromJSONObject(Field field, JSONObject obj) throws ClassNotFoundException{
		
		Object value= obj.get(field.getName());
		if(value instanceof  JSONObject)
		{
			return analysisJSONObj(field.getType(),(JSONObject)value);
		}
		else  if(value instanceof JSONArray)
		{
			String generic = getGenericClass(field.getGenericType());
			return JSONArrayToList(Class.forName(generic),(JSONArray)value);
		}
		else
		{
			return value;
		}
	}
	/**
	 * 获取泛型类的泛型类型
	 * @param genericType
	 * @return
	 */
	public static String getGenericClass(Type genericType) {
		String type = genericType.toString();
		int index  = type.indexOf(" ");
		if(index <0)
		{
			 index  = type.indexOf("<");
			 int max  = type.indexOf(">");
			 return type.substring(index+1, max);
		}
		return type.substring(index+1);
	}
	/**
	 * json数组转换为List
	 * @param <T>
	 * @param clas
	 * @param values
	 * @return
	 */
	public  static <T> List<T> JSONArrayToList(Class<T> clas, JSONArray values) {
		List<T> ls = new LinkedList<T>();
		@SuppressWarnings("unchecked")
		Iterator<JSONObject> it  = values.iterator();
		while(it.hasNext())
		{
			ls.add(analysisJSONObj(clas,it.next()));
		}
		return  ls;
	}
	/**
	 * 解析json对象转化为实体
	 * @param <T>
	 * @param clas
	 * @param value
	 * @return
	 */
	public static <T> T analysisJSONObj(Class<T> clas, JSONObject value) {
		T reObj  = null;
		try {
			reObj = clas.newInstance();
			Field[] fields = clas.getDeclaredFields();
			for(Field field:fields)
			{
				field.setAccessible(true);
				Object val = value.get(field.getName());
				if(val instanceof  JSONArray)
				{
					String type = field.getGenericType().toString();
					field.set(reObj, JSONArrayToList(Class.forName(type.substring(type .indexOf(" ",0)+1)),(JSONArray)val));
				}
				else if(val instanceof  JSONObject)
				{
					field.set(reObj, analysisJSONObj(field.getType(), (JSONObject)val));
				}
				else
				{
					field.set(reObj,val);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reObj;
	}
    
}
