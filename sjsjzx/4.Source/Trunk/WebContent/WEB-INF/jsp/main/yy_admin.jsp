<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/tiles/cj/title_setting.jsp" %>
<div class="row">
    <h1 id="disable-responsive" class="page-header">平台运营</h1>
    <br/>
    <div class="row">
        <div class="col-md-4">
            <div class="panel">
                <div class="panel-body extra-pad">
                    <h4 class="pros-title">单位调用排名</h4>
                    <div class="row">
                        <div class="col-sm-4 col-xs-4">
                            <div id="p-lead-1"></div>
                            <p class="p-chart-title">民政局</p>
                        </div>
                        <div class="col-sm-4 col-xs-4">
                            <div id="p-lead-2"></div>
                            <p class="p-chart-title">卫计委</p>
                        </div>
                        <div class="col-sm-4 col-xs-4">
                            <div id="p-lead-3"></div>
                            <p class="p-chart-title">社治办</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel">
                <div class="panel-body extra-pad">
                    <div class="col-sm-6 col-xs-6">
                        <div class="v-title">服务调用量</div>
                        <div class="v-value">10,090</div>
                        <div id="visit-1"></div>
                        <div class="v-info">本月</div>
                    </div>
                    <div class="col-sm-6 col-xs-6">
                        <div class="v-title">服务申请量</div>
                        <div class="v-value">8,173</div>
                        <div id="visit-2"></div>
                        <div class="v-info">本月</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4">

            <div class="panel green-box">
                <div class="panel-body extra-pad">
                    <div class="row">
                        <div class="col-sm-6 col-xs-6">
                            <div class="knob">
                                        <span class="chart" data-percent="79">
                                            <span class="percent">79% <span class="sm">总交换量占比</span></span>
                                        </span>
                            </div>
                        </div>
                        <div class="col-sm-6 col-xs-6">
                            <div class="knob">
                                        <span class="chart" data-percent="56">
                                            <span class="percent">56% <span class="sm">月交换量占比</span></span>
                                        </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



</div>
