package com.ac.common.tag.form.validation;

import org.apache.commons.lang.StringUtils;

public class Validation {

	private boolean required;
	private String custom;//ipv4 url dateTimeFormat dateFormat date number  integer email phone onlyNumberSp onlyLetterSp onlyLetterNumber
	private String equals;
	private Integer minSize;
	private Integer maxSize;
	private Integer min;
	private Integer max;
	private Integer maxCheckbox;
	private Integer minCheckbox;
	
	
	@Override
	public String toString() {
		StringBuilder  valid = new StringBuilder("validate[ ");
		if(this.getRequired())
		{
			valid.append("required,");
		}
		if(StringUtils.isNotBlank(this.getEquals()))
		{
			valid.append("equals[");	
			valid.append(this.getEquals());	
			valid.append("],");	
		}
		if(StringUtils.isNotBlank(custom))
		{
			valid.append("custom[");
			valid.append(this.getCustom());
			valid.append("],");
		}
	/*	if(this.getMinSize()!=null && this.getMaxSize()!=null)
		{
			valid.append("length[ ");
			valid.append(this.getMinSize());
			valid.append(", ");
			valid.append(this.getMaxSize());
			valid.append("], ");
		}
		else*/ if(this.getMinSize()!=null)
		{
			valid.append("minSize[");
			valid.append(this.getMinSize());
			valid.append("],");
		}
		if(this.getMaxSize()!=null)
		{
			valid.append("maxSize[");
			valid.append(this.getMaxSize());
			valid.append("],");
		}
		if(this.getMin()!=null)
		{
			valid.append("min[");
			valid.append(this.getMin());
			valid.append("],");
		}
		if(this.getMax()!=null)
		{
			valid.append("max[");
			valid.append(this.getMax());
			valid.append("],");
		}
		if(this.getMaxCheckbox()!=null)
		{
			valid.append("minCheckbox[");
			valid.append(this.getMaxCheckbox());
			valid.append("],");
		}
		if(this.getMaxCheckbox()!=null)
		{
			valid.append("maxCheckbox[");
			valid.append(this.getMaxCheckbox());
			valid.append("],");
		}
		String validStr = valid.substring(0, valid.length()-1);
		return validStr +"] ";
	}
	
	public String getClass(String type)
	{
		if(type.equals("text"))
			return " text-input ";
		else if(type.toLowerCase().equals("checkbox"))
			return " checkbox ";
		else if(type.toLowerCase().equals("radio"))
			return " radio ";
		return " ";
	}
	
	
	public boolean getRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public String getCustom() {
		return custom;
	}
	public void setCustom(String custom) {
		this.custom = custom;
	}
	public Integer getMinSize() {
		return minSize;
	}
	public void setMinSize(Integer minSize) {
		this.minSize = minSize;
	}
	public Integer getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}
	public Integer getMin() {
		return min;
	}
	public void setMin(Integer min) {
		this.min = min;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	public Integer getMaxCheckbox() {
		return maxCheckbox;
	}
	public void setMaxCheckbox(Integer maxCheckbox) {
		this.maxCheckbox = maxCheckbox;
	}
	public Integer getMinCheckbox() {
		return minCheckbox;
	}
	public void setMinCheckbox(Integer minCheckbox) {
		this.minCheckbox = minCheckbox;
	}

	public String getEquals() {
		return equals;
	}

	public void setEquals(String equals) {
		this.equals = equals;
	}
}
