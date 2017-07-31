/***
*** 日期时间类工具类
*** author:xieguojun
**/

/**
***  获取
***/
function getSystemDate(now){

	return parseDate(now);			
}
function getWeekByDate(dateStr, format) {
	var weekDay = ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
	var myDate = parseDate(dateStr, format);
	return weekDay[myDate.getDay()];
}
//计算年龄
function caculatePsnlAge(birth,serverdate,format) {
	if (birth==null || birth=="") {
		alert("人员出生日期未登记。");
		return -1;
	}
	
	if (serverdate==null || serverdate=="") {
		alert("获取服务器时间失败。");
		return -1;
	}
	
	var oldDate=new Date(Date.parseDate(birth,format));
	var newDate=new Date(Date.parseDate(serverdate,format));
	
	var result=0;
	
	result=newDate.getFullYear()-oldDate.getFullYear()-1;
	if(newDate.getMonth()>oldDate.getMonth()) {
		result+=1;
	}else if(newDate.getMonth()==oldDate.getMonth() && newDate.getDate()>oldDate.getDate()){
		result+=1;
	}
	
	return result;
}

/**
** 获取业务月度
**/
function getYYYYMM(now){
	var date =getSystemDate(now);
	
	var result ='';
	var month =date.getMonth();
	 
	result +=date.getYear();
	
	if(month.length==2)
		result +=month;
	else
		result +="0"+month;
	
	return result;	
}


/** 
**  将dateStr日期字符串转换为javascript日期
**   dateStr:日期字符串
**	 format:日期格式，默认值为Y-m-d H:i:s
**/
function parseDate(dateStr,format){
	
	if(typeof(dateStr) !='undefined'){
		if(typeof(format) !='undefined')
			return Date.parseDate(dateStr,format);
		else
			return Date.parseDate(dateStr,'Y-m-d H:i:s');	
	}
}


