mini.namespace("MiniUtils");

MiniUtils.openErrPage = function(htmlcontent){
	var newWindow = window.open("","","status,height=600,width=800");
	newWindow.document.write(htmlcontent);
    newWindow.document.close();
}
MiniUtils.request = function(params){
	$.ajax({
         url: params.url,  
         type: "post",
         data: params.data,
         success: function (text) {
        	 try{
        		 var obj = mini.decode(text,true);
                 if ( obj.success ){
                	 if(typeof(params.success)=="function"){
                		 params.success(obj.result[0]);
           			 }
                 }else{
                	 var errMsg = "服务端返回格式有误";
                	 if(typeof(obj.errors[0].msg)!="undefined"){
    					errMsg = obj.errors[0].msg;
    				 }
                	 if(typeof(params.error)=="function"){
                		 params.error(errMsg);
          			 }
                 }
        	}catch(e){
        		if(text && text.indexOf("/login.jsp")>0){
        			var win = mini.open(__CONTEXT_PATH__+"/blank.jsp");
    				setTimeout(function() {
    					dealLoginTimeoutAjaxError(e,params,win);
					}, 500);
        		} else {
	        		if(e.message){
	        			errMsg = e.message;
	        		} else {
	        			errMsg = text;
	        		}
	        		if(typeof(params.error)=="function"){
	            		params.error(errMsg);
	      			}
        		}
        	 }
             
         },
         error: function (e,f,g) {
        	 var errMsg = e.responseText;
        	 if(typeof(params.error)=="function"){
        		 params.error(errMsg);
			 }
         }
      });
}

function dealLoginTimeoutAjaxError(e,params,win) {
	try{
		win.destroy();
	} catch (e) {}
	if(e.message){
		errMsg = e.message;
	} else {
		errMsg = text;
	}
	if(typeof(params.error)=="function"){
		params.error(errMsg);
	}
}

MiniUtils.asyncRequest = function(params){
	$.ajax({
         url: params.url,  
         type: "post",
         async:params.async,
         data: params.data,
         success: function (text) {
        	 try{
        		 var obj = mini.decode(text,true);
                 if ( obj.success ){
                	 if(typeof(params.success)=="function"){
                		 params.success(obj.result[0]);
           			 }
                 }else{
                	 var errMsg = "服务端返回格式有误";
                	 if(typeof(obj.errors[0].msg)!="undefined"){
    					errMsg = "服务端发生错误："+obj.errors[0].msg;
    				 }
                	 //alert(errMsg);
                	 if(typeof(params.error)=="function"){
                		 params.error(errMsg);
          			 }
                 }
        		 
        	 }catch(e){
        		 errMsg = "服务端发生错误："+text;
        		 if(typeof(params.error)=="function"){
            		 params.error(errMsg);
      			 }
        	 }
             
         },
         error: function (e,f,g) {
        	 var errMsg = "服务端发生异常：" + e.responseText;
        	//alert(errMsg);
        	 if(typeof(params.error)=="function"){
        		 params.error(errMsg);
			 }
         }
      });
}
MiniUtils.bindCodeSelect = function(parentid,childid,codeType){
	var parent = mini.get(parentid);
	var child = mini.get(childid);
	if ( (parent instanceof mini.CodeSelect) && (child instanceof mini.CodeSelect)){
		if(codeType){
			child.setCodeType(codeType);
		}
		parent.relateTo(child);
	}else{
		alert("只能对[mini-codeselect]组件进行绑定");
	}
}
MiniUtils.getTopWin = function(){
	var topwin = top["mainFrame"];
	if(!topwin)topwin=top;
	return topwin;
}
MiniUtils.error = function(msg,callback){
	mini.showMessageBox({
         title: "提示信息",
         iconCls: "mini-messagebox-error",
         buttons: ["ok"],
         message: msg,
         callback:function(action){
        	 if(typeof(callback)=="function"){
        		 try{
        			 callback(action);
        		 }catch(e){
        			 alert("回调函数执行错误");
        		 }
        	 }
         }
     });
}

