<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<cir:form id="menu"  action="/menu/save.action" clas="pull-center" callback="updatePage">
		<input type="hidden" id="id" name = "id"/>
		<input type="hidden" id="parent" name="parent"/>
		<input type="hidden" id="sort" name="sort"/>
		<div class="form-group pull-center">
			<label for="exampleInputEmail1" class="text-left">名称:</label>
    		<cir:input type="text" id="name" required="true"></cir:input>
		</div>	
		<div class="form-group pull-center">
			<label for="exampleInputEmail1" class="text-left">地址:</label>
    		<cir:input type="text" required="true" minlength="5" id="url" placeholder=""></cir:input>
		</div>	
		<div class="form-group pull-center">
		</div>
	</cir:form>
</body>
</html>