<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>

    <script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/ace/js/ace.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/ace/js/ace-elements.min.js"></script>
    <!-- echarts -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/echarts/echarts.min.js"></script>

<div class="row">
    <h1 id="disable-responsive" class="page-header">数据采集</h1>
    <br/>
   	<div class="row">
       <div class="col-md-12 col-sm-8 col-xs-8 infobox-container" >
			<div class="infobox infobox-green">
				<div class="infobox-icon">
					<i class="ace-icon fa fa-street-view"></i>
				</div>

				<div class="infobox-data link-pannel">
					<span class="infobox-data-number">1,923,399</span>
					<div class="infobox-content">实有人口总数</div>
				</div>

				<div class="badge badge-success">
					+2697
					<i class="ace-icon fa fa-arrow-up"></i>
				</div>
			</div>
			<div class="infobox infobox-blue">
				<div class="infobox-icon">
					<i class="ace-icon fa fa-sitemap"></i>
				</div>

				<div class="infobox-data link-pannel">
					<span class="infobox-data-number">236,987</span>
					<div class="infobox-content">实有法人总数</div>
				</div>

				<div class="badge badge-info">
					-357
					<i class="ace-icon fa fa-arrow-down"></i>
				</div>
			</div>
			<div class="infobox infobox-pink ">
				<div class="infobox-icon">
					<i class="ace-icon fa fa-university"></i>
				</div>

				<div class="infobox-data link-pannel">
					<span class="infobox-data-number">2,236,987</span>
					<div class="infobox-content">实有房屋总数</div>
				</div>
				<div class="badge badge-success">
					+178
					<i class="ace-icon fa fa-arrow-up"></i>
				</div>
			</div>
			<div class="infobox infobox-blue ">
				<div class="infobox-icon">
					<i class="ace-icon fa fa-street-view"></i>
				</div>

				<div class="infobox-data link-pannel">
					<span class="infobox-data-number">1,204,212</span>
					<div class="infobox-content">本市户籍人口总数</div>
				</div>
				<div class="badge badge-success">
					+741
					<i class="ace-icon fa fa-arrow-up"></i>
				</div>
			</div>
			<div class="infobox infobox-red">
				<div class="infobox-icon">
					<i class="ace-icon fa fa-flask"></i>
				</div>

				<div class="infobox-data link-pannel">
					<span class="infobox-data-number" id="zyAllCount1">0</span>
					<div class="infobox-content">数据目录总量</div>
				</div>
			</div>
			<div class="infobox infobox-orange2">
				<div class="infobox-icon">
					<i class="ace-icon fa fa-flask"></i>
				</div>

				<div class="infobox-data link-pannel">
					<span class="infobox-data-number" id="fwCount1">0</span>
					<div class="infobox-content">共享服务总量</div>
				</div>

			</div>

			<div class="infobox infobox-blue2">
				<div class="infobox-icon">
					<i class="ace-icon fa fa-flask"></i>
				</div>

				<div class="infobox-data">
					<span class="infobox-data-number">20</span>

					<div class="infobox-content">
						接入单位总量
					</div>
				</div>
			</div>
			<div class="infobox infobox-red">
				<div class="infobox-icon">
					<i class="ace-icon fa fa-flask"></i>
				</div>

				<div class="infobox-data">
					<span class="infobox-data-number">41</span>

					<div class="infobox-content">
						接收单位总量
					</div>
				</div>
			</div>
        </div>
    </div>
