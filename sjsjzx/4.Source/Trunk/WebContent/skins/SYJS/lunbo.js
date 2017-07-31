$(function(){
/*banner轮播*/
	var picCur = 0;
	var picNub = $(".box_photo img").length;
	function moveImg(){
		if(picCur<picNub-1){
			picCur++;
		}
		else{
			picCur=0;
		}
		$(".box_photo").animate({marginLeft:-1002*picCur},1000);
		$(".banner_text li").hide().eq(picCur).fadeIn(500);
		$(".banner_btn span").removeClass("current");
		if(picCur==$(".box_photo img").length){
			$(".banner_btn span").eq(0).addClass("current");
		}else{
			$(".banner_btn span").eq(picCur).addClass("current");
		}
	}	
	t=setInterval(moveImg,8000);
	$(".banner_box").hover(function(){
		clearInterval(t);
		},function(){
		t=setInterval(moveImg,8000);
	})
	
	$(".banner_btn span").click(function(){
		picCur=$(".banner_btn span").index(this);
		$(".box_photo").stop().animate({marginLeft:-1002*picCur},1000);
		$(".banner_text li").hide().eq(picCur).fadeIn(500);
		$(".banner_btn span").removeClass("current");
		$(this).addClass("current");
	});
});