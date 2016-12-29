package com.ac.circular.menu.controller;


import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ac.circular.menu.entity.Menu;
import com.ac.circular.menu.service.MenuService;
import com.ac.common.BaseCtronller;
import com.ac.common.paging.PageResult;
import com.ac.common.util.BeanUtils;

@Controller
public class MenuController extends BaseCtronller{
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="/menu/menu.action",method={RequestMethod.GET})
	public String menu(HttpServletRequest req, HttpServletResponse resp)
	{
		Menu menu = BeanUtils.reflectParameter(Menu.class, req);
		String menus = JSONArray.fromObject(menuService.list(menu)).toString();
		req.setAttribute("menus", menus);
		return "circular/menu/menu";
	}
	/**
	 * 获取列表
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="/menu/list.action",method={RequestMethod.POST})
	public void list(HttpServletRequest req, HttpServletResponse resp)
	{
		PageResult<Menu> page = PageResult.newInstance(req.getParameter("page").toString());
		page.setParams(BeanUtils.requestToMap( req));
		resp.setContentType("text/javascript; charset=utf-8");
		try {
			page.setAaData(menuService.pageList(page));
			resp.getWriter().write(JSONObject.fromObject(page).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 保存
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="/menu/save.action",method={RequestMethod.POST})
	public void save(HttpServletRequest req, HttpServletResponse resp)
	{
		Menu menu = BeanUtils.reflectParameter(Menu.class, req);
		if(menu.getId()==null)
		{
			menu.setCreatetime(new Date());
			menu.setCreator(this.getUser(req).getId());
		}
		this.menuService.save(menu);
		resp.setContentType("text/javascript; charset=utf-8");
		try {
			resp.getWriter().write(JSONObject.fromObject(menu).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除
	 * @param req
	 * @param resp
	 */
	@RequestMapping(value="/menu/delMenu.action",method={RequestMethod.POST})
	public void delMenu(HttpServletRequest req, HttpServletResponse resp)
	{
		String ids  = req.getParameter("ids");
		List<String> ls = null;
		if(StringUtils.isNotBlank(ids))
		{
			ls = Arrays.asList(ids.split(","));
		}
		if(ls!=null)
		{
			this.menuService.delMenu(ls);
		}
		resp.setContentType("text/javascript; charset=utf-8");
		try {
			resp.getWriter().write(JSONObject.fromObject(1).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
