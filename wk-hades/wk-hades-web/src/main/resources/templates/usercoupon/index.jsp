<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<section class="content-header">
  <h1 id="firstTitle">
     优费券管理<small id="secondTitle">用户优费券管理</small>
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
    <li><a href="#">首页</a></li>
    <li class="active">用户优费券管理</li>
  </ol>
</section>
<section class="content" id="page-content">
<div class="">
  <div class="page-header">
    <shiro:hasPermission name="usercoupon:add"><button class="btn btn-primary" onclick="addModel('${pageContext.request.contextPath}/usercoupon/addUI','添加用户优费券','450px')">添加</button></shiro:hasPermission>
    <shiro:hasPermission name="usercoupon:edit"><button class="btn btn-success" onclick="editModel1('${pageContext.request.contextPath}/usercoupon/editUI','修改用户优费券','450px')">编辑</button></shiro:hasPermission>
    <shiro:hasPermission name="usercoupon:deleteBatch"><button class="btn btn-danger" onclick="delModel('${pageContext.request.contextPath}/usercoupon/deleteBatch')">删除</button></shiro:hasPermission>
  </div>

  <section>
        <div class="box">
            <!-- /.box-header -->
            <div class="box-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-1 col-sm-offset-0"  for="searchKey">优惠券名称</label>
                        <div class="col-sm-2">
                            <input class="form-control" type="text" name="searchKey" id="searchKey" placeholder="优惠券名称...">
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
<input type="hidden" id="menuId" value="35">

<script>
var dtGridColumns = [{
    id : 'id',
    title : '优惠券编号',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'xs'
}, {
    id : 'name',
    title : '优惠券名称',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'denomination',
    title : '面额',
    type : 'numer',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center'
},{
    id : 'status',
    title : '状态',
    type : 'string',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center'
},{
    id : 'marchant_id',
    title : '优惠券所属商户',
    type : 'number',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
},
{
    id : 'valid_begin',
    title : '有效开始时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'sm|xs'
},
{
    id : 'valid_end',
    title : '有效结束时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'sm|xs'
}
];

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    loadURL : sys.rootPath + '/usercoupon/list',
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
</script>