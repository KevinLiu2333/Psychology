<!doctype html>
<html lang="en">
 <head>
  <title>图片切换</title>
  <style>
li{
	list-style-type:none;
}
#ad{
	width:524px;
	text-align:center;
}
#ad_screen{
	width:524px;
	height:190px;
	float:left;
}
.ad_page{
	float:left;
	margin-top:-30px;
	margin-left:210px;
}
.ad_page li{
	float:left;
	background-color:#999;
	width:20px;
	height:20px;
	margin-left:3px;
}
.ad_page li a{
	color:#fff;
}
</style>
  <script>
function changead(num,refre)
{	
	if(num == 5)
		num = 1;
	var sc = document.getElementById("screens");
	sc.src="images/ad-0" + num+ ".jpg";
	
}
</script>
 </head>
 <body>
	<div class="middle">
	 <div id="ad">
	   <div id="ad_screen">
		 <img id="screens" src="http://localhost:8080/sjdc/skins/images/index/banner_01.jpg" width="524" height="190">
		</div>
		<ul class="ad_page">
	     <li><a href="#" id="p1" onclick="changead(1);">1</a></li>
		 <li><a href="#" id="p2" onclick="changead(2);">2</a></li>
		 <li><a href="#" id="p3" onclick="changead(3);">3</a></li>
		 <li><a href="#" id="p4" onclick="changead(4);">4</a></li>
	   </ul>
	 </div>
  </div>
 </body>
</html>