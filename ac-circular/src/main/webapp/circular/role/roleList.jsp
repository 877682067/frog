<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common_base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<div class="form-group pull-right circular-btn">
		<!-- <button type="button" id="addBtn" class="btn btn-success">新增</button> -->
		<cir:button type="add" url="/role/toAdd.action" tableid="role" id="addBtn"></cir:button>
		<cir:button type="update" url="/role/toAdd.action" tableid="role" id="updateBtn"></cir:button>
		<cir:button type="del" url="/role/delRole.action" tableid="role" id="delBtn"></cir:button>
	</div>
	<cir:table id="role" url="/role/list.action"  clas="table table-bordered table-hover">
		<cir:th id="name" name="name" width="100px">名称</cir:th>
	</cir:table>
</body>
</html>