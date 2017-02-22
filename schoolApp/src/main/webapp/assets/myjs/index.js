
var index = {
		
		interceptor: function(){
			if(($.cookie('adminName'))==undefined || ($.cookie('adminName'))==''){
				location.href="login.html";//跳转到登录页面
			}
		},
		
		notify: function(){
			var totalNotify = $("#totalNotify");
			var noActiveNotify = $("#noActiveNotify");
			var noUserNotify = $("#noUserNotify");
			$.ajax({
				url:'admin/notifies.do',
				data:'',
				success:function(data){
					$("#totalNotifyBell").html('');
					$("#totalNotifyBell").html(data.noActiveNotify+data.noUserNotify);
					
					$("#totalNotify").html('');
					$("#totalNotify").html('<i class="icon-warning-sign"></i>'+(data.noActiveNotify+data.noUserNotify)+'条通知');
					
					$("#noActiveNotify").html('');
					$("#noActiveNotify").html(data.noActiveNotify);
					
					$("#noUserNotify").html('');
					$("#noUserNotify").html(data.noUserNotify);
					
					$("#adminUserName").html('');
					$("#adminUserName").html('<small>欢迎光临,</small>'+'<b>'+$.cookie("adminName")+'</b>');
				},
				error:function(){}
			})
		},
		
		//刷新系统数据
		refreshSysData: function(){
			try{
				index.notify();
				layer.msg('系统数据刷新成功\r\n', {icon: 1});
			}catch (e) {
				layer.msg('系统数据刷新失败,请联系系统管理员\r\n', {icon: 0});
			}
		}
		
		
		
}