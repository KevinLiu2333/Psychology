/**===================================================
* version:  1.0.0
* date:     2009-12-29
* Directions:
*        这里主要是js对数学计算的一个工具类，目前提供的功能为
*			1.浮点数加法运算
*	 		2.浮点数减法运算
*			3.浮点数乘法运算
*			4.浮点数除法运算
*			5.浮点数的四舍五入
* Remarks:	目前提供的功能比较少，有可能在开发的过程中会遇到很多不
*			一样的功能，待以后开发过程中总结、归纳、以及更新。
*
* 使用方法: alert(NumberUtils.floatAdd(1.232, 2.342342));
===================================================**/
var NumberUtils= function(){};
/**
 * 浮点数加法运算   arg1+arg2
 * @param arg1 数值1
 * @param arg2 数值2
 * @return 加法结果
 */
NumberUtils.floatAdd = function(arg1,arg2){
	var r1,r2,m;  
	try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}  
	try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}  
	m=Math.pow(10,Math.max(r1,r2))  
	return (arg1*m+arg2*m)/m 
}
/**
 * 浮点数减法运算   arg1-arg2
 * @param arg1 数值1
 * @param arg2 数值2
 * @return 减法结果
 */
NumberUtils.floatSub = function(arg1,arg2){  
	 var r1,r2,m,n;  
	 try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}  
	 try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}  
	 m=Math.pow(10,Math.max(r1,r2));  
	 //动态控制精度长度  
	 n=(r1>=r2)?r1:r2;  
	 return ((arg1*m-arg2*m)/m).toFixed(n);  
}
/**
 * 浮点数乘法运算  arg1*arg2
 * @param arg1 数值1
 * @param arg2 数值2
 * @return 乘法结果
 */
NumberUtils.floatMul = function(arg1,arg2){   
	var m=0,s1=arg1.toString(),s2=arg2.toString();   
	try{m+=s1.split(".")[1].length}catch(e){}   
	try{m+=s2.split(".")[1].length}catch(e){}   
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)   
}
/**
 * 浮点数除法运算  arg1/arg2
 * @param arg1 被除数
 * @param arg2 除数
 * @return 除法结果
 */
NumberUtils.floatDiv = function(arg1,arg2){   
	var t1=0,t2=0,r1,r2;   
	try{
		t1=arg1.toString().split(".")[1].length
	}catch(e){}   
	try{
		t2=arg2.toString().split(".")[1].length
	}catch(e){}   
	with(Math){   
		r1=Number(arg1.toString().replace(".",""))   
		r2=Number(arg2.toString().replace(".",""))   
		return (r1/r2)*pow(10,t2-t1);   
	} 
}
/**
 * 四舍五入的方法
 * @param value 为要被格式化的数值
 * @param length 保留几位
 * @return 格式化之后的数值 
 */
NumberUtils.round = function(value,length){
	return(Math.round(value*Math.pow(10,length))*Math.pow(10,-length));
}