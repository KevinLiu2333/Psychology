

//顶部导航
function ontop(selfObj){
	var ul=document.getElementById("top_navigation").getElementsByTagName("li");
	var ullength = ul.length;
	for(i=0; i<ullength; i++){
		ul[i].className = "";
	}
	    selfObj.parentNode.className = "selected";
}

function outtop(selfObj){
	var ul=document.getElementById("top_navigation").getElementsByTagName("li");
	var ullength = ul.length;
	for(i=0; i<ullength; i++){
		ul[i].className = "";
	}
	    selfObj.parentNode.className = "";
}


var objBanner;
var objCtrl;
var objMenu;
var objFooter;
var objContent;
var objWidth;
var objWidth2;
//左菜单
function showHideBar() {
objWidth2 = parseInt(document.getElementById("ctrl").offsetWidth);
if (parseInt(objMenu.style.width) != 0) {
   objWidth = parseInt(document.getElementById("menu").offsetWidth);
   objMenu.style.width = "0px";
   objMenu.style.display = "none";
   objCtrl.style.left = objMenu.style.width;
   objContent.style.left = parseInt(objMenu.style.width) + objWidth2 + "px";
   objContent.style.width = parseInt(document.body.offsetWidth) - parseInt(document.getElementById("menu").offsetWidth) - parseInt(document.getElementById("ctrl").offsetWidth) + "px";
   objCtrl.className = "ctrl_visibility";
}
else {
   objMenu.style.width = objWidth + "px";
   objCtrl.style.left = objWidth + "px";
   objMenu.style.display = "block";
   objContent.style.left = objWidth + objWidth2 + "px";
   objCtrl.className = "ctrl_hidden";
objContent.style.width = parseInt(document.body.offsetWidth) - parseInt(document.getElementById("menu").offsetWidth) - parseInt(document.getElementById("ctrl").offsetWidth) + "px"; }
}
//布局高宽
function initSideBar() {
objBanner = document.getElementById("banner");
     objCtrl = document.getElementById("ctrl");
     objMenu = document.getElementById("menu");
	 objFooter = document.getElementById("footer");
objContent = document.getElementById("content");objMenu.style.height = parseInt(document.body.offsetHeight) - parseInt(document.getElementById("banner").offsetHeight) - parseInt(document.getElementById("footer").offsetHeight) + "px";

objCtrl.style.height = parseInt(document.body.offsetHeight) - parseInt(document.getElementById("banner").offsetHeight)  - parseInt(document.getElementById("footer").offsetHeight) + "px";

objContent.style.height = parseInt(document.body.offsetHeight) - parseInt(document.getElementById("banner").offsetHeight)  - parseInt(document.getElementById("footer").offsetHeight) + "px";

objContent.style.width = parseInt(document.body.offsetWidth) - parseInt(document.getElementById("menu").offsetWidth) - parseInt(document.getElementById("ctrl").offsetWidth) + "px";
}

//布局模式选择
function Select_Mode(selfObj){
	var mode=document.getElementById("layout_mode").getElementsByTagName("li");
	var mode_length = mode.length;
	for(i=0; i<mode_length; i++){
		mode[i].className = "";
	}
	    selfObj.className = "selected";
}


//复选框全选效果
function CheckAll(obj)
{
for (var j = 0; j <= 9; j++) {
box = eval("document.getElementById('allbox').k" + j); 
if (obj.checked == true){
	
	box.checked = true;
	}

else box.checked = false;
   }
}


//结果列表全选checkbox
function checkAll(objName){
	var obj = document.getElementsByName(objName);
	for (var i=0;i<obj.length;i++){
		obj.item(i).checked = event.srcElement.checked;
	}
}


//checkbox单选控制

function onlyCheckOne(){
//单选checkbox
var elementGroup = event.srcElement.parentElement.getElementsByTagName("INPUT");
	for (var i=0;i<elementGroup.length;i++){
		if (elementGroup.item(i)!=event.srcElement 
				&& elementGroup.item(i).type=="checkbox" 
				&& elementGroup.item(i).name== event.srcElement.name ){
			elementGroup.item(i).checked = false;
		}
	}
}

//显示/隐藏层
function show_table(i){
 var show_h=document.getElementById("hid"+i);	
 if(show_h.style.display==""){
	 show_h.style.display="none";
	 }
 else {
	 show_h.style.display="";
	 }
}
	
//鼠标滑过表格标题效果
function col_on(i) {
	var col=document.getElementById("col"+i);
	col.style.backgroundColor="#f2f6f9";
}
	
function col_out(i) {
	var col=document.getElementById("col"+i);
	col.style.backgroundColor="";
}
	
	
//	
var a=1;	
function hid() {
	var tagaa = document.getElementById("tagss").getElementsByTagName("li");
	var taglengthaa = tagaa.length-1;
	for(i=0; i<taglengthaa; i++){
		if(i>=2){
			tagaa[i].style.display="none";
			}
	}
	a=2;
	}	
