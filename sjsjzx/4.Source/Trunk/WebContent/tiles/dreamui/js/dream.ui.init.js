$(function(){
	DWZ.init("/dwzfrag.xml", {
		loginUrl:"login.jsp", loginTitle:"登录",	// 弹出登录对话框
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"tiles/dreamui/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});