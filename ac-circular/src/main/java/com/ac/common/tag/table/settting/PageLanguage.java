package com.ac.common.tag.table.settting;

public class PageLanguage {
	private String sProcessing = "处理中...";
	private String sLengthMenu = "_MENU_";
	private String sZeroRecords = "没有匹配结果";
	private String sInfo = "第 _START_ 至 _END_ 条，共 _TOTAL_ 条  ";
	private String sInfoEmpty = "显示第 0 至 0 项结果，共 0 项";
	private String sInfoPostFix = "";
	private String sSearch = "搜索:";
	private String sUrl = "";
	private String sEmptyTable = "无可查询数据";
	private String sLoadingRecords = "载入中...";
	private String sInfoFiltered = "";
	private String sInfoThousands = "";
	private Paginate paginate = new Paginate();
	
	
	public String getsProcessing() {
		return sProcessing;
	}
	public void setsProcessing(String sProcessing) {
		this.sProcessing = sProcessing;
	}
	public String getsLengthMenu() {
		return sLengthMenu;
	}
	public void setsLengthMenu(String sLengthMenu) {
		this.sLengthMenu = sLengthMenu;
	}
	public String getsZeroRecords() {
		return sZeroRecords;
	}
	public void setsZeroRecords(String sZeroRecords) {
		this.sZeroRecords = sZeroRecords;
	}
	public String getsInfo() {
		return sInfo;
	}
	public void setsInfo(String sInfo) {
		this.sInfo = sInfo;
	}
	public String getsInfoEmpty() {
		return sInfoEmpty;
	}
	public void setsInfoEmpty(String sInfoEmpty) {
		this.sInfoEmpty = sInfoEmpty;
	}
	public String getsInfoPostFix() {
		return sInfoPostFix;
	}
	public void setsInfoPostFix(String sInfoPostFix) {
		this.sInfoPostFix = sInfoPostFix;
	}
	public String getsSearch() {
		return sSearch;
	}
	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}
	public String getsUrl() {
		return sUrl;
	}
	public void setsUrl(String sUrl) {
		this.sUrl = sUrl;
	}
	public String getsEmptyTable() {
		return sEmptyTable;
	}
	public void setsEmptyTable(String sEmptyTable) {
		this.sEmptyTable = sEmptyTable;
	}
	public String getsLoadingRecords() {
		return sLoadingRecords;
	}
	public void setsLoadingRecords(String sLoadingRecords) {
		this.sLoadingRecords = sLoadingRecords;
	}
	public String getsInfoFiltered() {
		return sInfoFiltered;
	}
	public void setsInfoFiltered(String sInfoFiltered) {
		this.sInfoFiltered = sInfoFiltered;
	}
	public String getsInfoThousands() {
		return sInfoThousands;
	}
	public void setsInfoThousands(String sInfoThousands) {
		this.sInfoThousands = sInfoThousands;
	}
	public Paginate getPaginate() {
		return paginate;
	}
	public void setPaginate(Paginate paginate) {
		this.paginate = paginate;
	}
}
