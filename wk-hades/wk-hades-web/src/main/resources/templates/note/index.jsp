<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1 id="firstTitle">
    	随笔管理
    	<small id="secondTitle">随笔列表</small>
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
    <li><a href="#">首页</a></li>
    <li class="active">随笔管理</li>
  </ol>
</section>
  <section class="content" id="page-content">
  <div class="">
  <div class="page-header">
    <%-- <shiro:hasPermission name="note:add"><button class="btn btn-primary" onclick="addModel('${pageContext.request.contextPath}/note/addUI','添加随笔','500px','790px',true)">添加</button></shiro:hasPermission> --%>
    <shiro:hasPermission name="note:edit"><button class="btn btn-success" onclick="editModel2('${pageContext.request.contextPath}/note/editUI','修改随笔','500px','790px',true)">编辑</button></shiro:hasPermission>
    <shiro:hasPermission name="note:deleteBatch"><button class="btn btn-danger" onclick="delModel('${pageContext.request.contextPath}/note/deleteBatch')">删除</button></shiro:hasPermission>
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
<input type="hidden" id="menuId" value="79">
<script type="text/javascript">
var dtGridColumns = [{
    id : 'userName',
    title : '用户名',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'content',
    title : '随笔内容',
    type : 'string',
    headerStyle : 'background:rgb(236,240,245); width:60%;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;',
    columnClass : 'text-left'
}, {
    id : 'publishDate',
    title : '发表时间',
    type : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center'
}, {
    id : 'featured',
    title : '是否为精选',
    type : 'string',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
		if (value == "0" || value=="") {
			return "否";
		} else {
			return "是";
		}
    }
}, {
    id : 'recommend',
    title : '是否为推荐',
    type : 'string',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if (value == "0" || value=="") {
			return "否";
		} else {
			return "是";
		}
    }
} , {
    id : 'operation',
    title : '操作',
    type : 'string',
    columnClass : 'text-left',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	var featuredBtn = "";
    	if (record.featured == "0" || record.featured == "" || record.featured == null) {
    		featuredBtn = '<button class="btn btn-sm btn-default" onclick="setFeactured('+record.id+', 1)">设为精选</button>&nbsp;';
    	} else if (record.featured == "1") {
    		featuredBtn = '<button class="btn btn-sm btn-default" onclick="setFeactured('+record.id+', 0)">取消精选</button>&nbsp;';
    	}
    	
    	var recommendBtn = "";
    	if (record.recommend == "0" || record.recommend=="" || record.recommend == null) {
    		recommendBtn = '<button class="btn btn-sm btn-default" onclick="setRecommend('+record.id+', 1)">设为推荐</button>&nbsp;';
    	} else if (record.recommend == "1") {
    		recommendBtn = '<button class="btn btn-sm btn-default" onclick="setRecommend('+record.id+', 0)">取消推荐</button>&nbsp;';
    	}

        return featuredBtn + recommendBtn;
    }
}];

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    loadURL : sys.rootPath + '/note/list',
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

