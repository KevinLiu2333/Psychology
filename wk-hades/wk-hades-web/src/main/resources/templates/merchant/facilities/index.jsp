<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="content-header">
  <h1 id="firstTitle">
     门店管理<small id="secondTitle">配套设施</small>
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
    <li><a href="#">首页</a></li>
    <li class="active">门店管理</li>
  </ol>
</section>
<section class="content" id="page-content">
<div class="">
  <div class="page-header">
        <shiro:hasPermission name="facilities:add"><button onclick="addModel('${pageContext.request.contextPath }/facilities/addUI','添加精选门店','620px')" class="btn btn-primary">添加</button></shiro:hasPermission>
    	<shiro:hasPermission name="facilities:edit"><button class="btn btn-success" onclick="editModel2('${pageContext.request.contextPath}/facilities/editUI','修改商铺','500px','790px',true)">编辑</button></shiro:hasPermission>
        <shiro:hasPermission name="facilities:deleteBatch "><button onclick="delModel('${pageContext.request.contextPath }/facilities/deleteBatch')" class="btn btn-danger">删除</button></shiro:hasPermission>
  </div>

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
<input type="hidden" id="menuId" value="97">

<script>
var dtGridColumns = [{
    id : 'name',
    title : '设施名称',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'xs',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'imageUrl',
    title : '门店名称',
    type : 'img',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    resolution:function(value,record,column,grid,dataNo,columnNo){
        return "<img src="+value+" width='50' height='50'>";
    }
}, {
    id : 'createDate',
    title : '创建时间',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
}];

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    loadURL : sys.rootPath + '/facilities/list',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : '',
    pageSize : sys.pageNum,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.DtGrid.init(dtGridOption);
$(function() {
    grid.load();
    $("#btnSearch").click(customSearch);

    //注册回车键事件
    document.onkeypress = function(e){
        var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            customSearch();
        }
    };

});

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() {
    grid.parameters = new Object();
    grid.parameters['name'] = $("#name").val();
    grid.parameters['cityId'] = $("#cityId").val();
    grid.refresh(true);
}

function editMerchant(topicId) {
 var index = layer.open({
     type: 2,
     area: ['750px', '770px'],
     fix: false, //不固定
     maxmin: true,
     content: '../topicMerchant/queryMerchantByTopic?topicId=' + topicId,
     title:'门店列表'
 });
}
</script>