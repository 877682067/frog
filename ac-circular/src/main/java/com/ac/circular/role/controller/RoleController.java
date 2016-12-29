package com.ac.circular.role.controller;


import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ac.circular.menu.service.MenuService;
import com.ac.circular.role.entity.Role;
import com.ac.circular.role.service.RoleService;
import com.ac.common.BaseCtronller;
import com.ac.common.paging.PageResult;
import com.ac.common.util.BeanUtils;

@Controller
public class RoleController extends  BaseCtronller{
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@RequestMapping(value="/role/role.action",method={RequestMethod.GET})
	public String role(HttpServletRequest req, HttpServletResponse resp)
	{
		return "circular/role/roleList";
	}
	/**
	 * 获取列表
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="/role/list.action",method={RequestMethod.POST})
	public void list(HttpServletRequest req, HttpServletResponse resp)
	{
		PageResult<Role> page = PageResult.newInstance(req.getParameter("page").toString());
		page.setParams(BeanUtils.requestToMap( req));
		resp.setContentType("text/javascript; charset=utf-8");
		try {
			page.setAaData(roleService.pageList(page));
			resp.getWriter().write(JSONObject.fromObject(page).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取列表
	 * @param req
	 * @param resp
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/role/save.action",method={RequestMethod.POST})
	public String save(HttpServletRequest req, HttpServletResponse resp)
	{
		Role role = BeanUtils.reflectParameter(Role.class, req);
		if(role.getId()==null)
		{
			role.setCreatetime(new Date());
			role.setCreator(this.getUser(req).getId());
		}
		this.roleService.save(role);
		return "/role/role.action";
	}
	/**
	 * 获取列表
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="/role/toAdd.action",method={RequestMethod.GET})
	public String toAdd(HttpServletRequest req, HttpServletResponse resp)
	{
		Role role = BeanUtils.reflectParameter(Role.class, req);
		if(role.getId()!=null)
		{
			req.setAttribute("role", this.roleService.getRole(role));
		}
		return "circular/role/roleEdit";
	}
	/**
	 * 获取列表
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="/role/toSetMenu.action",method={RequestMethod.POST})
	public String toSetMenu(HttpServletRequest req, HttpServletResponse resp)
	{
		Map m = BeanUtils.requestToMap(req);
		Object id = m.get("id");
		if(id !=null){
			req.setAttribute("checkedMenus", JSONArray.fromObject(this.menuService.getMenuByRoleId(id.toString())).toString());
		}
		req.setAttribute("menus", JSONArray.fromObject(
				this.menuService.list(null)
		).toString());
		return "circular/role/roleSetMenu";
	}
	@RequestMapping(value="/role/delRole.action",method={RequestMethod.POST})
	public void delRole(HttpServletRequest req, HttpServletResponse resp)
	{
		String ids  = req.getParameter("ids");
		List<String> ls = null;
		if(StringUtils.isNotBlank(ids))
		{
			ls = Arrays.asList(ids.split(","));
		}
		if(ls!=null)
		{
			this.roleService.delRole(ls);
		}
		resp.setContentType("text/javascript; charset=utf-8");
		try {
			resp.getWriter().write(JSONObject.fromObject(1).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/role/getRoles.action",method={RequestMethod.POST})
	public @ResponseBody List getRoles(HttpServletRequest req, HttpServletResponse resp)
	{
		Map m = BeanUtils.requestToMap(req);
		
		return this.roleService.getRoles(m);
	}
}
