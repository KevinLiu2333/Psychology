mini.namespace("ReadCardUtil");
var __LBReader__;
try {
	__LBReader__ = new ActiveXObject("LBActiveX.LBActiveX_ATL");
} catch (e) {}
//读社保卡
ReadCardUtil.ReadCPU = function(){
	try{
		//连接读卡器
		var port=0;
		__LBReader__.I_V1=0;
		__LBReader__.ReaderConnect();
		if(__LBReader__.O_ST != 0){
			__LBReader__.ReaderDisconnect();
			for(var i=1;i<=20;i++){
				__LBReader__.I_V1=i;
				__LBReader__.ReaderConnect();
				if(__LBReader__.O_ST == 0){
					port = i;
					break;
				} else {
					__LBReader__.ReaderDisconnect();
				}
			}
			if(port == 0){
				var data={};
				data.error="未连接读卡器";
				return data;
			}
		}
		var data={};
		//上电
		__LBReader__.CardPowerOn();
		if (__LBReader__.O_ST != 0){
			/**
			 * 对于50177返回值的修复，尝试读取一个字段，对于读取成功的，再次上电后继续做下去
			 */
			if(__LBReader__.O_ST == 50177){
				ReadCardUtil.ReadCpuContent("05020101");
				if(__LBReader__.O_ST != 0) {
					__LBReader__.CardPowerOff();
					__LBReader__.ReaderDisconnect();
					if(port>0){
						data.error="请插入社保卡";
					} else {
						data.error="请插入卡片";
					}
					return data;
				} else {
					__LBReader__.CardPowerOn();
					if(__LBReader__.O_V1){
						data.phidno=__LBReader__.O_V1;
					}
				}
			} else {
				__LBReader__.CardPowerOff();
				__LBReader__.ReaderDisconnect();
				if(port>0){
					data.error="请插入社保卡";
				} else {
					data.error="请插入卡片";
				}
				return data;
			}
			
		}  else {
			if(__LBReader__.O_V1){
				data.phidno=__LBReader__.O_V1;
			}
		}
		
		data.readCardType = ReadCardUtil.stringToAscii(ReadCardUtil.ReadCpuContent("05020101"));//卡类型 6 居住证 3 社保卡 2学籍卡
		data.idno = ReadCardUtil.stringToAscii(ReadCardUtil.ReadCpuContent("06080112"));//身份证
		data.fullname = ReadCardUtil.ReadCpuContent("0609001E");//姓名
		if(data.fullname){
			data.fullname = data.fullname.trim();
		}
		
		data.sex = ReadCardUtil.stringToAscii(ReadCardUtil.ReadCpuContent("060A0D01"));//性别
		data.nationality = ReadCardUtil.ReadCpuContent("060B0101");//民族
		
		if (data.readCardType=='6' || data.readCardType=='9'){
			data.birthday = ReadCardUtil.ReadCpuContent("060D0104");//出生日期
			data.cardno = ReadCardUtil.ReadCpuContent("05010110");//卡识别码
			data.lhregno = ReadCardUtil.ReadCpuContent("06E40109");//来沪登记号
			data.cedate = ReadCardUtil.ReadCpuContent("05050104");//发卡日期
			data.validdate = ReadCardUtil.ReadCpuContent("05060104");//卡有效期
			data.hjaddress = ReadCardUtil.ReadCpuContent("1521003C");//原户籍所在地址
			data.address = ReadCardUtil.ReadCpuContent("16250120");//居住地地址
			data.outcardno = ReadCardUtil.stringToAscii(ReadCardUtil.ReadCpuContent("05070109"));//外卡号
			data.kmzjno = ReadCardUtil.stringToAscii(ReadCardUtil.ReadCpuContent("05E3010D"));//卡面证件编号
		}else{
			data.birthday = ReadCardUtil.ReadCpuContent("060D0104");//出生日期
			data.cardno = ReadCardUtil.ReadCpuContent("05010110");//卡识别码
			data.regno = ReadCardUtil.stringToAscii(ReadCardUtil.ReadCpuContent("05070109"));//登记号
			data.version = ReadCardUtil.stringToAscii(ReadCardUtil.ReadCpuContent("05030104"));//规范版本
			data.ceorgan = ReadCardUtil.ReadCpuContent("0504010C");//初始化机构
			data.cedate = ReadCardUtil.ReadCpuContent("05050104");//发卡日期
			data.validdate = ReadCardUtil.ReadCpuContent("05060104");//卡有效期
			if(data.readCardType=='2'){
				data.birthplace = ReadCardUtil.ReadCpuContent("060C0103");//出生地
			}
			data.address = ReadCardUtil.ReadCpuContent("1625003E");//居住地地址
		}
		
		try{
			
			data.zipcode = ReadCardUtil.ReadCpuContent("16270103");//居住地邮编
			data.otherphoneno = ReadCardUtil.stringToAscii(ReadCardUtil.ReadCpuContent("1628010F"));//联系电话
			data.education = ReadCardUtil.ReadCpuContent("172A0101");//文化程度
			data.marriage = ReadCardUtil.stringToAscii(ReadCardUtil.ReadCpuContent("182B0101"));//婚姻状况
			
			data.matename = ReadCardUtil.ReadCpuContent("182C001E");//配偶姓名
			data.mateidno = ReadCardUtil.stringToAscii(ReadCardUtil.ReadCpuContent("182D0112"));//配偶身份证号
			data.psstatus = ReadCardUtil.stringToAscii(ReadCardUtil.ReadCpuContent("17290101"));//个人状态
			data.corpname = ReadCardUtil.ReadCpuContent("192E003E");//单位名称
			data.corpaddress = ReadCardUtil.ReadCpuContent("1933003E");//单位通讯地址
		}catch(e){}
		
		if(!data.idno) {
			data.error="未读取到卡内身份证号";
		}
		__LBReader__.CardPowerOff();//下电
		__LBReader__.ReaderDisconnect();//断开读卡器
		return data;
	}catch(e){
		var data={};
		if(!__LBReader__){
			data.error="未安装读卡控件";
		} else {
			data.error=e.message;
		}
		try{
			__LBReader__.CardPowerOff();
			__LBReader__.ReaderDisconnect();
		}catch(e){}
		return data;
	}
}

