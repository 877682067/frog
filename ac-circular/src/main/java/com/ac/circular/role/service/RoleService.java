package com.ac.circular.role.service;

import java.util.List;
import java.util.Map;

import com.ac.circular.login.entity.User;
import com.ac.circular.role.entity.Role;
import com.ac.common.paging.PageResult;

public interface RoleService {
	
	public List<Role> getRole(User user);

	public List<Role> pageList(PageResult<Role> page);

	public void save(Role role);

	public void delRole(List<String> ls);

	public List getRoles(Map m);

	public Role getRole(Role role);
}
