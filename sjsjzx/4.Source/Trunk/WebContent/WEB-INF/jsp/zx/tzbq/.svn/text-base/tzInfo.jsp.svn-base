<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp" />
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
        <h3 class="panel-title">${obj.ruleName }</h3>
      </div> <!-- end of panel-title  -->

      <div class="panel-body" style="margin-top:20px">
        <table id="dynamic-table" class="table table-striped table-bordered table-hover bigger-110">
            <thead>
                <tr>
                    <th class="center">名称</th>
                    <th class="center">社会统一信用代码</th>
                    <th class="center">机构代码</th>
                    <th class="center">注册资金</th>
                    <th class="center">成立日期</th>
                    <th class="center">所属街镇</th>
                </tr>
            </thead>
            <tbody id="tdata">
            <c:forEach items="${obj.list}" var="info" varStatus="row">
                <tr>
                    <td><a href="#" onclick="view('${info.corecorpid}')">${info.corpname }</a></td>
                    <td>${info.uniscid }</td>
                    <td>${info.organcode }</td>
                    <td>${info.regcapital }</td>
                    <td><fmt:formatDate value="${info.establishdate }" pattern="yyyy-MM-dd" /></td>
                    <td><wd:dicvalue dicId="1077" dicCode="${info.jddm }" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

      </div>  <!-- end of panel-body  -->
      <form id="queryForm" name="queryForm" action="${ctx}/tz/toTzInfo" method="post" >
      	<input type="hidden" name="ruleId" value="${obj.ruleId}"/>
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
function view(id){
	var re = window.showModalDialog("${ctx}/query/viewCorpInfo?corpinfoid="+id,self,"dialogWidth=910px;dialogHeight=600px;status:no;");
	if(re){
		query();
	}
}
</script>
</body>
</html>