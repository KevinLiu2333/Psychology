/**
 * 字符串操作增强
 */

/**
 * 常用正则表达式
*/
var PATTERN_EMAIL="\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
var PATTERN_CHINESE_CHAR="[\u4e00-\u9fa5]";
var PATTERN_DOUBLEBYTE_CHAR="[^\x00-\xff]";
var PATTERN_UNSIGNED_INTEGER="^\\d+$";

/**
 * 去掉左边空格
*/
String.prototype.ltrim = function() { 
	return this.replace(/(^\s*)/g, ""); 
}


/**
 * 去掉右边空格
*/
String.prototype.rtrim = function() { 
	return this.replace(/(\s*$)/g, ""); 
}


/**
 * 去掉前后空格
*/
String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
}

/**
 * 得到左边的字符串
*/
String.prototype.left = function(len) {
	if(isNaN(len)||len==null) { 
		len = this.length; 
	} else { 
		if(parseInt(len)<0||parseInt(len)>this.length) { 
			len = this.length; 
		} 
	}
	return this.substr(0,len); 
}

/**
 * 得到右边的字符串
*/
String.prototype.right = function(len) {
	if(isNaN(len)||len==null) { 
		len = this.length; 
	} else { 
		if(parseInt(len)<0||parseInt(len)>this.length) { 
			len = this.length; 
		} 
	}
	return this.substring(this.length-len,this.length); 
}


/**
 * 得到中间的字符串,注意从0开始 
*/
String.prototype.mid = function(start,len) { 
	return this.substr(start,len); 
}

/**
 * 判断是否是IP地址
*/
String.prototype.isIP = function() {
	var reSpaceCheck = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/;
	if (reSpaceCheck.test(this)) { 
		this.match(reSpaceCheck); 
		if (RegExp.$1 <= 255 && RegExp.$1 >= 0 
				&& RegExp.$2 <= 255 && RegExp.$2 >= 0 
				&& RegExp.$3 <= 255 && RegExp.$3 >= 0 
				&& RegExp.$4 <= 255 && RegExp.$4 >= 0) { 
			return true; 
		} else { 
			return false; 
		} 
	} else { 
		return false; 
	}

}


/**
 * 判断是否是手机号码
*/
String.prototype.isMobile = function() { 
	return /^0{0,1}13[0-9]{9}$/.test(this); 
}

/**
 * 判断是否是邮件地址
*/
String.prototype.isEmail = function() { 
	return /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(this); 
}

/**
 * 判断是否是邮编
*/
String.prototype.isZipCode = function() { 
	return /^[\\d]{6}$/.test(this); 
}

/**
 * 判断是否有汉字
*/
String.prototype.existChinese = function() { 
	//[\u4E00-\u9FA5]為漢字﹐[\uFE30-\uFFA0]為全角符號 
	return /^[\x00-\xff]*$/.test(this); 
}

/**
 * 字节长度
*/
String.prototype.lengthb = function(){
	return this.replace(/[^\x00-\xff]/g,"**").length;
}

/**
 * 对字符串进行Html编码，方便显示
*/
String.prototype.toHtmlEncode = function() { 
	var str = this;
	str=str.replace(/&/g,"&"); 
	str=str.replace(/</g,"<"); 
	str=str.replace(/>/g,">"); 
	str=str.replace(/\'/g,"&apos;"); 
	str=str.replace(/\"/g,"&quot;"); 
	str=str.replace(/\n/g,"<br>"); 
	str=str.replace(/\ /g," "); 
	str=str.replace(/\t/g,"    ");
	return str; 
}