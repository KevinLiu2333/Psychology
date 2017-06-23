var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?db7a75599ee5b52297633ce950a71943";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();

$(function(){
	var btnGotoTop = document.createElement("a");
	btnGotoTop.setAttribute("class", "btn-gotoTop");
	btnGotoTop.setAttribute("href", "javascript:void(0)");
	var btnZxkf = document.createElement("a");
	btnZxkf.setAttribute("class", "btn-zxkf");
	btnZxkf.setAttribute("href", "javascript:void(0)");
	var s = document.getElementsByTagName("div")[0]; 
	s.parentNode.insertBefore(btnGotoTop, s);
	s.parentNode.insertBefore(btnZxkf, s);
	$(".btn-gotoTop").css("display","none");
	$(window).scroll(function() {
		if ($(document).scrollTop() > 0 ) {
			$(".btn-gotoTop").css("display","block");
		}else{
			$(".btn-gotoTop").css("display","none");
		}

	  		});	
	$(".btn-zxkf").click(
		function(){
			window.open(
					"http://p.qiao.baidu.com/cps/chat?siteId=9178434&userId=21135638",
					"newwindow",
					"toolbar=no,scrollbars=yes,resizable=no,top=50%,left=50%,width=800,height=600"
					);
			
		}
			);
	$(".btn-gotoTop").click(function(){
		scrollTo(0,0);
		
	});
	
});