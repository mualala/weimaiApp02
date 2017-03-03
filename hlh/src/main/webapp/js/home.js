/**
 * Created by Administrator on 2016/10/28.
 */
$(function () {
    $(window).scroll(function () {
         if($(window).scrollTop()>44){
             $(".mark2").css({
                 opacity:1
             });
         }else {
             $(".mark2").css({
                 opacity:0
             });
         }
    });
});
$(function () {
    var pos_menue=$('.pos_menue1');
    pos_menue.click(function () {
        $(".big").hide();
        $(".big_map").show();
        /*
        mui.init();
        mui.ready(function() {
            var header = document.querySelector('header.mui-bar');
            var list = document.getElementById('list');
            //calc hieght
            list.style.height = (document.body.offsetHeight - header.offsetHeight) + 'px';
            //create
            window.indexedList = new mui.IndexedList(list);
        });
        */
    });
});
// 关闭功能
$(function () {
    var clouse=$(".clouse");
    clouse.click(function(){
    	$(".big").show();
        $(".big_map").hide();
    });
});