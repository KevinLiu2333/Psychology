/*
 * 用于处理管理员页面的js.
 * @author Gray
 */
//页面加载完成后执行
$(function(){
	//菜单栏的动画
	$(".menu dl dt").toggle(function() {//下降
		$(this).next().slideDown();
		$(this).removeClass("menu_tCon");
	},function() {//上升
		$(this).next().slideUp();
		$(this).addClass("menu_tCon");
	});
	//赋值图片基础路径
	var loacation = "gray/admin/images/";
	//子菜单单击事件
	$(".menu dl dd a").click(
			function() {
				//设置路径
				var srcBefore = loacation
						+ $(".menu_cCon img").attr("pic-id") + ".png";
				//设置未被单击样式图片
				$(".menu_cCon img").attr("src", srcBefore);
				//删除所有子菜单样式,对单机子菜单添加样式
				$(".menu dl dd a").removeClass("menu_cCon").eq(
						$(".menu dl dd a").index(this)).addClass("menu_cCon");
				//设置已被单机样式图片
				var src = loacation
						+ $(this).children("img").attr("pic-id") + "w.png";
				//设置被单击子菜单样式
				$(this).children("img").attr("src", src);
			});
	//显示所在位置
	function changeposition(position) {
		//通过ID获取显示框
		var p = document.getElementById("position");
		//设置内容
		p.innerHTML = "当前位置：" + position;
	}
	//设置框架显示内容
	function setiframe(url) {
		//显示内容路径
		$('#main_frame').attr('src', '${ctx}/frame?url=' + url);
	}
})