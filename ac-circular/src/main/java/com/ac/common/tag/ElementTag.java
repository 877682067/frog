package com.ac.common.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("serial")
public abstract class ElementTag  extends BodyTagSupport {
	private String id;//元素id
	private String click;
	private String change;
	private String dbclick;
	private String clas;
	private String width;
	private String value;
	private String placeholder;
	private boolean disable;
	private boolean readOnly;
	private boolean required;
	
	public String  getPath(String url)
	{
		if(StringUtils.isBlank(url))
			return "";
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		String path = request.getContextPath();
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClick() {
		return click;
	}
	public void setClick(String click) {
		this.click = click;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public String getDbclick() {
		return dbclick;
	}
	public void setDbclick(String dbclick) {
		this.dbclick = dbclick;
	}
	public String getClas() {
		if(StringUtils.isBlank(clas))
			return  "";
		return clas;
	}
	public void setClas(String clas) {
		this.clas = clas;
	}
	public String  getDisable() {
		if(disable)
			return  " disabled ";
		return "";
	}
	public void setDisable(boolean disable) {
		this.disable = disable;
	}
	public String getReadOnly() {
		if(readOnly)
			return  " readonly ";
		return "";
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	public boolean getRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public String getWidth() {
		if(StringUtils.isBlank(value))
			return  "";
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getValue() {
		if(StringUtils.isBlank(value))
			return  "";
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getPlaceholder() {
		return placeholder;
	}
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
}
