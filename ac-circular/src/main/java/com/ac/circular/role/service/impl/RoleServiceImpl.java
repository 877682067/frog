package com.ac.circular.role.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ac.circular.login.entity.User;
import com.ac.circular.menu.mapper.MenuMapper;
import com.ac.circular.role.entity.Role;
import com.ac.circular.role.mapper.RoleMapper;
import com.ac.circular.role.service.RoleService;
import com.ac.common.paging.PageResult;
@Service(value="roleService")
public class RoleServiceImpl implements RoleService {
	@Resource
	RoleMapper roleMapper;
	 public List<Role> getRole(User user)
	{
		 return roleMapper.queryByUser(user);
	}

	public List<Role> pageList(PageResult<Role> page) 
	{
		return this.roleMapper.pageList(page);
	}

	public void save(Role role) {
		if(role.getId()==null)
		{
			this.roleMapper.insert(role);
		}
		else
		{
			this.roleMapper.updateById(role);
			this.delRoleMenu(role);
		}
		this.insertRoleMenu(role);
	}

	private void insertRoleMenu(Role role) {
		List<String> menus  = new ArrayList<String>();
		if(role.getMenus()!=null)
			menus = Arrays.asList(role.getMenus().split(","));
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id", role.getId());
		m.put("menus", menus);
		roleMapper.insertRoleMenu(m);
	}

	private void delRoleMenu(Role role) {
		
		this.roleMapper.delRoleMenu(role);
	}

	public void delRole(List<String> ls) {
		for(String id :ls)
		{
			this.roleMapper.deleteById(Long.valueOf(id));
		}
	}

	public List getRoles(Map m) {
		return this.roleMapper.getRoles(m);
	}

	public Role getRole(Role role) {
		role = this.roleMapper.selectById(role.getId());
		List<Long> menu= this.roleMapper.getRoleMenu(role.getId());
		if(menu==null||menu.size()==0)
			return role;
		StringBuilder menuIds = new StringBuilder(); 
		for(Long menuid  : menu)
		{
			menuIds.append(",");
			menuIds.append(menuid);
		}
		role.setMenus(menuIds.substring(1));
		return role;
	}
}
