<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/tiles/cj/title_setting.jsp" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.wonders.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>松江区政务数据中心</title>
    <%@ include file="/tiles/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/skins/css/wdsp.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/tiles/js/jquery-2.2.3.min.js"></script>
	<link href="${ctx}/skins/css/layout.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/skins/SYJS/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/skins/SYJS/lunbo.js"></script>
<script type="text/javascript" src="${ctx}/skins/SYJS/dianji.js"></script>
<style type="text/css">

.button{
	position: relative; 
    overflow: visible; 
    display: inline-block; 
    padding: 0.5em 1em; 
    border: 1px solid #6495ED; 
    margin: 0;
    width:120px;
    text-decoration: none; 
    text-shadow: 1px 1px 0 #fff; 
    font-family:"Microsoft YaHei", "微软雅黑", "宋体", Arial, Helvetica, sans-serif;
	font-size: 16px;
    color: #333; 
    font-weight:bold;
    white-space: nowrap; 
    cursor: pointer; 
    outline: none; 
    background-color: #fff;
    -webkit-background-clip: padding;
    -moz-background-clip: padding;
    -o-background-clip: padding-box; 
    /*background-clip: padding-box;*/ /* commented out due to Opera 11.10 bug */
    -webkit-border-radius: 0.2em; 
    -moz-border-radius: 0.2em; 
    border-radius: 0.2em; 
    /* IE hacks */
    zoom: 1; 
    *display: inline; 
}
 .red{
     color:red;
    }
.dfinput{width:345px; height:25px; line-height:25px; border-top:solid 1px #a7b5bc; border-left:solid 1px #a7b5bc; border-right:solid 1px #ced9df; border-bottom:solid 1px #ced9df; background:url(../images/inputbg.gif) repeat-x; text-indent:10px;}

.code 
{
 background:url(code_bg.jpg);
 font-family:Arial;
 font-style:italic;
 color:blue;
 font-size:30px;
 border:0;
 padding:2px 3px;
 letter-spacing:3px;
 font-weight:bolder;
 float:left;
 cursor:pointer;
 width:50px;
 height:10px;
 line-height:10px;
 text-align:center;
 vertical-align:middle;
 }
	.header_info_title{width:1002px; margin:0 auto; overflow:hidden; background:url(${ctx}/skins/images/index/header_bg_02.png) bottom right no-repeat;}
.header_info_title img{float:left;margin-top:17px;}
.header_info_title a{margin-left:7%;padding:0 20px;line-height:82px;color:#0f51b2; font-family:"微软雅黑";}
</style>
</head>
<body>
<jsp:include page="/common/index_public.jsp"/>
<!-- top of the page -->
<div class="banner" style="margin-bottom:10px;">
	<div class="banner_box">
    	<ul class="box_photo">
          <li><a href="#"><img src="${ctx}/skins/images/index/banner_01.jpg" alt="" ></a></li>
          <li><a href="#"><img src="${ctx}/skins/images/index/banner_02.jpg" alt=""></a></li>
          <li><a href="#"><img src="${ctx}/skins/images/index/banner_03.jpg" alt=""></a></li>
        </ul>
        <div class="banner_btn"><span class="current"></span><span></span><span></span></div>
    </div>
</div>
<!-- center of the page -->
<div id="content">
    <div class="container" id="m-for-apend">
        <!-- 菜单模块 -->
            <jsp:include page="main/module_admin.jsp"/>
            <jsp:include page="main/pt_admin.jsp"/>
        
        
       
    </div>
</div>
<!-- bottom of the page -->
<jsp:include page="/tiles/cj/foot.jsp"/>
</body>
</html>

