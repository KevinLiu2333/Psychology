<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/cookie.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/wddc/tiles/ace/css/ace.min.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style>
			body{
	        	background-color: #ffffff;
	        	font-size: 16px;
	        }
	        .footer{
	        	position: absolute;
	    		bottom: 0;
	    		width: 100%;
	    		height: 50px;
	    		border-top: 1px solid #ccc;
	    		padding-top: 0px;
	        }
	        .navbar{
        	min-height: 50px;
        	}
			.h4text{
            	margin-left: 10px;
            	font-weight: 600;
            	font-family: 黑体;
            	font-size: 18px;
            	color:grey;
            }
            .col-xs-5{
            	padding-left: 0px;
            	padding-right: 0px;
            	height:125px;
            	width:80px;
            }
            .icon-size{
            	width:40px;
            	height: 40px;
            	margin-top: 5px;
            	margin-left: 5px;
            }
            .circle{
            	width: 50px;
            	height: 50px;
            	background-color:white;
            	border-radius: 50%;
            	-moz-border-radius: 50%;
            	-webkit-border-radius: 50%;
            	margin-left: 15px;
            	margin-top: 15px;
            }
            .p-text{
            	font-size: 13px;
            	letter-spacing: -2px;
            	font-family: 黑体;
            	font-weight: 600;
            	text-align:center;
            	margin-top: 10px;
            	color:white;
            }
            .btn-light1{
			background-color:#f2f2f2;
			padding-left: 0px;
			padding-right: 0px;
			}
			.text{
				font-size:30px;
				text-align:center;
				line-height: 80px; 
				padding-left: 0px;
				padding-right: 10px;
				text-align: right;
				height:65%;
			}
			.sm-text{
				font-size: 15px;
				text-align: right;
			}
			.imgSize{
				width:50px;
				heigth:50px
			}
            /*黄：#F9B24A  蓝：#5F9EED  灰：#6C8C9B  紫：#A87CED 绿：#72D753  红：#F4514E;*/
        </style>
