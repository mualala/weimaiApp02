/**
 * active
 * 	.verify
 * 	.theme categore
 * 	.active report
 */
var active = {
		themeToLocal: new Array(),//将主题大类的分类保存到本地,用于二级分类的下拉选择
		
		//js的array去重
		arrayRemoVal: function(array){
			var resultArray = [];
			for(var i=0;i<array.length;i++){
				!RegExp(array[i],"g").test(resultArray.join(",")) && (resultArray.push(array[i]));
			}
			return resultArray;
		},
		
		//js时间戳转日期
		formatDate: function(_timestamp){
			try{
				var _date = new Date(_timestamp);
				var year = _date.getFullYear();
				var month = _date.getMonth()+1;
				var day = _date.getDate();
				var hour = _date.getHours();
				var minute = _date.getMinutes();
				var second = _date.getSeconds();
				return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
			}catch(e){}
		},
		
		verify: {
			/*配置动态审核报表*/
			initActiveVerifyTable: function(){
			    $("#activeTable").bootstrapTable("destroy");//先销毁表格
			    //初始化表格，动态从服务器加载数据
			    $("#activeTable").bootstrapTable({
			        method: "post",
			        url: 'admin/activeReport.do',
			        height: "350",
			        striped: false,//不显示斑马线
			        clickToSelect: true,//点击行即可选中单选/复选框
			        dataType: "json",
			        contentType: "application/x-www-form-urlencoded",
			        pagination: true,//启动分页
			        pageSize: 10,//每页显示的记录数
			        pageNumber: 1,//当前第几页
			        pageList: [10,20,30,50,100,500,1000],//记录数可选列表
			        search: true,//是否启用查询,是客户端client才有效
			        searchOnEnterKey: true,//按回车触发搜索方法，否则自动触发搜索方法
			        showColumns: true,//显示下拉框勾选要显示的列
			        showExport: true,//是否显示导出
			        exportDataType: "basic",
			        showRefresh: true,//显示刷新按钮
			        silent: true,//刷新事件必须设置
			        strictSearch: true,//全匹配搜索，否则为模糊搜索
			        showToggle: true,//显示 切换试图（table/card）按钮
			        //singleSelect:true,
			        toolbar: '#activeVerifyTool', 
			        sidePagination: "server",//服务器端请求
			        
			        columns: [{
			            field: 'state',
			            checkbox: true,
			            width: 30
			        },{
			            field: 'active_user_id',
			            title: 'ID',
			            sortable: true,
			            width: 50
			        },{
			            field: 'user_nickname',
			            title: '昵称',
			            width: 80
			        },{
			            field: 'user_id',
			            title: 'uuid',
			            width: 80
			        },{
			            field: 'type_a',
			            title: '首页大类',
			            width: 80
			        },{
			            field: 'title',
			            title: '标题',
			            width: 80
			        },{
			            field: 'saysay',
			            title: '说说的内容',
			            width: 100
			        },{
			            field: 'pics',
			            title: '图片',
			            width: 150,
			            formatter: function(value,row,index){
			            	var resultPics = "";
			            	if(value.length>0){
			            		for(var i=0;i<value.length;i++){
			            			resultPics += '<img  src="'+value[i]+'" class="img-rounded" onclick="javascript:window.open(this.src);"></img>';
			            		}
			            	}
			            	return resultPics;
			            }
			        },{
			        	field: 'docums',
			        	title: '资源文件',
			        	width: '200',
			        	formatter: function(value,row,index){
			        		var resultDocum = "";
			        		if(value.length>0){
			        			for(var i=0;i<value.length;i++){
			        				var start = value[i].lastIndexOf("/")+1;
			        				var end = value[i].length;
			    					resultDocum = resultDocum+'<a href="'+value[i]+'">'+value[i].replace("__","").substring(start,end)+'</a><br/>';
			        			}
			        		}
			        		return resultDocum;
			        	}
			        },{
			            field: 'active_creatime',
			            title: '创建时间',
			            formatter: function(value,row,index){
			            	return active.formatDate(value);
			            },
			            sortable: true,
			            width: 120
			        },{
			        	field: 'operate',
			            title: '操作',
			            align: 'center',
			            width: 90,
			            valign: 'middle',
			            formatter: active.verify.operateFormatter,//表格中增加按钮
			            events: active.verify.operateEvents//给按钮注册事件
			        }],
			        
			        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
			        //设置为limit可以获取limit, offset, search, sort, order  
			        queryParamsType: "undefined", 
			        
			        queryParams: function queryParams(params){
			            //请求的分页参数
			            var param = {
			                    pageNumber: params.pageNumber,
			                    pageSize: params.pageSize,
			                    searchText: $(".search").children('input').val(),
			                    startDate: $("#startDate").val(),
			                    endDate: $("#endDate").val(),
			                    sortName: params.sortName,
			                    sortOrder: params.sortOrder
			            };
			            return param;
			        },
			        onLoadSuccess: function(data){//加载成功时执行
			        	//隐藏列uuid
			        	$("#activeTable").bootstrapTable("hideColumn","user_id");
			        },
			        onLoadError: function(){//加载失败时执行
			        	layer.msg('加载数据失败,请联系系统管理员'+'\r\n', {icon: 1});
			        }
			    });
			},
			//表格中增加按钮
			operateFormatter: function(value, row, index) {//这三个参数是bootsharp-table默认的
				return [
				        
				        '<button type="button" class="pass btn btn-info  btn-xs" style="margin-right:15px;"><span class="glyphicon glyphicon-ok"></span>通过</button>',
				        '<button type="button" class="nono btn btn-danger  btn-xs" style="margin-right:15px;"><span class="glyphicon glyphicon-remove"></span>不通过</button>'
				        ].join('');
			},
			//注册按钮的点击事件
			operateEvents: {
				'click .pass': function(e, value, row, index){
					//console.log(value);
					//console.log(row.active_user_id);
					var info = {
							active_user_id:row.active_user_id
					};
					$.ajax({
						url:"admin/oneVerify.do",
						type:"post",
						data:info,//请求参数
						dataType:"",
						success:function(data){
							layer.msg(data.msg+'\r\n', {icon: 1});
						},
						error:function(){
							layer.alert(
									'审核通过失败,请联系系统管理员\r\n',
									{
										title: '提示框',
										icon:0
							 		}
								);
						}
					});
				},
				'click .nono': function(e, value, row, index){
					var info = {
							active_user_id:row.active_user_id
					};
					$.ajax({
						url:"admin/oneNoVerify.do",
						type:"post",
						data:info,//请求参数
						dataType:"",
						success:function(data){
							layer.msg(data.msg+'\r\n', {icon: 1});
						},
						error:function(){
							layer.alert(
									'审核不通过失败,请联系系统管理员\r\n',
									{
										title: '提示框',
										icon:0
							 		}
								);
						}
					});
				}
			},
			//批量审核动态
			batchVerifyActive: function(){
				var rows = $("#activeTable").bootstrapTable('getAllSelections');
				var rowLen = rows.length;
				var ids = "";
				if(rowLen<1){
					layer.alert(
							'请至少选择1条数据\r\n',
							{
								title: '提示框',
								icon:0
					 		}
						);
				}else{
					for(var i=0;i<rowLen;i++){
						var active_id = rows[i].active_user_id;
						ids = ids+active_id+",";
					}
					$.ajax({
						url:"admin/batchActiveVerify.do",
						type:"post",
						data:{param:ids},
						dataType:"",
						success:function(data){
							layer.msg(data.msg+'\r\n', {icon: 1});
						},
						error:function(){
							layer.alert(
									'批量审核失败,请联系系统管理员\r\n',
									{
										title: '提示框',
										icon:0
							 		}
								);
						}
					});
				}
			},
			//下载动态中的资源文件
			downLoadDocum: function(){
				var docName = $(obj).html();//获取资源名称
				console.log(docName);
				$.ajax({
					url:docName+"/toDownDocum.do",
					type:"post",
					data:'',//请求参数
					dataType:"",
					success:function(){},
					error:function(){
						layer.msg("下载资源文件失败,请联系系统管理员"+'\r\n', {icon: 1});
					}
				});
			}
		},
		
		themeCategReport: {
			/*主题分类管理的报表*/
			initActiveVerifyTable: function(){
				//模态框可拖动
				$(document).on("show.bs.modal", ".modal", function(){
				    $(this).draggable({
					   handle: ".modal-header"   //只能点击头部拖动
				    });
				    $(this).css("overflow", "hidden"); //防止出现滚动条，出现的话，会把滚动条一起拖着走
				});
				
				$("#themeCategTable").bootstrapTable("destroy");//先销毁表格
			    //初始化表格，动态从服务器加载数据
			    $("#themeCategTable").bootstrapTable({
			        method: "post",
			        url: 'admin/verifies.do',
			        height: "480",
			        striped: false,//不显示斑马线
			        clickToSelect: true,//点击行即可选中单选/复选框
			        dataType: "json",
			        contentType: "application/x-www-form-urlencoded",
			        pagination: true,//启动分页
			        pageSize: 10,//每页显示的记录数
			        pageNumber: 1,//当前第几页
			        pageList: [10,20,30,50,100,500],//记录数可选列表
			        search: true,//是否启用查询,是客户端client才有效
			        searchOnEnterKey: true,//按回车触发搜索方法，否则自动触发搜索方法
			        showColumns: true,//显示下拉框勾选要显示的列
			        showExport: true,//是否显示导出
			        exportDataType: "basic",
			        showRefresh: true,//显示刷新按钮
			        silent: true,//刷新事件必须设置
			        strictSearch: true,//全匹配搜索，否则为模糊搜索
			        showToggle: true,//显示 切换试图（table/card）按钮
			        toolbar: '#themeCategTool', 
			        sidePagination: "server",//服务器端请求
			        
			        columns: [{
			            field: 'state',
			            checkbox: true,
			            width: 30
			        },{
			            field: 'class_active',
			            title: '主题类别',
			            width: 140
			        },{
			            field: 'theme_pic',
			            title: '主题分类UI',
			            formatter: function(value,row,index){
			            	var resultPics = "";
			            	if(value != null){
			            		resultPics += '<img  src="'+value+'" class="img-rounded" onclick="javascript:window.open(this.src);"></img>';
			            	}else {
			            		resultPics = '无';
							}
			            	return resultPics;
			            },
			            width: 140
			        },{
			            field: 'two_class',
			            title: '二级分类',
			            width: 140
			        },{
			            field: 'two_pic',
			            title: '二级分类UI',
			            formatter: function(value,row,index){
			            	var resultPics = "";
			            	if(value != null){
			            		resultPics += '<img  src="'+value+'" class="img-rounded" onclick="javascript:window.open(this.src);"></img>';
			            	}else {
			            		resultPics = '无';
							}
			            	return resultPics;
			            },
			            width: 80
			        },{
			            field: 'stu_verify',
			            title: '学生证验证权限(编辑)',
			            editable: {
			            	type: 'select',//编辑的类型
			            	source: [{value:0,text:'0'},{value:1,text:'1'}],
			            },
			            width: 140
			        },{
			            field: 'certi_verify',
			            title: '毕业证验证权限(编辑)',
			            editable: {
			            	type: 'select',//编辑的类型
			            	source: [{value:0,text:'0'},{value:1,text:'1'}],
			            },
			            width: 140
			        },{
			            field: 'act_creatime',
			            title: '创建时间',
			            formatter: function(value,row,index){
			            	return active.formatDate(value);
			            },
			            sortable: true,
			            width: 140
			        }],
			        onEditableSave: function(field, row, oldValue, $el){//编辑数据的回调函数
			        	console.log(field);
			        	var info = {
			        			class_active: row.class_active,
			        			verify: field
			        	};
			        	if(field=="stu_verify"){
			        		info.state = row.stu_verify;
			        	}else if(field=="certi_verify"){
			        		info.state = row.certi_verify;
			        	}
			        	$.ajax({
			        		type: "post",
			        		url: "admin/modifyVerify.do",
			        		data: info,
			        		success: function(data){
			        			layer.msg(data.msg+'\r\n', {icon: 0});
			        		},
			        		error: function(){
			        			layer.alert(
										'修改权限失败,请联系系统管理员\r\n',
										{
											title: '提示框',
											icon:0
								 		}
									);
			        		}
			        	});
			        },
			        
			        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
			        //设置为limit可以获取limit, offset, search, sort, order  
			        queryParamsType: "undefined", 
			        
			        queryParams: function queryParams(params){
			            //请求的分页参数
			            var param = {
			                    pageNumber: params.pageNumber,
			                    pageSize: params.pageSize,
			                    searchText: $(".search").children('input').val(),
			                    sortName: params.sortName,
			                    sortOrder: params.sortOrder
			            };
			            return param;
			        },
			        onLoadSuccess: function(){//加载成功时执行
//			        	$("#themeCategTable").bootstrapTable("mergeCells",{index:1,field:'class_active',rowspan:2,colspan:1});
			        	//保存分类到本地变量active.themeToLocal
			        	var tableRows = $("#themeCategTable").bootstrapTable("getOptions").data;
			        	active.themeToLocal.splice(0, active.themeToLocal.length);//先清空array
			        	console.log(tableRows);
			        	for(var i=0;i<tableRows.length;i++){
			        		active.themeToLocal.push(tableRows[i].class_active);
			        	}
			        	//array去重
			        	active.themeToLocal = active.arrayRemoVal(active.themeToLocal);
			        	
			        },
			        onLoadError: function(){//加载失败时执行
			        	layer.msg('加载数据失败\r\n', {icon: 0});
			        }
			    });
			},
			
			//批量删除主题
			batchDeleteThemeCateg: function(){
				var rows = $("#themeCategTable").bootstrapTable('getAllSelections');
				var rowLen = rows.length;
				var ids = "";
				if(rowLen<1){
					layer.alert(
							'请至少选择1条数据\r\n',
							{
								title: '提示框',
								icon:0
					 		}
						);
				}else{
					for(var i=0;i<rowLen;i++){
						var id = rows[i].act_verify_id;
						ids = ids+id+",";
					}
					console.log(ids);
					$.ajax({
						url:"admin/batchDelThemeCateg.do",
						type:"post",
						data:{param:ids},
						dataType:"",
						success:function(data){
							layer.msg(data.msg+'\r\n', {icon: 1});
						},
						error:function(){
							layer.alert(
									'批量删除失败,请联系系统管理员\r\n',
									{
										title: '提示框',
										icon:0
							 		}
								);
						}
					});
				}
			},
			
			//添加主题类型
			addThemeCateg: {
				//弹出添加的模态框
				addThemeModal: function(){
					//清空上传图片中的值
    				var pic = $('#themePic');
    				pic.after(pic.clone().val(''));
    				pic.remove();
    				//清空控件中的值
    				$("#themeCateg").val('');
					$("#addThemeCateg").modal("toggle");
				},
				//提交添加的主题类型
				submitAddTheme: function(){
					var themeCategVal = $("#themeCateg").val();
					var themePicVal = document.getElementById("themePic").files[0];
					if(themeCategVal == ''){
						layer.alert(
								'请添加主题大类\r\n',
								{
									title: '提示框',
									icon:0
						 		}
							);
					}else if(themePicVal==null || themePicVal==undefined){
						layer.alert(
								'请添加UI图标\r\n',
								{
									title: '提示框',
									icon:0
						 		}
							);
					}else {
						var formData = new FormData();
						formData.append("themeCategVal",themeCategVal+'#');
						formData.append("themePicVal",themePicVal);
						$.ajax({
							url:'admin/attachThemeCateg.do',
							type:'post',
							data:formData,
							processData: false,
		            		contentType: false,
		            		success:function(data){
		            			if(data.status){
		            				layer.msg(data.msg+'\r\n', {icon: 0});
		            				//清空上传图片中的值
		            				var pic = $('#themePic');
		            				pic.after(pic.clone().val(''));
		            				pic.remove();
		            				//清空控件中的值
		            				$("#themeCateg").val('');
		            			}
		            		},
		            		error:function(){
		            			layer.alert(
										'添加主题分类失败,请联系系统管理员\r\n',
										{
											title: '提示框',
											icon:0
								 		}
									);
		            		}
						});
					}
				}
			},
			
			//添加二级动态类别分类
			addTwoCateg: {
				//弹出添加的模态框
				addTwoModal: function(){
					//清空上传图片中的值
    				var twoPic = $('#twoPic');
    				twoPic.after(twoPic.clone().val(''));
    				twoPic.remove();
    				//清空控件中的值
    				$("#twoCateg").val('');
    				
					$("#addTwoCateg").modal("toggle");
					//加载主题大类的列表
					$("#selecThemeCateg").empty();
					for(var i=0;i<active.themeToLocal.length;i++){
						$("#selecThemeCateg").append("<option>"+active.themeToLocal[i]+"</option>");
					}
				},
				//提交添加的二级分类
				submitTwoCateg: function(){
					var themeCategVal = $("#selecThemeCateg option:selected").val();
					var twoCategVal = $("#twoCateg").val();
					var twoPicVal = document.getElementById("twoPic").files[0];
					console.log(themeCategVal);
					console.log(twoCategVal);
					console.log(twoPicVal);
					if(twoCategVal == ''){
						layer.alert(
								'请添加二级分类\r\n',
								{
									title: '提示框',
									icon:0
						 		}
							);
					}else if (twoPicVal==null || twoPicVal==undefined) {
						layer.alert(
								'请添加二级分类UI图标\r\n',
								{
									title: '提示框',
									icon:0
						 		}
							);
					}else {
						var formData = new FormData();
						formData.append("themeCategVal",themeCategVal);
						formData.append("twoCategVal",twoCategVal);
						formData.append("twoPicVal",twoPicVal);
						$.ajax({
							url:'admin/attachActiveTwoCateg.do',
							data:formData,
							type:'post',
							processData: false,
		            		contentType: false,
		            		success:function(data){
		            			layer.msg(data.msg+'\r\n', {icon: 0});
		            			//清空上传图片中的值
	            				var twoPic = $('#twoPic');
	            				twoPic.after(twoPic.clone().val(''));
	            				twoPic.remove();
	            				//清空控件中的值
	            				$("#twoCateg").val('');
		            		},
		            		error:function(){
		            			layer.alert(
										'添加二级分类失败,请联系系统管理员\r\n',
										{
											title: '提示框',
											icon:0
								 		}
									);
		            		}
						});
					}
				}
			}
		},
		
		activeReport: {
			/*主题分类的汇总统计报表*/
			initActiveCategRepoet: function(){
				$("#activeReportTable").bootstrapTable("destroy");//先销毁表格
			    //初始化表格，动态从服务器加载数据
			    $("#activeReportTable").bootstrapTable({
			        method: "post",
			        url: 'admin/activeCategReport.do',
			        height: "355",
			        striped: false,//不显示斑马线
			        clickToSelect: true,//点击行即可选中单选/复选框
			        dataType: "json",
			        contentType: "application/x-www-form-urlencoded",
			        pagination: true,//启动分页
			        pageSize: 10,//每页显示的记录数
			        pageNumber: 1,//当前第几页
			        pageList: [10,20,30,50,100,500],//记录数可选列表
			        //search: true,//是否启用查询,是客户端client才有效
			        searchOnEnterKey: true,//按回车触发搜索方法，否则自动触发搜索方法
			        showColumns: true,//显示下拉框勾选要显示的列
			        showExport: true,//是否显示导出
			        exportDataType: "basic",
			        showRefresh: true,//显示刷新按钮
			        silent: true,//刷新事件必须设置
			        strictSearch: true,//全匹配搜索，否则为模糊搜索
			        showToggle: true,//显示 切换试图（table/card）按钮
			        //toolbar: '#themeCategTool', 
			        sidePagination: "server",//服务器端请求
			        
			        columns: [{
			            field: 'themeType',
			            title: '主题类别',
			            width: 140
			        },{
			            field: 'twoType',
			            title: '二级分类',
			            width: 140
			        },{
			            field: 'activeCount',
			            title: '动态数量',
			            width: 80
			        }],
			        
			        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
			        //设置为limit可以获取limit, offset, search, sort, order  
			        queryParamsType: "undefined", 
			        
			        queryParams: function queryParams(params){
			            //请求的分页参数
			            var param = {
			                    pageNumber: params.pageNumber,
			                    pageSize: params.pageSize,
			                    startDate: $("#startDate").val(),
			                    endDate: $("#endDate").val()
			            };
			            return param;
			        },
			        onLoadSuccess: function(){//加载成功时执行
			        },
			        onLoadError: function(){//加载失败时执行
			        	layer.msg('加载数据失败\r\n', {icon: 0});
			        }
			    });
			}
		}
		
}