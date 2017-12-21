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
功能：将fromObject下拉列表中的列表数据复制到toObject中
返回值：无
*/
function  pub_copySelected(fromObject, toObject)
{
    pub_copySelected(fromObject,toObject);    
    //fromObject.selectedIndex = -1;
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
功能：向object添加一个option
参数：object为要添加的下拉框名,text为options的显示值,value为下拉框的实际值,select为是否被默认选中
返回值：无
*/
function pub_addOption(object, text, value, select) {	
	
	var defaultSelected = false;
    var selected = select;
    var optionName = new Option(text, value, defaultSelected, selected);
    var flag = true ;
    
    for(var i =0 ;i<object.length;i++)
	{	    
		if(object.options[i].value==value)
		{
    		flag = false ;
    	}
    }
    if(flag)
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
