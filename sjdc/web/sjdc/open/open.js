function imgFlow(){  
$("#item li:not(:first)").css("display","none");//只显示第一张图片，其它隐藏
  var bb = $("#item li:last");
  var aa = $("#item li:first");
  setInterval(function(){
   if(bb.is(":visible")){
    aa.fadeIn(2000).addClass("in");
    bb.hide();
   }else{
    $("#item li:visible").addClass("in");
    var nextobj = $("#item li.in").next();
	 $("#item li.in").removeClass("in").hide();
	 nextobj.fadeIn(2000);
   }
  },5000);
}

function pause(nextobj){
 }