</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<div class="container">
	<div class="row">
		<h2 class="page-header" style="margin-top: 25px">交换结果查看</h2>
	</div>
	<div class="row panel">
		<div class="col-md-12">
			<div class="panel" style="background-color:#f5f5f5">
				<h4 class="h4text">人员类别</h4>
			</div>
		</div>
		<div class="col-md-12">
			<div class="col-sm-3 ">
				<div class="col-xs-5 btn-danger">
					<p class="p-text">人员表</p>
					<div class="circle">
						<img src="${ctx }/wddc/skins/images/9.png" class="icon-size" alt="" />
					</div>
				</div>
				<div class="col-xs-8 btn-light1" style="height:125px">
					<div class="col-md-12 text">
						<span id="ryb"><img class="imgSize" src="${ctx}/wddc/skins/images/loading2-black.gif"></span><span class="sm-text">条</span>
					</div>
					<div class="col-md-12">
						<span style="font-size:12px">数据最后更新时间:</span>
					</div>
					<div class="col-md-12">
						<p id="rybTime" class="sm-text">2017-3-20</p>
					</div>
				</div>
			</div>
			
			<div class="col-sm-3 ">
				<div class="col-xs-5 btn-info">
					<p class="p-text">个人基本信息</p>
					<div class="circle">
						<img src="${ctx }/wddc/skins/images/5.png" class="icon-size" alt="" />
					</div>
				</div>
				<div class="col-xs-8 btn-light1" style="height:125px">
					<div class="col-md-12 text">
						<span id="grjbxx"><img class="imgSize" src="${ctx}/wddc/skins/images/loading2-black.gif"></span><span class="sm-text">条</span>
					</div>
					<div class="col-md-12">
						<span style="font-size:12px">数据最后更新时间:</span>
					</div>
					<div class="col-md-12">
						<p id="grjbxxTime" class="sm-text">2017-3-20</p>
					</div>
				</div>
			</div>
			
			<div class="col-sm-3 ">
				<div class="col-xs-5 btn-warning">
					<p class="p-text">党员信息</p>
					<div class="circle">
						<img src="${ctx }/wddc/skins/images/13.png" class="icon-size" alt="" />
					</div>
				</div>
				<div class="col-xs-8 btn-light1" style="height:125px">
					<div class="col-md-12 text">
						<span id="dyxx"><img class="imgSize" src="${ctx}/wddc/skins/images/loading2-black.gif"></span><span class="sm-text">条</span>
					</div>
					<div class="col-md-12">
						<span style="font-size:12px">数据最后更新时间:</span>
					</div>
					<div class="col-md-12">
						<p id="dyxxTime" class="sm-text">2017-3-20</p>
					</div>
				</div>
			</div>
			
			<div class="col-sm-3 ">
				<div class="col-xs-5 btn-success">
					<p class="p-text">残疾信息</p>
					<div class="circle">
						<img src="${ctx }/wddc/skins/images/14.png" class="icon-size" alt="" />
					</div>
				</div>
				<div class="col-xs-8 btn-light1" style="height:125px">
					<div class="col-md-12 text">
						<span id="cjxx"><img class="imgSize" src="${ctx}/wddc/skins/images/loading2-black.gif"></span><span class="sm-text">条</span>
					</div>
					<div class="col-md-12">
						<span style="font-size:12px">数据最后更新时间:</span>
					</div>
					<div class="col-md-12">
						<p id="cjxxTime" class="sm-text">2017-3-20</p>
					</div>
				</div>
			</div>
			
			<div class="col-sm-3" style="margin-top:20px">
				<div class="col-xs-5 btn-purple">
					<p class="p-text">老龄信息</p>
					<div class="circle">
						<img src="${ctx }/wddc/skins/images/15.png" class="icon-size" alt="" />
					</div>
				</div>
				<div class="col-xs-8 btn-light1" style="height:125px">
					<div class="col-md-12 text">
						<span id="llxx"><img class="imgSize" src="${ctx}/wddc/skins/images/loading2-black.gif"></span><span class="sm-text">条</span>
					</div>
					<div class="col-md-12">
						<span style="font-size:12px">数据最后更新时间:</span>
					</div>
					<div class="col-md-12">
						<p id="llxxTime" class="sm-text">2017-3-20</p>
					</div>
				</div>
			</div>
			
		</div>
		<div class="col-md-12">
			<div class="panel" style="background-color:#f5f5f5;margin-top:20px"">
				<h4 class="h4text">专题类别</h4>
			</div>
		</div>
			<div class="col-md-12">
				<div class="col-sm-3 ">
					<div class="col-xs-5 btn-danger">
						<p class="p-text">志愿者队伍</p>
						<div class="circle">
							<img src="${ctx }/wddc/skins/images/16.png" class="icon-size" alt="" />
						</div>
					</div>
					<div class="col-xs-8 btn-light1" style="height:125px">
						<div class="col-md-12 text">
							<span id="zyzdw"><img class="imgSize" src="${ctx}/wddc/skins/images/loading2-black.gif"></span><span class="sm-text">条</span>
						</div>
						<div class="col-md-12">
							<span style="font-size:12px">数据最后更新时间:</span>
						</div>
						<div class="col-md-12">
							<p id="zyzdwTime" class="sm-text">2017-3-20</p>
						</div>

					</div>
				</div>

				<div class="col-sm-3 ">
					<div class="col-xs-5 btn-info">
						<p class="p-text">救助信息</p>
						<div class="circle">
							<img src="${ctx }/wddc/skins/images/17.png" class="icon-size" alt="" />
						</div>
					</div>
					<div class="col-xs-8 btn-light1" style="height:125px">
						<div class="col-md-12 text">
							<span id="jzxx"><img class="imgSize" src="${ctx}/wddc/skins/images/loading2-black.gif"></span><span class="sm-text">条</span>
						</div>
						<div class="col-md-12">

							<span style="font-size:12px">数据最后更新时间:</span>
						</div>
						<div class="col-md-12">
							<p id="jzxxTime" class="sm-text">2017-3-20</p>
						</div>

					</div>
				</div>
				<div class="col-sm-3 ">
					<div class="col-xs-5 btn-warning">
						<p class="p-text">就业信息</p>
						<div class="circle">
							<img src="${ctx }/wddc/skins/images/18.png" class="icon-size" alt="" />
						</div>
					</div>
					<div class="col-xs-8 btn-light1" style="height:125px">
						<div class="col-md-12 text">
							<span id="jyxx"><img class="imgSize" src="${ctx}/wddc/skins/images/loading2-black.gif"></span><span class="sm-text">条</span>
						</div>
						<div class="col-md-12">

							<span style="font-size:12px">数据最后更新时间:</span>
						</div>
						<div class="col-md-12">
							<p id="jyxxTime" class="sm-text">2017-3-20</p>
						</div>
					</div>
				</div>
				<div class="col-sm-3 ">
					<div class="col-xs-5 btn-success">
						<p class="p-text">侨情信息</p>
						<div class="circle">
							<img src="${ctx }/wddc/skins/images/1.png" class="icon-size" alt="" />
						</div>
					</div>
					<div class="col-xs-8 btn-light1" style="height:125px">
						<div class="col-md-12 text">
							<span id="qqxx"><img class="imgSize" src="${ctx}/wddc/skins/images/loading2-black.gif"></span><span class="sm-text">条</span>
						</div>
						<div class="col-md-12">

							<span style="font-size:12px">数据最后更新时间:</span>
						</div>
						<div class="col-md-12">
							<p id="qqxxTime" class="sm-text">2017-3-20</p>
						</div>
					</div>
				</div>
				
				<div class="col-sm-3 " style="margin-top:20px">
					<div class="col-xs-5 btn-purple">
						<p class="p-text">房屋信息</p>
						<div class="circle">
							<img src="${ctx }/wddc/skins/images/19.png" class="icon-size" alt="" />
						</div>
					</div>
					<div class="col-xs-8 btn-light1" style="height:125px">
						<div class="col-md-12 text">
							<span id="fwxx"><img class="imgSize" src="${ctx}/wddc/skins/images/loading2-black.gif"></span><span class="sm-text">条</span>
						</div>
						<div class="col-md-12">

							<span style="font-size:12px">数据最后更新时间:</span>
						</div>
						<div class="col-md-12">
							<p id="fwxxTime" class="sm-text">2017-3-20</p>
						</div>
					</div>
				</div>
			</div>
	</div>
