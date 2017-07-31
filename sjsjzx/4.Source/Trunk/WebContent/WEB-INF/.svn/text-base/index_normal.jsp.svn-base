<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>松江区政务数据中心</title>
<link href="${ctx}/skins/css/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/skins/SYJS/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/skins/SYJS/lunbo.js"></script>
<script type="text/javascript" src="${ctx}/skins/SYJS/textScroll.js"></script>
<script type="text/javascript" src="${ctx}/skins/SYJS/dianji.js"></script>
<script src="${ctx}/tiles/echarts/echarts.js" ></script>
<style type="text/css">

.button{
	position: relative; 
    overflow: visible; 
    display: inline-block; 
    padding: 0.5em 1em; 
    border: 1px solid #6495ED; 
    margin: 0;
    width:120px;
    text-decoration: none; 
    text-shadow: 1px 1px 0 #fff; 
    font-family:"Microsoft YaHei", "微软雅黑", "宋体", Arial, Helvetica, sans-serif;
	font-size: 16px;
    color: #333; 
    font-weight:bold;
    white-space: nowrap; 
    cursor: pointer; 
    outline: none; 
    background-color: #fff;
    -webkit-background-clip: padding;
    -moz-background-clip: padding;
    -o-background-clip: padding-box; 
    /*background-clip: padding-box;*/ /* commented out due to Opera 11.10 bug */
    -webkit-border-radius: 0.2em; 
    -moz-border-radius: 0.2em; 
    border-radius: 0.2em; 
    /* IE hacks */
    zoom: 1; 
    *display: inline; 
}
 .red{
     color:red;
    }
