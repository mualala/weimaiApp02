/**
 * Created by Administrator on 2016/11/2.
 */
var dataArr = [
    {picUrl:'./resource/images/1.jpg',width:522,height:783},
    {picUrl:'./resource/images/2.jpg',width:550,height:786},
    {picUrl:'./resource/images/3.jpg',width:535,height:800},
    {picUrl:'./resource/images/4.jpg',width:578,height:504},
    {picUrl:'./resource/images/5.jpg',width:1440,height:900}
];

$.each(dataArr, function(index, item) {
    $("body").append('<div class="box box-item">' +
        '<div class="img" style="height:0;padding-bottom:'+cRate(item) * 100 + "%"+'" data-src="'+item.picUrl+'"></div>' +
        '<div class="desc">Description</div>' +
        '</div>');
});

var boxArr = $('.box'),
    num = Math.floor(document.body.clientWidth / boxArr.eq(0).outerWidth(true)),
    columnHeightArr = [];
columnHeightArr.length = num;

arrangement();

$('body').css('minHeight',Math.max.apply(null, columnHeightArr));

lazyLoad();

function arrangement() {
    boxArr.each(function(index, item) {
        if (index < num) {
            columnHeightArr[index] = $(item).position().top + $(item).outerHeight(true);
        } else {
            var minHeight = Math.min.apply(null, columnHeightArr),
                minHeightIndex = $.inArray(minHeight, columnHeightArr);
            $(item).css({
                position: 'absolute',
                top: minHeight,
                left: boxArr.eq(minHeightIndex).position().left
            });
            columnHeightArr[minHeightIndex] += $(item).outerHeight(true);
        }
    });
}

function lazyLoad() {
    var boxArr = $('.box-item');
    boxArr.each(function(index, item) {
        var viewTop = $(item).offset().top - $(window).scrollTop(),
            imgObj = $(item).find('.img');
        if ((viewTop < $(window).height()) && ($(item).offset().top + $(item).outerHeight(true) > $(window).scrollTop())) {
//                console.log($(item).attr('data-src'));
            imgObj.css('backgroundImage','url('+imgObj.attr("data-src")+')').removeClass('data-src');
            $(item).removeClass('box-item');
        }
    })
}

function cRate(obj) {
    return obj.height / obj.width;
}

function scrollLoad() {
    var viewHeight = $(window).scrollTop() + $(window).height(),
        minHeight = Math.min.apply(null, columnHeightArr);
    if (viewHeight >= minHeight) {
        //loadMore...
    }
}

$(window).scroll(function() {
    lazyLoad();
    scrollLoad();
});