mini.namespace("DateUtil");
/*获取各个时间项的值 首个参数为时间字符串*/
function ___MyDate(sDate, sFmt) {
	var pos;
	var len;
	var myFmt;
	var adjustflag = 0;
	if (sFmt) {
		myFmt = sFmt;
	} else {
		myFmt = ""; //无格式，等价于返回当前时间
	}
	this.yearPos = myFmt.indexOf("yyyy"); //年份起始位置
	this.yearLen = 4; //年份长度
	this.yearAdjust = 0;//年份格式调整标志
	if (this.yearPos > -1) {
		this.firstPos = this.yearPos;
	} //记录有效时间在字符串中最早的位置
	if (this.yearPos > -1) {
		this.endPos = this.yearPos + this.yearLen - 1;
	}//记录有效时间在字符串中最后的位置
	pos = myFmt.indexOf("MM");
	if (pos == -1) {
		pos = myFmt.indexOf("M");
		if (pos > -1) {
			var vMonth = parseInt(sDate.substr(pos, 2), 10);
			if (vMonth <= 12 && vMonth >= 10) {
				myFmt = myFmt.replace("M", "MM"); //自动转为长格式
				len = 2;
				adjustflag = 1;
			} else {
				len = 1;
			}
		} else {
			len = 0;
		}
	} else {
		len = 2;
	}
	this.monthPos = pos;//月份起始位置
	this.monthLen = len;//月份长度
	this.monthAdjust = adjustflag;//月份格式调整标志
	if (this.monthPos > -1 && this.monthPos < this.firstPos) {
		this.firstPos = this.monthPos;
	}
	if (this.monthPos > -1 && (this.monthPos + this.monthLen - 1 > this.endPos)) {
		this.endPos = this.monthPos + this.monthLen - 1;
	}
	pos = myFmt.indexOf("dd");
	if (pos == -1) {
		pos = myFmt.indexOf("d");
		if (pos > -1) {
			var vDay = parseInt(sDate.substr(pos, 2), 10);
			if (vDay <= 31 && vDay >= 10) {
				myFmt = myFmt.replace("d", "dd"); //自动转为长格式
				len = 2;
				adjustflag = 1;
			} else {
				len = 1;
			}
		} else {
			len = 0;
		}
	} else {
		len = 2;
	}
	this.dayPos = pos;//天数起始位置
	this.dayLen = len;//天数长度
	this.dayAdjust = adjustflag; //天数格式调整标志
	if (this.dayPos > -1 && this.dayPos < this.firstPos) {
		this.firstPos = this.dayPos;
	}
	if (this.dayPos > -1 && (this.dayPos + this.dayLen - 1 > this.endPos)) {
		this.endPos = this.dayPos + this.dayLen - 1;
	}
	this.hourPos = myFmt.indexOf("hh"); //小时起始位置
	this.hourLen = 2;//小时长度
	this.hourAdjust = 0;//小时格式调整标志
	if (this.hourPos > -1 && this.hourPos < this.firstPos) {
		this.firstPos = this.hourPos;
	}
	if (this.hourPos > -1 && (this.hourPos + this.hourLen - 1 > this.endPos)) {
		this.endPos = this.hourPos + this.hourLen - 1;
	}
	this.minutePos = myFmt.indexOf("mm");//分起始位置
	this.minuteLen = 2;//分长度
	this.minuteAdjust = 0;//分格式调整标志
	if (this.minutePos > -1 && this.minutePos < this.firstPos) {
		this.firstPos = this.minutePos;
	}
	if (this.minutePos > -1 && (this.minutePos + this.minuteLen - 1 > this.endPos)) {
		this.endPos = this.minutePos + this.minuteLen - 1;
	}
	this.secondPos = myFmt.indexOf("ss"); //秒起始位置
	this.secondLen = 2; //秒长度
	this.secondAdjust = 0;//秒格式调整标志
	if (this.secondPos > -1 && this.secondPos < this.firstPos) {
		this.firstPos = this.secondPos;
	}
	if (this.secondPos > -1 && (this.secondPos + this.secondLen - 1 > this.endPos)) {
		this.endPos = this.secondPos + this.secondLen - 1;
	}
	this.testPos = -1; //判断用位
	var dDate = new Date();
	this.year = (this.yearPos == -1) ? 2000 : parseInt(sDate.substr(this.yearPos, this.yearLen), 10);
	this.month = (this.monthPos == -1) ? 0 : (parseInt(sDate.substr(this.monthPos, this.monthLen), 10) - 1);
	this.day = (this.dayPos == -1) ? 1 : parseInt(sDate.substr(this.dayPos, this.dayLen), 10);
	this.hour = (this.hourPos == -1) ? 0 : parseInt(sDate.substr(this.hourPos, 2), 10);
	this.minute = (this.minutePos == -1) ? 0 : parseInt(sDate.substr(this.minutePos, 2), 10);
	this.second = (this.secondPos == -1) ? 0 : parseInt(sDate.substr(this.secondPos, 2), 10);
	this.dateFormat = myFmt; //记下实际格式
	this.dateValue = new Date(this.year, this.month, this.day, this.hour, this.minute, this.second);//Date类型的值
	this.month = this.month + 1; //月份为0-11编码，加1用于后面计算
	this.getValueCtrl = function (iPos) {//获取指定位置的格式控制对象
		var posArray = new Array();
		var i, j, num;
		num = 6;
		posArray[0] = "year";
		posArray[1] = "month";
		posArray[2] = "day";
		posArray[3] = "hour";
		posArray[4] = "minute";
		posArray[5] = "second";
		posArray[6] = "test";
		/*允许非正常时间顺序，排序下，主要用于移位*/
		for (i = 0; i < num; ) {//先删掉没有的对象
			var vpos1 = eval("this." + posArray[i] + "Pos");
			if (vpos1 == -1) {
				for (j = i; j < num; j++) {
					posArray[j] = posArray[j + 1];
				}
				num = num - 1;
			} else {
				i++;
			}
		}
		for (i = 0; i < num; i++) {//排序
			var vpos1 = eval("this." + posArray[i] + "Pos");
			for (j = i + 1; j < num; j++) {
				var vpos2 = eval("this." + posArray[j] + "Pos");
				if (vpos2 < vpos1) {
					var temp;
					temp = posArray[i];
					posArray[i] = posArray[j];
					posArray[j] = temp;
					vpos1 = eval("this." + posArray[i] + "Pos");
				}
			}
		}
		posArray[num] = "";
		var lastpos, nextpos;//下个位置和前个位置
		for (i = 0; i < num; i++) {//取到当前位置
			var posmin = eval("this." + posArray[i] + "Pos");
			var posmax = posmin + eval("this." + posArray[i] + "Len");
			if (iPos >= posmin && iPos < posmax) {
				lastpos = iPos - 1;
				if (lastpos < 0) {
					lastpos = 0;
				}
				if (lastpos < posmin) {
					if (i == 0) {
						lastpos = posmin;
					} else {
						lastpos = eval("this." + posArray[i - 1] + "Pos") + eval("this." + posArray[i - 1] + "Len") - 1;
					}
				}
				nextpos = iPos + 1;
				if (nextpos >= posmax) {
					if (posArray[i + 1] == "") {
						nextpos = posmax - 1;
					} else {
						nextpos = eval("this." + posArray[i + 1] + "Pos");
					}
				}
				break;
			}
		}
		if (i == num) {//不在范围内
			return "ERR";
		}
		var posdef = posArray[i];
		var maxvalue;
		var minvalue;
		if (posdef == "year") {
			maxvalue = 9999;
			minvalue = 1000;
		}
		if (posdef == "month") {
			maxvalue = 12;
			minvalue = 1;
		}
		if (posdef == "day") {
			var tempMonth = this.month;
			if (tempMonth == 4 || tempMonth == 6 || tempMonth == 9 || tempMonth == 11) {
				maxvalue = 30;
			} else {
				if (tempMonth == 2) {
					if ((this.year % 4 == 0 && this.year % 100 != 0) || this.year % 400 == 0) {
						maxvalue = 29;
					} else {
						maxvalue = 28;
					}
				} else {
					maxvalue = 31;
				}
			}
			minvalue = 1;
		}
		if (posdef == "hour") {
			maxvalue = 23;
			minvalue = 0;
		}
		if (posdef == "minute") {
			maxvalue = 59;
			minvalue = 0;
		}
		if (posdef == "second") {
			maxvalue = 59;
			minvalue = 0;
		}
		var next2;
		if (i != num - 1) {
			next2 = eval("this." + posArray[i + 1] + "Pos");
		} else {
			next2 = -1;	//已到最后个区域，可跳出控件
		}
		return new DateValueCtrl(maxvalue, minvalue, Math.pow(10, eval("this." + posdef + "Len") - 1 - (iPos - eval("this." + posdef + "Pos"))), eval("this." + posdef), lastpos, nextpos, eval("this." + posdef + "Pos"), eval("this." + posdef + "Len"), eval("this." + posdef + "Adjust"), next2);
	};
}
function DateValueCtrl(maxvalue, minvalue, addradix, basevalue, lastpos, nextpos, pos, len, adjust, next2) {
	this.maxValue = maxvalue;//当前区域最小值
	this.minValue = minvalue;//当前区域最大值
	this.addRadix = addradix; //当前位置乘法指数
	this.baseValue = basevalue;//当前区域原值
	this.lastPos = lastpos; //光标下个位置
	this.nextPos = nextpos; //光标前个位置
	this.Pos = pos; //当前区域起始位置
	this.Len = len; //当前区域长度
	this.Adjust = adjust;//是否进行了格式调整，是的情况下要考虑格式转换
	this.nexPos2 = next2;//下个区域
}

