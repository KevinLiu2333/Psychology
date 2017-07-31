<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>松江区政务数据中心</title>
<link href="${ctx}/skins/index/css/home.css" rel="stylesheet" type="text/css" />
<script src="${ctx }/skins/index/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/skins/index/js/home/lunbo.js"></script>
<script type="text/javascript" src="${ctx}/skins/index/js/home/textScroll.js"></script>
<script type="text/javascript" src="${ctx}/skins/index/js/home/dianji.js"></script>
<script src="${ctx}/tiles/echarts/echarts.js" ></script>
<style type="text/css">
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
 .header_info_title{width:1002px; margin:0 auto; overflow:hidden;}
 .header_info_title img{float:left;margin-top:17px;}
 .header_info_title a{float:left;padding:0 20px;line-height:82px;color:#ffffff; font-family:"微软雅黑";font-size: 15px;margin-left:95px; }
}

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
	
	//数量统计折线图
	function drawSltj(ec){
		var temp;
		$.ajax({
         type: "GET",
         url: "${ctx}/dp/queryjson?info.sqlcode=20160519172846889",
         data: {},
         async : false,
         success: function(data){
        		data = eval('('+data+')');
				temp = data;
         }
     	});
		
		var myChart = ec.init(document.getElementById('sltj'));
		var option = {
				title : {
			        text: '基础数据统计走势图 ',
			        x:'left'
			    },tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['基础数据总量'],
			    	y : 'bottom'
			    },
			    
			    calculable : true,
			    xAxis : [
			             {
			                 type : 'category',
			                 boundaryGap : false,
			                 data : cldjYear
			             }
			         ],
			         yAxis : [
			                  {
			                      type : 'value',
			                      axisLabel : {
			                          formatter: '{value}'
			                      }
			                  }
			     ],
			     series : [
							{
								name:"基础数据总量",
								type:'line',
								smooth:true,
								data:cldjValue,
								itemStyle: {
									normal: {
										areaStyle: {type: 'default',
											//填充颜色
											color : 'rgba(95, 165, 85,0.8)'
											},
										}
								},
								markPoint : {
							        data : [
							            {type : 'max', name: '最大值'},
							            {type : 'min', name: '最小值'}
							        ]
							    }
							}
	            ]
		};
		myChart.setOption(option);
		
	}
	
	//资源目录占比 
	function drawZyzb(ec)
	{
		//使用SQLCode获取数据
		var temp;
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
			    },
			    
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

	//获取首页人口和法人数据
	$(document).ready(function(){
		$.post("${ctx}/dp/piequery?info.sqlcode=20160516160951884",
				{ Action: "post"},
				function(data, textStatus){
					data = eval('('+data+')');
					$('.span01 h2').text(data[0].value);//人口总数
					$('.span02 h2').text(data[2].value);//法人总数
				}
			);
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
					<li><label><div class="code" id="checkCode" onclick="createCode()" style="margin-left: 110px;margin-right: auto;	"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="createCode()">看不清换一张</a></label></li>
					<li><input type="button" value="确认提交" class="submitBtn" onclick="loginValidate();"/></li>
					
				</ul>
			</form>
		</div>
	</div>
</div>
<!-- 登录窗口结束 -->

<c:if test="${obj.user eq null}">
	<div class="header">
		<div class="header_info" id="btnLogin"><img src="${ctx}/skins/index/images/home/logo.png" alt=""/><a href="#" id='loginbtn'>登&nbsp;&nbsp;录</a></div> 
	</div>
</c:if>
<c:if test="${obj.user != null}">
	<div class="header">
		<div class="header_info_title" style="width:100%"><img style="margin-left:32%;" src="${ctx}/skins/index/images/home/logo.png" alt=""/>
			<a  >${obj.user.displayName}，您好！</a>
			<a style="margin-right:0px;margin-left:0px;" href="${ctx }/logout">退  出</a>
		</div> 
		
	</div>
</c:if>


<div class="banner">
	<div class="banner_box">
    	<ul class="box_photo">
          <li><img src="${ctx}/skins/index/images/home/banner_01.jpg" alt="" ></li>
          <li><img src="${ctx}/skins/index/images/home/banner_02.jpg" alt=""></li>
          <li><img src="${ctx}/skins/index/images/home/banner_03.jpg" alt=""></li>
        </ul>
        <div class="banner_btn"><span class="current"></span><span></span><span></span></div>
    </div>
</div>
<div class="zmd_text">
	<ul class="textInfo">
    <li>
    	<c:if test="${obj.user != null}">
    		<c:forEach items="${obj.ownApplyList}" var="ownApply">
		    	<a href="#" onclick="showApply('${ownApply.applyId}')">${ownApply.applyTopic}</a>
	    	</c:forEach>
    	</c:if>
    	<c:if test="${obj.user eq null}">
    		<a>您还没有申请数据服务！</a>
    	</c:if>
    </li>
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
<div class="content">
      <!--第一行内容-->
	<div class="first">
    	<div class="first_left">
        	<ul>
            <li><img src="${ctx}/skins/index/images/home/icon-renkou.png" alt="" /><span class="span01"><h2>0</h2><p>&nbsp;</p><h3>（人）</h3></span></li>
            <li><img src="${ctx}/skins/index/images/home/icon-faren.png" alt="" /><span class="span02"><h2>0</h2><p>&nbsp;</p><h3>（家）</h3></span></li>
            </ul>
        </div>
        <!-- 数量统计折线图   -->
        <c:if test="${obj.user eq null}">
        	 <div class="first_right" id="sltj"><img src="${ctx}/skins/index/images/home/shuju.png" alt="" style="margin:20px 0 0 20px" /></div>
        </c:if>
        
        <c:if test="${obj.user != null}">
        	<div class="first_right">
        	<ul style="margin-left:15px;font-size: 15px;line-height: 25px; cursor:pointer;">
        		<c:if test="${obj.user.type eq '0'}">
	        		<!-- 
	        		<li><a href="${ctx}/zymlgx/toResourceApplyList?userId=${obj.user.userId}" target="main_frame">● 我的申请</a></li>
        			<li><a href="${ctx}/zymlgx/checkResourceApply?userId=${obj.user.userId}" target="main_frame">● 我的审核</a></li>
	        		 -->
	        		<li><a href="#" onclick="toCatalogTemplet('${obj.user.userId}')">● 目录模板</a></li>
        			<li><a href="${ctx}/zymlgl/toZxbmList?userId=${obj.user.userId}" target="main_frame">● 我的目录</a></li>
        			<li><a href="${ctx}/zymlgl/toBmEdit?userId=${obj.user.userId}" target="main_frame">● 在线编目</a></li>
        		</c:if>
        		<li><a href="${ctx}/zymlgl/toContentIndex?applyFlag=1&userId=${obj.user.userId}"  target="main_frame">● 目录浏览(数据预览、申请资源)</a></li>
        		<c:if test="${obj.user.type eq '1'}">
        			<li><a href="${ctx}/zymlgl/toZxbmList?userId=${obj.user.userId}" target="main_frame">● 目录审核</a></li>
        		</c:if>
        	</ul>
        </div>
        </c:if>
    </div>
    
    <!--第二行内容-->
    <div class="second">
    	<div class="second_left">
        	<div class="title">
            	<span><img src="${ctx}/skins/index/images/home/icon-shujml.png" alt="" align="absmiddle" />目录资源</span><a href="${ctx}/zymlgl/toContentIndex?isSy=1" target="_blank">更多>></a>
            </div>
              <img style="margin:0 8px;" src="${ctx}/skins/index/images/home/title_xian.jpg" alt="" />
              <dl>
              	<dt class="click_info" style="margin-left:24px;">基础</dt>
                <dt>专题</dt>
                <!-- 基础类 -->
                <dd>
                	<c:forEach items="${obj.jcResourceList}" var="resource">
                		<p><span class="textCon"><img src="${ctx}/skins/index/images/home/dian.jpg" alt="" align="absmiddle"/><a href="${ctx}/zymlgl/toDetail?resourceId=${resource.resourceId}&isSy=1" target="_blank">${resource.resourceName}</a></span>
                		<span class="liulan">已有${resource.browseCount}次浏览</span>
                		<span class="day"><fmt:formatDate value="${resource.updateDate}" pattern="yyyy-MM-dd"/></span></p>
                	</c:forEach>
                </dd>
                <!-- 专题类 -->
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
        <!-- 资源目录占比 -->
        <div >
        	 <div class="second_right" id="zyzb"></div>
        	<!-- 
        	<div><img src="${ctx}/skins/index/images/home/zymlzb.jpg" alt="" style="" /></div>
        	 -->
        	 
        </div>
    </div>
    
  <!--第三行内容-->  
    <div class="third">
    <div class="second_left third_left">
        	<div class="title">
            	<span><img src="${ctx}/skins/index/images/home/icon-shujufw.png" alt="" align="absmiddle" />数据服务</span><a href="${ctx}/home/moreApplyList" target="_blank">更多>></a>
            </div>
              <img style="margin:0 8px;" src="${ctx}/skins/index/images/home/title_xian.jpg" alt="" />
              
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
        	<img src="${ctx}/skins/index/images/home/tubiao_shuj.png" alt="" style="margin:65px 0 0 16px; float:left;" />
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
            	<span><img src="${ctx}/skins/index/images/home/icon-zhengcefw.png" alt="" align="absmiddle" />政策服务</span><a href="${ctx}/db/toDoc" target="_blank">更多>></a>
            </div>
              <img style="margin:0 8px;" src="${ctx}/skins/index/images/home/title_xian.jpg" alt="" />
              <dl>
              	<dt class="click_info" style="margin-left:24px;">政策法规</dt>
                <dt>操作规范</dt>
                <dt>其他</dt>
                
                <dd>
                	<c:forEach items="${obj.docList}" var="doc">
                		<p><span class="textCon"><img src="${ctx}/skins/index/images/home/dian.jpg" alt="" align="absmiddle"/><a href="${ctx}/db/file/fileDownById?fid=${doc.fid}">${doc.filenamelocal}</a></span>
                		<span class="liulan">文件大小&nbsp;<fmt:formatNumber value="${doc.filelength/1024}" pattern="#.0"></fmt:formatNumber>kb</span>
                		<span class="day"><fmt:formatDate value="${doc.postedtime}" pattern="yyyy-MM-dd"/></span></p>
                	</c:forEach>
                </dd>
                <dd><p>2</p></dd>
                <dd><p>3</p></dd>
              </dl>
        </div>
        <div class="fourth_right">
        	<a href="http://192.168.104.6/gisplatform"><img src="${ctx}/skins/index/images/home/bnt_ditu.png" alt=""/></a>
        </div>
    </div>
    
    <!--第五行内容--> 
    <div class="fifth">
    	<ul>
        	<li>
            	<h3>数据目录编制情况排序</h3>
                <div class="fifth_con"><p>松江科委</p><span>30个<img src="${ctx}/skins/index/images/home/incon-up.png" alt="" align="absmiddle" /></span></div>
                <div class="fifth_con"><p>松江民政局</p><span>20个<img src="${ctx}/skins/index/images/home/icon-down.png" alt="" align="absmiddle" /></span></div>
                <div class="fifth_con"><p>松江社治办</p><span>10个<img src="${ctx}/skins/index/images/home/incon-up.png" alt="" align="absmiddle" /></span></div>
                <div class="fifth_con"><p>松江公安局</p><span>6个<img src="${ctx}/skins/index/images/home/incon-up.png" alt="" align="absmiddle" /></span></div>
            </li>
            <li>
            	<h3>部门资源数量排序</h3>
                <div class="fifth_con"><p>松江科委</p><span>30个<img src="${ctx}/skins/index/images/home/incon-up.png" alt="" align="absmiddle" /></span></div>
                <div class="fifth_con"><p>松江民政局</p><span>20个<img src="${ctx}/skins/index/images/home/icon-down.png" alt="" align="absmiddle" /></span></div>
                <div class="fifth_con"><p>松江社治办</p><span>10个<img src="${ctx}/skins/index/images/home/incon-up.png" alt="" align="absmiddle" /></span></div>
                <div class="fifth_con"><p>松江公安局</p><span>6个<img src="${ctx}/skins/index/images/home/incon-up.png" alt="" align="absmiddle" /></span></div>
            </li>
            <li>
            	<h3>资源服务调用次数排序</h3>
                <div class="fifth_con"><p>松江科委</p><span>30个<img src="${ctx}/skins/index/images/home/incon-up.png" alt="" align="absmiddle" /></span></div>
                <div class="fifth_con"><p>松江民政局</p><span>20个<img src="${ctx}/skins/index/images/home/icon-down.png" alt="" align="absmiddle" /></span></div>
                <div class="fifth_con"><p>松江社治办</p><span>10个<img src="${ctx}/skins/index/images/home//incon-up.png" alt="" align="absmiddle" /></span></div>
                <div class="fifth_con"><p>松江公安局</p><span>6个<img src="${ctx}/skins/index/images/home/incon-up.png" alt="" align="absmiddle" /></span></div>
            </li>
        </ul>
    </div>
</div>
<div class="footer">
	<span></span>
</div>
</body>
</html>
