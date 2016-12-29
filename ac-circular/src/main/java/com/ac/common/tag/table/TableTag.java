package com.ac.common.tag.table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.lang.StringUtils;

import com.ac.common.tag.ElementTag;
import com.ac.common.tag.table.settting.Column;
import com.ac.common.tag.table.settting.MenuOption;
import com.ac.common.tag.table.settting.TableSetting;
import com.ac.common.util.script.ScriptUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class TableTag extends ElementTag{
	private String url ;
	private String order;
	private String lengthMenu;
	private String param;
	private boolean checkbox;
	private TableSetting setting;
	
	@Override  
	public String getWidth() {
		String width = super.getWidth();
		if(StringUtils.isBlank(width))
			width = "100%";
		return width;
	}
	@Override  
	public int doStartTag() throws JspException {  
		setting = new TableSetting(this.getId());
		String columnType ="number";
		if(this.getCheckbox())
			columnType = "checkbox";
		Column number = new Column(columnType,this.getId());
		setting.getAoColumns().add(number);
		return BodyTag.EVAL_BODY_BUFFERED;  
	}  
	@Override
	public void doInitBody() throws JspTagException { 
		try 
		{
			this.getBodyContent().append("<table id=\"");
			this.getBodyContent().append(this.getId());
			this.getBodyContent().append("\" class =\"");
			this.getBodyContent().append(this.getClas());
			this.getBodyContent().append("\" width=\"");
			this.getBodyContent().append(this.getWidth());
			this.getBodyContent().append("\"");
			this.getBodyContent().append(" style=\"max-height:200px;\"");
			this.getBodyContent().append("><thead>");
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
			this.getBodyContent().append("</thead></table>");
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
	    this.setOrder();
	    this.setLengthMenu();
	    try 
	    {  
	    	this.getBodyContent().append(this.getScript());
	    	String content =  	this.getBodyContent().getString();  
	    	this.pageContext.getOut().write(content);  
	    } 
	    catch (IOException e) 
	    {  
	        throw new RuntimeException(e);  
	    }  
	    return Tag.EVAL_PAGE;  
	}  
	
	private void setLengthMenu() {
		List<List> lengthMenu = new ArrayList<List>();
		MenuOption option = new  MenuOption();
		if(StringUtils.isNotBlank(this.getLengthMenu()))
		{
			JSONArray menuArray = JSONArray.fromObject(this.getLengthMenu());
			for(int  i =0;i<menuArray.size();i++)
			{
				int length = Integer.valueOf(menuArray.get(i).toString());
				option.addOption(length);
			}
		}
		else
		{
			option = this.getDefaultLengthMenu();
		}
		lengthMenu.add(option.getLength());
		lengthMenu.add(option.getName());
		this.setting.setaLengthMenu(lengthMenu);
	}
	private void setOrder() {
	    List<Column>  columns = this.getSetting().getAoColumns();
		JSONObject jsOrder = JSONObject.fromObject(this.getOrder());
		if(jsOrder==null||jsOrder.isEmpty())
			return;
	    for(int i=0;i < columns.size();i++)
	    {
	    	Column column = columns.get(i);
	    	List<String> orderInfo =  new ArrayList<String>();
	    	Object value = jsOrder.get(column.getmDataProp());
	    	if(value!=null)
	    	{
	    		orderInfo.add(i+"");
	    		orderInfo.add((String)value);
	    		setting.getAaSorting().add(orderInfo);
	    	}
	    }
	}
	private String getScript() {
		StringBuilder  script = new StringBuilder("<script type=\"text/javascript\">");
		script.append("var "+ScriptUtils.getDatatableVar(this.getId())+";\n");  
		script.append(ScriptUtils.createJqueryReady(this.getReadyBody()));  
		script.append("function fnServerData");  
		script.append(this.getId());  
		script.append("(sSource, aoData, fnCallback) \n");  
		script.append("{\n");
		if(this.getCheckbox())
			script.append("$(\"#"+ScriptUtils.getHeadCheckSign()+this.getId()+"\").attr('checked',false);\n");  
		script.append("    $.ajax({\n");  
		script.append("      type : 'post',\n");  
		script.append("      url:'"+this.getUrl()+"',\n");  
		script.append("      dataType : 'json',\n");  
		script.append("      data :\n");  
		script.append("      \n");  
		script.append("    	 getParam"+this.getId()+"(aoData,"+ScriptUtils.getDatatableVar(this.getId())+")\n");  
		script.append("      ,\n");  
		script.append("      success : function(resp) \n");  
		script.append("      {\n console.info(resp); ");  
		script.append("			fnCallback(resp);\n");  
		script.append("		}\n");  
		script.append("	});\n");  
		script.append("}\n");
		script.append("function getParam"+this.getId()+"(aoData,java_table)\n");
		script.append("{\n");
		script.append("	var data={ page:{}},cloumns = [];\n");
		script.append("	\n");
		script.append("	\n");
		script.append("	for(var i  = 0; i< aoData.length;  i++)\n");
		script.append("	{\n");
		script.append("		var obj = aoData[i];\n");
		script.append("		data.page[obj.name]  = obj.value;\n");
		script.append("	}\n");
		script.append("	for(var index in data.page.columns)\n");
		script.append("	{\n");
		script.append("		cloumns.push(data.page.columns[index].data);\n");
		script.append("	}\n");
		script.append("	for(var index in data.page.order)\n");
		script.append("	{\n");
		script.append("		var ord = data.page.order[index];\n");
		script.append("		ord.column = cloumns.slice(ord.column,ord.column+1)[0];\n");
		script.append("	}\n");
		script.append("	if(java_table)\n");
		script.append("	{\n");
		script.append("		data.page.pageNo=java_table.page.info().page+1;\n");
		script.append("	}\n");
		script.append("	else\n");
		script.append("	{\n");
		script.append("		data.page.pageNo = data.page.draw;	\n");
		script.append("	}\n");
		if(StringUtils.isNotBlank(this.getParam()))
		{
			script.append("	var param ;");
			script.append("	if(!"+this.getParam()+") return;");
			script.append("	if( typeof " );script.append(this.getParam() );	script.append(" == 'function')\n");
			script.append("	{\n");
			script.append("		param = ");script.append(this.getParam());script.append("();\n");
			script.append(" } \n");
			script.append(" else \n");
			script.append(" { \n");
			script.append(" 	param  = eval(\"(");script.append(this.getParam());script.append(")\")");
			script.append(" } \n");
			script.append(" for(var attr in param) \n");
			script.append(" { \n");
			script.append(" 	data[attr]=param[attr]; \n");
			script.append(" } \n");
		}
		script.append(" data.page = JSON.stringify(data.page);");
		script.append("	return data;\n");
		script.append("} \n");
		script.append(ScriptUtils.createRefreshDatatable(this.getId()));
		script.append(this.getTdClick(this.getId()));
		script.append("function _getSelectRow"+this.getId()+"()\n");
		script.append("{\n");
		script.append("	var _data = []; \n");
		script.append("	$('tr."+ScriptUtils.getTrSelectClass()+"').each(function()\n");
		script.append("	{\n");
		script.append("		 var rowdata  = "+ScriptUtils.getDatatableVar(this.getId())+".row(this.rowIndex-1).data();\n");
		script.append("		_data.push(rowdata);\n");
		script.append("		\n");
		script.append("	});\n");
		script.append("	return _data;\n");
		script.append("}\n");
		script.append("</script>");
		return script.toString();
	}
	private String getTdClick(String tableId) {
		StringBuilder tdClick = new StringBuilder("function registerTableClick"+tableId+"(){ \n");
		tdClick.append(" debugger;\n");
		tdClick.append("	$(\"#"+tableId+" tbody\").on('click','tr',  function()\n");
		tdClick.append(" 	{\n");
		tdClick.append("		if($(this).hasClass('"+ScriptUtils.getTrSelectClass()+"'))\n");
		tdClick.append(" 		{\n");
		tdClick.append(" 			var $_check = $(this).find(\"input[type='checkbox'][id^='"+ScriptUtils.getTrCheckSign()+"']\");\n");
		tdClick.append("   			if($_check.length<0||!$_check.attr('checked'))\n");
		tdClick.append("   			$(this).removeClass('"+ScriptUtils.getTrSelectClass()+"')\n");
		tdClick.append(" 		}\n");
		tdClick.append(" 		else \n");
		tdClick.append("		{ \n" );
		tdClick.append("			$(this).addClass('"+ScriptUtils.getTrSelectClass()+"')\n");
		tdClick.append("		}\n");
		tdClick.append(" 	});\n");
		tdClick.append(" 	$(\"#"+ScriptUtils.getHeadCheckSign()+tableId+"\").click(function(){ \n " );
		tdClick.append("		debugger;if(this.checked){  \n ");
		tdClick.append(" 			$(\"input[name='"+ScriptUtils.getTrCheckSign()+tableId+"']\").each(function(){");
		tdClick.append(" 				if(this.checked) return;\n");
		tdClick.append(" 				$(this).trigger('click');\n");
		tdClick.append(" 				$(this).parents('tr').addClass('"+ScriptUtils.getTrSelectClass()+"');\n");
		tdClick.append("			});\n");
		tdClick.append("		}\n " );
		tdClick.append("		else{  \n " );
		tdClick.append(" 			$(\"input[name='tablecheck"+tableId+"']\").each(function(){");
		tdClick.append(" 				if(!this.checked) return;\n");
		tdClick.append(" 				$(this).trigger('click');\n");
		tdClick.append(" 				$(this).parents('tr').removeClass('"+ScriptUtils.getTrSelectClass()+"');\n");
		tdClick.append("			});\n");
		tdClick.append("		}\n");
		tdClick.append("	}\n);");
		tdClick.append(" 	$(\"input[name='"+ScriptUtils.getTrCheckSign()+tableId+"']\").click(function(){ \n " );
		tdClick.append("		if(!this.checked){  \n ");
		tdClick.append(" 				$(this).parents('tr').addClass('"+ScriptUtils.getTrSelectClass()+"');\n");
		tdClick.append("		}\n " );
		tdClick.append("		else{  \n " );
		tdClick.append(" 				$(this).parents('tr').removeClass('"+ScriptUtils.getTrSelectClass()+"');\n");
		tdClick.append("		}\n");
		tdClick.append("	}\n);");
		tdClick.append("}");
		return  tdClick.toString();
	}
	
	private String getReadyBody() {
		return ScriptUtils.createDataTables(this.getId(),this.setting);
	}
	
	private MenuOption getDefaultLengthMenu()
	{
		MenuOption option = new  MenuOption();
		option.addOption(10);
		option.addOption(20);
		option.addOption(50);
		option.addOption(100);
		option.addOption(-1);
		return option;
	}
	public TableSetting getSetting() {
		return setting;
	}
	public void setSetting(TableSetting setting) {
		this.setting = setting;
	}
	public void setUrl(String url) {
		this.url = this.getPath(url);
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getUrl() {
		return url;
	}
	public String getLengthMenu() {
		return lengthMenu;
	}
	public void setLengthMenu(String lengthMenu) {
		this.lengthMenu = lengthMenu;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public boolean getCheckbox() {
		return checkbox;
	}
	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}
}
