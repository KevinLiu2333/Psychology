$(document).ready(function(){
	var largeC = 190,
		centerC =120,
		smallC = 68,
		left = 490,
		bottom = 490,
		x = 0,
		y = 0;
	$("#nodeShow li").each(function(index, li){
		if($(li).find("h6").html().length >= 7){
			$(li).addClass("bigCirle");
			x = randomC(0, left - largeC);
			y = randomC(0, bottom - largeC);
		}
		else if((7 > $(li).find("h6").html().length) && ($(li).find("h6").html().length >= 5)){
			$(li).addClass("midCirle");
			x = randomC(0, left - centerC);
			y = randomC(0, bottom - centerC);
		}
		else{
			$(li).addClass("smallCirle");
			x = randomC(0, left - smallC);
			y = randomC(0, bottom - smallC);
		}
		$(li).css({
		  "left" : x,
		  "top" : y
		});
	})
	function randomC(minV,maxV){
		return Math.floor(minV+Math.random()*(maxV-minV));
	}
})