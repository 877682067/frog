<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="../../common/common_base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>国航公告信息管理系统</title>
<style>  
body
{  
	margin-left:auto;  
	margin-right:auto; 
	margin-TOP:100PX; 
	width:20em;  
}
</style>
<script type="text/javascript">
	$(function()
	{
		if("${msg}"!="null"&&"${msg}"!="")
		{
			$.alert("${msg}");
		}
		
	});
function makePasswordMD5()
{
	$("#pass").removeAttr("name");
	$("#passWord").val(hex_md5($("#pass").val()));
	return true;
}
</script>
</head>
<body>
	<cir:form id="login" action="/login.action" beforeSubmit="makePasswordMD5">
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-user"></i></span>
			<cir:input id="userName" placeholder="用户名" required="true" ></cir:input>
			<!-- <input id="userName" type="text" class="form-control" placeholder="用户名" aria-describedby="basic-addon1"> -->
		</div>
		<br>
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-lock"></i></span>
			<input type="hidden" name="passWord"  id = "passWord">
			<cir:input id="pass" type="password"  required="true" placeholder="密码"></cir:input>
<!-- 			<input id="passWord" type="password" class="form-control" placeholder="密码" aria-describedby="basic-addon1">
 -->		</div>
		<br>
		<button type="submit" style="width:249px;" class="btn btn-default">登 录</button>
	</cir:form>

	<%-- <form action="<%=basePath %>/login.action" method="post"> 
	<div class="input-group">
		<span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-user"></i></span>
		<input id="userName" type="text" class="form-control" placeholder="用户名" aria-describedby="basic-addon1">
	</div>
	<br>
	<div class="input-group">
		<span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-lock"></i></span>
		<input id="passWord" type="password" class="form-control" placeholder="密码" aria-describedby="basic-addon1">
	</div>
	<br>
	<button type="submit" style="width:249px;" class="btn btn-default">登 录</button>
    </form>--%>
</body>
</html>