function show(){
	var tagbb = document.getElementById("tagss").getElementsByTagName("li");
	var taglengthbb = tagbb.length-1;
	if(a==2){
		for(i=0; i<taglengthbb; i++){
				tagbb[i].style.display="block";
		}
		a=a+1
		
	}
	else{
	hid();
	}	
	}

//表格效果
function col_ons(m,n) {
	var col=document.getElementById("col"+m+n);
	col.style.backgroundColor="#F7FCCF";
	}
	
function col_outs(m,n) {
	var col=document.getElementById("col"+m+n);
	col.style.backgroundColor="";
	}	
	

function show_deli(s){
var de=document.getElementById("deli"+s);
	if(de.style.display=='none'){
		de.style.display="block";
		}else { de.style.display="none";}
	}


function clo(i){
	var sel=document.getElementById("all"+i);
	var imgs=document.getElementById("img"+i);
	if(i==0){
		for(var j=1;j<=6;j++){
		document.getElementById("all"+j).style.display ="none";
		document.getElementById("img"+j).innerHTML="<img src='../../resource/images/icon/icon_jj.gif' width='9' height='9' />";
		}
	}
	if(sel.style.display =="none"){	
		sel.style.display="block";
		imgs.innerHTML="<img src='../../resource/images/icon/icon_j.gif' width='9' height='9' />";
	}else{
		sel.style.display ="none";
		imgs.innerHTML="<img src='../../resource/images/icon/icon_jj.gif' width='9' height='9' />";
	}
}



//选项卡效果
function secBoard(showContent,selfObj){
	// 操作标签
	var ulboard=document.getElementById("secTable").getElementsByTagName("li");
	var ulboardlength = ulboard.length;
	for(i=0; i<ulboardlength; i++){
		ulboard[i].className = "";
	}
	selfObj.parentNode.className = "changnow";
	// 操作内容	
	for(i=0; j=document.getElementById("changtable"+i); i++){
		j.style.display = "none";
	}
	document.getElementById(showContent).style.display = "block";	
}

/**全屏打开一个新窗口*/
function openAllscreenwin(winURL, name)
{
	var Allscreenwin = window.open(winURL,name,"height=720,width=1015,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no");
	Allscreenwin.resizeTo(screen.availWidth, screen.availHeight);
}

/** 居中打开一个新窗口，高度，宽度自定义*/

function openWindowInCenter(winURL,height,width, name)
{
	var top = (800 - height)/2;
	var left = (1259 - width)/2;
	var InCenterwin = window.open(winURL,name,"height=" + height + ",width=" + width + ",top=" + top + ",left=" + left + ",toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no");

}




//避免页面出现科学计数法

// 编辑页面替换查询并替换全部显示的input输入框显示内容
function init(){
	var objFloat = document.getElementsByTagName("input");
	for(var i=0;i<objFloat.length;i++){			
			var objValue = objFloat[i].value;
			if (objValue.indexOf('.0E-4') > -1 ){			
				var subValue=parseFloat(objValue.substring(0,objValue.indexOf('.0E-4')),10);					
				objFloat[i].value = accMul(subValue,0.0001);
			}		
	}	
}

// 列表显示页面查询并替换全部显示内容

function initSingleTable(){
	var objTable = document.getElementById("dataTable");	
	var objTr = objTable.rows;
	
	for( var i = 0 ; i < objTr.length && objTr.length > 0 ; i++){
		var objTd = objTr[i].cells;
		for( var j = 0 ; j < objTd.length && objTd.length > 0 ; j++){
			var tdValue = objTd[j].innerHTML;
			if (tdValue.indexOf('.0E-4') > -1){
				var newValue = parseFloat(tdValue.substring(0,tdValue.indexOf('.0E-4')),10);					
				objTd[j].innerHTML = accMul(newValue,0.0001);
			}
		}
	}	
}
// 页面多个table数据区替换
function initFloatData(){
	var objTable,objTr,objTd;	
	objTable = document.all("dataTable");		
    if (objTable != null) {
		if(objTable.length == undefined){			
		// 只有一张数据表
			objTr = objTable.rows;	
			for( var i = 0 ; i < objTr.length && objTr.length > 0 ; i++){
				objTd = objTr[i].cells;
				for( var j = 0 ; j < objTd.length && objTd.length > 0 ; j++){
					var tdValue = objTd[j].innerHTML;
					if (tdValue.indexOf('.0E-4') > -1){
						var newValue = parseFloat(tdValue.substring(0,tdValue.indexOf('.0E-4')),10);					
						objTd[j].innerHTML = accMul(newValue,0.0001);
					}
				}
			}	
			
		}else{
		// 多数据表		
			for(var k = 0; k <objTable.length ; k++){
				objTr = objTable[k].rows;				
				for( var i = 0 ; i < objTr.length && objTr.length > 0 ; i++){
					objTd = objTr[i].cells;
					for( var j = 0 ; j < objTd.length && objTd.length > 0 ; j++){
						var tdValue = objTd[j].innerHTML;
						if (tdValue.indexOf('.0E-4') > -1){
							var newValue = parseFloat(tdValue.substring(0,tdValue.indexOf('.0E-4')),10);					
							objTd[j].innerHTML = accMul(newValue,0.0001);
						}
					}
				}
			}		
		}    
	}
}	


