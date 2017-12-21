function allPrpos ( obj ) {
	// 用来保存所有的属性名称和值
	var props = "" ;
	// 开始遍历
	for ( var p in obj ){ // 方法
		if ( typeof ( obj [ p ]) == " function " ){ obj [ p ]() ;
		} else { // p 为属性名称，obj[p]为对应属性的值
			props += p + " = " + obj [ p ] + " /t " ;
		} } // 最后显示所有的属性
	alert ( props ) ;
}
function curentTime()
{
	var now = new Date();

	var year = now.getFullYear();       //年
	var month = now.getMonth() + 1;     //月
	var day = now.getDate();            //日

	var hh = now.getHours();            //时
	var mm = now.getMinutes();          //分

	var clock = year + "-";

	if(month < 10)
		clock += "0";

	clock += month + "-";

	if(day < 10)
		clock += "0";

	clock += day + " ";

	if(hh < 10)
		clock += "0";

	clock += hh + ":";
	if (mm < 10) clock += '0';
	clock += mm;
	return(clock);
}

function curentTime(date)
{
	var now = date;

	var year = now.getFullYear();       //年
	var month = now.getMonth() + 1;     //月
	var day = now.getDate();            //日

	var hh = now.getHours();            //时
	var mm = now.getMinutes();          //分

	var clock = year + "-";

	if(month < 10)
		clock += "0";

	clock += month + "-";

	if(day < 10)
		clock += "0";

	clock += day + " ";
	return(clock);
}