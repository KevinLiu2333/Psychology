<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${ctx }/skins/css/form.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/skins/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/skins/css/dtree.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/jquery-easyui/jquery-1.8.0.min.js"></script>  
<script type="text/javascript" src="${ctx }/tiles/scripts/dtree.js"></script>
<script type="text/javascript" src="${ctx }/tiles/scripts/selectAndCheck.js"></script>
<title>角色信息</title>
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
});
</script>
</head>
<body>
	<form action="update" method="post" name="userInfoForm" onsubmit="onSubmit()">
		<input type="hidden" name="role.roleId" value="${obj.role.roleId }"/>
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
						<td class="label_1" width="20%" align="center"><label>角色描述:</label></td>
						<td class="label_2" width="80%"><input type="text" name="role.roleDesc" value="${obj.role.roleDesc }"/></td>
					</tr>
				</table>
				<div>
		        	<label>角色包含的用户</label>
		        </div>
		        <div>
		        	<table class="contentTable" cellspacing="1" cellpadding="0">
	       				<tr class="contentRowDeep">
	         				<td>
	           					<label>可选列表：</label><br>
	           					<select name="userGroupListFrom" style="width:240px;height:100px" ondblclick="pub_moveSelected(userInfoForm.userGroupListFrom,userInfoForm.userGroupList)" multiple>
	           						<c:forEach items="${obj.otherUsers}" var="user" >
	           							<option value="${user.userId}">${user.displayName}</option>
	           						</c:forEach>
								</select>
	         				</td>
	         				<td width="30px" align="center">
	          					<input type="button" class="smallbutton" style="width:15px:height:15px" value="→" onclick="pub_moveSelected(userInfoForm.userGroupListFrom,userInfoForm.userGroupList)">
	          					<br><br>
	          					<input type="button" class="smallbutton" style="width:15px:height:15px" value="←" onclick="pub_moveSelected(userInfoForm.userGroupList,userInfoForm.userGroupListFrom)">
	         				</td>
	         				<td >
	           					<label>已选列表：</label><br/>
	         					<select name="userGroupList" style="width:240px;height:100px" ondblclick="pub_moveSelected(userInfoForm.userGroupList,userInfoForm.userGroupListFrom)" multiple>
	           						<c:forEach items="${obj.role.users}" var="user" >
	           							<option value="${user.userId}">${user.displayName}</option>
	           						</c:forEach>
	  							</select>
	         				</td>
	       				</tr>
	     			</table>
		        </div>
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
		<input type="submit" value="提交" />
	</form>
</body>
<script>
function onSubmit(){
	pub_selectAll(userInfoForm.userGroupList);
}
function pub_selectAll(objName){
	for(var i=0;i<objName.options.length;i++){
		objName.options[i].selected = true
	}
}
function pub_moveSelected(fromObject, toObject)
{
    pub_copySelected(fromObject,toObject);
    pub_removeSelected(fromObject);
    //fromObject.selectedIndex = -1;
}
</script>
</html>