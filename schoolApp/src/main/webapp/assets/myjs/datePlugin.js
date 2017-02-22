/*
 * ��input�������ڿؼ�
 * param _obj��jQuery����
 */
function initDate(_obj){
	_obj.datepicker({
		showButtonPanel: true,//��ʾ�ײ��˵�
		changeMonth: true,//��ѡ����
	    changeYear: true,//��ѡ����
	    dateFormat: 'yy-mm-dd'
	});
}

/* 
 * ��ʱ���װ����yyyy-MM-dd HH-mm-ss�����ڸ�ʽ
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