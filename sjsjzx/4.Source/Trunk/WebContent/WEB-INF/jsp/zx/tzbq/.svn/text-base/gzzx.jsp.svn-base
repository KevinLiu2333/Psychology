<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${ctx}/tiles/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/tiles/bootstrap/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/tiles/data-tables/css/demo_page.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/tiles/data-tables/css/demo_table.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/tiles/data-tables/DT_bootstrap.css" rel="stylesheet" type="text/css" />
<style type="text/css">
            #ul > li{
                border: solid 1px #a29e9e;
            }
            .sub-menu-list > li{
                border-top: solid 1px #a29e9e;
            }
            .panel-default>.panel-heading {
                color: #fff;
                background-color: #2e97cd;
                border-color: #ddd;
            }
            .div-center{
                margin: 20px auto;
            }
        </style>
</head>
<body>
<div class="col-md-12" style="margin-top:40px;">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h3 class="panel-title col-md-8" style="margin-top:10px">规则执行</h3>
        <div align="right">
            <button class="btn btn-info" onclick="goToAddRule()">新 增</button>
            <button class="btn btn-info" onclick="executeRule()">立即执行</button>
            <button class="btn btn-info" onclick="deleteRule()">删 除</button>
        </div>
      </div> <!-- end of panel-title  -->
		<form id="queryForm" name="queryForm" action="${ctx}/tz/toRuleExecute" method="post" >
      <div class="panel-body" style="margin-top:30px">
        <table id="dynamic-table" class="table table-striped table-bordered table-hover bigger-110">
            <thead>
                <tr>
                    <th class="center"></th>
                    <th class="center">规则名称</th>
                    <th class="center">执行方式</th>
                    <th class="center">执行时间</th>
                    <th class="center"></th>
                </tr>
            </thead>
            <tbody id="tdata">
             <c:forEach items="${obj.list}" var="rule" varStatus="row">
                <tr>
                	
                    <td align="center"><input type="radio" name="ruleId" value="${rule.ruleid }"></td>
                    <td><a href="javascript:void(0)" onclick="edit('${rule.ruleid}')">${rule.rulename }</a></td>
                    <td>${rule.executeType == 0?"手动执行":"立即执行"}</td>
                    <td><fmt:formatDate value="${rule.ruledate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <c:if test="${rule.ruledate == null||rule.ruledate == ''}">
                    	<td></td>
                    </c:if>
                    <c:if test="${rule.ruledate != null}">
                    	<td><a href="javascript:void(0)" onclick="exportData('${rule.ruleid}','${rule.rulename }')">导出</a></td>
                    </c:if> 
                </tr>
             </c:forEach>
            </tbody>
        </table>
      </div>  <!-- end of panel-body  -->
      <div>
			<table width="96%" class="tables">
				<tr>
					<td><jsp:include page="/common/pzgl/pager-iframe.jsp"></jsp:include>
					</td>
				</tr>
			</table>
		</div>
      </form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/tiles/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/bootstrap/js/bootstrap.min.js"></script>
 <!-- 动态表格 -->
<script type="text/javascript" language="javascript" src="${ctx }/tiles/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx }/tiles/data-tables/DT_bootstrap.js"></script>
<script src="${ctx }/tiles/data-tables/dynamic_table_init.js"></script>
<script type="text/javascript">
	function goToAddRule(){
		window.location.href="${ctx}/tz/toAddRule";
	}
	function executeRule(){
		var ruleId = $('[type="radio"]:checked').val();
		if(confirm("确定立即执行吗？")){
			$.post("${ctx}/tz/executeRule",{ruleId:ruleId},function(){
				location.reload();
				});
		}
	}
	function deleteRule(){
		var ruleId = $('[type="radio"]:checked').val();
		if(confirm("确定删除吗？")){
			$.post("${ctx}/tz/deleteRule",{ruleId:ruleId},function(){
				location.reload();
				});
		}
	}
	function edit(ruleId){
		window.location.href = "${ctx}/tz/toAddRule?ruleId="+ruleId;
	}
	
	function exportData(ruleId,rulename){
		$.post("${ctx}/tz/exportData",{ruleId:ruleId},function(){
				alert("excel导出成功，路径为D:\\"+rulename+".xls");
			});
	}
	
</script>
</body>
</html>