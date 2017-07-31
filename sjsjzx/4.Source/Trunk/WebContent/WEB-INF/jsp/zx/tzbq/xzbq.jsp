<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
            <div class="panel panel-default" style="height:400px">
              <div class="panel-heading">
                <h3 class="panel-title">标签新增</h3>
              </div> <!-- end of panel-title  -->

              <div class="panel-body" style="margin-top:30px">
                <div class="col-md-12">
                    <div class="col-sm-2"  style="text-align:right;margin-top:8px">
                        <label class="control-label">标签名称：</label>
                    </div>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="input1" placeholder="请输入标签名称">
                    </div>
                </div>

                <div class="col-md-12" style="margin-top:40px">
                    <div class="col-sm-2"  style="text-align:right;margin-top:8px">
                        <label class="control-label">所属类别：</label>
                    </div>
                    <div class="col-sm-4">
                        <form role="form">
                            <div class="form-group">
                                <select class="form-control">
                                    <option>基础标签</option>
                                    <option>专题标签</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="col-sm-4">
                        <form role="form">
                            <div class="form-group">
                                <select class="form-control">
                                    <option>企业注册资金</option>
                                    <option>企业类别</option>
                                    <option>基本信息</option>
                                    <option>法定代表人</option>
                                    <option>诚信</option>
                                    <option>预警</option>
                                </select>
                            </div>
                        </form>
                    </div>
                </div>  <!-- end of select -->

                <div class="col-md-12 div-center" align="center" style="margin-top:100px">
                    <button type="button" class="btn btn-primary">保 存</button>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="button" class="btn btn-primary" onclick="javascript:history.back(-1);">取 消</button>
                </div>

              </div>  <!-- end of panel-body  -->
            </div>
        </div>
<script type="text/javascript" src="${ctx}/tiles/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/bootstrap/js/bootstrap.min.js"></script>
 <!-- 动态表格 -->
<script type="text/javascript" language="javascript" src="${ctx }/tiles/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx }/tiles/data-tables/DT_bootstrap.js"></script>
<script src="${ctx }/tiles/data-tables/dynamic_table_init.js"></script>
</body>
</html>