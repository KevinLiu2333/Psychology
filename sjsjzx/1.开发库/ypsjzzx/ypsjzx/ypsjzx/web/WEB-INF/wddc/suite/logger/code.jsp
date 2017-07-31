<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<!-- top of the page -->
<jsp:include page="/cj/header.jsp"/>
<form action="${ctx }/suite/unit/saveUnit" name="fwsqForm" id="fwsqForm">
<input type="hidden" name="userUnit.unitId" value="${obj.userUnit.unitId}"/>
<div class='container'>
    <h3 id="disable-responsive2" class="page-header">日志代码表</h3>
   
 <table class="table table-bordered">
            <thead>
              <tr>
                <th width="100px">大类代码</th>
                <th width="100px">大类名称</th>
                <th>小类代码和名称</th>
              </tr>
            </thead>
            <tbody>
             
              <tr>
                <td>xt</td>
                <td>系统消息</td>
                <td>xt001:登录      ||  xt002:登出      ||  xt003:公告      <br/>
                    xt101:登录错误      ||  xt102:服务错误
                </td>
              </tr>
              
                 <tr>
                <td>yj</td>
                <td>预警动态</td>
                <td></td>
              </tr>  
              
               <tr>
                <td>jh</td>
                <td>交换动态</td>
                <td></td>
              </tr>  
              
               <tr>
                <td>fw</td>
                <td>服务动态</td>
                <td>fw001:服务申请      ||  fw002:服务浏览      ||  fw003:服务调用      ||  fw004:服务审核      ||  fw005:服务发布</td>
              </tr>  
               
              <tr>
                <td>cz</td>
                <td>操作日志</td>
                <td>cz001:目录编制      ||  cz002:目录浏览      ||  cz003:目录发布        ||  cz004:目录审核<br/>
                    cz101:人口查询      ||  cz102:法人查询      ||  cz103:房屋查询        ||  cz104:法人资质查询        ||  cz105:法人处罚查询        ||  cz106:专题库查询   <br/> 
                    cz201:人口图表信息查看      ||  cz202:志愿者图表信息查看      ||  cz203:残疾人图表信息查看      ||  cz204:老龄人图表信息查看<br/>
                    cz301:人口报表信息查看      ||  cz302:侨民报表信息查看      ||  cz303:残疾人报表信息查看      ||  cz304:老龄人报表信息查看
                </td>
              </tr>  
              
               <tr>
                <td>ds</td>
                <td>定时任务</td>
                <td></td>
              </tr>
              
            
            </tbody>
          </table>
</div>
  

</form>
</body>
<script type="text/javascript">
function fwsq() {
	$("#fwsqForm").submit();
}
</script>
</html>