DateUtil.Format = function (sDate, tFmt, sFmt) {
	var dDate;
	var result;
	if (sDate) {
		if (sFmt) {
			var myDate = new ___MyDate(sDate, sFmt);
			dDate = myDate.dateValue;
		} else {
			dDate = sDate;
		}
	} else {
		dDate = new Date();
	}
	result = tFmt;
	result = result.replace("yyyy", dDate.getFullYear());//认为年份总能满足4位，不做判断
	if (tFmt.indexOf("MM") == -1) {
		result = result.replace("M", dDate.getMonth() + 1);
	} else {
		var vMonth = dDate.getMonth() + 1;
		result = result.replace("MM", vMonth < 10 ? ("0" + vMonth) : vMonth);
	}
	if (tFmt.indexOf("dd") == -1) {
		result = result.replace("d", dDate.getDate());
	} else {
		var vDay = dDate.getDate();
		result = result.replace("dd", vDay < 10 ? ("0" + vDay) : vDay);
	}
	var vHour = dDate.getHours();
	var vMinute = dDate.getMinutes();
	var vSecond = dDate.getSeconds();
	result = result.replace("hh", vHour < 10 ? ("0" + vHour) : vHour);
	result = result.replace("mm", vMinute < 10 ? ("0" + vMinute) : vMinute);
	result = result.replace("ss", vSecond < 10 ? ("0" + vSecond) : vSecond);
	return result;
};

