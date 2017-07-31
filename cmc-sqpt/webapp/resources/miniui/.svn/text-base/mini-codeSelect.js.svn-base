/**
 * 下拉字典表
 * 版本修正：
 * 修正下拉框的滚动条行为，增加级联功能
 * JSPrint(new mini.ComboBox(),"miniComboBox");
 * _OnValueChanged :  找到函数doValueChanged，其内调用的就是_OnValueChanged
 * */

mini.CodeSelect = function () {
    mini.CodeSelect.superclass.constructor.call(this);
}
mini.extend(mini.CodeSelect, mini.ComboBox, {
    uiCls: "mini-codeselect",
    emptyText: "请选择..." ,
    textField: "text",
    valueField: "value",
    popupMaxHeight: 300,
    codeType: "",
    relatedObj:null,
    hasRelate:false,
    setCodeType: function (value) {
        this.codeType = value;
    },
    getCodeType: function () {
        return this.codeType;
    },
    OoOo:function(){//_OnValueChanged补丁，触发事件的同时触发联动
    	if ( this.hasRelate ) {
    		var sf = this;
            setTimeout(function () {
                sf.changeRelate();
            }, 1);
    	}
    	mini.CodeSelect.superclass.OoOo.call(this); //fixname
    },
    
    showPopup: function () {//下拉框修正横向滚动条
        mini.CodeSelect.superclass.showPopup.call(this);
        var popup = this.getPopup();
        var popupEl = popup.getBodyEl();
        var innerEl = mini.byClass('mini-listbox-view', popupEl);
       
        
        var outerSize = new WIN_SizeInfo(popupEl);//下拉框外部大小
        var innerSize = new WIN_SizeInfo(innerEl);//下拉框内部大小
        var winSize = new WIN_SizeInfo(popupEl.ownerDocument.documentElement);//窗体大小
        
        //如果下拉框超过了窗体底部，修正高度
        var newHeight = popup.getHeight();
    	if ( outerSize.offsetTop + outerSize.offsetHeight > winSize.clientHeight ){
    	    newHeight = winSize.clientHeight - outerSize.offsetTop -9;
    		if ( newHeight>20 ){
    			popupEl.style.height = newHeight + "px";
        		popup.setHeight(newHeight);
        		innerEl.style.height = newHeight + "px";
    		}
    	}
    	
    	//如果下拉框往上弹出超过了窗体顶部，修正高度
    	if ( outerSize.offsetTop<=0 ){
    		popupEl.style.top = 9 + "px";
    		newHeight = popupEl.scrollHeight + outerSize.offsetTop - 9;
    		popupEl.style.height = newHeight + "px";
    		popup.setHeight(newHeight);
    		innerEl.style.height = newHeight + "px";
    	} 
    	
    	WIN_SizeInfo_Copy( popupEl , outerSize );//下拉框外部大小
    	WIN_SizeInfo_Copy( innerEl , innerSize );//下拉框内部大小
    	//newHeight = newHeight -2;
    	
		if(innerSize.scrollHeight>innerSize.clientHeight){
			newHeight = innerEl.offsetHeight+4;
		}else{
			newHeight = innerEl.clientHeight+4;
		}
		
		if( newHeight >outerSize.scrollHeight){
			newHeight = outerSize.scrollHeight;
		}
		innerEl.style.overflowY ="auto";
		innerEl.style.overflowX ="hidden";
		popupEl.style.overflow ="hidden";
        //自动修正宽度防止横向滚动条
        //先判断是否出现了滚动条，横向纵向都算
    	//如果是offsetHeight超过clientHeight很多，则认为出现了滚动条(正常是相等或者加边框宽度)
    	//如果scrollHeight超过了clientHeight,肯定出现了垂直滚动条
        if ( innerSize.scrollHeight>innerSize.clientHeight || innerSize.offsetHeight > innerSize.clientHeight+5 ){
        	//修正宽度
        	var oldwidth = popup.getWidth();
        
        	if(innerSize.scrollWidth-oldwidth>=2){
        		var innerWidth = innerSize.scrollWidth;
        		if( innerWidth<innerSize.offsetWidth)innerWidth=innerSize.offsetWidth;
        		innerEl.style.width = innerWidth + "px";
        		popup.setWidth(innerWidth);
        	}
        	WIN_SizeInfo_Copy( popupEl , outerSize );//下拉框外部大小
        	WIN_SizeInfo_Copy( innerEl , innerSize );//下拉框内部大小
        	var widthFix = outerSize.offsetWidth - innerSize.clientWidth;
    		var newWidth = innerSize.scrollWidth + widthFix;
    		
    		if ( newWidth>oldwidth ){
    			popupEl.style.width = newWidth + "px";
    			popup.setWidth( newWidth+2 );
    			innerEl.style.height = newHeight + "px";
    			//再判断次，如果还有滚动条，再进行修正
        		{
        			WIN_SizeInfo_Copy( popupEl , outerSize );//下拉框外部大小
        			WIN_SizeInfo_Copy( innerEl , innerSize );//下拉框内部大小
 					
        			var newfix = innerSize.scrollWidth-innerSize.clientWidth;
       
                	if ( newfix>0 ){

                		newWidth += newfix;
                		popupEl.style.width = newWidth + "px";
                		popup.setWidth( newWidth );
                		innerEl.style.height = newHeight + "px";
                		WIN_SizeInfo_Copy( popupEl , outerSize );//下拉框外部大小
                		WIN_SizeInfo_Copy( innerEl , innerSize );//下拉框内部大小
            			
                	}
        		}
    			
        		//默认往右扩展，如果超出窗体边框，修正为往左扩展
        		if( outerSize.offsetLeft + outerSize.scrollWidth > winSize.clientWidth ){
        			popupEl.style.left = (outerSize.offsetLeft-(newWidth-oldwidth))+ "px";;
        			WIN_SizeInfo_Copy( popupEl , outerSize );//下拉框外部大小
        		}
    		}

        }
 
    },
    relateTo: function (obj) {//级联绑定
        if ( obj instanceof mini.CodeSelect ){
        	var dicCode="";
        	try{
        		dicCode = obj.getCodeType();
        	}catch(e){
        		
        	}
        	if ( !dicCode || dicCode==""){
        		this.relatedObj = null;
            	this.hasRelate = false;
        		alert("二级下拉列表必须定义codeType属性");
        		return;
        	}
        	this.relatedObj = obj;
        	this.hasRelate = true;
        	var sf = this;
            setTimeout(function () {
                sf.changeRelate();
            }, 1);
        }else{
        	this.relatedObj = null;
        	this.hasRelate = false;
        	if ( obj && obj!="" ){
        		alert("只能绑定codeselect组件");
        	}
        }
    },
    
    changeRelate:function(){//级联动作
    	var obj = this.relatedObj;
    	if ( obj!=null ){
    		var url ='';
    		if(typeof(__DICRELATE_URL__)==='string'){
    			url =__DICRELATE_URL__;
    		}else{
    			alert("系统未指定级联接口(__DICRELATE_URL__)");
        		return;
    		}
    		var dicCode="";
        	try{
        		dicCode = obj.getCodeType();
        	}catch(e){
        		
        	}
        	if ( dicCode==""){
        		alert("二级下拉列表必须定义codeType属性");
        		return;
        	}
        	var parentCode = this.getValue();
        	obj.setUrl(url+"?dicCode="+dicCode+"&parentCode="+parentCode);
        	if (obj.getText()==""){
        		obj.select(0);
        	}
    	}else{
    		this.hasRelate = false;
    	}
    },
    
    doFilter:function(filterCode){
    	var url ='';
		if(typeof(__DICRELATE_URL__)==='string'){
			url =__DICRELATE_URL__;
		}else{
			alert("系统未指定代码接口(__DICRELATE_URL__)");
    		return;
		}
		var dicCode="";
    	try{
    		dicCode = obj.getCodeType();
    	}catch(e){
    		
    	}
    	if ( dicCode==""){
    		alert("二级下拉列表必须定义codeType属性");
    		return;
    	}
    	this.setUrl(url+"?dicCode="+dicCode+"&parentCode="+filterCode);
    	
    },
    
    
    getAttrs: function (el) {
        var attrs = mini.CodeSelect.superclass.getAttrs.call(this, el);
        mini._ParseString(el, attrs,["codeType"]);
        attrs.textField = this.textField;
        attrs.valueField = this.valueField;
        if(el.readOnly){
        	attrs.emptyText=" ";
        }
        return attrs;
    }
});
mini.regClass(mini.CodeSelect, 'codeselect');
