
var banner = {
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
		
		initBannerReport: function(){
			//模态框可拖动
			$(document).on("show.bs.modal", ".modal", function(){
			    $(this).draggable({
				   handle: ".modal-header"   //只能点击头部拖动
			    });
			    $(this).css("overflow", "hidden"); //防止出现滚动条，出现的话，会把滚动条一起拖着走
			});
			
			$("#bannerTable").bootstrapTable("destroy");//先销毁表格
		    //初始化表格，动态从服务器加载数据
		    $("#bannerTable").bootstrapTable({
		        method: "post",
		        url: 'admin/bannerReport.do',
		        height: "480",
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
		        //strictSearch: true,//全匹配搜索，否则为模糊搜索
		        showToggle: true,//显示 切换试图（table/card）按钮
		        toolbar: '#bannerTool', 
		        sidePagination: "server",//服务器端请求
		        
		        columns: [{
		            field: 'state',
		            checkbox: true,
		            width: 30
		        },{
		            field: 'ban_id',
		            title: 'banner图的位置',
		            sortable: true,
		            width: 50
		        },{
		            field: 'title',
		            title: '标题',
		            width: 50
		        },{
		            field: 'ban_pic',
		            title: 'banner图',
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
		            field: 'ban_url',
		            title: '第三方路径',
		            width: 50
		        },{
		            field: 'total_see',
		            title: '跳转点击量',
		            width: 50
		        },{
		            field: 'ban_creatime',
		            title: '创建时间',
		            sortable: true,
		            formatter: function(value,row,index){
		            	var time = "";
		            	if(value != null){
		            		time = banner.formatDate(value);
		            	}
		            	return time;
		            },
		            width: 50
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
		                    sortName: params.sortName,
		                    sortOrder: params.sortOrder
		            };
		            return param;
		        },
		        onLoadSuccess: function(){//加载成功时执行
		        },
		        onLoadError: function(){//加载失败时执行
		        	layer.alert(
							'加载数据失败,请联系系统管理员\r\n',
							{
								title: '提示框',
								icon:0
					 		}
						);
		        }
		    });
		},
		
		//批量删除
		batchDelBanner: function(){
			var rows = $("#bannerTable").bootstrapTable('getAllSelections');
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
					var active_id = rows[i].ban_id;
					ids = ids+active_id+",";
				}
				$.ajax({
					url:"admin/batchDelBannerPic.do",
					type:"post",
					data:{param:ids},
					dataType:"",
					success:function(data){
						layer.msg(data.msg+'\r\n', {icon: 1});
					},
					error:function(){
						layer.alert(
								'删除banner失败,请联系系统管理员\r\n',
								{
									title: '提示框',
									icon:0
						 		}
							);
					}
				});
			}
		},
		
		//添加banner图
		addBannerPic: {
			addBannerPicModal:function(){
				$.ajax({//添加图片id的select项
					url:"admin/bannerIndex.do",
					type:"post",
					data:'',
					dataType:"",
					success:function(data){
						$("#selectPicId").empty();
						if(data.data == true){
							$("#selectPicId").append("<option>"+1+"</option>");
						}else {
							for(var i=0;i<data.data.length;i++){
								$("#selectPicId").append("<option>"+(data.data)[i]+"</option>");
							}
						}
					},
					error:function(){}
				});
				//添加弹出层
				$("#addBannerPic").modal("toggle");
			},
			
			submitAddBannerPic: function(){
				var selectPicIdVal = $("#selectPicId option:selected").val();
				var titleVal = $("#title").val();
				var bannerPicVal = document.getElementById("bannerPic").files[0];
				var thredURLVal = $("#thredURL").val();
				if(bannerPicVal==null || bannerPicVal==undefined){
					layer.alert(
							'添加的banner图为空,请重新添加\r\n',
							{
								title: '提示框',
								icon:0
					 		}
						);
				}else {
					var formData = new FormData();
					formData.append("selectPicIdVal",selectPicIdVal);
					formData.append("titleVal",titleVal);
					formData.append("bannerPicVal",bannerPicVal);
					formData.append("thredURLVal",thredURLVal);
					$.ajax({
						url:"admin/updateBannerPic.do",
						type:"post",
						data:formData,
						processData: false,
	            		contentType: false,
						success:function(data){
							layer.msg(data.msg+'\r\n', {icon: 1});
							//清空上传图片中的值
            				var pic = $('#bannerPic');
            				pic.after(pic.clone().val(''));
            				pic.remove();
            				//清空控件中的值
            				$("#title").val("");
							$("#thredURL").val("");
						},
						error:function(){
							layer.alert(
									'添加banner图失败,请联系系统管理员\r\n',
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
		
}