MiniUtils.topError = function(msg,callback){
	if(top.mini) {
		top.mini.showMessageBox({
	         title: "提示信息",
	         iconCls: "mini-messagebox-error",
	         buttons: ["ok"],
	         message: msg,
	         callback:function(action){
	        	 if(typeof(callback)=="function"){
	        		 try{
	        			 callback(action);
	        		 }catch(e){
	        			 alert("回调函数执行错误");
	        		 }
	        	 }
	         }
	     });
	} else {
		mini.showMessageBox({
	         title: "提示信息",
	         iconCls: "mini-messagebox-error",
	         buttons: ["ok"],
	         message: msg,
	         callback:function(action){
	        	 if(typeof(callback)=="function"){
	        		 try{
	        			 callback(action);
	        		 }catch(e){
	        			 alert("回调函数执行错误");
	        		 }
	        	 }
	         }
	     });
	}
}

MiniUtils.alert = function(msg,callback){
	mini.showMessageBox({
        title: "提示信息",
        iconCls: "mini-messagebox-warning",
        buttons: ["ok"],
        message: msg,
        callback:function(action){
        	if(typeof(callback)=="function"){
	       		 try{
	       			 callback(action);
	       		 }catch(e){
	       			 alert("回调函数执行错误");
	       		 }
	       	 }
        }
    });
}

MiniUtils.topAlert = function(msg,callback){
	if(top.mini) {
		top.mini.showMessageBox({
	        title: "提示信息",
	        iconCls: "mini-messagebox-warning",
	        buttons: ["ok"],
	        message: msg,
	        callback:function(action){
	        	if(typeof(callback)=="function"){
		       		 try{
		       			 callback(action);
		       		 }catch(e){
		       			 alert("回调函数执行错误");
		       		 }
		       	 }
	        }
	    });
	} else {
		mini.showMessageBox({
	        title: "提示信息",
	        iconCls: "mini-messagebox-warning",
	        buttons: ["ok"],
	        message: msg,
	        callback:function(action){
	        	if(typeof(callback)=="function"){
		       		 try{
		       			 callback(action);
		       		 }catch(e){
		       			 alert("回调函数执行错误");
		       		 }
		       	 }
	        }
	    });
	}
}

MiniUtils.info = function(msg,callback){
	mini.showMessageBox({
        title: "提示信息",
        iconCls: "mini-messagebox-info",
        buttons: ["ok"],
        message: msg,
        callback:function(action){
        	if(typeof(callback)=="function"){
	       		 try{
	       			 callback(action);
	       		 }catch(e){
	       			 alert("回调函数执行错误");
	       		 }
	       	 }
        }
    });
}

MiniUtils.confirm = function(msg,callback){
	mini.confirm(msg, "确认信息",
        function (action) {
			if(typeof(callback)=="function"){
				if (action == "ok") {
                	try{
	   	       			 callback(true);
	   	       		 }catch(e){
	   	       			 alert("回调函数执行错误");
	   	       		 }
                } else {
                	try{
	   	       			 callback(false);
	   	       		 }catch(e){
	   	       			 alert("回调函数执行错误");
	   	       		 }
                }
			}
        }
    );
}

MiniUtils.prompt = function(msg,callback,isMulti){
	mini.prompt(msg, "请输入",
        function (action,value) {
			if(typeof(callback)=="function"){
				if (action == "ok") {
                	try{
	   	       			 callback(value);
	   	       		 }catch(e){
	   	       			 alert("回调函数执行错误");
	   	       		 }
                }
			}
        },isMulti
    );
}

MiniUtils.mask = function(){
	var topwin = MiniUtils.getTopWin();
	try{
		topwin.mask();
	}catch(e){
		
	}
}

MiniUtils.unmask = function(){
	var topwin = MiniUtils.getTopWin();
	try{
		topwin.unmask();
	}catch(e){
		
	}
}

MiniUtils.maskwin = function(msg){
	mini.mask({
        el: document.body,
        cls: 'mini-mask-loading',
        html: msg
    });
}

MiniUtils.unmaskwin = function(){
	mini.unmask(document.body);
}

MiniUtils.NVL = function(){
	for (i = 0; i < arguments.length; i++){
		if(arguments[i])return arguments[i];
	}
	return "";
}