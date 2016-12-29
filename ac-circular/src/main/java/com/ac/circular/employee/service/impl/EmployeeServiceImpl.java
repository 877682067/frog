package com.ac.circular.employee.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ac.circular.employee.entity.Employee;
import com.ac.circular.employee.mapper.EmployeeMapper;
import com.ac.circular.employee.service.EmployeeService;
import com.ac.circular.login.entity.User;
import com.ac.circular.login.mapper.UserMapper;
import com.ac.circular.role.entity.Role;
import com.ac.circular.role.mapper.RoleMapper;
import com.ac.common.paging.PageResult;
@Service(value="employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	@Resource
	EmployeeMapper employeeMapper;
	@Resource
	UserMapper userMapper;
	@Resource
	RoleMapper roleMapper;

	public List<Employee> pageList(PageResult<Employee> page) 
	{
		return this.employeeMapper.pageList(page);
	}

	public void save(Employee employee) {
		if(employee.getId()==null)
		{
			User user = employee.getUser();
			if(employee.getIsUser())
			{
				userMapper.insert(user);
				Map m = new HashMap();
				m.put("userId", user.getId());
				m.put("roleId", user.getRoleId());
				this.roleMapper.insertRoleUser(m);
			}
			employee.setUserId(user.getId());
			this.employeeMapper.insert(employee);
		}
		else
		{
			this.employeeMapper.updateById(employee);
		}
	}

	public void delEmployee(List<String> ls) {
		for(String id :ls)
		{
			this.employeeMapper.delete(Long.valueOf(id));
		}
	}

	public Employee getEmployee(Employee employee) {
		
		return this.employeeMapper.selectById(employee.getId());
	}
}
