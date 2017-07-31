mini.namespace("Wssip.util2");

Wssip.util2.request = function(params) {
	$.ajax({
		url : params.url,
		type : "post",
		data : params.data,
		success : function(text) {
			if (text == "") {
				if (typeof (params.success) == "function") {
					params.success(null);
				}
				return;
			}
			var jsonResult = Wssip.util2.decode(text);
			if(jsonResult.success){
				if (typeof (params.success) == "function") {
					params.success(jsonResult.result);
				}
			}else{
				if (typeof (params.error) == "function") {
					params.error(jsonResult.errmsg);
				}
			}
		},
		error : function(e, f, g) {
			var errMsg = "服务端发生异常：\n" + e.responseText;
			Wssip.mini.showError(errMsg);
			if (typeof (params.error) == "function") {
				params.error(errMsg);
			}
		}
	});
}

Wssip.util2.decode = function(text) {
	var jsonResult = {};
	var temp = null;
	try {// 尝试转换成对象
		temp = mini.decode(text, true);
	}catch (e) {
		Wssip.util2.showError(text);
		jsonResult.errmsg = "服务端返回错误：" + text;
		jsonResult.success = false;
		return;
	}
	
	if( typeof (temp.pageSize) != "undefined" ){
		//分页结果,将结果转为miniui格式
		var result = {};
		result.total = temp.totalSize;
		result.data = temp.result;
		result.pageSize = temp.totalSize;
		jsonResult.result = result;
		jsonResult.success = true;
	}else if( temp.success ){
		var result = temp.result;
		jsonResult.result = result[0];
		//jsonResult.result = result;
		jsonResult.success = true;
	}else {
		var errMsg = "";
		if ( typeof (temp.success) == "undefined" ){
			errMsg = "服务器端返回格式有误：" + text;
		}else{
			var errors = temp.errors;
			if(errors){
				if(JSObjectIsArray(errors)){
					
					for ( var x = 0; x < errors.length; x++) {
						if (typeof (errors[x].msg) != "undefined") {
							errMsg += errors[x].msg + "\n";
						}
					}
					errMsg = errMsg;
				}else{
					if (typeof (errors.msg) != "undefined") {
						errMsg = errors.msg;
					}else{
						errMsg = errors;
					}
				}
			}else{
				errMsg = "服务器端返回错误，但错误原因缺失";
			}
			
		}
		jsonResult.success = false;
		jsonResult.errmsg = errMsg;
		alert( errMsg );
	}
	return jsonResult;

}

Wssip.util2.submit = function(params) {
	Wssip.util2.request(params);
}
Wssip.util2.showError = function(text) {
	var newWindow = window.open("", "错误信息", "height=500,width=600,top=0,scrollbars=yes,resizable=yes");
	newWindow.document.write(text);
	newWindow.document.close();
	var newWidth = 600;
	if (newWidth > screen.availWidth)
		newWidth = screen.availWidth;
	var newHeight = 500;
	if (newHeight > screen.availHeight)
		newHeight = screen.availHeight;
	newWindow.moveTo((screen.availWidth - newWidth) / 2, (screen.availHeight - newHeight) / 2);
	newWindow.resizeTo(newWidth, newHeight);
}


Wssip.util2.getModel = function(data) {
	var model = "";
	for ( var key in data) {
		model += "private String " + key.toString() + "; //<br><br>";
	}
	JSPrint(model, "VO模型");
}

Wssip.util2.getFilter = function(data) {
	var model = "";
	for ( var key in data) {
		var firstKey = key.charAt(0);
		var leftKey = key.substring(1);
		firstKey = firstKey.toUpperCase();
		var getKey = firstKey.concat(leftKey);
		model += "if (StringUtils.isNotBlank(filters.get" + getKey + "())) { //" + "<br>";
		model += "&nbsp;&nbsp;&nbsp;&nbsp;querySQL.append(\" and a." + key + " = '\").append(filters.get" + getKey
				+ "().trim()).append(\"'\");" + "<br>";
		model += "}<br>";
	}
	JSPrint(model, "过滤条件");
}


Wssip.util2.CheckPage = function(el) {
	if (!el)
		el = document;
	if (typeof el == "string") {
		el = mini.byId(el);
	}
	var tags = el.getElementsByTagName("*");
	var count = tags.length;
	var idArr = {};
	var idRetArr = {};
	var nameArr = {};
	var nameRetArr = {};
	var retId = true;
	var retName = true;
	for ( var i = 0; i < count; i++) {
		var id = tags[i].id;
		var name = tags[i].name;
		if (id) {
			if (idArr[id]) {
				idArr[id] += 1;
				idRetArr[id] = idArr[id];
				retId = false;
			} else {
				idArr[id] = 1;
			}
		}

		if (name) {
			if (nameArr[name]) {
				nameArr[name] += 1;
				nameRetArr[name] = nameArr[name];
				retName = false;
			} else {
				nameArr[name] = 1;
			}
		}
	}
	if (!retId) {
		JSObjectPrint(idRetArr, "重复ID");
	}
	if (!retName) {
		JSObjectPrint(nameRetArr, "重复NAME");
	}
	return retId && retName;
}
 
Wssip.util2.maskwin = function(msg){
	mini.mask({
        el: document.body,
        cls: 'mini-mask-loading',
        html: msg
    });
}

Wssip.util2.unmaskwin = function(){
	mini.unmask(document.body);
}

Wssip.util2.info = function(msg, cb) {
	mini.showMessageBox({
		title : "提示信息",
		iconCls : "mini-messagebox-info",
		buttons : [ "ok" ],
		message : msg.replace("\n", "<BR/>"),
		callback : function(action) {
			if (typeof (cb) == "function") {
				try {
					cb(action);
				} catch (e) {
					alert("回调函数执行错误");
				}
			}
		}
	});
}