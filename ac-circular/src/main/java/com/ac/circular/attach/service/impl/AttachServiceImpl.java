package com.ac.circular.attach.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ac.circular.attach.entity.Attach;
import com.ac.circular.attach.entity.AttachMapping;
import com.ac.circular.attach.mapper.AttachMapper;
import com.ac.circular.attach.service.AttachService;

@Service(value="attachService")
public class AttachServiceImpl implements AttachService {
	@Resource
	AttachMapper attachMapper;


	public void save(List<AttachMapping> attach) {
		this.attachMapper.insertMapping(attach);
	}

	public void delete(Long belongId) {
			this.attachMapper.delete(belongId);
	}

	public void save(Attach attach) {
		this.attachMapper.insert(attach);
		
	}

	public List<Attach> getAttach(AttachMapping mapp) {
		return this.attachMapper.getAttachByMapping(mapp);
	}


}