ReadCardUtil.ReadCpuContent = function(address){
	__LBReader__.I_V1 = address;
	__LBReader__.ReadContent();
	return __LBReader__.O_V1;
}

ReadCardUtil.stringToAscii = function(code){
	if(code){
		var val = "";
		for(var i=0;i<code.length;){
			val += String.fromCharCode(parseInt(code.substring(i,i+2),16));
			i += 2;
		}
		return val.trim();
	} else {
		return code;
	}
}

//读身份证
ReadCardUtil.ReadPIDWithPhoto = function(){
	try{
		//连接读卡器
		__LBReader__.ReaderConnect();
		if(__LBReader__.O_ST != 0){
			try {
				var data = ReadCardUtil.PTReadPIDWithPhoto();
				if(data.error && data.append){
					data.error = "未连接6合1读卡器/"+data.error;
				}
				return data;
			} catch (e) {
				var data={};
				data.error="未连接6合1读卡器";
				return data;
			}
		}
		//读卡
		__LBReader__.ReadPID();
	    if(__LBReader__.O_ST != 0){
			var data={};
			data.error="请放置身份证";
			return data;
		}
		var info_arry=__LBReader__.O_V1.split("|");
		var retData = {};
		retData.idno = info_arry[0];	//身份证号
		retData.fullname = info_arry[1];//姓名
		retData.sex = info_arry[2];		//性别
		retData.nationality = info_arry[3];//民族
		retData.birthday = info_arry[4];//生日
		retData.residence = info_arry[5];//户籍地址
		retData.ceorgan = info_arry[6];	//发卡机构
		retData.cedate = info_arry[7];	//发卡日期
		retData.validdate = info_arry[8];	//卡有效期
		try{
			retData.imgbase64 = __LBReader__.O_V2;	//照片
		} catch(e){
			
		}
		__LBReader__.ReaderDisconnect();//断开读卡器
		return retData;
	} catch(e){
		var data={};
		if(!__LBReader__){
			try {
				data = ReadCardUtil.PTReadPIDWithPhoto();
				if(data.error && data.append){
					data.error = "未安装6合1读卡控件/"+data.error;
				}
				return data;
			} catch(e){
				data.error="未安装6合1读卡控件";
			}
		} else {
			data.error=e.message;
		}
		try {
			__LBReader__.ReaderDisconnect();//断开读卡器
		} catch (e){}
		return data;
	}
}

