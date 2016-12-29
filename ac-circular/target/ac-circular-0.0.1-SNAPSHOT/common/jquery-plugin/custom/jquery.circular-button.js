jQuery.del = function (url,_getSelectRowFun,_refreshFun,validfun)
	{
		if(validfun)
			validfun();
		var data = _getSelectRowFun();
		if((!data)||data.length<=0)
		{
			$.alert("请选择需要删除的数据！");
			return;
		}
		var ids =[];
		for(var i  = 0;  i<data.length; i++)
		{	
			ids.push(data[i].id);
		}
		$.ajax({
			url:url,
			data:{
				ids:ids.join(',')
			},
			type:"post",
			success:function ()
			{
				_refreshFun();
			}
		});
	};
jQuery.add = function(url,id)
	{
		location.href=id?url+"?id="+id:url;
	};
jQuery.update = function(url,_getSelectRowFun)
	{
		debugger;
		var data = _getSelectRowFun();
		if((!data)||data.length!=1)
		{
			$.alert("请选择一条需要修改的数据！");
			return;
		}
		$.add(url,data[0].id);	
	};
