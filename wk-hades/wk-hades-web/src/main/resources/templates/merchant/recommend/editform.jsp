<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="content">
  <div class="box">
    <div class="box-body">
    	<form class="form-horizontal">
            <div class="form-group">
               <label class="control-label col-sm-1 col-sm-offset-0"  for="cityId">城市名称</label>
               <div class="col-sm-2" style="padding-top:8px;">
                  <select id="cityId" name="cityId">
              		<option value="">==请选择==</option>
	               <c:forEach items="${cityList}" var="city">
						<option value="${city.id}">${city.name}</option>
	               </c:forEach>
              	</select>
               </div>
               <div class="col-sm-6">
                   <button type="button" class="btn btn-primary" id="btnSearch">查询</button>
               </div>
            </div>
        </form>
      	<div id="dtGridContainer" class="dt-grid-container"></div>
        <div id="dtGridToolBarContainer" class="dt-grid-toolbar-container"></div>
    </div>
  </div>
</section>

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
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'telephone',
    title : '联系电话',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'operation',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);text-align:center',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	var selectHtml='<button class="btn btn-sm btn-default" onclick="select('+record.id+')">设为精选</button>';
        return selectHtml;
    }
}];

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : false,
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
    $("#cityId").click(customSearch);
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
    grid.parameters['city_id'] = $("#cityId").val();
    grid.refresh(true);
}

function deleteCon(id) {
	layer.confirm('确定要删除该专题页吗?', {icon: 3, title:'提示'}, function(index){
        ajaxPost('/topic/deleteTopicConBatch',{ids:id});
        layer.close(index);
    });
}

function select(merchantId) {
	layer.confirm('确定要推荐该门店吗?', {icon: 3, title:'提示'}, function(index){
        ajaxPost('/merchant/setRecommendBatch',{ids:merchantId});
    });
}
</script>