//读取敬老卡
ReadCardUtil.ReadRepCard = function(){
	try{
		//连接读卡器
		__LBReader__.ReaderConnect();
		if(__LBReader__.O_ST != 0){
			var data={};
			data.error="未连接读卡器";
			return data;
		}
		//读卡
		__LBReader__.ReadSeniorCard();
	    if(__LBReader__.O_ST != 0){
			var data={};
			data.error="请放置敬老卡";
			return data;
		}
		var info_arry=__LBReader__.O_V1.split("|");
		var retData = {};
		retData.v1cardno = info_arry[0];//v1卡号
		retData.regno = info_arry[1];	//登记号
		retData.idno = info_arry[2];	//身份证号
		retData.validdate = info_arry[3];//卡有效期
		retData.issuedate = info_arry[4];//发行日期
		retData.birthday = info_arry[5];//生日
		retData.sex = info_arry[6];//性别
		retData.nationality = info_arry[7];//民族
		retData.fullname = info_arry[8];//姓名
		try{
			var traninfo = __LBReader__.O_V2;
			retData.ceorgan = traninfo.substring(0,18);	//初始化机构代码
			retData.tranvaliddate = traninfo.substring(18,22);	//交通卡有效期
			retData.usercode = traninfo.substring(22,26);	//登记人编号
			retData.dtczflag = traninfo.substring(26,28);	//地铁出站标志
			retData.cardno = traninfo.substring(28,36);	//卡号
		} catch(e){
			
		}
		__LBReader__.ReaderDisconnect();//断开读卡器
		return retData;
	} catch(e){
		var data={};
		if(!__LBReader__){
			data.error="未安装读卡控件";
		} else {
			data.error=e.message;
		}
		try {
			__LBReader__.ReaderDisconnect();//断开读卡器
		} catch (e){}
		return data;
	}
}

//读取新版银行卡
ReadCardUtil.ReadBoc = function(){
	try{
		//连接读卡器
		var port=0;
		__LBReader__.I_V1=0;
		__LBReader__.ReaderConnect();
		if(__LBReader__.O_ST != 0){
			__LBReader__.ReaderDisconnect();
			for(var i=1;i<=20;i++){
				__LBReader__.I_V1=i;
				__LBReader__.ReaderConnect();
				if(__LBReader__.O_ST == 0){
					port = i;
					break;
				} else {
					__LBReader__.ReaderDisconnect();
				}
			}
			if(__LBReader__.O_ST != 0){
				var data={};
				data.error="未连接读卡器";
				return data;
			}
		}
		
		
		//读卡
		__LBReader__.I_V1 = "0100";
		__LBReader__.ReadPBOCCard();
	    if(__LBReader__.O_ST != 0){
	    	__LBReader__.I_V1 = "0000";
	    	__LBReader__.ReadPBOCCard();
	    	if(__LBReader__.O_ST != 0){
				var data={};
				data.error="请放置/插入银行卡";
				return data;
	    	}
		}
	    
		var retData = {};
		retData.cardno = __LBReader__.O_V1.replace(/F/g,"");	//卡号
		__LBReader__.ReaderDisconnect();//断开读卡器
		return retData;
	} catch(e){
		var data={};
		if(!__LBReader__){
			data.error="未安装读卡控件";
		} else {
			data.error=e.message;
		}
		try {
			__LBReader__.ReaderDisconnect();//断开读卡器
		} catch (e){}
		return data;
	}
}