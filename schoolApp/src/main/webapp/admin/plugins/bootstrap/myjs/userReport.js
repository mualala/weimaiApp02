/*配置动态审核报表*/
function initUserReport(){
    $("#userReportTable").bootstrapTable("destroy");//先销毁表格
    
    //初始化表格，动态从服务器加载数据
    $("#userReportTable").bootstrapTable({
        method: "post",
        url: 'userReport.do',
        height: "440",
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
            field: 'schoolId',
            title: 'ID'
        },{
            field: 'user_nickname',
            title: '昵称'
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
        	field: 'school',
        	title: '大学'
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
            title: '是否是认证用户'
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
            title: '用户等级'
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
            title: '用户的创建时间'
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