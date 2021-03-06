<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1 id="firstTitle">
    	banner管理
    	<small id="secondTitle">banner列表</small>
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
    <li><a href="#">首页</a></li>
    <li class="active">banner管理</li>
  </ol>
</section>
  <section class="content" id="page-content">
  <div class="">
  <div class="page-header">
    <shiro:hasPermission name="banner:add"><button class="btn btn-primary" onclick="addModel('${pageContext.request.contextPath}/banner/addUI','添加banner','500px','790px',true)">添加</button></shiro:hasPermission>
    <shiro:hasPermission name="banner:edit"><button class="btn btn-success" onclick="editModel2('${pageContext.request.contextPath}/banner/editUI','修改banner','500px','790px',true)">编辑</button></shiro:hasPermission>
    <shiro:hasPermission name="banner:deleteBatch"><button class="btn btn-danger" onclick="delModel('${pageContext.request.contextPath}/banner/deleteBatch')">删除</button></shiro:hasPermission>
  </div>
  <section>
        <div class="box">
            <!-- /.box-header -->
            <div class="box-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-1 col-sm-offset-0"  for="searchKey">用户名</label>
                        <div class="col-sm-1">
                            <input class="form-control" type="text" name="userName" id="userName" placeholder="用户名">
                        </div>
                        <div class="col-sm-1">
                            <button type="button" class="btn btn-primary" id="btnSearch">查询</button>
                        </div>
                    </div>
                </form>
            </div>
            <!-- /.box-body -->
        </div>
    </section>
  
	<section>
	  <div class="box">
	    <div class="box-body">
	      <div id="dtGridContainer" class="dt-grid-container"></div>
          <div id="dtGridToolBarContainer" class="dt-grid-toolbar-container"></div>
	    </div>
	  </div>
	 </section>
	</div>
</section>
<input type="hidden" id="menuId" value="90">
<script type="text/javascript">
var dtGridColumns = [{
    id : 'picUrl',
    title : '图片',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        return '<a href="'+record.picUrl+'" data-lightbox="activity_image"><img width="50px" height="50px" src="'+record.picUrl+'" ></a>';
    }
}, {
    id : 'bannerType',
    title : 'banner类型',
    type : 'string',
    headerStyle : 'background:rgb(236,240,245);overflow: hidden;white-space: nowrap;text-overflow: ellipsis;',
    columnClass : 'text-center',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	var bannerType = "";
        if (value=="1") {
        	bannerType="第三方URL";
        } else if (value=="2") {
        	bannerType="本地编辑";
        } else if (value=="3") {
        	bannerType="只是图片无链接";
        }
        
        return bannerType;
    }
}, {
    id : 'linkUrl',
    title : '链接地址',
    type : 'string',
    headerStyle : 'background:rgb(236,240,245); width:60%;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;',
    columnClass : 'text-left'
}, {
    id : 'createDate',
    title : '创建时间',
    type : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center'
}];

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    loadURL : sys.rootPath + '/banner/list',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : '',
    pageSize : sys.pageNum,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.DtGrid.init(dtGridOption);
grid.load();
$("#btnSearch").click(customSearch);
//注册回车键事件
document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
    if(ev.keyCode==13) {
        customSearch();
    }
};

function customSearch() {
    grid.parameters = new Object();
    grid.parameters['userName'] = $("#userName").val();
    grid.refresh(true);
}

function setFeactured(id, status) {
	ajaxPost('/note/updateFeatured',{id:id,status:status});
}

function setRecommend(id, status) {
	ajaxPost('/note/updateRecommend',{id:id,status:status});
}
</script>

