/*论坛的相关交互*/
var luntan = {
	//分类的选中
	selectType: function(){
		$(".c_xuanzhong").click(function(_this){
        	  $(this).addClass("selected").siblings().removeClass("selected");
        	  $(this).css("background","rgba(40,96,144,0.8) !important").siblings().css("background","#E4E4E4 !important");
        	  
        	  var typeVal = "";
        	  var types = $("#lt_type").children().siblings();
        	  for(var i=0;i<types.length;i++){
  	  			var selected = $(types[i]).hasClass("selected");
  	  			if(selected){
  	  				typeVal = $(types[i]).find("div").text();
  	  			}
        	  }
        	  //保存选中分类到session中保存
        	  util.setSession("type", typeVal);
        	  util.delSession('lt_pagin_data');//移除分页数据
        	  location.reload(true);//get一次,刷新页面
          });
	},
	
	//发帖
	fatie: {
		LtJump: function(){
			if(util.getSession('token')){
	   			location.href = "luntan_fatie.html";
			}else{
				alert("请您先登录");
				location.href = "login.html";
			}
		},
		TWJump: function(){//图文直播跳转
			if(util.getSession('token')){
	   			location.href = "picture_fatie.html";
			}else{
				alert("请您先登录");
				location.href = "login.html";
			}
		},
		
		//提交发帖,参数_lt_type=1 论坛发帖 2 图文直播类型
		submitTie: function(_lt_type){
			var lt_content = $("#lt_content").val();
			var files = document.getElementById("luntanPics").files;
			var type = '';
			if(_lt_type != 2){
				type = util.getSession("type");
			}
			var location = util.getSession("area");
			var user_id = util.getSession("token");
			if(type==null || type==undefined){
				alert("请选择一种分类");
			}else if (location==null || location==undefined || location=='地区') {
				alert("请选择您所在的地区");
			}else if ((files==null || files==undefined) && lt_content=='') {
				alert("发表的图片和内容不能同时为空!");
			}else if (files.length>4) {
				alert("发表的图片超过4张!");
			}else {
				var formData = new FormData();//通过FormData对象提交流和文本数据
				formData.append("lt_content",lt_content);
				formData.append("type",type);
				formData.append("lt_type",_lt_type);
				formData.append("location",location);
				formData.append("user_id",user_id);
				for(i=0;i<files.length;i++){
					formData.append("pics",files[i]);
				}
				
				$.ajax({
					url:'luntan/addCommont.do',
					type:'post',
					data:formData,
					dataType:'json',
					processData: false,
		    		contentType: false,
		    		success:function(data){
		    			if(data.status){
		    				alert(data.msg);
		    				if(_lt_type == 1){
		    					window.location.href = "luntan.html";
		    				}else if(_lt_type == 2){
		    					window.location.href = "picture_live.html";
		    				}
		    			}
		    		},
		    		error:function(data){
		    			if(data.status){
		    				alert(data.msg);
		    			}
		    		}
				});
			}
		}
	},
	
	//分页展示论坛
	paginationTie: {
		//pageSize: 3,//每页数据
		
        showPaginationData: function(lt_type){
        	//console.log($(".swiper-slide-active").length);
        	if(lt_type == 1){
        		$(".swiper-slide-active").eq(1).append(util.getSession('lt_pagin_data'));
        	}else {
        		$(".swiper-slide-active").eq(0).append(util.getSession('tw_pagin_data'));
			}
        },
        
        reqPaginationData: function(pageSize, pageNumber, lt_type){
        	var lt_params = {
	    			pageSize: pageSize,
	    			pageNumber: pageNumber,
	    			type: util.getSession('type'),
	    			lt_type: lt_type,
	    			area: util.getSession('area')
	    		};//属性值：pageSize,pageNumber,type,lt_type,area
        	console.log(lt_params);
        	//请求得到分页数据
            $.ajax({
				url: 'luntan/showPaginationLT.do',
				data: lt_params,
				type: 'post',
				async: false,
				dataType: 'json',
				success: function(data){
					//console.log(data);
					var slide = '<div class="swiper-slide"></div>';
					if(1 == lt_type){//论坛分页数据
						var paginAllData = '';
						if(data.rows.length>0){
							for(var i=0;i<data.rows.length;i++){
								var paginData ='<div class="container" id="lunta_tie" style="padding: 0; margin-top: 10px;">';
								paginData += '<div class="col-xs-8" style="padding-right: 0">';
								paginData += '<div class="">';
								paginData += '<a href="" class="btn-primary btn btn-sm">置顶</a>';
								if(data.rows[i].profile != null){
									paginData += '<img src="'+data.rows[i].profile+'" alt="" class="radios">';
								}
								paginData += '<span style="font-size: 12px">'+data.rows[i].nickname+'</span>';
								paginData += '<span class="btn btn-warning btn-sm">LV.'+(data.rows[i].level)+'</span>';	
								paginData += '</div>';
								paginData += '<a href="javascript:void(0);" onclick="luntan.seeTie.localLtInfo('+data.rows[i].lt_id+',\''+data.rows[i].user_id+'\');" style="display: inline-block">';
								paginData += '<div class="LH"><p>'+data.rows[i].lt_content+'</p></div>';
								paginData += '</a>';
								paginData += '</div>';
								paginData += '<div class="col-xs-4 pos_img">';
								if(data.rows[i].pics.length>0){
									paginData += '<a href="javascript:void(0);" onclick="luntan.seeTie.localLtInfo('+data.rows[i].lt_id+',\''+data.rows[i].user_id+'\');" style="display: inline-block"><img src="'+data.rows[i].pics[0]+'" style="width: 80px;height: 80px;" /></a>';
								}
								paginData += '<p class="opacit"></p>';
								if(data.rows[i].pics.length>0){
									paginData += '<p class="num">还有'+(data.rows[i].pics.length-1)+'张</p>';
								}else{
									paginData += '<p class="num">还有0张</p>';
								}
								paginData += '</div></div>';
								paginData += '<div class="col-sm-12" style="overflow: hidden">';
								paginData += '<a href="lingju_luntan.html" class="btn btn-primary btn-sm" style="background: #ffffff;color: #00a0e9">邻里论坛</a>';
								paginData += '<span class="time">'+util.dateDiffForLT(data.rows[i].lt_creatime)+'</span>';
								paginData += '<span class="bottom-right" style="float: right"><img src="img/home-age-comments.png" alt="" style="width: 17px;height: 17px;"><span>0</span></span>';
								paginData += '</div>';
								paginAllData += paginData;
							}
							util.setSession('lt_pagin_data', paginAllData);
							//向下添加slide
							if(Math.ceil(data.total/pageSize)>($("#lt_swiper-wrapper").children().length)){
								$("#lt_swiper-wrapper").append(slide);
							}
						}
					}else {//图文直播类型 lt_type=2
						var twPaginAllData = '';
						if(data.rows.length>0){
							for(var i=0;i<data.rows.length;i++){
								var paginData ='<div class="container" style="padding: 10px 15px;border-bottom: 1px solid #E4E4E4">';
								//左边
								paginData += '<div class="col-xs-8" style="padding-left: 0;padding-right: 0">';
								paginData += '<div class="col-xs-12" style="padding-left: 0;padding-right: 0">';
								paginData += '<a href="" class="btn btn-primary btn-sm">直播中</a>';
								if(data.rows[i].profile != null){
									paginData += '<img src="'+data.rows[i].profile+'" alt="" class="radios">';
								}
								paginData += '<span style="font-size: 12px">'+data.rows[i].nickname+'</span>';
								paginData += '<span class="btn btn-warning btn-sm">LV.'+(data.rows[i].level)+'</span>';	
								paginData += '</div>';
								paginData += '<div class="LH"><p>'+data.rows[i].lt_content+'</p></div>';
								paginData += '</div>';
								paginData += '<div class="col-xs-4 pos_img" style="padding-right: 0">';
								if(data.rows[i].pics.length>0){
									paginData += '<img src="'+data.rows[i].pics[0]+'" style="width: 80px;height: 80px;" />';
								}
								paginData += '<p class="opacit"></p>';
								if(data.rows[i].pics.length>0){
									paginData += '<p class="num">还有'+(data.rows[i].pics.length-1)+'张</p>';
								}else{
									paginData += '<p class="num">还有0张</p>';
								}
								paginData += '</div>';
								paginData += '<div class="col-xs-12" style="overflow: hidden;padding: 0">';
								paginData += '<a href="" class="btn btn-primary btn-sm" style="background: #ffffff;color: #00a0e9">直播中</a>';
								paginData += '<span class="time">'+util.dateDiffForLT(data.rows[i].lt_creatime)+'</span>';
								paginData += '<span class="bottom-right" style="float: right"><img src="img/home-age-comments.png" alt="" style="width: 17px;height: 17px;margin-top: 5px;"><span style="font-size: 12px;margin-left: 3px">0</span></span>';
								paginData += '</div></div>';
								twPaginAllData += paginData;
							}
							util.setSession('tw_pagin_data', twPaginAllData);
							//向下添加slide
							if(Math.ceil(data.total/pageSize)>($("#tw_swiper-wrapper").children().length)){
								$("#tw_swiper-wrapper").append(slide);
							}
						}
					}
				},
				error: function(){
					if(lt_type ==1){
						alert('论坛数据加载失败');
					}else {
						alert('图文直播数据加载失败');
					}
				}
			});
        },
	},
	
	seeTie: {
		//保存点击的lt_id并跳转详情页
		localLtInfo: function(lt_id, other_user_id){
			util.setSession('lt_id', lt_id);
			util.setSession('other_user_id', other_user_id);
			location.href = 'seeTie.html';
		},
		
		//帖子详情
		showTieDetail: function(){
			$.ajax({
				url: 'luntan/showDetailLT.do',
				data: {'lt_id': util.getSession('lt_id')},
				type: 'post',
				dataType: 'json',
				success: function(data){
					console.log(data);
					var LTDetailData = '<div class="row" style="margin-top: 37px;border-top:10px solid #F1F1F1;padding: 20px 0 6px">';
					LTDetailData += '<div class="container">';
					LTDetailData += '<div class="col-xs-12" style="padding-top: 15px">';
					LTDetailData += '<a href="lingju_yonghuziliao.html" style="display: inline-block"><img src="'+data.data.profile+'" alt="" class="radios"></a>';
					LTDetailData += '<span class="font" id="nick">'+data.data.nickname+'</span><a href="" class="btn-sm btn-warning btn " id="user_lv" style="margin-left: 5px">LV'+data.data.level+'</a>';
					LTDetailData += '<a href="" class="btn btn-primary btn-sm">楼主</a>';
					LTDetailData += '<span style="margin-left: 5px" id="during_date">'+util.dateDiffForLT(data.data.lt_creatime)+'</span>';
					LTDetailData += '</div></div>';
					
					LTDetailData += '<div class="content_tie container">';
					LTDetailData += '<div class="col-xs-12 meet " style="line-height: 30px;color:#555;height: auto" id="lun_cont">'+data.data.lt_content+'</div>';
					//LTDetailData += '<div class="col-xs-12 meet " style="line-height: 30px;height: 30px" id="tie_type">美女想认识你</div>';
					LTDetailData += '</div>';
					if(data.data.pics.length>0){
						for(var i=0;i<data.data.pics.length;i++){
							LTDetailData += '<div class="img_tie container" style="padding: 5px ">';
							LTDetailData += '<div class="col-xs-12">';
							LTDetailData += '<img src="'+data.data.pics[i]+'" alt="" style="width: 100%;overflow: hidden">';
							LTDetailData += '</div>';
						}
					}
					LTDetailData += '</div>';
					//点赞详情
					LTDetailData += '<div class="container">';
					LTDetailData += '<div class="col-xs-12 text-right">';
					LTDetailData += '<span class="zan"><img src="img/list-post-Thumb-up.png" alt="" style="width: 25px;height: 25px;"></span>';
					LTDetailData += '<span class="b_radios"><img src="img/list-post-The-picture.png" alt="" style=""></span>';
					LTDetailData += '<span class="b_radios"><img src="img/list-post-The-picture.png" alt="" style=""></span>';
					LTDetailData += '<span>.,......</span>';
					LTDetailData += '</div></div>';
					//点赞列表
					LTDetailData += '<div class="" style="height: 10px;background: #E7E7E7;margin-top: 10px"></div>';
					//一级评论
					if(data.data.comms.length>0){
						for(var i=0;i<data.data.comms.length;i++){
							LTDetailData += '<div class="row" style="margin-top: 37px;border-top:10px solid #F1F1F1;padding: 20px 0 6px">';
							LTDetailData += '<div class="container">';
							LTDetailData += '<div class="col-xs-2">';
							if(data.data.comms[i].profile!= null || data.data.comms[i].profile!='' || data.data.comms[i].profile!=undefined){
								LTDetailData += '<a href="lingju_yonghuziliao.html" style="display: inline-block"><img src="'+data.data.comms[i].profile+'" alt=""></a>';
							}else {
								LTDetailData += '<a href="lingju_yonghuziliao.html" style="display: inline-block"><img src="img/list-alerts-The-picture.png" alt=""></a>';
							}
							LTDetailData += '</div>';
							LTDetailData += '<div class="col-xs-10 zan_div" style="padding-top: 5px">';
							LTDetailData += '<div class="col-xs-12" style="padding-right: 0">';
							LTDetailData += '<span class="font block">'+data.data.comms[i].nickname+'</span>';
							LTDetailData += '<a href="" class="btn-sm btn-warning btn block" style="margin-left: 5px">LV'+data.data.comms[i].level+'</a>';
							LTDetailData += '<span href="" class="time block">'+util.dateDiffForLT(data.data.comms[i].comm_creatime)+'</span>';
							LTDetailData += '<span style="margin-left:1%" class="stairblock block">1楼</span>';
							LTDetailData += '<span class="z_icon block" style=""></span>';
							LTDetailData += '<span class="z_number block">0</span>';
							LTDetailData += '<span class="blue_icon block pop_modal" onclick="luntan.commont.popTwoCommont('+data.data.comms[i].comm_id+',\''+data.data.comms[i].user_id+'\');"></span>';
							LTDetailData += '</div>';
							LTDetailData += '<div class="col-xs-12" style="line-height: 1.7;padding-top: 15px;padding-bottom: 15px;color: #C4C4C4;word-wrap: break-word">'+data.data.comms[i].content;
							LTDetailData += '</div></div></div>';
							//添加聊天详情
							LTDetailData += '<div class="container" style="padding-top: 20px;padding-bottom: 20px">';
							var multiComms = data.data.comms[i].multiComms;
							if(multiComms!=null && multiComms.length>0){
								for(var j=0;j<multiComms.length;j++){
									LTDetailData += '<div class="col-xs-12 meet col-xs-offset-1">';
									LTDetailData += '<a href="lingju_yonghuziliao.html" style="display: inline-block"><img src="'+multiComms[j].profile+'" alt=""></a>';
									LTDetailData += '<span class="name">'+multiComms[j].nickname+'</span>';
									//LTDetailData += '<span class="btn btn-primary btn-xs ">楼主</span>';
									LTDetailData += '<span>&nbsp;:&nbsp;&nbsp;&nbsp;</span>';
									LTDetailData += '<span>'+multiComms[j].t_content+'</span>';
//								LTDetailData += '<div class="col-xs-12 meet col-xs-offset-1 ">';
//								LTDetailData += '<span>已经被帅死</span>';
//								LTDetailData += '<span>:</span>';
//								LTDetailData += '<span>哎啊被发现了</span>';
//								LTDetailData += '</div>';
									//LTDetailData += '<div class="noMore">没有更多了..</div>';
									LTDetailData += '</div>';
								}
							}
							LTDetailData += '</div>';
						}
					}
					LTDetailData += '<div class="col-xs-12" style="text-align: right;"><a href="chartAnser.html">查看更多>></a></div>';
					LTDetailData += '</div>';
					$('.wrap').append(LTDetailData);
				},
				error: function(){alert('论坛详情数据展示失败');}
			});
		}
	},
	
	/*评论相关的交互*/
	commont: {
		//对一级评论是的某条论坛数据展示
		showOneLTData: function(){
			$.ajax({
				url: 'luntan/showDetailLT.do',
				data: {lt_id: util.getSession('lt_id')},
				type: 'post',
				dataType: 'json',
				success: function(data){
					var oneLt = '';
					if(data.data){
						oneLt = '<div class="row" style="margin-top: 37px;border-top:10px solid #F1F1F1;padding: 20px 0 6px">';
						oneLt += '<div class="container">';
						oneLt += '<div class="col-xs-2">';
						oneLt += '<a href="lingju_yonghuziliao.html" style="display: inline-block"><img src="'+data.data.profile+'" alt=""></a>';
						oneLt += '</div>';
						oneLt += '<div class="col-xs-7">';
						oneLt += '<div class="col-xs-12"><span class="font">'+data.data.nickname+'</span><a href="" class="btn-sm btn-warning btn "style="margin-left: 5px">LV.'+data.data.level+'</a>';
						oneLt += '<span style="margin-left: 5px">'+util.dateDiffForLT(data.data.lt_creatime)+'</span>';
						oneLt += '</div></div></div>';
						oneLt += '<div class="container">';
						oneLt += '<div class="col-xs-12 meet col-xs-offset-2">'+data.data.lt_content+'</div>';
						oneLt += '</div></div>';
					}
					$('#oneLt').append(oneLt);
				},
				error: function(){alert('评论失败');}
			});
		},
		//提交一级评论
		submitOneLVComm: function(){
			if(util.getSession('token')==null || util.getSession('token')==undefined || util.getSession('token')==''){
				alert('请您先登录');
				location.href = 'login.html';
			}else if($('#comm_content').val()=='' || $('#comm_content').val()==null){
				alert('评论的内容不能为空');
			}else {
				var comm_params = {
					user_id: util.getSession('token'),
					other_user_id: util.getSession('other_user_id'),
					lt_id: util.getSession('lt_id'),
					content: $('#comm_content').val()
				};
				console.log(comm_params);
				$.ajax({
					url: 'luntan/addOneLVCommont.do',
					data: comm_params,
					type: 'post',
					dataType: 'json',
					success: function(data){
						alert(data.msg);
						$('#comm_content').val('');
					},
					error: function(){alert('评论失败');}
				});
			}
		},
		
		popTwoCommont: function(comm_id, user_id){
			$('#commont').modal('toggle');
			util.setSession('comm_id', comm_id);
			util.setSession('other_user_id', user_id);
		},
		//提交二级评论
		submitTwoLVComm: function(){
			if(util.getSession('token')==null || util.getSession('token')==undefined || util.getSession('token')==''){
				alert('请您先登录');
				location.href = 'login.html';
			}else if($('#content').val()=='' || $('#content').val()==null){
				alert('评论的内容不能为空');
			}else {
				var comm_params = {
					user_id: util.getSession('token'),
					other_user_id: util.getSession('other_user_id'),
					comm_id: util.getSession('comm_id'),
					content: $('#content').val()
				};
				console.log(comm_params);
				$.ajax({
					url: 'luntan/addTwoLVCommont.do',
					data: comm_params,
					type: 'post',
					dataType: 'json',
					success: function(data){
						alert(data.msg);
						$('#content').val('');
						location.reload(true);
					},
					error: function(){alert('评论失败');}
				});
			}
		}
	},
	
	
};
