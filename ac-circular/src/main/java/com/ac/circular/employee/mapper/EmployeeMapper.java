package com.ac.circular.employee.mapper;

import java.util.List;

import com.ac.circular.employee.entity.Employee;
import com.ac.common.paging.PageResult;

public interface EmployeeMapper {
    int delete(Long id);

    int insert(Employee record);

    Employee selectById(Long id);

    int updateById(Employee record);

	List<Employee> pageList(PageResult<Employee> page);
}