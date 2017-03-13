/*店铺相关的交互*/
var shop = {
	//选中店铺分类
	selectType: function(){
		$(".s_xuanzhong").click(function(){
	      	  $(this).addClass("selected").siblings().removeClass("selected");
	      	  $(this).css("background","rgba(40,96,144,0.8) !important").siblings().css("background","#E4E4E4 !important");
	      	  
	      	  var typeVal = "";
	      	  var types = $("#shop_type").children().siblings();
	      	  for(var i=0;i<types.length;i++){
		  			var selected = $(types[i]).hasClass("selected");
		  			if(selected){
		  				typeVal = $(types[i]).find("div").text();
		  			}
	      	  }
	      	  util.setSession("shop_type", typeVal);
		});
	},
	
	//提交创建
	submitCreateShop: function(){
		var token = util.getSession('token');
		var shop_name = $('#shop_name').val();
		var shop_type = util.getSession('shop_type');
		var files = document.getElementById("shop_pic").files;
		var shop_describe = $('#shop_describe').val();
		var shop_address = $('#shop_address').val();
		var contacts_name = $('#contacts_name').val();
		var contacts_phone = $('#contacts_phone').val();
		if(shop_name== undefined || shop_name=='' || shop_name==null){
			alert('店铺名称为空');
		}else if(shop_type=='' || shop_type==undefined || shop_type==null){
			alert('未选择店铺分类');
		}else if (files.length>1) {
			alert('仅能上传一张图片');
		}else if (shop_describe.length>150) {
			alert("店铺描述不能超过150个字");
		}
		else {
			var formData = new FormData();
			formData.append('token',token);
			formData.append('shop_name',shop_name);
			formData.append('shop_type',shop_type);
			formData.append('shop_profile',files[0]);
			formData.append('shop_describe',shop_describe);
			formData.append('shop_address',shop_address);
			formData.append('contacts_name',contacts_name);
			formData.append('contacts_phone',contacts_phone);
			$.ajax({
				url:'shop/createShop.do',
				type:'post',
				data:formData,
				dataType:'json',
				processData: false,
	    		contentType: false,
	    		success:function(data){
	    			alert(data.msg);
	    			//清空数据
	    			$('#shop_name').val('');
	    			util.delSession('shop_type');
	    			$('#shop_describe').val('');
	    			$('#shop_address').val('');
	    			$('#contacts_name').val('');
	    			$('#contacts_phone').val('');
	    		},
	    		error:function(data){
	    			alert(data.msg);
	    		}
			});
		}
	},
	
	//店铺展示相关的交互
	showShop: {
		//加载分类
		showShopType: function(){
			$.ajax({
				url:'shop/showShopType.do',
				type:'post',
				data:'',
				async: false,
				dataType:'json',
	    		success:function(data){
	    			console.log(data.data);
	    			if(data.data!=null || data.data!=undefined || data.data.length>0){
	    				var li = '';
	    				for(var i=0;i<data.data.length;i++){
	    					li += '<li class="c_xuanze">'+data.data[i]+'</li>';
	    				}
	    				$('#shop_type').append(li);
	    			}
	    		},
	    		error:function(){
	    			alert('店铺的分类加载失败');
	    		}
			});
		},
		
		//
		selectShowType: function(){
			$(".c_xuanze").click(function(_this){
		      	  $(this).addClass("selected").siblings().removeClass("selected");
		      	  $(this).css({'background':'#308EE3','color':'#fff'}).siblings().css({'background':'#F1F1F1','color':'#838383'});
		      	  
		      	  var shopTypeVal = "";
		      	  var types = $("#shop_type").children().siblings();
		      	  for(var i=0;i<types.length;i++){
			  			var selected = $(types[i]).hasClass("selected");
			  			console.log(i+'='+selected+',val='+$(types[i]).text());
			  			if(selected){
			  				console.log('aaa='+$(types[i]).text());
			  				shopTypeVal = $(types[i]).text();
			  			}
		      	  }
		      	  util.setSession("shop_type", shopTypeVal);
		      	  location.reload(true);
		        });
		},
		
		//分页展示店铺信息
		showPaginShopInfo: {
			showPaginationData: function(){
        		$(".swiper-slide-active").eq(0).append(util.getSession('shop_pagin_data'));
	        },
	        reqPaginationData: function(pageSize, pageNumber){
	        	var shop_params = {
	        			shopType: util.getSession('shop_type'),
		    			pageSize: pageSize,
		    			pageNumber: pageNumber,
		    		};
	        	console.log(shop_params);
	            $.ajax({
					url: 'shop/showShopPaginData.do',
					data: shop_params,
					type: 'post',
					async: false,
					dataType: 'json',
					success: function(data){
						console.log(data);
						var slide = '<div class="swiper-slide"></div>';
						var paginAllData = '';
						if(data.rows.length>0){
							for(var i=0;i<data.rows.length;i++){
								var paginData ='<div class="row c_dianpu">';
								paginData += '<p class="col-xs-4">';
								paginData += '<img src="'+data.rows[i].shop_profile+'" alt=""/>';
								paginData += '</p>';
								paginData += '<div class="c_content_text col-xs-8">';
								paginData += '<p>';
								paginData += '<span class="">'+data.rows[i].shop_describe+'</span>';
								paginData += '<a href="tel:'+data.rows[i].contacts_phone+'"><i class="c_phone"></i></a>';
								paginData += '</p>';
								paginData += '<span class="c_huanjing">环境优美，提供没费wifi</span>';
								paginData += '</div></div>';
								paginAllData += paginData;
							}
							util.setSession('shop_pagin_data', paginAllData);
							//向下添加slide
							if(Math.ceil(data.total/pageSize)>($("#shop_swiper-wrapper").children().length)){
								$("#shop_swiper-wrapper").append(slide);
							}
						}
					},
					error: function(){
						alert('店铺展示失败');
					}
				});
	        },
	        
	        
	        
		}
	},
	
	
	
}