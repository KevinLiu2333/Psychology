var dtGridColumns = [{
    id : 'name',
    title : '城市名称',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'longitude',
    title : '经度',
    type : 'double',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center'
},{
    id : 'latitude',
    title : '纬度',
    type : 'double',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}];

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    loadURL : sys.rootPath + '/city/list',
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
    grid.parameters['name'] = $("#searchKey").val();
    grid.refresh(true);
}

var city={
    deleteRow:function(id){
        layer.confirm('确定要删除当前记录吗?', {icon: 3, title:'提示'}, function(index){
            ajaxPost('/merchant/deleteTopic',{ids:id});
            layer.close(index);
        });
    }
}