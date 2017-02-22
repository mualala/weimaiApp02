
var home = {
		//格式化chart中日期选择框某年月的所有day
		getMonthDays: function(ym){
			var year = ym.split('-')[0];
			var month = ym.split('-')[1];
			var monthStartDate = new Date(year, month-1, 1);
            var monthEndDate = new Date(year,month, 1);
            var days = (monthEndDate - monthStartDate)/(1000 * 60 * 60 * 24);
            var dateArray = new Array();
            for(var i=0;i<days;i++){
            	dateArray.push(i+1+'号');
            }
            return dateArray;
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
				return year+"年"+month+"月"+day+"日"+hour+"时"+minute+"分"+second+"秒";
			}catch(e){}
		},
		
		myChart: '',//定义一个chart空对象容器
		
		//加载新增用户统计图
		chartOption: function(){
			/*获取chart筛选框的值&其他全网统计数*/
			$.ajax({
				url:'admin/homeSelectorVal.do',
				type:'post',
				data:'',
				dataType:'json',
				success:function(data){
					$('#selectDate').empty();
					var nowDate = new Date();
					var nowYear = nowDate.getFullYear();
					var nowMonth = nowDate.getMonth()+1;
					if(nowMonth<10){
						nowMonth = '0'+nowMonth;
					}
					var nowYMStr = nowYear+'-'+nowMonth;
					for(var i=0;i<data.data.length;i++){
						if(nowYMStr == (data.data)[i]){
							$('#selectDate').append("<option selected='selected'>"+(data.data)[i]+"</option>");
						}else {
							$('#selectDate').append("<option>"+(data.data)[i]+"</option>");
						}
					}
					//添加全网数据
					$("#allNetPerson").text(data.allNetPeople);
					$("#allNetActive").text(data.allNetActive);
					$("#hint").html(',你本次登陆时间为 '+home.formatDate(data.hint)+'，登陆IP: '+data.loginIP);
				},
				error:function(){
					layer.alert('图标数据加载请求失败,请联系体统管理员\r\n', {icon:0});
				}
			});
			
			/*初始化并配置chart*/
			require.config({
			    paths: {
			        echarts: './assets/dist'
			    }
			});
			require(
				    [
				        'echarts',
				        'echarts/theme/macarons',
				        'echarts/chart/line', // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
				        'echarts/chart/bar'
				    ],
				    function(ec, theme) {
				    	home.myChart = ec.init(document.getElementById('main'), theme);
				        option = {
				            title: {
				                text: '每日新增用户'
				            },
				            tooltip: {
				                trigger: 'axis'
				            },
				            legend: {
				                data: []
				            },
				            toolbox: {
				                show: true,
				                feature: {
				                    mark: { show: true },
				                    dataView: { show: true, readOnly: false },
				                    magicType: { show: true, type: ['line', 'bar'] },
				                    restore: { show: true },
				                    saveAsImage: { show: true }
				                }
				            },
				            calculable: true,
				            xAxis: [{
				                type: 'category',
				                data: []
				            }],
				            yAxis: [{
				                type: 'value'
				            }],
				            series: []
				        };
				        home.myChart.setOption(option,true);//取消默认的merge项
				        home.dynamicLoadMyChart();
				    }
				);
		},
		
		//根据筛选参数动态加载myChart
		dynamicLoadMyChart: function(){
			var param = {
					selectDate: $("#selectDate option:selected").val()
			};
			$.ajax({
				url:'admin/homeChartData.do',
				type:'post',
				data:param,
				dataType:'json',
				success:function(data){
					var option = home.myChart.getOption();
					//横坐标值
					option.xAxis[0].data = home.getMonthDays(param.selectDate);
					//
					option.series[0] = {
							type: 'bar',
							data: data.series,
							markPoint: {
								data: [{ type: 'max', name: '最大值' },{ type: 'min', name: '最小值' }]
							},
							markLine: {
								data: [{type: 'average',name: '平均值'}]
							}
					};
					/*
					for(var i=0;i<data.length;i++){
						concole.log(option.series[i]);
						option.series[i].data = data.series[i];
					}
					*/
					home.myChart.setOption(option,true);//取消默认的merge项
				},
				error:function(){
					layer.msg('图标数据加载请求失败,请联系体统管理员\r\n', {icon: 1});
				}
			});
		},
		
		//月份选中时重新加载chart
		reLoadChartOnSelected: function(){
			$("#selectDate").change(function(){
				home.dynamicLoadMyChart();
			});
		}
		
		
}