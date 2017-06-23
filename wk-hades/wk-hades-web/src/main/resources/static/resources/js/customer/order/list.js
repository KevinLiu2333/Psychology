var dtGridColumns = [{
    id : 'peopleNum',
    title : '人数',
    type : 'number',
    width:80,
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);text-align:center',
    hideType : 'xs'
}, {
    id : 'date',
    title : '时间',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);text-align:center'
}, {
    id : 'surname',
    title : '联系人',
    type : 'string',
    headerStyle : 'background:rgb(236,240,245);text-align:center',
    columnClass : 'text-center'
},{
    id : 'mobile',
    title : '联系电话',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);text-align:center'
},{
    id : 'note',
    title : '备注',
    type : 'string',
    width:80,
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);text-align:center'
},
{
    id : 'helpOrder',
    title : '是否帮他人订',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);text-align:center', 
    resolution:function(value, record, column, grid, dataNo, columnNo){
    	var content = '';
    	if (value) {
	        content = '是';
	    } else {
	        content = '否';
	    }
	    return content;
	}
},
{
    id : 'status',
    title : '状态',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);text-align:center', 
    resolution:function(value, record, column, grid, dataNo, columnNo){
    	var content = '';
    	switch(value) {
	    	case '1':
		    	  content = '待商家确定';
		    	  break;
	    	case '2':
	    		content = '用户取消';
	    		break;
	    	case '3':
	    		content = '订单确认-成功';
	    		break;
	    	case '4':
	    		content = '订单确认-拒绝';
	    		break;
	    	default:
	    	  '未知'
    	}
    	
	    return content;
	}
},
 {
    id : 'operation',
    title : '操作',
    type : 'string',
    columnClass : 'text-left',
    headerStyle : 'background:rgb(236,240,245);text-align:center',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        return '<center><button class="btn btn-sm btn-default" onclick="editModel('+record.topic_id+',\'/merchant/edit\',\'订单确认\',\'630px\')">确认</button></center>';
    }
}];

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    loadURL : sys.rootPath + '/order/list',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : '',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()){
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
    grid.parameters['merchant_name'] = $("#searchKey").val();
    grid.refresh(true);
}
