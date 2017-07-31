

//��������
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
//��˵�
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
//���ָ߿�
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

//����ģʽѡ��
function Select_Mode(selfObj){
	var mode=document.getElementById("layout_mode").getElementsByTagName("li");
	var mode_length = mode.length;
	for(i=0; i<mode_length; i++){
		mode[i].className = "";
	}
	    selfObj.className = "selected";
}


//��ѡ��ȫѡЧ��
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


//����б�ȫѡcheckbox
function checkAll(objName){
	var obj = document.getElementsByName(objName);
	for (var i=0;i<obj.length;i++){
		obj.item(i).checked = event.srcElement.checked;
	}
}


//checkbox��ѡ����

function onlyCheckOne(){
//��ѡcheckbox
var elementGroup = event.srcElement.parentElement.getElementsByTagName("INPUT");
	for (var i=0;i<elementGroup.length;i++){
		if (elementGroup.item(i)!=event.srcElement 
				&& elementGroup.item(i).type=="checkbox" 
				&& elementGroup.item(i).name== event.srcElement.name ){
			elementGroup.item(i).checked = false;
		}
	}
}

//��ʾ/���ز�
function show_table(i){
 var show_h=document.getElementById("hid"+i);	
 if(show_h.style.display==""){
	 show_h.style.display="none";
	 }
 else {
	 show_h.style.display="";
	 }
}
	
//��껬�������Ч��
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

//���Ч��
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



//ѡ�Ч��
function secBoard(showContent,selfObj){
	// ������ǩ
	var ulboard=document.getElementById("secTable").getElementsByTagName("li");
	var ulboardlength = ulboard.length;
	for(i=0; i<ulboardlength; i++){
		ulboard[i].className = "";
	}
	selfObj.parentNode.className = "changnow";
	// ��������	
	for(i=0; j=document.getElementById("changtable"+i); i++){
		j.style.display = "none";
	}
	document.getElementById(showContent).style.display = "block";	
}

/**ȫ����һ���´���*/
function openAllscreenwin(winURL, name)
{
	var Allscreenwin = window.open(winURL,name,"height=720,width=1015,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no");
	Allscreenwin.resizeTo(screen.availWidth, screen.availHeight);
}

/** ���д�һ���´��ڣ��߶ȣ�����Զ���*/

function openWindowInCenter(winURL,height,width, name)
{
	var top = (800 - height)/2;
	var left = (1259 - width)/2;
	var InCenterwin = window.open(winURL,name,"height=" + height + ",width=" + width + ",top=" + top + ",left=" + left + ",toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no");

}




//����ҳ����ֿ�ѧ����

// �༭ҳ���滻��ѯ���滻ȫ����ʾ��input�������ʾ����
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

