mini.namespace("ReadCardUtil");
var __PortForReader__ = "COM1";
var __xSSC4SH__,__pReader__;
try {
	__xSSC4SH__ = new ActiveXObject("xSSC4SH.SSC4SH");
	__pReader__ = new ActiveXObject("工程1.pReader");
} catch (e) {
	
}

//读社保卡
ReadCardUtil.ReadCPU = function(){
	try{
		__PortForReader__ = __pReader__.CheckPort(__PortForReader__);
		if(!__PortForReader__){
			var data={};
			data.error="未连接读卡器";
			return data;
		}
		var ret = __xSSC4SH__.ReaderConnect(__PortForReader__);//连接读卡器
		ret = __xSSC4SH__.CardPowerOn();//上电
		if ( ret <=0 ){
			__xSSC4SH__.ReaderDisconnect();
			return null;
		}
		var data={};
		data.readCardType = __xSSC4SH__.ReadContent(0x05020901);//卡类型 6 居住证 3 社保卡 2学籍卡
		data.idno = __xSSC4SH__.ReadContent(0x06080512);//身份证
		data.fullname = __xSSC4SH__.ReadContent(0x0609091E);//姓名
		data.sex = __xSSC4SH__.ReadContent(0x060A0D01);//性别
		data.nationality = __xSSC4SH__.ReadContent(0x060B1201);//民族
		if (data.readCardType=='6'){
			data.birthday = __xSSC4SH__.ReadContent(0x060D1604);//出生日期
		}else{
			data.birthday = __xSSC4SH__.ReadContent(0x060D1A04);//出生日期
		}
		if (data.readCardType=='3'){
			data.cardno = __xSSC4SH__.ReadContent(0x05071D09);
		}
		
		try{
			__xSSC4SH__.SelectContentArea(1);
			data.address = __xSSC4SH__.ReadContent(0x1625053E);//居住地地址
			data.zipcode = __xSSC4SH__.ReadContent(0x16270E03);//居住地邮编
			data.otherphoneno = __xSSC4SH__.ReadContent(0x1628110F);//联系电话
			data.education = __xSSC4SH__.ReadContent(0x172A0A01);//文化程度
			data.marriage = __xSSC4SH__.ReadContent(0x182B0501);//婚姻状况
		}catch(e){
			
		}
		for(var key in data){
			if ( data[key]=='NA') delete data[key];
		}
		__xSSC4SH__.CardPowerOff();//下电
		__xSSC4SH__.ReaderDisconnect();//断开读卡器

		if(data.idno==null || data.idno=='' || data.idno=='null') {
			return null;
		}
		return data;
	}catch(e){
		var data={};
		if(!__pReader__||!__xSSC4SH__){
			data.error="未安装读卡控件";
		} else {
			data.error=e.message;
		}
		try{
			__xSSC4SH__.CardPowerOff();
			__xSSC4SH__.ReaderDisconnect();
		}catch(e){}
		return data;
	}
} 

//读身份证
ReadCardUtil.ReadPIDWithPhoto = function(){
	try{
		__PortForReader__ = __pReader__.CheckPort(__PortForReader__);
		if(!__PortForReader__){
			var data={};
			data.error="未连接读卡器";
			return data;
		}
		var data = __pReader__.ReadPid(__PortForReader__);//连接读卡器
		if(data==null || data=='' || data=='null') {
			return null;
		}
		var info_arry=data.split("|");
		var retData = {};
		retData.idno = info_arry[0];
		retData.fullname = info_arry[1];
		retData.sex = info_arry[2];
		retData.nationality = info_arry[3];
		retData.birthday = info_arry[4];
		retData.residence = info_arry[5];
		try{
			retData.imgbase64 = info_arry[9];
		}catch(e){
			
		}
		return retData;
	} catch(e){
		var data={};
		if(!__pReader__||!__xSSC4SH__){
			data.error="未安装读卡控件";
		} else {
			data.error=e.message;
		}
		return data;
	}
} 
