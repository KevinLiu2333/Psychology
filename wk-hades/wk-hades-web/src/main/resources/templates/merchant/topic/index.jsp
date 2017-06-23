<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="content-header">
  <h1 id="firstTitle">
     门店管理<small id="secondTitle">城市专题列表</small>
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
        <shiro:hasPermission name="category:add"><button onclick="addModel('${pageContext.request.contextPath }/cityTopic/addUI','添加专题','620px')" class="btn btn-primary">添加</button></shiro:hasPermission>
        <shiro:hasPermission name="category:edit"><button onclick="editModel1('${pageContext.request.contextPath }/cityTopic/editUI','编辑专题','620px')" class="btn btn-success">编辑</button></shiro:hasPermission>
        <shiro:hasPermission name="category:deleteBatch "><button onclick="delModel('${pageContext.request.contextPath }/cityTopic/deleteBatch')" class="btn btn-danger">删除</button></shiro:hasPermission>
  </div>

  <section>
        <div class="box">
            <!-- /.box-header -->
            <div class="box-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-1 col-sm-offset-0"  for="name">专题名称</label>
                        <div class="col-sm-2">
                            <input class="form-control" type="text" name="name" id="name" placeholder="专题名称...">
                        </div>
                        <label class="control-label col-sm-1 col-sm-offset-0"  for="cityId">城市名称</label>
                        <div class="col-sm-1" style="padding-top:8px;">
                           <select id="cityId" name="cityId">
		                     	<option value="">==请选择==</option>
			                     <c:forEach items="${cityList}" var="city">
									<option value="${city.id}"<c:if test="${cityTopic.id eq city.id }">selected=selected</c:if>>${city.name}</option>                   
			                     </c:forEach>
		                     </select>
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
<input type="hidden" id="menuId" value="74">

<script>
var dtGridColumns = [{
    id : 'name',
    title : '专题名称',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'xs',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'pic',
    title : '专题图片',
    type : 'img',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    resolution:function(value,record,column,grid,dataNo,columnNo){
        return "<img src="+value+" width='50' height='50'>";
    }
}, {
    id : 'cityName',
    title : '所属城市',
    type : 'img',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'createDate',
    title : '创建时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'sm|xs|md'
}, {
    id : 'operation',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        return '<button class="btn btn-sm btn-default" onclick="editMerchant('+record.id+',\'/merchant/editUI\',\'绑定门店\',\'630px\',\'630px\',true)">绑定门店</button>';
    }
}];

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    loadURL : sys.rootPath + '/cityTopic/list',
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