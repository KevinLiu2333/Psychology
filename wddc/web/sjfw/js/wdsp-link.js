//URL链接
function downAppFile(busId,fwInfoId){
	location.href=$("#js_ctx").val()+"/kernel/file/fileDownByBusId?catalog=09&busId="+busId+"&fwInfoId="+fwInfoId;
}
//服务申请
function toFwUsed(fwInfoId){
	$.ajax({
		type:"post",
		async:false,
		url:$("#js_ctx").val()+"/fw/apply/fwInfoData?fwInfoId="+fwInfoId,
		success:function(data){
			if(data.fwInfo.fwTypeCode == "Biaozhun1"){
				location.href = $("#js_ctx").val()+"/kernel/file/fileDownByBusId?catalog=09&busId="+data.fwInfo.fileId+"&fwInfoId="+fwInfoId;
			}else if(data.fwInfo.fwTypeCode == "Waibu2"){
				location.href = data.fwInfo.configUrl;
			}else if(data.fwInfo.fwTypeCode == "Chaxun1"){
				location.href = data.fwInfo.configUrl;
			}else if(data.fwInfo.fwTypeCode == "Chaxun2"){
				location.href = $("#js_ctx").val()+"/kernel/file/fileDownByBusId?catalog=09&busId="+data.fwInfo.fileId+"&fwInfoId="+fwInfoId;
			}else if(data.fwInfo.fwTypeCode == "Gis"){
				location.href = data.fwInfo.configUrl;
			}else {
				location.href = $("#js_ctx").val()+"/fw/apply/toApplyUsed?fwInfoId="+fwInfoId;
			}
		
		}
		});
}
//服务主页
function toFw(){
	window.location.href=$("#js_ctx").val()+"/fw/toIndex";
}
//资源主页
function toZy(tagId){
	window.location.href=$("#js_ctx").val()+"/zy/toIndex?tagId="+tagId;
}
//运营主页
function toYy(){
	window.location.href=$("#js_ctx").val()+"/main/toIndex";
}
//服务申请
function toFwApply(fwInfoId){
	window.location.href=$("#js_ctx").val()+"/fw/toApplyUsed?fwInfoId="+fwInfoId;
}
//服务申请查看
function toFwApplyView(fwApplyId){
	window.open($("#js_ctx").val()+"/fw/apply/toFwsqFinish?fwApplyId="+fwApplyId);
}
//服务调用日志信息
function toFwLog(){
	window.location.href=$("#js_ctx").val()+"/merit/toFwrzList";
}
//系统错误日志信息
function toErrorLog(){
	window.location.href=$("#js_ctx").val()+"/merit/toErrorLogList";
}
//登入登出日志
function toLoginLog(){
    window.location.href=$("#js_ctx").val()+"/merit/toLoginLogList";
}
//平台操作日志
function toOpLog(){
    window.location.href=$("#js_ctx").val()+"/merit/toLogOpList";
}
function logCount(){
    $.ajax({
        type:"post",
        async:false,
        url:$("#js_ctx").val()+"/merit/logCount",
        success:function(data){
            $("#usedCount").html(data.usedCount);
            $("#errorCount").html(data.errorCount);
            $("#loginCount").html(data.loginCount);
            $("#opCount").html(data.opCount);
        }
    });
}


function fwCount(elementId){
	$.ajax({
	type:"post",
	async:false,
	url:$("#js_ctx").val()+"/merit/fwCount",
	success:function(data){
	$("#"+elementId).html(data.fwCount);
	}
	});
}
function fwUsedCount(elementId,usedCount){
	$.ajax({
	type:"post",
	async:false,
	url:$("#js_ctx").val()+"/fw/apply/fwCount",
	success:function(data){
	$("#"+elementId).html(data.fwCount);
	$("#noApply").html(parseInt(data.fwCount)-parseInt(usedCount));
	
	}
	});
}

function zyCount(elementId){
	$.ajax({
	type:"post",
	async:false,
	url:$("#js_ctx").val()+"/merit/zyCount",
	success:function(data){
	$("#"+elementId).html(data.zyCount);
	}
	});
}
function userCount(){
	$.ajax({
	type:"post",
	async:false,
	url:$("#js_ctx").val()+"/merit/userCount",
	success:function(data){
	$("#usedCount").html(data.usedCount);
	$("#loginCount").html(data.loginCount);
	$("#zyCount").html(data.zyCount);
	$("#applyCount").html(data.applyCount);
	$("#unitLevel").html(data.userUnit.unitLevel);
	$("#unitKey").html(data.userUnit.unitKey);
	$("#unitName").html(data.userUnit.unitName);
	}
	});
}

