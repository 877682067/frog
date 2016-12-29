package com.ac.circular.login.service.impl;


import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ac.circular.login.entity.User;
import com.ac.circular.login.mapper.UserMapper;
import com.ac.circular.login.service.LoginService;
import com.ac.common.MessageInfo;
import com.ac.common.util.EncryptUtil;
@Service(value="loginService")
public class LoginServiceImpl implements LoginService {
	@Resource
	UserMapper userMapper;
	public MessageInfo validUserName(User user) {
		String message = "";
		boolean isSuccess = false;
		if(StringUtils.isBlank(user.getUserName()))
		{
			message = "请输入用户名";
		}
		if(StringUtils.isBlank(user.getPassWord()))
		{
			message = "请输入密码";
		}
		User checkUser = userMapper.getByUserName(user.getUserName());
		if(checkUser == null)
		{
			message = "该用户不存在";
		}
		else
		{
			if(EncryptUtil.getMd5(user.getPassWord()).equals(checkUser.getPassWord()))
			{
				message = "登陆成功";
				isSuccess = true;
				//设置信息
				user.setId(checkUser.getId());
				
			}
			else
			{
				message = "密码错误";
			}
			
		}
		MessageInfo mes = new MessageInfo();
		mes.setMessage(message);
		mes.setIsSuccess(isSuccess);
		return mes;
	}
}
