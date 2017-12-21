<%@page import="java.net.URLEncoder"%>
<%@ page contentType="application/vnd.ms-excel;charset=UTF-8" %>
<%response.setHeader("Content-Disposition","attachment; filename="+URLEncoder.encode("法人详细信息.xls","utf-8"));%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
</head>
<body>
<table width="50%" border="1" cellspacing="2" cellpadding="0" >
				<tr>
         			 <td width="15%" class="info_title_aa">法人名称</td>
        			 <td width="35%" align="left" bgcolor="#FFFFFF">${obj.corp.corpname }</td>
        		</tr>
        		<tr>
       			     <td width="15%" class="info_title_aa">组织机构代码</td>
       				 <td width="35%" align="left" bgcolor="#FFFFFF">${obj.corp.organcode }</td>
        		</tr>
        		<tr>
         			 <td width="15%" class="info_title_bb">法人类型</td>
        			 <td width="35%" align="left" bgcolor="#FFFFFF"><wd:dicvalue dicId="1052" dicCode="${obj.corp.corptype }"/></td>
       			</tr>
        		<tr>
       			     <td width="15%" class="info_title_bb">法定代表人</td>
       				 <td width="35%" align="left" bgcolor="#FFFFFF">${obj.corp.personname }</td>
        		</tr>
        		<tr>
         			 <td width="15%" class="info_title_aa">经营场所</td>
        			 <td width="35%" align="left" bgcolor="#FFFFFF">${obj.corp.address }</td>
        		</tr>
        		<tr>
        			 <td width="15%" class="info_title_aa">法人状态</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF"><wd:dicvalue dicId="1041" dicCode="${obj.corp.corpstatus }"/></td>
        		</tr>
        		<tr>
         			 <td width="15%" class="info_title_bb">邮编</td>
        			 <td width="35%" align="left" bgcolor="#FFFFFF">${obj.corp.zip }</td>
       			    </tr>
        		<tr>
       			     <td width="15%" class="info_title_bb">联系电话</td>
       				 <td width="35% " align="left" bgcolor="#FFFFFF">${obj.corp.telephone }</td>
        		</tr>
        		<tr>
        			 <td width="15%" class="info_title_aa">统一社会信用代码</td>
       			     <td width="35%" align="left" bgcolor="#FFFFFF">${obj.corp.uniscid }</td>
         			</tr>
        		<tr>
         			 <td width="15%" class="info_title_aa">成立日期</td>
        			 <td width="35%" align="left" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.establishdate }" pattern="yyyy年MM月dd日"/></td>
        		</tr>
        		<tr>
         			 <td width="15%" class="info_title_bb">币种</td>
        			 <td width="35%" align="left" bgcolor="#FFFFFF">${obj.corp.currency }</td>
       			    </tr>
        		<tr>
       			     <td width="15%" class="info_title_bb">开办资金(万)</td>
       				 <td width="35%" align="left" bgcolor="#FFFFFF">
       				 	<fmt:formatNumber type="number" value="${obj.corp.regcapital }" maxFractionDigits="0"/>
       				 </td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">业务范围</td>
       				 <td width="35%" align="left" bgcolor="#FFFFFF" >${obj.corp.businessscope }</td>
        			
        		</tr>
        		
        		
        		<tr>
        			<td width="15%" class="info_title_bb">行业类别</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF"><wd:dicvalue dicId="1049" dicCode="${obj.corp.industrycode}"/></td>
        			</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">业务主管单位</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF">${obj.corp.organizers }</td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">经费来源</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF">${obj.corp.fundingsrc }</td>
        			</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">营业执照注册号</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF">${obj.corp.regno }</td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">法人注销原因</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF"> ${obj.corp.repealreason }</td>
        			</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">法人变更登记事项</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF">${obj.corp.changeitem }</td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">法人注销日期</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.repealdate }" pattern="yyyy年MM月dd日"/></td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">法人变更日期</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.changedate }" pattern="yyyy年MM月dd日"/></td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">分支机构数（社会组织）</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF">
        			<fmt:formatNumber type="number" value="${obj.corp.branchnum }" maxFractionDigits="0"/>
        			</td>
        			</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">代表机构数（社会组织）</td>
        			
        			<td width="35%" align="left" bgcolor="#FFFFFF">
        			<fmt:formatNumber type="number" value="${obj.corp.representnum }" maxFractionDigits="0"/>
        			</td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">登记类业务发布时间</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.regupddate }" pattern="yyyy年MM月dd日"/></td>
        			</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">纳税人识别号</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF"> ${obj.corp.taxpayerscode }</td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">组合位置编码</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF">${obj.corp.taxcode }</td>
        			</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">税务登记日期</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.taxregdate }" pattern="yyyy年MM月dd日"/></td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">税务变更内容</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF">${obj.corp.taxchgecontent }</td>
        			</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">税务变更日期</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.taxchgedate }" pattern="yyyy年MM月dd日"/></td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">税务注销原因</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF">${obj.corp.taxrepealreason }</td>
        			</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">税务注销日期</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.taxrepealdate }" pattern="yyyy年MM月dd日"/></td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">实际经营地址</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF" >${obj.corp.businessaddress }</td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">税务注销机关</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF">${obj.corp.taxrepealorgan }</td>
        			</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">是否工商联</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF">${obj.corp.isgsl }</td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">税务类业务发布时间</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.taxupddate }" pattern="yyyy年MM月dd日"/></td>
        			</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">组织机构代码赋码日期</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.organcodedate }" pattern="yyyy年MM月dd日"/></td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">组织机构代码变更日期</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.orgcodechgdate }" pattern="yyyy年MM月dd日"/></td>
        			</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">组织机构代码注销日期</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.orgcoderepealdate }" pattern="yyyy年MM月dd日"/></td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">质监类业务发布时间</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.qsupddate }" pattern="yyyy年MM月dd日"/></td>
        			</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">是否自贸区</td>
        			<td width="35%" align="left" bgcolor="#FFFFFF"><wd:dicvalue dicId="1040" dicCode="${obj.corp.iszmq }"/></td>
        		</tr>
			</table>
</body>
</html>