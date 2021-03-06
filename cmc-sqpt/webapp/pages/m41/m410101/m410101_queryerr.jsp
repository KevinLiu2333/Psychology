<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报文内容</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/pages/include/include.jsp?v=2"></jsp:include>
</head>

<body style="margin: 0;padding: 0;border: 0;width: 100%;height: 100%;">
	<div id="xmlForm" class="mini-fit" align="center">
		<input id="errmsg" name="errmsg" style="width:100%;height:100%;" class="mini-textarea" readonly="readonly" allowinput="false"/> 
	</div> 
<script type="text/javascript">

mini.parse();
form = new mini.Form("#xmlForm");
function SetData(data) {
	try{
		//跨页面传递的数据对象，克隆后才可以安全使用
		MiniUtils.maskwin("导入中...");
		data = mini.clone(data);
		MiniUtils.request({
			url: "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!loaderr.action",
			data: { iftransdetailid: data.iftransdetailid },
			success: function (text) {
				form.setData(text);
				MiniUtils.unmaskwin();	
			},
			error: function (text) {
				MiniUtils.unmaskwin();	
				mini.alert("提交失败，返回结果:" + text);
			}
		});
	}
	catch(e){
		mini.alert("未知异常："+e.responseText);
	}
}

</script>
</body>
</html>
