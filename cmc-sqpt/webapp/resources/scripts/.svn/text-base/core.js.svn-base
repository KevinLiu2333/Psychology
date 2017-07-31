
/*
将object转换为数组，如果object是数组则直接返回
*/
function asArray(object){
	var array;
	if(typeof object=='undefined'){
		return new Array();
	}else if(typeof object["length"]=='undefined'){
		array=new Array();
		array[0]=object;
	}else{
		array = object;
	}

	return array;
}

/**
 * 根据元素获取其所在元素组的索引

 * 参数：

 * element:元素
 * id:元素的标识

 */
function getElementIndex(element,id){
	var elements = asArray(document.all[id]);
	for(var i = 0; i < elements.length; i++){
		if(elements[i] == element){
			return i;
		}
	}
	return -1;
}

/**
 * 根据某个元素获取与其处在相同索引的元素

 * 参数：

 * element:元素
 * id:元素的标识

 * otherElementIds...:其他元素的标识，动态参数

 * 返回：{id1:element1,id2:element2,...}
 */
function getSameIndexElements(element,id,otherElementIds){
	var index=getElementIndex(element,id);

	var result={};
	for(var i=2;i<arguments.length;i++){
		var eid=arguments[i];
		result[eid]=asArray(document.all[eid])[index];
	}
	return result;
}

/*
	复制table标签的行
*/
function copyRows(
		desTab/*目的表格*/,
		srcTab/*源表格*/,
		from/*源起始位置*/,
		size/*复制行数*/,
		to/*目的表格位置*/
	){
	if(arguments.length<3)
		from=0;

	if(arguments.length<4)
		size=srcTab.rows.length-from;

	if(arguments.length<5)
		to=-1;

	if(isNaN(from)||isNaN(size)||isNaN(to))
		return;

	if(size<=0)
		return;
	if(from>=srcTab.rows.length)
		return;
	if(to>=desTab.rows.length)
		to=desTab.rows.length;
	if(to<0)
		to=desTab.rows.length;

	var toIndex=to;
	var newrow;
	for(var i=0;i<size;i++){
	  newrow = desTab.insertRow(toIndex++);
	  newrow.replaceNode(srcTab.rows(from+i).cloneNode(true));
	}
}
/*
	删除table标签从from开始的size行

*/
function deleteRows(table,from,size){
	if(table.rows.length==0)
		return;

	if(arguments.length<3)
		size=-1;

	if(arguments.length<2)
		from=0;

	if(from>=table.rows.length)
		from=table.rows.length-1;

	if(size<0)
		size = table.rows.length-from;

	if(table.rows.length-from<size)
		size = table.rows.length-from;

	for(var i=size-1;i>=0;i--){
		table.deleteRow(from+i);
	}
}
/*
	删除table标签的index行，table至少应该有minrows行

*/
function deleteRow(table,index,minrows){
	if(arguments.length<3)
		minrows=0;
	if(arguments.length<2)
		index=-1;
	if(index==-1)
		index = table.rows.length-1;
	if(minrows<0)
		minrows=0;
	if(index<0)
		return;
	if(table.rows.length<=minrows)
		return;
	table.deleteRow(index);
}
/**
 * 删除当前行，参数：

 * obj：行内对象

 * marchLevel:父亲级别，默认为1，即第一个包含的TR
 */
function deleteCurrentRow(obj,marchLevel){
	var parent=obj;
	var level=1;
	if(marchLevel){
		level=marchLevel;
	}
	var hit=0;
	for(;true;){
		parent = parent.parentElement;
		if(parent==document.body){
			break;
		}
		if(parent.tagName.toLowerCase()=='tr'){
			hit=hit+1;
			if(hit==level){
				parent.removeNode(true);
				break;
			}
		}
	}
}

function strToObj(str){
	return eval("("+str+")");
}

//条线业务页面用于组装返回json
function jsonObjToStr(obj){
	var str = "";
	for(var key in obj){
		var s = '\"' + key + '\":\"' + obj[key] + '\"';
		str = str + s+ ',';
	}
	return str.substr(0, str.length-1);
}
function JSObjectIsDate(value){
	 return !!(value && value.getFullYear);
}


/**
 * 判断对象是否是数值
 */
function JSObjectIsNumber(value){
	 return !isNaN(value);
}

