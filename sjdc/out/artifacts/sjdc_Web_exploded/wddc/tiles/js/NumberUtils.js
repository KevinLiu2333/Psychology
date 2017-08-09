/**===================================================
* version:  1.0.0
* date:     2009-12-29
* Directions:
*        ������Ҫ��js����ѧ�����һ�������࣬Ŀǰ�ṩ�Ĺ���Ϊ
*			1.�������ӷ�����
*	 		2.��������������
*			3.�������˷�����
*			4.��������������
*			5.����������������
* Remarks:	Ŀǰ�ṩ�Ĺ��ܱȽ��٣��п����ڿ����Ĺ����л������ܶ಻
*			һ���Ĺ��ܣ����Ժ󿪷��������ܽᡢ���ɡ��Լ����¡�
*
* ʹ�÷���: alert(NumberUtils.floatAdd(1.232, 2.342342));
===================================================**/
var NumberUtils= function(){};
/**
 * �������ӷ�����   arg1+arg2
 * @param arg1 ��ֵ1
 * @param arg2 ��ֵ2
 * @return �ӷ����
 */
NumberUtils.floatAdd = function(arg1,arg2){
	var r1,r2,m;  
	try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}  
	try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}  
	m=Math.pow(10,Math.max(r1,r2))  
	return (arg1*m+arg2*m)/m 
}
/**
 * ��������������   arg1-arg2
 * @param arg1 ��ֵ1
 * @param arg2 ��ֵ2
 * @return �������
 */
NumberUtils.floatSub = function(arg1,arg2){  
	 var r1,r2,m,n;  
	 try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}  
	 try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}  
	 m=Math.pow(10,Math.max(r1,r2));  
	 //��̬���ƾ��ȳ���  
	 n=(r1>=r2)?r1:r2;  
	 return ((arg1*m-arg2*m)/m).toFixed(n);  
}
/**
 * �������˷�����  arg1*arg2
 * @param arg1 ��ֵ1
 * @param arg2 ��ֵ2
 * @return �˷����
 */
NumberUtils.floatMul = function(arg1,arg2){   
	var m=0,s1=arg1.toString(),s2=arg2.toString();   
	try{m+=s1.split(".")[1].length}catch(e){}   
	try{m+=s2.split(".")[1].length}catch(e){}   
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)   
}
/**
 * ��������������  arg1/arg2
 * @param arg1 ������
 * @param arg2 ����
 * @return �������
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
 * ��������ķ���
 * @param value ΪҪ����ʽ������ֵ
 * @param length ������λ
 * @return ��ʽ��֮�����ֵ 
 */
NumberUtils.round = function(value,length){
	return(Math.round(value*Math.pow(10,length))*Math.pow(10,-length));
}