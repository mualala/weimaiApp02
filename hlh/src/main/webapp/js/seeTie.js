/**
 * Created by Administrator on 2016/11/3 0003.
 */
$(function () {
   var z_icon =$(".z_icon");
    z_icon.click(function () {
        $(this).toggleClass("z_icon2");
        var text=parseInt($(this).siblings(".z_number").text());
        var img = $(this).parents(".zan_div").prev(".left_img").find("img").attr("src");
        var type = $(this).parents(".zan_div").prev(".left_img").find("img").data("type");
        var last = $(".text-right").find("span:last");
        if($(this).hasClass("z_icon2")){
            $(this).siblings(".z_number").text(text+1);
            last.before('<span class="b_radios" data-type="'+ type +'"><img src="'+ img +'" /></span>')
        }else {
            $(this).siblings(".z_number").text(text-1);
            $(".text-right").find("span").each(function (i,e) {
                if($(e).attr('data-type') == type){
                    $(e).remove();
                }
            });
        }
    })
})