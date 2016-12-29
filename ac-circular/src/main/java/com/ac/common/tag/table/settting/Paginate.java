package com.ac.common.tag.table.settting;

public class Paginate {
	private String sFirst="首页" ;
	private String sPrevious= "上页";
	private String sNext="下页" ;
	private String sJump= "跳转";
	private String sLast= "末页";
	
	
	public String getsFirst() {
		return sFirst;
	}
	public void setsFirst(String sFirst) {
		this.sFirst = sFirst;
	}
	public String getsPrevious() {
		return sPrevious;
	}
	public void setsPrevious(String sPrevious) {
		this.sPrevious = sPrevious;
	}
	public String getsNext() {
		return sNext;
	}
	public void setsNext(String sNext) {
		this.sNext = sNext;
	}
	public String getsJump() {
		return sJump;
	}
	public void setsJump(String sJump) {
		this.sJump = sJump;
	}
	public String getsLast() {
		return sLast;
	}
	public void setsLast(String sLast) {
		this.sLast = sLast;
	}
}
