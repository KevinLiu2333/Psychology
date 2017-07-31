<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-1.12.3.min.js"></script>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    

   

</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<input type="hidden" id="js_ctx" value="${ctx }"></input>
<br/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2 class="page-header" style="margin-top: 5px">数据查询配置
            	<span style="float: right;padding-right: 10px"><a href="${ctx }/suite/config/query/toEditQuery" class="btn btn-warning" >新增数据查询</a></span>
            </h2>

            <div class="content" >
                <div class="panel-body">
                    <div class="adv-table">
                        <table  class="display table table-bordered table-striped" id="dynamic-table">
                            <thead>
                            <tr>
                            	<th style="vertical-align:middle; text-align:center;">序号</th>
                                <th style="vertical-align:middle; text-align:center;">配置名称</th>
                                <th style="vertical-align:middle; text-align:center;">配置时间</th>
                                <th style="vertical-align:middle; text-align:center;">是否绑定表单</th>
                                <th style="vertical-align:middle; text-align:center;">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${obj.list }"  var="queryConfig" varStatus="row">
                            	<tr>
									<td style="vertical-align:middle; text-align:center;">${row.count }</td>
									<td style="vertical-align:middle; text-align:center;">${queryConfig.name }</td>
									<td style="vertical-align:middle; text-align:center;"><fmt:formatDate value="${queryConfig.saveDate }" /></td>
									<c:if test="${not empty queryConfig.formId }">
										<td style="vertical-align:middle; text-align:center;">已绑定</td>
									</c:if>
									<c:if test="${empty queryConfig.formId }">
										<td style="vertical-align:middle; text-align:center;">取消绑定</td>
									</c:if>
									<td style="vertical-align:middle; text-align:center;">									
										<a href="#" onclick="preview('${queryConfig.saveId}','${queryConfig.formId }')" >预览</a>
										<a href="${ctx }/suite/config/query/toEditQuery?saveId=${queryConfig.saveId}"  >修改</a>
										<a href="${ctx }/suite/config/query/toDelQuery?saveId=${queryConfig.saveId}" >删除</a>
										<a href="${ctx }/suite/config/query/toBindForm?saveId=${queryConfig.saveId}"  >绑定表单</a>
										<a href="${ctx }/suite/config/query/CancelBindForm?saveId=${queryConfig.saveId}" >取消绑定表单</a>
									</td> 
								</tr>                           	
                            	</c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>


        </div>

    </div>
</div>
    <jsp:include page="/cj/foot.jsp"/>

</body>
 <link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
 <link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
 <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/swich/css/bootstrap3/bootstrap-switch.min.css"/>

<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" language="javascript" src="${ctx}/wddc/tiles/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/data-tables/DT_bootstrap.js"></script>
<!--dynamic table initialization -->
<script src="${ctx}/wddc/tiles/data-tables/dynamic_table_init.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/swich/js/bootstrap-switch.min.js"></script>
 <script type="text/javascript" >
    $(function(argument) {
      $('[type="checkbox"]').bootstrapSwitch();
    });
    function preview(saveId,formId){
       if(formId != null && formId != ""){
    	   window.open("${ctx }/suite/config/query/toResult?saveId="+saveId);
        }else{
			alert("请您绑定表单！");
        }
		
    	 //href="${ctx }/suite/config/query/toResult?saveId=${queryConfig.saveId}";
    }
    
    </script>

</html>
