/**
 * utf8编码，用于一些页面参数传递
 */

function EncodeUtf8(s1){
	var s = escape(s1);
	var sa = s.split("%");
	var retV ="";
	if(sa[0] != ""){
		 retV = sa[0];
	}
	for(var i = 1; i < sa.length; i ++){
		if(sa[i].substring(0,1) == "u"){
			 retV += __Hex2Utf8__(__Str2Hex__(sa[i].substring(1,5)));
			 retV += sa[i].substring(5);
		} else {
			retV += "%" + sa[i];
		}
	}
	return retV;
}
function __Str2Hex__(s){
	var c = "";
	var n;
	var ss = "0123456789ABCDEF";
	var digS = "";
	for(var i = 0; i < s.length; i ++){
		c = s.charAt(i);
		n = ss.indexOf(c);
		digS += __Dec2Dig__(eval(n));
	}
	return digS;
}
function __Dec2Dig__(n1){
	var s = "";
	var n2 = 0;
	for(var i = 0; i < 4; i++){
		n2 = Math.pow(2,3 - i);
		if(n1 >= n2) {
				s += '1';
				n1 = n1 - n2;
		}else{
			s += '0';
		}
	}
	return s;
}
function __Dig2Dec__(s){
	var retV = 0;
	if(s.length == 4){
		for(var i = 0; i < 4; i ++){
			retV += eval(s.charAt(i)) * Math.pow(2, 3 - i);
		}
		return retV;
	}
	return -1;
} 
function __Hex2Utf8__(s){
	 var retS = "";
	 var tempS = "";
	 var ss = "";
	 if(s.length == 16){
		 tempS = "1110" + s.substring(0, 4);
		 tempS += "10" +	s.substring(4, 10); 
		 tempS += "10" + s.substring(10,16); 
		 var sss = "0123456789ABCDEF";
		 for(var i = 0; i < 3; i ++)
		 {
				retS += "%";
				ss = tempS.substring(i * 8, (eval(i)+1)*8);

				retS += sss.charAt(__Dig2Dec__(ss.substring(0,4)));
				retS += sss.charAt(__Dig2Dec__(ss.substring(4,8)));
		 }
		 return retS;
	 }
	 return "";
}