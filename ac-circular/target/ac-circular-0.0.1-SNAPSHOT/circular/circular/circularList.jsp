<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common_base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
	function format(data,type,full)
	{
		return '<a  href="<%= basePath%>/circular/toView.action?id='+full.id+'">'+data+'</a>';	
	}
</script>
</head>
<body>
	<div class="form-group pull-right circular-btn">
		<cir:button type="add" url="/circular/toAdd.action"   tableid="circular"  id="addBtn"></cir:button>
		<cir:button type="update" url="/circular/toAdd.action" tableid="circular" id="updateBtn"></cir:button>
		<cir:button type="del" url="/circular/delCircular.action" tableid="circular" id="delBtn"></cir:button>
	</div>
	<cir:table id="circular" url="/circular/list.action"  clas="table table-bordered table-hover">
		<cir:th id="name" name="name" width="100px" format ="format">名称</cir:th>
	</cir:table>
</body>
</html>