<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<div class="row">
    <h3 id="disable-responsive" class="page-header">平台入口</h3>
<div class="col-md-12">
        <!--statistics start-->
        <div class="row state-overview">
            <div class="col-md-3 col-xs-6 col-sm-3">
                <div class="panel purple link-pannel" onclick="javaScript:toJh()">
                    <div class="symbol">
                        <i class="fa fa-database"></i>
                    </div>
                    <div class="state-value">
                        <div class="value" style="font-size: 16px">资源共享交换平台</div>
                        <div class="title">资源项<span class="state-count" id="zyAllCount">0</span></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-xs-6 col-sm-3">
                <div class="panel red link-pannel" onclick="javaScript:toLy()">
                    <div class="symbol">
                        <i class="fa fa-jsfiddle"></i>
                    </div>
                    <div class="state-value">
                        <div class="value" style="font-size: 16px">信息综合利用平台</div>
                        <div class="title">服务项<span class="state-count" id="fwCount">0</span></div>
                    </div>
                </div>
            </div>

            <div class="col-md-3 col-xs-6 col-sm-3">
                <div class="panel blue link-pannel" onclick="javaScript:toJk()">
                    <div class="symbol">
                        <i class="fa fa-cogs"></i>
                    </div>
                    <div class="state-value">
                        <div class="value" style="font-size: 16px">实时安全监控平台</div>
                        <div class="title">分析项<span class="state-count">12</span></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-xs-6 col-sm-3">
                <div class="panel green link-pannel" onclick="javaScript:toDt()">
                    <div class="symbol">
                        <i class="fa fa-headphones"></i>
                    </div>
                    <div class="state-value">
                        <div class="value" style="font-size: 16px">地理信息服务</div>
                        <div class="title">监控项 <span class="state-count">8</span></div>
                    </div>
                </div>
            </div>
        </div>
        <!--statistics end-->
    </div>



</div>
