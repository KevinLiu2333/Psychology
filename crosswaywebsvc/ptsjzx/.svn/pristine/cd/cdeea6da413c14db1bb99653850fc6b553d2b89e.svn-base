<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		
   <p style="text-align:center;font-size:24px;padding-bottom: 15px;padding-top: 10px">${obj.resource.resourceName}</p>
   <div style="margin-left: 30px;">
   		<table width="80%" align="center" class="table_multilist" >
	          <%--  <tr>
	           		<td width="15%" class="label_1">访问</td>
	           		<td width="85%" colspan="6" class="label_2">
		           		<c:if test="${obj.resource.browseCount eq null}">
		           			0&nbsp; 次
		           		</c:if>
		           		<c:if test="${obj.resource.browseCount != null}">
		           			${obj.resource.browseCount}&nbsp;次
		           		</c:if>
	           		</td>
	           </tr> --%>
	          <%--  <tr>
	           		<td width="15%" class="label_1">摘要</td>
	           		<td width="85%" colspan="6" class="label_2">${obj.resource.abstractWord}</td>
	           </tr>
	           <tr>
	           		<td width="15%"  class="label_1">关键字</td>
	           		<td width="85%" colspan="6" class="label_2">${obj.resource.keyWord}</td>
	           </tr>
	           <tr>
	           		<td width="15%" class="label_1">信息系统名</td>
	           		<td width="85%" colspan="6" class="label_2">${obj.resource.infoSysName}</td>
	           </tr>
	           <tr>
	           		<td width="15%" class="label_1">信息系统链接</td>
	           		<td width="85%" colspan="6" class="label_2">${obj.resource.sysUrl}</td>
	           </tr>
	           <tr>
	           		<td width="15%" class="label_1">系统简介</td>
	           		<td width="85%" colspan="6" class="label_2">${obj.resource.sysAbstract}</td>
	           </tr>
	           <tr>
	           		<td width="15%" class="label_1">提供科室</td>
	           		<td width="85%" colspan="6" class="label_2">${obj.resource.provideDepartment}</td>
	           </tr>
	           <tr>
	           		<td class="label_1">业务联系人</td>
					<td width="35%" class="label_2" colspan="2">${obj.resource.busLinkman}</td>
					<td width="15%" class="label_1">联系电话</td>
					<td width="35%" class="label_2" colspan="3">${obj.resource.busLinkmanPhone}</td>
	           </tr> --%>
	           <tr>
	           		<td class="label_1">业务联系人</td>
					<td width="35%" class="label_2" colspan="2">${obj.resource.busLinkman}</td>
					<td width="15%" class="label_1">联系电话</td>
					<td width="35%" class="label_2" colspan="3">${obj.resource.busLinkmanPhone}</td>
	           </tr>
	           <tr>
	           		<td class="label_1">对接方式</td>
					<td width="35%" class="label_2" colspan="2"><wd:dicvalue dicId="1082" dicCode="${obj.resource.jointType}"/></td>
					<td width="15%" class="label_1">资源更新频度</td>
					<td width="35%" class="label_2" colspan="3"><wd:dicvalue dicId="1083" dicCode="${obj.resource.updateRate}"/></td>
	           </tr>
	           <tr>
					<td class="label_1">所属资源类型</td>
					<td width="35%" class="label_2" colspan="2">
						<c:if test="${obj.resource.resourceType eq 'r_ztl'}">
							专题类
						</c:if>
						<c:if test="${obj.resource.resourceType != 'r_ztl'}">
						<wd:dicvalue dicId="1054" dicCode="${obj.resource.resourceType}"/>
						</c:if>
						</td>
						<td width="15%" class="label_1">访问</td>
						<td width="35%" colspan="6" class="label_2">
		           		<c:if test="${obj.resource.browseCount eq null}">
		           			0&nbsp; 次
		           		</c:if>
		           		<c:if test="${obj.resource.browseCount != null}">
		           			${obj.resource.browseCount}&nbsp;次
		           		</c:if>
	           		</td>
					<%-- <td width="15%" class="label_1">资源公开属性</td>
					<td width="35%" class="label_2" colspan="3"><wd:dicvalue dicId="1087" dicCode="${obj.resource.openProperty}"/></td> --%>
				</tr>
				<%-- <tr>
					<td class="label_1">数据库中文表名</td>
					<td width="35%" class="label_2" colspan="2">${obj.resource.tableChinese}</td>				
					<td width="15%" class="label_1">数据库表名</td>	
					<td width="35%" class="label_2" colspan="3">${obj.resource.tableName}</td>				
				</tr> --%>
	    </table>
	    <div style="margin-top: 10px;margin-bottom: 10px;text-align: center;">
		    <!-- <a href="javascript:show_div()" style="font-size: 15px;">展开/收起</a> -->
	    </div>
	    <table width="80%"  id="starlist" class="table_multilist" align="center">
	    	   <tr style="background-color: silver;">
	           		<td class="label_1" width="15%" style="text-align: center;">资源项名</td>
	           		<td class="label_1" width="15%" style="text-align: center;">数据库字段名</td>
	           		<td class="label_1" width="8%" style="text-align: center;">数据类型</td>
	           		<td class="label_1" width="15%" style="text-align: center;">备注</td>
	           		<td class="label_1" width="8%" style="text-align: center;">共享属性</td>
	           		<!-- <td class="label_1" width="8%" style="text-align: center;">公开属性</td> -->
	           		<td class="label_1" width="15%" style="text-align: center;">不予共享/公开理由</td>
	           </tr>
	           <c:forEach items="${obj.resourceDetailsList}" var="resourceDetails" varStatus="row">
	           		<c:if test="${row.index%2 == 0}">
	           			<tr class="bg_white">
		           			<td align="center" class="fontClass">${resourceDetails.dataItemName}</td>
		           			<td align="center" class="fontClass">${resourceDetails.fieldCode}</td>
			           		<td align="center" class="fontClass">
			           			<wd:dicvalue dicId="1085" dicCode="${resourceDetails.dataItemType}"/>
			           		</td>
			           		<td align="center" class="fontClass">${resourceDetails.memo}</td>
			           		<td align="center" class="fontClass">
			           			<wd:dicvalue dicId="1086" dicCode="${resourceDetails.shareProperty}"/>
			           		</td>
			           		<%-- <td align="center" class="fontClass">
			           			<wd:dicvalue dicId="1087" dicCode="${resourceDetails.openProperty}"/>
			           		</td> --%>
			           		<td align="center" class="fontClass">${resourceDetails.noOpenReason}</td>
		           		</tr>
	           		</c:if>
	           		<c:if test="${row.index%2 != 0}">
		           		<tr class="bg_grey">
		           			<td align="center" class="fontClass">${resourceDetails.dataItemName}</td>
		           			<td align="center" class="fontClass">${resourceDetails.fieldCode}</td>
			           		<td align="center" class="fontClass">
			           			<wd:dicvalue dicId="1085" dicCode="${resourceDetails.dataItemType}"/>
			           		</td>
			           		<td align="center" class="fontClass">${resourceDetails.memo}</td>
			           		<td align="center" class="fontClass">
			           			<wd:dicvalue dicId="1086" dicCode="${resourceDetails.shareProperty}"/>
			           		</td>
			           	<%-- 	<td align="center" class="fontClass">
			           			<wd:dicvalue dicId="1087" dicCode="${resourceDetails.openProperty}"/>
			           		</td> --%>
			           		<td align="center" class="fontClass">${resourceDetails.noOpenReason}</td>
		           		</tr>
	           		</c:if>
	           </c:forEach>
	    </table>
   </div>
   <br>


