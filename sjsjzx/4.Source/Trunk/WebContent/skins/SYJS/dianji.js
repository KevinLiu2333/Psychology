// JavaScript Document
$.fn.dianji=function(){
	var $secDJ = $(".second_left dl dt");
	$(".second_left dl dd:gt(0)").hide();
			$secDJ.click(function(){
					  $(this).addClass("click_info")
					  .siblings().removeClass(); 
				var indexnub=$secDJ.index(this);
				$(".second_left dl dd").hide().eq(indexnub).fadeIn(200);
					  });
	var $fouDJ = $(".fourth_left dl dt");
	$(".fourth_left dl dd:gt(0)").hide();
			$fouDJ.click(function(){
					  $(this).addClass("click_info")
					  .siblings().removeClass(); 
				var indexnub=$fouDJ.index(this);
				$(".fourth_left dl dd").hide().eq(indexnub).fadeIn(200);
					  });

};

