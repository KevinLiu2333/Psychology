$(document).ready(function(){
	var month = $(".monthLine");
	month.each(function(index, mon){
		$(mon).find("li").each(function(i,o){
//			var step = $(mon).width()/($(mon).find("li").length-1);
//			$(o).css("left", i*step);
//			$(o).click(function(){
//				$(this).addClass("selected").siblings().removeClass("selected");
//			})
		})
	})
	$(".year").each(function(index, year){
		$(year).find("span").click(function(){
			if($(this).prev().is(":visible")){
				$(this).prev().hide();
			}
			else{
				$(this).prev().show();
			}
		})
		$(year).find("li").click(function(){
			$(this).addClass("selected").siblings().removeClass("selected");
			$(year).find("span").html($(this).html());
			$(this).parent().hide();
		})
		$(year).find("ul").mouseleave(function(){
			$(this).hide();
		})
	})
	$(".queue").each(function(index, queue){
		var maxWidth = $(queue).find("li h1").eq(0).width(),
			maxVal = $(queue).find("li h6").eq(0).text();
		$(queue).find("li h6").each(function(i,o){
			$(o).prev().find("div").width(($(o).text()/maxVal)*maxWidth);
		})
	})
})