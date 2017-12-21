<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>安全审计</title>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/jk/css/main_bumen.css" />
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
        <ul class="user_ui main_01">
        	<li>
            	<b class="ui_bg01">
                	<h5>登入次数</h5>
                    <a href="#"><span>136</span></a>
                	<h5>用户访问次数</h5>
                </b>
            </li>
        	<li>
            	<b class="ui_bg02">
                	<h5>登出次数</h5>
                    <a href="#"><span>126</span></a>
                	<h5>安全退出情况</h5>
                </b>
            </li>
        	<li>
            	<b class="ui_bg03">
                	<h5>访问最频繁的时间段</h5>
                    <a href="#"><span>9:00-10:30</span></a>
                	<h5>集中访问时间情况</h5>
                </b>
            </li>
        	<li>
            	<b class="ui_bg04">
                	<h5>访问最频繁的人</h5>
                    <a href="#"><span>李敏丽</span></a>
                	<h5>人员访问情况</h5>
                </b>
            </li>
        </ul>
       <dl class="main_01 mima_tit">
        	<dt>
            	<span>
                	<b>门禁刷卡总人数</b>
                    <h1>356</h1>
                </span>
            </dt>
        	<dd>
            	<span>
                	<b>门禁刷卡次数最多</b>
                    <h1>李薇薇</h1>
                </span>
            </dd>
        </dl>
      </div>  

<script type="text/javascript">
$(function(){
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