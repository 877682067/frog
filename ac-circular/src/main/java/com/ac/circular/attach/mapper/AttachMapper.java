package com.ac.circular.attach.mapper;


import java.util.List;

import com.ac.circular.attach.entity.Attach;
import com.ac.circular.attach.entity.AttachMapping;

public interface AttachMapper {
    int delete(Long id);
    
    int insert(Attach attach);
    
    Attach selectById(Long id);
    
    List<Attach>  selectByBelongId(Integer belongId);
	
    int insertMapping(List<AttachMapping> attach);

	void deleteMapping(Long id);

	List<Attach> getAttachByMapping(AttachMapping mapp);

}