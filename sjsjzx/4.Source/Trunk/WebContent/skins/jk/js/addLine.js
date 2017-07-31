$(document).ready(function(){
	var index = 1,
		btn = "";
	$(".addIcon").on("click", function(){
		var $this = $(this),
			str = "",
			idName = "",
			idOrigin = $this.parent().find("li").eq(0).find("select").attr("id"),
			last = $this.parent().find("li").length - 1;
		if(btn == idOrigin.substring(idOrigin.indexOf("_")-1,idOrigin.indexOf("_"))){
			index++;
			console.log("A"+index);
		}
		else{
			for(i = 0; i < $this.parent().parent().find("ul").length; i++){
				var maxV = 0;
				var eachID = $this.parent().parent().find("ul").eq(i).find("li select").eq(0).attr("id");
				eachID = eachID.substring(eachID.length-1, eachID.length);
				if(maxV < parseInt(eachID)){maxV = parseInt(eachID);}
			}	
			index = maxV + 1;
			console.log("B"+index);
		}
		for(i = 0; i < last; i++){
			idOrigin = $this.parent().find("li").eq(i).find("select").attr("id");
			idName = idOrigin.substring(0,idOrigin.indexOf("_") + 1) + index;
			str = str + "<li>" + $this.parent().find("li select").eq(i).attr("id",idName).parent().html() + "</li>";
			$this.parent().find("li select").eq(i).attr("id",idOrigin);
		}
		btn = idOrigin.substring(idOrigin.indexOf("_")-1,idOrigin.indexOf("_"));
		str = "<ul>" + str + "<li class='deleteLine'><span title='删除当前部门信息'></span></li></ul>";
		$this.parent().parent().append(str);
	})
	$(".alertContent").delegate(".deleteLine span", "click", function(){
		$(this).parent().parent().remove();
	})
})