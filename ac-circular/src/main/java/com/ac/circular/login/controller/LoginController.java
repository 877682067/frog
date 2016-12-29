package com.ac.circular.login.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ac.circular.login.entity.User;
import com.ac.circular.login.service.LoginService;
import com.ac.circular.menu.entity.Menu;
import com.ac.circular.menu.service.MenuService;
import com.ac.common.MessageInfo;
import com.ac.common.util.BeanUtils;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="/login.action",method={RequestMethod.POST})
	public String login(HttpServletRequest req, HttpServletResponse resp,RedirectAttributes attr)
	{
		User user = BeanUtils.reflectParameter(User.class, req);
		MessageInfo mes = loginService.validUserName(user);
		if(mes.getIsSuccess())
		{
			this.setSession(user,req);
			return "index";
		}
		attr.addFlashAttribute("msg", mes.getMessage());
		return "redirect:circular/login/login.jsp";
	}
	@RequestMapping(value="/loginOut.action",method={RequestMethod.GET})
	public String loginOut(HttpServletRequest req, HttpServletResponse resp,RedirectAttributes attr)
	{
		req.getSession().invalidate();
		return "redirect:circular/login/login.jsp";
	}

	private void setSession(User user,HttpServletRequest req) {
		List<Menu> menu = menuService.getMenu(user);
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("menus",JSONArray.fromObject(menu).toString());
	}
	
}
