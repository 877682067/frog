<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common_base.jsp"%>
<html>
<head>
</head>
<body>
	<cir:form id="user">
		<div  class="form-group text-center">
			<label>账号：</label>
			<cir:input id="user_name"  required="true" ></cir:input>
		</div>
		<div  class="form-group text-center">
			<label>密码：</label>
			<cir:input id="passWord" type="password" required="true"></cir:input>
		</div>
		<div  class="form-group text-center">
			<label>确认密码：</label>
			<cir:input id="reuPassWord" type="password" required="true" equals="passWord"></cir:input>
		</div>
		<div  class="form-group text-center">
			<label>角色：</label>
			 <cir:select id="roleS" url="/role/getRoles.action" required="true" key="id" text="name"></cir:select>
		</div>
	</cir:form>
</body>
</html>