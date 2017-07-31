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
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/sjdc/skins/css/wddc_index.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/sjdc/skins/css/layout.css"/>
    
    
<script>
function changead(num,refre) {	
	if(num == 5)
		num = 1;
	var sc = document.getElementById("screens");
	sc.src="${ctx}/sjdc/skins/images/index/banner_0" + num+ ".jpg";
}
</script>
    
</head>
<body>
<div class="header">
	<div class="header_info" id="btnLogin">
		<img src="${ctx}/sjdc/skins/images/index/logo.png" alt="" />
		<a href="#"  id='loginbtn'>登录</a>
	</div> 
</div>

<div class="middle">
	 <div id="ad">
	   <div id="ad_screen">
		 <img id="screens" src="${ctx}/sjdc/skins/images/index/banner_01.jpg" width="1002" height="250">
		</div>
		<ul class="ad_page">
	     <li><a href="#" id="p1" onclick="changead(1);">1</a></li>
		 <li><a href="#" id="p2" onclick="changead(2);">2</a></li>
		 <li><a href="#" id="p3" onclick="changead(3);">3</a></li>
	   </ul>
	 </div>
  </div>
  
<div class="content">
      <!--第一行内容-->
	<div class="subj">
		   
           <div class="subj_header"><span><img src="${ctx}/sjdc/skins/images/index/icon-shujml.png" alt="" align="absmiddle" />资源目录</span></div>
           
           <ul>
	               <li>
	                   <a href="http://datagz.gov.cn:80/data/catalog/index.htm?subjectId=sub-1" target="_blank">
		                   <div class="sy_themel1"></div> 经济发展
	                   </a>
	               </li>
	           
	               <li>
	                   <a href="http://datagz.gov.cn:80/data/catalog/index.htm?subjectId=sub-2" target="_blank">
		                   <div class="sy_themel2"></div> 城市建设
	                   </a>
	               </li>
	           
	               <li>
	                   <a href="http://datagz.gov.cn:80/data/catalog/index.htm?subjectId=sub-3" target="_blank">
		                   <div class="sy_themel3"></div> 道路交通
	                   </a>
	               </li>
	           
	               <li>
	                   <a href="http://datagz.gov.cn:80/data/catalog/index.htm?subjectId=sub-4" target="_blank">
		                   <div class="sy_themel4"></div>  教育科技
	                   </a>
	               </li>
	           
	               <li>
	                   <a href="http://datagz.gov.cn:80/data/catalog/index.htm?subjectId=sub-5" target="_blank">
		                   <div class="sy_themel5"></div> 民生服务
	                   </a>
	               </li>
	           
	               <li>
	                   <a href="http://datagz.gov.cn:80/data/catalog/index.htm?subjectId=sub-6" target="_blank">
		                   <div class="sy_themel6"></div> 企业服务
	                   </a>
	               </li>
	           
	               <li>
	                   <a href="http://datagz.gov.cn:80/data/catalog/index.htm?subjectId=sub-7" target="_blank">
		                   <div class="sy_themel7"></div> 健康卫生
	                   </a>
	               </li>
	           
	               <li>
	                   <a href="http://datagz.gov.cn:80/data/catalog/index.htm?subjectId=sub-8" target="_blank">
		                   <div class="sy_themel8"></div>  资源环境
	                   </a>
	               </li>
	           
	               <li>
	                   <a href="http://datagz.gov.cn:80/data/catalog/index.htm?subjectId=sub-9" target="_blank">
		                   <div class="sy_themel9"></div> 文体娱乐
	                   </a>
	               </li>
	           
	               <li>
	                   <a href="http://datagz.gov.cn:80/data/catalog/index.htm?subjectId=sub-10" target="_blank">
		                   <div class="sy_themel10"></div> 机构团体
	                   </a>
	               </li>
	           
	               <li>
	                   <a href="http://datagz.gov.cn:80/data/catalog/index.htm?subjectId=sub-11" target="_blank">
		                   <div class="sy_themel11"></div> 公共安全
	                   </a>
	               </li>
	           
	               <li>
	                   <a href="http://datagz.gov.cn:80/data/catalog/index.htm?subjectId=sub-12" target="_blank">
		                   <div class="sy_themel12"></div> 农业农村
	                   </a>
	               </li>
	           
	               <li>
	                   <a href="http://datagz.gov.cn:80/data/catalog/index.htm?subjectId=sub-13" target="_blank">
		                   <div class="sy_themel13"></div> 财税金融
	                   </a>
	               </li>
	           
	               <li>
	                   <a href="http://datagz.gov.cn:80/data/catalog/index.htm?subjectId=sub-14" target="_blank">
		                   <div class="sy_themel14"></div> 劳动人事
	                   </a>
	               </li>
	           
           </ul>
       </div>
    <br/>
    <!--第二行内容-->
    <div class="second">
    	<div class="second_left">
        	<div class="title">
            	<span><img src="${ctx}/sjdc/skins/images/index/icon-shujml.png" alt="" align="absmiddle" />资源目录</span><a href="${ctx}/zymlgl/toContentIndex?isSy=1" target="_blank">更多>></a>
            </div>
              <img style="margin:0 8px;" src="${ctx}/sjdc/skins/images/index/title_xian.jpg" alt="" />
              <dl>
              	<dt class="click_info" style="margin-left:24px;">基础</dt>
                <dt>专题</dt>
                
                <dd>
                	<c:forEach items="${obj.jcResourceList}" var="resource">
                		<p><span class="textCon"><img src="${ctx}/sjdc/skins/images/index/dian.jpg" alt="" align="absmiddle"/><a href="${ctx}/zymlgl/toDetail?resourceId=${resource.resourceId}&isSy=1" target="_blank">${resource.resourceName}</a></span>
                		<span class="liulan">已有${resource.browseCount}次浏览</span>
                		<span class="day"><fmt:formatDate value="${resource.updateDate}" pattern="yyyy-MM-dd"/></span></p>
                	</c:forEach>
                </dd>
                <dd>
                	<c:forEach items="${obj.topicResourceList}" var="resource">
                		<p><span class="textCon"><img src="${ctx}/sjdc/skins/images/index/dian.jpg" alt="" align="absmiddle"/><a href="${ctx}/zymlgl/toDetail?resourceId=${resource.resourceId}&isSy=1" target="_blank">${resource.resourceName}</a></span>
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
            <span><img src="${ctx}/sjdc/skins/images/index/icon-shujufw.png" alt="" align="absmiddle" />数据服务</span><a href="${ctx}/home/moreApplyList" target="_blank">更多>></a>
            </div>
              <img style="margin:0 8px;" src="${ctx}/sjdc/skins/images/index/title_xian.jpg" alt="" />
              
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
        	<img src="${ctx}/sjdc/skins/images/index/tubiao_shuj.png" alt="" style="margin:65px 0 0 16px; float:left;" />
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
            	<span><img src="${ctx}/sjdc/skins/images/index/icon-zhengcefw.png" alt="" align="absmiddle" />政策服务</span>
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
