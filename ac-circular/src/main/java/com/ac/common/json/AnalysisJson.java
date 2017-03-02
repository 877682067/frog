package com.baoku.common.reflection.json;

import java.lang.reflect.*;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.baoku.common.reflection.form.AnalysisRequestForm;
import com.baoku.common.utils.DateUtils;
import com.baoku.common.utils.ListUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AnalysisJson {
	
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
			return JSONArrayToCollection(field.getType(),Class.forName(generic),(JSONArray)value);
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
    private static String getGenericClass(Type genericType) {
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
	public  static <T> Collection<T> JSONArrayToCollection(Class type,Class<T> clas, JSONArray values) {
		@SuppressWarnings("unchecked")
		Collection<T> ls = createCollection(type);
		@SuppressWarnings("unchecked")
		Iterator<JSONObject> it  = values.iterator();
		while(it.hasNext())
		{
			ls.add(analysisJSONObj(clas,it.next()));
		}
		return  ls;
	}
	private static Collection createCollection(@SuppressWarnings("rawtypes") Class type) {
		@SuppressWarnings("rawtypes")
		Collection col = null;
		if(isList(type))
		{
			col = ListUtils.newArrayList();
		}
		else if(isSet(type))
		{
			col = ListUtils.newHashSet();
		}
		return col;
	}
	private static boolean isList(@SuppressWarnings("rawtypes") Class face) {
		
		return face == List.class;
	}

	private static boolean isSet(@SuppressWarnings("rawtypes") Class face) {
		return face == Set.class;
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
			reObj = (T) clas.newInstance();
			Field[] fields = clas.getDeclaredFields();
			for(Field field:fields)
			{
				if ((field.getModifiers() & Modifier.FINAL) > 0
			               || (field.getModifiers() & Modifier.STATIC) > 0) {
			               continue;
			    }
				field.setAccessible(true);
				Object val = value.get(field.getName());
				val = AnalysisRequestForm.getSimpleValue(field.getType(),val);
				if(val instanceof  JSONArray)
				{
					field.set(reObj, JSONArrayToCollection(field.getType(),Class.forName(getGenericClass(field.getGenericType())),(JSONArray)val));
				}
				else if(val instanceof  JSONObject)
				{
					field.set(reObj, analysisJSONObj(field.getType(), (JSONObject)val));
				}
				else
				{
					if (Date.class == field.getType()) 
					{
						if(val instanceof String)
						{
							val = DateUtils.convertStringToDate(val.toString(),DateUtils.YYYYMMDDHHMMSSS);
						}
						else if(val instanceof Long)
						{
							val = new Date((long) val);
						}
					}
					
					field.set(reObj,val);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reObj;
	}
   
	
}
