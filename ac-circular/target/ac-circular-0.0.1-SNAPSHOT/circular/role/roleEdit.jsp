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

	function  showMenu()
	{
		$.modal("menu","设置资源","<%= basePath%>/role/toSetMenu.action?id="+$("#id").val(),'','',"400px",'calback');
	}
	function calback()
	{
		var nodes = getMenuNodes();//roleSetMenu里的方法
		var menuIds=[];
		for(var i=0;i<nodes.length;i++)
		{
			console.info(nodes[i]);
			menuIds.push(nodes[i].id);
		}
		$("#menus").val(menuIds.join(","));
	}
</script>
</head>
<body>
	<div class="form-group circular-btn">
		<button type="button" id="addBtn"  class="btn btn-default">保存</button>
		<button type="button" id="updateBtn"  onclick="javascript:history.back(-1);" class="btn btn-success">返回</button>
	</div>
	<div class="form-group circular-form">
		<cir:form id="form"  action="/role/save.action">
		<input type="hidden" id="id" name = "id" value="${role.id }"/>
			<div class="form-group text-center ">
				<label  style="width: 100px;" class="text-left">名称:</label>
	    		<cir:input type="text" id="name" required="true" value="${role.name }" ></cir:input>
	    		<input type="hidden" name="menus"  id="menus" />
			</div>	
		</cir:form>
		<div class="form-group text-center ">
				<label style="width: 100px;" class="text-left">设置菜单资源:</label>
				<button  style="width: 210px;" type="button" class="btn btn-primary btn-lg" onclick="showMenu();">
				</button>
		</div>
	</div>
</body>
</html>