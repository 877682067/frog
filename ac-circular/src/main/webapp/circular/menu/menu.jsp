<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/common_base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
				data: {
					simpleData: {
						enable:true,
						idKey: "id",
						pIdKey: "pId",
						rootPId: ""
					}
				},
				callback: {
					beforeClick: function(treeId, treeNode) {
						var zTree = $.fn.zTree.getZTreeObj("tree");
						if (treeNode.isParent) {
							$("#dataParam").attr("data-parent",treeNode.id);
							$("#parent").val(treeNode.id);
							showList();
							refreshTable();
							return true;
						} else {
							$("#id").val(treeNode.id);
							$("#parent").val(treeNode.pId);
							$("#sort").val(treeNode.sort);
							$("#url").val(treeNode._url);
							$("#name").val(treeNode.name);
							showForm();
							return true;
						}
					}
				}
			};
		var zNodes = createTreeNode('${menus}');
		tree = $.fn.zTree.init(t, setting, zNodes);
		$("#saveBtn").click(function(){
			$("#menu").submit();
		});
	});
function createTreeNode(menus)
{
	menus = menus.replace(/parent/g, "pId");
	menus = menus.replace(/url/g, "_url");
	menus = eval(menus);
	menus.push({"name":"菜单","id":"-1","pId":""});
	$("#dataParam").attr("data-parent","-1");
	return menus;
}
function updatePage(menu)
{
	var menuNode = {
			id:menu.id,
			pId:menu.parent,
			_url:menu.url,
			name:menu.name,
			sort:menu.sort
	};
	var parent = tree.getNodesByParam("id",menuNode.pId)[0];
	var node = tree.getNodesByParam("id",menuNode.id)[0];
	if(node)
	{
		node.name = menuNode.name;
		node._url = menuNode._url;
		node.sort = menuNode.sort;
		tree.updateNode(node);
	}
	else
	{
		tree.addNodes(parent,menuNode);
	}
	refreshTable();
	showList();
	$.alert("保存成功！");
}
function showList()
{
	$("#saveBtnDiv").hide();
	$("#menuEdit").hide();
	$("#menuList").show();
}
function showForm()
{
	$("#menuList").hide();
	$("#menuEdit").show();
	$("#saveBtnDiv").show();
}
function showAdd()
{
	$('#menu')[0].reset();
	$('#id').val("");
	$('#sort').val("");
	showForm();
}

function validDel(datas)
{
	var candel=true,name="";
	for(var i = 0;i<datas.length; i++)
	{
		var node = tree.getNodeByParam("id",datas[i].id);
		if(node.children.length>0)
		{
			candel=  false;
			name = datas[i].name;
			break;
		}
	}
	if(!candel)
	{
		$.alert("‘"+name+"’下存在，子菜单不可删除，请重新选择！");
	}
	return candel;
}

</script>
</head>
<body>
	<input type="hidden" id="dataParam" data-parent=""/>
	<!-- 树形结构菜单 -->
	<div class="col-xs-2 col-sm-2 col-md-2">
		<ul id="tree" class="ztree" style="width:260px; overflow:auto;"></ul>
	</div>
	<!-- 列表增加修改 -->
	<div class="col-xs-10 col-sm-10 col-md-10">
		<div id="menuList" style="display: none;">
			<div class="form-group  pull-right circular-btn">
				<button type="button" id="addBtn" onclick="showAdd();" class="btn btn-success">新增</button>
				<cir:button type="del" url="/menu/delMenu.action" tableid="data" valid="validDel" id="delBtn"></cir:button>
			</div>
			<div class="form-group">
				<%@ include file="menuList.jsp" %>
			</div>
		</div>
		<div  id="saveBtnDiv" class="form-group  pull-right circular-btn"  style="display: none;">
			<button type="submit" id="saveBtn"  class="btn btn-default">保存</button>
		</div>
		<div id="menuEdit" class="pull-center" style="display: none;">
			<div  class="text-center circular-form" >
			<%@ include file="menuEdit.jsp" %>
			</div>
		</div>
	</div>
</body>
</html>