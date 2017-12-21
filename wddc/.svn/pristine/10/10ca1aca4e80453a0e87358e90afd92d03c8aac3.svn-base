//======================================================================================
// select、radio and checkbox
//-----------------------index----------------------------------------------------------
//  1.pub_addOption									向object添加一个option
//  2.pub_deleteOption							删除指定的一个select的option
//  3.pub_removeSelected						从指定的select中删除所有的的option
//  4.pub_moveSelectedUpOrDown			改变对应列表框中选则条件的次序
//  5.pub_copySelected							将fromObject下拉列表中的列表数据copy到toObject中
//  6.pub_moveSelected							将fromObject下拉列表中的列表数据移至到toObject中
//  7.pub_selectOption							选中某一指定的值
//  8.pub_selectAll									将列表中的option全选
//  9.pub_getSelectedValue					获得选中option的值
// 10.pub_ifCheck										判断某组checkbox框是否有被选中的
// 11.pub_ifRadioCheck							判断某组radio框是否有被选中的
// 12.pub_checkAll									某组checkbox框全部被选中
// 13.pub_uncheckAll								某组checkbox框全部不被选中
// 14.pub_switchAll									把所有check 反选
// 15.pub_ifRadioCheckAndRtn                        判断一组radio中是否有选中的，并且返回选中的值
//=======================================================================================
/*
功能：向object添加一个option
参数：object为要添加的下拉框名,text为options的显示值,value为下拉框的实际值,select为是否被默认选中
返回值：无
*/
function pub_addOption(object, text, value, select) {
    var defaultSelected = false;
    var selected = select;
    var optionName = new Option(text, value, defaultSelected, selected);
    object.options[object.length] = optionName;
}

/*
功能：删除指定的一个select的option
参数：object为要添加的下拉框名,index为要删除的序列号（从0开始）
返回值：无
*/
function pub_deleteOption(object, index) {
    object.options[index] = null;
}

/*
功能：从指定的select中删除所有的的option
参数：fromObject为要删除的下拉框名
返回值：无
*/
function pub_removeSelected(fromObject) {
    for (var i=fromObject.options.length-1; i>-1; i--) {
        if (fromObject.options[i].selected) {
           pub_deleteOption(fromObject,i);
        }
    }    
}

/*
功能：改变对应列表框中选则条件的次序
返回值：无
*/
function pub_moveSelectedUpOrDown(oSelect, forward){
	var nextVal = oSelect.selectedIndex ;
	if (nextVal >=0){
		if (forward == -1){
			nextVal--;if (nextVal<0) nextVal =0;
		}else{
			nextVal++;if (nextVal>=oSelect.options.length) nextVal =oSelect.options.length-1;
		}
		oSelect.options[oSelect.selectedIndex].swapNode( oSelect.options[nextVal] ); 
	}
}

/*
功能：将fromObject下拉列表中的列表数据copy到toObject中
返回值：无
*/
function pub_copySelected(fromObject, toObject) {
   var count = 0;
   for (var i=0, len=fromObject.options.length; i<len; i++)
   {
       if (fromObject.options[i].selected)
          {
            count++;
           if (count==1) toObject.selectedIndex = -1;
           pub_addOption(toObject,fromObject.options[i].text,fromObject.options[i].value, true);
         }
    }
   if (count==1)toObject.selectedIndex = toObject.options.length -1;
}

/*
功能：将fromObject下拉列表中的列表数据移至到toObject中
返回值：无
*/
function  pub_moveSelected(fromObject, toObject)
{
    pub_copySelected(fromObject,toObject);
    pub_removeSelected(fromObject);
    //fromObject.selectedIndex = -1;
}
/*
功能：选中某一指定的值
返回值：无
*/
function pub_selectOption(selectObj,optionValue){	
	for(var i=0; i<selectObj.options.length; i++){
		if(selectObj.options[i].value == optionValue){
			selectObj.options[i].selected = true;
			return true;;
		}
	}
	
	selectObj.options[0].selected = true;
	return true;
}

/*
功能：将列表中的option全选
返回值：无
*/
function pub_selectAll(objName){
	for(var i=0;i<objName.options.length;i++){
		objName.options[i].selected = true
	}
}

/*
功能：获得选中option的值
返回值：无
*/
function pub_getSelectedValue(objName){
	for(var i=0;i<objName.options.length;i++){
		if(objName.options[i].selected){
			return objName.options[i].value
		}
	}
	return ""
}

/*
功能：判断某组checkbox框是否有被选中的
参数：aform为表名,avalue为控件名称,aname为控件显示的名字
*/
function pub_ifCheck(aform,aname,avalue){
    var num=0;
    for (var j=0;j<aform.elements.length;j++){
	 if (aform.elements[j].name== avalue ){
            if(aform.elements[j].checked == true){
                 num=num+1;   
            } 	            	 	
        }   
    }      
    if (num == 0){
       alert("请选择"+aname+"!");
       return false ;
    }		
    return true;	
}

/*
功能：判断某组radio框是否有被选中的
参数：aform为表名,avalue为控件名称,aname为控件显示的名字
*/
function pub_ifRadioCheck(aform,avalue){
    var num=0;
    var itemLength = aform.avalue.length
    var ifSelected=false
    if(aform.avalue.length){
      for(var i=0;i<itemLength;i++){
    	if(aform.avalue[i].checked)	
    	{
    		ifSelected = true
    		break
    	}
     }
    }else{
     if(aform.avalue.checked){
      ifSelected=true
     }    
    }	
    return ifSelected;	
}
	

/*
功能：某组checkbox框全部被选中
参数：aform为表名,avalue为控件名称
返回值：无
*/
function pub_checkAll(aform,avalue)
{	
        for (var j=0;j<aform.elements.length;j++){
	 	if (aform.elements[j].name==avalue){
	 	aform.elements[j].checked=true;
	 	}
	}	
}

/*
功能：某组checkbox框全部不被选中
参数：aform为表名,avalue为控件名称
返回值：无
*/
function pub_uncheckAll(aform,avalue)
{
   for (var j=0;j<aform.elements.length;j++){
	 	if (aform.elements[j].name==avalue){
	 		aform.elements[j].checked=false;
	 	}
	}
}

/*
功能：把所有check 反选
参数：aform为表名,avalue为控件名称
返回值：无
*/
function pub_switchAll(aform,avalue)
{
  for (var j=0;j<aform.elements.length;j++){
	 	if (aform.elements[j].name==avalue){
	 		aform.elements[j].checked = !aform.elements[j].checked;
	 	}
	}
}

/*
功能：判断一组radio中是否有选中的，并且返回选中的值
参数：obj radio对象，undefinedMsg 对象没有定义时的提示，unselectedMsg 对象没有被选中时的提示
返回：被选中的radio的值
*/
function pub_ifRadioCheckAndRtn(obj,undefinedMsg,unselectedMsg) {

    var rtnValue = "";
    var flag = false;
    
    if(obj==undefined){
        alert(undefinedMsg);
        return rtnValue;
    }
    
    if(obj.length) {
        //有多个radio
        for (var i=0;i<obj.length;i++) {
            if(obj[i].checked) {
                rtnValue = obj[i].value;
                flag = true;
                break;
            }
        }
    } else {
        //只有一个radio
        if (obj.checked) {
            rtnValue = obj.value;
            flag = true;
        }
    }
    
    //判断是否有选中的
    if (!flag){
        alert(unselectedMsg);
        return rtnValue; 
    }
    
    return rtnValue;
}
