package com.ac.common.util.script;

import com.ac.common.tag.table.settting.TableSetting;

public class ScriptUtils {
	private static final String DATATABLE_VAR_START ="datatable";
	private static final String DATATABLE_REFRESH_FUN_START ="_refresh";
	private static final String DATATABLE_TR_CHECK ="_tablecheck";
	private static final String DATATABLE_HEAD_CHECK ="_tablecheckbox";
	private static final String DATATABLE_TR_SELECT_CLASS ="alert-success";
	private static final String  FUN_SALT = "F_U_N_";
	private static final String  FUN_HEAD = "function ";
	public static final String  ENTER = "\n ";
	private static final String  FUN_START = "{ ";
	private static final String  JQUERY_READY = "$(document).ready(";
	private static final String  FUN_END = "}";
	
	/**
	 * 利用字符拼接方式创建javascript方法
	 * 
	 * @param name 方法名
	 * @param variable 参数
	 * @param body 方法内容
	 * @return
	 */
	public static String createFunction(String name,String variable,String body)
	{
		StringBuilder function  = new StringBuilder(FUN_SALT);
		function.append(FUN_HEAD);
		function.append(name);
		function.append("(");
		function.append(variable);
		function.append(")");
		function.append(FUN_START);
		function.append(ENTER);
		function.append(body);
		function.append(ENTER);
		function.append(FUN_END);
		function.append(FUN_SALT);
		return function.toString();
	}
	/**
	 * 利用字符拼接方式创建匿名javascript方法
	 * @param variable 参数 多个参数“,”分割
	 * @param body 方法内容
	 * @return
	 */
	public static String createAnonymousFun(String variable,String body)
	{
		StringBuilder function  = new StringBuilder(FUN_SALT);
		function.append(FUN_HEAD);
		function.append("(");
		function.append(variable);
		function.append(")");
		function.append(FUN_START);
		function.append(body);
		function.append(FUN_END);
		function.append(FUN_SALT);
		return function.toString();
	}
	/**
	 * 利通字符拼接方式创建页面加载后出发的jquery方法
	 * @param body
	 * @return
	 */
	public static String createJqueryReady(String body)
	{
		StringBuilder function  = new StringBuilder(JQUERY_READY);
		function.append( createAnonymousFun(" ", body) );
		function.append(");");
		return function.toString().replace(FUN_SALT, "");
	}
	
	public static String creatValidation(String id,String type)
	{
		StringBuilder valid = new  StringBuilder("jQuery(\"#");
		valid.append(id);
		valid.append("\").validationEngine('"+type+"')");
		return valid.toString();
	}
	
	public static String createScrpt(String  scriptBody)
	{
		StringBuilder script = new  StringBuilder("<script type=\"text/javascript\">");
		script.append(scriptBody);
		script.append("</script>");
		return script.toString();
	}
	
	@SuppressWarnings("unused")
	private static String removeFunSalt(TableSetting setting)
	{
		String set = setting.toString().replace("\""+FUN_SALT, "");
		return set.replace(FUN_SALT+"\"", "");
	}
	
	
	/**
	 * 为table元素初始化datatable 全局变量为datatable+table元素的ID
	 * @param id
	 * @param setting
	 * @return
	 */
	public static String createDataTables(String id,TableSetting setting)
	{	
		StringBuilder table  = new StringBuilder(DATATABLE_VAR_START);
		table.append( id );
		table.append( " = $('#" );
		table.append( id );
		table.append( "')" );
		table.append( " .on( 'init.dt', function () {");
		table.append( "registerTableClick"+id+"();\n");
		table.append( " })");
		table.append( ".DataTable(" );
		table.append( removeFunSalt(setting));
		table.append(")");
		return table.toString();
	}
	/**
	 * 创建刷新datatable的方法 名为 refresh+table元素的ID
	 * @param tableId
	 * @return
	 */
	public static String createRefreshDatatable(String tableId) 
	{
		String varName=DATATABLE_VAR_START+tableId;
		StringBuilder script = new  StringBuilder(FUN_HEAD);
		script.append(DATATABLE_REFRESH_FUN_START);
		script.append(tableId);
		script.append("()");
		script.append(FUN_START);
		script.append(varName);
		script.append(".ajax.url("+varName+".ajax.url).load();");
		script.append(FUN_END);
		return script.toString();
	}
	/**
	 * 返回datadable全局变量的名称
	 * @param tableId
	 * @return
	 */
	public static String getDatatableVar(String tableId) 
	{
		return DATATABLE_VAR_START+tableId;
	}
	/**
	 * 返回tbody checkbox id的标记
	 * @param tableId
	 * @return
	 */
	public static String getTrCheckSign() 
	{
		return DATATABLE_TR_CHECK;
	}
	/**
	 * 返回table头check box  id的标记
	 * @param tableId
	 * @return
	 */
	public static String getHeadCheckSign() 
	{
		return DATATABLE_HEAD_CHECK;
	}
	/**
	 * 返回TR 选中的样式
	 * @param tableId
	 * @return
	 */
	public static String getTrSelectClass() 
	{
		return DATATABLE_TR_SELECT_CLASS;
	}
	/**
	 * 返回刷新table的方法
	 * @param tableId
	 * @return
	 */
	public static String getRefreshFunName(String  tableId) 
	{
		return DATATABLE_REFRESH_FUN_START+tableId;
	}
}
