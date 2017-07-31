<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
 <link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css"/>
<title>松江区政务数据中心-数据接口服务详情</title>
<jsp:include page="/common/meta.jsp"/>
<script type="text/javascript">
function save(){
	window.close();
}
</script>
</head>
<body style="overflow-x:hidden">
	<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>数据接口服务详情</b></p>
	
	<table width="80%" class="table_multilist" align="center">
		<p style="text-align:center;font-size:15px;padding-bottom: 15px;padding-top: 10px">数据发送的具体实现方法都在SendMessageService类中；其中构造xml数据对象和短信发送等的接口介绍如下:</p>
		<tr>
			<td style="text-align:center; height:20px" width="25%">接口名称</td>
			<td style="text-align:center; height:20px" width="20%">参数类型</td>
			<td style="text-align:center; height:20px" width="15%">返回值</td>
			<td style="text-align:center; height:20px" width="40%">说明</td>
		</tr>
		<tr>
			<td style="text-align:center; height:20px">sendData2ZwdtImpl()</td>
			<td style="text-align:center; height:20px">ReceiveApp, WfItem, String</td>
			<td style="text-align:center; height:20px">void</td>
			<td style="text-align:center; height:20px">发送数据主方法，发送数据的入口函数，第三个参数是判断是否打证的标记（1表示打证）</td>
		</tr>
		<tr>
			<td style="text-align:center; height:20px">makeXmlStr()</td>
			<td style="text-align:center; height:20px">ReceiveApp, WfItem, String</td>
			<td style="text-align:center; height:20px">ExDataXml</td>
			<td style="text-align:center; height:20px">根据受理状态、节点名称、是否打证标记生成ExDataXml对象；参数1：受理App，参数2：流程节点，参数3：打证标记</td>
		</tr>
		<tr>
			<td style="text-align:center; height:20px">getNode()</td>
			<td style="text-align:center; height:20px">ReceiveApp, WfItem, ExDataXml</td>
			<td style="text-align:center; height:20px">Node</td>
			<td style="text-align:center; height:20px">根据受理状态和节点名称获取Node节点信息</td>
		</tr>
		<tr>
			<td style="text-align:center; height:20px">insert2zwdt()</td>
			<td style="text-align:center; height:20px">ExData, String, Long</td>
			<td style="text-align:center; height:20px">void</td>
			<td style="text-align:center; height:20px">向政务大厅发送数据；参数1：插入数据对象，参数2：XML文件路径，参数3：自增长主键</td>
		</tr>
		<tr>
			<td style="text-align:center; height:20px">insert2own()</td>
			<td style="text-align:center; height:20px">ExData, String, Long</td>
			<td style="text-align:center; height:20px">void</td>
			<td style="text-align:center; height:20px">向社会组织库中插入数据；参数1：插入数据对象，参数2：XML文件路径，参数3：自增长主键</td>
		</tr>
		<tr>
			<td style="text-align:center; height:20px">reInsert2zwdt()</td>
			<td style="text-align:center; height:20px">DataSending, String, nmSeqId</td>
			<td style="text-align:center; height:20px">void</td>
			<td style="text-align:center; height:20px">数据对账失败后的重发操作；参数1：需要重发的数据，参数2：问题数据修改后保存的文件路径，参数3：主键</td>
		</tr>
		<tr>
			<td style="text-align:center; height:20px">getHallNumBySoap()</td>
			<td style="text-align:center; height:20px">String</td>
			<td style="text-align:center; height:20px">String</td>
			<td style="text-align:center; height:20px">调用WebService获取统一编码;参数为组织类型（社团=社会团体登记|民非=民办非企业单位登记|基金会=基金会登记）</td>
		</tr>
		<tr>
			<td style="text-align:center; height:20px">getResult()</td>
			<td style="text-align:center; height:20px">String,String</td>
			<td style="text-align:center; height:20px">String类型的Y或N</td>
			<td style="text-align:center; height:20px">参数1：单位名称，参数2：单位口令</td>
		</tr>
		<tr>
			<td style="text-align:center; height:20px">sendMessage()</td>
			<td style="text-align:center; height:20px">String, String, String</td>
			<td style="text-align:center; height:20px">null</td>
			<td style="text-align:center; height:20px">发送短信；参数1：多个手机号码字符串，参数2：短信内容，参数3：行政区划</td>
		</tr>
  	</table>
</body>
</html>