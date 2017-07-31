$(document).ready(function(){
	$('canvas.percent').each(function(i,o) {  
        // 第一部先拿到canvas标签中间的文字,就是那个61%(这里的stringTrim方法是我自己的方法,去前后空格的方法很多的,这里就不贴出来了)  
        var text = $(this).text();  
        var process = text.substring(0, text.length-1);  
                  
        // 一个canvas标签  
        var canvas = this;  
            // 拿到绘图上下文,目前只支持"2d"  
        var context = canvas.getContext('2d');
		var role = $(this).data("role");
		var color = "";
		var color_red = "#f00",
			color_orange = "#f09307",
			color_green = "#00a449";
    	// 将绘图区域清空,如果是第一次在这个画布上画图,画布上没有东西,这步就不需要了  
        context.clearRect(0, 0, 70, 70);  
          
    	// ***开始画一个灰色的圆  
        context.beginPath();  
        // 坐标移动到圆心  
        context.moveTo(35, 35);  
        // 画圆,圆心是35,35,半径35,从角度0开始,画到2PI结束,最后一个参数是方向顺时针还是逆时针  
        context.arc(35, 35, 35, 0, Math.PI * 2, false);  
        context.closePath();  
        // 填充颜色  
        context.fillStyle = '#dbdbdb';  
        context.fill();  
        // ***灰色的圆画完  
          
        // 画进度  
        context.beginPath();  
        // 画扇形的时候这步很重要,画笔不在圆心画出来的不是扇形  
        context.moveTo(35, 35);  
        // 跟上面的圆唯一的区别在这里,不画满圆,画个扇形  
        context.arc(35, 35, 35, 0, Math.PI * 2 * process / 100, false);  
        context.closePath();
		if(role == "cpu"){
			if(parseInt(process) >= 95){
				color = color_red;
			} 
			else if( (50 < parseInt(process)) && (parseInt(process) < 95)){
				color = color_orange;
			}
			else if(parseInt(process) <= 50){
				color = color_green;
			}
		}
		else{
			if(parseInt(process) >= 80){
				color = color_red;
			} 
			else if( (50 < parseInt(process)) && (parseInt(process) < 80)){
				color = color_orange;
			}
			else if(parseInt(process) <= 50){
				color = color_green;
			}
		}
		
        context.fillStyle = color;  
        context.fill();  
  
        // 画内部空白  
        context.beginPath();  
        context.moveTo(35, 35);  
        context.arc(35, 35, 32, 0, Math.PI * 2, true);  
        context.closePath();  
        context.fillStyle = 'rgba(244,235,246,1)';  
        context.fill();
          
        //在中间写字  
        context.font = "normal 12pt Arial";  
        context.fillStyle = color;  
        context.textAlign = 'center';  
        context.textBaseline = 'middle';  
        context.moveTo(35, 35);  
        context.fillText(text, 35, 35);  
   })
})