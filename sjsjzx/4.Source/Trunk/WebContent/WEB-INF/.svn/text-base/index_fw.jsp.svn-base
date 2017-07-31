<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>松江区政务数据中心-信息综合利用服务平台</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <link href="${ctx }/skins/fw/resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx }/skins/fw/data/styles.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx }/skins/fw/files/home/styles.css" type="text/css" rel="stylesheet"/>
    <script src="${ctx }/skins/fw/resources/scripts/jquery-1.7.1.min.js"></script>
   
   <script type="text/javascript">
   function openService(openId){
	href = "${ctx }/fw/open?openId="+openId;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=910px;center=yes");
	 if (returnValue==1){
		 query();
	 }
	}
   function viewService(openId){
		href = "${ctx }/fw/view?openId="+openId;
		var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=910px;center=yes");
		 if (returnValue==1){
			 query();
		 }
		}
   function res(rId){
		href = "${ctx }/fw/res?rId="+rId;
		var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=910px;center=yes");
		 if (returnValue==1){
			 query();
		 }
		}
   function initData(){
		$.post("${ctx}/fw/usedData", 
		        { Action: "post"},
		        function (data, textStatus){
		            //初始值
			        var counts=0;
			        var sums=0;

			        for(var i=0;i<data.result.length;i++){
				        if(data.result[i].cm == 'getPersonInfo'){
			            	$("#cishu1"+data.result[i].cm).html(data.result[i].counts);
			            	$("#cishu2"+data.result[i].cm).html(data.result[i].counts);

				        }
				        if(data.result[i].cm == 'queryPersonListByJuWeiDM'){
			            	$("#cishu1"+data.result[i].cm).html(data.result[i].counts);
			            	$("#cishu2"+data.result[i].cm).html(data.result[i].counts);
				        }
		            	sums =counts+data.result[i].sums;
		            	counts =counts+data.result[i].counts;
			           
		            }
		               
	            	$("#shuliang1").html(sums);
	            	$("#shuliang2").html(sums);

	            	$("#cishu3").html(counts);
		            
		         }
		        , "json");
	}

   $(document).ready(function(){
	   initData();
	});

   setInterval("initData()",10000);
	</script>
   
  </head>
  <body>
    <div id="base" class="">

      <!-- Unnamed (Image) -->
      <div id="u0" class="ax_image">
        <img id="u0_img" class="img " src="${ctx }/skins/fw/images/home/u0.jpg"/>
        <!-- Unnamed () -->
        <div id="u1" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u2" class="ax_shape">
        <img id="u2_img" class="img " src="${ctx }/skins/fw/images/home/u2.png"/>
        <!-- Unnamed () -->
        <div id="u3" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u4" class="ax_shape">
        <img id="u4_img" class="img " src="${ctx }/skins/fw/images/home/u4.png"/>
        <!-- Unnamed () -->
        <div id="u5" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u6" class="ax_shape">
        <img id="u6_img" class="img " src="${ctx }/skins/fw/images/home/u6.png"/>
        <!-- Unnamed () -->
        <div id="u7" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u8" class="ax_shape">
        <img id="u8_img" class="img " src="${ctx }/skins/fw/images/home/u8.png"/>
        <!-- Unnamed () -->
        <div id="u9" class="text">
          <p><span>平台资源目录</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u10" class="ax_shape">
        <img id="u10_img" class="img " src="${ctx }/skins/fw/images/home/u10.png"/>
        <!-- Unnamed () -->
        <div id="u11" class="text">
          <p><span>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </span><span>&nbsp; &nbsp; </span><span>&nbsp;</span><span>&nbsp; </span><span>本月</span><span>调用</span><span>次数</span><span>&nbsp; </span><span>&nbsp; </span><span>&nbsp; &nbsp; &nbsp; </span><span>&nbsp; 总</span><span>调用</span><span>总</span><span>次数</span><span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;&nbsp; </span><span>&nbsp; &nbsp; &nbsp; </span><span>&nbsp;</span><span>总</span><span>申请</span><span>次数</span></p>
        </div>
      </div>

      <!-- Unnamed (Horizontal Line) -->
      <div id="u12" class="ax_horizontal_line">
        <img id="u12_start" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif" alt="u12_start"/>
        <img id="u12_end" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif" alt="u12_end"/>
        <img id="u12_line" class="img " src="${ctx }/skins/fw/images/home/u12_line.png" alt="u12_line"/>
      </div>

      <!-- Unnamed (Horizontal Line) -->
      <div id="u13" class="ax_horizontal_line">
        <img id="u13_start" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif" alt="u13_start"/>
        <img id="u13_end" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif" alt="u13_end"/>
        <img id="u13_line" class="img " src="${ctx }/skins/fw/images/home/u12_line.png" alt="u13_line"/>
      </div>

      <!-- Unnamed (Horizontal Line) -->
      <div id="u14" class="ax_horizontal_line">
        <img id="u14_start" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif" alt="u14_start"/>
        <img id="u14_end" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif" alt="u14_end"/>
        <img id="u14_line" class="img " src="${ctx }/skins/fw/images/home/u12_line.png" alt="u14_line"/>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u15" class="ax_shape">
        <img id="u15_img" class="img " src="${ctx }/skins/fw/images/home/u15.png"/>
        <!-- Unnamed () -->
        <div id="u16" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u17" class="ax_shape">
        <img id="u17_img" class="img " src="${ctx }/skins/fw/images/home/u17.png"/>
        <!-- Unnamed () -->
        <div id="u18" class="text">
          <p><span>单位名称</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u19" class="ax_shape">
        <img id="u19_img" class="img " src="${ctx }/skins/fw/images/home/u17.png"/>
        <!-- Unnamed () -->
        <div id="u20" class="text">
          <p><span>服务级别</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u21" class="ax_shape">
        <img id="u21_img" class="img " src="${ctx }/skins/fw/images/home/u17.png"/>
        <!-- Unnamed () -->
        <div id="u22" class="text">
          <p><span>服务</span><span>key</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u23" class="ax_shape">
        <img id="u23_img" class="img " src="${ctx }/skins/fw/images/home/u23.png"/>
        <!-- Unnamed () -->
        <div id="u24" class="text">
          <p><span>资源</span></p><p><span>目录</span></p><p><span>信息</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u25" class="ax_shape">
        <img id="u25_img" class="img " src="${ctx }/skins/fw/images/home/u25.png"/>
        <!-- Unnamed () -->
        <div id="u26" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u27" class="ax_paragraph">
        <img id="u27_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u28" class="text">
          <p><span>松江区社会治理办公室</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u29" class="ax_paragraph">
        <img id="u29_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u30" class="text">
          <p><span>高级</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u31" class="ax_paragraph">
        <img id="u31_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u32" class="text">
          <p><span>201602002598</span></p>
        </div>
      </div>

     
      <!-- Unnamed (Shape) -->
      <div id="u35" class="ax_shape">
        <img id="u35_img" class="img " src="${ctx }/skins/fw/images/home/u35.png"/>
        <!-- Unnamed () -->
        <div id="u36" class="text">
          <p><span>综合</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u37" class="ax_paragraph">
        <img id="u37_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u38" class="text">
          <p><span style="color:#990000;">【已开通】</span><span style="color:#000000;">通过人员的身份证查询人员基础信息接口服务</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u39" class="ax_paragraph">
        <img id="u39_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u40" class="text">
          <p><span>数据由松江区科学技术委员会整体提供</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u41" class="ax_shape">
        <img id="u41_img" class="img " src="${ctx }/skins/fw/images/home/u41.png"/>
        <!-- Unnamed () -->
        <div id="u42" class="text">
          <p><span><a href='#' onclick="viewService('person')" style="color: white;text-decoration:none;">查看服务</a></span></p>
        </div>
      </div>
      
       <div id="u163" class="ax_shape">
        <img id="u163_img" class="img " src="${ctx }/skins/fw/images/home/u41.png"/>
        <!-- Unnamed () -->
        <div id="u164" class="text">
          <p><span><p><span><a href='#' onclick="openService('ju')" style="color: white;text-decoration:none;">开通服务</a></span></p></span></p>
        </div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u43" class="ax_image">
        <img id="u43_img" class="img " src="${ctx }/skins/fw/images/home/u43.png"/>
        <!-- Unnamed () -->
        <div id="u44" class="text"></div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u45" class="ax_image">
        <img id="u45_img" class="img " src="${ctx }/skins/fw/images/home/u45.png"/>
        <!-- Unnamed () -->
        <div id="u46" class="text"></div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u47" class="ax_image">
        <img id="u47_img" class="img " src="${ctx }/skins/fw/images/home/u47.png"/>
        <!-- Unnamed () -->
        <div id="u48" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u49" class="ax_paragraph">
        <img id="u49_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u50" class="text">
          <p><span id="cishu2getPersonInfo">0</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u51" class="ax_paragraph">
        <img id="u51_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u52" class="text">
          <p><span>1</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u53" class="ax_paragraph">
        <img id="u53_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u54" class="text">
          <p><span id="cishu1getPersonInfo">0</span></p>
        </div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u55" class="ax_image">
        <img id="u55_img" class="img " src="${ctx }/skins/fw/images/home/u47.png"/>
        <!-- Unnamed () -->
        <div id="u56" class="text"></div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u57" class="ax_image">
        <img id="u57_img" class="img " src="${ctx }/skins/fw/images/home/u45.png"/>
        <!-- Unnamed () -->
        <div id="u58" class="text"></div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u59" class="ax_image">
        <img id="u59_img" class="img " src="${ctx }/skins/fw/images/home/u43.png"/>
        <!-- Unnamed () -->
        <div id="u60" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u61" class="ax_paragraph">
        <img id="u61_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u62" class="text">
          <p><span style="color:#990000;">【已开通】</span><span style="color:#1E1E1E;">通过居村委代码查询居村所属人员接口服务</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u63" class="ax_paragraph">
        <img id="u63_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u64" class="text">
          <p><span>数据由松江区科学技术委员会整体提供</span></p>
        </div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u65" class="ax_image">
        <img id="u65_img" class="img " src="${ctx }/skins/fw/images/home/u43.png"/>
        <!-- Unnamed () -->
        <div id="u66" class="text"></div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u67" class="ax_image">
        <img id="u67_img" class="img " src="${ctx }/skins/fw/images/home/u47.png"/>
        <!-- Unnamed () -->
        <div id="u68" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u69" class="ax_paragraph">
        <img id="u69_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u70" class="text">
          <p><span id="cishu1queryPersonListByJuWeiDM">10782</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u71" class="ax_paragraph">
        <img id="u71_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u72" class="text">
          <p><span >1</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u73" class="ax_paragraph">
        <img id="u73_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u74" class="text">
          <p><span id="cishu2queryPersonListByJuWeiDM">1</span></p>
        </div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u75" class="ax_image">
        <img id="u75_img" class="img " src="${ctx }/skins/fw/images/home/u45.png"/>
        <!-- Unnamed () -->
        <div id="u76" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u77" class="ax_shape">
        <img id="u77_img" class="img " src="${ctx }/skins/fw/images/home/u77.png"/>
        <!-- Unnamed () -->
        <div id="u78" class="text">
          <p><span>规范下载</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u79" class="ax_paragraph">
        <img id="u79_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u80" class="text">
          <p><span style="color:#990000;">【公开】</span><span style="color:#000000;">数据交换技术标准与接口规范</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u81" class="ax_paragraph">
        <img id="u81_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u82" class="text">
          <p><span>数据由松江区科学技术委员会整体提供</span></p>
        </div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u83" class="ax_image">
        <img id="u83_img" class="img " src="${ctx }/skins/fw/images/home/u43.png"/>
        <!-- Unnamed () -->
        <div id="u84" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u85" class="ax_paragraph">
        <img id="u85_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u86" class="text">
          <p><span>358</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u87" class="ax_paragraph">
        <img id="u87_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u88" class="text">
          <p><span>1200</span></p>
        </div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u89" class="ax_image">
        <img id="u89_img" class="img " src="${ctx }/skins/fw/images/home/u45.png"/>
        <!-- Unnamed () -->
        <div id="u90" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u91" class="ax_paragraph">
        <img id="u91_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u92" class="text">
          <p style="font-size:16px;"><span style="font-family:'微软雅黑 Regular', '微软雅黑';font-weight:400;font-size:13px;">开通服务：</span><span style="font-family:'微软雅黑 Bold', '微软雅黑';font-weight:700;font-size:16px;">2</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u93" class="ax_paragraph">
        <img id="u93_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u94" class="text">
          <p style="font-size:16px;"><span style="font-family:'微软雅黑 Regular', '微软雅黑';font-weight:400;font-size:13px;">调用次数：</span><span style="font-family:'微软雅黑 Bold', '微软雅黑';font-weight:700;font-size:16px;" id="cishu3">0</span></p>
        </div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u95" class="ax_image">
        <img id="u95_img" class="img " src="${ctx }/skins/fw/images/home/u95.png"/>
        <!-- Unnamed () -->
        <div id="u96" class="text"></div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u97" class="ax_image">
        <img id="u97_img" class="img " src="${ctx }/skins/fw/images/home/u97.png"/>
        <!-- Unnamed () -->
        <div id="u98" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u99" class="ax_paragraph">
        <img id="u99_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u100" class="text">
          <p style="font-size:16px;"><span style="font-family:'微软雅黑 Regular', '微软雅黑';font-weight:400;font-size:13px;">月交换量：</span><span style="font-family:'微软雅黑 Bold', '微软雅黑';font-weight:700;font-size:16px;" id="shuliang1">0</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u101" class="ax_paragraph">
        <img id="u101_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u102" class="text">
          <p style="font-size:16px;"><span style="font-family:'微软雅黑 Regular', '微软雅黑';font-weight:400;font-size:13px;">交换总量：</span><span style="font-family:'微软雅黑 Bold', '微软雅黑';font-weight:700;font-size:16px;" id="shuliang2">0</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u103" class="ax_shape">
        <img id="u103_img" class="img " src="${ctx }/skins/fw/images/home/u103.png"/>
        <!-- Unnamed () -->
        <div id="u104" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u105" class="ax_shape">
        <img id="u105_img" class="img " src="${ctx }/skins/fw/images/home/u8.png"/>
        <!-- Unnamed () -->
        <div id="u106" class="text">
          <p><span>关于服务平台</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u107" class="ax_shape">
        <img id="u107_img" class="img " src="${ctx }/skins/fw/images/home/u15.png"/>
        <!-- Unnamed () -->
        <div id="u108" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u109" class="ax_shape">
        <img id="u109_img" class="img " src="${ctx }/skins/fw/images/home/u109.png"/>
        <!-- Unnamed () -->
        <div id="u110" class="text">
          <p><span style="font-family:'微软雅黑 Bold', '微软雅黑';font-weight:700;">服务电话</span><span style="font-family:'微软雅黑 Regular', '微软雅黑';font-weight:400;">24178888</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u111" class="ax_shape">
        <img id="u111_img" class="img " src="${ctx }/skins/fw/images/home/u109.png"/>
        <!-- Unnamed () -->
        <div id="u112" class="text">
          <p><span style="font-family:'微软雅黑 Bold', '微软雅黑';font-weight:700;">服务QQ群</span><span style="font-family:'微软雅黑 Regular', '微软雅黑';font-weight:400;">32016678</span></p>
        </div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u113" class="ax_image">
        <img id="u113_img" class="img " src="${ctx }/skins/fw/images/home/u113.png"/>
        <!-- Unnamed () -->
        <div id="u114" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u115" class="ax_shape">
        <img id="u115_img" class="img " src="${ctx }/skins/fw/images/home/u115.png"/>
        <!-- Unnamed () -->
        <div id="u116" class="text">
          <p><span>已开通服务</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u117" class="ax_shape">
        <img id="u117_img" class="img " src="${ctx }/skins/fw/images/home/u117.png"/>
        <!-- Unnamed () -->
        <div id="u118" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u119" class="ax_paragraph">
        <img id="u119_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u120" class="text">
          <p><span style="color:#990000;">【</span><span style="color:#990000;">数据接口服务</span><span style="color:#990000;">】</span><span style="color:#0066CC;">通过人员的身份证查询人员基础信息接口服务</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u121" class="ax_shape">
        <img id="u121_img" class="img " src="${ctx }/skins/fw/images/home/u117.png"/>
        <!-- Unnamed () -->
        <div id="u122" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u123" class="ax_paragraph">
        <img id="u123_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u124" class="text">
        <p><span style="color:#990000;">【</span><span style="color:#990000;">数据接口服务</span><span style="color:#990000;">】</span><span style="color:#0066CC;">通过居村委代码查询居村所属人员接口服务</span></p>
         </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u125" class="ax_shape">
        <img id="u125_img" class="img " src="${ctx }/skins/fw/images/home/u125.png"/>
        <!-- Unnamed () -->
        <div id="u126" class="text">
          <p><span>数据接口服务</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u127" class="ax_shape">
        <img id="u127_img" class="img " src="${ctx }/skins/fw/images/home/u127.png"/>
        <!-- Unnamed () -->
        <div id="u128" class="text"></div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u129" class="ax_image">
        <img id="u129_img" class="img " src="${ctx }/skins/fw/images/home/u129.png"/>
        <!-- Unnamed () -->
        <div id="u130" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u131" class="ax_shape">
        <img id="u131_img" class="img " src="${ctx }/skins/fw/images/home/u125.png"/>
        <!-- Unnamed () -->
        <div id="u132" class="text">
          <p><span>数据接口服务</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u133" class="ax_shape">
        <img id="u133_img" class="img " src="${ctx }/skins/fw/images/home/u127.png"/>
        <!-- Unnamed () -->
        <div id="u134" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u135" class="ax_shape">
        <img id="u135_img" class="img " src="${ctx }/skins/fw/images/home/u135.png"/>
        <!-- Unnamed () -->
        <div id="u136" class="text">
          <p><span>标准规范服务</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u137" class="ax_shape">
        <img id="u137_img" class="img " src="${ctx }/skins/fw/images/home/u137.png"/>
        <!-- Unnamed () -->
        <div id="u138" class="text"></div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u139" class="ax_image">
        <img id="u139_img" class="img " src="${ctx }/skins/fw/images/home/u139.png"/>
        <!-- Unnamed () -->
        <div id="u140" class="text"></div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u141" class="ax_image">
        <img id="u141_img" class="img " src="${ctx }/skins/fw/images/home/u129.png"/>
        <!-- Unnamed () -->
        <div id="u142" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u143" class="ax_shape">
        <img id="u143_img" class="img " src="${ctx }/skins/fw/images/home/u8.png"/>
        <!-- Unnamed () -->
        <div id="u144" class="text">
          <p><span>平台标准服务</span></p>
        </div>
      </div>
 <!-- Unnamed (Shape) -->
      <div id="u33" class="ax_shape">
        <img id="u33_img" class="img " src="${ctx }/skins/fw/images/home/u33.png"/>
        <!-- Unnamed () -->
        <div id="u34" class="text">
          <p><span><b><a href='#' onclick="res('rkk')" style="color: white;text-decoration:none;">人口信息</a></b></span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u145" class="ax_shape">
        <img id="u145_img" class="img " src="${ctx }/skins/fw/images/home/u33.png"/>
        <!-- Unnamed () -->
        <div id="u146" class="text">
          <p><span><b><a href='#' onclick="res('frk')" style="color: white;text-decoration:none;">法人信息</a></b></span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u147" class="ax_shape">
        <img id="u147_img" class="img " src="${ctx }/skins/fw/images/home/u33.png"/>
        <!-- Unnamed () -->
        <div id="u148" class="text">
          <p><span><b><a href='#' onclick="res('fwk')" style="color: white;text-decoration:none;">房屋信息</a></b></span></p>
        </div>
      </div>
	    <!-- Unnamed (Shape) -->
      <div id="u161" class="ax_shape">
        <img id="u161_img" class="img " src="${ctx }/skins/fw/images/home/u33.png"/>
        <!-- Unnamed () -->
        <div id="u162" class="text">
          <p><span><b><a href='#' onclick="res('gfk')" style="color: white;text-decoration:none;">标准规范</a></b></span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u149" class="ax_paragraph">
        <img id="u149_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u150" class="text">
          <p><span>松江区政务数据中心</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u151" class="ax_paragraph">
        <img id="u151_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u152" class="text">
          <p><span style="color:#FFFFFF;">信息综合利用服务平台</span></p>
        </div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u153" class="ax_paragraph">
        <img id="u153_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u154" class="text">
          <p><span>欢迎你，孙亮</span></p>
        </div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u155" class="ax_image">
        <img id="u155_img" class="img " src="${ctx }/skins/fw/images/home/u155.png"/>
        <!-- Unnamed () -->
        <div id="u156" class="text"></div>
      </div>

      <!-- Unnamed (Shape) -->
      <div id="u157" class="ax_paragraph">
        <img id="u157_img" class="img " src="${ctx }/skins/fw/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u158" class="text">
          <p><a href="${ctx }/logout" style="color: white;text-decoration:none;"><span>退出</span></a></p>
        </div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u159" class="ax_image">
        <img id="u159_img" class="img " src="${ctx }/skins/fw/images/home/u159.png"/>
        <!-- Unnamed () -->
        <div id="u160" class="text"></div>
      </div>

    

      <!-- Unnamed (Shape) -->
     
    </div>
  </body>
</html>
