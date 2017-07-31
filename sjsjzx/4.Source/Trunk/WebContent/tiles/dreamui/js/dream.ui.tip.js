/**
 * 用户替换表单验证中，错误提示的问题：
 * 
 * 原有方式通过 在input后insert一个<span class="error">错误提示信息</span>
 * 由于受限于表格的布局方式，以及表单中过多的控件验证导致错误提示样式混乱等问题
 * 
 * 现将验证提示信息修改为鼠标悬停，弹出tip提示错误信息
 * 
 * 注意： 如果使用tip错误提示方式，在index.jsp页面中需引入 jquery.validate.tip.js
 * 
 * 如使用原生的提示方式，请将jquery.validate.tip.js 替换为 jquery.validate.js
 * 
 */
$(function() {
	var xOffset = -20; // x distance from mouse
	var yOffset = 20; // y distance from mouse

	$.fn.extend({
		tip : function(message) {
			var $this = $(this);
			$this.hover(
					function(e) {
						var top = (e.pageY + yOffset);
						var left = (e.pageX + xOffset);
						$('body').append('<p id="vtip"><img id="vtipArrow" src="tiles/dreamui/themes/default/images/vtip_arrow.png"/>' + message + '</p>');
						$('p#vtip').css("top", top + "px").css("left", left + "px");
						$('p#vtip').bgiframe();
					}, function() {
						$("p#vtip").remove();
					}
			).mousemove(
				function(e) {
					var top = (e.pageY + yOffset);
					var left = (e.pageX + xOffset);
					$("p#vtip").css("top", top + "px").css("left", left + "px");
				}
			)
		},
		untip : function() {
			var $this = $(this);
			$("p#vtip").remove();
			$this.unbind('hover').unbind('mousemove');
		}
	})
});