$(function(){
    $(".c_section_nav li").click(function(){
        $(this).addClass("c_xuanze").siblings().removeClass("c_xuanze");
    });
});