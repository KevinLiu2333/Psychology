/**  
*功能:Cookies处理
*/  
function Cookie(pName,pExpiredInSeconds,pDocument,pDomain) {
	this.$name = pName;
	
	if(arguments.length<3){
		pDocument=document;
	}
	this.$document = pDocument;
	if(arguments.length<2){
		pExpiredInSeconds=0;
	}
	this.$expiration = pExpiredInSeconds;
	if(this.$expiration>0){
		var date=new Date();
		date.setTime(date.getTime()+1000*this.$expiration);
		this.$expire_date=date;
	}else{
		this.$expire_date=new Date(2999,12,31);
	}
	if(arguments.length<4){	
		pDomain=getRootDomain(pDocument);
	}	
	this.$domain = pDomain;
	this.data = null;
}

//Cookie对象需要一个方法来存储临时数据，比如用户名这些，并将这些数据赋值给 Cookie 的 data属性
Cookie.prototype.setVals = function (d) {
	if (this.data == null) {
		this.data = [];
		this.data[0] = [d];
	} else {
		this.data[0][0] = d[0];
	}
};

//Cookie对象可拥有的成员方法 -- 存储cookies
Cookie.prototype.store = function () {
	var cookieval = "";
	if (this.data != null) {
		for (var i = 0; i < this.data.length; i++) {
			cookieval += this.data[i].join(":") + "&";
		}
	}
	if (cookieval != "" && cookieval.charAt(cookieval.length - 1) == "&") {
		cookieval = cookieval.substring(0, cookieval.length - 1);
	}
	var	cookie = this.$name + "=" + cookieval+ ";expires=" + this.$expire_date.toGMTString()+";path=/";
	this.$document.cookie = cookie;
};

Cookie.prototype.remove = function () {
	var date=new Date();
	date.setTime(date.getTime()-1000);
	var cookie = this.$name + "=;expires=" + date.toGMTString()+";path=/";
	this.$document.cookie = cookie;
}

//Cookie对象可拥有的成员方法 -- 加载cookies
Cookie.prototype.load = function () {
	var allcookies = this.$document.cookie;
	if (allcookies == "") {
		return false;
	}
	var start = allcookies.indexOf(this.$name + "=");
	if (start == -1) {
		return false;
	}
	start += this.$name.length + 1;
	var end = allcookies.indexOf(";", start);
	if (end == -1) {
		end = allcookies.length;
	}
	var cookieval = allcookies.substring(start, end);
	var a = cookieval.split("&");
	for (var i = 0; i < a.length; i++) {
		a[i] = a[i].split(":");
	}
	this.data = a;
	return true;
};

//获取当前域
function getRootDomain(pDocument) {
	if(arguments.length<1){
		pDocument=document;
	}	
	var ss = pDocument.domain;
	try {
		var ii = ss.lastIndexOf(".");
		if (ii > 0) {
			if (!isNaN(ss.substr(ii + 1) * 1)) {
				return ss;
			}
			ii = ss.lastIndexOf(".", ii - 1);
			if (ii > 0) {
				return ss.substr(ii + 1);
			}
		}
	}
	catch (exp) {
	}
	return ss;
}

