package com.ac.circular.circular.service;

import java.util.List;

import com.ac.circular.circular.entity.Circular;
import com.ac.common.paging.PageResult;
import com.ac.common.paging.PhonePage;

public interface CircularService {
	

	public List<Circular> pageList(PageResult<Circular> page);

	public void save(Circular user,String[]  attachs);

	public void delCircular(List<String> ls);

	public Circular getCircular(Long id);

	public List<Circular> phoneList(PhonePage<Circular> phonePage);
}
