<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<div class="row">
    <div class="col-md-6">
        <!--more statistics box start-->
        <div class="panel deep-purple-box">
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-7 col-sm-7 col-xs-7">

                        <div class="panel">
                            <div class="panel-body">
                                <ul class="p-info">
                                    <li>
                                        <div class="title">所属单位</div>
                                        <div class="desk" id="unitName">&nbsp;</div>
                                    </li>
                                    <li>
                                        <div class="title">服务级别</div>
                                        <div class="desk" id="unitLevel">&nbsp;</div>
                                    </li>
                                    <li>
                                        <div class="title">服务key</div>
                                        <div class="desk" id="unitKey">&nbsp;</div>
                                    </li>
                                    <li>
                                        <div class="title">开通服务数量</div>
                                        <div class="desk" id="applyCount">0</div>
                                    </li>
                                    <li>
                                        <div class="title">开放资源数量</div>
                                        <div class="desk" id="zyCount">0</div>
                                    </li>

                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5 col-sm-5 col-xs-5">
                        <div class="state-info">
                            <section class="panel">
                                <div class="panel-body">
                                    <div class="summary">
                                        <span>调用次数</span>
                                        <h3 class="red-txt" id="usedCount">&nbsp;</h3>
                                    </div>
                                    <div id="income" class="chart-bar"></div>
                                </div>
                            </section>
                            <section class="panel">
                                <div class="panel-body">
                                    <div class="summary">
                                        <span>交换总量</span>
                                        <h3 class="green-txt">45,600</h3>
                                    </div>
                                    <div id="expense" class="chart-bar"></div>
                                </div>
                            </section>
                            <section class="panel">
                                <div class="panel-body">
                                    <div class="summary">
                                        <span>月交换量</span>
                                        <h3 class="blue-txt">45,600</h3>
                                    </div>
                                    <div id="expense2" class="chart-bar"></div>
                                </div>
                            </section>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--more statistics box end-->
    </div>

    <div class="col-md-6">
        <!--statistics start-->
        <div class="row state-overview">
            <div class="col-md-6 col-xs-12 col-sm-6">
                <div class="panel purple link-pannel" onclick="javaScript:toZy('')">
                    <div class="symbol">
                        <i class="fa fa-database"></i>
                    </div>
                    <div class="state-value">
                        <div class="value">数据资源</div>
                        <div class="title">资源项<span class="state-count" id="zyAllCount">0</span></div>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-xs-12 col-sm-6">
                <div class="panel red link-pannel" onclick="javaScript:toFw()">
                    <div class="symbol">
                        <i class="fa fa-jsfiddle"></i>
                    </div>
                    <div class="state-value">
                        <div class="value"><a href="${ctx}/fw/toIndex" class="state-info-a">共享服务</a></div>
                        <div class="title">服务项<span class="state-count" id="fwCount">0</span></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row state-overview">
            <div class="col-md-6 col-xs-12 col-sm-6">
                <div class="panel blue">
                    <div class="symbol">
                        <i class="fa fa-bar-chart-o"></i>
                    </div>
                    <div class="state-value">
                        <div class="value">GIS服务</div>
                        <div class="title">分析项<span class="state-count">12</span></div>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-xs-12 col-sm-6">
                <div class="panel green">
                    <div class="symbol">
                        <i class="fa fa-headphones"></i>
                    </div>
                    <div class="state-value">
                        <div class="value">使用指南</div>
                        <div class="title">tel <span class="state-count">24178888</span></div>
                    </div>
                </div>
            </div>
        </div>
        <!--statistics end-->
    </div>



</div>
