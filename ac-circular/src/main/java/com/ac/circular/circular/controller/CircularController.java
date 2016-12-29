package com.ac.circular.circular.controller;


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

import com.ac.circular.attach.entity.Attach;
import com.ac.circular.attach.entity.AttachMapping;
import com.ac.circular.attach.service.AttachService;
import com.ac.circular.circular.entity.Circular;
import com.ac.circular.circular.service.CircularService;
import com.ac.common.BaseCtronller;
import com.ac.common.StatusConstant.AttachType;
import com.ac.common.paging.PageResult;
import com.ac.common.paging.PhonePage;
import com.ac.common.util.BeanUtils;

@Controller
public class CircularController extends  BaseCtronller{
	@Autowired
	private CircularService circularService;
	@Autowired
	private AttachService attachService;
	@RequestMapping(value="/circular/circular.action",method={RequestMethod.GET})
	public String circular(HttpServletRequest req, HttpServletResponse resp)
	{
		return "circular/circular/circularList";
	}
	/**
	 * 获取列表
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="/circular/list.action",method={RequestMethod.POST})
	public void list(HttpServletRequest req, HttpServletResponse resp)
	{
		PageResult<Circular> page = PageResult.newInstance(req.getParameter("page").toString());
		page.setParams(BeanUtils.requestToMap( req));
		resp.setContentType("text/javascript; charset=utf-8");
		try {
			page.setAaData(circularService.pageList(page));
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
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/circular/phoneList.action",method={RequestMethod.POST})
	public List<Circular> phonePage(HttpServletRequest req, HttpServletResponse resp)
	{
		Circular cir = BeanUtils.reflectParameter(Circular.class, req);
		@SuppressWarnings("unchecked")
		PhonePage<Circular> phonePage  = BeanUtils.reflectParameter(PhonePage.class, req);
		phonePage.setParam(cir);
		return this.circularService.phoneList(phonePage);
	}
	/**
	 * 保存
	 * @param req
	 * @param resp
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/circular/save.action",method={RequestMethod.POST})
	public String save(HttpServletRequest req, HttpServletResponse resp)
	{
		Circular circular = BeanUtils.reflectParameter(Circular.class, req);
		if(circular.getId()==null)
		{
			circular.setCreatetime(new Date());
			circular.setCreator(this.getUser(req).getId());
		}
		else
		{
			circular.setLastmodify(new Date());
			circular.setLastmodifier(this.getUser(req).getId());
		}
		String[] attachs =  req.getParameterValues("attach");
		this.circularService.save(circular,attachs);
		return "/circular/circular.action";
	}
	
	/**
	 * 获取列表
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="/circular/toAdd.action",method={RequestMethod.GET})
	public String toAdd(HttpServletRequest req, HttpServletResponse resp)
	{
		Circular circular = BeanUtils.reflectParameter(Circular.class, req);
		if(circular.getId()!=null)
		{
			req.setAttribute("circular", this.circularService.getCircular(circular.getId()));
		}
		return "circular/circular/circularEdit";
	}
	/**
	 * 查看
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="/circular/toView.action",method={RequestMethod.GET})
	public String toView(HttpServletRequest req, HttpServletResponse resp)
	{
		Circular circular = BeanUtils.reflectParameter(Circular.class, req);
		if(circular.getId()!=null)
		{
			req.setAttribute("circular", this.circularService.getCircular(circular.getId()));
		}
		return "circular/circular/circularView";
	}

	@RequestMapping(value="/circular/delCircular.action",method={RequestMethod.POST})
	public void delCircular(HttpServletRequest req, HttpServletResponse resp)
	{
		String ids  = req.getParameter("ids");
		List<String> ls = null;
		if(StringUtils.isNotBlank(ids))
		{
			ls = Arrays.asList(ids.split(","));
		}
		if(ls!=null)
		{
			this.circularService.delCircular(ls);
		}
		resp.setContentType("text/javascript; charset=utf-8");
		try {
			resp.getWriter().write(JSONObject.fromObject(1).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@ResponseBody
	@RequestMapping(value="/circular/getAttach.action",method={RequestMethod.POST})
	public List<Attach> getAttach(HttpServletRequest req, HttpServletResponse resp)
	{
		AttachMapping attach = BeanUtils.reflectParameter(AttachMapping.class, req);
		attach.setType(AttachType.CIRCULAR);
		return attachService.getAttach(attach);
	}
}
