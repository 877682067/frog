package com.ac.common.tag.table;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import com.ac.common.tag.ElementTag;
import com.ac.common.tag.table.settting.Column;
import com.ac.common.tag.table.settting.TableSetting;

@SuppressWarnings("serial")
public class ThTag extends ElementTag{
	private String name;
	private String format;
	private String click;
	private String orderAble;
	
    @Override  
    public int doEndTag() throws JspException {  
    	if (this.getParent() instanceof TableTag) {
			//设置table页签setting的aoClomns字段
			TableTag table = (TableTag) this.getParent();
			TableSetting setting = table.getSetting();
			Column column = new Column(this.getName());
			column.setmRender(this.getFormat());
			String content =  	this.getBodyContent().getString();  
			column.setsTitle(content);
			column.setsClass(this.getClas());
			column.setsWidth(this.getWidth());
			setting.getAoColumns().add(column );
		}
        return Tag.EVAL_PAGE;  
    }  
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getClick() {
		return click;
	}
	public void setClick(String click) {
		this.click = click;
	}
	public String getOrderAble() {
		return orderAble;
	}
	public void setOrderAble(String orderAble) {
		this.orderAble = orderAble;
	}
}
