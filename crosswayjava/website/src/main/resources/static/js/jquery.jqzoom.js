//**************************************************************
// jQZoom allows you to realize a small magnifier window,close
// to the image or images on your web page easily.
//
// jqZoom version 2.1
// Author Doc. Ing. Renzi Marco(www.mind-projects.it)
// First Release on Dec 05 2007
// i'm searching for a job,pick me up!!!
// mail: renzi.mrc@gmail.com
//**************************************************************

(function ($) {

    $.fn.jqueryzoom = function (options) {
    	//图片详情设置参数
        var settings = {
            xzoom: 200,//zoomed width default width
            yzoom: 200,//zoomed div default width
            //空出30
            offset: 360,	//zoomed div default offset
            position: "right",//zoomed div default position,offset position is to the right of the image
            lens: 1, //zooming lens over the image,by default is 1;
            preload: 1
        };
        //如果传入对象存在，将绑定参数嵌入options中
        if (options) {
            $.extend(settings, options);
        }

        var noalt = '';
        //定义hover事件
        $(this).hover(function () {
        	//获取元素的偏移量
            var imageLeft = $(this).offset().left;
            var imageRight = this.offsetRight;
            var imageTop = $(this).get(0).offsetTop;
            var imageWidth = $(this).children('img').get(0).offsetWidth;
            var imageHeight = $(this).children('img').get(0).offsetHeight;
            //alt属性--无法显示图片时出现的文本
            noalt = $(this).children("img").attr("alt");
            //大图的路径属性
            var bigimage = $(this).children("img").attr("src");
            //控制台输出属性
            //设置所有图片alt属性为''
            $(this).children("img").attr("alt", "");
            //如果zoomdiv为空--zoomdiv就是右边出现的图片放大镜
            if ($("div.zoomdiv").get().length == 0) {
            	//就在当前节点后插入一个放大镜图片
                $(this).after("<div class='zoomdiv'><img class='bigimg' src='" + bigimage + "'/></div>");
                //jqZoomPup的定位有问题
                //当前节点内部再插入一个jqZoomPup--灰色的小框
                $(this).append("<div class='jqZoomPup'>&nbsp;</div>");

            }

            //如果设置成右侧出现放大镜
            if (settings.position == "right") {
            		//总宽超出屏幕
                if ( imageWidth + settings.offset + settings.xzoom > screen.width) {
                	//放左边
                    leftpos = - settings.offset - settings.xzoom;

                } else {
                	//放右边--目前使用的样式 leftpos...放大镜左边的距离参数
                    leftpos =  imageWidth + settings.offset;
                    
                }
            } else {
                leftpos = imageLeft - settings.xzoom - settings.offset;
                if (leftpos < 0) {
                	
                    leftpos = imageWidth + settings.offset;

                }

            }
            //放大镜和图片平齐高
            //放大镜位置参数！TO FIXED
            $("div.zoomdiv").css({ top: imageTop, left: leftpos });
            //长宽固定参数
            $("div.zoomdiv").width(settings.xzoom);

            $("div.zoomdiv").height(settings.yzoom);
            //出现
            $("div.zoomdiv").show();
            //如果没有setting.lens参数
            if (!settings.lens) {
            	//设置光标形状参数
                $(this).css('cursor', 'crosshair');
            }



            //捕捉鼠标参数
            $(document.body).mousemove(function (e) {
            	
            	
            	
                mouse = new MouseEvent(e);
                /*$("div.jqZoomPup").hide();*/

                //zoomdiv可见长宽
                var bigwidth = $(".bigimg").get(0).offsetWidth;
                
                var bigheight = $(".bigimg").get(0).offsetHeight;
                
                var scaley = 'x';

                var scalex = 'y';


                if (isNaN(scalex) | isNaN(scaley)) {

                    var scalex = (bigwidth / imageWidth);

                    var scaley = (bigheight / imageHeight);


                    //放大镜高宽

                    $("div.jqZoomPup").width((settings.xzoom) / scalex);

                    $("div.jqZoomPup").height((settings.yzoom) / scaley);
                    
                    //visible
                    if (settings.lens) {
                        $("div.jqZoomPup").css('visibility', 'visible');
                    }

                }



                xpos = mouse.x - $("div.jqZoomPup").width() / 2 - imageLeft;
                ypos = mouse.y - $("div.jqZoomPup").height() / 2 - imageTop;
                if (settings.lens) {
                	//(鼠标x坐标-放大镜宽度/2)  
                    xpos = (mouse.x - $("div.jqZoomPup").width() / 2 < imageLeft) ? 0 : (mouse.x + $("div.jqZoomPup").width() / 2 > imageWidth + imageLeft) ? (imageWidth - $("div.jqZoomPup").width() - 2) : xpos;
                    ypos = (mouse.y - $("div.jqZoomPup").height() / 2 < imageTop) ? 0 : (mouse.y + $("div.jqZoomPup").height() / 2 > imageHeight + imageTop) ? (imageHeight - $("div.jqZoomPup").height() - 2) : ypos;
                    
                }

                
                if (settings.lens) {
                	
                    $("div.jqZoomPup").css({ top: ypos-11, left: xpos });
                }



                scrolly = ypos;

                $("div.zoomdiv").get(0).scrollTop = scrolly * scaley;

                scrollx = xpos;

                $("div.zoomdiv").get(0).scrollLeft = scrollx * scalex;


            });
        }, function () {

            $(this).children("img").attr("alt", noalt);
            $(document.body).unbind("mousemove");
            if (settings.lens) {
                $("div.jqZoomPup").remove();
            }
            $("div.zoomdiv").remove();

        });

        count = 0;

        if (settings.preload) {

            $('body').append("<div style='display:none;' class='jqPreload" + count + "'>sdsdssdsd</div>");

            $(this).each(function () {

                var imagetopreload = $(this).children("img").attr("src");

                var content = jQuery('div.jqPreload' + count + '').html();

                jQuery('div.jqPreload' + count + '').html(content + '<img src=\"' + imagetopreload + '\">');

            });

        }

    }

})(jQuery);

function MouseEvent(e) {
    this.x = e.pageX
    this.y = e.pageY
}