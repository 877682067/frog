package com.ac.common.tag.form.select.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ac.common.tag.form.select.service.SelectService;
import com.ac.common.util.BeanUtils;
@Controller
public class SelectContoller {
	@Autowired
	SelectService selectService;
	@ResponseBody 
	@RequestMapping(value="/select/select.action",method={RequestMethod.POST})
	public void getData(HttpServletRequest req, HttpServletResponse resp)
	{
		Map m = BeanUtils.requestToMap(req);
		this.selectService.query(m);
	}
	
}
