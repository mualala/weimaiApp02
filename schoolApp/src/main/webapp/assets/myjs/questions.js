var pengpeng = {
		/*配置动态审核报表*/
		initQuestionTable: function(){
			//模态框可拖动
			$(document).on("show.bs.modal", ".modal", function(){
			    $(this).draggable({
				   handle: ".modal-header"   //只能点击头部拖动
			    });
			    $(this).css("overflow", "hidden"); //防止出现滚动条，出现的话，会把滚动条一起拖着走
			});
			
		    $("#questionTable").bootstrapTable("destroy");//先销毁表格
		    //初始化表格，动态从服务器加载数据
		    $("#questionTable").bootstrapTable({
		        method: "post",
		        url: 'admin/questionsManager.do',
		        height: "355",
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
		        toolbar: '#ppQuestionTool', 
		        sidePagination: "server",//服务器端请求
		        
		        columns: [{
		            field: 'select',
		            checkbox: true,
		            width: 30
		        },{
		            field: 'ques_id',
		            title: 'ID',
		            sortable: true,
		            width: 80
		        },{
		        	field: 'question',
		        	title: '问题',
		        	width: 80
		        },{
		        	field: 'strQues_creatime',
		        	title: '创建时间',
		        	sortable: true,
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
		                    searchText: $(".search").children('input').val(),
		                    startDate: $("#startDate").val(),
		                    endDate: $("#endDate").val(),
		                    sortName: params.sortName,
		                    sortOrder: params.sortOrder
		            };
		            return param;
		        },
		        onLoadSuccess: function(){//加载成功时执行
		        },
		        onLoadError: function(){//加载失败时执行
		        	layer.alert(
							'问题列表加载数据失败,请联系系统管理员\r\n',
							{
								title: '提示框',
								icon:0
					 		}
						);
		        }
		    });
		},
		
		//提交添加的问题
		addQuestion: {
			//弹出添加问题的模态框
			addQuestionModal: function(){
				$("#addQuestionModal").modal("toggle");
			},
			//移除问题的内容
			detachQuestionContent: function(){
				$("#questionArea").val("");
			},
			//提交添加的问题
			submitQuestion: function(){
				var info = {
						question: $("#questionArea").val()
					};
					if(info.question==null || info.question.trim()==""){
						layer.alert(
								'您发布的问题为空,请重新输入后发布\r\n',
								{
									title: '提示框',
									icon:0
						 		}
							);
						$("#questionArea").val("");
					}else{
						$.ajax({
							url: "admin/addQuestion.do",
							data: info,
							type: "post",
							success: function(data){
								layer.msg(data.msg+'\r\n', {icon: 1});
								$("#questionArea").val("");
							},
							error: function(){
								layer.alert(
										'发布问题失败,请联系系统管理员\r\n',
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
		
		//批量删除问题
		batchDeleteQuestion: function(){
			var rows = $("#questionTable").bootstrapTable('getAllSelections');
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
					var id = rows[i].ques_id;
					ids = ids+id+",";
				}
				console.log(ids);
				$.ajax({
					url:"admin/batchDelQuestion.do",
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
		}
		
		
}