// �б���ʾҳ���ѯ���滻ȫ����ʾ����

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
// ҳ����table������滻
function initFloatData(){
	var objTable,objTr,objTd;	
	objTable = document.all("dataTable");		
    if (objTable != null) {
		if(objTable.length == undefined){			
		// ֻ��һ����ݱ�
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
		// ����ݱ�		
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


//�˷����������õ���ȷ�ĳ˷����
//˵����javascript�ĳ˷�����������������������˵�ʱ���Ƚ����ԡ��������ؽ�Ϊ��ȷ�ĳ˷����
//���ã�accMul(arg1,arg2)
//����ֵ��arg1����arg2�ľ�ȷ���
function accMul(arg1,arg2)
{
	if(!isNaN(arg1) && !isNaN(arg2)){
		arg1=String(arg1);var i=arg1.length-arg1.indexOf(".")-1;i=(i>=arg1.length)?0:i
		arg2=String(arg2);var j=arg2.length-arg2.indexOf(".")-1;j=(j>=arg2.length)?0:j
		return (arg1.replace(".","")*arg2.replace(".","")/Math.pow(10,i+j)).toFixed(4)
	}else return 0
}

//�ӷ����������õ���ȷ�ļӷ����
//˵����javascript�ļӷ�����������������������ӵ�ʱ���Ƚ����ԡ��������ؽ�Ϊ��ȷ�ļӷ����
//���ã�accAdd(arg1,arg2)
//����ֵ��arg1����arg2�ľ�ȷ���
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

//�������������õ���ȷ�ļӷ����
//˵����javascript�ļ����������������������������ʱ���Ƚ����ԡ��������ؽ�Ϊ��ȷ�ļ������
//���ã�accSub(arg1,arg2)
//����ֵ��arg1����arg2�ľ�ȷ���
function accSub(arg1,arg2){
	if(!isNaN(arg1) && !isNaN(arg2)){
		var r1,r2,m;
		try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
		try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
		m=Math.pow(10,Math.max(r1,r2))
		return ((arg1*m-arg2*m)/m).toFixed(4)
	}else return 0
}

//���������õ���ȷ�ĳ���
//˵����javascript�ĳ������������������������ʱ���Ƚ����ԡ��������ؽ�Ϊ��ȷ�ĳ���
//���ã�accDiv(arg1,arg2)
//����ֵ��arg1����arg2�ľ�ȷ���
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

	// ��ŵ��֪��ҳ��߶�
	function initDivHeight(divId){
		var divHeight;
		divHeight=document.body.offsetHeight-164;
		document.getElementById(divId).style.height=divHeight;
	}

	/**
	* ������˸��ɫ�仯�ű�
	*����˵����objId����Ҫ��ɫ������id
	*���÷�ʽ��ҳ�����onload��ʽ��ֱ�ӵ��þ��
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
	
function changeOpnnStyle(objname){	
	var styleVar=eval("document.all."+objname+".style.display")
	if(styleVar=="none"){
		eval("document.all."+objname+".style.display='block'")
		eval("document.all."+objname+"Img.src='../img/nolines_minus.gif'")	
	}
	else{
		eval("document.all."+objname+".style.display='none'")
		eval("document.all."+objname+"Img.src='../img/nolines_plus.gif'")	
	}
}
function shoHistoryDiv(){
	alert("shoHistoryDiv="+document.all.liuchengView2Lab);
	if(document.all.liuchengViewLab.innerText=="流程查看"){
		document.all.liuchengViewLab.innerText="隐藏流程"
		document.all.historyDivImg.src="../img/nolines_minus.gif"
		document.all.historyDiv.style.display="block"
		
	}else{
		document.all.liuchengViewLab.innerText="流程查看"
		document.all.historyDivImg.src="../img/nolines_plus.gif"
		document.all.historyDiv.style.display="none"
	}
	
}
function shoHistoryDiv2(id){
	if($("#"+id+"Lab").text()=="展开流程"){
		$("#"+id+"Lab").text("隐藏流程");		
		$("#"+id).show();
	}else{		
		$("#"+id+"Lab").text("展开流程");		
		$("#"+id).hide();
	}
}
function changeHistoryTabStyle(objName){
	
	var styleVar=eval("document.all."+objName+".style.display")
	
	if(styleVar=="block"){	
		eval("document.all."+objName+".style.display='none'")			
	}else{
		eval("document.all."+objName+".style.display='block'")			
	}
}
function changeTrStyle(objName,index){
	
	var styleVar=eval("document.all."+objName+index+".style.display")
	
	if(styleVar=="block"){
	for(var i=1;i<=index;i++){
			eval("document.all."+objName+i+".style.display='none'")
			eval("document.all."+objName+"Img.src='../img/nolines_plus.gif'")		
		}		
	}else{
		for(var i=1;i<=index;i++){			
			eval("document.all."+objName+i+".style.display='block'")			
			eval("document.all."+objName+"Img.src='../img/nolines_minus.gif'")			
		}
	}
}

function changeOpnnTabStyle(objName){		
	var styleVar=eval("document.all."+objName+".style.display")
	
	if(styleVar=="block"){	
			eval("document.all."+objName+".style.display='none'")
			eval("document.all."+objName+"Img.src='../img/nolines_plus.gif'")				
	}else{
		eval("document.all."+objName+".style.display='block'")
			eval("document.all."+objName+"Img.src='../img/nolines_minus.gif'")	
		
	}
}

// TAB选项卡效果
function selectTag(showContent, selfObj) {
	// 操作标签
	var tag = document.getElementById("tags").getElementsByTagName("li");
	var taglength = tag.length;
	for (i = 0; i < taglength; i++) {
		tag[i].className = "";
	}
	selfObj.parentNode.className = "selectTag";
	// 操作内容
	for (i = 0; j = document.getElementById("changtable" + i); i++) {
		j.style.display = "none";
	}
	document.getElementById(showContent).style.display = "block";

}