//乘法函数，用来得到精确的乘法结果
//说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
//调用：accMul(arg1,arg2)
//返回值：arg1乘以arg2的精确结果
function accMul(arg1,arg2)
{
	if(!isNaN(arg1) && !isNaN(arg2)){
		arg1=String(arg1);var i=arg1.length-arg1.indexOf(".")-1;i=(i>=arg1.length)?0:i
		arg2=String(arg2);var j=arg2.length-arg2.indexOf(".")-1;j=(j>=arg2.length)?0:j
		return (arg1.replace(".","")*arg2.replace(".","")/Math.pow(10,i+j)).toFixed(4)
	}else return 0
}

//加法函数，用来得到精确的加法结果
//说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
//调用：accAdd(arg1,arg2)
//返回值：arg1加上arg2的精确结果
function accAdd(arg1,arg2,num){
	if(accAdd.arguments.length < 3) num = 4;
	if(!isNaN(arg1) && !isNaN(arg2)){
		var r1,r2,m;
		try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
		try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
		m=Math.pow(10,Math.max(r1,r2))
		return ((arg1*m+arg2*m)/m) .toFixed(num)
	}else return 0
}

//减法函数，用来得到精确的加法结果
//说明：javascript的减法结果会有误差，在两个浮点数相减的时候会比较明显。这个函数返回较为精确的减法结果。
//调用：accSub(arg1,arg2)
//返回值：arg1加上arg2的精确结果
function accSub(arg1,arg2){
	if(!isNaN(arg1) && !isNaN(arg2)){
		var r1,r2,m;
		try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
		try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
		m=Math.pow(10,Math.max(r1,r2))
		return ((arg1*m-arg2*m)/m).toFixed(4)
	}else return 0
}

//除法函数，用来得到精确的除法结果
//说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
//调用：accDiv(arg1,arg2)
//返回值：arg1除以arg2的精确结果
function accDiv(arg1,arg2){
	if(!isNaN(arg1) && !isNaN(arg2)){
		var t1=0,t2=0,r1,r2;
		try{t1=arg1.toString().split(".")[1].length}catch(e){}
		try{t2=arg2.toString().split(".")[1].length}catch(e){}
		with(Math){
			r1=Number(arg1.toString().replace(".",""))
			r2=Number(arg2.toString().replace(".",""))
			return ((r1/r2)*pow(10,t2-t1)).toFixed(4);
		}
	}else return 0;
}

	// 承诺告知书页面高度
	function initDivHeight(divId){
		var divHeight;
		divHeight=document.body.offsetHeight-164;
		document.getElementById(divId).style.height=divHeight;
	}

	/**
	* 字体闪烁颜色变化脚本
	*参数说明：objId，需要变色的区域id
	*调用方式：页面加载onload方式或直接调用均可
	*/
	function blinklink()
	{   
		var obj = document.getElementById('phoneNum');
		
		if(!obj || obj == null) return;
		if (!(obj.style.color))   
		{   
			obj.style.color="red"  
		}   
		if (obj.style.color=="red")   
		{   
			obj.style.color='#ffffff'  
		}   
		else  
		{   
			obj.style.color="red"  
		}   
			timer=setTimeout("blinklink('phoneNum')",1000)   
	}   
  
	function stoptimer()   
	{   
		clearTimeout(timer)   
	} 
	
function toggleLogo() {
	var logo = document.getElementById('header_top');
	var menu = document.getElementById('menu');
	var ctrl = document.getElementById('ctrl');
	var content = document.getElementById('content');
	var toggle = document.getElementById('toggle');
	if (logo.style.display == 'block') {
		logo.style.display = 'none';
		menu.style.top = '35px';
		menu.style.bottom = '0px';
		menu.style.height = '520px';
		content.style.top = '35px';
		content.style.bottom = '0px';
		content.style.height = '520px';
		ctrl.style.top = '10px';
		ctrl.style.bottom = '0px';
		ctrl.style.height = '520px';
		toggle.innerText = '↓↓';
	} else if (logo.style.display == 'none') {
		logo.style.display = 'block';
		menu.style.top = '128px';
		menu.style.height = '425px';
		content.style.top = '128px';
		content.style.height = '425px';
		ctrl.style.top = '128px';
		ctrl.style.height = '425px';
		toggle.innerText = '↑↑';
	}
}
