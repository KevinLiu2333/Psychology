var dtGridColumns = [{
    id : 'id',
    title : '商铺编号',
    type : 'number',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'xs'
}, {
    id : 'merchant_name',
    title : '商铺名称',
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
},
{
    id : 'location',
    title : '坐标经纬度',
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
    id : 'isHot',
    title : '是否热门',
    otype : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if(value == null || value == false){
            return '<span class="label label-sm label-success arrowed arrowed-righ">否</span>';
        }else{
            return '<span class="label label-sm label-danger arrowed arrowed-righ">是</span>';
        }
    }
}, {
    id : 'status',
    title : '状态',
    otype : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if(value==0){
            return '<span class="label label-sm label-info arrowed arrowed-righ">生效</span>';
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
        var topHtml='<button class="btn btn-sm btn-default" onclick="merchant.setTop('+record.id+')">设置置顶</button>&nbsp;';
        if(record.isTop==1){
            topHtml='<button class="btn btn-sm btn-default" onclick="merchant.setTop('+record.id+')">取消置顶</button>&nbsp;';
        }
        var hotHtml='<button class="btn btn-sm btn-default" onclick="merchant.setHot('+record.id+')">取消热门</button>&nbsp;';
        if(record.hot==null || record.hot==false){
            hotHtml='<button class="btn btn-sm btn-default" onclick="merchant.setHot('+record.id+')">置为热门</button>&nbsp;';
        }

        return '<button class="btn btn-sm btn-default" onclick="editModel('+record.topic_id+',\'/merchant/edit\',\'编辑门店\',\'630px\')">编辑</button>&nbsp;' +
                '<button class="btn btn-sm btn-default" onclick="topic.changeStatus('+record.topic_id+')">改状态</button>&nbsp;' +
                '<button class="btn btn-sm btn-default" onclick="editModel('+record.topic_id+',\'/Topic/toTopicCommentList\',\'查看评论\',\'630px\')">查看评论</button>&nbsp;' +topHtml+hotHtml+
                '<button class="btn btn-sm btn-default" onclick="editModel('+record.topic_id+',\'/Topic/recommendForm\',\'推荐门店\',\'630px\',\'790px\')">推荐</button>';
    }
}];

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    loadURL : sys.rootPath + '/merchant/list',
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

var merchant={
    setTop:function setTop(id,status){
        ajaxPost('/merchant/setOrUnsetTop',{ids:id});
    },
    setHot:function setHot(id){
        ajaxPost('/merchant/setOrUnsetHot',{ids:id});
    },
    changeStatus:function(id){
        ajaxPost('/merchant/changeStatus',{ids:id});
    },
    deleteRow:function(id){
        layer.confirm('确定要删除当前记录吗?', {icon: 3, title:'提示'}, function(index){
            ajaxPost('/merchant/deleteTopic',{ids:id});
            layer.close(index);
        });
    }
}