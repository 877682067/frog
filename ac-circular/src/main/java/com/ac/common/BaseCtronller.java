package com.ac.common;

import javax.servlet.http.HttpServletRequest;

import com.ac.circular.login.entity.User;


public class BaseCtronller {

	public User getUser(HttpServletRequest  request)
	{
		return  (User) request.getSession().getAttribute("user");
	}
	public String  getPath(HttpServletRequest request)
	{
		String path = request.getContextPath();
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path ;
	}
}
