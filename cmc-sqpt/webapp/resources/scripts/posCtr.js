mini.namespace("PosCtrUtil");
var __PosCtr__;
try {
	__PosCtr__ = new ActiveXObject("PosCtrXControl.PosCtr");
} catch (e) {}

//处理出参
PosCtrUtil.DealRetval = function (retval){
	var cardtraninfo = new Object();
	try{
	if(retval){
		var info_arry=retval.split("<|>");
		cardtraninfo.operatetype = info_arry[0];
		cardtraninfo.transtype = info_arry[1];
		cardtraninfo.cardtype = info_arry[2];
		cardtraninfo.responsecode = info_arry[3];
		cardtraninfo.responsemsg = info_arry[4];
		cardtraninfo.cashregno = info_arry[5];
		cardtraninfo.casherno = info_arry[6];
		cardtraninfo.amount = info_arry[7];
		cardtraninfo.settlenum = info_arry[8];
		cardtraninfo.merchantid = info_arry[9];
		cardtraninfo.merchantname = info_arry[10];
		cardtraninfo.terminalid = info_arry[11];
		cardtraninfo.cardno = info_arry[12];
		cardtraninfo.expdate = info_arry[13];
		cardtraninfo.bankno = info_arry[14];
		cardtraninfo.transdate = info_arry[15];
		cardtraninfo.transtime = info_arry[16];
		cardtraninfo.authcode = info_arry[17];
		cardtraninfo.sysrefno = info_arry[18];
		cardtraninfo.cashtraceno = info_arry[19];
		cardtraninfo.origintrace = info_arry[20];
		cardtraninfo.systraceno = info_arry[21];
		cardtraninfo.originsystrace = info_arry[22];
		cardtraninfo.reserved = info_arry[23];
	} else {
		cardtraninfo.responsecode = 'FF';
		cardtraninfo.responsemsg = '未获得返回信息';
	}
	} catch(e){
		cardtraninfo.responsecode = 'FE';
		cardtraninfo.responsemsg = '返回信息处理有误:'+e.message;
	}
	return cardtraninfo;
}

//查询卡结算情况
PosCtrUtil.QueryBalance = function() {
	var cardtraninfo = new Object();
	try {
		var retval = __PosCtr__.queryBalance();
		alert(retval);
		cardtraninfo = PosCtrUtil.DealRetval(retval);
		return cardtraninfo;
	} catch(e){
		if(!__PosCtr__){
			cardtraninfo.responsecode = 'FD';
			cardtraninfo.responsemsg="未安装Pos控件";
		} else {
			cardtraninfo.responsecode = 'FD';
			cardtraninfo.responsemsg=e.message;
		}
		return cardtraninfo;
	}
}
//签到
PosCtrUtil.Signin = function() {
	var cardtraninfo = new Object();
	try {
		var retval = __PosCtr__.signin();
		cardtraninfo = PosCtrUtil.DealRetval(retval);
		return cardtraninfo;
	} catch(e){
		if(!__PosCtr__){
			cardtraninfo.responsecode = 'FD';
			cardtraninfo.responsemsg="未安装Pos控件";
		} else {
			cardtraninfo.responsecode = 'FD';
			cardtraninfo.responsemsg=e.message;
		}
		return cardtraninfo;
	}
}

//结算
PosCtrUtil.Balance = function() {
	var cardtraninfo = new Object();
	try {
		var retval = __PosCtr__.balance();
		cardtraninfo = PosCtrUtil.DealRetval(retval);
		return cardtraninfo;
	} catch(e){
		if(!__PosCtr__){
			cardtraninfo.responsecode = 'FD';
			cardtraninfo.responsemsg="未安装Pos控件";
		} else {
			cardtraninfo.responsecode = 'FD';
			cardtraninfo.responsemsg=e.message;
		}
		return cardtraninfo;
	}
}

//消费
PosCtrUtil.Pay = function(ije) {
	var cardtraninfo = new Object();
	var je = parseFloat(ije);
	if(!je || je<0){
		cardtraninfo.responsecode = 'FC';
		cardtraninfo.responsemsg="金额非法";
		return;
	}
	
	try {
		var retval = __PosCtr__.pay(je);
		cardtraninfo = PosCtrUtil.DealRetval(retval);
		return cardtraninfo;
	} catch(e){
		if(!__PosCtr__){
			cardtraninfo.responsecode = 'FD';
			cardtraninfo.responsemsg="未安装Pos控件";
		} else {
			cardtraninfo.responsecode = 'FD';
			cardtraninfo.responsemsg=e.message;
		}
		return cardtraninfo;
	}
}

//撤销
PosCtrUtil.Cancel = function(oje,lsh) {
	var cardtraninfo = new Object();
	var je = parseFloat(oje);
	if(!je || je<0){
		cardtraninfo.responsecode = 'FC';
		cardtraninfo.responsemsg="退回金额非法";
		return;
	}
	if(!lsh){
		cardtraninfo.responsecode = 'FC';
		cardtraninfo.responsemsg="退回流水号非法";
		return;
	}
	
	try {
		var retval = __PosCtr__.cancel(je,lsh);
		cardtraninfo = PosCtrUtil.DealRetval(retval);
		return cardtraninfo;
	} catch(e){
		if(!__PosCtr__){
			cardtraninfo.responsecode = 'FD';
			cardtraninfo.responsemsg="未安装Pos控件";
		} else {
			cardtraninfo.responsecode = 'FD';
			cardtraninfo.responsemsg=e.message;
		}
		return cardtraninfo;
	}
}

//读卡
PosCtrUtil.ReadCard = function() {
	var cardtraninfo = new Object();
	try {
		var retval = __PosCtr__.readCard();
		cardtraninfo = PosCtrUtil.DealRetval(retval);
		return cardtraninfo;
	} catch(e){
		if(!__PosCtr__){
			cardtraninfo.responsecode = 'FD';
			cardtraninfo.responsemsg="未安装Pos控件";
		} else {
			cardtraninfo.responsecode = 'FD';
			cardtraninfo.responsemsg=e.message;
		}
		return cardtraninfo;
	}
}

//保存交易记录
PosCtrUtil.SavePosMsg = function(cardtraninfo,balanceid,posid) {
	if(!cardtraninfo && !operatetype.operatetype){
		mini.alert("交易记录传入为空");
		return;
	}
	
	if(balanceid){
		cardtraninfo.balanceid = balanceid;
	}
	
	if(posid){
		cardtraninfo.posid = posid;
	}
	
	MiniUtils.maskwin("保存交易记录中...");
	MiniUtils.request({
		url : __CONTEXT_PATH__+"/m31/m310701/m310701!saveposmsg.action", //action地址
		data : cardtraninfo,//提交参数
		success : function(result) {//成功回调
			MiniUtils.unmaskwin();
		},
		error:function(){
			MiniUtils.unmaskwin();
		}
	});
	
	
}