<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common_base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">

	$(function()
	{
		$("#addBtn").click(function()
		{
			$.ajax({
				url:$('#form').attr("action"),
				type:"post",		
				data:$("#form").serialize(),		
				success:function(data)
				{
					$.alert("保存成功 ");
					location.href="<%=basePath%>"+data;
				}
			});
		});
	});

	function  showUser(element)
	{
		if(element.checked)
		{
			$.modal("user","注册新用户","<%= basePath%>/employee/toRegist.action",'','',"400px",'calback','cancel');
		}
	}
	function  cancel()
	{
		$("#isUser").removeAttr("checked");
	}
	function calback()
	{
		if(!jQuery("#user").validationEngine('validate'))
		{
			return false;
		}
		$("#userName").val($("#user_name").val());
		$("#userPassWord").val(hex_md5($("#passWord").val()));
		$("#role").val($("#roleS").val());
	}
</script>
</head>
<body>
	<div class="form-group  circular-btn" >
		<button type="button" id="addBtn"  class="btn btn-default">保存</button>
		<button type="button" id="updateBtn"  onclick="javascript:history.back(-1);" class="btn btn-success">返回</button>
	</div>
	<input type="hidden" id="id" name = "id"  value="${employee.name}"/>
	<div class="form-group circular-form">
		<cir:form id="form"  action="/employee/save.action" >
			<div class="form-group text-center ">
				<label  class="text-left">名称:</label>
	    		<cir:input type="text" id="name" required="true" value="${employee.name}"></cir:input>
			</div>
			<div class="form-group text-center ">
				<label class="text-left">工号:</label>
	    		<cir:input type="text" id="no" required="true" value="${employee.no}"></cir:input>
			</div>	
			<div class="form-group text-center ">
				<label class="text-left">是否注册账户 :</label>
				<cir:input type="checkbox" id="isUser" value="true" change="showUser"></cir:input>
			</div>
			<input type="hidden" id="userName" name="user.name"/>
			<input type="hidden"  id="userPassWord" name="user.passWord"/>
			<input type="hidden"  id="role" name="user.role"/>
		</cir:form>
	</div>
</body>
</html>