</div>
<div class="row">
    <h1 id="disable-responsive" class="page-header">数据交换</h1>
    <br/>
    <div class="row">
        <div class="col-md-8">
		    <div class="panel">
		        <div id="jhjk" style="height:270px;"></div>
		    </div>
        </div>
    <div class="col-md-4">
		<div class="profile-user-info profile-user-info-striped">
	    <div class="profile-info-row">
	        <div class="profile-info-name" style="height:40px"> 交换接入单位 </div>
	
	        <div class="profile-info-value">
	            <span class="editable" id="username">6</span>
	        </div>
	    </div>
	    <div class="profile-info-row">
	        <div class="profile-info-name" style="height:40px"> 数据调用单位 </div>
	
	        <div class="profile-info-value">
	            <span class="editable" id="city">20</span>
	        </div>
	    </div>
	    <div class="profile-info-row">
	        <div class="profile-info-name" style="height:40px"> 数据交换总量 </div>
	        <div class="profile-info-value">
	            <span class="editable" id="age">2,690,207</span>
	        </div>
	    </div>
	    <div class="profile-info-row">
	        <div class="profile-info-name" style="height:40px"> 数据调用总量 </div>
	
	        <div class="profile-info-value">
	            <span class="editable" id="signup">380,233</span>
	        </div>
	    </div>
	    <div class="profile-info-row">
	        <div class="profile-info-name" style="height:40px"> 本月交换量 </div>
	
	        <div class="profile-info-value">
	            <span class="editable" id="login">20,236</span>
	        </div>
	    </div>
	    <div class="profile-info-row">
	        <div class="profile-info-name" style="height:40px"> 本月调用量 </div>
	
	        <div class="profile-info-value">
	            <span class="editable" id="about">4,557</span>
	        </div>
	    </div>
      	<div class="profile-info-row">
	        <div class="profile-info-name" style="height:40px"> 最后更新日期 </div>
	        <div class="profile-info-value">
	            <span class="editable" id="about">2016-7-22</span>
	        </div>
    	</div>
		</div>
    </div>
    </div>
</div>
<div class="row">
    <h1 id="disable-responsive" class="page-header">资源目录</h1>
    <br/>
	<div class="col-sm-12 col-md-6 features">
	    <div class="feature">
	        <div class="feature-icon">
	            <span class="glyphicon glyphicon-star-empty"></span>
	        </div>
	        <h5><b>人员基本信息目录</b></h5>
	        <p>提供人员的证件种类、证件号码、姓名、性别、民族、出生日期、户籍地详址、居住地详址等信息</p>
	        </div>
	        <div class="feature">
	            <div class="feature-icon">
	                <span class="glyphicon glyphicon-star-empty"></span>
	            </div>
	            <h5><b>法人基本信息目录</b></h5>
	            <p>提供法人姓名、性别、证件种类、证件号码、公司详细信息、办公地址等信息</p>
	        </div>
	 </div>
	 <div class="col-sm-12 col-md-6 features">
	     <div class="feature">
	         <div class="feature-icon">
	             <span class="glyphicon glyphicon-star-empty"></span>
	         </div>
	         <h5><b>法人处罚信息目录</b></h5>
	         <p>提供法人姓名、性别、证件类型、证件号码、公司名称、违反条例、处罚方式等信息</p>
	     </div>
	     <div class="feature">
	         <div class="feature-icon">
	             <span class="glyphicon glyphicon-star-empty"></span>
	         </div>
	         <h5><b>房屋基本信息目录</b></h5>
	         <p>提供房屋使用面积、居住面积、详细地址、居住人员、使用年限等信息</p>
	     </div>
	  </div>
</div>

<script type="text/javascript">
function jhjk(){
	option = {
		    title : {
		        text: '半年内交换信息分析',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        x : 'center',
		        y : 'bottom',
		        data:['民政局','税务局','计生委','公安局','教育局','房管局']
		    },
		    calculable : true,
		    series : [
		        {
		            name:'各单位数据接入量',
		            type:'pie',
		            radius : [10, 100],
		            center : ['25%', '50%'],
		            roseType : 'radius',
		            label: {
		                normal: {
		                    show: false
		                },
		                emphasis: {
		                    show: true
		                }
		            },
		            lableLine: {
		                normal: {
		                    show: false
		                },
		                emphasis: {
		                    show: true
		                }
		            },
		            data:[
		                {value:10, name:'民政局'},
		                {value:5, name:'税务局'},
		                {value:15, name:'计生委'},
		                {value:25, name:'公安局'},
		                {value:20, name:'教育局'},
		                {value:35, name:'房管局'}
		            ]
		        },
		        {
		            name:'各单位数据使用量',
		            type:'pie',
		            radius : [10, 100],
		            center : ['75%', '50%'],
		            roseType : 'area',
		            
		            data:[
		                {value:10, name:'民政局'},
		                {value:5, name:'税务局'},
		                {value:15, name:'计生委'},
		                {value:25, name:'公安局'},
		                {value:20, name:'教育局'},
		                {value:35, name:'房管局'}
		            ]
		        }
		    ]
		};


    var myChart = echarts.init(document.getElementById('jhjk'));
	myChart.setOption(option);
			
}

jhjk();
</script>
