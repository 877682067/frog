package com.ac.circular.employee.service;

import java.util.List;

import com.ac.circular.employee.entity.Employee;
import com.ac.common.paging.PageResult;

public interface EmployeeService {
	

	public List<Employee> pageList(PageResult<Employee> page);

	public void save(Employee user);

	public void delEmployee(List<String> ls);

	public Employee getEmployee(Employee employee);
}
