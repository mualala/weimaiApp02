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
	
	
	
	
	
}