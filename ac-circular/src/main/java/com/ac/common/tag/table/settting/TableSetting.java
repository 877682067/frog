package com.ac.common.tag.table.settting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.ac.common.util.script.ScriptUtils;

import net.sf.json.JSONObject;



public class TableSetting {
	private String sDom= "<\'top\'l>rt<\'bottom\'ifp><\'clear\'>";
	private boolean serverSide= true;//从服务器获取数据
	private boolean bPaginate= true;//分页
	private boolean ordering= true;//排序
	private boolean searching= false;//查询
	private boolean bSort= true;//
	private List<List> aLengthMenu = new LinkedList<List>();//每页显示 多少
	private boolean bInfo= true;
	private boolean bWidth= true;//宽度
	private boolean bScrollCollapse= true;
	private String sScrollY= "60%";
	private String pagingType= "full_numbers";//table页面布局样式
	private String fnServerData;//ajax获取 数据后的回调函数
	private String nDrawCallback;//画完table的回调函数
	private List<Column> aoColumns = new  ArrayList<Column>();//字段
	private List<List<String>>  aaSorting  = new ArrayList<List<String>>();//默认排序字段
	private PageLanguage language = new PageLanguage();//国际化
	private String initComplete;//初始化完成后回调函数
	
	
	public TableSetting(String tableId)
	{
		
		//this.initComplete = ScriptUtils.createAnonymousFun("","registerTableClick"+tableId+"();");;
		this.fnServerData  = ScriptUtils.createAnonymousFun("sSource, aoData, fnCallback", "fnServerData"+tableId+"(sSource, aoData, fnCallback);");
	}
	@Override
	public String toString() {
		JSONObject obj = JSONObject.fromObject(this);
		return obj.toString();
	}
	public String getsDom() {
		return sDom;
	}
	public void setsDom(String sDom) {
		this.sDom = sDom;
	}
	public boolean isServerSide() {
		return serverSide;
	}
	public void setServerSide(boolean serverSide) {
		this.serverSide = serverSide;
	}
	public boolean isbPaginate() {
		return bPaginate;
	}
	public void setbPaginate(boolean bPaginate) {
		this.bPaginate = bPaginate;
	}
	public boolean isOrdering() {
		return ordering;
	}
	public void setOrdering(boolean ordering) {
		this.ordering = ordering;
	}
	public boolean isSearching() {
		return searching;
	}
	public void setSearching(boolean searching) {
		this.searching = searching;
	}
	public boolean isbSort() {
		return bSort;
	}
	public void setbSort(boolean bSort) {
		this.bSort = bSort;
	}
	public boolean isbInfo() {
		return bInfo;
	}
	public void setbInfo(boolean bInfo) {
		this.bInfo = bInfo;
	}
	public boolean isbWidth() {
		return bWidth;
	}
	public void setbWidth(boolean bWidth) {
		this.bWidth = bWidth;
	}
	public boolean isbScrollCollapse() {
		return bScrollCollapse;
	}
	public void setbScrollCollapse(boolean bScrollCollapse) {
		this.bScrollCollapse = bScrollCollapse;
	}
	public String getPagingType() {
		return pagingType;
	}
	public void setPagingType(String pagingType) {
		this.pagingType = pagingType;
	}
	public String getFnServerData() {
		return fnServerData;
	}
	public void setFnServerData(String fnServerData) {
		this.fnServerData = fnServerData;
	}
	public PageLanguage getLanguage() {
		return language;
	}
	public void setLanguage(PageLanguage language) {
		this.language = language;
	}
	public List<Column> getAoColumns() {
		return aoColumns;
	}
	public void setAoColumns(List<Column> aoColumns) {
		this.aoColumns = aoColumns;
	}
	public List<List<String>> getAaSorting() {
		return aaSorting;
	}
	public void setAaSorting(List<List<String>> aaSorting) {
		this.aaSorting = aaSorting;
	}
	public List<List> getaLengthMenu() {
		return aLengthMenu;
	}
	public void setaLengthMenu(List<List> aLengthMenu) {
		this.aLengthMenu = aLengthMenu;
	}
	public String getnDrawCallback() {
		return nDrawCallback;
	}
	public void setnDrawCallback(String nDrawCallback) {
		this.nDrawCallback = nDrawCallback;
	}
	public String getInitComplete() {
		return initComplete;
	}
	public void setInitComplete(String initComplete) {
		this.initComplete = initComplete;
	}
	public String getsScrollY() {
		return sScrollY;
	}
	public void setsScrollY(String sScrollY) {
		this.sScrollY = sScrollY;
	}
}
