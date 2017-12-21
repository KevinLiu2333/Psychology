$(document).ready(function(){
	var cover = $("#alertBack"),
		shut = $(".winClose"),
		win = $("#alertWin");
	var cancel = $(".canclBtn");
	$.ajax({
		url : '/action/queryForth',
		data : '',
		dataType : 'text',
		error : function() {
		},
		success : function(msg){
			var keyvalue = msg.split(",");
			$("#forthP_0").empty(); 
			$("#forthD_0").empty(); 
			for(var i=0;i<keyvalue.length;i++){
				var deptNo = keyvalue[i].split(":")[0];
				var deptName = keyvalue[i].split(":")[1];
				var option1 = $("<option>").val(deptNo).text(deptName); 
				var option2 = $("<option>").val(deptNo).text(deptName);
				var option3 = $("<option>").val(deptNo).text(deptName);
				$("#forthP_0").append(option1);
				$("#forthD_0").append(option2);
			}
		}
	});
	$.ajax({
		url : '/action/queryForthU',
		data : '',
		dataType : 'text',
		error : function() {
		},
		success : function(msg) {
			var keyvalue = msg.split(",");
			$("#forthU_0").empty(); 
			$("#forthU_1").empty(); 
			for(var i=0;i<keyvalue.length;i++){
				var deptNo = keyvalue[i].split(":")[0];
				var deptName = keyvalue[i].split(":")[1];
				var option1 = $("<option>").val(deptNo).text(deptName); 
				var option2 = $("<option>").val(deptNo).text(deptName);
				var option3 = $("<option>").val(deptNo).text(deptName);
				$("#forthU_0").append(option1);
				$("#forthU_1").append(option2);
			}
		}
	});
	
	$(".addFormBtn").click(function(){
		var current = "#" + $(this).data("role");
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	cover.click(function(){
		// cover.hide();
		// win.hide();
		// $(".alertForm").hide();
		// $("body").css("overflow","auto");
	})
	shut.click(function(){
		cover.hide();
		win.hide();
		$(".alertForm").hide();
		$("body").css("overflow","auto");
		history.go(0);
	})
	cancel.click(function(){
		cover.hide();
		win.hide();
		$(".alertForm").hide();
		$("body").css("overflow","auto");
		history.go(0);
	})
})

function getParams(params) {
	var cover = $("#alertBack"),
	shut = $(".winClose"),
	win = $("#alertWin");
	// 业务域
	$(".areaEditFormBtn").click(function(){
		var current = "#"+$(this).data("role");
		$("#bscode").attr("value",params[0]);
		$("#bsname").attr("value",params[1]);
		$("#memo").attr("value",params[2]);
		$("#areaTitle").html("编辑业务域");
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	//业务域详情
	$(".areaDetailFormBtn").click(function(){
		var current = "#"+$(this).data("role");
		$("#bscode").attr("value",params[0]);
		$("#bsname").attr("value",params[1]);
		$("#memo").attr("value",params[2]);
		$("#areaTitle").html("业务域详情");
		$("#bscode").attr("disabled",true);
		$("#bsname").attr("disabled",true);
		$("#memo").attr("disabled",true);
		$("#bssubmit").hide();
		$("#bscancel").hide();
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	//业务域删除
	$(".areaDelFormBtn").click(function(){
		$("#bscode").attr("value",params[0]);
		$("#bsname").attr("value",params[1]);
		$("#memo").attr("value",params[2]);
		saveForm("bs","del");
	})
	
	// 编辑部门
	$(".apartmentEditFormBtn").click(function(){
		var current = "#"+$(this).data("role");
		$("#deptNo").attr("value",params[0]);
		$("#deptName").attr("value",params[1]);
		$("#parentDeptNo").attr("value",params[2]);
		$("#parentDeptName").attr("value",params[3]);
		$("#type").val(params[4]);
		$("#isConn").attr("value",params[5]);
		selectCheckbox("deptcheck",params[6])
		$("#deptTitle").html("编辑部门");
		if($("#type").val()!="0" && $("#type").val()!="1" && $("#type").val()!="2"&&$("#type").val()!="3"&&$("#type").val()!="4"&&$("#type").val()!="5"){
			$("#deptbsq").css("display","none");
		}
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	//部门详情
	$(".apartmentDetailFormBtn").click(function(){
		var current = "#"+$(this).data("role");
		$("#deptNo").attr("value",params[0]);
		$("#deptName").attr("value",params[1]);
		$("#parentDeptNo").attr("value",params[2]);
		$("#parentDeptName").attr("value",params[3]);
		$("#type").val(params[4]);
		$("#isConn").attr("value",params[5]);
		selectCheckbox("deptcheck",params[6])
		$("#deptTitle").html("部门详情");
		if($("#type").val()!="0" && $("#type").val()!="1" && $("#type").val()!="2"&&$("#type").val()!="3"&&$("#type").val()!="4"&&$("#type").val()!="5"){
			$("#deptbsq").css("display","none");
		}
		$("#deptNo").attr("disabled",true);
		$("#deptName").attr("disabled",true);
		$("#parentDeptNo").attr("disabled",true);
		$("#parentDeptName").attr("disabled",true);
		$("#type").attr("disabled",true);
		$("#isConn").attr("disabled",true);
		$("#deptsubmit").hide();
		$("#deptcancel").hide();
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	//部门删除
	$(".apartmentDelFormBtn").click(function(){
		$("#deptNo").attr("value",params[0]);
		$("#deptName").attr("value",params[1]);
		$("#parentDeptNo").attr("value",params[2]);
		$("#parentDeptName").attr("value",params[3]);
		$("#type").val(params[4]);
		$("#isConn").attr("value",params[5]);
		saveForm("dept","del");
	})
	// 节点
$(".pointEditFormBtn").click(function(){
		var current = "#"+$(this).data("role");
		$("#nodeid").attr("value",params[0]);
		$("#nodename").attr("value",params[1]);
		$("#nodeip").attr("value",params[2]);
		$("#hostName").attr("value",params[3]);
		$("#os").attr("value",params[4]);
		$("#username").attr("value",params[5]);
		$("#password").attr("value",params[6]);
		$("#pointTitle").html("编辑节点");
		var firstv="",secondv="",thirdv="",forthv="";
		var nodedata = {
			nodeid:params[0]
		};
		$.ajax( {
			url : '/action/queryDeptByNodeId',
			data : nodedata,
			async: false,
			dataType : 'text',
			error : function() {
			},
			success : function(msg) {
				var keyvalue = msg.split("#");
				for(var k=0;k<keyvalue.length-1;k++){
						var index = 1,
						btn = "";
						var btnIcon = $("#paddIcon"),
						str = "",
						idName = "",
						idOrigin = btnIcon.parent().find("li").eq(0).find("select").attr("id"),
						last = btnIcon.parent().find("li").length - 1;
					if(btn == idOrigin.substring(idOrigin.indexOf("_")-1,idOrigin.indexOf("_"))){
						index++;
						console.log("A"+index);
					}
					else{
						for(i = 0; i < btnIcon.parent().parent().find("ul").length; i++){
							var maxV = 0;
							var eachID = btnIcon.parent().parent().find("ul").eq(i).find("li select").eq(0).attr("id");
							eachID = eachID.substring(eachID.length-1, eachID.length);
							if(maxV < parseInt(eachID)){maxV = parseInt(eachID);}
						}	
						index = maxV + 1;
						console.log("B"+index);
					}
					for(i = 0; i < last; i++){
						idOrigin = btnIcon.parent().find("li").eq(i).find("select").attr("id");
						idName = idOrigin.substring(0,idOrigin.indexOf("_") + 1) + index;
						str = str + "<li>" + btnIcon.parent().find("li select").eq(i).attr("id",idName).parent().html() + "</li>";
						btnIcon.parent().find("li select").eq(i).attr("id",idOrigin);
					}
					btn = idOrigin.substring(idOrigin.indexOf("_")-1,idOrigin.indexOf("_"));
					str = "<ul>" + str + "<li class='deleteLine'><span title='删除当前部门信息'></span></li></ul>";
					btnIcon.parent().parent().append(str);
				}
				for(var k=0;k<keyvalue.length;k++){
					var jibie = keyvalue[k].split(",")[0];
					$("#firstP_"+k).val(jibie);
				if(jibie=="A"){
					firstv="A";
					forthv=keyvalue[k].split(",")[3];
					$("#forthP_"+k).val(forthv);
				} else if(jibie=="B"){
					firstv="B";
					secondv=keyvalue[k].split(",")[1];
					forthv=keyvalue[k].split(",")[3];
					$("#secondP_"+k).css("display","table-row");
					$("#thirdP_"+k).css("display","none");
					$("#forthP_"+k).empty(); 
					$.ajax({
						url : '/action/querySecond',
						data : '',
						async: false,
						dataType : 'text',
						error : function() {
						},
						success : function(msg2) {
							var keyvalue2 = msg2.split(",");
							$("#secondP_"+k).empty(); 
							for(var i=0;i<keyvalue2.length;i++){
								var deptNo = keyvalue2[i].split(":")[0];
								var deptName = keyvalue2[i].split(":")[1];
								var option = $("<option>").val(deptNo).text(deptName); 
								$("#secondP_"+k).append(option);
							}
							$("#secondP_"+k).val(secondv);
							        var deptdata = {
									    deptNo:secondv
							        };
									$.ajax( {
										url : '/action/querySecondForth',
										data : deptdata,
										async: false,
										dataType : 'text',
										error : function() {
										},
										success : function(msg) {
											var keyvalue3 = msg.split(",");
											$("#forthP_"+k).empty(); 
											for(var i=0;i<keyvalue3.length;i++){
												var deptNo = keyvalue3[i].split(":")[0];
												var deptName = keyvalue3[i].split(":")[1];
												var option = $("<option>").val(deptNo).text(deptName); 
												$("#forthP_"+k).append(option);
											}
											$("#forthP_"+k).val(forthv);
										}
									});
						}
					});
				} else if (jibie=="C"){
					firstv="C";
					secondv=keyvalue[k].split(",")[1];
					thirdv=keyvalue[k].split(",")[2];
					forthv=keyvalue[k].split(",")[3];
					$("#secondP_"+k).css("display","table-row");
					$("#thirdP_"+k).css("display","table-row");
					$("#forthP_"+k).empty(); 
					$.ajax( {
						url : '/action/querySecond',
						data : '',
						async: false,
						dataType : 'text',
						error : function() {
						},
						success : function(msg2) {
							var keyvalue2 = msg2.split(",");
							$("#secondP_"+k).empty(); 
							for(var i=0;i<keyvalue2.length;i++){
								var deptNo = keyvalue2[i].split(":")[0];
								var deptName = keyvalue2[i].split(":")[1];
								var option = $("<option>").val(deptNo).text(deptName); 
								$("#secondP_"+k).append(option);
							}
							$("#secondP_"+k).val(secondv);
							        var deptdata = {
									    deptNo:secondv
							        };
									$.ajax( {
										url : '/action/querySecondThird',
										data : deptdata,
										async: false,
										dataType : 'text',
										error : function() {
										},
										success : function(msg3) {
											var keyvalue3 = msg3.split(",");
											$("#thirdP_"+k).empty(); 
											for(var i=0;i<keyvalue3.length;i++){
												var deptNo = keyvalue3[i].split(":")[0];
												var deptName = keyvalue3[i].split(":")[1];
												var option = $("<option>").val(deptNo).text(deptName); 
												$("#thirdP_"+k).append(option);
											}
											$("#thirdP_"+k).val(thirdv);
											        var deptdata2 = {
													    deptNo:thirdv
											        };
													$.ajax( {
														url : '/action/queryThirdForth',
														data : deptdata2,
														async: false,
														dataType : 'text',
														error : function() {
														},
														success : function(msg4) {
															var keyvalue4 = msg4.split(",");
															$("#forthP_"+k).empty(); 
															for(var i=0;i<keyvalue4.length;i++){
																var deptNo = keyvalue4[i].split(":")[0];
																var deptName = keyvalue4[i].split(":")[1];
																var option = $("<option>").val(deptNo).text(deptName); 
																$("#forthP_"+k).append(option);
															}
															$("#forthP_"+k).val(forthv); 
														}
													});
										}
									});
						}
					});
				}
			}
			}
		});
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	//节点详情
	$(".pointDetailFormBtn").click(function(){
		var current = "#"+$(this).data("role");
		$("#nodeid").attr("value",params[0]);
		$("#nodename").attr("value",params[1]);
		$("#nodeip").attr("value",params[2]);
		$("#hostName").attr("value",params[3]);
		$("#os").attr("value",params[4]);
		$("#username").attr("value",params[5]);
		$("#password").attr("value",params[6]);
		$("#pointTitle").html("节点详情");
		$("#nodeid").attr("disabled",true);
		$("#nodename").attr("disabled",true);
		$("#nodeip").attr("disabled",true);
		$("#hostName").attr("disabled",true);
		$("#os").attr("disabled",true);
		$("#username").attr("disabled",true);
		$("#password").attr("disabled",true);
		$("#pointsubmit").hide();
		$("#pointcancel").hide();
		var firstv="",secondv="",thirdv="",forthv="";
		var nodedata = {
			nodeid:params[0]
		};
		$.ajax( {
			url : '/action/queryDeptByNodeId',
			data : nodedata,
			async: false,
			dataType : 'text',
			error : function() {
			},
			success : function(msg) {
				var keyvalue = msg.split("#");
				for(var k=0;k<keyvalue.length-1;k++){
						var index = 1,
						btn = "";
						var btnIcon = $("#paddIcon"),
						str = "",
						idName = "",
						idOrigin = btnIcon.parent().find("li").eq(0).find("select").attr("id"),
						last = btnIcon.parent().find("li").length - 1;
					if(btn == idOrigin.substring(idOrigin.indexOf("_")-1,idOrigin.indexOf("_"))){
						index++;
						console.log("A"+index);
					}
					else{
						for(i = 0; i < btnIcon.parent().parent().find("ul").length; i++){
							var maxV = 0;
							var eachID = btnIcon.parent().parent().find("ul").eq(i).find("li select").eq(0).attr("id");
							eachID = eachID.substring(eachID.length-1, eachID.length);
							if(maxV < parseInt(eachID)){maxV = parseInt(eachID);}
						}	
						index = maxV + 1;
						console.log("B"+index);
					}
					for(i = 0; i < last; i++){
						idOrigin = btnIcon.parent().find("li").eq(i).find("select").attr("id");
						idName = idOrigin.substring(0,idOrigin.indexOf("_") + 1) + index;
						str = str + "<li>" + btnIcon.parent().find("li select").eq(i).attr("id",idName).parent().html() + "</li>";
						btnIcon.parent().find("li select").eq(i).attr("id",idOrigin);
					}
					btn = idOrigin.substring(idOrigin.indexOf("_")-1,idOrigin.indexOf("_"));
					str = "<ul>" + str + "<li class='deleteLine'><span title='删除当前部门信息'></span></li></ul>";
					btnIcon.parent().parent().append(str);
				}
				for(var k=0;k<keyvalue.length;k++){
					var jibie = keyvalue[k].split(",")[0];
					$("#firstP_"+k).val(jibie);
				if(jibie=="A"){
					firstv="A";
					forthv=keyvalue[k].split(",")[3];
					$("#forthP_"+k).val(forthv);
				} else if(jibie=="B"){
					firstv="B";
					secondv=keyvalue[k].split(",")[1];
					forthv=keyvalue[k].split(",")[3];
					$("#secondP_"+k).css("display","table-row");
					$("#thirdP_"+k).css("display","none");
					$("#forthP_"+k).empty(); 
					$.ajax({
						url : '/action/querySecond',
						data : '',
						async: false,
						dataType : 'text',
						error : function() {
						},
						success : function(msg2) {
							var keyvalue2 = msg2.split(",");
							$("#secondP_"+k).empty(); 
							for(var i=0;i<keyvalue2.length;i++){
								var deptNo = keyvalue2[i].split(":")[0];
								var deptName = keyvalue2[i].split(":")[1];
								var option = $("<option>").val(deptNo).text(deptName); 
								$("#secondP_"+k).append(option);
							}
							$("#secondP_"+k).val(secondv);
							        var deptdata = {
									    deptNo:secondv
							        };
									$.ajax( {
										url : '/action/querySecondForth',
										data : deptdata,
										async: false,
										dataType : 'text',
										error : function() {
										},
										success : function(msg) {
											var keyvalue3 = msg.split(",");
											$("#forthP_"+k).empty(); 
											for(var i=0;i<keyvalue3.length;i++){
												var deptNo = keyvalue3[i].split(":")[0];
												var deptName = keyvalue3[i].split(":")[1];
												var option = $("<option>").val(deptNo).text(deptName); 
												$("#forthP_"+k).append(option);
											}
											$("#forthP_"+k).val(forthv);
										}
									});
						}
					});
				} else if (jibie=="C"){
					firstv="C";
					secondv=keyvalue[k].split(",")[1];
					thirdv=keyvalue[k].split(",")[2];
					forthv=keyvalue[k].split(",")[3];
					$("#secondP_"+k).css("display","table-row");
					$("#thirdP_"+k).css("display","table-row");
					$("#forthP_"+k).empty(); 
					$.ajax( {
						url : '/action/querySecond',
						data : '',
						async: false,
						dataType : 'text',
						error : function() {
						},
						success : function(msg2) {
							var keyvalue2 = msg2.split(",");
							$("#secondP_"+k).empty(); 
							for(var i=0;i<keyvalue2.length;i++){
								var deptNo = keyvalue2[i].split(":")[0];
								var deptName = keyvalue2[i].split(":")[1];
								var option = $("<option>").val(deptNo).text(deptName); 
								$("#secondP_"+k).append(option);
							}
							$("#secondP_"+k).val(secondv);
							        var deptdata = {
									    deptNo:secondv
							        };
									$.ajax( {
										url : '/action/querySecondThird',
										data : deptdata,
										async: false,
										dataType : 'text',
										error : function() {
										},
										success : function(msg3) {
											var keyvalue3 = msg3.split(",");
											$("#thirdP_"+k).empty(); 
											for(var i=0;i<keyvalue3.length;i++){
												var deptNo = keyvalue3[i].split(":")[0];
												var deptName = keyvalue3[i].split(":")[1];
												var option = $("<option>").val(deptNo).text(deptName); 
												$("#thirdP_"+k).append(option);
											}
											$("#thirdP_"+k).val(thirdv);
											        var deptdata2 = {
													    deptNo:thirdv
											        };
													$.ajax( {
														url : '/action/queryThirdForth',
														data : deptdata2,
														async: false,
														dataType : 'text',
														error : function() {
														},
														success : function(msg4) {
															var keyvalue4 = msg4.split(",");
															$("#forthP_"+k).empty(); 
															for(var i=0;i<keyvalue4.length;i++){
																var deptNo = keyvalue4[i].split(":")[0];
																var deptName = keyvalue4[i].split(":")[1];
																var option = $("<option>").val(deptNo).text(deptName); 
																$("#forthP_"+k).append(option);
															}
															$("#forthP_"+k).val(forthv); 
														}
													});
										}
									});
						}
					});
				}
			}
			}
		});
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	//节点删除
	$(".pointDelFormBtn").click(function(){
		$("#nodeid").attr("value",params[0]);
		$("#nodename").attr("value",params[1]);
		$("#nodeip").attr("value",params[2]);
		$("#hostName").attr("value",params[3]);
		$("#os").attr("value",params[4]);
		$("#username").attr("value",params[5]);
		$("#password").attr("value",params[6]);
		saveForm("point","del");
	})
	
	// 数据源
$(".dbEditFormBtn").click(function(){
		var current = "#"+$(this).data("role");
		$("#dbcode").attr("value",params[0]);
		$("#dbname").attr("value",params[1]);
		$("#url").attr("value",params[2]);
		$("#driver").attr("value",params[3]);
		$("#user").attr("value",params[4]);
		$("#pwd").attr("value",params[5]);
		$("#dbTitle").html("编辑数据源");
		var firstv="",secondv="",thirdv="",forthv="";
		var dbdata = {
			dbcode:params[0]
		};
		$.ajax( {
			url : '/action/queryDeptByDbcode',
			data : dbdata,
			async: false,
			dataType : 'text',
			error : function() {
			},
			success : function(msg) {
				var keyvalue = msg.split("#");
				for(var k=0;k<keyvalue.length-1;k++){
						var index = 1,
						btn = "";
						var btnIcon = $("#paddIcon"),
						str = "",
						idName = "",
						idOrigin = btnIcon.parent().find("li").eq(0).find("select").attr("id"),
						last = btnIcon.parent().find("li").length - 1;
					if(btn == idOrigin.substring(idOrigin.indexOf("_")-1,idOrigin.indexOf("_"))){
						index++;
						console.log("A"+index);
					}
					else{
						for(i = 0; i < btnIcon.parent().parent().find("ul").length; i++){
							var maxV = 0;
							var eachID = btnIcon.parent().parent().find("ul").eq(i).find("li select").eq(0).attr("id");
							eachID = eachID.substring(eachID.length-1, eachID.length);
							if(maxV < parseInt(eachID)){maxV = parseInt(eachID);}
						}	
						index = maxV + 1;
						console.log("B"+index);
					}
					for(i = 0; i < last; i++){
						idOrigin = btnIcon.parent().find("li").eq(i).find("select").attr("id");
						idName = idOrigin.substring(0,idOrigin.indexOf("_") + 1) + index;
						str = str + "<li>" + btnIcon.parent().find("li select").eq(i).attr("id",idName).parent().html() + "</li>";
						btnIcon.parent().find("li select").eq(i).attr("id",idOrigin);
					}
					btn = idOrigin.substring(idOrigin.indexOf("_")-1,idOrigin.indexOf("_"));
					str = "<ul>" + str + "<li class='deleteLine'><span title='删除当前部门信息'></span></li></ul>";
					btnIcon.parent().parent().append(str);
				}
				for(var k=0;k<keyvalue.length;k++){
					var jibie = keyvalue[k].split(",")[0];
					$("#firstD_"+k).val(jibie);
				if(jibie=="A"){
					firstv="A";
					forthv=keyvalue[k].split(",")[3];
					$("#forthD_"+k).val(forthv);
				} else if(jibie=="B"){
					firstv="B";
					secondv=keyvalue[k].split(",")[1];
					forthv=keyvalue[k].split(",")[3];
					$("#secondD_"+k).css("display","table-row");
					$("#thirdD_"+k).css("display","none");
					$("#forthD_"+k).empty(); 
					$.ajax({
						url : '/action/querySecond',
						data : '',
						async: false,
						dataType : 'text',
						error : function() {
						},
						success : function(msg2) {
							var keyvalue2 = msg2.split(",");
							$("#secondD_"+k).empty(); 
							for(var i=0;i<keyvalue2.length;i++){
								var deptNo = keyvalue2[i].split(":")[0];
								var deptName = keyvalue2[i].split(":")[1];
								var option = $("<option>").val(deptNo).text(deptName); 
								$("#secondD_"+k).append(option);
							}
							$("#secondD_"+k).val(secondv);
							        var deptdata = {
									    deptNo:secondv
							        };
									$.ajax( {
										url : '/action/querySecondForth',
										data : deptdata,
										async: false,
										dataType : 'text',
										error : function() {
										},
										success : function(msg) {
											var keyvalue3 = msg.split(",");
											$("#forthD_"+k).empty(); 
											for(var i=0;i<keyvalue3.length;i++){
												var deptNo = keyvalue3[i].split(":")[0];
												var deptName = keyvalue3[i].split(":")[1];
												var option = $("<option>").val(deptNo).text(deptName); 
												$("#forthD_"+k).append(option);
											}
											$("#forthD_"+k).val(forthv);
										}
									});
						}
					});
				} else if (jibie=="C"){
					firstv="C";
					secondv=keyvalue[k].split(",")[1];
					thirdv=keyvalue[k].split(",")[2];
					forthv=keyvalue[k].split(",")[3];
					$("#secondD_"+k).css("display","table-row");
					$("#thirdD_"+k).css("display","table-row");
					$("#forthD_"+k).empty(); 
					$.ajax( {
						url : '/action/querySecond',
						data : '',
						async: false,
						dataType : 'text',
						error : function() {
						},
						success : function(msg2) {
							var keyvalue2 = msg2.split(",");
							$("#secondD_"+k).empty(); 
							for(var i=0;i<keyvalue2.length;i++){
								var deptNo = keyvalue2[i].split(":")[0];
								var deptName = keyvalue2[i].split(":")[1];
								var option = $("<option>").val(deptNo).text(deptName); 
								$("#secondD_"+k).append(option);
							}
							$("#secondD_"+k).val(secondv);
							        var deptdata = {
									    deptNo:secondv
							        };
									$.ajax( {
										url : '/action/querySecondThird',
										data : deptdata,
										async: false,
										dataType : 'text',
										error : function() {
										},
										success : function(msg3) {
											var keyvalue3 = msg3.split(",");
											$("#thirdD_"+k).empty(); 
											for(var i=0;i<keyvalue3.length;i++){
												var deptNo = keyvalue3[i].split(":")[0];
												var deptName = keyvalue3[i].split(":")[1];
												var option = $("<option>").val(deptNo).text(deptName); 
												$("#thirdD_"+k).append(option);
											}
											$("#thirdD_"+k).val(thirdv);
											        var deptdata2 = {
													    deptNo:thirdv
											        };
													$.ajax( {
														url : '/action/queryThirdForth',
														data : deptdata2,
														async: false,
														dataType : 'text',
														error : function() {
														},
														success : function(msg4) {
															var keyvalue4 = msg4.split(",");
															$("#forthD_"+k).empty(); 
															for(var i=0;i<keyvalue4.length;i++){
																var deptNo = keyvalue4[i].split(":")[0];
																var deptName = keyvalue4[i].split(":")[1];
																var option = $("<option>").val(deptNo).text(deptName); 
																$("#forthD_"+k).append(option);
															}
															$("#forthD_"+k).val(forthv); 
														}
													});
										}
									});
						}
					});
				}
			}
			}
		});
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	//数据源详情
	$(".dbDetailFormBtn").click(function(){
		var current = "#"+$(this).data("role");
		$("#dbcode").attr("value",params[0]);
		$("#dbname").attr("value",params[1]);
		$("#url").attr("value",params[2]);
		$("#driver").attr("value",params[3]);
		$("#user").attr("value",params[4]);
		$("#pwd").attr("value",params[5]);
		$("#dbTitle").html("数据源详情");
		$("#dbcode").attr("disabled",true);
		$("#dbname").attr("disabled",true);
		$("#url").attr("disabled",true);
		$("#driver").attr("disabled",true);
		$("#user").attr("disabled",true);
		$("#pwd").attr("disabled",true);
		$("#dbsubmit").hide();
		$("#dbcancel").hide();
		var firstv="",secondv="",thirdv="",forthv="";
		var dbdata = {
			dbcode:params[0]
		};
		$.ajax( {
			url : '/action/queryDeptByDbcode',
			data : dbdata,
			async: false,
			dataType : 'text',
			error : function() {
			},
			success : function(msg) {
				var keyvalue = msg.split("#");
				for(var k=0;k<keyvalue.length-1;k++){
						var index = 1,
						btn = "";
						var btnIcon = $("#paddIcon"),
						str = "",
						idName = "",
						idOrigin = btnIcon.parent().find("li").eq(0).find("select").attr("id"),
						last = btnIcon.parent().find("li").length - 1;
					if(btn == idOrigin.substring(idOrigin.indexOf("_")-1,idOrigin.indexOf("_"))){
						index++;
						console.log("A"+index);
					}
					else{
						for(i = 0; i < btnIcon.parent().parent().find("ul").length; i++){
							var maxV = 0;
							var eachID = btnIcon.parent().parent().find("ul").eq(i).find("li select").eq(0).attr("id");
							eachID = eachID.substring(eachID.length-1, eachID.length);
							if(maxV < parseInt(eachID)){maxV = parseInt(eachID);}
						}	
						index = maxV + 1;
						console.log("B"+index);
					}
					for(i = 0; i < last; i++){
						idOrigin = btnIcon.parent().find("li").eq(i).find("select").attr("id");
						idName = idOrigin.substring(0,idOrigin.indexOf("_") + 1) + index;
						str = str + "<li>" + btnIcon.parent().find("li select").eq(i).attr("id",idName).parent().html() + "</li>";
						btnIcon.parent().find("li select").eq(i).attr("id",idOrigin);
					}
					btn = idOrigin.substring(idOrigin.indexOf("_")-1,idOrigin.indexOf("_"));
					str = "<ul>" + str + "<li class='deleteLine'><span title='删除当前部门信息'></span></li></ul>";
					btnIcon.parent().parent().append(str);
				}
				for(var k=0;k<keyvalue.length;k++){
					var jibie = keyvalue[k].split(",")[0];
					$("#firstD_"+k).val(jibie);
				if(jibie=="A"){
					firstv="A";
					forthv=keyvalue[k].split(",")[3];
					$("#forthD_"+k).val(forthv);
				} else if(jibie=="B"){
					firstv="B";
					secondv=keyvalue[k].split(",")[1];
					forthv=keyvalue[k].split(",")[3];
					$("#secondD_"+k).css("display","table-row");
					$("#thirdD_"+k).css("display","none");
					$("#forthD_"+k).empty(); 
					$.ajax({
						url : '/action/querySecond',
						data : '',
						async: false,
						dataType : 'text',
						error : function() {
						},
						success : function(msg2) {
							var keyvalue2 = msg2.split(",");
							$("#secondD_"+k).empty(); 
							for(var i=0;i<keyvalue2.length;i++){
								var deptNo = keyvalue2[i].split(":")[0];
								var deptName = keyvalue2[i].split(":")[1];
								var option = $("<option>").val(deptNo).text(deptName); 
								$("#secondD_"+k).append(option);
							}
							$("#secondD_"+k).val(secondv);
							        var deptdata = {
									    deptNo:secondv
							        };
									$.ajax( {
										url : '/action/querySecondForth',
										data : deptdata,
										async: false,
										dataType : 'text',
										error : function() {
										},
										success : function(msg) {
											var keyvalue3 = msg.split(",");
											$("#forthD_"+k).empty(); 
											for(var i=0;i<keyvalue3.length;i++){
												var deptNo = keyvalue3[i].split(":")[0];
												var deptName = keyvalue3[i].split(":")[1];
												var option = $("<option>").val(deptNo).text(deptName); 
												$("#forthD_"+k).append(option);
											}
											$("#forthD_"+k).val(forthv);
										}
									});
						}
					});
				} else if (jibie=="C"){
					firstv="C";
					secondv=keyvalue[k].split(",")[1];
					thirdv=keyvalue[k].split(",")[2];
					forthv=keyvalue[k].split(",")[3];
					$("#secondD_"+k).css("display","table-row");
					$("#thirdD_"+k).css("display","table-row");
					$("#forthD_"+k).empty(); 
					$.ajax( {
						url : '/action/querySecond',
						data : '',
						async: false,
						dataType : 'text',
						error : function() {
						},
						success : function(msg2) {
							var keyvalue2 = msg2.split(",");
							$("#secondD_"+k).empty(); 
							for(var i=0;i<keyvalue2.length;i++){
								var deptNo = keyvalue2[i].split(":")[0];
								var deptName = keyvalue2[i].split(":")[1];
								var option = $("<option>").val(deptNo).text(deptName); 
								$("#secondD_"+k).append(option);
							}
							$("#secondD_"+k).val(secondv);
							        var deptdata = {
									    deptNo:secondv
							        };
									$.ajax( {
										url : '/action/querySecondThird',
										data : deptdata,
										async: false,
										dataType : 'text',
										error : function() {
										},
										success : function(msg3) {
											var keyvalue3 = msg3.split(",");
											$("#thirdD_"+k).empty(); 
											for(var i=0;i<keyvalue3.length;i++){
												var deptNo = keyvalue3[i].split(":")[0];
												var deptName = keyvalue3[i].split(":")[1];
												var option = $("<option>").val(deptNo).text(deptName); 
												$("#thirdD_"+k).append(option);
											}
											$("#thirdD_"+k).val(thirdv);
											        var deptdata2 = {
													    deptNo:thirdv
											        };
													$.ajax( {
														url : '/action/queryThirdForth',
														data : deptdata2,
														async: false,
														dataType : 'text',
														error : function() {
														},
														success : function(msg4) {
															var keyvalue4 = msg4.split(",");
															$("#forthD_"+k).empty(); 
															for(var i=0;i<keyvalue4.length;i++){
																var deptNo = keyvalue4[i].split(":")[0];
																var deptName = keyvalue4[i].split(":")[1];
																var option = $("<option>").val(deptNo).text(deptName); 
																$("#forthD_"+k).append(option);
															}
															$("#forthD_"+k).val(forthv); 
														}
													});
										}
									});
						}
					});
				}
			}
			}
		});
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	//数据源删除
	$(".dbDelFormBtn").click(function(){
		$("#dbcode").attr("value",params[0]);
		$("#dbname").attr("value",params[1]);
		$("#url").attr("value",params[2]);
		$("#driver").attr("value",params[3]);
		$("#user").attr("value",params[4]);
		$("#pwd").attr("value",params[5]);
		saveForm("db","del");
	})
	//角色
	$(".roleEditFormBtn").click(function(){
		var current = "#"+$(this).data("role");
		$("#level").attr("value",params[0]);
		$("#rolename").attr("value",params[1]);
		$("#rolememo").attr("value",params[2]);
		$("#roleTitle").html("编辑角色");
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	//角色详情
	$(".roleDetailFormBtn").click(function(){
		var current = "#"+$(this).data("role");
		$("#level").attr("value",params[0]);
		$("#rolename").attr("value",params[1]);
		$("#rolememo").attr("value",params[2]);
		$("#roleTitle").html("角色详情");
		$("#level").attr("disabled",true);
		$("#rolename").attr("disabled",true);
		$("#rolememo").attr("disabled",true);
		$("#rolesubmit").hide();
		$("#rolecancel").hide();
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	//角色删除
	$(".roleDelFormBtn").click(function(){
		$("#level").attr("value",params[0]);
		$("#rolename").attr("value",params[1]);
		$("#rolememo").attr("value",params[2]);
		saveForm("role","del");
	})
	
		// 用户
$(".userEditFormBtn").click(function(){
		var current = "#"+$(this).data("role");
		$("#uname").attr("value",params[0]);
		$("#upass").attr("value",params[1]);
		$("#createtime").attr("value",params[2]);
		$("#uroles").val(params[3]);
		$("#userTitle").html("编辑用户");
		if(params[5]=="0"){
			$("#userselect").val("A");
		var p = {
			depttype:"0"
		};
		$.ajax( {
			url : '/action/queryUDEPT',
			data : p,
			dataType : 'text',
			async: false,
			error : function() {
			},
			success : function(msg) {
				var keyvalue = msg.split(",");
				$("#userselectv").empty(); 
				for(var i=0;i<keyvalue.length;i++){
					var deptNo = keyvalue[i].split(":")[0];
					var deptName = keyvalue[i].split(":")[1];
					var option = $("<option>").val(deptNo).text(deptName); 
					$("#userselectv").append(option);
				}
				 
			}
		});
		} else if(params[5]=="1"){
			$("#userselect").val("B");
			var p = {
					depttype:"1"
				};
				$.ajax( {
					url : '/action/queryUDEPT',
					data : p,
					dataType : 'text',
					async: false,
					error : function() {
					},
					success : function(msg) {
						var keyvalue = msg.split(",");
						$("#userselectv").empty(); 
						for(var i=0;i<keyvalue.length;i++){
							var deptNo = keyvalue[i].split(":")[0];
							var deptName = keyvalue[i].split(":")[1];
							var option = $("<option>").val(deptNo).text(deptName); 
							$("#userselectv").append(option);
						}
						 
					}
				});
				} else if(params[5]=="2"){
					$("#userselect").val("C");
					var p = {
							depttype:"2"
						};
						$.ajax( {
							url : '/action/queryUDEPT',
							data : p,
							dataType : 'text',
							async: false,
							error : function() {
							},
							success : function(msg) {
								var keyvalue = msg.split(",");
								$("#userselectv").empty(); 
								for(var i=0;i<keyvalue.length;i++){
									var deptNo = keyvalue[i].split(":")[0];
									var deptName = keyvalue[i].split(":")[1];
									var option = $("<option>").val(deptNo).text(deptName); 
									$("#userselectv").append(option);
								}
								 
							}
						});
						}  else if(params[5]=="3"){
							$("#userselect").val("D");
							var p = {
									depttype:"3"
								};
								$.ajax( {
									url : '/action/queryUDEPT',
									data : p,
									dataType : 'text',
									async: false,
									error : function() {
									},
									success : function(msg) {
										var keyvalue = msg.split(",");
										$("#userselectv").empty(); 
										for(var i=0;i<keyvalue.length;i++){
											var deptNo = keyvalue[i].split(":")[0];
											var deptName = keyvalue[i].split(":")[1];
											var option = $("<option>").val(deptNo).text(deptName); 
											$("#userselectv").append(option);
										}
										 
									}
								});
								}  else if(params[5]=="4"){
									$("#userselect").val("E");
									var p = {
											depttype:"4"
										};
										$.ajax( {
											url : '/action/queryUDEPT',
											data : p,
											dataType : 'text',
											async: false,
											error : function() {
											},
											success : function(msg) {
												var keyvalue = msg.split(",");
												$("#userselectv").empty(); 
												for(var i=0;i<keyvalue.length;i++){
													var deptNo = keyvalue[i].split(":")[0];
													var deptName = keyvalue[i].split(":")[1];
													var option = $("<option>").val(deptNo).text(deptName); 
													$("#userselectv").append(option);
												}
											}
										});
										} else if(params[5]=="5"){
											$("#userselect").val("F");
											var p = {
													depttype:"5"
												};
												$.ajax( {
													url : '/action/queryUDEPT',
													data : p,
													dataType : 'text',
													async: false,
													error : function() {
													},
													success : function(msg) {
														var keyvalue = msg.split(",");
														$("#userselectv").empty(); 
														for(var i=0;i<keyvalue.length;i++){
															var deptNo = keyvalue[i].split(":")[0];
															var deptName = keyvalue[i].split(":")[1];
															var option = $("<option>").val(deptNo).text(deptName); 
															$("#userselectv").append(option);
														}
														 
													}
												});
												} 
		$("#userselectv").val(params[4]);
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	//用户详情
	$(".userDetailFormBtn").click(function(){
		var current = "#"+$(this).data("role");
		$("#uname").attr("value",params[0]);
		$("#upass").attr("value",params[1]);
		$("#createtime").attr("value",params[2]);
		$("#uroles").val(params[3]);
		$("#userTitle").html("用户详情");
		if(params[5]=="0"){
			$("#userselect").val("A");
		var p = {
			depttype:"0"
		};
		$.ajax( {
			url : '/action/queryUDEPT',
			data : p,
			dataType : 'text',
			async: false,
			error : function() {
			},
			success : function(msg) {
				var keyvalue = msg.split(",");
				$("#userselectv").empty(); 
				for(var i=0;i<keyvalue.length;i++){
					var deptNo = keyvalue[i].split(":")[0];
					var deptName = keyvalue[i].split(":")[1];
					var option = $("<option>").val(deptNo).text(deptName); 
					$("#userselectv").append(option);
				}
				 
			}
		});
		} else if(params[5]=="1"){
			$("#userselect").val("B");
			var p = {
					depttype:"1"
				};
				$.ajax( {
					url : '/action/queryUDEPT',
					data : p,
					dataType : 'text',
					async: false,
					error : function() {
					},
					success : function(msg) {
						var keyvalue = msg.split(",");
						$("#userselectv").empty(); 
						for(var i=0;i<keyvalue.length;i++){
							var deptNo = keyvalue[i].split(":")[0];
							var deptName = keyvalue[i].split(":")[1];
							var option = $("<option>").val(deptNo).text(deptName); 
							$("#userselectv").append(option);
						}
						 
					}
				});
				} else if(params[5]=="2"){
					$("#userselect").val("C");
					var p = {
							depttype:"2"
						};
						$.ajax( {
							url : '/action/queryUDEPT',
							data : p,
							dataType : 'text',
							async: false,
							error : function() {
							},
							success : function(msg) {
								var keyvalue = msg.split(",");
								$("#userselectv").empty(); 
								for(var i=0;i<keyvalue.length;i++){
									var deptNo = keyvalue[i].split(":")[0];
									var deptName = keyvalue[i].split(":")[1];
									var option = $("<option>").val(deptNo).text(deptName); 
									$("#userselectv").append(option);
								}
								 
							}
						});
						}  else if(params[5]=="3"){
							$("#userselect").val("D");
							var p = {
									depttype:"3"
								};
								$.ajax( {
									url : '/action/queryUDEPT',
									data : p,
									dataType : 'text',
									async: false,
									error : function() {
									},
									success : function(msg) {
										var keyvalue = msg.split(",");
										$("#userselectv").empty(); 
										for(var i=0;i<keyvalue.length;i++){
											var deptNo = keyvalue[i].split(":")[0];
											var deptName = keyvalue[i].split(":")[1];
											var option = $("<option>").val(deptNo).text(deptName); 
											$("#userselectv").append(option);
										}
										 
									}
								});
								}  else if(params[5]=="4"){
									$("#userselect").val("E");
									var p = {
											depttype:"4"
										};
										$.ajax( {
											url : '/action/queryUDEPT',
											data : p,
											dataType : 'text',
											async: false,
											error : function() {
											},
											success : function(msg) {
												var keyvalue = msg.split(",");
												$("#userselectv").empty(); 
												for(var i=0;i<keyvalue.length;i++){
													var deptNo = keyvalue[i].split(":")[0];
													var deptName = keyvalue[i].split(":")[1];
													var option = $("<option>").val(deptNo).text(deptName); 
													$("#userselectv").append(option);
												}
											}
										});
										} else if(params[5]=="5"){
											$("#userselect").val("F");
											var p = {
													depttype:"5"
												};
												$.ajax( {
													url : '/action/queryUDEPT',
													data : p,
													dataType : 'text',
													async: false,
													error : function() {
													},
													success : function(msg) {
														var keyvalue = msg.split(",");
														$("#userselectv").empty(); 
														for(var i=0;i<keyvalue.length;i++){
															var deptNo = keyvalue[i].split(":")[0];
															var deptName = keyvalue[i].split(":")[1];
															var option = $("<option>").val(deptNo).text(deptName); 
															$("#userselectv").append(option);
														}
														 
													}
												});
												} 
		$("#userselectv").val(params[4]);
		$("#uname").attr("disabled",true);
		$("#upass").attr("disabled",true);
		$("#createtime").attr("disabled",true);
		$("#uroles").attr("disabled",true);
		$("#usersubmit").hide();
		$("#usercancel").hide();
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	//用户删除
	$(".userDelFormBtn").click(function(){
		$("#uname").attr("value",params[0]);
		$("#upass").attr("value",params[1]);
		$("#createtime").attr("value",params[2]);
		$("#uroles").val(params[3]);
		saveForm("user","del");
	})
	
	
	//业务表
	$(".bstEditFormBtn").click(function(){
		var current = "#"+$(this).data("role");
		$("#bstcode").val(params[0]);
		$("#tablecode").attr("value",params[1]);
		$("#tablename").attr("value",params[2]);
		$("#tableoname").attr("value",params[3]);
		$("#bstTitle").html("编辑业务表");
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	//业务表详情
	$(".bstDetailFormBtn").click(function(){
		var current = "#"+$(this).data("role");
		$("#bstcode").val(params[0]);
		$("#tablecode").attr("value",params[1]);
		$("#tablename").attr("value",params[2]);
		$("#tableoname").attr("value",params[3]);
		$("#bstTitle").html("业务表详情");
		$("#bstcode").attr("disabled",true);
		$("#tablecode").attr("disabled",true);
		$("#tablename").attr("disabled",true);
		$("#tableoname").attr("disabled",true);
		$("#bstsubmit").hide();
		$("#bstcancel").hide();
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	//业务表删除
	$(".bstDelFormBtn").click(function(){
		$("#bstcode").val(params[0]);
		$("#tablecode").attr("value",params[1]);
		$("#tablename").attr("value",params[2]);
		$("#tableoname").attr("value",params[3]);
		saveForm("bst","del");
	})
	
	
	
	//中间件
	$(".midEditFormBtn").click(function(){
		var current = "#"+$(this).data("role");
		$("#midtype").val(params[0]);
		$("#midcenter").attr("value",params[1]);
		$("#midurl").attr("value",params[2]);
		$("#miduser").attr("value",params[3]);
		$("#midpwd").attr("value",params[4]);
		$("#midTitle").html("编辑中间件");
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	//中间件详情
	$(".midDetailFormBtn").click(function(){
		var current = "#"+$(this).data("role");
		$("#midtype").val(params[0]);
		$("#midcenter").attr("value",params[1]);
		$("#midurl").attr("value",params[2]);
		$("#miduser").attr("value",params[3]);
		$("#midpwd").attr("value",params[4]);
		$("#midTitle").html("中间件详情");
		$("#midtype").attr("disabled",true);
		$("#midcenter").attr("disabled",true);
		$("#midurl").attr("disabled",true);
		$("#miduser").attr("disabled",true);
		$("#midpwd").attr("disabled",true);
		$("#midsubmit").hide();
		$("#midcancel").hide();
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	//中间件删除
	$(".midDelFormBtn").click(function(){
		$("#midtype").val(params[0]);
		$("#midcenter").attr("value",params[1]);
		$("#midurl").attr("value",params[2]);
		$("#miduser").attr("value",params[3]);
		$("#midpwd").attr("value",params[4]);
		saveForm("mid","del");
	})
	
	
		//通信链路
	$(".linkEditFormBtn").click(function(){
		var current = "#"+$(this).data("role");
		$("#forthU_0").val(params[0]);
		$("#forthU_1").val(params[1]);
		$("#checkport").attr("value",params[2]);
		$("#linkTitle").html("编辑通信链路");
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	//通信链路详情
	$(".linkDetailFormBtn").click(function(){
		var current = "#"+$(this).data("role");
		$("#forthU_0").val(params[0]);
		$("#forthU_1").val(params[1]);
		$("#checkport").attr("value",params[2]);
		$("#linkTitle").html("通信链路详情");
		$("#forthU_0").attr("disabled",true);
		$("#forthU_1").attr("disabled",true);
		$("#forthU_1").attr("disabled",true);
		$("#linksubmit").hide();
		$("#linkcancel").hide();
		cover.show();
		win.show();
		$(current).show();
		$("body").css("overflow","hidden");
	})
	//通信链路删除
	$(".linkDelFormBtn").click(function(){
		$("#forthU_0").val(params[0]);
		$("#forthU_1").val(params[1]);
		$("#checkport").attr("value",params[2]);
		saveForm("link","del");
	})
}

function saveForm(type,del){
	if(type=="bs"){
        var bsdata = {
		    bscode:$("#bscode").val(),
            bsname:$("#bsname").val(),
            memo:$("#memo").val()
        };
	if("del"==del){
		$.ajax( {
			url : '/action/delAreaForm',
			data : bsdata,
			dataType : 'text',
			error : function() {
// alert("删除出错!");
			},
			success : function(msg) {
              if(msg=='1'){
//                alert('删除成功!');
                }else{
//                    alert('删除失败!');
                }
				history.go(0);
			}

		});
	} else {
		$.ajax( {
			url : '/action/saveAreaForm',
			data : bsdata,
			dataType : 'text',
			error : function() {
			},
			success : function(msg) {
				var cover = $("#alertBack"),
				shut = $(".winClose"),
				win = $("#alertWin");
				var cancel = $(".canclBtn");
				cover.hide();
				win.hide();
				$(".alertForm").hide();
				$("body").css("overflow","auto");
				history.go(0);
			}
		});
	}
	} else 	if(type=="dept"){
		var isConn='Y',type=$("#type").val(),deptbs='';
// 0 省中心
// 0000 市级路由上行库
// 1 市中心
// 100 省级部门库
// 1000 省级中心库
// 1111 市级路由下行库
// 2 县中心
// 200 市级部门库
// 2000 市级中心库
// 2222 县级路由上行库
// 3 省级部门
// 300 县级部门库
// 3000 县级中心库
// 3333 县级路由下行库
// 4 市级部门
// 5 县级部门
// 90 省级TI监控管理中心
// 91 市级TI监控管理中心
// 92 县级TI监控管理中心
// ZJSD 省级路由下行库
// ZJSU 省级路由上行库
		if(type=="0" || type=="1" || type=="1000" || type=="2" || type=="3"){
			isConn='Y';
		} else {
			isConn="N";
		} 
		if(type=="0" || type=="1" || type=="2"){
            jQuery("input[name='deptcheck']:checked").each(function(){
                deptbs += (deptbs==""?"":",") + $(this).val();
            })
		} else {
			deptbs="";
		} 
        var deptdata = {
		    deptNo:$("#deptNo").val(),
            deptName:$("#deptName").val(),
            parentDeptNo:$("#parentDeptNo").val(),
            parentDeptName:$("#parentDeptName").val(),
			type:type,
            isConn:isConn,
            deptbs:deptbs
        };
	if("del"==del){
		$.ajax( {
			url : '/action/delapartmentForm',
			data : deptdata,
			dataType : 'text',
			error : function() {
			},
			success : function(msg) {
				history.go(0);
			}

		});
	} else {
		$.ajax( {
			url : '/action/saveapartmentForm',
			data : deptdata,
			dataType : 'text',
			error : function() {
			},
			success : function(msg) {
				var cover = $("#alertBack"),
				shut = $(".winClose"),
				win = $("#alertWin");
				var cancel = $(".canclBtn");
				cover.hide();
				win.hide();
				$(".alertForm").hide();
				$("body").css("overflow","auto");
				history.go(0);
			}

		});
	}
	} else if(type=="point"){
		var deptpoint="";
		$("select[name='pointselect']").each(function(){
			deptpoint += (deptpoint==""?"":",") + $(this).val();
		 });
        var pointdata = {
		    nodeid:$("#nodeid").val(),
            nodename:$("#nodename").val(),
            nodeip:$("#nodeip").val(),
			hostName:$("#hostName").val(),
			os:$("#os").val(),
			username:$("#username").val(),
			password:$("#password").val(),
			deptpoint:deptpoint
        };
	if("del"==del){
		$.ajax( {
			url : '/action/delPointForm',
			data : pointdata,
			dataType : 'text',
			error : function() {
			},
			success : function(msg) {
				history.go(0);
			}

		});
	} else {
		$.ajax( {
			url : '/action/savePointForm',
			data : pointdata,
			dataType : 'text',
			error : function() {
			},
			success : function(msg) {
				var cover = $("#alertBack"),
				shut = $(".winClose"),
				win = $("#alertWin");
				var cancel = $(".canclBtn");
				cover.hide();
				win.hide();
				$(".alertForm").hide();
				$("body").css("overflow","auto");
				history.go(0);
			}

		});
	}
	}  else if(type=="db"){
		var deptpoint="";
		$("select[name='dbselect']").each(function(){
			deptpoint += (deptpoint==""?"":",") + $(this).val();
		 });
        var dbdata = {
		    dbcode:$("#dbcode").val(),
            dbname:$("#dbname").val(),
            url:$("#url").val(),
			driver:$("#driver").val(),
			user:$("#user").val(),
			pwd:$("#pwd").val(),
			deptpoint:deptpoint
        };
	if("del"==del){
		$.ajax( {
			url : '/action/delDbForm',
			data : dbdata,
			dataType : 'text',
			error : function() {
			},
			success : function(msg) {
				history.go(0);
			}

		});
	} else {
		$.ajax( {
			url : '/action/saveDbForm',
			data : dbdata,
			dataType : 'text',
			error : function() {
			},
			success : function(msg) {
				var cover = $("#alertBack"),
				shut = $(".winClose"),
				win = $("#alertWin");
				var cancel = $(".canclBtn");
				cover.hide();
				win.hide();
				$(".alertForm").hide();
				$("body").css("overflow","auto");
				history.go(0);
			}

		});
	}
	}  else if(type=="role"){
		
        var roledata = {
		    level:$("#level").val(),
            rolename:$("#rolename").val(),
			memo:$("#rolememo").val()
        };
	if("del"==del){
		$.ajax( {
			url : '/action/delRoleForm',
			data : roledata,
			dataType : 'text',
			error : function() {
			},
			success : function(msg) {
				history.go(0);
			}
		});
	} else {
		$.ajax( {
			url : '/action/saveRoleForm',
			data : roledata,
			dataType : 'text',
			error : function() {
			},
			success : function(msg) {
				var cover = $("#alertBack"),
				shut = $(".winClose"),
				win = $("#alertWin");
				var cancel = $(".canclBtn");
				cover.hide();
				win.hide();
				$(".alertForm").hide();
				$("body").css("overflow","auto");
				history.go(0);
			}

		});
	}
	} else if(type=="user"){
		var deptpoint="";
		$("select[name='userselect']").each(function(){
			deptpoint += (deptpoint==""?"":",") + $(this).val();
		 });
		        var udata = {
				    username:$("#uname").val(),
		            password:$("#upass").val(),
					level:$("#uroles").val(),
					deptpoint:deptpoint
		        };
			if("del"==del){
				$.ajax( {
					url : '/action/delUserForm',
					data : udata,
					dataType : 'text',
					error : function() {
					},
					success : function(msg) {
						history.go(0);
					}
				});
			} else {
				$.ajax( {
					url : '/action/saveUserForm',
					data : udata,
					dataType : 'text',
					error : function() {
					},
					success : function(msg) {
						var cover = $("#alertBack"),
						shut = $(".winClose"),
						win = $("#alertWin");
						var cancel = $(".canclBtn");
						cover.hide();
						win.hide();
						$(".alertForm").hide();
						$("body").css("overflow","auto");
						history.go(0);
					}
				});
			}
		} else if(type=="bst"){
		        var bstdata = {
				    bscode:$("#bstcode").val(),
		            tablecode:$("#tablename").val(),
					tablename:$("#tablename").val(),
					tableoname:$("#tableoname").val()
		        };
				if("del"==del){
					$.ajax( {
						url : '/action/delBstForm',
						data : bstdata,
						dataType : 'text',
						error : function() {
						},
						success : function(msg) {
							history.go(0);
						}
					});
				} else {
					$.ajax( {
						url : '/action/saveBstForm',
						data : bstdata,
						dataType : 'text',
						error : function() {
						},
						success : function(msg) {
							var cover = $("#alertBack"),
							shut = $(".winClose"),
							win = $("#alertWin");
							var cancel = $(".canclBtn");
							cover.hide();
							win.hide();
							$(".alertForm").hide();
							$("body").css("overflow","auto");
							history.go(0);
						}
					});
				}
	}  else if(type=="mid"){
		        var middata = {
				    midtype:$("#midtype").val(),
		            midcenter:$("#midcenter").val(),
					midurl:$("#midurl").val(),
					miduser:$("#miduser").val(),
					midpwd:$("#midpwd").val()
		        };
				if("del"==del){
					$.ajax( {
						url : '/action/delmidForm',
						data : middata,
						dataType : 'text',
						error : function() {
						},
						success : function(msg) {
							history.go(0);
						}
					});
				} else {
					$.ajax( {
						url : '/action/savemidForm',
						data : middata,
						dataType : 'text',
						error : function() {
						},
						success : function(msg) {
							var cover = $("#alertBack"),
							shut = $(".winClose"),
							win = $("#alertWin");
							var cancel = $(".canclBtn");
							cover.hide();
							win.hide();
							$(".alertForm").hide();
							$("body").css("overflow","auto");
							history.go(0);
						}
					});
				}
			}   else if(type=="link"){
				        var linkdata = {
						    deptNo:$("#forthU_0").val(),
				            toDeptNo:$("#forthU_1").val(),
							port:$("#checkport").val()
				        };
						if("del"==del){
							$.ajax( {
								url : '/action/dellinkForm',
								data : linkdata,
								dataType : 'text',
								error : function() {
								},
								success : function(msg) {
									history.go(0);
								}
							});
						} else {
							$.ajax( {
								url : '/action/savelinkForm',
								data : linkdata,
								dataType : 'text',
								error : function() {
								},
								success : function(msg) {
									var cover = $("#alertBack"),
									shut = $(".winClose"),
									win = $("#alertWin");
									var cancel = $(".canclBtn");
									cover.hide();
									win.hide();
									$(".alertForm").hide();
									$("body").css("overflow","auto");
									history.go(0);
								}
							});
						}
						} 
}

function check(item){
	if(item=="bscode"){
		if($("#bscode").val()=="" ||$("#bscode").val()==null){
			$("#bscodemsg").html("业务域编码不能为空!");
			return;
		} else {
			$("#bscodemsg").html("√");
			return;
		}
	}
	if(item=="bsname"){
		if($("#bsname").val()=="" ||$("#bsname").val()==null){
			$("#bsnamemsg").html("业务域名称不能为空!");
			return;
		} else {
			$("#bsnamemsg").html("√");
			return;
		}
	}
	if(item=="deptNo"){
		if($("#deptNo").val()=="" ||$("#deptNo").val()==null){
			$("#deptNomsg").html("部门编码不能为空!");
			return;
		} else {
			$("#deptNomsg").html("√");
			return;
		}
	}
	if(item=="deptName"){
		if($("#deptName").val()=="" ||$("#deptName").val()==null){
			$("#deptNamemsg").html("部门名称不能为空!");
			return;
		} else {
			$("#deptNamemsg").html("√");
			return;
		}
	}
	if(item=="type"){
		if($("#type").val()!="0" && $("#type").val()!="1"&&$("#type").val()!="2"&&$("#type").val()!="3" && $("#type").val()!="4" &&$("#type").val()!="5"){
			selectCheckbox("deptcheck","");
			$("#deptbsq").css("display","none");
		} else {
			$("#deptbsq").css("display","table-row");
		}
	}
	
	// firstP_i,firstD_i,firstU_i
	var firstfront = item.substring(0,5);
	var firstfoot = item.substring(5,(item.length));
	
	if(firstfront=="first"){
		if($("#"+item).val()=="A"){
			$("#second"+firstfoot).css("display","none");
			$("#third"+firstfoot).css("display","none");
			$.ajax( {
				url : '/action/queryForth',
				data : '',
				dataType : 'text',
				error : function() {
				},
				success : function(msg) {
					var keyvalue = msg.split(",");
					$("#forth"+firstfoot).empty(); 
					for(var i=0;i<keyvalue.length;i++){
						var deptNo = keyvalue[i].split(":")[0];
						var deptName = keyvalue[i].split(":")[1];
						var option = $("<option>").val(deptNo).text(deptName); 
						$("#forth"+firstfoot).append(option);
					}
				}
			});
		} else if($("#"+item).val()=="B"){
			$("#second"+firstfoot).css("display","table-row");
			$("#third"+firstfoot).css("display","none");
			$("#forth"+firstfoot).empty(); 
			$.ajax( {
				url : '/action/querySecond',
				data : '',
				dataType : 'text',
				error : function() {
				},
				success : function(msg) {
					var keyvalue = msg.split(",");
					$("#second"+firstfoot).empty(); 
					for(var i=0;i<keyvalue.length;i++){
						var deptNo = keyvalue[i].split(":")[0];
						var deptName = keyvalue[i].split(":")[1];
						var option = $("<option>").val(deptNo).text(deptName); 
						$("#second"+firstfoot).append(option);
					}
					
					        var deptdata = {
							    deptNo:$("#second"+firstfoot).val()
					        };
							$.ajax( {
								url : '/action/querySecondForth',
								data : deptdata,
								dataType : 'text',
								error : function() {
								},
								success : function(msg) {
									var keyvalue = msg.split(",");
									$("#forth"+firstfoot).empty(); 
									for(var i=0;i<keyvalue.length;i++){
										var deptNo = keyvalue[i].split(":")[0];
										var deptName = keyvalue[i].split(":")[1];
										var option = $("<option>").val(deptNo).text(deptName); 
										$("#forth"+firstfoot).append(option);
									}
								}
							});
				}
			});
		} else {
			$("#second"+firstfoot).css("display","table-row");
			$("#third"+firstfoot).css("display","table-row");
			$("#forth"+firstfoot).empty(); 
			$.ajax( {
				url : '/action/querySecond',
				data : '',
				dataType : 'text',
				error : function() {
				},
				success : function(msg) {
					var keyvalue = msg.split(",");
					$("#second"+firstfoot).empty(); 
					for(var i=0;i<keyvalue.length;i++){
						var deptNo = keyvalue[i].split(":")[0];
						var deptName = keyvalue[i].split(":")[1];
						var option = $("<option>").val(deptNo).text(deptName); 
						$("#second"+firstfoot).append(option);
					}
					        var deptdata = {
							    deptNo:$("#second"+firstfoot).val()
					        };
							$.ajax( {
								url : '/action/querySecondThird',
								data : deptdata,
								dataType : 'text',
								error : function() {
								},
								success : function(msg) {
									var keyvalue = msg.split(",");
									$("#third"+firstfoot).empty(); 
									for(var i=0;i<keyvalue.length;i++){
										var deptNo = keyvalue[i].split(":")[0];
										var deptName = keyvalue[i].split(":")[1];
										var option = $("<option>").val(deptNo).text(deptName); 
										$("#third"+firstfoot).append(option);
									}
									        var deptdata2 = {
											    deptNo:$("#third"+firstfoot).val()
									        };
											$.ajax( {
												url : '/action/queryThirdForth',
												data : deptdata2,
												dataType : 'text',
												error : function() {
												},
												success : function(msg) {
													var keyvalue = msg.split(",");
													$("#forth"+firstfoot).empty(); 
													for(var i=0;i<keyvalue.length;i++){
														var deptNo = keyvalue[i].split(":")[0];
														var deptName = keyvalue[i].split(":")[1];
														var option = $("<option>").val(deptNo).text(deptName); 
														$("#forth"+firstfoot).append(option);
													}
													 
												}
											});
								}
							});
				}
			});
		}
	}
	
	var secondfront = item.substring(0,6);
	var secondfoot = item.substring(6,(item.length));
	if(secondfront=="second"){
		if($("#first"+secondfoot).val()=="B"){
		        var deptdata = {
				    deptNo:$("#second"+secondfoot).val()
		        };
				$.ajax( {
					url : '/action/querySecondForth',
					data : deptdata,
					dataType : 'text',
					error : function() {
					},
					success : function(msg) {
						var keyvalue = msg.split(",");
						$("#forth"+secondfoot).empty(); 
						for(var i=0;i<keyvalue.length;i++){
							var deptNo = keyvalue[i].split(":")[0];
							var deptName = keyvalue[i].split(":")[1];
							var option = $("<option>").val(deptNo).text(deptName); 
							$("#forth"+secondfoot).append(option);
						}
						 
					}
				});
		} else {	
					        var deptdata = {
							    deptNo:$("#second"+secondfoot).val()
					        };
							$.ajax( {
								url : '/action/querySecondThird',
								data : deptdata,
								dataType : 'text',
								error : function() {
								},
								success : function(msg) {
									var keyvalue = msg.split(",");
									$("#third"+secondfoot).empty(); 
									for(var i=0;i<keyvalue.length;i++){
										var deptNo = keyvalue[i].split(":")[0];
										var deptName = keyvalue[i].split(":")[1];
										var option = $("<option>").val(deptNo).text(deptName); 
										$("#third"+secondfoot).append(option);
									}
									 
									        var deptdata2 = {
											    deptNo:$("#third"+secondfoot).val()
									        };
											$.ajax( {
												url : '/action/queryThirdForth',
												data : deptdata2,
												dataType : 'text',
												error : function() {
												},
												success : function(msg) {
													var keyvalue = msg.split(",");
													$("#forth"+secondfoot).empty(); 
													for(var i=0;i<keyvalue.length;i++){
														var deptNo = keyvalue[i].split(":")[0];
														var deptName = keyvalue[i].split(":")[1];
														var option = $("<option>").val(deptNo).text(deptName); 
														$("#forth"+secondfoot).append(option);
													}
													 
												}
											});
								}
							});
		}
	}
	var thirdfront = item.substring(0,5);
	var thirdfoot = item.substring(5,(item.length));
	if(thirdfront=="third"){
		if($("#first"+thirdfoot).val()=="A"){

		} else if($("#first"+thirdfoot).val()=="B"){
		 
		} else {	
	        var deptdata2 = {
			    deptNo:$("#third"+thirdfoot).val()
	        };
			$.ajax( {
				url : '/action/queryThirdForth',
				data : deptdata2,
				dataType : 'text',
				error : function() {
				},
				success : function(msg) {
					var keyvalue = msg.split(",");
					$("#forth"+thirdfoot).empty(); 
					for(var i=0;i<keyvalue.length;i++){
						var deptNo = keyvalue[i].split(":")[0];
						var deptName = keyvalue[i].split(":")[1];
						var option = $("<option>").val(deptNo).text(deptName); 
						$("#forth"+thirdfoot).append(option);
					}
					 
				}
			});
		}
	}
	
	if(item=="userselect"){
	        var jibie = $("#userselect").val();
			if(jibie=="A"){
			var p = {
				depttype:"0"
			};
			$.ajax( {
				url : '/action/queryUDEPT',
				data : p,
				dataType : 'text',
				error : function() {
				},
				success : function(msg) {
					var keyvalue = msg.split(",");
					$("#userselectv").empty(); 
					for(var i=0;i<keyvalue.length;i++){
						var deptNo = keyvalue[i].split(":")[0];
						var deptName = keyvalue[i].split(":")[1];
						var option = $("<option>").val(deptNo).text(deptName); 
						$("#userselectv").append(option);
					}
					 
				}
			});
			} else if(jibie=="B"){
				var p = {
						depttype:"1"
					};
					$.ajax( {
						url : '/action/queryUDEPT',
						data : p,
						dataType : 'text',
						error : function() {
						},
						success : function(msg) {
							var keyvalue = msg.split(",");
							$("#userselectv").empty(); 
							for(var i=0;i<keyvalue.length;i++){
								var deptNo = keyvalue[i].split(":")[0];
								var deptName = keyvalue[i].split(":")[1];
								var option = $("<option>").val(deptNo).text(deptName); 
								$("#userselectv").append(option);
							}
							 
						}
					});
					} else if(jibie=="C"){
						var p = {
								depttype:"2"
							};
							$.ajax( {
								url : '/action/queryUDEPT',
								data : p,
								dataType : 'text',
								error : function() {
								},
								success : function(msg) {
									var keyvalue = msg.split(",");
									$("#userselectv").empty(); 
									for(var i=0;i<keyvalue.length;i++){
										var deptNo = keyvalue[i].split(":")[0];
										var deptName = keyvalue[i].split(":")[1];
										var option = $("<option>").val(deptNo).text(deptName); 
										$("#userselectv").append(option);
									}
									 
								}
							});
							}  else if(jibie=="D"){
								var p = {
										depttype:"3"
									};
									$.ajax( {
										url : '/action/queryUDEPT',
										data : p,
										dataType : 'text',
										error : function() {
										},
										success : function(msg) {
											var keyvalue = msg.split(",");
											$("#userselectv").empty(); 
											for(var i=0;i<keyvalue.length;i++){
												var deptNo = keyvalue[i].split(":")[0];
												var deptName = keyvalue[i].split(":")[1];
												var option = $("<option>").val(deptNo).text(deptName); 
												$("#userselectv").append(option);
											}
											 
										}
									});
									}  else if(jibie=="E"){
										var p = {
												depttype:"4"
											};
											$.ajax( {
												url : '/action/queryUDEPT',
												data : p,
												dataType : 'text',
												error : function() {
												},
												success : function(msg) {
													var keyvalue = msg.split(",");
													$("#userselectv").empty(); 
													for(var i=0;i<keyvalue.length;i++){
														var deptNo = keyvalue[i].split(":")[0];
														var deptName = keyvalue[i].split(":")[1];
														var option = $("<option>").val(deptNo).text(deptName); 
														$("#userselectv").append(option);
													}
												}
											});
											} else if(jibie=="F"){
												var p = {
														depttype:"5"
													};
													$.ajax( {
														url : '/action/queryUDEPT',
														data : p,
														dataType : 'text',
														error : function() {
														},
														success : function(msg) {
															var keyvalue = msg.split(",");
															$("#userselectv").empty(); 
															for(var i=0;i<keyvalue.length;i++){
																var deptNo = keyvalue[i].split(":")[0];
																var deptName = keyvalue[i].split(":")[1];
																var option = $("<option>").val(deptNo).text(deptName); 
																$("#userselectv").append(option);
															}
															 
														}
													});
													} 
	}
}

function selectCheckbox(name,value) {     
    var checkObject = document.getElementsByName(name);
    if(value!=""){
    var values = value.split(",");     
    for(var j = 0; j < values.length; j++)     
    {     
    	for (var i = 0; i < checkObject.length; i++)      
        {     
    		if(checkObject[i].value == values[j])     
            {     
                checkObject[i].checked = true;     
                continue;     
            }     
        }     
    }
    }
    if(value==""){
    	for (var i = 0; i < checkObject.length; i++)      
        {     
            checkObject[i].checked = false;     
        }
    }
}   
