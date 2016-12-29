package com.ac.circular.employee.controller;


import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ac.circular.employee.entity.Employee;
import com.ac.circular.employee.service.EmployeeService;
import com.ac.circular.login.entity.User;
import com.ac.common.BaseCtronller;
import com.ac.common.paging.PageResult;
import com.ac.common.util.BeanUtils;
import com.ac.common.util.EncryptUtil;

@Controller
public class EmployeeController extends  BaseCtronller{
	@Autowired
	private EmployeeService employeeService;
	@RequestMapping(value="/employee/employee.action",method={RequestMethod.GET})
	public String employee(HttpServletRequest req, HttpServletResponse resp)
	{
		return "circular/employee/employeeList";
	}
	/**
	 * 获取列表
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="/employee/list.action",method={RequestMethod.POST})
	public void list(HttpServletRequest req, HttpServletResponse resp)
	{
		PageResult<Employee> page = PageResult.newInstance(req.getParameter("page").toString());
		page.setParams(BeanUtils.requestToMap( req));
		resp.setContentType("text/javascript; charset=utf-8");
		try {
			page.setAaData(employeeService.pageList(page));
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
	@RequestMapping(value="/employee/save.action",method={RequestMethod.POST})
	public String save(HttpServletRequest req, HttpServletResponse resp)
	{
		Employee employee = BeanUtils.reflectParameter(Employee.class, req);
		/*employee.setIsUser(req.getParameter("isUser").equals("1"));*/
		if(employee.getId()==null)
		{	
			employee.setCreatetime(new Date());
			employee.setCreator(this.getUser(req).getId());
			User user = new User();
			user.setUserName(req.getParameter("user.name"));
			user.setPassWord(EncryptUtil.getMd5(req.getParameter("user.passWord")));
			user.setRoleId(req.getParameter("user.role"));
			employee.setUser(user);
		}
		this.employeeService.save(employee);
		return "/employee/employee.action";
	}
	/**
	 * 录入页面
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="/employee/toAdd.action",method={RequestMethod.GET})
	public String toAdd(HttpServletRequest req, HttpServletResponse resp)
	{
		Employee employee = BeanUtils.reflectParameter(Employee.class, req);
		if(employee.getId()!=null)
		{
			req.setAttribute("employee",this.employeeService.getEmployee(employee) );
		}
		
		return "circular/employee/employeeEdit";
	}
	/**
	 * 获取列表
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="/employee/toRegist.action",method={RequestMethod.POST})
	public String toRegist(HttpServletRequest req, HttpServletResponse resp)
	{
		return "circular/employee/userRegist";
	}

	@RequestMapping(value="/employee/delEmployee.action",method={RequestMethod.POST})
	public void delEmployee(HttpServletRequest req, HttpServletResponse resp)
	{
		String ids  = req.getParameter("ids");
		List<String> ls = null;
		if(StringUtils.isNotBlank(ids))
		{
			ls = Arrays.asList(ids.split(","));
		}
		if(ls!=null)
		{
			this.employeeService.delEmployee(ls);
		}
		resp.setContentType("text/javascript; charset=utf-8");
		try {
			resp.getWriter().write(JSONObject.fromObject(1).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
