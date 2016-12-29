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
		<cir:button type="add" url="/employee/toAdd.action"   tableid="employee"  id="addBtn"></cir:button>
		<cir:button type="update" url="/employee/toAdd.action" tableid="employee" id="updateBtn"></cir:button>
		<cir:button type="del" url="/employee/delEmployee.action" tableid="employee" id="delBtn"></cir:button>
		<!-- <button type="button" id="addBtn" onclick="showAdd();" class="btn btn-success">新增</button>
		<button type="button" id="updateBtn" onclick="updateAdd();" class="btn btn-info">修改</button>
		<button type="button" id="delBtn" onclick="delAdd();" class="btn btn-warning">删除</button> -->
	</div>
	<cir:table id="employee" url="/employee/list.action" clas="table table-bordered table-hover">
		<cir:th id="name" name="name" width="100px">名称</cir:th>
	</cir:table>
</body>
</html>