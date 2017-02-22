/*
 * 给input设置日期控件
 * param _obj是jQuery对象
 */
function initDate(_obj){
	_obj.datepicker({
		showButtonPanel: true,//显示底部菜单
		changeMonth: true,//月选择项
	    changeYear: true,//年选择项
	    dateFormat: 'yy-mm-dd'
	});
}

/* 
 * 将时间戳装换成yyyy-MM-dd HH-mm-ss的日期格式
 */
function dateFormat(_timestamp){
	var _date = new Date(_timestamp);
	var year = _date.getFullYear();
	var month = _date.getMonth()+1;
	var day = _date.getDate();
	var hour = _date.getHours();
	var minute = _date.getMinutes();
	var second = _date.getSeconds();
	return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
}