package com.ac.circular.login.mapper;

import com.ac.circular.login.entity.User;


public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User user);
    User getByUserName(String userName);

    int insertSelective(User user);

    User selectById(Integer id);

    int updateByIdSelective(User user);

    int updateById(User user);
}