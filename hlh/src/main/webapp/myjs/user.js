/*用户相关的交互*/
var user = {
	submitUserInfo: function(){
		console.log($('#label').val());
		var userInfo = {
			token: util.getSession('token'),
			nickname: $('#nickname').val(),
			age: $('#age').val(),
			gender: $('input:radio:checked').val(),
			mobile: $('#mobile').val(),
			homeland: $('#homeland').val(),
			job: $('#job').val(),
			label: $('#label').val()
		};
		console.log(userInfo);
		$.ajax({
			url: 'user/updateUser.do',
			data: userInfo,
			type: 'post',
			dataType: 'json',
			success: function(data){
				alert(data.msg);
			},
			error: function(data){alert(data.msg);}
		});
	},
	
	showUserInfo: function(){
		console.log($('#nickname').text());
		console.log($('#luntan_lv').text());
		console.log($('#f_count').text());
		console.log($('#jifen').text());
		console.log($('#label').text());
		if(util.getSession('token')){
			$.ajax({
				url: 'user/showUserInfo.do',
				data: {token: util.getSession('token')},
				type: 'post',
				dataType: 'json',
				success: function(data){
					console.log(data);
					if(data.status){
						if(data.data.profile == null || data.data.profile == undefined){
							$('#profile').attr('src','img/list-alerts-The-picture.png');
						}else {
							$('#profile').attr('src',data.data.profile);
						}
						$('#nickname').text(data.data.nickname);
						if(data.data.gender == '男'){
							$('#gender').css({
								'background': 'url(img/list-alertsi-boy.png) no-repeat center center'
							});
						}else if (data.data.gender == '女') {
							$('#gender').css({
								'background':'url(img/list-alertsi-girl.png) no-repeat center center'
							});
						}else {
							$('#gender').css({
								'background':'url(img/list-alerts-The-picture.png) no-repeat center center'
							});
						}
						$('#luntan_lv').text('LV.'+data.data.level);
						$('#f_count').text('28');
						$('#jifen').text('123');
						$('#label').text(data.data.label);
					}
				},
				error: function(data){alert(data.msg);}
			});
		}else {}
	},
	
	addAddr_jump: function(){//跳转登录页和添加收货地址页
		if(util.getSession('token')){
			location.href = 'new_adress.html';
		}else {
			alert('请您先登录');
			location.href = 'login.html';
		}
	},
	
	//收货地址
	shoppingAddress: {
		submitShoppingAddress: function(){//添加收货地址
			var addressInfo = {
				token: util.getSession('token'),
				name: $('#name').val(),
				phone: $('#phone').val(),
				area: $('#area').val(),
				address: $('#address').val()
			};
			$.ajax({
				url: 'user/attachShoppingAddress.do',
				data: addressInfo,
				type: 'post',
				dataType: 'json',
				success:function(data){
					alert(data.msg);
					if(data.status){//跳转
						location.href = 'guanli_adress.html';
					}
				},
				error:function(data){
					alert('添加收货地址失败');
				}
			});
		},
		
		showPaginShoppingAddress: {//分页展示收货地址
			showPaginationData: function(){
	        	console.log($(".swiper-slide-active").length);
        		$(".swiper-slide-active").eq(0).append(util.getSession('addr_pagin_data'));
	        },
	        reqPaginationData: function(pageSize, pageNumber){
	        	var addr_params = {
	        			token: util.getSession('token'),
		    			pageSize: pageSize,
		    			pageNumber: pageNumber,
		    		};//属性值：pageSize,pageNumber,type,lt_type,area
	        	//请求得到分页数据
	            $.ajax({
					url: 'user/showAddrPagination.do',
					data: addr_params,
					type: 'post',
					async: false,
					dataType: 'json',
					success: function(data){
						console.log(data);
						var slide = '<div class="swiper-slide"></div>';
						var paginAllData = '';
						var ul = '<ul id="OA_task_1" class="mui-table-view" style="margin-top: 45px;">';
						if(data.rows.length>0){
							for(var i=0;i<data.rows.length;i++){
								//var paginData = '<ul id="OA_task_1" class="mui-table-view" style="margin-top: 45px;">';
								var paginData ='<li class="mui-table-view-cell">';
								paginData += '<div class="mui-slider-right mui-disabled">';
								paginData += '<a class="mui-btn mui-btn-red">删除</a>';
								paginData += '</div>';
								paginData += '<div class="mui-slider-handle" style="font-size: 1.3rem">';
								paginData += '<div class="container" style="padding: 0">';
								paginData += '<div class="col-xs-6">'+data.rows[i].name +'</div>';
								paginData += '<div class="col-xs-6" style="text-align: right">'+data.rows[i].phone+'</div>';
								paginData += '<div class="col-xs-12">地址：'+data.rows[i].area+' '+data.rows[i].address+'</div>';
								paginData += '</div></div></li>';
								ul += paginData;
								//paginAllData += paginData;
							}
							ul += '</ul>';
							paginAllData += ul;
							util.setSession('addr_pagin_data', paginAllData);
							//向下添加slide
							if(Math.ceil(data.total/pageSize)>($("#addr_swiper-wrapper").children().length)){
								$("#addr_swiper-wrapper").append(slide);
							}
						}
					},
					error: function(){
						alert('收货地址列表加载失败');
					}
				});
	        },
		}
	},
	
	
	
}