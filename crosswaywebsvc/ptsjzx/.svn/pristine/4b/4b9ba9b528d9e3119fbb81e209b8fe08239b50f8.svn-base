<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp" />
<link href="${ctx }/tiles/ligerUI/skins/Aqua/css/ligerui-dialog.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx }/skins/query/css/style.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css"
	type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript"
	src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx }/tiles/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script src="${ctx }/tiles/ligerUI/js/plugins/ligerDrag.js"
	type="text/javascript"></script>
<script src="${ctx }/tiles/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<!-- 输入框自适应宽度 -->
<script type="text/javascript"
	src="${ctx }/tiles/scripts/JQueryAutoFit.js"></script>


<title>房屋室号展现</title>
<style type="text/css">
.loading {
	top: 250px;
	left: 350px;
	z-index: 10000;
	position: absolute;
	font-size: 30px;
	color: rgba(200, 0, 0, 0.5);
	filter: alpha(opacity = 80);
	opacity: 0.8;
	display: none;
}
input{
	overflow:visible;
}
</style>
</head>
<body>
	<form id="queryForm" name="queryForm" action="${ctx}/query/toShowHouse"
		method="post" onsubmit="beforesubmit()">
		<table width="96%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		<table width="96%" border="1" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td valign="top" id="list_search">
					<table width="96%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td>&nbsp;</td>
						</tr>
					</table>
					<table width="96%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td width="7%">房屋地址：</td>
							<td width="40%">
								<input id="road" class="list_search_input" name="road" value="${obj.road }" style="width:60px;text-align:right" /> 路
								<input id="nong" class="list_search_input" name="nong" value="${obj.nong }" style="width:60px;text-align:right" /> 弄
								<input id="number" class="list_search_input" name="number" value="${obj.number }" style="width:60px;text-align:right" /> 号
							</td>
					
							<%-- <td width="12%" align="center">户籍街道：</td>
				        <td><wd:select id="jddm" name="jddm" dicCode="1059"
								className="list_search_select" initValue="-------请选择-------"
								defaultValue="${obj.jddm }" style="width:94%;" /> 
				        </td> --%>
							<td width="30%">&nbsp;</td>
							<td width="20%" rowspan="3" valign="middle" align="center"><input
								type="button" name="button" id="list_search_button" value=" "
								onclick="query(1)" /></td>
					</tr>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td colspan="4">注：不需要输入“上海市普陀区”，路名与室号必填，依据户籍地址 。</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;下方列表中单行“户籍地址”和“居住地址”属于市级人口信息内同一房屋编号下。</td>
					</tr>
					<%-- <tr>
						<td >&nbsp;&nbsp;&nbsp;&nbsp;</td>
				        <td >所属居委会：</td>
				        <td width="30%"><input id="jcwmc" class="list_search_input" name="jcwmc" value="${obj.jcwmc }" style="width:90%;"/></td>
				        <td align="center"><a href="#" onclick="clearcondition()">重置查询条件</a></td>
				        <td >&nbsp;&nbsp;&nbsp;&nbsp;</td>
			        </tr> --%>
					<tr>
						<td>&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<input id = "data" hidden val='${obj.houseList}' />
	<c:if test="${obj.houseList != null}">
		<!-- <div id = "charts" style="height:200px;width:96%"></div> -->
	</c:if>
	<div align="center">
		<table width="96%" class="table_list">
			<tr>
				<th width="10%">序号</th>
				<th width="40%">户籍地址</th>
				<th width="40%">居住地址</th>
			</tr>
			<c:forEach items="${obj.list}" var="house" varStatus="row">
				<tr>
					<td align="center">${row.index+1 }</td>
					<td align="center">${house.hjdz }</td>
					<td align="center">${house.jzdz }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		<table width="96%" class="tables">
			<tr>
				<td>
					<jsp:include page="/common/pzgl/pager-iframe.jsp"></jsp:include>
				</td>
			</tr>
		</table>
	</div>
</form>
<div id="loading" class="loading">
		<img src="${ctx }/skins/images/loading.gif" />&nbsp;查询中...
</div>
<p id="Object" style="display:none">
		<OBJECT ID="SafeEngineCtl"
			CLASSID="CLSID:B48B9648-E9F0-48A3-90A5-8C588CE0898F" width="400"
			height="50" border=0></OBJECT>
	</p>
