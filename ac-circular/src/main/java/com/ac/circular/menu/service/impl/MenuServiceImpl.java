package com.ac.circular.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ac.circular.login.entity.User;
import com.ac.circular.menu.entity.Menu;
import com.ac.circular.menu.mapper.MenuMapper;
import com.ac.circular.menu.service.MenuService;
import com.ac.common.paging.PageResult;
@Service(value="menuService")
public class MenuServiceImpl implements MenuService {
	@Resource
	MenuMapper menuMapper;
	
	 public List<Menu> getMenu(User user)
	{
		 return menuMapper.queryByUser(user);
	}

	public List<Menu> pageList(PageResult page) 
	{
		return this.menuMapper.pageList(page);
	}
	public List<Menu> list(Menu menu) 
	{
		return this.menuMapper.list(menu);
	}

	public void save(Menu menu) {
		if(menu.getId()==null)
		{
			menu.setSort(this.menuMapper.getNextSort(menu));
			this.menuMapper.insert(menu);
		}
		else
		{
			this.menuMapper.update(menu);
		}
	}

	public void delMenu(List<String> ls) {
		for(String id :ls)
		{
			this.menuMapper.delete(Long.valueOf(id));
		}
	}

	public List<Menu> getMenuByRoleId(String roleId) {
		return this.menuMapper.getMenuByRoleId(roleId);
	}
}
