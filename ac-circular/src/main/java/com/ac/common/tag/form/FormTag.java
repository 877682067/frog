package com.ac.common.tag.form;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.lang.StringUtils;

import com.ac.common.StatusConstant.ValidType;
import com.ac.common.tag.ElementTag;
import com.ac.common.util.script.ScriptUtils;


public class FormTag extends ElementTag{
	private String method;
	private String action;
	private String callback;
	private String beforeSubmit;
	
	
	@Override  
	public int doStartTag() throws JspException {  
		return BodyTag.EVAL_BODY_BUFFERED;  
	}  
	@Override
	public void doInitBody() throws JspTagException { 
		try 
		{
			this.getBodyContent().append("<form id=\"");
			this.getBodyContent().append(this.getId());
			this.getBodyContent().append("\" action=\"");
			this.getBodyContent().append(this.getAction());
			this.getBodyContent().append("\" class =\" ");
			this.getBodyContent().append(this.getClas());
			this.getBodyContent().append("\" method =\"");
			this.getBodyContent().append(this.getMethod());
			this.getBodyContent().append("\" >");
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}  
	@Override
    public int doAfterBody() throws JspTagException {  
    	try 
    	{
			this.getBodyContent().append("</form>");
		} 
    	catch (IOException e1) 
		{
			e1.printStackTrace();
		}  
        return SKIP_BODY;  
    }  
  
	@Override  
    public int doEndTag() throws JspException {  
        //拿到标签体  
	    try 
	    {  
	    	StringBuilder body = new StringBuilder();
	    	if(StringUtils.isBlank(this.getCallback()))
	    	{
	    		body.append(ScriptUtils.creatValidation(this.getId(),ValidType.ATTACH));
	    		body.append("\n $(\"#"+this.getId()+"\").submit(function(e){\n");
	    		body.append("if(");
	    		body.append(ScriptUtils.creatValidation(this.getId(),ValidType.VALIDATE));
	    		body.append("){\n");
	    		body.append(" $(\"#"+this.getId()+" button[type='submit']\").attr('disabled','disabled');");
	    		body.append(" $(\"#"+this.getId()+" input[type='submit']\").attr('disabled','disabled');");
	    		if(StringUtils.isNotBlank(this.getBeforeSubmit()))
	    		{
	    			body.append("if(!"+this.getBeforeSubmit()+"())\n");
	    			body.append("	return false;\n");
	    		}
	    		body.append(" return true;\n");
	    		body.append(" }\n");
	    		body.append(" return false;\n");
	    		body.append("\n});");
	    	}
	    	else
	    	{
	    		body.append(ScriptUtils.creatValidation(this.getId(),ValidType.ATTACH));
	    		body.append("\n");
	    		body.append("$(\"#"+this.getId()+"\").submit(function(e){\n");
		    	body.append("$.ajax({");
				body.append("url:\""+this.getAction()+"\",\n");
				body.append("type:\"post\",\n");
				body.append("data:$(\"#"+this.getId()+"\").serialize(),\n");
				body.append("dataType:\"json\",");
				body.append("beforeSend:function(){\n ");
				body.append("if(");
	    		body.append(ScriptUtils.creatValidation(this.getId(),ValidType.VALIDATE));
	    		body.append("){\n");
	    		body.append(" $(\"#"+this.getId()+" button[type='submit']\").attr('disabled','disabled');");
	    		body.append(" $(\"#"+this.getId()+" input[type='submit']\").attr('disabled','disabled');");
	    		if(StringUtils.isNotBlank(this.getBeforeSubmit()))
	    		{
	    			body.append("if(!"+this.getBeforeSubmit()+"())\n");
	    			body.append("	return false;\n");
	    		}
	    		body.append(" return true;");
	    		body.append(" }");
	    		body.append(" return false; \n},");
				body.append("success:");
				body.append(this.getCallback());
				body.append("});\n");
		    	body.append("return false;");
		    	body.append("});");
	    	}
	    	String script  = ScriptUtils.createScrpt(ScriptUtils.createJqueryReady(body.toString()));
	    	this.getBodyContent().append(script);
	    	String content =  	this.getBodyContent().getString();  
	    	this.pageContext.getOut().write(content);  
	    } 
	    catch (IOException e) 
	    {  
	        throw new RuntimeException(e);  
	    }  
	    return Tag.EVAL_PAGE;  
	}
	public String getMethod() {
		if(StringUtils.isBlank(method))
			return "post";
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getAction() {
		return this.getPath(action);
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getBeforeSubmit() {
		return beforeSubmit;
	}
	public void setBeforeSubmit(String beforeSubmit) {
		this.beforeSubmit = beforeSubmit;
	}
}
