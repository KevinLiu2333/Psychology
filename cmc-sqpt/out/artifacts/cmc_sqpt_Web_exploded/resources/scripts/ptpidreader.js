mini.namespace("ReadCardUtil");
var __PTPidReader__;
try {
	__PTPidReader__ = new ActiveXObject("FIRSTACTIVEX.FirstActivexCtrl.1");
} catch (e) {}


//读身份证
ReadCardUtil.PTReadPIDWithPhoto = function(){
	try{
		if(!__PTPidReader__){
			var data={};
			data.append=true;
			data.error="未安装普天阅读器安装程序";
			return data;
		} else {
			__PTPidReader__ = document.getElementById("ptpidread");
		}
		
		
		__PTPidReader__.Flag=0;
		var rst = __PTPidReader__.ReadCard();
	    if(0x90==rst) {
			var retData = {};
			retData.idno = __PTPidReader__.CardNo();	//身份证号
			retData.fullname = __PTPidReader__.NameL();//姓名
			retData.sex = __PTPidReader__.Sex();//性别
			retData.nationality = __PTPidReader__.Nation();//民族
			var lenBorn=__PTPidReader__.Born();
			retData.birthday = lenBorn.substring(0,4)+"-"+lenBorn.substring(4,6)+"-"+lenBorn.substring(6);//生日
			retData.residence = __PTPidReader__.Address();//户籍地址
			retData.ceorgan = __PTPidReader__.Police();	//发卡机构
			retData.cedate = __PTPidReader__.ActivityL().substring(0,10);	//发卡日期
			retData.validdate = __PTPidReader__.ActivityL().substring(11);	//卡有效期
			try{
				retData.imgbase64 = __PTPidReader__.GetImage();	//照片
			} catch(e){
				
			}
			return retData;
	    } else if(0x41==rst) {
	    	var data={};
			data.error="请放置身份证";
			return data;
	    } else {
	    	var data={};
	    	data.append=true;
			data.error="未连接普天阅读器";
			return data;
	    } 
	} catch(e){
		var data={};
		data.error=e.message;
		return data;
	}
}