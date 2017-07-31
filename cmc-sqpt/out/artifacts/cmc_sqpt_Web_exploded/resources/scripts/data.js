var DIC_IFSUBTYPE = [{ id: "01", text: "WSDL" },{ id: "02", text: "REST" }];
var DIC_IFTYPE = [{ id: "", text: "" },{ id: "01", text: "条线接口" },{ id: "02", text: "应用" },{ id: "03", text: "开放端口" },{ id: "04", text: "下级平台" }];
var DIC_RETURNTYPE = [{ id: 'string', text: 'string' }, { id: 'byte[]', text: 'byte[]'}];
var DIC_STATUS = [{ id: "", text: "" },{ id: "001", text: "正常" },{ id: "002", text: "无法联通/无法访问" },{ id: "003", text: "接口/应用有误" }];
var DIC_METHOD = [{ id: "post", text : "post" },{ id: "get", text : "get" },{ id: "put", text : "put" },{ id: "delete", text : "delete" }];
var DIC_ACCEPTYPE = [{ id: "string", text : "string" },{ id: "json", text : "json" },{ id: "xml", text : "xml" }];
var DIC_IFENCODE = [{ id: "0", text : "未加密" },{ id: "1", text : "加密" }];
function toDIC_STATUS(e) {
	return toLocalDic(e,DIC_STATUS);
}

function toLocalDic(e,type){
	var cVal;
	if(e.value){
		cVal = e.value;
	} else {
		if(typeof e==='object'){
			cVal = "";
		} else {
			cVal = e;
		}
	}
	for (var i = 0; i < type.length; i++) {
        var g = type[i];
        if (g.id == cVal) {
        	return g.text;
        }
    }
    return cVal;
}