/**
 * 判断对象是否是数组
 */
function JSObjectIsArray(value){
	return !!(value && !!value.push);
}

function JSObjectClone(source){

	if ( typeof(source)!='object' ) return source;
	if ( source==null ) return null;
	if ( JSObjectIsDate(source) ){
		var desc = new Date();
		desc.setDate(source.getDate());
		desc.setMonth(source.getMonth());
		desc.setFullYear(source.getFullYear());
		desc.setHours(source.getHours());
		desc.setMinutes(source.getMinutes());
		desc.setSeconds(source.getSeconds());
		desc.setMilliseconds(source.getMilliseconds());
		return desc;
	}

	if( source instanceof Array){
		var desc = new Array();
		for(var i = 0, l = source.length; i < l; i++) {
			desc[i] = source[i];
		}
		return desc;
	}

	var desc = {};
	for(var key in source){
		if(typeof(source[key])=="object"){
			desc[key] = JSObjectClone(source[key]);
		}else{
			desc[key] = source[key];
		}
	}
	return desc;
}
//js属性复制
function JSObjectCopy(source,desc){
	if(typeof(source)!='object' || typeof(desc)!='object' )return;
	if( source instanceof Array){
		desc = new Array();
		for(var i = 0, l = source.length; i < l; i++) {
			desc[i] = source[i];
		}
		return;
	}
	for(var key in source){
		if(typeof(source[key])=="function"){


		}else if(typeof(source[key])=="object"){
			if ( typeof(desc[key])!='undefined' && typeof(desc[key])!='function' ){
				if ( source[key]==null ){
					desc[key] = null;
				}else if(typeof(desc[key])=="object"){
					if ( JSObjectIsDate(source[key]) ){
						if ( !JSObjectIsDate(desc[key]) ){
							desc[key] = new Date();
						}
						try{
							desc[key].setDate(source[key].getDate());
							desc[key].setMonth(source[key].getMonth());
							desc[key].setFullYear(source[key].getFullYear());
							desc[key].setHours(source[key].getHours());
							desc[key].setMinutes(source[key].getMinutes());
							desc[key].setSeconds(source[key].getSeconds());
							desc[key].setMilliseconds(source[key].getMilliseconds());
						}catch(e){

						}
					}else{
						JSObjectCopy(source[key],desc[key]);
					}
				}else{
					desc[key] = JSObjectClone(source[key]);
				}
			}
		}else{
			if ( typeof(desc[key])!='undefined' && typeof(desc[key])!='function' ){
				desc[key] = source[key];
			}
		}
	}
}

function __openJSPrintWin(obj,msg,winname){
	var windowsname = "对象信息";
	if( winname ) windowsname = winname;
	var newWindow = window.open("",windowsname,"height=600,width=800,top=0,scrollbars=yes,resizable=yes");
	var scriptstr = "<script>var objM;var printFunc={};function setObj(obj){objM = obj;};function setFunc(func){printFunc=func;};";
	scriptstr += "function showTable(keystr,divstr){var showdiv = document.getElementById(\"div_\"+divstr);" +
			"if(showdiv.isShow){showdiv.isShow = false;showdiv.style.display=\"none\";}" +
			"else if (typeof(showdiv.isShow)==\"undefined\"){var keys = keystr.split(\"#\");obj = objM;" +
			"for(i=0;i<keys.length;i++){obj = obj[keys[i]];}showdiv.innerHTML=printFunc(obj,1,keystr,divstr);showdiv.style.display=\"\";showdiv.isShow=true;}" +
			"else{showdiv.style.display=\"\";showdiv.isShow=true;};}" +
			"</script>";
	newWindow.document.write("<html><head><TITLE>"+winname+"</TITLE><style>table{border-collapse: collapse;}td{border: 1px solid black;}</style>\n"+scriptstr+"\n</head><body>\n"+msg+"\n</body></html>");
    newWindow.document.close();
    newWindow.setObj(obj);
    newWindow.setFunc(__JSObjectPrint);
}
function __JSObjectPrintCreateTD(index,key,str){
	return "<tr><td width='5%'>"+index +"</td><td>"+key +"</td><td>"+ str +"</td></tr>\n";
}

