<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/cj/title_setting.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${sys_title }</title>
<link href="${ctx }/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<link href="${ctx }/wddc/skins/css/wddc.css" rel="stylesheet" type="text/css"></link>

<link rel="stylesheet" href="${ctx }/wddc/tiles/horizontal-timeline/css/reset.css"> <!-- CSS reset -->
<link rel="stylesheet" href="${ctx }/wddc/tiles/horizontal-timeline/css/style.css"> <!-- Resource style -->

<!-- Loading jquery -->
<script type="text/javascript" src="${ctx }/wddc/tiles/js/jquery-2.2.3.min.js"></script>

<script src="${ctx }/tiles/bootstrap/js/bootstrap.min.js"></script>


<script src="${ctx }/wddc/tiles/horizontal-timeline/js/modernizr.js"></script> <!-- Modernizr -->
</head>
<body>

<br/>
<br/>
<div class="container">

	
	
	<div class="cd-horizontal-timeline">
	
		<div class="timeline">
			<div class="events-wrapper">
				<div class="events">
					<ol>
						<li><a href="#0" data-date="16/01/2014" class="selected">16 Jan</a></li>
						<li><a href="#0" data-date="28/02/2014">28 Feb</a></li>
						<li><a href="#0" data-date="20/04/2014">20 Mar</a></li>
						<li><a href="#0" data-date="20/05/2014">20 May</a></li>
						<li><a href="#0" data-date="09/07/2014">09 Jul</a></li>
						<li><a href="#0" data-date="30/08/2014">30 Aug</a></li>
						<li><a href="#0" data-date="15/09/2014">15 Sep</a></li>
						<li><a href="#0" data-date="01/11/2014">01 Nov</a></li>
						<li><a href="#0" data-date="10/12/2014">10 Dec</a></li>
						<li><a href="#0" data-date="19/01/2015">29 Jan</a></li>
						<li><a href="#0" data-date="03/03/2015">3 Mar</a></li>
					</ol>
	
					<span class="filling-line" aria-hidden="true"></span>
				</div> <!-- .events -->
			</div> <!-- .events-wrapper -->
				
			<ul class="cd-timeline-navigation">
				<li><a href="#0" class="prev inactive">Prev</a></li>
				<li><a href="#0" class="next">Next</a></li>
			</ul> <!-- .cd-timeline-navigation -->
		</div> <!-- .timeline -->
	
		<div class="events-content">
			<ol>
				<li class="selected" data-date="16/01/2014">
					<h2>Horizontal Timeline</h2>
					<em>January 16th, 2014</em>
					<p>	
						Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum praesentium officia, fugit recusandae ipsa, quia velit nulla adipisci? Consequuntur aspernatur at, eaque hic repellendus sit dicta consequatur quae, ut harum ipsam molestias maxime non nisi reiciendis eligendi! Doloremque quia pariatur harum ea amet quibusdam quisquam, quae, temporibus dolores porro doloribus.
					</p>
				</li>
	
				<li data-date="28/02/2014">
					<h2>Event title here</h2>
					<em>February 28th, 2014</em>
					<p>	
						Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum praesentium officia, fugit recusandae ipsa, quia velit nulla adipisci? Consequuntur aspernatur at, eaque hic repellendus sit dicta consequatur quae, ut harum ipsam molestias maxime non nisi reiciendis eligendi! Doloremque quia pariatur harum ea amet quibusdam quisquam, quae, temporibus dolores porro doloribus.
					</p>
				</li>
	
				<li data-date="20/04/2014">
					<h2>Event title here</h2>
					<em>March 20th, 2014</em>
					<p>	
						Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum praesentium officia, fugit recusandae ipsa, quia velit nulla adipisci? Consequuntur aspernatur at, eaque hic repellendus sit dicta consequatur quae, ut harum ipsam molestias maxime non nisi reiciendis eligendi! Doloremque quia pariatur harum ea amet quibusdam quisquam, quae, temporibus dolores porro doloribus.
					</p>
				</li>
	
				<li data-date="20/05/2014">
					<h2>Event title here</h2>
					<em>May 20th, 2014</em>
					<p>	
						Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum praesentium officia, fugit recusandae ipsa, quia velit nulla adipisci? Consequuntur aspernatur at, eaque hic repellendus sit dicta consequatur quae, ut harum ipsam molestias maxime non nisi reiciendis eligendi! Doloremque quia pariatur harum ea amet quibusdam quisquam, quae, temporibus dolores porro doloribus.
					</p>
				</li>
	
				<li data-date="09/07/2014">
					<h2>Event title here</h2>
					<em>July 9th, 2014</em>
					<p>	
						Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum praesentium officia, fugit recusandae ipsa, quia velit nulla adipisci? Consequuntur aspernatur at, eaque hic repellendus sit dicta consequatur quae, ut harum ipsam molestias maxime non nisi reiciendis eligendi! Doloremque quia pariatur harum ea amet quibusdam quisquam, quae, temporibus dolores porro doloribus.
					</p>
				</li>
	
				<li data-date="30/08/2014">
					<h2>Event title here</h2>
					<em>August 30th, 2014</em>
					<p>	
						Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum praesentium officia, fugit recusandae ipsa, quia velit nulla adipisci? Consequuntur aspernatur at, eaque hic repellendus sit dicta consequatur quae, ut harum ipsam molestias maxime non nisi reiciendis eligendi! Doloremque quia pariatur harum ea amet quibusdam quisquam, quae, temporibus dolores porro doloribus.
					</p>
				</li>
	
				<li data-date="15/09/2014">
					<h2>Event title here</h2>
					<em>September 15th, 2014</em>
					<p>	
						Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum praesentium officia, fugit recusandae ipsa, quia velit nulla adipisci? Consequuntur aspernatur at, eaque hic repellendus sit dicta consequatur quae, ut harum ipsam molestias maxime non nisi reiciendis eligendi! Doloremque quia pariatur harum ea amet quibusdam quisquam, quae, temporibus dolores porro doloribus.
					</p>
				</li>
	
				<li data-date="01/11/2014">
					<h2>Event title here</h2>
					<em>November 1st, 2014</em>
					<p>	
						Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum praesentium officia, fugit recusandae ipsa, quia velit nulla adipisci? Consequuntur aspernatur at, eaque hic repellendus sit dicta consequatur quae, ut harum ipsam molestias maxime non nisi reiciendis eligendi! Doloremque quia pariatur harum ea amet quibusdam quisquam, quae, temporibus dolores porro doloribus.
					</p>
				</li>
	
				<li data-date="10/12/2014">
					<h2>Event title here</h2>
					<em>December 10th, 2014</em>
					<p>	
						Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum praesentium officia, fugit recusandae ipsa, quia velit nulla adipisci? Consequuntur aspernatur at, eaque hic repellendus sit dicta consequatur quae, ut harum ipsam molestias maxime non nisi reiciendis eligendi! Doloremque quia pariatur harum ea amet quibusdam quisquam, quae, temporibus dolores porro doloribus.
					</p>
				</li>
	
				<li data-date="19/01/2015">
					<h2>Event title here</h2>
					<em>January 19th, 2015</em>
					<p>	
						Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum praesentium officia, fugit recusandae ipsa, quia velit nulla adipisci? Consequuntur aspernatur at, eaque hic repellendus sit dicta consequatur quae, ut harum ipsam molestias maxime non nisi reiciendis eligendi! Doloremque quia pariatur harum ea amet quibusdam quisquam, quae, temporibus dolores porro doloribus.
					</p>
				</li>
	
				<li data-date="03/03/2015">
					<h2>Event title here</h2>
					<em>March 3rd, 2015</em>
					<p>	
						Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum praesentium officia, fugit recusandae ipsa, quia velit nulla adipisci? Consequuntur aspernatur at, eaque hic repellendus sit dicta consequatur quae, ut harum ipsam molestias maxime non nisi reiciendis eligendi! Doloremque quia pariatur harum ea amet quibusdam quisquam, quae, temporibus dolores porro doloribus.
					</p>
				</li>
			</ol>
		</div> <!-- .events-content -->
		
			
	</div>
</div>
<jsp:include page="/cj/foot.jsp"/>
<script src="${ctx }/wddc/tiles/horizontal-timeline/js/jquery.mobile.custom.min.js"></script>
<script src="${ctx }/wddc/tiles/horizontal-timeline/js/main.js"></script> <!-- Resource jQuery -->
</body>
</html>