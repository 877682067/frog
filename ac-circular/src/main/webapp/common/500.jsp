<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统提示</title>
<link href="${ctx}/css/common.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="margin:160px auto;background:url(${ctx}/images/error/error_ts.jpg) no-repeat;width:280px;height:101px;padding:26px 20px 0 217px;color:#696969;font-weight:bold;">
	系统发生内部错误,请与系统管理员联系！
</div>
<div style="display: none;">
<%
	Throwable ex = null;
	if (exception != null)
		ex = exception;
	if (request.getAttribute("javax.servlet.error.exception") != null)
		ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
	//记录日志
	out.println(ex);
%>
</div>
</body>
</html>
