<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/tiles/cj/title_setting.jsp" %>
       <!-- 共享服务 -->
        <div class="row">

            <h1 id="disable-responsive2" class="page-header">共享服务</h1>

            <br/>
			
			<!-- 孟振乾 20160618 动态生成共享服务信息标签   end -->
			<c:forEach items="${obj.fwResult.rows}" var="fwInfo" varStatus="row" >
				<div class="col-sm-12 col-md-6 features">

	                <div class="feature">
	                    <div class="feature-icon">
	                        <span class="glyphicon glyphicon-star-empty"></span>
	                    </div>
	                    <h5><b>${fwInfo.fwName }</b></h5>
	                    <p>${fwInfo.fwAbstract}</p>
	                    <h5> <span class="glyphicon glyphicon-record" style="margin-left: 80px" title="申请次数"></span><a href="${ctx}/stat/toAppUsedCount"><span>${fwInfo.applyCount}</span></a>
	                        <span class="glyphicon glyphicon-export" style="margin-left: 80px" title="调用次数"></span><a href="${ctx}/stat/toAppUsedCount"><span>${fwInfo.usedCount}</span></a>
	                        <span style="float: right;padding-right: 15px;">
								<c:if test="${ not empty obj.applyMap[fwInfo.fwInfoId]}">
								<button type="button" class="btn btn-sm btn-success" onclick="toFwApplyView('${obj.applyMap[fwInfo.fwInfoId].fwApplyId}')"><i class="fa fa-cloud"></i>已开通</button>
								</c:if>
								<c:if test="${ empty obj.applyMap[fwInfo.fwInfoId]}">
									<c:if test="${ empty fwInfo.fileId}">
										<button type="button" class="btn btn-sm btn-warning" onclick="toFwApply('${fwInfo.fwInfoId}')"><i class="fa fa-cloud"></i>申请使用</button>
									</c:if>
									<c:if test="${ not empty fwInfo.fileId}">
										<button type="button" class="btn btn-sm btn-success" onclick="downAppFile('${fwInfo.fileId}','${fwInfo.fwInfoId}')"><i class="fa fa-cloud"></i>下载</button>
									</c:if>

								</c:if>
							</span>
	
	                    </h5>
	                </div>
				</div>
			</c:forEach>
			<!-- 孟振乾 20160618 动态生成共享服务信息标签   end -->

       </div>