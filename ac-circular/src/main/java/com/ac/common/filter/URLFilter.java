package com.ac.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class URLFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException 
	{
		request.setCharacterEncoding("UTF-8");     
		// 设置返回请求的字符编码     
		response.setContentType("text/html; charset=utf-8");     
		// 转换ServletRequest为 HttpServletRequest     
		HttpServletRequest req = (HttpServletRequest) request;     
		// 转换ServletResponse为HttpServletRequest     
		HttpServletResponse res = (HttpServletResponse) response;     
		// 获取Session     
		HttpSession session = req.getSession();     
		// 获取Session中存储的对象     
		Object o = session.getAttribute("user");     
		// 获取当前请求的URI     
		String url = req.getRequestURI();     
		// 判断Session中的对象是否为空；判断请求的URI是否为不允许过滤的URI     
		if (o == null      
				&& !url.endsWith("login.jsp")     // 对URL地址为此结尾的文件不过滤     
				&& !url.endsWith("common_base.jsp")  
				&& url.indexOf("login.action") < 0    // 对URL地址中包含此字符串的文件不过滤     
				&& !url.endsWith(".js")  
				&& !url.endsWith("cir-phone.html")  
				&& !url.endsWith("circular/phoneList.action")  
				&& !url.endsWith("circular/getAttach.action")  
				&& !url.endsWith("cir-phone.html")  
				&& !url.endsWith(".css")  
				&& !url.substring(0,url.length()-1).equals(req.getContextPath())  
				&& url.indexOf("/images/") < 0
				) 
		{     
			res.sendRedirect(req.getContextPath());     
		} 
		else 
		{     
			chain.doFilter(request, response);     
			res.setHeader("Cache-Control","no-store");           
			res.setDateHeader("Expires",0);        
			res.setHeader("Pragma","no-cache");      
			res.flushBuffer();     
		}    
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
