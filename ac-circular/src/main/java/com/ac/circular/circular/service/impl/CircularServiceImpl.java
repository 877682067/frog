package com.ac.circular.circular.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ac.circular.attach.entity.AttachMapping;
import com.ac.circular.attach.mapper.AttachMapper;
import com.ac.circular.circular.entity.Circular;
import com.ac.circular.circular.mapper.CircularMapper;
import com.ac.circular.circular.service.CircularService;
import com.ac.common.StatusConstant.AttachType;
import com.ac.common.paging.PageResult;
import com.ac.common.paging.PhonePage;
@Service(value="circularService")
public class CircularServiceImpl implements CircularService {
	@Resource
	CircularMapper circularMapper;
	@Resource
	AttachMapper attachMapper;
	public List<Circular> pageList(PageResult<Circular> page) 
	{
		return this.circularMapper.pageList(page);
	}

	public void save(Circular circular,String[] attachs) {
		if(circular.getId()==null)
		{
			this.circularMapper.insert(circular);
		}
		else
		{
			this.circularMapper.update(circular);
			this.attachMapper.deleteMapping(circular.getId());
		}
		if(attachs!=null)
		{
			List<AttachMapping> ls = new LinkedList<AttachMapping>();
			for(String attach : attachs )
			{
				AttachMapping att = new AttachMapping();
				att.setAttachId(Long.valueOf(attach));
				att.setType(AttachType.CIRCULAR);
				att.setId(circular.getId());
				ls.add(att);
			}
			this.attachMapper.insertMapping(ls);
		}
		
	}

	public void delCircular(List<String> ls) {
		for(String id :ls)
		{
			this.circularMapper.delete(Long.valueOf(id));
		}
	}

	public Circular getCircular(Long id) {
		Circular circular = this.circularMapper.selectById(id);
		AttachMapping mapp = new AttachMapping();
		mapp.setId(circular.getId());
		mapp.setType(AttachType.CIRCULAR);
		circular.setAttach(this.attachMapper.getAttachByMapping(mapp));
		return circular ;
	}

	public List<Circular> phoneList(PhonePage<Circular> phonePage) {
		
		return this.circularMapper.phoneList(phonePage);
	}
}
