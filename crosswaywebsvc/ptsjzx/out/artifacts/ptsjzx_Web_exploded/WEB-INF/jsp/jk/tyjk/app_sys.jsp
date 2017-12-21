<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>硬件设备</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/skins/jk/js/manhuaDate.1.0.js"></script>
<script src="${ctx}/tiles/echarts/echarts.min.js"></script>
<script>
	$(document).ready(function() {
		var DJdt = $(".header_time dt")
		DJdt.click(function(){
			$(this).addClass('time_title_click').show()
					.siblings().removeClass('time_title_click')		
			
			});
			
     $(function(){
			$('.time_form form').jqTransform();
		});
    });
</script>
</head>

<body>
<div class="mian_box">
   	<dl class="header_time">
	   	<dt class="time_title_click">今天</dt>
	    <dt>昨天</dt>
	    <dt>最近7天</dt>
	    <dt>最近30天</dt>
       	<dd class="rili">
       		<input type="text" class="mh_date" readonly value="时间" onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue;}"/>
      	</dd>
	    <dd class="zhi">至</dd>
	    <dd class="rili">
	       	<input type="text" class="mh_date" readonly value="时间" onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue;}"/>
	    </dd>
     </dl>
     <dl class="main_01 mima_tit">
     	<dt style="width:100%;text-align:center;">
      	<img src="${ctx}/skins/jk/images/01.jpg" style="width:100%;height:98%;text-align:center;"/>
     	</dt>
     </dl>
     <div class="main_01 pb20">
     	<div class="user_tit">
         	<h1>应用系统状态一览表</h1>
     	</div>
     	<table class="age_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
           <tr>
             <td width="15%">系统名称</td>
             <td width="15%">服务器IP</td>
             <td width="15%">状态</td>
             <td width="15%">进程数</td>
             <td width="25%">操作时间</td>
           </tr>
           <tr>
             <td>系统1</td>
             <td>192.168.104.2</td>
             <td>开启</td>
             <td>14</td>
             <td>2015.12.18&nbsp;11:20</td>
           </tr>
           <tr>
              <td>系统2</td>
             <td>192.168.104.4</td>
             <td>开启</td>
             <td>16</td>
             <td>2015.12.18&nbsp;11:20</td>
           </tr>
           <tr>
              <td>系统3</td>
             <td>192.168.104.4</td>
             <td>关闭</td>
             <td>0</td>
             <td>2015.12.18&nbsp;11:20</td>
           </tr>
         </table>
      </div>
      <div class="main_01 pb20">
      	<div class="user_tit">
          	<h1>数据库连接状态一览表</h1>
      	</div>
      	<table class="age_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="15%">数据库名称</td>
              <td width="15%">所属局委办</td>
              <td width="15%">服务器IP</td>
              <td width="15%">状态</td>
              <td width="15%">连接数</td>
              <td width="15%">最大连接数</td>
            </tr>
            <tr>
              <td>sjdcbs(普陀电子台帐)</td>
              <td>综治办</td>
              <td>192.168.104.2</td>
              <td>畅通</td>
              <td>35</td>
              <td>200</td>
            </tr>
            <tr>
              <td>sjsjzx(普陀数据中心)</td>
              <td>科委</td>
              <td>192.168.104.2</td>
              <td>畅通</td>
              <td>98</td>
              <td>200</td>
            </tr>
            <tr>
              <td>test(测试数据库)</td>
              <td>万达信息</td>
              <td>10.1.8.73</td>
              <td>畅通</td>
              <td>245</td>
              <td>500</td>
            </tr>
        </table>
     </div>
</div>

<script type="text/javascript">
$(function (){
	$("input.mh_date").manhuaDate({					       
		Event : "click",//可选				       
		Left : 0,//弹出时间停靠的左边位置
		Top : -16,//弹出时间停靠的顶部边位置
		fuhao : "-",//日期连接符默认为-
		isTime : false,//是否开启时间值默认为false
		beginY : 2014,//年份的开始默认为1949
		endY :2016//年份的结束默认为2049
	});
});
</script>
</body>
</html>