<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<section class="content-header">
  <h1 id="firstTitle">
     商品管理<small id="secondTitle">商品分类</small>
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
    <li><a href="#">首页</a></li>
    <li class="active">商品分类</li>
  </ol>
</section>
<section class="content" id="page-content">
<div class="">
  <div class="page-header">
        <shiro:hasPermission name="category:add"><button onclick="addModel('${pageContext.request.contextPath }/category/addUI','添加分类','320px')" class="btn btn-primary">添加</button></shiro:hasPermission>
        <shiro:hasPermission name="category:edit"><button onclick="editModel1('${pageContext.request.contextPath }/category/editUI','编辑分类','320px')" class="btn btn-success">编辑</button></shiro:hasPermission>
        <shiro:hasPermission name="category:deleteBatch "><button onclick="delModel('${pageContext.request.contextPath }/category/deleteBatch')" class="btn btn-danger">删除</button></shiro:hasPermission>
  </div>

  <section>
        <div class="box">
            <!-- /.box-header -->
            <div class="box-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-1 col-sm-offset-0"  for="searchKey">类别名称</label>
                        <div class="col-sm-2">
                            <input class="form-control" type="text" name="searchKey" id="searchKey" placeholder="类别名称...">
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
<input type="hidden" id="menuId" value="31">

<script>
var dtGridColumns = [{
    id : 'id',
    title : '编号',
    type : 'number',
    columnClass : 'text-center',
    hideType : 'xs',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'categoryName',
    title : '类别名称',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'categoryDesc',
    title : '类别描述',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
},{
    id : 'modifyTime',
    title : '更新时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'sm|xs|md'
}];

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    loadURL : sys.rootPath + '/category/list',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : '',
    pageSize : sys.pageNum,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.DtGrid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
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
    grid.parameters['categoryName'] = $("#searchKey").val();
    grid.refresh(true);
}
</script>