function __JSObjectPrint(obj,lvl,keystr,divstr){
	var props = "<table border='0' width='100%''>";
	if ( typeof(obj)!="object" ) {
		props = obj;
	}else{
		var index = 1;
		for(var key in obj){
			//if ( key!='innerHTML' && key!='outerHTML'){
				if(typeof(obj[key])=="function"){
					props += __JSObjectPrintCreateTD(index++, key, obj[key] );
				}else if(typeof(obj[key])=="object"){
					if ( obj[key]==null || JSObjectIsDate(obj[key]) ){
						props += __JSObjectPrintCreateTD( index++, key, obj[key] );
					}else{
						var div_id = key;
						var key_id = key;
						if ( keystr ){
							key_id = keystr + "#" + key;
							div_id = divstr + "." + key;
						}
						var vtype = "(object)";
						if ( JSObjectIsArray(obj[key]) ) vtype = "(array)";
						var clickDiv = clickDiv = "<a href='javascript:void(0);' onclick=\"showTable('"+key_id+"','"+div_id+"');\">"+vtype+"</a><div id='div_"+div_id+"'></div>\n";
						props += __JSObjectPrintCreateTD( index++, key, clickDiv );
					}
				}else{
					try{
						var str = obj[key].toString();
						var regS = new RegExp("<","gi");
						str=str.replace(regS,"<font><</font>");
						props += __JSObjectPrintCreateTD( index++, key, str );
					}catch(e){
						props += __JSObjectPrintCreateTD( index++, key, obj[key] );
					}
				}
			//}
		}
	}
	props = props + "</table>";
	return props;
}

//js属性遍历
function JSObjectPrint(obj,winname){
	__openJSPrintWin(obj, __JSObjectPrint(obj, 0 ),winname );
}
JSPrint = JSObjectPrint;
function JSObjectFunctionPrint(obj,winname){
	var props = "<table border='0' width='100%'>";
	if ( typeof(obj)!="object" ) {
		alert(obj);
		return;
	}
	for(var key in obj){
		if(typeof(obj[key])=="function"){
			var index = 1;
			props += __JSObjectPrintCreateTD( index++, key, obj[key] );
		}
	}
	props = props + "</table>";
	__openJSPrintWin(obj,props,winname);;
}


function splitAreaCode(areacode){
	var data = {};
	data.area1 = "";
	data.area2 = "";
	data.area3 = "";
	if ( areacode && areacode.length==6){
		var str = areacode;
		if ( str.substring(4)=='00'){

		}else{
			data.area3  = str;
			str = str.substr(0,4 )+"00";
		}

		if ( str.substring(2)=='0000'){

		}else{
			data.area2 = str;
			str = str.substr(0,2 )+"0000";
		}
		data.area1 = str;
	}
	return data;
}


WIN_SizeInfo = function(obj) {
	this.clientTop = 0;
	this.offsetTop = 0;
	this.scrollTop = 0;

	this.clientWidth = 0;
	this.scrollWidth = 0;
	this.offsetWidth = 0;

	this.clientLeft = 0;
	this.offsetLeft = 0;
	this.scrollLeft = 0;

	this.clientHeight = 0;
	this.offsetHeight = 0;
	this.scrollHeight = 0;

	if (typeof (obj) == 'object') {
		for ( var key in this) {
			try {
				this[key] = obj[key];
			} catch (e) {
			}
		}
	}
}

function WIN_SizeInfo_Copy(source, desc) {
	for (var key in desc) {
		if (typeof (desc[key]) == "function") {
		} else if (typeof (desc[key]) == "object") {
		} else {
			try {
				desc[key] = source[key];
			} catch (e) {
			}
		}
	}
}


/**
 * 命名空间
 */
function JSNamespace(ns){
	 if(typeof(ns)!="string")return;
	 var o =null;
	 var ni =null;
	 ns=ns.split(".");
	 for(var i=0,len=ns.length;i<len,ni=ns[i];i++){
	    try{o=(o?(o[ni]=o[ni]||{}):(eval(ni+"="+ni+"||{}")));}catch(e){o=eval(ni+"={}");};
	 }
}

/**
 * 判断对象是否是数值
 */
function JSObjectIsNumber(value){
	 return !isNaN(value);
}

JSClone = JSObjectClone;

/**
 * 在JS对象间进行属性复制，只对目标对象存在的属性操作
 */
