/**
 * min-WssipAutoComplete 自动完成标签
 * 
 * 版本修正：
 * autoquery 允许取消自动查询，只能按回车查询
 * fixinput 允许选择记录后不清空内容
 * 修正最小字符无效的bug
 * 修正第二次操作同一人用鼠标选择无法触发change事件的bug
 * 加载信息默认中文显示
 * 增加setInputText用于强行设置文本框内容
 * JSPrint(new mini.AutoComplete(),"minAutoComplete");
 * __OnInputKeyDown： 搜索 ("keydown"  所在的函数即为__OnInputKeyDown
 * _tryQuery: 查找doQuery函数，其内部调用的即为_tryQuery
 * __OnItemClick: 查找beforeitemclick，所在函数即为__OnItemClick
 */
mini.WssipAutoComplete = function () {
    mini.WssipAutoComplete.superclass.constructor.call(this);
}

mini.extend(mini.WssipAutoComplete, mini.AutoComplete, {
	uiCls: "mini-autocomplete-wssip",
	delay:650,
	minChars:2,
	searchField:"query",
	popupLoadingText: "<span class='mini-textboxlist-popup-loading'>查询中...</span>",
    popupErrorText: "<span class='mini-textboxlist-popup-error'>查询出错</span>",
    popupEmptyText: "<span class='mini-textboxlist-popup-noresult'>无符合条件的数据</span>",
    getData: function () {
    	if(!this.data||!(this.data.length))return [];
    	return this.data;  
    },
    focus:function(){
    	return;
    },
    setText:function(text){
    	//this.text = text;
    	if(this.fixinput){
    		
    	}else{
    		mini.WssipAutoComplete.superclass.setText.call(this,text);
    	}
    },
//    innerValue:0,
//    getValue:function(){//保证value变化
//    	this.innerValue = this.innerValue +1;
//    	if(this.innerValue>1000)this.innerValue=1;
//    	return this.innerValue;
//    },
    setInputText:function(text){
    	mini.WssipAutoComplete.superclass.setText.call(this,text);
    },
    
    OOl1O:function (e) {//fix __OnInputKeyDown
    	if(!this.isShowPopup() && (e.keyCode == 13 || e.keyCode == 40 ) ){
    		var ex = { htmlEvent: e };
            this.fire("keydown", ex);
    		this.doQueryNow(this.getText());
    	}else{
    		mini.WssipAutoComplete.superclass.OOl1O.call(this,e);//fix
    	}
    	
    },
    doQueryNow:function(text){
    	var olddelay = this.delay;
    	this.delay = 100;
    	mini.WssipAutoComplete.superclass.doQuery.call(this);
    	this.delay = olddelay;
    },
    O0o00:function(oldText) {//_tryQuery
    	//增加延时判断，如果禁用了自动查询，那么只有延时为100时才继续
    	if(!this.autoquery && this.delay >100 )return;
    	var sf = this;
	    if (this._queryTimer) {
	        clearTimeout(this._queryTimer);
	        this._queryTimer = null
	    }
	    this._queryTimer = setTimeout(function () {
	    	
            var text = sf.getText();
            var len = text.length;
            try{
            	len = text.lengthb();
            }catch(e){
            }
            
            if(len<sf.minChars){
            	//位数不足，不查询
            }else{
            	sf.showPopup("loading");
            	sf.oOlo0(text); //_doQuery,参考_tryQuery代码修改
            }            
            
        }, this.delay);
        //this.showPopup("loading"); 移到里面去
	    
    },
    l1o1ol: function(e){//__OnItemClick
    	this.setValue("123");
    	mini.WssipAutoComplete.superclass.l1o1ol.call(this,e);
    },
    autoquery: true,
    setAutoquery: function (value) {
    	this.autoquery = value;
    },
    getAutoquery: function () {
        return this.autoquery;
    },
	fixinput:true,
	setFixinput: function (value) {
    	this.fixinput = value;
    },
    getFixinput: function () {
        return this.fixinput;
    },
	getAttrs: function (el) {
		var attrs = mini.WssipAutoComplete.superclass.getAttrs.call(this, el);
		mini._ParseBool(el, attrs,["autoquery","fixinput"] );
		mini._ParseInt(el, attrs,["minChars"]);
		return attrs;
	}
}
);
mini.regClass(mini.WssipAutoComplete, 'wssipautocomplete');