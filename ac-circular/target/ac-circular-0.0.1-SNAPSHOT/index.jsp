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
		var menus = eval('${menus}');
		loadMenu(menus);
		$("#menu dl:first ul").trigger("click");
		setWindowSize();
		registerMenuEvent();
		window.onresize = function(){
			setWindowSize();
		};
	});
	
	function setWindowSize()
	{
		var frameBodyheight  = $(document.body).height()-$("header").height();
		$("#frameBody").height(frameBodyheight);
		$("#frameSection").height(frameBodyheight);
		$("#iframe_box").height($("#frameBody").height()-$("#tabNav").height());
		$("#iframe_box").find("iframe").height($("#frameBody").height()-$("#tabNav").height());
		$("#iframe_box").find("body").height($("#frameBody").height()-$("#tabNav").height());
	}
	
	//加载菜单
	function loadMenu(menus)
	{
		//数组无元素时推出方法
		if(menus&&menus.length<=0)
			return;
		for(var i =  menus.length-1;i>=0; i--)
		{
			var menu = menus[i];
			//存在父节点显示到页面上并删除数组上的元素 
			if(menu.parent==-1)
			{
				addParentMenu(menu,$("#menu"));
				menus.splice(i,1);
			}
			//父亲添加完 添加儿子
			if($("#menu-"+menu.parent).length>0)
			{
				//
				if($("#menu-ul-"+menu.parent).length == 0)
				{
					$("#menu-"+menu.parent).append($("<dd><ul id='menu-ul-"+menu.parent+"'></ul></dd>"));					
				}
				$("#menu-ul-"+menu.parent).append($('<li  id="menu-li'+menu.id+'" data-url="'+menu.url+'"  data-parent="'+menu.parent+'" data-id="'+menu.id+'" data-title="'+menu.name+'" ></li>'));
				if(isParent(menu.id,menus))
				{
					$("#menu-li"+menu.id).attr("isparent",true);
					addParentMenu(menu,$("#menu-li"+menu.id));
				}else
				{
					$("#menu-li"+menu.id).append('<a href="javascript:void(0)">'+menu.name+'</a>');
				}
				menus.splice(i,1);
			}else
			{
				menus.push(menus.splice(i,1)[0]);
			}
		}
		//继续向页面添加未添加的
		loadMenu(menus);
	};
	
	function isParent(id,menus)
	{
		var isparent = false;
		for(var i = 0;i<menus.length;i++)
		{
			if(menus[i].parent==id)
			{
				isparent = true;
				break;
			}
		}
		return isparent;
	}
	function addParentMenu(menu,parent)
	{
		parent.append('<dl id="menu-'+menu.id+'"><dt><i class="iconfont"></i><text  id="menu-li'+menu.id+'" data-title="'+menu.name+'">'+menu.name+'</text><i class="iconfont menu_dropdown-arrow">&#xe6d5;</i></dt></dl>');
		
		$("#menu-"+menu.id+" dt").click(function()
		{
			$(this).parent().find("dt").toggleClass("select");
			$(this).parent().find("dd").toggle();
		});
	}
	function registerMenuEvent()
	{
		$("#menu ul li").click(showView);
	}
	
	function showView()
	{
		var $menu = $(this);
		if($menu.attr("isparent"))
			return;
		var menu_id = $menu.attr("data-id"); 
		var $page = $("#page_"+menu_id);
		if($page.length>0)
		{
			$page.trigger("click");
			return;
		}
		var url ="<%=basePath%>"+$menu .attr("data-url");
		var $viewManager = $("#min_title_list");
		var $iframeManager = $("#iframe_box");
		$viewManager.find("li").each(function()
		{
			$(this).removeClass("active");
		});
		$iframeManager.children().hide();
		//增加一个标签
		$viewManager.append($('<li id="page_'+menu_id+'" class="active"><span title="'+
				$menu.attr("data-title")+'" data-href="'+url+'">'
				+$menu.attr("data-title")+'</span><i id="close_page_'+menu_id+'"></i><em></em></li>'));
		//只能加一个view
		$iframeManager.append( addTitle(menu_id));
		
		var $iframdiv = $('<div id="frame_page_'+menu_id+'" style="height:'+$("#iframe_box").height()+';" class="col-xs-12 framePage">'
				+'</div>');
		var  $iframe = $('<iframe  id="iframe_'+menu_id+'" scrolling="yes" frameborder="0" src="'+url+'"></iframe>');
		
	/* 	$iframe.load(function() {  
			debugger;
		    var iframeHeight=$(this).contents().height(); 
		    
		    $(this.contentDocument.body).find("body").height(iframeHeight);
		});  */
		$iframdiv.append($iframe);
		$iframeManager.append($iframdiv);
		/* addTitle(menu_id); */
		//定义激活 事件
		$("#page_"+menu_id).click(function(){
			activeMyself(this);
		});
		//定义关闭页签时间
		$("#close_page_"+menu_id).click(function()
		{
			closeNavBar(this);	
		});
	}
	function addTitle(menuId)
	{
		 /* $("#iframe_"+menuId).load(function(){
			 var title = '<i class="iconfont"></i> ';
			 var $nav=  $('<nav class="breadcrumb"></nav>');
			 title += getTitle(menuId);	
			 $nav.append(title);
			 $(document.getElementById(this.id).contentWindow.document.body).prepend($nav);
		    }); */ 
		 var title = '<i class="iconfont"></i> ';
		 var $nav=  $('<nav id="nav_page_'+menuId+'" class="breadcrumb"></nav>');
		 title += getTitle(menuId);	
		 $nav.append(title);
		 return $nav;
	}
	function getTitle(menuId)
	{
		var parentId = $("#menu-li"+menuId).attr("data-parent");
		var title=$("#menu-li"+menuId).attr("data-title");
		if(parentId)
		{
			title  = getTitle(parentId)+'<span class="c-gray en">&gt;</span>'+title;
		}
		return  title;
	}
	//关闭选项卡
	function closeNavBar(element)
	{
		var  navBar = $(element).parent();
		$("#frame_"+navBar.attr("id")).remove();
		var $showli  = $(element).parent().prev();
		if($showli.length==0){
			$showli= $(element).parent().next();
		}
		$showli.trigger("click");
		navBar.remove();
	}
	//激活自己
	function activeMyself(element){
		var $iframeManager = $("#iframe_box");
		var  menuid  = $(element).attr("id");
		//隐藏其他页，显示点击页
		$iframeManager.children().each(function()
		{
			if(this.id == "frame_"+menuid||this.id=="nav_"+menuid)
			{
				$(this).show();
			}
			else
			{
				$(this).hide();
			}
		});
		var $viewManager = $("#min_title_list");
		$viewManager.children().each(function()
		{
			if(this.id == element.id)
			{
				(!$(this).hasClass('active'))&&$(this).addClass("active");
			}
			else
			{
				$(this).removeClass("active");
			}
		});
	}
