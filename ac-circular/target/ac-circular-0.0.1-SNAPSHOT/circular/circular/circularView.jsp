<%@page import="com.ac.common.StatusConstant.CircularType"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common_base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	.form-control{
		width:100%!important;
	}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
	$(function()
	{
		var attach=[];
		<c:forEach items = "${circular.attach}" var = "attach" varStatus="status">
			attach.push({path:"${attach.path}",name:"${attach.name}",id:"${attach.id}"});
		 </c:forEach>
		 if(attach.length>0)
		 {
			 var initinput='',initshow='';
			 setting.initialPreview=attach;
			 $.each(attach, function(key, info) {
				 initinput +='<input  type="hidden" id="attach'+info.id+'" name="attach" value="'+info.id+'" /> ';
				 initshow += '<li class="alert-success"><a href="javascript:download(\''+info.path+'\',\''+info.name+'\')">'+info.name+'</a></li>';;
	          });
	        $("#form").append(initinput);
	        $('#uploadinfo ul').append(initshow);
	        $('#uploadinfo').fadeIn('slow');
		 }
	});
	
	function  download(path,name)
	{
		var $form =$('<form action="<%=basePath%>/upload/download.action"  method="post"></form>');
		$form.append($('<input type="hidden" name="path" value="'+path+'"/>'));	
		$form.append($('<input type="hidden" name="name" value="'+name+'"/>'));	
		$form.submit();
	}
</script>
</head>
<body>
	<div class="form-group   circular-btn" >
		<button type="button" id="updateBtn"  onclick="javascript:history.back(-1);" class="btn btn-success">返回</button>
	</div>
	<div class="form-group circular-form">
			<div class="form-group text-center ">
				<span id ="title">${circular.name}</span>
			</div>	
			<div class="form-group text-center ">
					${circular.content}
			</div>
			<div id="uploadinfo" class="alert fade in" style="margin-top:10px;display:none">
				<ul>
				</ul>
			</div>
	</div>
</body>
</html>