</div>
<jsp:include page="/cj/foot.jsp"/>
<script type="text/javascript">
	function Format (strTime) {
	    var date = new Date(strTime);
	    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
	}
	//从服务获取数据 ${fwurl}?unitKey=***&fwCode=***
	$.ajax({
		type:"get",
		url:"${fwurl}?unitKey=${unitKey}&fwCode=fw00013&id=F00022",
		dataType:"text",
		success:function(result){
			var data = eval('('+result+')');
			if(data.DATA.person.count != ""||data.DATA.person.count != null){
				$("#ryb").html(data.DATA.person.count);
			}else{
				$("#ryb").html(0);
			}
			if(data.DATA.personInfo.count != ""||data.DATA.personInfo.count != null){
				$("#grjbxx").html(data.DATA.personInfo.count);
			}else{
				$("#grjbxx").html(0);
			}
			if(data.DATA.dyInfo.count != ""||data.DATA.dyInfo.count != null){
				$("#dyxx").html(data.DATA.dyInfo.count);
			}else{
				$("#dyxx").html(0);
			}
			if(data.DATA.bodyInfo.count != ""||data.DATA.bodyInfo.count != null){
				$("#cjxx").html(data.DATA.bodyInfo.count);
			}else{
				$("#cjxx").html(0);
			}
			if(data.DATA.laolingInfo.count !=""||data.DATA.laolingInfo.count!=null){
				$("#llxx").html(data.DATA.laolingInfo.count);
			}else{
				$("#llxx").html(0);
			}
			if(data.DATA.volunteer.count != ""||data.DATA.volunteer.count != null){
				$("#zyzdw").html(data.DATA.volunteer.count);
			}else{
				$("#zyzdw").html(0);
			}
			if(data.DATA.rescueInfo.count !=""||data.DATA.rescueInfo.count !=null){
				$("#jzxx").html(data.DATA.rescueInfo.count);
			}else{
				$("#jzxx").html(0);
			}
			if(data.DATA.employmentInfo.count != ""||data.DATA.employmentInfo.count !=null){
				$("#jyxx").html(data.DATA.employmentInfo.count);
			}else{
				$("#jyxx").html(0);
			}
			if(data.DATA.qiaoqingInfo.count !=""||data.DATA.qiaoqingInfo.count !=null){
				$("#qqxx").html(data.DATA.qiaoqingInfo.count);
			}else{
				$("#qqxx").html(0);
			}
			if(data.DATA.houseInfo.count != ""||data.DATA.houseInfo.count !=null){
				$("#fwxx").html(data.DATA.houseInfo.count);
			}else{
				$("#fwxx").html(0);
			}
			
			$("#rybTime").html(Format(data.DATA.person.lastupdatetime));
			$("#grjbxxTime").html(Format(data.DATA.personInfo.lastupdatetime));
			$("#dyxxTime").html(Format(data.DATA.dyInfo.lastupdatetime));
			$("#cjxxTime").html(Format(data.DATA.bodyInfo.lastupdatetime));
			$("#llxxTime").html(Format(data.DATA.laolingInfo.lastupdatetime));
			$("#zyzdwTime").html(Format(data.DATA.volunteer.lastupdatetime));
			$("#jzxxTime").html(Format(data.DATA.rescueInfo.lastupdatetime));
			$("#jyxxTime").html(Format(data.DATA.employmentInfo.lastupdatetime));
			$("#qqxxTime").html(Format(data.DATA.qiaoqingInfo.lastupdatetime));
			$("#fwxxTime").html(Format(data.DATA.houseInfo.lastupdatetime));
		}
	});
</script>
</body>
</html>