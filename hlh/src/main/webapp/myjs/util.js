
var util = {
	//seesion级别缓存
	setSession: function(_key,_value){
		try{
			sessionStorage.setItem(_key, _value);
		}catch(e){console.log("token storage failed");}
	},
	getSession: function(_key){
		var sessionVal = sessionStorage.getItem(_key);
		return sessionVal;
	},
	delSession: function(_key){
		sessionStorage.removeItem(_key);
	},
	
	//持久化缓存
	setLocalDB: function(_key,_value){
		try{
			localStorage.setItem(_key, _value);
		}catch(e){console.log("token storage failed");}
	},
	getLocalDB: function(_key){
		var LocalDBVal = localStorage.getItem(_key);
		return LocalDBVal;
	},
	delLocalDB: function(_key){
		localStorage.removeItem(_key);
	},
	
	
	dateDiffForLT: function(_timestamp){
		var timeDiff = '';
        var date = new Date();
        var cur_time = date - (new Date(_timestamp));
        if (cur_time / (24 * 3600 * 1000) >= 1) {
        	timeDiff = Math.floor(cur_time / (24 * 3600 * 1000)) + '天前';
        } else if (cur_time / (3600 * 1000) >= 1) {
        	timeDiff = Math.floor(cur_time / (3600 * 1000)) + '小时前';
        } else if (cur_time / 60 * 1000 >= 1) {
        	timeDiff = Math.ceil(cur_time / (60 * 1000)) + '分钟前';
        } else {
        	timeDiff = Math.ceil(cur_time / 1000) + '秒前';
        }
        return timeDiff;
	},
	
	
	//js的array去重
	arrayRemoVal: function(array){
		var resultArray = [];
		for(var i=0;i<array.length;i++){
			!RegExp(array[i],"g").test(resultArray.join(",")) && (resultArray.push(array[i]));
		}
		return resultArray;
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
			return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
		}catch(e){}
	}
	
	
	
	
}