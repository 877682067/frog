package com.ac.circular.attach.service;

import java.util.List;

import com.ac.circular.attach.entity.Attach;
import com.ac.circular.attach.entity.AttachMapping;

public interface AttachService {
	
	public void save(List<AttachMapping> mapping);
	
	public void save(Attach attach);

	public void delete(Long belongId);
	
	public List<Attach> getAttach(AttachMapping mapp);
}
