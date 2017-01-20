/*配置动态审核报表*/
function initQuestionTable(){
    $("#questionTable").bootstrapTable("destroy");//先销毁表格
    
    //初始化表格，动态从服务器加载数据
    $("#questionTable").bootstrapTable({
        method: "post",
        url: 'questionsManager.do',
        height: "300",
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
        //toolbar: '#toolbar03', 
        sidePagination: "server",//服务器端请求
        
        columns: [{
            field: 'ques_id',
            title: 'ID',
            width: 80
        },{
        	field: 'question',
        	title: '问题',
        	width: 80
        },{
        	field: 'strQues_creatime',
        	title: '创建时间',
        	width: 80
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
            alert("加载数据失败", {time : 1500, icon : 2});
        }
    });
};
//添加问题按钮
$("#subquestion").click(function(){
	var info = {
		question: $("#questionArea").val()
	};
	if(info.question==null || info.question.trim()==""){
		alert("您发布的问题为空,请输入后重新发布。");
		$("#questionArea").val("");
	}else{
		$.ajax({
			url: "addQuestion.do",
			data: info,
			type: "post",
			success: function(data){
				alert(data.msg);
				$("#questionArea").val("");
			},
			error: function(){
				alert("发布问题失败,请联系系统管理员");
			}
		});
	}
});
