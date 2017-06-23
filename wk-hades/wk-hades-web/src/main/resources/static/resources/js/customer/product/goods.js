var dtGridColumns = [{
    id : 'id',
    title : '编号',
    type : 'number',
    columnClass : 'text-center',
    hideType : 'sm|xs|md|lg',
    headerStyle : 'background:rgb(236,240,245);',
    fastQuery:true,
    fastQueryType:'eq'
},{
    id : 'imageUrl',
    title : '图片',
    type : 'img',
    columnClass : 'text-center',
    hideType : 'xs',
    headerStyle : 'background:rgb(236,240,245);',
    resolution:function(value,record,column,grid,dataNo,columnNo){
        return "<img src="+value+" width='50' height='50'>";
    }
}, {
    id : 'productName',
    title : '商品名称',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    fastQuery:true,
    fastQueryType:'lk'
}, {
    id : 'englishName',
    title : '英文名',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    fastQuery:true,
    fastQueryType:'lk'
}, {
    id : 'unitPrice',
    title : '单价',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if(record.specification==1){
            return value;
        }
        return "<a href='javascript:void(0)'>查看明细</a>";
    },
    fastQuery:true,
    fastQueryType:'eq'
}, {
    id : 'categoryList',
    title : '所属分类',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'status',
    title : '产品状态',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'newProductFlag',
    title : '是否新品',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    resolution:function(value, record, column, grid, dataNo, columnNo){
        if (value == 0) {
            return '<span class="label label-sm label-danger arrowed arrowed-righ">否</span>';
        } else {
            return '<span class="label label-sm label-info arrowed arrowed-righ">是</span>';
        }
    }
}, {
    id : 'recommend',
    title : '是否推荐',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    resolution:function(value, record, column, grid, dataNo, columnNo){
        if (value == 0) {
            return '<span class="label label-sm label-danger arrowed arrowed-righ">否</span>';
        } else {
            return '<span class="label label-sm label-success arrowed arrowed-righ">是</span>';
        }
    }
}, {
    id : 'specification',
    title : '是否可选规格',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'sm|xs|md|lg',
    resolution:function(value, record, column, grid, dataNo, columnNo){
        if (value == 0) {
            return '<span class="label label-sm label-success arrowed arrowed-righ">不可选</span>';
        } else {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">可选</span>';
        }
    }
},{
    id : 'createTime',
    title : '创建时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'sm|xs|md|lg',
    fastQuery:true,
    fastQueryType:'range'
},{
    id : 'modifyTime',
    title : '更新时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'sm|xs|md|lg'
},{
    id:'operation',
    title:'操作',
    type:'string',
    columnClass:'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    resolution:function(value, record, column, grid, dataNo, columnNo){
        var content = '';
        if(record.status==0) {
            content += '<button class="btn btn-xs btn-success" onclick="doExcute(\''+record.id+'\',\'up\')"><i class="fa fa-arrow-up"></i>上架</button>';
        }else{
            content += '<button class="btn btn-xs btn-danger" onclick="doExcute(\''+record.id+'\',\'down\')"><i class="fa fa-arrow-down"></i>下架</button>';
        }
        content += '  ';
        if(record.recommend==0){
            content += '<button class="btn btn-xs btn-info" onclick="setStar(\''+record.id+'\',\'set\')"><i class="fa fa-star"></i>设置推荐</button>';
        }else{
            content += '<button class="btn btn-xs btn-warning" onclick="setStar(\''+record.id+'\',\'cancel\')"><i class="fa fa-star"></i>取消推荐</button>';
        }

        return content;
    }
}];


//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    loadURL : sys.rootPath + '/goods/list',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|fastQuery',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
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
    grid.parameters['productName'] = $("#searchKey").val();
    grid.refresh(true);
}

function doExcute(id,oper){
    var status=(oper=="up"?"1":"0");
    $.ajax({
        type:"post",
        url:sys.rootPath+"/goods/upOrDownOper",
        data:{status:status,id:id},
        success:function(data){
            if(data.success){
                grid.reload({allReload:true});
            }else{
                layer.msg(data.responseText ,{icon:2});
            }
        },
        error : function(data, errorMsg) {
            layer.msg(data.responseText ,{icon:2});
        }
    })
}

function setStar(id,oper){
    var recom=(oper=="set"?"1":"0");
    $.ajax({
        type:"post",
        url:sys.rootPath+"/goods/setStar",
        data:{id:id,recom:recom},
        success:function(data){
            if(data.success){
                grid.reload({allReload:true});
            }else{
                layer.msg(data.responseText ,{icon:2});
            }
        },
        error : function(data, errorMsg) {
            layer.msg(data.responseText ,{icon:2});
        }
    });
}


