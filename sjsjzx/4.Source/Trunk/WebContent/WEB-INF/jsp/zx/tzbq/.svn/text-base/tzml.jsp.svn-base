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
            .panel.success-box {
			    background: none repeat scroll 0 0 #87b87f;
			    box-shadow: 0 5px 0 rgba(14, 82, 2, 0.72);
			    color: #fff;
			}
			.panel.warning-box {
			    background: none repeat scroll 0 0 rgba(255, 183, 82, 0.82);
			    box-shadow: 0 5px 0 #ffb752;
			    color: #fff;
			}
			.panel.danger-box {
			    background: none repeat scroll 0 0 #d15b47;
			    box-shadow: 0 5px 0 rgba(255, 87, 34, 0.93);;
			    color: #fff;
			}
			.panel.purple-box {
			    background: none repeat scroll 0 0 #9585bf;
			    box-shadow: 0 5px 0 rgba(45, 32, 76, 0.69);
			    color: #fff;
			}
			h3{
				font-family:"微软雅黑";
			}
			.bg-img{
				background:url(${ctx}/skins/images/tztj.png) no-repeat right bottom;
				background-color:#5ab5de
			}
        </style>
</head>
<body>
<div class="col-md-12" style="margin-top:40px;">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h3 class="panel-title">台账目录</h3>
      </div> <!-- end of panel-title  -->
    
      <div class="panel-body" style="margin-top:30px">
      	<c:forEach items="${obj.list}" var="rule" varStatus="row">
        	<div class="col-md-3">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel widget-info-twt bg-img">
                                <h5  style="height: 80px;margin-top:10px">${rule.rulename }</h5>
                                <div class="followers" style="margin-bottom:5px">
                                    <p id="count_${row.index}">共有0条数据</p>
                                    <span>最后执行时间：<fmt:formatDate value="${rule.ruledate }" pattern="yyyy-MM-dd HH:mm:ss" /></span>
                                </div>
                                <a class="btn btn-follow" href="${ctx }/tz/toTzInfo?ruleId=${rule.ruleid}"><span><i></i>查看详细信息</span></a>
                            </div>
                        </div>
                    </div>
                </div>
         </c:forEach>

      </div>  <!-- end of panel-body  -->
      
    </div>
</div>
<script type="text/javascript" src="${ctx}/tiles/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/bootstrap/js/bootstrap.min.js"></script>
 <!-- 动态表格 -->
<script type="text/javascript" language="javascript" src="${ctx }/tiles/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx }/tiles/data-tables/DT_bootstrap.js"></script>
<script src="${ctx }/tiles/data-tables/dynamic_table_init.js"></script>
<script type="text/javascript">
	${obj.countList}.forEach(function(count,index){
		$("#count_"+index).html('共有'+count+'条数据');
	});
</script>
</body>
</html>