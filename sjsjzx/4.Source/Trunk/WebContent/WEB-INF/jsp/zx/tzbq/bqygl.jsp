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
        <h3 class="panel-title col-md-10" style="margin-top:10px">标签云管理</h3>
        <div align="right">
            <button class="btn btn-info" onclick="window.location.href='${ctx}/tz/toAddTag'">新 增</button>
            <button class="btn btn-info" onclick="update()">失 效</button>
        </div>
      </div> <!-- end of panel-title  -->
      <form id="queryForm" name="queryForm" action="${ctx}/tz/toTagManage" method="post" >
    
      <div class="panel-body" style="margin-top:30px">
        <table id="dynamic-table" class="table table-striped table-bordered table-hover bigger-110">
            <thead>
                <tr>
                    <th class="center" width="25%"></th>
                    <th class="center" width="25%">标签名</th>
                    <th class="center" width="25%">所属类别</th>
                    <th class="center" width="25%">状态</th>
                </tr>
            </thead>
            <tbody id="tdata">
             <c:forEach items="${obj.list}" var="tag" varStatus="row">
                <tr>
                    <td><input type="radio" name="tagid" value="${tag.id }"></td>
                    <td>${tag.tagName }</td>
                    <td>${tag.tagAttr }</td>
                    <td>${tag.tagStatus == 0?"失效":"正常"}</td>
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
  function update(){
	  if(confirm("确定修改吗？")){
		  $("#queryForm").submit();
	  } 
  }
</script>
</body>
</html>