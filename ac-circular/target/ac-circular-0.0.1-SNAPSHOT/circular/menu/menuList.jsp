<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
	function getparam()
	{
		var parm = {};
		var parent = $("#dataParam").attr("data-parent");
		parent && (parm.parent  = parent);
		var id = $("#dataParam").attr("data-id");
		parent && (parm.id  = id);
		return parm;
	}
	function refreshTable()
	{
		//标签 生成
		_refreshdata();
	}
</script>
</head>
<body>
	<cir:table id="data" url="/menu/list.action" param="getparam"  clas="table table-bordered table-hover">
		<cir:th id="name" name="name" width="100px">名称</cir:th>
		<cir:th id="url" name="url" width="150px">地址</cir:th>
	</cir:table>
</body>
</html>