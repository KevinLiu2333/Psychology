mini.namespace("PrintCardUtil");
var __P330iPrinterX__;
try {
	__P330iPrinterX__ = new ActiveXObject("P330iPrinterXControl.P330iPrinterX");
} catch (e) {}

PrintCardUtil.PrintCards = function (datas) {
	var hPrinter = 0;
	var data={};
	data.error = "";
	if(!datas || !datas.length){
		data.error="传入数据为空";
		return data;
	}
	for(var i=0;i<datas.length;i++){
		if(!datas[i].idno||!datas[i].cardno||!datas[i].name||!datas[i].carddata){
			data.error="第"+(i+1)+"条记录传入数据不完整";
			return data;
		}
	}
	try {
		for(var i=0;i<datas.length;i++){
			var retval = __P330iPrinterX__.func_WriteCard_P330i(datas[i].name,datas[i].cardno,datas[i].idno,datas[i].carddata);
			if(retval != 0){
				data.error = __P330iPrinterX__.func_WriteCard_Error();
				return data;
			}
		}
	} catch(e){
		if(!__P330iPrinterX__){
			data.error="未安装P330i打印控件";
		} else {
			data.error=e.message;
		}
		return data;
	}
}