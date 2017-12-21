// JavaScript Document
$(document).ready(function(){
	var monitor = $("#monitorAll li"),
		step = 95,
		height = 40,
		index = 888,
		len = monitor.length;
	for(i=0; i < len; i++){
		var j = Math.floor(i / 8);
		if(j%2 ==0){
			var left = 26 + (i - j * 8)*step - j*5 + "px",
				iHeight = (j + 1)*height + "px",
				iWidth = 100 + j*5 + "px",
				top = 160 - j*26 + "px",
				index = index -1;
			monitor.eq(i).css("left", left);
			monitor.eq(i).css("height", iHeight);
			monitor.eq(i).css("width", iWidth);
			monitor.eq(i).css("top", top);
			monitor.eq(i).css("z-index", index);
		}
		else{
			monitor.eq(i).addClass("below");
			var left = (i - j * 8)*step + j*2 - 58 + "px",
				iHeight = 40 + (j-1)*26 + "px",
				iWidth = 100 + j*3 + "px",
				top = 250 + "px";
			monitor.eq(i).css("left", left);
			monitor.eq(i).css("height", iHeight);
			monitor.eq(i).css("width", iWidth);
			monitor.eq(i).css("top", top);
			monitor.eq(i).css("z-index", index);
			
		}
	};
})