/*时间增减函数，分别为年，月，日的增减
*sDate ：源时间，为空时为当前时间

*addValue：增减值，可负，必须

*sFmt：源时间格式，可选，为空时sDate将作为Date类型处理，否则将按指定格式解析sDate,此参数同时影响返回值格式

*返回值：与源时间同类型的运算后的时间
*/
DateUtil.Add = function (sDate, addValue, sFmt, func) {//基础方法
	var dDate;
	if (sDate) {
		if (sFmt) {
			var myDate = new ___MyDate(sDate, sFmt);
			dDate = myDate.dateValue;
		} else {
			dDate = sDate;
		}
	} else {
		dDate = new Date();
	}
	eval("dDate.set" + func + "(dDate.get" + func + "()+addValue)"); //动态调用

	if (sFmt) {
		return DateUtil.Format(dDate, sFmt);
	} else {
		return dDate;
	}
};
//年份加减
DateUtil.AddYear = function (sDate, addValue, sFmt) {
	return DateUtil.Add(sDate, addValue, sFmt, "FullYear");
};
//月份加减
DateUtil.AddMonth = function (sDate, addValue, sFmt) {
	return DateUtil.Add(sDate, addValue, sFmt, "Month");
};
//天数加减
DateUtil.AddDate = function (sDate, addValue, sFmt) {
	return DateUtil.Add(sDate, addValue, sFmt, "Date");
};

//相隔月数，以年和月为依据，无视天数值

DateUtil.Month_Between = function (sDate, eDate, sFmt) {
	var dDateF;
	var dDateE;
	if (sDate) {
		if (sFmt) {
			var myDate = new ___MyDate(sDate, sFmt);
			dDateF = myDate.dateValue;
		} else {
			dDateF = sDate;
		}
	} else {
		dDateF = new Date();
	}
	if (eDate) {
		if (sFmt) {
			var myDate = new ___MyDate(eDate, sFmt);
			dDateE = myDate.dateValue;
		} else {
			dDateE = sDate;
		}
	} else {
		dDateE = new Date();
	}
	var yearf, monthf;
	var yeare, monthe;
	yearf = dDateF.getFullYear();
	monthf = dDateF.getMonth();
	yeare = dDateE.getFullYear();
	monthe = dDateE.getMonth();
	return (yeare * 12 + monthe)-(yearf * 12 + monthf);
};
/*获取指定月两头日期

*第一个参数为日期型，返回计算后的值*/
DateUtil.getFirstDate = function (dDate) {
	dDate.setDate(1);
	return dDate;
};
DateUtil.getEndDate = function (dDate) {
	var vYear = dDate.getFullYear();
	var vMonth = dDate.getMonth() + 1;
	var iMaxDay = 0;
	if (vMonth == 2) {
		if ((vYear % 4 == 0 && vYear % 100 != 0) || vYear % 400 == 0) {
			iMaxDay = 29;
		} else {
			iMaxDay = 28;
		}
	} else {
		if (vMonth == 4 || vMonth == 6 || vMonth == 9 || vMonth == 11) {
			iMaxDay = 30;
		} else {
			iMaxDay = 31;
		}
	}
	dDate.setDate(iMaxDay);
	return dDate;
};

