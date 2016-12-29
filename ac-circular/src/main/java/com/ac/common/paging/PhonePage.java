package com.ac.common.paging;

public class PhonePage<T> {
	private int min;
	private int max;
	private T  param;
	
	
	public T getParam() {
		return param;
	}
	public void setParam(T param) {
		this.param = param;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
}
