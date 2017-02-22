
var adminLog = {
		/*配置动态审核报表*/
		report: function initAdminLogTable(){
		    $("#adminLog").bootstrapTable("destroy");//先销毁表格
		    
		    //初始化表格，动态从服务器加载数据
		    $("#adminLog").bootstrapTable({
		        method: "post",
		        url: 'admin/adminLogReport.do',
		        height: "350",
		        striped: false,//不显示斑马线
		        clickToSelect: true,//点击行即可选中单选/复选框
		        dataType: "json",
		        contentType: "application/x-www-form-urlencoded",
		        pagination: true,//启动分页
		        pageSize: 5,//每页显示的记录数
		        pageNumber: 1,//当前第几页
		        pageList: [10,20,30,50,100,150,500],//记录数可选列表
		        searchOnEnterKey: true,//按回车触发搜索方法，否则自动触发搜索方法
		        showColumns: true,//显示下拉框勾选要显示的列
		        showExport: true,//是否显示导出
		        exportDataType: "basic",
		        showRefresh: true,//显示刷新按钮
		        silent: true,//刷新事件必须设置
		        //strictSearch: true,//全匹配搜索，否则为模糊搜索
		        showToggle: true,//显示 切换试图（table/card）按钮
		        //singleSelect:true,
		        locale: "zh-CN",//中文支持
		        toolbar: '#adminLogtool', 
		        sidePagination: "server",//服务器端请求
		        
		        columns: [{
		            field: 'select',
		            checkbox: true,
		            width: 30
		        },{
		            field: 'id',
		            title: 'ID',
		            width: 50
		        },{
		            field: 'admin_id',
		            title: '登录用户名',
		            width: 50
		        },{
		            field: 'login_ip',
		            title: '登录的IP',
		            width: 50
		        },{
		            field: 'browser_info',
		            title: '浏览器信息',
		            width: 80
		        },{
		            field: 'login_time',
		            title: '登录时间',
		            sortable: true,
		            formatter: function(value,row,index){
		            	return adminLog.formatDate(value);
		            },
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
		                    dateVal: $("#date").val(),
		                    adminId: $("#admin_id").val(),
		                    sortName:params.sortName,
		                    sortOrder:params.sortOrder
		            };
		            return param;
		        },
		        onLoadSuccess: function(){//加载成功时执行
		        	//隐藏列uuid
		        	$("#adminLog").bootstrapTable("hideColumn","id");
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
		batchDelLog: function(){
			var rows = $("#adminLog").bootstrapTable('getAllSelections');
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
					var id = rows[i].id;
					ids = ids+id+",";
				}
				$.ajax({
					url:"admin/batchDelLog.do",
					type:"post",
					data:{param:ids},
					dataType:"",
					success:function(data){
						layer.alert(
								data.msg+'\r\n',
								{
									title: '提示框',
									icon:1
						 		}
							);
					},
					error:function(){
						layer.alert(
								data.msg+'批量删除失败,请联系系统管理员\r\n',
								{
									title: '提示框',
									icon:0
						 		}
							);
					}
				});
			}
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
		}
}