function JSObjectCopy(source,desc,copyall){
	if(typeof(source)!='object' || typeof(desc)!='object' )return;
	if( source instanceof Array){
		desc = new Array();
		for(var i = 0, l = source.length; i < l; i++) {
			desc[i] = source[i];
		}
		return;
	}
	if(copyall){
		for(var key in source){
			desc[key] = JSObjectClone(source[key]);
		}
		return;
	}
	
	for(var key in source){
		if(typeof(source[key])=="function"){ 
			
			
		}else if(typeof(source[key])=="object"){
			if ( typeof(desc[key])!='undefined' && typeof(desc[key])!='function' ){
				if ( source[key]==null ){
					desc[key] = null;
				}else if(typeof(desc[key])=="object"){
					if ( JSObjectIsDate(source[key]) ){
						if ( !JSObjectIsDate(desc[key]) ){
							desc[key] = new Date();
						}
						try{
							desc[key].setDate(source[key].getDate());
							desc[key].setMonth(source[key].getMonth());
							desc[key].setFullYear(source[key].getFullYear());
							desc[key].setHours(source[key].getHours());
							desc[key].setMinutes(source[key].getMinutes());
							desc[key].setSeconds(source[key].getSeconds());
							desc[key].setMilliseconds(source[key].getMilliseconds());
						}catch(e){
							
						}
					}else{
						JSObjectCopy(source[key],desc[key]);
					}
				}else{
					desc[key] = JSObjectClone(source[key]);
				}
			}
		}else{
			if ( typeof(desc[key])!='undefined' && typeof(desc[key])!='function' ){
				desc[key] = source[key];
			}
		}
	}
}
JSCopy = JSObjectCopy;

function __JSObjectPrintCreateTD2(index,key,str){
	return "<tr><td width='5%'>"+index +"</td><td>"+key +"</td><td><textarea cols='30' rows='10'>"+ str +"</textarea></td></tr>\n";
}
/**
 * 取首个非空值JSNVL(x,y,z,...)
 */
function JSNVL(){
	for (i = 0; i < arguments.length; i++){
		if(arguments[i])return arguments[i];
	}
	return "";
}



function CPos(x, y)
{
    this.x = x;
    this.y = y;
}

/**
 * 获取屏幕位置信息
 */
function GetObjPos(obj)
{
    var targetObj = obj;
    var pos = new CPos(targetObj.offsetLeft, targetObj.offsetTop);
    var targetObj = targetObj.offsetParent;
    while (targetObj)
    {
        pos.x += targetObj.offsetLeft;
        pos.y += targetObj.offsetTop;
        targetObj = targetObj.offsetParent
    }
    return pos;
}

function formatSFZ(text,flag){
	if(text.length<=6)return text;
	if( text.substr(0,2)=="10" ) return text;
	var sfzHtml = text.substr(0,6);
	if(flag){
		sfzHtml = sfzHtml + " <span class='inp-focus-focus'>"+text.substr(6,8) + "</span> " ;
	}else{
		sfzHtml = sfzHtml + "<span class='inp-focus-focus'>"+text.substr(6,8) + "</span>" ;
	}
	if(text.length>14){
		sfzHtml = sfzHtml + text.substring(14);
	}
	return sfzHtml;
}

/**
 * 根据ID获取html元素
 * 传入el，只在el子节点查询，否则全局查询
 */
function JSById(idstr,el){
	if(!el){
		return document.getElementById(idstr);
	}
	var tags = el.getElementsByTagName("*");
	var count = tags.length;
	for(var i = 0; i < count; i++){
		 var id=tags[i].id;
		 if(id==idstr){
			 return tags[i];
		 } 
	}
	return null;
}

/**
 * 将传入的值按int类型相加JSSumInt(x,y,z,...)
 */
function JSSumInt(){
	var sum = 0;
	for (i = 0; i < arguments.length; i++){
		if(arguments[i]){
			var value=0;
			try{
				if(!isNaN(arguments[i]))value = parseInt(arguments[i] ,10);
			}catch(e){
				
			}
			sum += value;
		};
	}
	return sum;
}
/**
 * 将传入的值按浮点类型相加JSSumFloat(decimalPlaces,x,y,z,...)
 * 首位为小数位数
 */
