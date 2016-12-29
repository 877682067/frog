package com.ac.common.tag.form.select;


import org.apache.commons.lang.StringUtils;

import com.ac.common.StatusConstant.DataType;

public class SelectSetting {

	private String  id;
	private	String data;
	private	String placeholder;
	private	boolean multiple;
	private String width;
	private String key;
	private String text;
	private StringBuilder  select2;
	private String initvalue;
	public SelectSetting(String id,DataType type,String data,String key,String text,
			String width,String placeholder,boolean  multiple ,String initvalue)
	{
		this.id = id;
		this.key=key;
		this.text  = text;
		this.data = data;
		this.placeholder  = placeholder;
		this.multiple = multiple;
		this.width = width;
		this.initvalue = initvalue;
		this.createSelect2(type);
	}
	private void createSelect2(DataType type) {
		select2 = new StringBuilder();
		select2.append("$(\"#"+this.id+"\").select2({\n");
		select2.append("language: \"zh-CN\",");
		select2.append("placeholder:'"+this.getPlaceholder()+"',");
		select2.append("	allowClear: true,");
		select2.append("width: '"+this.getWidth()+"',");
		if(initvalue!=null)
		{
			//初始化代码。。。用时再写。。
		}
		if(type==DataType.AJAX)
		{
			select2.append("    ajax: {\n");
			select2.append("        url: \""+this.data+"\",\n");
			select2.append("        dataType: 'json',\n");
			select2.append("        delay: 250,\n");
			select2.append("        type: 'post',\n");
			select2.append("        data: function (params) {\n");
			select2.append("            return {\n");
			select2.append("                name: params."+key+", \n");
			select2.append("                page: params."+text+"\n");
			select2.append("            };\n");
			select2.append("        },\n");
		}
		else
		{
			select2.append("    data: function(){  \n");
			select2.append("    if("+this.data+" instanceof function){  \n");
			select2.append("      return "+this.data+"();\n } \n");
			select2.append("    if("+this.data+" instanceof Array){  \n");
			select2.append("      return "+this.data+";\n ");
			select2.append("     } \n");
			select2.append("    else { \n");
			select2.append("    return eval("+this.data+"); \n");
		}
		select2.append("        processResults: function (data, params) {\n");
		select2.append("          for(var i=0;i<data.length;i++)\n");
		select2.append("          {\n");
		select2.append("          for(var _attr in data[i]){\n");
		select2.append("          	if(_attr=='"+this.text+"')");
		select2.append("          		data[i].text=data[i][_attr];");
		select2.append("          }\n");
		select2.append("          }\n");
		select2.append("            return {\n");
		select2.append("                results: data,\n");
		select2.append("                pagination: {\n");
		select2.append("                    more: (params.page * 10) < data.total_count\n");
		select2.append("                }\n");
		select2.append("            };\n");
		select2.append("        },\n");
		select2.append("        cache: true\n");
		select2.append("    },\n");
		select2.append("    escapeMarkup: function (markup) { return markup; } \n");
		select2.append("});\n");
		
	}
	public SelectSetting(String id,String data,DataType type)
	{
		this(id,type,data,"id","text");
	}
	public SelectSetting(String id,String data,DataType type,String initvalue)
	{
		this(id,data,type,"id","text",initvalue);
	}
	public SelectSetting(String id,DataType type,String data,String key,String text)
	{
		this(id,type,data,key,text,null);
	}
	public SelectSetting(String id,String data,DataType type,String key,String text,String initvalue)
	{
		this(id,type,data,key,text,null,initvalue);
	}
	public SelectSetting(String id,DataType type,String data,String key,String text,String width)
	{
		this(id,type,data,key,text,width,null);
	}
	public SelectSetting(String id,DataType type,String data,String key,String text, String width,String placeholder)
	{
		this(id,type,data,key,text,width,placeholder,false,null);
	}
	@Override
	public String toString() {
		return this.select2.toString();
	}
	private  String getPlaceholder(){
		if(StringUtils.isBlank(width))
		{
			return "";
		}
		return placeholder;
	}
	private  String getWidth(){
		if(StringUtils.isBlank(width))
		{
			return "210px";
		}
		return width;
	}
}

