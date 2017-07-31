/**
 * miniUI对象属性扩展
 */

mini.Form.prototype.setReadOnly = function(value) {
	
	var controls = this.getFields();
    for (var i = 0, l = controls.length; i < l; i++) {
        var c = controls[i];
        if (c.setReadOnly) c.setReadOnly(value);
        if (value && c.setIsValid) c.setIsValid(value);  
        if(c.removeCls){
        	c.removeCls("asLabel");
        	c.removeCls("readonly");
        }
        if(!c.emptyTextOrg) c.emptyTextOrg = c.emptyText;
        if(value){
        	if (c.addCls) c.addCls("readonly");  
        	if (c.setEmptyText) c.setEmptyText("　");
        }else{
        	if (c.setEmptyText) c.setEmptyText(c.emptyTextOrg);
        }
        
    }
    
}
mini.Form.prototype.setAsLabel = function(value) {
	
	var controls = this.getFields();
    for (var i = 0, l = controls.length; i < l; i++) {
        var c = controls[i];
        if (c.setReadOnly) c.setReadOnly(value);
        if (value && c.setIsValid) c.setIsValid(value);
        if(c.removeCls){
        	c.removeCls("asLabel");
        	c.removeCls("readonly");
        }
        if(!c.emptyTextOrg) c.emptyTextOrg = c.emptyText;
        if(value){
        	if (c.addCls) c.addCls("asLabel");
        	if (c.setEmptyText) c.setEmptyText("　");
        }else{
        	if (c.setEmptyText) c.setEmptyText(c.emptyTextOrg);
        }

    }
	
}


mini.CheckBoxList.prototype.getSelectedText = function(separator) {
	var textField = this.getTextField();
	if (!textField)
		return "";

	var selecteds = this.getSelecteds();
	if (selecteds.length == 0)
		return "";
	if (!separator)
		separator = "，";
	var text = selecteds[0][textField];
	for ( var i = 1; i < selecteds.length; i++) {
		text = text + separator + selecteds[i][textField]
	}
	return text;
}

mini.CheckBoxList.prototype.getSelectedValue = function(separator) {
	var valueField = this.getValueField();
	if (!valueField)
		return "";

	var selecteds = this.getSelecteds();
	if (selecteds.length == 0)
		return "";
	if (!separator)
		separator = ",";
	var value = selecteds[0][valueField];
	for ( var i = 1; i < selecteds.length; i++) {
		value = value + separator + selecteds[i][valueField]
	}
	return value;
}





/**
 * 多选输入框 JSPrint(new mini.TextBoxList(),"minTextBoxList");
 */

mini.WssipTextBoxList = function() {
	mini.WssipTextBoxList.superclass.constructor.call(this);
}
mini.WssipTextBoxList.prototype._xxxfuncxxx_ = null;//方便eclipse解析
mini.extend(mini.WssipTextBoxList, mini.TextBoxList, {
	uiCls : "mini-textboxlist-wssip",
	delay : 650,
	popupLoadingText : "<span class='mini-textboxlist-popup-loading'>查询中...</span>",
	popupErrorText : "<span class='mini-textboxlist-popup-error'>查询出错</span>",
	popupEmptyText : "<span class='mini-textboxlist-popup-noresult'>无数据</span>",
	Ool0O1 : function(_) {//_startQuery
		var sf = this;
		setTimeout(function() {
			sf.olOOo()
		}, 1);
		
		this.oOll();
		this._loading = true;
		this.delayTimer = setTimeout(function() {
			var _ = sf.o0Olo.value;
			sf[lOOlO]("loading");
			sf.oOlo0()
		}, this.delay)
	},
	doQueryNow:function(){
    	var olddelay = this.delay;
    	this.delay = 100;
    	mini.WssipTextBoxList.superclass.doQuery.call(this);
    	this.delay = olddelay;
    },
	OOl1O:function(e){//__OnInputKeyDown
		if(!this.isShowPopup && (e.keyCode == 13) ){
    		var ex = { htmlEvent: e };
            this.fire("keydown", ex);
            this.doQueryNow();
    	}else{
    		mini.WssipTextBoxList.superclass.OOl1O.call(this,e);//fix
    	}
		
	},
	getAttrs : function(el) {
		var attrs = mini.WssipTextBoxList.superclass.getAttrs.call(this, el);
		return attrs;
	}
});

mini.regClass(mini.WssipTextBoxList, 'wssiptextboxlist');