function JSSumFloat(decimalPlaces){
	var sum = 0;
	for (i = 1; i < arguments.length; i++){
		if(arguments[i]){
			var value=0;
			try{
				if(!isNaN(arguments[i])) value = parseFloat(arguments[i]);
			}catch(e){
				
			}
			sum += value;
		};
	}
	if(!decimalPlaces)decimalPlaces = 2;
	return sum.toFixed(decimalPlaces);
}

/**
 * 根据ID显示html元素
 * 传入el，只在el子节点查询，否则全局查询
 */
function JSShow(idstr,el){
	var obj = JSById(idstr,el);
	if(obj){
		obj.style.display="";
	}
}

/**
 * 根据ID隐藏html元素
 * 传入el，只在el子节点查询，否则全局查询
 */
function JSHide(idstr,el){
	var obj = JSById(idstr,el);
	if(obj){
		obj.style.display="none";
	}
}

/**
 * 将传入的对象值赋给对应id节点
 * 传入el，只在el子节点查询，否则全局查询
 */
function JSSetData(data,el){
	for(var key in data){
		if(typeof(data[key])=="function"){ 

		}else if(typeof(data[key])=="object"){
			 
		}else{
			try{
				var obj = JSById(key,el);
				if(obj && obj.tagName){
					var tname = obj.tagName.toLowerCase();
					if(tname=="input"||tname=="select"){
						obj.value = data[key];
					}else{
						obj.innerText = data[key];
					}
				}
			}catch(e){
			}
		}
		 
	}
}

/**
 * 判断对象是否空对象
 */
function JSIsEmpty(obj){
	if(obj){
		for(var key in obj){
			return false;
		}
	}
	return true;
}

/**
 * 最小值
 */
function JSLeast(){
	var val = arguments[0];
	for (i = 1; i < arguments.length; i++){
		if( val> arguments[i]){
			val = arguments[i]
		}
	}
	return val;
}
/**
 * 最大值
 */
function JSGreatest(){
	var val = arguments[0];
	for (i = 1; i < arguments.length; i++){
		if( val<arguments[i] ){
			val = arguments[i]
		}
	}
	return val;
}


/**
 * 判断年月日格式 
 * 只支持：yyyyMM yyyyMMdd yyyy-MM yyyy-MM-dd yyyy-M yyyy-M-d
 */
function JSGetDateFormat(value){
	var test = value.split("-");
	if(test.length==3){
		var format="yyyy-M";
		if(test[1].length==2){
			format = format+"M";
		}
		format = format+"-d";
		if(test[2].length==2){
			format = format+"d";
		}
		return format;
	}else if(test.length==2){
		var format="yyyy-M";
		if(test[1].length==2){
			format = format+"M";
		}
		return format;
	}else{
		var len = value.length
		if(len==6)return "yyyyMM";
		if(len==8)return "yyyyMMdd";
	}
	return "";
}

/**
 * 将列表中的指定属性转换数组
 * @param list
 * @param prop
 */
function listToArray(list,prop){
	var array = [];
	for(var i=0;i<list.length;i++){
		var value = list[i][prop];
		if(typeof value !="undefined")array.push(value);
	}
	return array;
}

/**
 * 合并数组
 * @param arr1
 * @param arr2
 * @returns {Array}
 */
function mergeArray(arr1,arr2){
	var array = [];
	var obj = {};
	if(JSObjectIsArray(arr1)){
		for(var i=0;i<arr1.length;i++){
			var value = arr1[i];
			if(obj[value]){
				
			}else{
				obj[value] = 1;
				array.push(value);
			}
		}
	}
	
	if(JSObjectIsArray(arr2)){
		for(var i=0;i<arr2.length;i++){
			var value = arr2[i];
			if(obj[value]){
				
			}else{
				obj[value] = 1;
				array.push(value);
			}
		}
	}
	
	return array;
}

/**
 * 从一个数组中清理在另一个数组中存在的数据
 * @param arr1
 * @param arr2
 * @returns {Array}
 */
function cleanArray(arr1,arr2){
	var array = [];
	var obj = {};
	
	if(JSObjectIsArray(arr2)){
		for(var i=0;i<arr2.length;i++){
			obj[arr2[i]] = 1;
		}
	}
	
	if(JSObjectIsArray(arr1)){
		for(var i=0;i<arr1.length;i++){
			var value = arr1[i];
			if(obj[value]){
				
			}else{
				obj[value] = 1;
				array.push(value);
			}
		}
	}
	
	return array;
}