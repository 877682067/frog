/**
 * 手机端分页
 * 包含属性 当前页pageNo,每页显示数量pageNum 
 */
/*jQuery.extend(
{ 
	
});  */

;(function( $, window, document, undefined ) {
	
	var cirPage = function(ele,options) 
    {
		this.$element = ele,
		this.data = [],//缓存数据 
		this.defaults ={
	    		pageNo:0,
	    		pageNum:20,
	    		url:"",
	    		param:{},
	    		columns:[{
	    			field:'',
	    			name:'',
	    			format:null
	    		}]//field 字段名称 format 格式化  name 列名
	    	};
		options  = options?options:this.defaults;
		(!options.pageNo)&&(options.pageNo = this.defaults.pageNo);
		(!options.pageNum)&&(options.pageNum = this.defaults.pageNum);
		this.option = $.extend({},options );
		$(ele).CirPage._getData(this);
    };
    cirPage.prototype = {
    		 /**
    	     * 下一页
    	     * @returns
    	     */
    	    nextPage : function()
    		{
    	    	console.info(this);
    	    	/*console.info(this.option.pageNo);*/
    			this.option.pageNo++;
    			this.$element.CirPage._nextData(this);
    		},
    		/**
    		 * 获取当前页
    		 * @returns
    		 */
    		getPageNo : function()
    		{
    			return  this.option.pageNo;
    		},
    		/**
    		 * 设置每页数量
    		 * @param pageNum
    		 */
    		setPageNum : function(pageNum)
    		{
    			this.option.pageNum = pageNum;
    		},
    		getOption : function()
    		{
    			return this.option;
    		},
    		setOption : function(options)
    		{
    			this.option  = options;
    		},
    		refresh:function()
    		{
    			this.$element.html('');
    			this.option.pageNo=0;
    			this.$element.CirPage._getData(this);
    		},
    		getRowData:function(index)
    		{
    			return this.data?this.data[index]:{};
    		},
    		setParam:function(obj)
    		{
    			this.option.param = obj;
    		}
    };
    var cirpageApi=
    {
				 _resetPageInfo:function(cirpg)
				 {
					 cirpg.option.pageNo = 0;
				 },
				  _createTable : function(data,cirpg)
				    {
				    	this._createHead(cirpg);
				    	this._createBody(cirpg,data);
				    	this.registerEvent(cirpg);
				    },
				registerEvent:function(cirpg)
				{
					this._registerTdEvent(cirpg);
					//注册TR 事件待处理  _registerTrEvent(cirpg);
					
				},
				_registerTdEvent:function(cirpg)
				{
					var columns  = cirpg.option.columns;
					for(var i =0;i< columns.length;i++)
					{
						var column =columns[i]; 
						if( typeof(column.click)== 'function')
						{
							$("#"+cirpg.$element.attr("id")+" tbody tr td[data-field='"+column.field+"']").click(function()
							{
								var data = cirpg.data[$(this).parent().index()];
								column.click(data);
							});
						}
					}
				},
				_createHead : function(cirpg)
				    {
				    	 var $thead =$("<thead></thead>");
				    	 var $tr = $("<tr></tr>");
				    	var columns = cirpg.option.columns;
				    	for(var i = 0; i<columns.length; i++)
						{
				    		$tr.append("<th>"+columns[i].name+"</th>");
						}
				    	$thead.append($tr);
				    	cirpg.$element.append($thead);
				    },
				_createBody : function(cirpg,data)
				    {
				    	var $tbody = $("<tbody></tbody>");
				    	for(var i  = 0;i<data.length; i++)
						{
				    		var info = data[i];
				    		$tbody.append(this._createInfo(info,cirpg,i));
						}
				    	cirpg.$element.append($tbody);
				    },
				_createInfo : function(info,cirpg,index)
				    {
				    	
				    	var $tr= $("<tr></tr>");
					for(var i=0;  i < cirpg.option.columns.length; i++)
					{
						var  cloumn =cirpg.option.columns[i];
						var field = info[cloumn.field];
						if(cloumn.format)
							field = cloumn.format(info[cloumn.field],index,info);
						var $td = $('<td data-field="'+cloumn.field+'"></td>');
						$td.html(field);
						$tr.append($td);
					}
					return $tr;
				},
				_nextData:function(cirpg)
				{
					var  apiObj = this;
					var suncess = function(data)
									{
										var  index = cirpg.$element.find("tbody tr:last").index()+1;
										for(var i  = 0;i<data.length; i++,index++)
										{
								    		var info = data[i];
								    		cirpg.$element.find("tbody").append(apiObj._createInfo(info,cirpg,index));
										}
									};
						this.ajaxGetData(cirpg,suncess);
				},
				ajaxGetData:function(cirpg,sucess)
				{
					var op;
					if(typeof(cirpg.option.param)=='function')
					{
						op = cirpg.option.param();
					}
					else
					{
						op = cirpg.option.param;
					}
					op  = op?op:{};
					op.pageNo =cirpg.option.pageNo;
					op.pageNum = cirpg.option.pageNum;
					op.min =op.pageNo*op.pageNum;
					op.max =op.min+op.pageNum;
					$.ajax({
			    		url:this._getRootPath()+cirpg.option.url,
			    		type:"post",
			    		data:op,
			    		dataType:"json",
			    		success:function(data)
			    		{
			    			sucess(data);
			    		}
			    	});
				},
				_getData : function (cirpg)
					    {
							var suncess = function(data)
											{
												if(data instanceof Array)
												{
								    				for(var i = 0;i<data.length;i++)
							    					{
								    					cirpg.data.push(data[i]);
							    					}
							    				}
								    			/*cirpg.data.push(data);*/
								    			cirpg.$element.CirPage._createTable(data,cirpg);
											};
							this.ajaxGetData(cirpg,suncess);
					    },
				_getRootPath:function() 
						{
							var pathName = window.location.pathname.substring(1);
							var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
							if (webName == "") 
							{
								return window.location.protocol + '//' + window.location.host;
							}
							else 
							{
								return window.location.protocol + '//' + window.location.host + '/' + webName;
							}
						}
	};
    $.fn.CirPage = cirpageApi;
	$.fn.cirPage  = function ( opts ) {
		 return  new cirPage(this, opts);
	};
	
})(jQuery, window, document);;
