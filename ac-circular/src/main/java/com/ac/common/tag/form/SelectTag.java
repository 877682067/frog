package com.ac.common.tag.form;


import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.lang.StringUtils;

import com.ac.common.StatusConstant.DataType;
import com.ac.common.tag.ElementTag;
import com.ac.common.tag.form.select.SelectSetting;
import com.ac.common.tag.form.validation.Validation;
import com.ac.common.util.script.ScriptUtils;

public class SelectTag extends ElementTag {
	private String key;
	private String text;
	private String data;
	private String url;
	private SelectSetting select;  
	private StringBuilder html ;
	private Validation valid = new Validation();
	
	
	
	@Override  
	public int doStartTag() throws JspException {
		DataType type = DataType.SCRIPT;
		String script = this.getData();
		if(StringUtils.isNotBlank(this.getUrl()))
		{
			type = DataType.AJAX;
			script = this.getUrl();
		}
		select = new SelectSetting(this.getId(),type , script, this.getKey(),
				this.getText(), this.getWidth(), this.getPlaceholder(), false, this.getValue());
		initHtml();
		return BodyTag.EVAL_BODY_BUFFERED;  
	}  
	private void initHtml() {
		html = new StringBuilder();
		this.valid.setRequired(this.getRequired());
		html.append("<select id =\""+this.getId()+"\" class=\""+valid.toString()+"\" name =\""+this.getId()+"\" ></select>\n");
	}
	@Override
    public int doAfterBody() throws JspTagException {  
        return SKIP_BODY;  
    }  
  
	@Override  
    public int doEndTag() throws JspException {  

		html.append(ScriptUtils.createScrpt(ScriptUtils.createJqueryReady(this.select.toString())));
		try {
			this.pageContext.getOut().write(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Tag.EVAL_PAGE;  
	}  
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getUrl() {
		return this.getPath(url);
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
