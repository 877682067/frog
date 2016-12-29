<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="<%=basePath%>/common/jquery-plugin/bootstrap/css/bootstrap.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="<%= basePath%>/common/js/jquery-1.11.3.min.js"></script>
<!-- \ Bootstrap 核心 JavaScript 文件 -->
<script src="<%= basePath%>/common/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>/circular/login/css/index.css">
<!-- DataTables -->
<script type="text/javascript" src="<%=basePath%>/common/jquery-plugin/dataTables/js/jquery.dataTables.js"></script>
<link rel="stylesheet" href="<%=basePath%>/common/jquery-plugin/dataTables/css/jquery.dataTables.css">
<!-- select2 -->
<script type="text/javascript" src="<%=basePath%>/common/jquery-plugin/select2/select2.full.js"></script>
<script type="text/javascript" src="<%=basePath%>/common/jquery-plugin/select2/i18n/zh-CN.js"></script>
<link rel="stylesheet" href="<%=basePath%>/common/jquery-plugin/select2/select2.min.css">
<!-- 表单验证控件 -->
<script type="text/javascript" src="<%=basePath%>/common/jquery-plugin/validationEngine/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=basePath%>/common/jquery-plugin/validationEngine/jquery.validationEngine-zh_CN.js"></script>
<link rel="stylesheet" href="<%= basePath%>/common/jquery-plugin/validationEngine/css/validationEngine.jquery.css">
<%-- <link rel="stylesheet" href="<%= basePath%>/common/jquery-plugin/validationEngine/css/template.css">
 --%>
 <!-- ztree -->
 <script src="<%=basePath%>/common/jquery-plugin/ztree/js/jquery.ztree.all.min.js"></script>
<link  rel="stylesheet" href="<%=basePath%>/common/jquery-plugin/ztree/css/metroStyle/metroStyle.css">
<!-- fileupload 文件上传下载插件 -->
 <script src="<%=basePath%>/common/jquery-plugin/ztree/js/jquery.ztree.all.min.js"></script>
<link  rel="stylesheet" href="<%=basePath%>/common/jquery-plugin/fileupload/css/fileinput.min.css">
 <script type="text/javascript" src="<%=basePath%>/common/jquery-plugin/fileupload/js/fileinput.js"></script>
<script type="text/javascript" src="<%=basePath%>/common/jquery-plugin/fileupload/locals/zh.js"></script>
<script type="text/javascript" src="<%=basePath%>/common/jquery-plugin/fileupload/themes/fa/theme.js"></script>
<!-- 富文本框插件 -->
<script type="text/javascript" src="<%=basePath%>/common/jquery-plugin/ckeditor/ckeditor.js"></script>
<!-- 自定义扩展Jquery弹出窗口方法 -->
<script type="text/javascript" src="<%=basePath%>/common/jquery-plugin/custom/jquery.circular-modal.js"></script>
<!-- 按钮 增删改查的方法 -->
<script type="text/javascript" src="<%=basePath%>/common/jquery-plugin/custom/jquery.circular-button.js"></script>
<!-- md5 -->
<script type="text/javascript" src="<%=basePath%>/common/js/md5.js"></script>
<%@ include file="../../common/taglibs.jsp"%>
<script type="text/javascript">
 $.fn.modal.Constructor.prototype.enforceFocus = function () {};
 
 var basePath  = "";
 $(function()
 {

	 function _getRootPath() {
	     var pathName = window.location.pathname.substring(1);
	     var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
	     if (webName == "") {
	         return window.location.protocol + '//' + window.location.host;
	     }
	     else {
	         return window.location.protocol + '//' + window.location.host + '/' + webName;
	     }
	 }
	 basePath =  _getRootPath();
 });
 </script>