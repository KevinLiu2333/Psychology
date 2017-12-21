<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../resources/css/form.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/global.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/dtree.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../resources/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="../resources/js/dtree.js"></script>
<title></title>
<script type="text/javascript">
$(document).ready(function(){
	// tab页面
	$("li").each(function(index,item){
		$(item).children().bind("click",function(){
			$("li").each(function(){
				$(this).removeClass();
			});
			$(this).parent().addClass("changnow");
			var target = $(this).attr("name");
			$(".changtables").children().each(function(){
				var id = $(this).attr("id");
				if (id == target) {
					$(this).show();
				} else {
					$(this).hide();
				}
			});
		});
	});
	$("form:first").submit(function(){
		$("input[name='authorities']:checked").each(function(index, item){
			alert($(item).val());
		});
	});
});
</script>
</head>
<body>
	<form action="update" method="post">
		<input type="hidden" name="userId" value="${obj.user.userId }"/>
		<table border="0" cellpadding="0" cellspacing="0" class="tables">
			<tr>
				<td align="left">
					<ul id="secTable">
						<li class="changnow"><a href="#" name="changtable0"><span>人员信息</span></a></li>
						<li><a href="#" name="changtable1"><span>权限信息</span></a></li>
					</ul>
				</td>
			</tr>
		</table>
		<div class="changtables">
			<div id="changtable0" style="display: block;">
				<table border="0" cellpadding="0" cellspacing="1" class="tables">
					<tr>
						<td class="label_1" width="20%" align="center"><label>用户名:</label></td>
						<td class="label_2" width="80%"><input type="text" name="logonName" value="${obj.user.logonName }1"/></td>
					</tr>
					<tr>
						<td class="label_1" width="20%" align="center"><label>密码:</label></td>
						<td class="label_2" width="80%"><input type="password" name="password" value="${obj.user.password }"/></td>
					</tr>
					<tr>
						<td class="label_1" width="20%" align="center"><label>显示名称:</label></td>
						<td class="label_2" width="80%"><input type="text" name="displayName" value="${obj.user.displayName }"/></td>
					</tr>
					<tr>
						<td class="label_1" width="20%" align="center" colspan="2">
							<input type="submit" id="submit" value="保存"/>
						</td>
					</tr>
				</table>
			</div>
			<div id="changtable1" style="display: none;">
				<div class="dtree">
					<p><a href="javascript: d.openAll();">展开</a> | <a href="javascript: d.closeAll();">叠起</a></p>
					
					<script type="text/javascript">
						<!--
						d = new dTree('d');
						d.add(0,-1,'权限树');
						<c:forEach items="${obj.allAuthorities }" var="authority">
							d.add('${authority.nodeId}','${authority.nodePid}','authority','${authority.nodeId}','${authority.nodeDesc}',${authority.checked},false);
						</c:forEach>
						document.write(d);
						d.openAll();
						//-->
					</script>
				</div>
			</div>
		</div>
	</form>
</body>
</html>