</body>
<script src="${ctx}/tiles/echarts/chart/echarts_tree.js"></script>
<script type="text/javascript">
	$(document).keyup(function(event) {
		if (event.keyCode == 13) {
			$("#list_search_button").trigger("click");
		}
	});

	//自适应input宽度
	$("input").autoFit();

	function query(type) {
		if( $.trim($('#road').val()) ==null || $.trim($('#road').val()) == ''){
			alert('请输入查询地址！');
			return;
		}
		if( $.trim($('#number').val())==null || $.trim($('#number').val()) == ''){
			alert('房屋号不能为空！');
			return;
		}
		if (type == '1') {
			$('#pageNo').val('1');
		}
		caquery();
	}
	/* function clearcondition(){
	 $('#addr').val("");
	 $('#jddm').val("");
	 $('#jcwmc').val("");
	 } */
	function beforesubmit() {
		$('#loading').show();
	}
	function caquery() {
		//$('#pageNo').val('1');
		//$('#newquery').val('1');
		//$('#queryForm').submit();
		//return;
		if (document.getElementById("Object").innerHTML.length < 140) {
			alert("在使用key前需要安装证书管理器.");
			return;
		}
		var sessionpsd = '${sessionScope.keypsd}';
		strpripath = "com1";
		strcertpath = "com1";
		strcertchainpath = "com1";
		ConfigurationNum = 0;
		DevNumber = 0xa;
		if (sessionpsd != '') {
			SafeEngineCtl.SEH_InitialSession(DevNumber, strpripath, sessionpsd,
					0, DevNumber, strcertchainpath, "");
			if (SafeEngineCtl.ErrorCode == 0) {
				$('#pageNo').val('1');
				$('#newquery').val('1');
				$('#queryForm').submit();
				return;
			} else {
				alert('key已被拔出，请关闭浏览器后重新登录');
				return;
			}
		}
		$.ligerDialog.prompt('请输入KEY的密码!', function(yes, value) {
			if (yes) {
				strpassword = value;
				if (strpassword == "") {
					alert("密码不能为空.");
					return;
				}
				SafeEngineCtl.SEH_InitialSession(DevNumber, strpripath,
						strpassword, 0, DevNumber, strcertchainpath, "");
				if (SafeEngineCtl.ErrorCode != 0) {
					alert("请确认证书插入、密码是否输入正确！");
					return;
				}
				$.ajax({
					tyep : "POST",
					url : "${ctx}/ca/getId",
					data : {
						password : value
					},
					dataType : "Json",
					sync : false,
					success : function(str) {
						if (str != null) {
							strCert = SafeEngineCtl.SEH_GetSelfCertificate(
									DevNumber, strcertpath, "");
							if (SafeEngineCtl.ErrorCode != 0) {
								alert("证书异常,请检查key是否正常.");
								return;
							}
							strSigned = SafeEngineCtl.SEH_SignData(str, 3);
							if (SafeEngineCtl.ErrorCode != 0) {
								alert("证书异常,请检查key是否正常.");
								SafeEngineCtl.SEH_ClearSession();
								return;
							}
							strItemVal = SafeEngineCtl.SEH_GetCertDetail(
									strCert, 17);
							if (SafeEngineCtl.ErrorCode != 0) {
								alert("证书异常,请检查key是否正常.");
								SafeEngineCtl.SEH_ClearSession();
								return;
							}
							strVal = SafeEngineCtl.SEH_SignData(strItemVal, 3);
							if (SafeEngineCtl.ErrorCode != 0) {
								alert("证书异常,请检查key是否正常.");
								SafeEngineCtl.SEH_ClearSession();
								return;
							}
							$.ajax({
								tyep : "POST",
								url : "${ctx}/ca/checker",
								data : {
									"cert" : strCert,
									"signed" : strSigned,
									"itemVal" : strVal
								},
								dataType : "Json",
								sync : false,
								success : function(s) {
									if (s != null) {
										// 验证成功,进行必要请求
										$('#pageNo').val('1');
										$('#newquery').val('1');
										$('#queryForm').submit();
									} else {
										alert("验证失败.");
									}
								},
								error : function() {
									alert("服务器繁忙,请稍后再试.");
								}
							});
						} else {
							alert("随机数非法.");
						}
					},
					error : function() {
						alert("服务器繁忙,请稍后再试.");
					}
				});
			}
		});
	}
	
	var data = $("#data").attr("val");
	//功能待新增
	//var myChart = echarts.init(document.getElementById('charts'));
	option = {
			title : {
		        text: '房屋室号图示',
		        x:50,
		        y:30
		    },
		 series : [
	                {
	                    name:'树图',
	                    type:'tree',
	                    orient: 'horizontal',  // vertical horizontal
	                    rootLocation: {x:380,y: 'center'}, // 根节点位置  {x: 100, y: 'center'}
	                    nodePadding: 100,
	                    layerPadding:300,
	                    hoverable: false,
	                    roam: true,
	                    symbolSize: 15,
	                    itemStyle: {
	                        normal: {
	                            color: '#4883b4',
	                            label: {
	                                show: true,
	                                position: 'right',
	                                formatter: "{b}",
	                                textStyle: {
	                                    color: '#000',
	                                    fontSize: 18
	                                }
	                            },
	                            lineStyle: {
	                                color: '#ccc',
	                                type: 'curve' // 'curve'|'broken'|'solid'|'dotted'|'dashed'

	                            }
	                        },
	                        emphasis: {
	                            color: '#4883b4',
	                            label: {
	                                show: false
	                            },
	                            borderWidth: 0
	                        }
	                    },

                    data: eval('('+data+')')
                }
            ]
        };
	myChart.setOption(option);            
</script>
</html>