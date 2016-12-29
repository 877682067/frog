package com.ac.circular.role.mapper;

import java.util.List;
import java.util.Map;

import com.ac.circular.login.entity.User;
import com.ac.circular.role.entity.Role;
import com.ac.common.paging.PageResult;

public interface RoleMapper {
    int deleteById(Long id);

    int insert(Role record);
    
    Role selectById(Long id);
    
    List<Role> queryByUser(User user);
    
    List<Role> pageList(PageResult<Role> page);
    
    int updateById(Role record);

	void insertRoleMenu(Map<String, Object> m);

	void delRoleMenu(Role role);

	List getRoles(Map m);

	List<Long> getRoleMenu(Long id);

	void insertRoleUser(Map m);

}