package com.ac.common.tag.form;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;

import com.ac.common.tag.ElementTag;
import com.ac.common.util.script.ScriptUtils;

@SuppressWarnings("serial")
public class ButtonTag extends ElementTag{
	private String type;
	private String tableid;
	private String url;
	private String valid;

	@Override
	public int doEndTag() throws JspException {

		this.getHtml();
		return super.doEndTag();
	}
	
	
	private void getHtml() {
		String clas="",clickFuntion="$(\"#"+super.getId()+"\").click(function(){ \n" ;
		String text="";
		StringBuilder html = new  StringBuilder("<button id = \"");
		html.append(super.getId());
		html.append("\" class  = \"");
		
		if(StringUtils.isNotBlank(this.getValid()))
		{
			clickFuntion +="if(!"+this.getValid()+"(_getSelectRow"+this.getTableid()+"())) \n";
			clickFuntion +=" return false; \n";
		}
		
		if(type.equals("add"))
		{
			clas = "btn btn-success";
			clickFuntion+=" $.add('"+this.getUrl()+"');";
			text="新增";
		}
		else if(type.equals("update"))
		{
			text="修改";
			clas = "btn btn-info";
			clickFuntion+=" $.update('"+this.getUrl()+"',_getSelectRow"+this.getTableid()+");\n";
		}
		else if(type.equals("del"))
		{
			text="删除";
			clas = "btn btn-warning";
			clickFuntion+=" $.del('"+this.getUrl()+"',_getSelectRow"+this.getTableid()+","+ScriptUtils.getRefreshFunName(this.getTableid())+");";
		}
		else
		{
			try {
				throw new Exception("不支持的 类型");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		clickFuntion +="})";
		html.append(clas);
		html.append("\" type  =\"button\">");
		html.append(text);
		html.append("</button>");
		html.append(ScriptUtils.createScrpt(ScriptUtils.createJqueryReady(clickFuntion)));
		try {
			this.pageContext.getOut().write(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTableid() {
		return tableid;
	}


	public void setTableid(String tableid) {
		this.tableid = tableid;
	}


	public String getUrl() {
		return super.getPath(url);
	}


	public void setUrl(String url) {
		this.url = url;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}
}
