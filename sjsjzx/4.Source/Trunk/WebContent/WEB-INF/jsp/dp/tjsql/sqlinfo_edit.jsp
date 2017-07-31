<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.wonders.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>松江政务数据中心</title>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/json.css">
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/tiles/Validform/js/Validform_v5.3.2.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/tiles/Validform/css/style_cun.css"/>
</head>

<body >
<form method="post" id="sqlForm" action="savesqlinfo" >
   <!-- 隐藏域开始 -->
  <input type="hidden" name="info.sqlid" value="${obj.info.sqlid }"/>
  <!-- 隐藏域结束-->
    	<table width="80%" class="table_multilist" border="1" cellspacing="0" align="center">
    		<tr>
				<td class="input_title" align="center" colspan="4">添加数据SQL管理</td>
			</tr>
    		<tr>
    			<td align="right">描述：</td>
    			<td align="left" colspan="3">&nbsp;
    				<input type="text" class="dfinput" id="title" name="info.title" value="${obj.info.title}"  style="width: 90%" datatype="*1-50" errormsg="请输入25个以内的汉字" nullmsg="申请人姓名不能为空"/>
    			</td>
    			<td width="25%"></td>
    		</tr>
    		<tr>
    			<td width="25%" align="right">统计类型：</td>
    			<td width="25%">&nbsp;
    				<!-- 
    				<select id="diccode" name="info.diccode"></select>
    				 -->
    				 <wd:select id="type" name="info.diccode" className="selectInput" style="width:300px;height:25px;" dicCode="<%=Constants.DIC_ACOUNT_TYPE%>" initValue="---请选择---" defaultValue="${obj.info.diccode}" onchange="changeType()"/>
    			</td>
    			<td width="25%" align="right">定时规则：</td>
    			<td align="left" width="25%">&nbsp;
    				<input type="text" class="dfinput" id="applyName" name="info.timing" value="${obj.info.timing}"  style="width: 90%" datatype="*1-50" errormsg="请输入25个以内的汉字" nullmsg="申请人姓名不能为空"/>
    			</td>
    		</tr>
    		<tr id="showLabel">
				<td align="center" colspan="4"><font color="red">注：</font><font color="green">法人库统计</font>(法人总数+法人登记数+注册地与办公地差异数工3个sql。)<p>
				<font color="green">人口统计</font>(实有人数+60岁以上老人+当月增长+当月减少共4个sql。)<p>
				<font color="green">年龄结构统计</font>(男女按阶段统计分别7个sql。)<p>
				<font color="green">人口比例</font>(户籍人口+外来人口共2个sql。)<p>
				<font color="red">每个sql之间用"|"隔开。</font></td>
			</tr>
    		<tr id="gg">
				<td align="right">SQL语句：</td>
				<td align="left" colspan="3">&nbsp;
					<textarea class="dftextarea" rows="6" id="sql" cols="100%" name="info.sqlvalue" >${obj.info.sqlvalue}</textarea>
				</td>
			</tr>
			
			<tr id="tabtr" style="display: none;">
				<td colspan="4">
					<div id="tabdiv">
					
					</div>
				</td>
			</tr>
    	</table>
    		
	 	<p align="center">
	 	<input type="button" class="button"  value="测试SQL" onclick="testsql()" /> 
		<input type="submit" class="button"  value="保存" /> 
		<input type="button" class="button" onclick="doBack()" value="返回" /> 
	 </p>
</form>
</body>
<script type="text/javascript">
	//统计类型
	function changeType(){
		if($('#type').val()=='1' || $('#type').val()=='5' || $('#type').val()=='6' || $('#type').val()=='7' || 
				$('#type').val()=='9'){
			$('#showLabel').show();
		} else {
			$('#showLabel').hide();
		}
	}
  
  
	$(function(){
	  $('#showLabel').hide();
	});
  
  	function testsql(){
  		var sql=$("#sql").val();
  		
	$.ajax({
		type:"post",
		url:"${ctx}/sqlinfo/testSql",
		data:{
			"sqlvalue":sql
			},
			success:function(result){
			result = eval("("+result+")");
				$("#tabdiv").html(result.total);
				var s=result.json;
				for (var key in s) { 
              alert(key+" : "+s[key]); 
          }
				$("#tabtr").show();
			}
		});
  	}
  	
  	function doBack(){
  		window.location.href="${ctx}/sqlinfo/tosqlinfo";
  	}
  </script>
</html>
