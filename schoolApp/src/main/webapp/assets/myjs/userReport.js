
var user = {
		/*配置用户报表*/
		initUserReport: function(){
		    $("#userReportTable").bootstrapTable("destroy");//先销毁表格
		    
		    //初始化表格，动态从服务器加载数据
		    $("#userReportTable").bootstrapTable({
		        method: "post",
		        url: 'admin/userReport.do',
		        height: "450",
		        striped: false,//不显示斑马线
		        clickToSelect: true,//点击行即可选中单选/复选框
		        dataType: "json",
		        contentType: "application/x-www-form-urlencoded",
		        pagination: true,//启动分页
		        pageSize: 10,//每页显示的记录数
		        pageNumber: 1,//当前第几页
		        pageList: [10,20,30,50,100,500,1000,5000,10000],//记录数可选列表
		        search: true,//是否启用查询,是客户端client才有效
		        searchOnEnterKey: true,//按回车触发搜索方法，否则自动触发搜索方法
		        showColumns: true,//显示下拉框勾选要显示的列
		        showExport: true,//是否显示导出
		        exportDataType: "basic",
		        showRefresh: true,//显示刷新按钮
		        silent: true,//刷新事件必须设置
		        strictSearch: true,//全匹配搜索，否则为模糊搜索
		        showToggle: true,//显示 切换试图（table/card）按钮
		        toolbar: '#userBlockTool', 
		        singleSelect: true,//禁止多选
		        sidePagination: "server",//服务器端请求
		        
		        columns: [{
		            field: 'state',
		            checkbox: true,
		            width: 30
		        },{
		            field: 'schoolId',
		            title: '校园号',
		            sortable: true
		        },{
		            field: 'user_nickname',
		            title: '昵称'
		        },{
		            field: 'block_status',
		            title: '封禁状态'
		        },{
		            field: 'phoneNum',
		            title: '手机号',
		            sortable: true
		        },{
		        	field: 'profile',
		            title: '头像',
		            width:120,
		            formatter: function(value,row,index){
		            	if(value.indexOf('null')<0){
		            		return '<img src="'+value+'" class="img-rounded" alt="no pic" onclick="javascript:window.open(this.src);"></img>';
		            	}
		            }
		        },{
		        	field: 'stu_state',
		        	title: '学生证验证状态'
		        },{
		        	field: 'certi_state',
		        	title: '毕业证验证状态'
		        },{
		            field: 'stu_verify',
		            title: '学生证认证',
		            formatter: function(value,row,index){
		            	if(value.indexOf('null')<0){
		            		return '<img src="'+value+'" class="img-rounded" alt="no pic" onclick="javascript:window.open(this.src);"></img>';
		            	}
		            }
		        },{
		            field: 'certi_verify',
		            title: '毕业证认证',
		            formatter: function(value,row,index){
		            	if(value.indexOf('null')<0){
		            		return '<img src="'+value+'" class="img-rounded" alt="no pic" onclick="javascript:window.open(this.src);"></img>';
		            	}
		            }
		        },{
		            field: 'verify_state',
		            title: '是否是认证用户',
		            sortable: true
		        },{
		            field: 'gender',
		            title: '性别'
		        },{
		            field: 'star',
		            title: '星座'
		        },{
		            field: 'e_state',
		            title: '情感状态'
		        },{
		            field: 'level',
		            title: '用户等级',
		            sortable: true
		        },{
		            field: 'province',
		            title: '省份'
		        },{
		            field: 'city',
		            title: '市'
		        },{
		            field: 'county',
		            title: '县'
		        },{
		            field: 'birthday',
		            title: '出生日期'
		        },{
		            field: 'grade',
		            title: '年级'
		        },{
		            field: 'profession',
		            title: '行业'
		        },{
		            field: 'major',
		            title: '专业'
		        },{
		            field: 'school',
		            title: '大学'
		        },{
		            field: 'highschool',
		            title: '高中'
		        },{
		            field: 'lable',
		            title: '个人标签'
		        },{
		            field: 'skill',
		            title: '个人技能'
		        },{
		            field: 'reportCreaTime',
		            title: '创建时间',
	            	sortable: true
		        },{
		            field: 'reportLastTime',
		            title: '最后修改时间'
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
		                    verifyState: $("#verifyState option:selected").val(),
		                    blockState: $("#blockState option:selected").val(),
		                    school: $("#schoolName").val(),
		                    gender: $("#gender option:selected").val(),
		                    profession: $("#profession").val(),
		                    startDate: $("#startDate").val(),
		                    endDate: $("#endDate").val(),
		                    sortName: params.sortName,
		                    sortOrder: params.sortOrder, 
		                    schoolID: $("#schoolID").val()
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
		
		/*配置用户审核报表*/
		initUserVertifyTable: function initUserVertifyTable(){
		    $("#userVertify").bootstrapTable("destroy");//先销毁表格
		    
		    //初始化表格，动态从服务器加载数据
		    $("#userVertify").bootstrapTable({
		        method: "post",
		        url: 'admin/userVerifyReport.do',
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
		        toolbar: '#userVerifyTool', 
		        sidePagination: "server",//服务器端请求
		        
		        columns: [{
		            field: 'schoolId',
		            title: '校园号',
		            sortable: true,
		            width: 60
		        },{
		            field: 'user_nickname',
		            title: '昵称',
		            width: 70
		        },{
		            field: 'phoneNum',
		            title: '手机号',
		            sortable: true,
		            width: 70
		        },{
		        	field: 'city',
		        	title: '城市',
		        	width: 80
		        },{
		        	field: 'county',
		        	title: '区县',
		        	width: 80
		        },{
		        	field: 'school',
		        	title: '大学',
		        	width: 80
		        },{
		        	field: 'stu_state',
		        	title: '学生证验证状态',
		        	editable: {
		        		type: 'select',
		        		source: [{value:0,text:'0'},{value:1,text:'1'},{value:2,text:'2'}],
		        	},
		        	width: 60
		        },{
		        	field: 'certi_state',
		        	title: '毕业证验证状态',
		        	editable: {
		        		type: 'select',
		        		source: [{value:0,text:'0'},{value:1,text:'1'},{value:2,text:'2'}],
		        	},
		        	width: 60
		        },{
		            field: 'stu_verify',
		            title: '学生证',
		            width: 80,
		            formatter: function(value,row,index){
		            	if(value.indexOf('null')<0){
		            		return '<img src="'+value+'" class="img-rounded" alt="no pic" onclick="javascript:window.open(this.src);"></img>';
		            	}
		            }
		        },{
		            field: 'certi_verify',
		            title: '毕业证',
		            width: 80,
		            formatter: function(value,row,index){
		            	if(value.indexOf('null')<0){
		            		return '<img src="'+value+'" class="img-rounded" alt="no pic" onclick="javascript:window.open(this.src);"></img>';
		            	}
		            }
		        },{
		            field: 'verify_state',
		            title: '是否是认证用户',
		            sortable: true
		        },{
		            field: 'reportCreaTime',
		            title: '创建时间',
	            	sortable: true
		        },{
		            field: 'reportLastTime',
		            title: '最后修改时间',
		            width: 120
		        }],
		        onEditableSave: function(field, row, oldValue, $el){
		        	var info = {
		        			schoolId: row.schoolId,
		        			verify: field
		        	};
		        	if(field=="stu_state"){
		        		info.state = row.stu_state;
		        		info.otherState = row.certi_state;
		        	}else if(field=="certi_state"){
		        		info.state = row.certi_state;
		        		info.otherState = row.stu_state;
		        	}
		        	console.log(info);
		        	$.ajax({
		        		type: "post",
		        		url: "admin/oneUserVerify.do",
		        		data: info,
		        		success: function(data){
		        			layer.alert(
									data.msg+'\r\n',
									{
										title: '提示框',
										icon:1
							 		}
								);
		        		},
		        		error: function(){
		        			layer.alert(
									'审核用户身份失败,请联系系统管理员\r\n',
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
		                    sortOrder: params.sortOrder, 
		                    startDate: $("#startDate").val(),
		                    endDate: $("#endDate").val(),
		                    schoolID: $("#schoolID").val(),
		                    phoneNum: $("#phoneNum").val()
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
		
		/*禁用/解禁用户方法  1禁用 0解禁*/
		unOrblockUser: function(_lock){
			var rows = $("#userReportTable").bootstrapTable('getAllSelections');
			var info = {
					user_id: rows[0].user_id,
					lock: _lock
			};
			$.ajax({
				url:"admin/unOrblockUser.do",
				type:"post",
				data:info,
				dataType:"",
				success:function(data){
					layer.msg(data.msg+'\r\n', {icon: 1});
				},
				error:function(){
					layer.alert(
							'禁用用户失败,请联系系统管理员\r\n',
							{
								title: '提示框',
								icon:0
					 		}
						);
				}
			});
		},
		
		/*移除条件*/
		delCondition: function(){
            $("#verifyState option[value='s1']").attr("selected",true);
            $("#schoolName").val('');
            $("#gender option:selected").val('');
            $("#profession").val('');
            $("#startDate").val('');
            $("#endDate").val('');
            $("#schoolID").val('');
            $("#phoneNum").val('');
		}
}
