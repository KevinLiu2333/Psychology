<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- Content Header (Page header) -->
 <section class="content-header">
     <h1 id="firstTitle">APP管理
         <small id="secondTitle">启动页管理</small>
     </h1>
     <ol class="breadcrumb">
         <li><a href="#"><i class="fa fa-dashboard"></i>APP管理</a></li>
         <li class="active">启动页管理</li>
     </ol>
 </section>
 <!-- Main content -->
<section class="content" id="page-content">
<div class="page-header">
        <shiro:hasPermission name="spec:add"><button onclick="addModel('${pageContext.request.contextPath }/loadingImg/editUI','添加启动页','420px')" class="btn btn-primary">添加</button></shiro:hasPermission>
  </div>
<div class="">
    <section>
        <div class="box">
            <div class="box-body">
                <div id="dtGridContainer" class="dt-grid-container"></div>
                <div id="dtGridToolBarContainer" class="dt-grid-toolbar-container"></div>
            </div>
            <!-- /.box-body -->
        </div>
    </section>
</div>
</section>
 <!-- /.content -->
<input type="hidden" id="menuId" value="78">
<script>
    var dtGridColumns = [{
        id : 'pic',
        title : '图片',
        type : 'string',
        headerStyle : 'background:rgb(236,240,245);text-align:center',
        columnClass : 'text-center',
        resolution : function(value, record, column, grid, dataNo, columnNo) {
            var img = "";
            img = '<a target="_blank" href="'+record.pic+'" data-lightbox="activity_image"><img width="30px" height="30px" src="'+record.pic+'" ></a>';
            return img;
        }
    }, {
        id : 'createDate',
        title : '时间',
        type : 'string',
        headerStyle : 'width:20%;text-align:center',
        columnClass : 'text-center',
        headerStyle : 'background:rgb(236,240,245);text-align:center'
    }];

    var dtGridOption = {
   	    lang : 'zh-cn',
   	    ajaxLoad : true,
   	    loadURL : sys.rootPath + '/loadingImg/list',
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
    /**
     * 自定义查询
     * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
     */
    function customSearch() {
    }
</script>