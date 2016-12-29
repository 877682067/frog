<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common_base.jsp"%>
<html>
<head>
	<script type="text/javascript">
//菜单ztree
var tree;
$(document).ready(function(){
		var t = $("#tree");
		var setting = {
				view: {
					dblClickExpand: false,
					showLine: true,
					selectedMulti: false
				},
				check: {
					enable: true,
					chkStyle:"checkbox",
					checkboxType:{"Y":"p","N":"p"},
					nocheckInherit: true,
					autoCheckTrigger: true
				},
				data: {
					simpleData: {
						enable:true,
						idKey: "id",
						pIdKey: "parent",
						rootPId: ""
					}
				},
			};
		var zNodes = createTreeNode('${menus}');
		tree = $.fn.zTree.init(t, setting, zNodes);
		checkNodes();
	});
function createTreeNode(menus)
{
/* 	menus = menus.replace(/parent/g, "pId");
	menus = menus.replace(/url/g, "_url"); */
	menus = eval(menus);
	menus.push({"name":"菜单","id":"-1","parent":""});
/* 	$("#dataParam").attr("data-parent","-1"); */
	return menus;
}
function getMenuNodes()
{
	return tree.getCheckedNodes();	
}
function checkNodes()
{
	debugger;
	var needcheck = eval('${checkedMenus}');
	if(!needcheck)
		return;
	for(var i  = 0;i<needcheck.length;i++)
	{
		var node = tree.getNodeByParam("id",needcheck[i].id);
		tree.checkNode(node,true);		//check
		tree.expandNode(node,true);		//展开节点
	}
}
</script>


</head>
<body>
	<ul id="tree" class="ztree" style="width:260px; overflow:auto;"></ul>
</body>
</html>