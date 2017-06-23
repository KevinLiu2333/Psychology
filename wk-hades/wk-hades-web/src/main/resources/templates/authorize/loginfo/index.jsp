<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/loginfo/list.js"></script>
<section class="content-header">
     <h1 id="firstTitle">日志管理
         <small id="secondTitle">用户操作信息列表</small>
     </h1>
     <ol class="breadcrumb">
         <li><a href="#"><i class="fa fa-dashboard"></i>首页</a></li>
         <li class="active">日志管理</li>
     </ol>
 </section>
 <!-- Main content -->
<section class="content" id="page-content">
<!-- 编辑器源码文件 -->
<div class="">
    <section>
        <div class="box">
            <!-- /.box-header -->
            <div class="box-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-1 col-sm-offset-0"  for="searchKey">账户名称</label>
                        <div class="col-sm-2">
                            <input class="form-control" type="text" name="searchKey" id="searchKey" placeholder="账户名称...">
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
<input type="hidden" id="menuId" value="22">

<script>
var dtGridColumns = [{
    id : 'id',
    title : '编号',
    type : 'number',
    columnClass : 'text-center',
    headerStyle:'background:rgb(236,240,245);'
}, {
    id : 'userId',
    title : '用户ID',
    type : 'string',
    columnClass : 'text-center',
    headerStyle:'background:rgb(236,240,245);'
}, {
    id : 'accountName',
    title : '账户名称',
    type : 'string',
    columnClass : 'text-center',
    headerStyle:'background:rgb(236,240,245);'
}, {
    id : 'operation',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerStyle:'background:rgb(236,240,245);',
    hideType : 'xs'
}, {
    id : 'createTime',
    title : '操作时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype:'string', 
    oformat:'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerStyle:'background:rgb(236,240,245);',
    hideType : 'sm|xs'
}, {
    id : 'content',
    title : '操作内容',
    type : 'string',
    columnClass : 'text-center',
    headerStyle:'background:rgb(236,240,245);',
    hideType : 'sm|xs|md|lg'
}];
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    loadURL : sys.rootPath + '/logInfo/list',
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

//自定义查询
function customSearch() {
    grid.parameters = new Object();
    grid.parameters['accountName'] = $("#searchKey").val();
    grid.refresh(true);
}

</script>