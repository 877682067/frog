package com.ac.common.paging;

public class Order {
	private String column;//第几个字段
	private String dir;//排序方式
	
	
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	
}
