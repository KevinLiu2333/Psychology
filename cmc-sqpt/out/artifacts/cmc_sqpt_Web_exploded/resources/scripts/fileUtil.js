mini.namespace('Wssip.file');
Wssip.file.upload = function(params) {

	$.ajaxFileUpload({
		url : params.url,// 用于文件上传的服务器端请求地址
		secureuri : false,// 一般设置为false
		fileElementId : params.id,// 文件上传控件的id属性 
		dataType : 'json',// 返回值类型 一般设置为json
		data : params.data,
		success : function(data, status) {
			if(data && data.success){
				if (typeof (params.success) == "function") {
					params.success(data.result);
				}
			}else{
				var errmsg = "文件导入失败"
				try {
					if(data && data.exception && data.exception[0] && data.exception[0].msg ){
						errmsg = data.exception[0].msg;
					}
				} catch(e){
					
				}
				if (typeof (params.error) == "function") {
					params.error(errmsg);
				}
			}
		},
		error : function(data, status, e) {
			if (typeof (params.error) == "function") {
				params.error(e.responseText);
			}
		}
	});

}
