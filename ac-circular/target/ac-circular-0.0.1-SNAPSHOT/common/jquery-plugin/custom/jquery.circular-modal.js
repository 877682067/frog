/**
 * 模态框生成类
 * 参数 option  {
 * 		id  
 *		title 标题 
 * 		url  获取页面的路径 
 *  	param 参数
 *   	type 类型  alert  提示框，confirm 选择框 modal模态
 *   	html  窗体内容
 *   	ensure  确定回调方法名
 *   	cancel 取消回调方法名
 * }
 * 
 * 		
 * @param _options
 * @returns
 */
jQuery.cirforms  = function cirforms(_options){
	this.id = _options.id;
	this.title = _options.title;
	this.url = _options.url;
	this.body = _options.body;
	var _attr  =  _options;
	_attr.header = true,
	_attr.footer =  true,
	_attr.body =  true;
	if(_attr.type =='alert')
	{
		_attr.footer =false;
		if(!_attr.title)
		{
			_attr.title = '提示';
		}
	}
	this.open = function()
	{
		$("#"+_attr.id+"Modal").modal('show');
	};
	this.close = function()
	{
		$("#"+_attr.id+"Modal").modal('hide');
		if(_attr.type =='alert'||_attr.type =='confirm')
		{
			$("#"+_attr.id+"Modal").remove();
		}
		$("#"+_attr.id+"ModalClose").unbind("click");
		$("#"+_attr.id+"ModalEnsure").unbind("click");
	};
	
	createModal = function()
	{
		createHeader =function ()
		{
			var _closeFun ='onclick = "javascript:';
			if(_attr.cancel)
			{
				_closeFun +="var  _iscontinue=";
				_closeFun += _attr.cancel+'();';
				_closeFun +="if(_iscontinue!=false)";
			}
			if(_attr.type =='alert'||_attr.type =='confirm')
			{
				_closeFun +='$(\'#'+_attr.id+'Modal\').remove();"';	
			}
			else
			{
				_closeFun +='$(\'#'+_attr.id+'Modal\').modal(\'hide\');"';
			}
			return '<div class="modal-header">'
							+'<button type="button" class="close" '+_closeFun+' aria-hidden="true">'
							+	'&times;'
							+'</button>'
							+'<h4 class="modal-title" id="myModalLabel">'+_attr.title
							+'</h4>'
					+'</div>';	
		};
		createFooter =function()
		{
			var _closeFun ,_ensuerFun=_cancelFun=' onclick = "javascript:';
			
				if(_attr.type =='alert'||_attr.type =='confirm')
				{
					_closeFun ='$(\'#'+_attr.id+'Modal\').remove();"';	
				}
				else
				{
					_closeFun ='$(\'#'+_attr.id+'Modal\').modal(\'hide\');"';
				}
			
				if(_attr.ensure)
				{

					_ensuerFun +="var  _iscontinue=";
					_ensuerFun += _attr.ensure+'();';
					_ensuerFun +="if(_iscontinue!=false)";
				}
				if(_attr.cancel)
				{
					_cancelFun +="var  _iscontinue=";
					_cancelFun += _attr.cancel+'();';
					_cancelFun +="if(_iscontinue!=false)";
				}
				_ensuerFun +=_closeFun;
				_cancelFun +=_closeFun;
			var _foot = '<div class="modal-footer">'
				+'	<button id="'+_attr.id+'ModalEnsure" type="button" class="btn btn-default"'+_ensuerFun+' >确定'
				+'	</button>'
					+'	<button id="'+_attr.id+'ModalClose" type="button" class="btn btn-success"'
					+_cancelFun 
					+'>'
					+'		关闭'
					+'	</button>'
					+'</div>';
			return  _foot;
		};
		createBody = function()
		{
			var _width =" ",_height=" ";
			if(_attr.width){
				_width="width :"+_attr.width+";";
			}
			if(_attr.height){
				_height="height :"+_attr.height+";";
			}
			var  body  = '<div class="modal-body" style="'+_width+_height+' overflow: auto;">';
			body+=getBodyHTML();
			body+='	</div>';
			return body;
		};
		getBodyHTML=function()
		{
			var _bodyHtml;
			if(_attr.url)
			{
				$.ajax({
					url:_attr.url,
					params:_attr.params,
					type:"post",
					async:false,
					success:function(data)
					{
						_bodyHtml  = data;
					}
				});
			}
			if(_attr.html)
			{
				_bodyHtml = _attr.html;
			}
			return _bodyHtml;
		};
		var html='<div class="modal fade" id="'+_attr.id+'Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">';
		html+=	'<div class="modal-dialog">';
		html+=	'<div class="modal-content">';		
		if(_attr.header)
		{
			html+=createHeader();
		}
		if(_attr.body)
		{
			html+=createBody();
		}
		if(_attr.footer)
		{
			html+=createFooter();
		}
		html+=	'</div>';
		html+=	'</div>';
		html+='</div>';
		return  html;
	};
	/*registerButtonClick=function()
	{
			$("#"+_attr.id+"ModalClose").click(function(){
				
				$('#'+_attr.id+'Modal').modal('hide');
				if(_attr.cancel)
					_attr.cancel($('#'+_attr.id+'Modal').find("body"));
			});
			$("#"+_attr.id+"ModalEnsure").click(function(){
				
				$('#'+_attr.id+'Modal').modal('hide');
				if(_attr.ensure)
				_attr.ensure($('#'+_attr.id+'Modal').find("body"));
			});
	}*/
	if($("#"+_attr.id+"Modal").length==0)
	{
		$("body").append($(createModal()));
		/*registerButtonClick();*/
	}
	return this;
};

jQuery.alert = function(body,title) {
	var _options  =  {
		id:'_alert',
		html:body,
		type:'alert',
		title:title
	};
	var  cirforms = $.cirforms(_options);
	cirforms.open();
};
/**
 *    	ensure  确定回调方法名（String）
 *   	cancel 取消回调方法名（String）
 */
jQuery.confirm = function(title,body,ensure,cancel) {
	var _options  =  {
			id:'_confirm',
			html:body,
			type:'alert',
			ensure:ensure,
			cancel:cancel
		};
	var  cirforms = $.cirforms(_options);
	cirforms.open();
};
/**
 * 
 * @param id
 * @param title
 * @param url
 * @param params
 * @param width
 * @param height
 * @param callBack  确定回调方法名
 * @returns {jQuery.modal}
 */
jQuery.modal= function(id,title,url,params,width,height,callBack,cancel) {
	
	var _options={
			id:id,
			title:title,
			url:url,
			callback:params,
			width:width,
			height:height,
			ensure:callBack,
			cancel:cancel
	};
	var  cirforms;
	cirforms = $.cirforms(_options);
	this.close = cirforms.close(); 
	this.open = cirforms.open(); 
	return this;
};
