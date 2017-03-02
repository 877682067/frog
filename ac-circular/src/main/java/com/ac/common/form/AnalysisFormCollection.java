package com.baoku.common.reflection.form;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.baoku.common.utils.ListUtils;

import antlr.collections.List;

public class AnalysisFormCollection {

	
	
	/**
     * 解析http报文中的collection对象 节后a.b.c[]
     * @param parentFilds  a.b
     * @param now 当前要注入数据的属性
     * @param request
     * @return
     * @throws  
     * @throws Exception 
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Collection analysisRequstToCollection(String  parentFilds,Field now, HttpServletRequest request) throws Exception
    {
    	Class genericType = Class.forName(getGenericClass(now.getGenericType()));
    	Collection cll = createCollection(now);
		int  index = 0;
		String key ;
		Object value;
		Map  paremeters = request.getParameterMap();
		while(isHasElement(paremeters,key = getKey(parentFilds,index,null)))
		{
			if(AnalysisRequestForm.isSimpleField(genericType))
	    	{
	    		value = request.getParameter(key);
	    	}
	    	else
	    	{
	    		value = AnalysisRequestForm.analysisObject(key, genericType, request);
	    	}
			cll.add(value);
			index++;
		}
		return cll;
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
     * 检查是否含有a.b[0]元素
     * @param paremeters
     * @param key
     * @return
     */
	private static boolean isHasElement(Map paremeters, String key) {
		boolean hasElement = false;
		@SuppressWarnings("unchecked")
		Iterator<String> it = paremeters.keySet().iterator();
		while(it.hasNext()&&!hasElement)
		{
			String keyVal = it.next();
			if(keyVal.contains(key))
			{
				hasElement = true;
			}
		}
		return hasElement;
	}

	private static Collection createCollection(Field now) {
		@SuppressWarnings("rawtypes")
		Class type = now.getType();
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
	 * 拼接请求体中的 数组类型的KEY；
	 * @param parentFilds
	 * @param name
	 * @param index
	 * @param properryName
	 * @return
	 */
	private static String getKey(String parentFilds, int index,String properryName) {
		
		if(StringUtils.isBlank(properryName))
		{
			properryName="";
		}
		else
		{
			properryName="."+properryName;
		}
		
		return parentFilds+"["+index+"]"+properryName;
	}
}
