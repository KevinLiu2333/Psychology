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

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    loadURL : sys.rootPath + '/usercoupon/list',
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