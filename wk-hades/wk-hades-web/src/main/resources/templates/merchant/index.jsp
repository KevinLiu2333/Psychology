<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1 id="firstTitle">
    门店管理<small id="secondTitle">门店列表</small>
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
    <shiro:hasPermission name="merchant:add"><button class="btn btn-primary" onclick="addModel('${pageContext.request.contextPath}/merchant/addUI','添加商铺','500px','790px',true)">添加</button></shiro:hasPermission>
    <shiro:hasPermission name="merchant:edit"><button class="btn btn-success" onclick="editModel2('${pageContext.request.contextPath}/merchant/editUI','修改商铺','500px','790px',true)">编辑</button></shiro:hasPermission>
    <shiro:hasPermission name="merchant:deleteBatch"><button class="btn btn-danger" onclick="delModel('${pageContext.request.contextPath}/merchant/deleteBatch')">删除</button></shiro:hasPermission>
  </div>
	<section>
        <div class="box">
            <!-- /.box-header -->
            <div class="box-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-1 col-sm-offset-0"  for="searchKey">门店名称</label>
                        <div class="col-sm-2">
                            <input class="form-control" type="text" name="searchKey" id="searchKey" placeholder="门店名称...">
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
<input type="hidden" id="menuId" value="23">

<script>
var dtGridColumns = [{
    id : 'merchant_name',
    title : '门店名称',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'contact',
    title : '联系人',
    type : 'string',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center'
},{
    id : 'telephone',
    title : '联系电话',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
},{
    id : 'shop_address',
    title : '商铺地址',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
},{
    id : 'modify_time',
    title : '创建时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'sm|xs'
}, {
    id : 'status',
    title : '状态',
    otype : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if(value=="3"){
            return '<span class="label label-sm label-info arrowed arrowed-righ">热门</span>';
        }else{
            return '<span class="label label-sm label-warning arrowed arrowed-righ">正常</span>';
        }
    }
}, {
    id : 'operation',
    title : '操作',
    type : 'string',
    columnClass : 'text-left',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        var topHtml='<button class="btn btn-sm btn-default" onclick="merchant.setTop('+record.id+')">设置置顶</button>&nbsp;';
        if(record.isTop==1){
            topHtml='<button class="btn btn-sm btn-default" onclick="merchant.setTop('+record.id+')">取消置顶</button>&nbsp;';
        }
        var hotHtml='<button class="btn btn-sm btn-default" onclick="merchant.updateStatus('+record.id+', 1)">取消热门</button>&nbsp;';
        if(record.status!="3"){
            hotHtml='<button class="btn btn-sm btn-default" onclick="merchant.updateStatus('+record.id+', 3)">置为热门</button>&nbsp;';
        }

        return '<button class="btn btn-sm btn-default" onclick="editModel('+record.id+',\'/merchant/editUI\',\'编辑门店\',\'630px\',\'630px\',true)">编辑</button>&nbsp;' +
                '<button class="btn btn-sm btn-default" onclick="merchant.deleteRow('+record.id+')">删除</button>&nbsp;' +
                /* '<button class="btn btn-sm btn-default" onclick="editModel('+record.topic_id+',\'/Topic/toTopicCommentList\',\'查看评论\',\'630px\')">查看评论</button>&nbsp;' + topHtml+*/hotHtml/* +
                '<button class="btn btn-sm btn-default" onclick="editModel('+record.topic_id+',\'/Topic/recommendForm\',\'推荐门店\',\'630px\',\'790px\')">推荐</button>' */;
    }
}];

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    loadURL : sys.rootPath + '/merchant/list',
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
    grid.parameters['merchant_name'] = $("#searchKey").val();
    grid.parameters['city_id'] = $("#cityId").val();
    grid.refresh(true);
}

var merchant={
    updateStatus:function setTop(id, status){
    	ajaxPost('/merchant/updateStatus',{ids:id,status:status});
    },
    deleteRow:function(id){
        layer.confirm('确定要删除该门店吗?', {icon: 3, title:'提示'}, function(index){
            ajaxPost('/topic/deleteTopicConBatch',{ids:id});
            layer.close(index);
        });
    }
}
</script>