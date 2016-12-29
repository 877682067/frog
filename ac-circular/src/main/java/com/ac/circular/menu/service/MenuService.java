package com.ac.circular.menu.service;

import java.util.List;

import com.ac.circular.login.entity.User;
import com.ac.circular.menu.entity.Menu;
import com.ac.common.paging.PageResult;

public interface MenuService {
	
	public List<Menu> getMenu(User user);

	public List<Menu> pageList(PageResult page);

	public List<Menu> list(Menu menu);

	public void save(Menu menu);

	public void delMenu(List<String> ls);

	public List<Menu> getMenuByRoleId(String roleId);
}
