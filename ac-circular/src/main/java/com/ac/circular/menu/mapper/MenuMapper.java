package com.ac.circular.menu.mapper;

import java.util.List;

import com.ac.circular.login.entity.User;
import com.ac.circular.menu.entity.Menu;
import com.ac.common.paging.PageResult;

public interface MenuMapper {
    int delete(Long id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectById(Long id);
    
    List<Menu> queryByUser(User user);

    List<Menu> pageList(PageResult page);
    List<Menu> list(Menu menu);

    int updateByPrimaryKeySelective(Menu record);

    int update(Menu record);

	List getMenuByRoleId(String roleId);

	Integer getNextSort(Menu menu);

}