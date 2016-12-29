package com.ac.common.tag.table.settting;

import org.apache.commons.lang.StringUtils;

import com.ac.common.util.script.ScriptUtils;

public class Column {
	private String mDataProp;//字段名称
	private String mRender;//字段格式化
	private String sWidth;//宽度
	private String sTitle;//别名
	private String sClass;//样式
	private boolean bSortable;//样式
	public Column(String name) {
		this.mDataProp = name;
		this.setbSortable(true);
	}
	public Column(String columnType, String tableId) {
		this.mDataProp = "id";
		this.sTitle="序号";
		this.bSortable=false;
		String body="   if(!full.num) num=1 ; return  (meta.row+1) ";
		if("checkbox".equals(columnType))
		{
			this.sTitle  += " <input id=\""+ScriptUtils.getHeadCheckSign()+tableId+"\" type=\"checkbox\" name=\"tableSelectAll"+tableId+"\" />";
			body+="+ '<input type=\"checkbox\" name=\""+ScriptUtils.getTrCheckSign()+tableId+"\"  value=\"\'+data+\'\" />'";
		}
		this.mRender = this.mRender = ScriptUtils.createAnonymousFun("data, type, full,meta", body);
	}
	public String getmDataProp() {
		return mDataProp;
	}
	public void setmDataProp(String mDataProp) {
		this.mDataProp = mDataProp;
	}
	public String getmRender() {
		return mRender;
	}
	public String getsWidth() {
		return sWidth;
	}
	public void setsWidth(String sWidth) {
		this.sWidth = sWidth;
	}
	public String getsTitle() {
		return sTitle;
	}
	public void setsTitle(String sTitle) {
		this.sTitle = sTitle;
	}
	public String getsClass() {
		return sClass;
	}
	public void setsClass(String sClass) {
		this.sClass = sClass;
	}
	public boolean isbSortable() {
		return bSortable;
	}
	public void setbSortable(boolean bSortable) {
		this.bSortable = bSortable;
	}
	public void setmRender(String mRender) {
		if(StringUtils.isBlank(mRender))
		{
			this.mRender=ScriptUtils.createAnonymousFun("data, type, full","return  data;");
		}
		else
		{
			this.mRender = ScriptUtils.createAnonymousFun("data, type, full","return  "+ mRender+"(data, type, full);");
		}
	}
}
