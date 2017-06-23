var dtGridColumns = [{
    id : 'description',
    title : '专题描述',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'topicPicUrl',
    title : '专题图片',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        return '<a href="'+record.topicPicUrl+'" data-lightbox="activity_image"><img width="50px" height="50px" src="'+record.topicPicUrl+'" ></a>';
    }
}, {
    id : 'createDate',
    title : '创建时间',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'status',
    title : '状态',
    otype : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if(value==1){
            return '<span class="label label-sm label-success arrowed arrowed-righ">生效</span>';
        }else{
            return '<span class="label label-sm label-warning arrowed arrowed-righ">失效</span>';
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
        var delHtml='<button class="btn btn-sm btn-default" onclick="rowDelete('+record.id+')">删除</button>';
        if(record.status==1){
            delHtml='<button class="btn btn-sm btn-default" onclick="rowDelete('+record.id+')">恢复</button>';
        }
        var editCon = '<button class="btn btn-sm btn-default" onclick="addModel(\'contentUI\', \'专题内容列表\', \'1024\', \'\', \'\')">编辑内容</button>';
        return '<button class="btn btn-sm btn-default" onclick="editModel('+record.id+',\'/publishTheme/toEditIndex\',\'编辑主题\',\'460px\',\'790px\')">修改</button>&nbsp;'+delHtml + editCon;
    }
}
];

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    loadURL : sys.rootPath + '/topic/list',
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
    grid.parameters['desc'] = $("#searchKey").val();
    grid.refresh(true);
}