.dfinput{width:345px; height:25px; line-height:25px; border-top:solid 1px #a7b5bc; border-left:solid 1px #a7b5bc; border-right:solid 1px #ced9df; border-bottom:solid 1px #ced9df; background:url(../images/inputbg.gif) repeat-x; text-indent:10px;}

.code 
{
 background:url(code_bg.jpg);
 font-family:Arial;
 font-style:italic;
 color:blue;
 font-size:30px;
 border:0;
 padding:2px 3px;
 letter-spacing:3px;
 font-weight:bolder;
 float:left;
 cursor:pointer;
 width:50px;
 height:10px;
 line-height:10px;
 text-align:center;
 vertical-align:middle;
 }
.header_info_title{width:1002px; margin:0 auto; overflow:hidden; background:url(${ctx}/skins/images/index/header_bg_02.png) bottom right no-repeat;}
.header_info_title img{float:left;margin-top:17px;}
.header_info_title a{margin-left:7%;padding:0 20px;line-height:82px;color:#0f51b2; font-family:"微软雅黑";}
}

.animated{
	-webkit-animation-duration:1.4s;
	animation-duration:1.4s;
	-webkit-animation-fill-mode:both;
	animation-fill-mode:both
}
#dialogBg{width:100%;background-color:#000000;opacity:.6;filter:alpha(opacity=60);position:fixed;top:0;left:0;z-index:9999;display:none;}
#dialog{width:350px;height:240px;margin:0 auto;display:none;background-color:#ffffff;position:fixed;top:50%;left:50%;margin:-120px 0 0 -150px;z-index:10000;border:1px solid #ccc;border-radius:5px;-webkit-border-radius:5px;}
.dialogTop{width:90%;margin:0 auto;border-bottom:1px dotted #ccc;letter-spacing:1px;padding:10px 0;text-align:right;}
.dialogIco{width:50px;height:50px;position:absolute;top:-25px;left:50%;margin-left:-25px;}
.editInfos{padding:15px 0;}
.editInfos li{width:90%;margin:8px auto auto;text-align: center;}
.ipt{border:1px solid #ccc;padding:5px;border-radius:3px;-webkit-border-radius:3px;box-shadow:0 0 3px #ccc inset;-webkit-box-shadow:0 0 3px #ccc inset;margin-left:5px;}
.ipt:focus{outline:none;border-color:#66afe9;box-shadow:0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(102, 175, 233, 0.6);-webkit-box-shadow:0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(102, 175, 233, 0.6);}
.submitBtn{width:90px;height:30px;line-height:30px;font-family:"微软雅黑","microsoft yahei";cursor:pointer;margin-top:3px;display:inline-block;border-radius:5px;-webkit-border-radius:5px;text-align:center;background-color:#428bca;color:#fff;box-shadow: 0 -3px 0 #2a6496 inset;-webkit-box-shadow: 0 -3px 0 #2a6496 inset;}


</style>
<script type="text/javascript">
	require.config({
		paths: {
			echarts: '${ctx}/tiles/echarts'
		}
	}); 
	require([
				'echarts',
				'echarts/chart/pie',
				'echarts/chart/scatter',
				'echarts/chart/bar',
				'echarts/chart/line'
			],function (ec){
		//绘制统计图
		drawZyzb(ec);		
	});
	
	
	
	//资源目录占比 
	function drawZyzb(ec)
	{
		//使用SQLCode获取数据
		var temp=null;
		$.ajax({
         type: "GET",
         url: "${ctx}/home/getResourceRatio",
         data: {},
         async : false,
         success: function(data){
        		data = eval('('+data+')');
				temp = data;
         }
     	});
		
		var myChart = ec.init(document.getElementById('zyzb'));
		option = {
				title: {
					text: '资源目录占比',
					x: 'center',
					y: 240,
					textStyle:{
						fontSize:18,
						fontWeight:'bolder',
						color:'#0F51B2'
					}
					
				},
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },v
			    
			    color:['#EB779C', '#3CA9EA','#1864A2','blueviolet'] ,
			    calculable : true,
			    series : [
			        {
			            name:'资源类型',
			            type:'pie',
			            radius : ['30%', '55%'],
			            center : ['50%', '45%'],
			            itemStyle : {
			                normal : {
			                    label : {
			                        show : true,
			                        textStyle:{
			                        	color:'#2B7ABB'
			                        }
			                    },
			                    labelLine : {
			                        show : true,
			                   }
			                },
			                emphasis : {
			                    label : {
			                        show : false,
			                        position : 'center',
			                        textStyle : {
			                            fontSize : '30',
			                            fontWeight : 'bold'
			                        }
			                    }
			                }
			            },
			            data:[
			                {value:temp[0].rkl, name:'人口类'},
			                {value:temp[1].frl, name:'法人类'},
			                {value:temp[2].fwl, name:'房屋类'}
			            ]
			        }
			    ]
			};
		myChart.setOption(option);
	}

	$(document).keydown(function(event){
		if(event.keyCode==13){
			loginValidate();
		}
		}); 
	//登录框
	var w,h,className;
	function getSrceenWH(){
		w = $(window).width();
		h = $(window).height();
		$('#dialogBg').width(w).height(h);
	}
	window.onresize = function(){  
		getSrceenWH();
	};  
	$(window).resize();  
	$(function(){
		getSrceenWH();	
		//显示弹框
		$('#btnLogin a').click(function(){
			className = $(this).attr('class');
			$('#dialogBg').fadeIn(300);
			$('#dialog').removeAttr('class').addClass('animated '+className+'').fadeIn();
		});
		//关闭弹窗
		$('.claseDialogBtn').click(function(){
			$('#dialogBg').fadeOut(300,function(){
				$('#dialog').addClass('bounceOutUp').fadeOut();
			});
		});
	});
	//获取首页人口和法人数据
	$(document).ready(function(){
		$.post("${ctx}/echarts/queryjson?theme_id=77",
				{ Action: "post"},
				function(data, textStatus){
					data = eval('('+data+')');
					var rk;
					var fr;
					var bzf;
					if(data.syrk.length>4){
						rk=(Number(data.syrk/10000)).toFixed(2)+"万";
					}
					else{
						rk=data.syrk;
						}
					if(data.syfr.length>4 )
					{
						fr=(Number(data.syfr/10000)).toFixed(2)+"万";
						
						}
					else{
						fr=data.syfr;
						}
					if(data.ryfw.length>4){
						bzf=(Number(data.ryfw/10000)).toFixed(2)+"万";
						}else
						{
							bzf=data.ryfw;
						}
					$('#.span03 h2').html(bzf);
					$('#.span02 h2').html(fr);
					$('#.span01 h2').html(rk);
				});
		iuy();
	});
	function iuy()
	{
		$.post("${ctx}/echarts/queryjson?theme_id=67",
				{ Action: "post"},
				function(data, textStatus){
					data = eval('('+data+')');
					var rk;
					var szb;
					var kw;
					if(data.syrk.length>4){
						rk=(Number(data.syrk/10000)).toFixed(2)+"万条";
					}
					else{
						rk=data.syrk+"条";
						}
					if(data.szb.length>4 )
					{
						szb=(Number(data.szb/10000)).toFixed(2)+"万条";
						
						}
					else{
						szb=data.szb+"条";
						}
					if(data.kw.length>4){
						kw=(Number(data.kw/10000)).toFixed(2)+"万条";
						}else
						{
							kw=data.kw+"条";
						}
					$('#kww').html(kw);
					if(szb ==0||szb==null)
					{
						szb==0+"条";
						}else{
							$('#szbbb').html(szb);
							}
					
					$('#gaaa').html(rk);
				});
		}
</script>
<!-- 验证码 -->
<script type="text/javascript">
var code;
function createCode() 
{
 code = "";
 var codeLength = 4; //验证码的长度
 var checkCode = document.getElementById("checkCode");
 var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
      'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的
 for(var i = 0; i < codeLength; i++) 
 {
  var charNum = Math.floor(Math.random() * 52);
  code += codeChars[charNum];
 }
 if(checkCode){
	  checkCode.className = "code";
	  checkCode.innerHTML = code;
 	}
}
function valiName(){
	var loginName = $('#loginName').val();
	if(loginName == ''){
		 alert("用户名不能为空!");
	 }
}
function valiPassword(){
	var password = $('#password').val();
	if(password == ''){
		 alert("密码不能为空!");
	 }
}
function loginValidate() 
{
	 var inputCode=document.getElementById("inputCode").value;
	 var loginName = $('#loginName').val();
	 var password = $('#password').val();
	 if(loginName == ''){
		 alert("用户名不能为空!");
		 return;
	 }
	 if(loginName =="admin" || loginName=="sjrkb")
	 {
		alert("用户名出错");
		return;
	 }
	 if(loginName =="administrator")
	 {
		alert("用户名出错");
		return;
	 }
	 if(password == ''){
		 alert("密码不能为空!");
		 return;
	 }
	 
	 if(inputCode.length <= 0){
	  	alert("请输入验证码！");
	  	return;
	 }else if(inputCode.toUpperCase() != code.toUpperCase()) {
	   alert("验证码输入有误！");
	   createCode();
	   return;
	 } else {
		 $.post("${ctx}/home/validateUser?loginName="+loginName+"&password="+password,
					{ Action: "post"},
					function(data, textStatus){
						data = eval('('+data+')');
						if(data == "1"){
							$('#LoginForm').submit();
						}else{
							alert("错误的用户名或密码!");
						}
					}
				);
	 }
	 
}  


var userId="${obj.user.userId}";
</script>
<script type="text/javascript">
	$(document).ready(function() {
		
				var n=0;
				$("#ctrlRight").click(function(){
					if(n<$(".third_info li").length-3){
						n=n+1;
						$(".third_info").animate({marginLeft:-530*n},600);
					}else{}
				});
				$("#ctrlLeft").click(function(){
					if(n>0){
						n=n-1;
						$(".third_info").animate({marginLeft:-530*n},600);
					}else{}
				});
    });
	
	//数据服务申请
	function showApply(applyId){
		var href = "${ctx}/home/singleApply?applyId="+applyId;
		window.showModalDialog(href, self, "dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
	}
	//目录模板
	function toCatalogTemplet(userId){
		var href = "${ctx}/zy/templet/catalogTemplet?userId="+userId;
		window.showModalDialog(href, self, "dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
	}
	function qu()
	{
		window.open("${ctx}/zymlgl/toContentIndex?applyFlag=1&userId=${obj.user.userId}");
	}
	function qu1()
	{
		window.open("${ctx}/zymlgl/toZxbmList?userId=${obj.user.userId}");
	}
	function qu2()
	{
		window.open("${ctx}/zymlgl/toBmEdit?userId=${obj.user.userId}");
	}
	function qu3()
	{
		window.open("${ctx}/zymlgl/toZxbmList?userId=${obj.user.userId}");
	}
	function qu4()
	{
		window.open("${ctx}/zymlgx/checkResourceApply?userId=${obj.user.userId}");
	}
	//修改密码
	function updateUser(){
		href = "${ctx}/update_pass";
			var returnValue = window.showModalDialog( href,'window',"dialogHeight=400px;dialogWidth=800px;center=yes");
			
	}
</script>
</head>

<body onload="createCode()">
<!-- 登录窗口开始 -->
<div id="wrapper">
	<div class="box">
				
		<div id="dialogBg"></div>
		<div id="dialog" class="animated">
			<img class="dialogIco" width="50" height="50" src="${ctx}/skins/index/images/home/ico.png" alt="" />
			<div class="dialogTop">
				<a href="javascript:;" class="claseDialogBtn">关闭</a>
			</div>
			<form action="${ctx}/login" method="post" id="LoginForm">
				<ul class="editInfos">
					<li><label><font color="#ff0000">* </font>用户名：<input type="text" name="loginName" id="loginName" value="" class="ipt" onblur="valiName()"/></label></li>
					<li><label><font color="#ff0000">* </font>密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password" id="password" value="" onblur="valiPassword()" class="ipt" /></label></li>
					<li><label><font color="#ff0000">* </font>校验码：<input type="text" name="" id="inputCode" required value="" class="ipt" /></label></li>
					<li><label><div class="code" id="checkCode" onclick="createCode()" style="margin-left: 110px;margin-right: auto;"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="createCode()">看不清换一张</a></label></li>
					<li><input type="button" value="确认提交" class="submitBtn" onclick="loginValidate();"/></li>
					
				</ul>
			</form>
		</div>
	</div>
</div>
<!-- 登录窗口结束 -->
<c:if test="${obj.user eq null}">
<div class="header">
	<div class="header_info" id="btnLogin"><img src="${ctx}/skins/images/index/logo.png" alt="" /><a href="#"  id='loginbtn'>登录</a></div> 
</div>
</c:if>
<c:if test="${obj.user != null}">
	<div class="header">
		<div class="header_info_title" style="width:100%"><img style="margin-left:17%;" src="${ctx}/skins/images/index/logo.png" alt=""/>
			<a id="ai" >${obj.user.displayName}，您好！</a>
			<a style="margin-left:3%;" href="#" onclick="updateUser();">修改密码</a>
			<a style="margin-left:-13px;" href="${ctx }/logout">退  出</a>
		</div> 
		
	</div>
</c:if>
<div class="banner">
	<div class="banner_box">
    	<ul class="box_photo">
          <li><a href="#"><img src="${ctx}/skins/images/index/banner_01.jpg" alt="" ></a></li>
          <li><a href="#"><img src="${ctx}/skins/images/index/banner_02.jpg" alt=""></a></li>
          <li><a href="#"><img src="${ctx}/skins/images/index/banner_03.jpg" alt=""></a></li>
        </ul>
        <div class="banner_btn"><span class="current"></span><span></span><span></span></div>
    </div>
</div>
<c:if test="${obj.user != null}">
<div class="zmd_text">
	<ul class="textInfo">
    
    <script>
    	$(function(){
			$(".textInfo").textScroll();
		});
    	function changeposition(position)
    	{
    		var p=document.getElementById("position");
    		p.innerHTML="当前位置："+position;
    	}
    </script>
    </ul>
</div>
</c:if>
<div style="margin-top:18px; border-top:1px solid #CDEBFF;border-bottom:1px solid #CDEBFF;line-height:38px;" align="center">
<c:if test="${obj.user != null}">
	<c:if test="${obj.user.type eq '0'}">
	 <!-- 
	 <input type="button" id="button"  class="button" value="目录模板" onclick="toCatalogTemplet('${obj.user.userId}')"/>
	  --> 
		<input type="button" id="button"  class="button" value="我的资源目录" onclick="qu1();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" id="button"  class="button" value="我的资源编目" onclick="qu2();" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" id="button"  class="button" value="我的共享申请" onclick="qu4();" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		<input type="button" id="button"  class="button" value="资源目录浏览" onclick="qu();" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</c:if>
		
	<c:if test="${obj.user.type eq '1'}">
	<input type="button" id="button"  class="button" value="我的资源目录" onclick="qu1();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" id="button" onclick="qu3();" class="button" value="资源编目审核" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" id="button"  onclick="qu4();" class="button" value="共享申请审核" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" id="button"  class="button" value="资源目录浏览" onclick="qu();" />
	</c:if>
</c:if>
        </div>
<div class="content">
      <!--第一行内容-->
	<div class="first">
    	<div class="first_left">
        	<ul>
            <li><img src="${ctx}/skins/images/index/icon-renkou.png" alt="" /><span class="span01"><h2 >191.04万</h2><p>&nbsp;</p><h3>（人）</h3></span></li>
            <li><img src="${ctx}/skins/images/index/icon-faren.png" alt="" /><span class="span02"><h2>13.36万</h2><p>&nbsp;</p><h3>（家）</h3></span></li>
            <li><img src="${ctx}/skins/images/index/icon-bzf.png" alt="" /><span class="span03"><h2>231.32万</h2><p>&nbsp;</p><h3>（平方米）</h3></span></li>
            </ul>
        </div>
        
    </div>
    <!--第二行内容-->
    <div class="second">
    	<div class="second_left">
        	<div class="title">
            	<span><img src="${ctx}/skins/index/images/home/icon-shujml.png" alt="" align="absmiddle" />资源目录</span><a href="${ctx}/zymlgl/toContentIndex?isSy=1" target="_blank">更多>></a>
            </div>
              <img style="margin:0 8px;" src="${ctx}/skins/images/index/title_xian.jpg" alt="" />
              <dl>
              	<dt class="click_info" style="margin-left:24px;">基础</dt>
                <dt>专题</dt>
                
                <dd>
                	<c:forEach items="${obj.jcResourceList}" var="resource">
                		<p><span class="textCon"><img src="${ctx}/skins/index/images/home/dian.jpg" alt="" align="absmiddle"/><a href="${ctx}/zymlgl/toDetail?resourceId=${resource.resourceId}&isSy=1" target="_blank">${resource.resourceName}</a></span>
                		<span class="liulan">已有${resource.browseCount}次浏览</span>
                		<span class="day"><fmt:formatDate value="${resource.updateDate}" pattern="yyyy-MM-dd"/></span></p>
                	</c:forEach>
                </dd>
                <dd>
                	<c:forEach items="${obj.topicResourceList}" var="resource">
                		<p><span class="textCon"><img src="${ctx}/skins/index/images/home/dian.jpg" alt="" align="absmiddle"/><a href="${ctx}/zymlgl/toDetail?resourceId=${resource.resourceId}&isSy=1" target="_blank">${resource.resourceName}</a></span>
                		<span class="liulan">已有${resource.browseCount}次浏览</span>
                		<span class="day"><fmt:formatDate value="${resource.updateDate}" pattern="yyyy-MM-dd"/></span></p>
                	</c:forEach>
                </dd>
              </dl>
              <script>
              $(function(){
					$(".second_left dl").dianji();
				});
              </script>
        </div>
         <div class="second_right" id="zyzb"></div>
    </div>
    
  <!--第三行内容-->  
    <div class="third">
    <div class="second_left third_left">
        	<div class="title">
            <span><img src="${ctx}/skins/index/images/home/icon-shujufw.png" alt="" align="absmiddle" />数据服务</span><a href="${ctx}/home/moreApplyList" target="_blank">更多>></a>
            </div>
              <img style="margin:0 8px;" src="${ctx}/skins/images/index/title_xian.jpg" alt="" />
              
              <div class="third_bnt">
              	<ul>
                    <li class="click_bnt">部门</li>
                    <li>系统</li>
                </ul>
                <script>
                $(function(){
					$(".third_con:gt(0)").hide();
					$(".third_bnt ul li").click(function(){
						$(this).addClass("click_bnt")
						.siblings().removeClass();
					
					var BntIndex = $(".third_bnt ul li").index(this);
					$(".third_con").hide().eq(BntIndex).fadeIn(200);	
						
						});
					});
                </script>
              </div>
              <div class="third_con">
              <div class="ctrl_left" id="ctrlLeft">&nbsp;</div>
                  <ul class="third_info">
                   <c:forEach items="${obj.resourceApplyList}" var="apply">
							<li>
								<h3><a href="#" onclick="showApply('${apply.applyId}')">${apply.resourceName}</a></h3>
								<p>
									<c:if test="${apply.applyTopic eq '' || apply.applyTopic eq null}">
										<br/><br/>
									</c:if>
									<c:if test="${fn:length(apply.applyTopic) < 13}">
										${apply.applyTopic}<br/><br/>
									</c:if>
									<c:if test="${fn:length(apply.applyTopic) > 24}">
										${apply.applyTopic}
									</c:if>
								</p>
								<h6 style="font-size: 12px;"><wd:dicvalue dicId="1003" dicCode="${apply.resourceProvider}"/>提供</h6>
							</li>
						</c:forEach>
                  </ul>
              <div class="ctrl_right" id="ctrlRight">&nbsp;</div>
              </div>
              <div class="third_con">2</div>
              
        </div>
        <div class="second_right">
        	<img src="${ctx}/skins/images/index/tubiao_shuj.png" alt="" style="margin:65px 0 0 16px; float:left;" />
            <div class="tubao_info">
            	<h5>已开通服务：<span>${obj.openedCount}个</span></h5>
                <h5>涉及到部门：<span>0个</span></h5>
                <h5>涉及到系统：<span>0个</span></h5>
                <h3>服务信息</h3>
            </div>
        </div>
    </div>
    
    <!--第四行内容--> 
    <div class="fourth">
    <div class="fourth_left">
        	<div class="title">
            	<span><img src="${ctx}/skins/images/index/icon-zhengcefw.png" alt="" align="absmiddle" />政策服务</span>
            	<a href="${ctx}/db/toDoc" target="_blank">更多>></a>
            </div>
              <img style="margin:0 8px;" src="${ctx}/skins/images/index/title_xian.jpg" alt="" />
              <dl>
              	<dt class="click_info" style="margin-left:24px;">政策法规</dt>
                <dt>操作规范</dt>
                <dt>其他</dt>
                
                <dd>
                	<c:forEach items="${obj.docList1}" var="doc">
                		<p><span class="textCon"><img src="${ctx}/skins/index/images/home/dian.jpg" alt="" align="absmiddle"/><a href="${ctx}/db/file/fileDownById?fid=${doc.fid}">${doc.filenamelocal}</a></span>
                		<span class="liulan">文件大小&nbsp;<fmt:formatNumber value="${doc.filelength/1024}" pattern="#.0"></fmt:formatNumber>kb</span>
                		<span class="day"><fmt:formatDate value="${doc.postedtime}" pattern="yyyy-MM-dd"/></span></p>
                	</c:forEach>
                </dd>
                <dd><c:forEach items="${obj.docList}" var="doc">
                	<c:if test="${doc.fileType == '操作规范'}">
                		<p><span class="textCon"><img src="${ctx}/skins/index/images/home/dian.jpg" alt="" align="absmiddle"/><a href="${ctx}/db/file/fileDownById?fid=${doc.fid}">${doc.filenamelocal}</a></span>
                		<span class="liulan">文件大小&nbsp;<fmt:formatNumber value="${doc.filelength/1024}" pattern="#.0"></fmt:formatNumber>kb</span>
                		<span class="day"><fmt:formatDate value="${doc.postedtime}" pattern="yyyy-MM-dd"/></span></p>
                	</c:if>
                	</c:forEach>
                </dd>
                <dd>
                	<c:forEach items="${obj.docList}" var="doc">
                	<c:if test="${doc.fileType == '其它'}">
                		<p><span class="textCon"><img src="${ctx}/skins/index/images/home/dian.jpg" alt="" align="absmiddle"/><a href="${ctx}/db/file/fileDownById?fid=${doc.fid}">${doc.filenamelocal}</a></span>
                		<span class="liulan">文件大小&nbsp;<fmt:formatNumber value="${doc.filelength/1024}" pattern="#.0"></fmt:formatNumber>kb</span>
                		<span class="day"><fmt:formatDate value="${doc.postedtime}" pattern="yyyy-MM-dd"/></span></p>
                	</c:if>
                	</c:forEach>
                </dd>
              </dl>
        </div>
        <div class="fourth_right">
        	<a href="http://192.168.104.7/gisplatform"><img src="${ctx}/skins/images/index/bnt_ditu.png" alt=""/></a>
        </div>
    </div>
    
    <!--第五行内容--> 
    <div class="fifth">
    	<ul>
        	<li>
            	<h3>资源目录编制情况</h3>
                <div class="fifth_con"><p>松江科委</p><span>${obj.kwCount}个</span></div>
                <div class="fifth_con"><p>松江民政局</p><span>${obj.mzCount}个</span></div>
                <div class="fifth_con"><p>松江社治办</p><span>${obj.szbCount}个</span></div>
                <div class="fifth_con"><p>松江区公安分局</p><span>${obj.gaCount}个</span></div>
            </li>
            <li>
            	<h3>资源接入数量</h3>
                <div class="fifth_con"><p>松江科委</p><span id="kww"></span></div>
                <div class="fifth_con"><p>松江民政局</p><span id="mzz">0条</span></div>
                <div class="fifth_con"><p>松江社治办</p><span id="szbbb"></span></div>
                <div class="fifth_con"><p>松江区公安分局</p><span id="gaaa"></span></div>
            </li>
            <li>
            	<h3>资源服务调用次数</h3>
                <div class="fifth_con"><p>松江科委</p><span>${obj.kwfu}个</span></div>
                <div class="fifth_con"><p>松江民政局</p><span>${obj.mzfu}个</span></div>
                <div class="fifth_con"><p>松江社治办</p><span>${obj.szbfu}个</span></div>
                <div class="fifth_con"><p>松江区公安分局</p><span>${obj.gafu}个</span></div>
            </li>
        </ul>
    </div>
</div>
<div class="footer">
	<span></span>
</div>
</body>
</html>