</script>
</head>
<body  style="height: 100%;">
	<header>
		<div style="width: 100%;height: 45px;"> 
			<div class="logo">
				<a class="navbar-userbar" href="javascript:;">公告管理系统</a> 
				<span>v1.0</span> 
				<a class="navbar-userbar" href="javascript:;">&#xe667;</a>
			</div>
			<nav id="Hui-userbar" class="col-md-10">
				<div class="dropdown logo pull-right">
				  <a  class="navbar-userbar" id="dLabel" data-target="#" href="javascript:;" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				    ${ user.userName} 
				    <span class="caret"></span>
				  </a>
				
				  <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
							<li><a class="navbar-userbar" href="<%=basePath%>/loginOut.action">退出</a></li>
				  </ul>
				</div>
			</nav>
		</div>
	</header>
<!-- 菜单 -->
<article id="frameBody">
	<aside class="aside col-xs-2 col-sm-2 col-md-2">
		<input runat="server" id="divScrollValue" type="hidden" value="" />
		<div id="menu" class="menu_dropdown">
		</div>
	</aside>
	<div class=""><a class="" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
	<section  id="frameSection" class=" article-box col-xs-10 col-sm-10 col-md-10 pull-right">
		<div id="tabNav" class="tabNav">
			<div class="tabNav-wp">
				<ul id="min_title_list" class="acrossTab">
					<li class="active" onclick="activeMyself(this)"  id="page_0" data-href="welcome.jsp"><span title="公告" data-href="welcome.html">公告</span><!-- <i id="close_page_0"></i> --><em></em></li>
				</ul>
				<!-- 
				<ul id="min_title_list"  class="nav nav-tabs" role="tablist">
				  <li role="presentation" class="active"><a href="#home" role="tab" data-toggle="tab">Home</a></li>
				  <li role="presentation"><a href="#profile" role="tab" data-toggle="tab">Profile</a></li>
				  <li role="presentation"><a href="#messages" role="tab" data-toggle="tab">Messages</a></li>
				  <li role="presentation"><a href="#settings" role="tab" data-toggle="tab">Settings</a></li>
				</ul> -->
				
			</div>
			<div class=""><a id="js-tabNav-prev" class="" href="javascript:;"><i class="">&#xe6d4;</i></a><a id="js-tabNav-next" class="" href="javascript:;"><i class="">&#xe6d7;</i></a></div>
		</div>
		<div id="iframe_box">
			<div id="frame_page_0">
				<div style="display:none" class=""></div>
				<iframe id="iframe_0" scrolling="yes" frameborder="0" src="welcome.jsp"></iframe>
			</div>
		</div>
	<!-- 	<div id="iframe_box" class="tab-content">
		  <div role="tabpanel" class="tab-pane active" id="home">
		  	<iframe scrolling="yes" frameborder="0" src="#"></iframe>
		  </div>
		  <div role="tabpanel" class="tab-pane" id="profile">...</div>
		  <div role="tabpanel" class="tab-pane" id="messages">...</div>
		  <div role="tabpanel" class="tab-pane" id="settings">...</div>
		</div> -->
	</section>
</article>
</body>
</html>