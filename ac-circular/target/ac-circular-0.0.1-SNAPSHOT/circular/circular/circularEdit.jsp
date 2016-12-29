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
		var cke = CKEDITOR.replace( 'editor1',
				{  
				    filebrowserImageUploadUrl : '<%=basePath%>/upload/editorUploadImg.action',

				} );
		/* if("${circular.content}"!=""&&"${circular.content}"!="null")
			{
				cke.setData("${circular.content}");
			} */
		$("input[name='type'][value='${circular.type}']").attr("checked","checked");
		$("#addBtn").click(function()
		{
			//解决使用serialize()  不能获取ckeditor的内容 
			for( var instance in CKEDITOR.instances )
			{ 
				CKEDITOR.instances[instance].updateElement(); 
			}
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
		var setting ={
				language: 'zh', //设置语言
		        uploadUrl: "<%=basePath%>/upload/upload.action", //上传的地址
		        //allowedFileExtensions : ['jpg', 'png','gif'],//接收的文件后缀
		        //showUpload: true, //是否显示上传按钮
		        //showCaption: true,//是否显示标题
		        showPreview:false,
		       // browseClass: "btn btn-primary", //按钮样式             
		       // previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", 
		         // allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
	                //uploadExtraData:{"id": 1, "fileName":'123.mp3'},
	                uploadAsync: false, //默认异步上传
	                showUpload: false, //是否显示上传按钮fasl
	                showRemove : false, //显示移除按钮
	               // showPreview : true, //是否显示预览
	              //  showCaption: false,//是否显示标题
	                browseClass: "btn btn-primary", //按钮样式     
	               // dropZoneEnabled: false,//是否显示拖拽区域
	                //minImageWidth: 50, //图片的最小宽度
	                //minImageHeight: 50,//图片的最小高度
	                //maxImageWidth: 1000,//图片的最大宽度
	                //maxImageHeight: 1000,//图片的最大高度
	                //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
	                //minFileCount: 0,
	                //maxFilesNum: 10,
	                maxFileCount: 10, //表示允许同时上传的最大文件个数
	                enctype: 'multipart/form-data',
	                theme: "fa",
	                validateInitialCount:true,
	               // previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
	               // msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！"
		    };
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
				 initshow += '<li class="alert-success"><a href="javascript:download(\''+info.path+'\',\''+info.name+'\')">'+info.name+'</a><i onclick="removeAttach(this);" data-attach="'+info.id+'" class="glyphicon glyphicon-remove-sign"></i></li>';;
	          });
	        $("#form").append(initinput);
	        $('#uploadinfo ul').append(initshow);
	        $('#uploadinfo').fadeIn('slow');
		 }
		var _fileinput = $('#file').fileinput(setting).on('filebatchselected', function(event, data, id, index) {
		    	_fileinput.fileinput("upload");
		    }).on('filebatchuploadsuccess', function(event, data) 
    		{
		        var out = '';
		        $.each(data.files, function(key, file) {
		            var fname = file.name;
		            out = out + '<li class="alert-success">' + '# ' + (key + 1) + ' - '  +  fname + '上传成功.' + '</li>';
		        });
		        var inputOut ='';
		        $.each(data.response, function(key, info) {
		        	inputOut  +='<input  type="hidden" name="attach"  id="attach'+info+'" value="'+info+'" /> ';
		        });
		        $("#form").append(inputOut);
		        $('#uploadinfo ul').append(out);
		        $('#uploadinfo').fadeIn('slow');
		    }).on('filebatchuploaderror',function(event, data)
    		{
		    	var out='';
		    	 $.each(data.files, function(key, file) {
			            var fname = file.name;
			            var msg;
			            if(data.response.info)
		            	{
		            		msg  =data.response.info;
		            	}
		    	 		else if(event.result)
	    	 			{
		    	 			msg  = event.result;
	    	 			}
		    	 		else
	    	 			{
		    	 			msg  = "上传失败";
	    	 			}
			            out = out + '<li class="alert-danger">' + '# ' + (key + 1) + ' - '  +  fname + msg + '.</li>';
			        });
			        $('#uploadinfo ul').append(out);
			        $('#uploadinfo').fadeIn('slow');
    		});;
	});
	
	function removeAttach(obj)
	{
		var attachid = $(obj).attr("data-attach");
		$("#attach"+attachid).remove();
		$(obj).parent().remove();
	}
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
	<div class="form-group circular-btn" >
		<button type="button" id="addBtn"  class="btn btn-default">保存</button>
		<button type="button" id="updateBtn"  onclick="javascript:history.back(-1);" class="btn btn-success">返回</button>
	</div>
	<div class="form-group circular-form">
		<cir:form id="form"  action="/circular/save.action">
		<input type="hidden" id="id" name = "id" value="${circular.id }"/>
			<div class="form-group text-center">
				 <label>
	    		<input type="radio" name="type"  value="<%=CircularType.PROMOTION%>" checked="checked">促销
	    		<input type="radio" name="type"  value="<%=CircularType.BUSINESS%>">业务
	    		</label>
			</div>
			<div class="form-group  text-center">
				<label>标题</label>
			</div>
			<div class="form-group">
	    		<cir:input type="text" id="name" required="true" value="${circular.name}"></cir:input>
			</div>
			<div class="form-group text-center ">
			<label  class="control-label">正文</label>
				<textarea class="ckeditor" cols="80" id="editor1" name="content" rows="10">
				${circular.content}
				</textarea>
			</div>	
			<div class="form-group text-center ">
				<label class="control-label">附件 </label>
				<input id="file" type="file" multiple />
			</div>
			<div id="uploadinfo" class="alert fade in" style="margin-top:10px;display:none">
				<ul>
				</ul>
			</div>
		</cir:form>
	</div>
</body>
</html>