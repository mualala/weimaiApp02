/*配置发动态权限审核*/
function initActiveVertify(){
    $("#activeVertify").bootstrapTable("destroy");//先销毁表格
    
    //初始化表格，动态从服务器加载数据
    $("#activeVertify").bootstrapTable({
        method: "post",
        url: 'verifies.do',
        height: "370",
        striped: false,//不显示斑马线
        clickToSelect: true,//点击行即可选中单选/复选框
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        pagination: true,//启动分页
        pageSize: 10,//每页显示的记录数
        pageNumber: 1,//当前第几页
        pageList: [5,10,15,20,25,30,50,100,150],//记录数可选列表
        search: true,//是否启用查询,是客户端client才有效
        searchOnEnterKey: true,//按回车触发搜索方法，否则自动触发搜索方法
        showColumns: true,//显示下拉框勾选要显示的列
        showExport: true,//是否显示导出
        exportDataType: "basic",
        showRefresh: true,//显示刷新按钮
        silent: true,//刷新事件必须设置
        strictSearch: true,//全匹配搜索，否则为模糊搜索
        showToggle: true,//显示 切换试图（table/card）按钮
        toolbar: '#toolbar02', 
        sidePagination: "server",//服务器端请求
        
        columns: [{
            field: 'class_active',
            title: '分类动态',
            width: 140
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
        }
        /*
        ,{
        	field: 'operate',
            title: '操作',
            align: 'center',
            width: 90,
            valign: 'middle',
            formatter: operateFormatter02,//表格中增加按钮
            events: operateEvents//给按钮注册事件
        }*/
        ],
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
        		url: "modifyVerify.do",
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
        	console.log(info);
        },
        
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
        //设置为limit可以获取limit, offset, search, sort, order  
        queryParamsType: "undefined", 
        
        queryParams: function queryParams(params){
            //请求的分页参数
            var param = {
                    pageNumber: params.pageNumber,
                    pageSize: params.pageSize
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
};
/*
//表格中增加按钮
function operateFormatter02(value, row, index) {//这三个参数是bootsharp-table默认的
	return [
	        
	        '<button type="button" class="stuVertify btn btn-info  btn-xs" style="margin-right:15px;"><span class="glyphicon glyphicon-pencil"></span>学生验证权限</button>',
	        '<button type="button" class="certiVertify btn btn-primary  btn-xs" style="margin-right:15px;"><span class="glyphicon glyphicon-pencil"></span>毕业证权限</button>'
	        ].join('');
};
//注册按钮的点击事件
window.operateEvents = {
	'click .stuVertify': function(e, value, row, index){
		$("#stuModal").modal("toggle");
		var info = {
				active_user_id:row.active_user_id
		};
		$.ajax({
			url:"",
			type:"post",
			data:info,//请求参数
			dataType:"",
			success:function(data){
				alert(data.msg);
			},
			error:function(){
				alert("审核失败！！");
			}
		});
	},
	'click .certiVertify': function(e, value, row, index){
		var info = {
				active_user_id:row.active_user_id
		};
		$.ajax({
			url:"···",
			type:"post",
			data:info,//请求参数
			dataType:"",
			success:function(data){
				alert(data.msg);
			},
			error:function(){
				alert("审核失败！！");
			}
		});
	}
};
*/