<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1 id="firstTitle">
    城市管理<small id="secondTitle">城市列表</small>
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
    <li><a href="#">首页</a></li>
    <li class="active">城市管理</li>
  </ol>
</section>
<section class="content" id="page-content">
<div class="">
  <div class="page-header">
    <shiro:hasPermission name="city:add"><button class="btn btn-primary" onclick="addModel('${pageContext.request.contextPath}/city/addUI','添加城市','730px','790px',false)">添加</button></shiro:hasPermission>
    <shiro:hasPermission name="city:addCategory"><button onclick="editModel1('${pageContext.request.contextPath }/city/editMerchantCategoryUI','编辑门店分类','820px')" class="btn btn-success">编辑门店分类</button></shiro:hasPermission>
    <%-- <shiro:hasPermission name="city:edit"><button class="btn btn-success" onclick="editModel1('${pageContext.request.contextPath}/city/editUI','修改城市','500px')">编辑</button></shiro:hasPermission> --%>
    <shiro:hasPermission name="city:deleteBatch"><button class="btn btn-danger" onclick="delModel('${pageContext.request.contextPath}/city/deleteBatch')">删除</button></shiro:hasPermission>
  </div>

  <section>
        <div class="box">
            <!-- /.box-header -->
            <div class="box-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-1 col-sm-offset-0"  for="searchKey">城市名称</label>
                        <div class="col-sm-2">
                            <input class="form-control" type="text" name="searchKey" id="searchKey" placeholder="城市名称...">
                        </div>
                        <div class="col-sm-6">
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
<input type="hidden" id="menuId" value="60">

<script type="text/javascript">
var dtGridColumns = [{
    id : 'name',
    title : '城市名称',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'longitude',
    title : '经度',
    type : 'double',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center'
},{
    id : 'latitude',
    title : '纬度',
    type : 'double',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}];

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    loadURL : sys.rootPath + '/city/list',
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
    grid.parameters['name'] = $("#searchKey").val();
    grid.refresh(true);
}

var city={
    deleteRow:function(id){
        layer.confirm('确定要删除当前记录吗?', {icon: 3, title:'提示'}, function(index){
            ajaxPost('/merchant/deleteTopic',{ids:id});
            layer.close(index);
        });
    }
}
</script>
