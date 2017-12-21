<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=8" />
	<title>房屋详细户籍信息</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
	<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
	<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
	<jsp:include page="/common/meta.jsp"/>
	<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
	<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
.info_title_aa,.info_title_bb {
	text-align:center;
}
.info_title_aa {
	background:url(${ctx}/skins/query/images/info_title_bg.jpg) repeat-x;
	background-size:100%;
}
.info_title_bb {
	background:#FFF;
}
</style>
</head>
<body style="overflow:scroll;overflow-x:hidden;">
	<table style="width:96% border:0; align:center; cellpadding:0; cellspacing:0;">
  		<tr>
  	 	 <td>&nbsp;</td>
 		 </tr>
	</table>
	
	
	
	<table width="94%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr><td>
	<div id="usual1" class="usual" style="margin-top:20px;">
		<div class="itab">
			<ul>
				<li><a href="#tab1" class="selected">户籍情况</a></li>
				<li><a href="#tab2" >居住情况</a></li>
			</ul>
		</div>
		<div id="tab1" class="tabson">
			<div id="main1">
				<div style="position:relative; z-index:1;">
				<div style="position:absolute;z-index:2;width: 100%;height: 100% ;background:url('${ctx}/watermark/${obj.userid}.png');"> </div>
				<%-- <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top" id="list_search" >
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_list">
									<tr>
										<td width="25%" align="center" style="font-weight:bold;border-bottom: dotted 0px #123456;"><font size="4" color="white">户籍地址</font></td>
										<td width="75%" align="left" colspan="3"><font size="4" color="white" style="border-bottom: dotted 0px #123456;">${obj.map1.address }</font></td>
									</tr>
									<tr>
										<td width="25%" align="center" style="font-weight:bold;border-bottom: dotted 0px #123456;"><font size="4" color="white">所属居委会</font></td>
				      					<td width="25%" align="left"><font size="4" color="white" style="border-bottom: dotted 0px #123456;">${obj.map1.jcwmc }</font></td>
										<td width="25%" align="center" style="font-weight:bold;border-bottom: dotted 0px #123456;"><font size="4" color="white">所属街道</font></td>
										<td width="25%" align="left"><font size="4" color="white" style="border-bottom: dotted 0px #123456;">${obj.map1.jdmc }</font></td>
									</tr>
							</table>
							
						</td>
					</tr>
				</table> --%>
				<c:forEach items="${obj.list1}" var="house" varStatus="row">
				<div style="height: 20px">&nbsp;</div>
				<table width="100%" align="center" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
			    		<tr>
			    			<td width="20%" class="info_title_a">姓名</td>
			          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.xm }</td>
			          		<td width="20%" class="info_title_a">性别</td>
			          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.xbhz }</td>
			    		</tr>
			    		<tr>
			    			<td width="20%" class="info_title_a">身份证号</td>
			          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.zjhm }</td>
			          		<td width="20%" class="info_title_a">出生年月</td>
			          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.csrq }</td>
			    		</tr>
			    		<%-- <tr>
			    			<td width="20%" class="info_title_a">婚姻状况</td>
			          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.hyzkhz }</td>
			          		<td width="20%" class="info_title_a">工作单位</td>
			          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.fwcs }</td>
			    		</tr>
			    		<tr>
			    			<td width="20%" class="info_title_a">文化程度</td>
			          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.whcdhz }</td>
			          		<td width="20%" class="info_title_a">是否空挂户</td>
			          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.is_kgh }</td>
			    		</tr>
			    		<tr>
			    			<td width="20%" class="info_title_a">户号</td>
			          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.hh }</td>
			          		<td width="20%" class="info_title_a">户主或与户主关系</td>
			          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.yhzgxhz }</td>
			    		</tr> --%>
			    		
			    		<tr>
			    			<td width="20%" class="info_title_a">户籍地址</td>
			          		<td width="75%" colspan="3" bgcolor="#FFFFFF" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			          		&nbsp;&nbsp;&nbsp;&nbsp;${house.address }</td>
			    		</tr>
			    		
			    	</table>
			    <div style="height: 20px">&nbsp;</div>
			    </c:forEach>
			    </div>
		    </div>
		   	<div style="height: 20px">&nbsp;</div>
		   	<div id="info1" style="height: 100%;font-size:20px;margin-left:25px;">此地址下无户籍信息！</div>
	    </div>
			
		<div id="tab2" class="tabson">
			<div id="main2">
			<div style="position:relative; z-index:1;">
			<div style="position:absolute;z-index:2;width: 100%;height: 100% ;background:url('${ctx}/watermark/${obj.userid}.png');"> </div>
			<%-- <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top" id="list_search" >
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_list">
									<tr>
										<td width="25%" align="center" style="font-weight:bold;border-bottom: dotted 0px #123456;"><font size="4" color="white">居住地址</font></td>
										<td width="75%" align="left" colspan="3"><font size="4" color="white" style="border-bottom: dotted 0px #123456;">${obj.map2.address }</font></td>
									</tr>
									<tr>
										<td width="25%" align="center" style="font-weight:bold;border-bottom: dotted 0px #123456;"><font size="4" color="white">所属居委会</font></td>
				      					<td width="25%" align="left"><font size="4" color="white" style="border-bottom: dotted 0px #123456;">${obj.map2.jcwmc }</font></td>
										<td width="25%" align="center" style="font-weight:bold;border-bottom: dotted 0px #123456;"><font size="4" color="white">所属街道</font></td>
										<td width="25%" align="left"><font size="4" color="white" style="border-bottom: dotted 0px #123456;">${obj.map2.jdmc }</font></td>
									</tr>
							</table>
							
						</td>
					</tr>
			</table> --%>
			<c:forEach items="${obj.list2}" var="house" varStatus="row">
			<div style="height: 20px">&nbsp;</div>
			<table width="100%" align="center" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
		    		<tr>
		    			<td width="20%" class="info_title_a">姓名</td>
		          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.xm }</td>
		          		<td width="20%" class="info_title_a">性别</td>
		          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.xbhz }</td>
		    		</tr>
		    		<tr>
		    			<td width="20%" class="info_title_a">证件种类汉字</td>
		          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.zjzlhz }</td>
		          		<td width="20%" class="info_title_a">证件号码</td>
		          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.zjhm }</td>
		    		</tr>
		    		<%-- <tr>
		    			<td width="20%" class="info_title_a">文化程度</td>
		          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.whcdhz }</td>
		          		<td width="20%" class="info_title_a">与主居住人关系</td>
		          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.yhzgxhz }</td>
		    		</tr>
		    		<tr>
		    			<td width="20%" class="info_title_a">籍贯</td>
		          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.jghz }</td>
		          		<td width="20%" class="info_title_a">婚姻状况</td>
		          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.hyzkhz }</td>
		    		</tr>
		    		<tr>
		    			<td width="20%" class="info_title_a">人口类别</td>
		          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.rklb }</td>
		          		<td width="20%" class="info_title_a">入境时间</td>
		          		<td width="30%" bgcolor="#FFFFFF" align="center">${house.rjsj }</td>
		    		</tr>
		    		<tr>
		    			<td width="20%" class="info_title_a">居住地址</td>
		          		<td width="80%" colspan="3" bgcolor="#FFFFFF" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		          		&nbsp;&nbsp;${house.address }</td>
		    		</tr>--%>
		    		<tr>
		    			<td width="20%" class="info_title_a">居住地址</td>
		          		<td width="80%" colspan="3" bgcolor="#FFFFFF" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		          		&nbsp;&nbsp;${house.address }</td>
		    		</tr> 
		    	</table>
		    <div style="height: 20px">&nbsp;</div>
		    </c:forEach>
		    </div>
		    </div>
		   	<div style="height: 20px">&nbsp;</div>
		   	<div id="info2" style="height: 100%;font-size:20px;margin-left:25px;">此地址下无居住信息！</div>
	    </div>
	</div>
	</td></tr>
</table>
	
	
</body>
<script type="text/javascript"> 

	  $("#usual1 ul").idTabs(); 
	  $(function(){
			var $node = $('#watermark');  
			//if是判断是否ie,IE8及一下版本只能用滤镜实现透明,ie9、else火狐等用opacity属性  
			if(!document.all){  
				$node.addClass('no_ie_style');
			}  
		});
	  
	  $("#main1").hide();
      $("#main2").hide();
      $("#info1").hide();
      $("#info2").hide();
      var flag = "${obj.flag}";
     
	  	if( flag == "0" ){
	  		$("#info1").show();
	  		$("#info2").show();
	  	}
	  	if( flag == "1" ){
	  		$("#main1").show();
	  		$("#info2").show();
	  	}
		if( flag == "2" ){
			$("#info1").show();
	  		$("#main2").show();
		}
		if( flag == "3" ){
			$("#main1").show();
	  		$("#main2").show();
		}
      
    
      
      
      
</script>
</html>