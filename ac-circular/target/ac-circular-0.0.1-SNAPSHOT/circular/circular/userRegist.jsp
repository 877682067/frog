<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common_base.jsp"%>
<html>
<head>
<script type="text/javascript">
	<%-- $(function()
	{
		debugger;
		$("#roleS").select2({
			allowClear: true,
			  initSelection: function (element, callback) {
                      return callback({id:1});
              },
			  ajax: {
				    url: "<%= basePath%>role/getRoles.action",
				    dataType: 'json',
				    type:"post",
				  /*   data: function (params) {
				    	console.info("ajax");
				    	console.info(params);
				    	return {
				        q: params.term, // search term
				        page: params.page
				      };
				    }, */
				    processResults: function (data, params) {
				      // parse the results into the format expected by Select2
				      // since we are using custom formatting functions we do not need to
				      // alter the remote JSON data, except to indicate that infinite
				      // scrolling can be used
				      debugger;
				      params.page = params.page || 1;

				      return {
				        results: data,
				        pagination: {
				          more: (params.page * 30) < data.total_count
				        }
				      };
				    },
				    cache: true
				  },
				  width:"200px",
				  createSearchChoice   : function(term, data) {           // 创建搜索结果（使用户可以输入匹配值以外的其它值）
				        return { id: term, text: term };
				    },
				  escapeMarkup: function (markup) { return markup; } // let our custom formatter work
				});
	});
	function formatRepo(a,b,c,d,e)
	{
		debugger;
		console.info("this is formatRepo");
		console.info(a);
		console.info(b);
		console.info(c);
		console.info(d);
		console.info(e);
	}
	function formatRepoSelection(a,b,c,d,e)
	{
		debugger;
		console.info("this is formatRepoSelection");
		console.info(a);
		console.info(b);
		console.info(c);
		console.info(d);
		console.info(e);
	} --%>
</script>


</head>
<body>
	<cir:form id="user">
		<div  class="form-group text-center">
			<label>账号：</label>
			<cir:input id="user.name"  required="true" ></cir:input>
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
			 <cir:select id="roleS" url="role/getRoles.action" required="true" key="id" text="name"></cir:select>
		</div>
	</cir:form>
</body>
</html>