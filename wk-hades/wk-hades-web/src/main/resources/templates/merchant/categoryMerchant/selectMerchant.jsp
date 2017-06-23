<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="content">
  <div class="box">
  	<form class="form-horizontal">
     	<input class="form-control" type="text" name="merchantName" id="merchantName" placeholder="门店名称...">
	  </form>
    <div class="box-body">
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
        return '<button class="btn btn-sm btn-default" onclick="select('+record.id+',\'/topic/editConUI\',\'选择\',\'460px\',\'790px\')">选择</button>';
    }
}];

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : false,
    loadURL : sys.rootPath + '/categoryMerchant/cityMerchantList?cityId=${cityId}',
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
    grid.parameters['merchant_name'] = $("#merchantName").val();
    grid.refresh(true);
}

function select(merchantId) {
	layer.confirm('确定要选择该门店吗?', {icon: 3, title:'提示'}, function(index){
        ajaxPost('/city/addCityCategoryMerchant',{cityCategoryId:${cityCategoryId}, merchantId:merchantId});
    });
}
</script>