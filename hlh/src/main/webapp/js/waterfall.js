// /**
//  * Created by win 7 on 2015/7/23.
//  */
//
// function ajaxFun(callBack,url){
//     $.ajax({
//         url: url,
//         dataType: "jsonp",
//         jsonpCallback:"test",
//         success: function(data){
//             callBack(data);
//
//         }
//     });
// }
// function waterFall(id,moduleLines,htmlTxt,ajaxFun,windowWidth,url){
//     this.element = $("#"+id);
//     this.moduleLines = moduleLines;      //  加载列数
//     this.htmlTxt = htmlTxt;
//     this.loadNum = 0;                            //  每次加载的个数统计
//     this.moduleNum = 0;                       //  总的模块加载个数
//     this.ajaxFun =  ajaxFun;                    //  ajax执行方法
//     this.imgUrls = [];                               //   图片连接
//     this.showNum = undefined;             // 每次加载个数
//     this.url=url;
// //    this.width = $(window).width();
//     this.win=windowWidth;
//     this.width = windowWidth<=640?windowWidth-10:630;
//     this.moduleWid = this.width/this.moduleLines;
//     this.moduleLeftArr = [];
//     this.linesTopArr = [];
//     this.loadFinish = false;
// }
// waterFall.prototype = {
//
//     // 加载模块
//     loadModule : function(){
//         var _this = this;
//         var imgUrl = this.imgUrls[this.loadNum].imgURL;
//         var _title=this.imgUrls[this.loadNum].p1;
//         var _test=this.imgUrls[this.loadNum].p2;
//         var _num=this.imgUrls[this.loadNum].num;
//         var $pic = $(this.htmlTxt);
//         var $module = $("<li class='module'></li>");
//         $module.append($pic).css({width:this.moduleWid,left:this.moduleLeftArr[this.lineIndex]+5,top:this.linesTopArr[this.lineIndex]});
//
//         $pic.find(".pic-title").text(_title);
//         $pic.find(".pic-test").text(_test);
//         $pic.find(".pic-num span").text(_num);
//         $pic.find("img").attr('src',imgUrl).load(function(){
//             _this.element.find("ul").append($module);
//             _this.linesTopArr[_this.lineIndex] += $module.outerHeight();
//             _this.loadNum++;
//             _this.moduleNum++;
//             if(_this.loadNum < _this.showNum){
//                 if(_this.moduleNum < _this.moduleLines){
//                     _this.create();
//                 }else{
//                     _this.lineIndex = _this.getMinIndex(_this.linesTopArr);
//                     _this.loadModule();
//                 }
//             }else{
//                 _this.lineIndex = _this.getMinIndex(_this.linesTopArr);
//                 _this.loadNum = 0;
//             }
//             _this.setConHeight(_this.linesTopArr);
//         });
//     },
//
//     // 初始化加载第一排
//     create : function(){
//         var i = this.loadNum;
//         this.lineIndex = i;
//         this.moduleLeftArr[i] = i*this.moduleWid;
//         this.linesTopArr[i] = 0;
//         this.loadModule();
//         return this;
//     },
//
//     // 设置模块区域的高度
//     setConHeight : function(arry){
//         var height = this.getMaxNum(arry);
//         this.element.css('height',height);
//     },
//
//     // 获取数组中最小值的索引值
//     getMinIndex : function(arry){
//         var min = arry[0], index = 0;
//         for(var i = 0; i < arry.length - 1; i++){
//             if(min > arry[i+1]){
//                 min = arry[i+1];
//                 index = i + 1;
//             }
//         }
//         return index;
//     },
//
//     // 获取数组中最大值
//     getMaxNum : function(arry){
//         var max = arry[0];
//         for(var i = 0; i < arry.length - 1; i++){
//             if(max < arry[i+1]){
//                 max = arry[i+1];
//             }
//         }
//         return max;
//     },
//
//     // 滚动加载
//     scrollLoad : function(){
//         var _this = this;
//         $(window).scroll(function(){
//             if($(this).scrollTop() + $(this).height() >= _this.element.height() - 20){
//                 // alert(0);
//                 _this.ajaxFun(function(data){
//                     _this.imgUrls = data.imgUrls;
//                     _this.showNum = _this.imgUrls.length;
//                     _this.loadModule();
//                 },_this.url);
//             }
//         });
//     },
//
//     // 初始化加载第一排
//     init : function(){
//         var _this = this;
//         this.ajaxFun(function(data){
//             _this.element.find("ul").html("");
//             _this.imgUrls = data.imgUrls;
//             _this.showNum = _this.imgUrls.length;
//             _this.create().scrollLoad();
//         },_this.url);
//
//     }
// }
// var htmlTxt = "<div class='pic'><a href='commodity.html'><p class='pic-title'></p><p class='pic-test'></p><div class='pic-num'><span></span> 宝豆</div><img src='' alt=''></a></div>";
// var $window=$(window).width(),url='js/json0.js';
// //result0= new waterFall("container0",2,htmlTxt,ajaxFun,$window,url);
// //result0.init();
// // result1= new waterFall("container1",2,htmlTxt,ajaxFun,$window,"js/json1.js");
// // result1.init();
// //result2= new waterFall("container2",2,htmlTxt,ajaxFun,$window,url);
// //result2.init();
// //$(".container").each(function(i){
// //    result= new waterFall("container"+i,2,htmlTxt,ajaxFun,$window,url);
// //    result.init();
// //
// //});
//
//
//
//
