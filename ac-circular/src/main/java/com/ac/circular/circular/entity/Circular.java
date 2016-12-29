package com.ac.circular.circular.entity;

import java.util.Date;
import java.util.List;

import com.ac.circular.attach.entity.Attach;

public class Circular {
    private Long id;

    private String name;

    private String content;

    private Date createtime;

    private Long creator;

    private Date lastmodify;

    private Long lastmodifier;

    private Integer status;
    private Integer type;
    
    private List<Attach> attach;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Date getLastmodify() {
		return lastmodify;
	}

	public void setLastmodify(Date lastmodify) {
		this.lastmodify = lastmodify;
	}

	public Long getLastmodifier() {
		return lastmodifier;
	}

	public void setLastmodifier(Long lastmodifier) {
		this.lastmodifier = lastmodifier;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Attach> getAttach() {
		return attach;
	}

	public void setAttach(List<Attach> attach) {
		this.attach = attach;
	}

}