
/**
 * Created by Administrator on 2016/11/10.
 */

mui.init();
(function($) {
    //$.swipeoutOpen(el,direction)//打开指定列的滑动菜单，el:指定列的dom对象，direction：取值left|right，指定打开的是左侧或右侧滑动菜单
    //$.swipeoutClose(el);//关闭指定列的滑动菜单，el:指定列的dom对象
    //				setTimeout(function() {
    //					$.swipeoutOpen(document.getElementById("OA_task_1").querySelector('li:last-child'), 'left');
    //					setTimeout(function() {
    //						$.swipeoutClose(document.getElementById("OA_task_1").querySelector('li:last-child'));
    //					}, 1000);
    //				}, 1000);
    //第一个demo，拖拽后显示操作图标，点击操作图标删除元素；
    $('#OA_task_1').on('tap', '.mui-btn', function(event) {
        var elem = this;
        var li = elem.parentNode.parentNode;
        mui.confirm('确认删除？', '', btnArray, function(e) {
            if (e.index == 0) {
                li.parentNode.removeChild(li);
            } else {
                setTimeout(function() {
                    $.swipeoutClose(li);
                }, 0);
            }
        });
    });
    var btnArray = ['确认', '取消'];
    //第二个demo，向左拖拽后显示操作图标，释放后自动触发的业务逻辑
    $('#OA_task_2').on('slideleft', '.mui-table-view-cell', function(event) {
        var elem = this;
        mui.confirm('确认删除？', '', btnArray, function(e) {
            if (e.index == 0) {
                elem.parentNode.removeChild(elem);
            } else {
                setTimeout(function() {
                    $.swipeoutClose(elem);
                }, 0);
            }
        });
    });
    //第二个demo，向右拖拽后显示操作图标，释放后自动触发的业务逻辑
    $('#OA_task_2').on('slideright', '.mui-table-view-cell', function(event) {
        var elem = this;
        mui.confirm('确认删除？', '', btnArray, function(e) {
            if (e.index == 0) {
                elem.parentNode.removeChild(elem);
            } else {
                setTimeout(function() {
                    $.swipeoutClose(elem);
                }, 0);
            }
        });
    });
})(mui);

