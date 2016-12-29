package com.ac.common;

import java.util.Date;

public class BaseEntity {
	private Integer id;
	private Date createTime;
	public Integer getId() {
		return id;
	}
	
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
