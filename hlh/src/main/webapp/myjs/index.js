
var index = {
	//替换选中的位置
	replaceLocation: function(_this){
		var location = $(_this).text();
		$("#location").text(location);
		util.setSession("area", location);
	},
	
	
}