package com.ac.common.paging;

public class Search {
	private String value;
	private boolean regex;//是否是正则表达式
	
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isRegex() {
		return regex;
	}
	public void setRegex(boolean regex) {
		this.regex = regex;
	}
	
}
