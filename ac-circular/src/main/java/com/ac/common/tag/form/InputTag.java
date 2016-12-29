package com.ac.common.tag.form;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.lang.StringUtils;


import com.ac.common.tag.ElementTag;
import com.ac.common.tag.form.validation.Validation;

public class InputTag extends ElementTag{
	private String  type;
	private Integer  minlength;
	private Integer  maxlength;
	private String  parentId;
	private String  placeholder;
	private String body;
	private String equals;
	private String ajax;
	private Validation valid = new Validation();
	
	@Override  
	public int doStartTag() throws JspException {  
		valid.setMinSize(this.getMinlength());
		valid.setMaxSize(this.getMaxlength());
		valid.setEquals(this.getEquals());
		valid.setRequired(this.getRequired());
		StringBuilder input= new  StringBuilder();
		input.append("<input id=\"");
		input.append(this.getId());
		input.append("\" name =\"");
		input.append(this.getId());
		input.append("\" class =\" form-control  ");
		input.append(valid.toString());
		input.append(" ");
		input.append(valid.getClass(this.getType()));
		input.append(this.getClas());
		input.append("\" ");
		input.append(" type=\"");
		input.append(this.getType());
		input.append("\" ");
		if(StringUtils.isNotBlank(this.getWidth()))
		{
			input.append(" width=\"");
			input.append(this.getWidth());
			input.append("\" ");
		}
		if(StringUtils.isNotBlank(this.getValue()))
		{
			input.append(" value=\"");
			input.append(this.getValue());
			input.append("\" ");
		}
		if(StringUtils.isNotBlank(this.getClick()))
		{
			input.append(" onclick=\"");
			input.append(this.getClick()+"(this);");
			input.append("\" ");
		}
		if(StringUtils.isNotBlank(this.getChange()))
		{
			input.append(" onchange=\"");
			input.append(this.getChange()+"(this);");
			input.append("\" ");
		}
		input.append(" placeholder=\"");
		input.append(this.getPlaceholder());
		input.append("\" ");
		input.append(this.getDisable());
		input.append(this.getReadOnly());
		input.append("/>");
	/*	if(this.getType().equals("checkbox")||this.getType().equals("radio"))
		{
			createFrame(input);
		}*/
		body = input.toString();
		return BodyTag.EVAL_BODY_BUFFERED;  
	}  
/*	private void createFrame(StringBuilder input) {
		input.insert(0, "<div class=\""+this.getType()+"\"><lable>");
		input.append("</lable>\n</div>");
	}*/
	@Override
    public int doAfterBody() throws JspTagException {  
        return SKIP_BODY;  
    }  
  
	@Override  
    public int doEndTag() throws JspException {  
        //拿到标签体  
	    try 
	    {  
	    	if(this.getBodyContent()!=null)
	    	{
	    	//	String content =  	this.getBodyContent().getString();  
		    	this.pageContext.getOut().write(body);
	    	}
	    	else
	    	{
	    		if (this.getParent() instanceof  ElementTag)
	    		{
	    			((ElementTag)this.getParent()).getBodyContent().append(body);
	    		}
	    	}
	    } 
	    catch (IOException e) 
	    {  
	        throw new RuntimeException(e);  
	    }  
	    return Tag.EVAL_PAGE;  
	}  
	public String creteAjaxValid()
	{
		StringBuilder ajaxValid = new StringBuilder("function _getAjaxType"+this.getId()+"{\n");
		ajaxValid.append("ajaxFormValidation: true,\n");
		ajaxValid.append("onAjaxFormComplete: ajaxValidationCallback,\n");
		ajaxValid.append("onBeforeAjaxFormValidation: beforeCall\n");
		ajaxValid.append("}\n");
		ajaxValid.append("function ");
		ajaxValid.append("}\n");
		ajaxValid.append("}\n");
		
		
		return ajaxValid.toString();
	}
	
	public String getType() {
		if(StringUtils.isBlank(type))
			return  "text";
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public Integer getMinlength() {
		return minlength;
	}
	public void setMinlength(Integer minlength) {
		this.minlength = minlength;
	}
	public Integer getMaxlength() {
		return maxlength;
	}
	public void setMaxlength(Integer maxlength) {
		this.maxlength = maxlength;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getPlaceholder() {
		if(StringUtils.isBlank(placeholder))
			return  " ";
		return placeholder;
	}
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getEquals() {
		return equals;
	}
	public void setEquals(String equals) {
		this.equals = equals;
	}
	public String getAjax() {
		return ajax;
	}
	public void setAjax(String ajax) {
		this.ajax = ajax;
	}
}
