package com.ac.circular.circular.mapper;

import java.util.List;

import com.ac.circular.circular.entity.Circular;
import com.ac.common.paging.PageResult;
import com.ac.common.paging.PhonePage;

public interface CircularMapper {
    int delete(Long id);

    int insert(Circular record);

    Circular selectById(Long id);

    int update(Circular record);

	List<Circular> pageList(PageResult<Circular> page);

	List<Circular> phoneList(PhonePage<Circular> phonePage);
}