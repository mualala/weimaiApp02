/*配置动态审核报表*/
function initActiveTable(){
    $("#activeTable").bootstrapTable("destroy");//先销毁表格
    
    //初始化表格，动态从服务器加载数据
    $("#activeTable").bootstrapTable({
        method: "post",
        url: 'activeReport.do',
        height: "424",
        striped: false,//不显示斑马线
        clickToSelect: true,//点击行即可选中单选/复选框
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        pagination: true,//启动分页
        pageSize: 5,//每页显示的记录数
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
        //singleSelect:true,
        toolbar: '#toolbar', 
        sidePagination: "server",//服务器端请求
        
        columns: [{
            field: 'state',
            checkbox: true,
            width: 30
        },{
            field: 'active_user_id',
            title: 'ID',
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
        	title: '资源',
        	width: '200',
        	formatter: function(value,row,index){
        		var resultDocum = "";
        		console.log(value);
        		if(value.length>0){
        			for(var i=0;i<value.length;i++){
        				console.log(value[i]);
        				var start = value[i].lastIndexOf("/")+1;
        				var end = value[i].length;
        				console.log("start="+start+",end="+end);
    					resultDocum = resultDocum+'<a href="'+value[i]+'">'+value[i].replace("__","").substring(start,end)+'</a><br/>';
        			}
        		}
        		return resultDocum;
        	}
        },{
            field: 'c_time',
            title: '创建时间',
            width: 120
        },{
        	field: 'operate',
            title: '操作',
            align: 'center',
            width: 90,
            valign: 'middle',
            formatter: operateFormatter,//表格中增加按钮
            events: operateEvents//给按钮注册事件
        }],
        
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
            alert("加载数据失败,请联系系统管理员", {time : 1500, icon : 2});
        }
    });
};
//表格中增加按钮
function operateFormatter(value, row, index) {//这三个参数是bootsharp-table默认的
	return [
	        
	        '<button type="button" class="pass btn btn-info  btn-xs" style="margin-right:15px;"><span class="glyphicon glyphicon-ok"></span>通&nbsp;&nbsp;&nbsp;过</button>',
	        '<button type="button" class="nono btn btn-danger  btn-xs" style="margin-right:15px;"><span class="glyphicon glyphicon-remove"></span>不通过</button>'
	        ].join('');
};
//注册按钮的点击事件
window.operateEvents = {
	'click .pass': function(e, value, row, index){
		//console.log(value);
		//console.log(row.active_user_id);
		var info = {
				active_user_id:row.active_user_id
		};
		$.ajax({
			url:"oneVerify.do",
			type:"post",
			data:info,//请求参数
			dataType:"",
			success:function(data){
				alert(data.msg);
			},
			error:function(){
				alert("审核失败,请联系系统管理员");
			}
		});
	},
	'click .nono': function(e, value, row, index){
		var info = {
				active_user_id:row.active_user_id
		};
		$.ajax({
			url:"oneNoVerify.do",
			type:"post",
			data:info,//请求参数
			dataType:"",
			success:function(data){
				alert(data.msg);
			},
			error:function(){
				alert("审核失败,请联系系统管理员");
			}
		});
	}
};
//批量审核
$("#batchActive").click(function(){
	var rows = $("#activeTable").bootstrapTable('getAllSelections');
	var rowLen = rows.length;
	var ids = "";
	if(rowLen<1){
		alert("请选择动态审核的数据");
	}else{
		for(var i=0;i<rowLen;i++){
			var active_id = rows[i].active_user_id;
			ids = ids+active_id+",";
		}
		$.ajax({
			url:"batchActiveVerify.do",
			type:"post",
			data:{param:ids},
			dataType:"",
			success:function(data){
				alert(data.msg);
			},
			error:function(){
				alert("批量审核失败,请联系系统管理员");
			}
		});
	}
});
$("#activeTable").bootstrapTable('hideColumn','user_id');
//下载动态中的资源文件
function downLoadDocum(obj){
	var docName = $(obj).html();//获取资源名称
	console.log(docName);
	$.ajax({
		url:docName+"/toDownDocum.do",
		type:"post",
		data:'',//请求参数
		dataType:"",
		success:function(){},
		error:function(){
			alert("下载资源文件失败,请联系系统管理员");
		}
	});
};