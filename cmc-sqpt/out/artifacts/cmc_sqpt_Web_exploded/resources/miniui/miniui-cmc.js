//**************************************************************//
// 说明：引入mini_doload自定义方法，统一解决后台格式以及异常问题
// 版本：miniui.js 3.2+
//**************************************************************//
mini_debugger = false;
mini_doload = function (e) {

	// e.text 返回的文本字符串
    // e.result JSON对象。如果不生成result，组件将自己解析text。
    // e.sender 事件激发对象
    // e.options ajax提交时的参数对象
	function isArray(o) {
        return Object.prototype.toString.call(o) === '[object Array]';
    }

	function getValue(obj){
		if(obj === null || obj === undefined){
			return "";
		}else{
			return obj;
		}
	}

	try{
		var obj = mini.decode(e.text);

		var success, result, total, data, errors, error, errorMsg;

		if (isArray(obj)){ // 格式为[]
			e.result = obj;
		}else{
			// 格式为{total:** , data:[**]}
			if(obj.result === "" || obj.result === null || obj.result === undefined){

				if(obj.total === "" || obj.total === null || obj.total === undefined){
					total = "";
				}else{
					total = obj.total;
				}

				if(obj.data === "" || obj.data === null || obj.data === undefined){
					data = [];
				}else{
					data =obj.data;
				}

			}else{
			// 格式 为{"success":**, result:[**], errors:[{msg:****}], target:"" }
				var result = obj.result[0];
				if(result === "" || result === null || result === undefined){
					data = e.text;
				}else{

					if(result.total === "" || result.total === null || result.total === undefined){
						total = "";
					}else{
						total = result.total;
					}

					if(result.data === "" || result.data === null || result.data === undefined){
						data = [];
					}else{
						data = result.data;
					}

					if(obj.success === "" || obj.success === null || obj.success === undefined){
						success = ""
					}else{
						success = obj.success;
					}

					if(obj.errors === ""||obj.errors === null || obj.errors === undefined){
						errorMsg = "";
					}else{
						errorMsg = obj.errors[0].msg;
					}
				}
			}

			if (e.sender.type == "datatable") {
				e.result = { total: total, data: data, error: success, errorMsg: errorMsg };
	        } else {
	            e.result = data;
	        }
		}

	}catch(ex){
		// 非JSON数据直接显示内容
		// var text = request.responseText;
		// var status = request.status
		// var url = settings.url;
		// alert("后台消息: " + status + " " + url + "" + text);
		// alert(ex);
		//alert("[后台消息:]  " +text);
		// e.result = text;
	};
}

// *********************************************//
// 说明：全局统一判断后台消息是否符合格式 //
// *********************************************//
$(document).ajaxError(function(event,request, settings){
	var text = request.responseText;
	var status = request.status
	var url = settings.url;
	if(status == "0"){
			
	} else {
		alert("后台发生错误: " + status  + "\n" +  url + "\n" +  text);
	}
})


$(document).ajaxComplete(function (evt, request, settings) {
	var text = request.responseText;
	var url = settings.url;
	var obj;
	try{
		obj = mini.decode(text);
		if(obj.success != "undefined" ){
			if(obj.success == false){
				if(typeof(obj.errors) !=='undefined'){
					if(typeof(obj.errors[0].msg)==="undefined"){
						var errMsg = "服务端返回错误的格式有误   \n" + url ;
						alert(errMsg);
					}else{
						var errMsg = obj.errors[0].msg;
						//alert("服务端异常：" + errMsg +  "\n" + url);
						//mini.alert(errMsg,"错误");
		    		}
				}
	    	}
		}

	}catch(e){
		//if(url.indexOf("?")>0)
		if(mini_debugger)
			alert("返回数据非JSON格式错误："  + "\n" + url + "\n"  + text + "\n" + e);
	}

})

// ******************************************************//
// 说明：返回结果判断是否符合格式
// 格式符合:
// {"success":true, "result":[], "errors":[{msg:***}]}
// ******************************************************//
var CmcAjax = {};
CmcAjax.checkSuccess = function(data, e){
	var url = e.url;
	try{
		obj = mini.decode(data);
		if(obj.success != "undefined" ){
			if(obj.success == false){
				if(typeof(obj.errors[0].msg)==="undefined"){
					var errMsg = "服务端返回错误的格式有误   \n" + url ;
					alert(errMsg);
					return false;
				}else{
					var errMsg = obj.errors[0].msg;
					//alert("服务端异常：" + errMsg +  "\n" + url);
					mini.alert(errMsg,"错误");
					return false;
	    		}
	    	}
		}
		return true;
	}catch(e){
		alert("返回数据非JSON格式错误：\n " + data + "\n" + e + "\n" + url );
		return false;
	}
}

/**************条线使用*********************/
// 页面控件全部只读
function label2Model() {
	var form = new mini.Form("#applyForm");
    var fields = form.getFields();
    for (var i = 0, l = fields.length; i < l; i++) {
        var c = fields[i];
        if (c.setReadOnly) c.setReadOnly(true);     //只读
        if (c.setIsValid) c.setIsValid(true);      //去除错误提示
        if (c.addCls) c.addCls("asLabel2");          //增加asLabel2外观
    }
}

/**************事项导航*********************/
//按钮选中
function setTab(name,cursel,n){
 for(i=1;i<=n;i++){
  var menu=document.getElementById(name+i);
  menu.className=i==cursel?"hover":"";
 }
}
