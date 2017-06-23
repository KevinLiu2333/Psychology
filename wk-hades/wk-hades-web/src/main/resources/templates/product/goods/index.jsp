<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<section class="content-header">
  <h1 id="firstTitle">
     商品管理<small id="secondTitle">商品列表</small>
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
    <li><a href="#">首页</a></li>
    <li class="active">商品管理</li>
  </ol>
</section>
<section class="content" id="page-content">
<div class="">
  <div class="page-header">
        <shiro:hasPermission name="product:add"><button onclick="addModel('${pageContext.request.contextPath }/goods/addUI','添加商品','770px')" class="btn btn-primary">添加</button></shiro:hasPermission>
        <shiro:hasPermission name="product:edit"><button onclick="editModel1('${pageContext.request.contextPath }/goods/editUI','编辑商品','770px')" class="btn btn-success">编辑</button></shiro:hasPermission>
        <shiro:hasPermission name="product:deleteBatch "><button onclick="delModel('${pageContext.request.contextPath }/goods/deleteBatch')" class="btn btn-danger">删除</button></shiro:hasPermission>
  </div>

  <section>
        <div class="box">
            <!-- /.box-header -->
            <div class="box-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-1 col-sm-offset-0"  for="searchKey">商品名称</label>
                        <div class="col-sm-2">
                            <input class="form-control" type="text" name="searchKey" id="searchKey" placeholder="商品名称...">
                        </div>
                        <label class="control-label col-sm-1  col-sm-offset-0" for="number">产品状态</label>
                        <div class="col-sm-2">
                            <select name="status" id="status" class="form-control">
                                <option value=" ">--请选择--</option>
                                <option value="1">上架</option>
                                <option value="0">下架</option>
                            </select>
                        </div>
                        <label class="control-label col-sm-1  col-sm-offset-0" for="number">是否推荐</label>
                        <div class="col-sm-2">
                            <select name="recommend" id="recommend" class="form-control">
                                <option value=" ">--请选择--</option>
                                <option value="1">推荐</option>
                                <option value="0">非推荐</option>
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
<input type="hidden" id="menuId" value="32">

<script type="text/javascript">
var dtGridColumns = [{
    id : 'id',
    title : '编号',
    type : 'number',
    columnClass : 'text-center',
    hideType : 'sm|xs|md|lg',
    headerStyle : 'background:rgb(236,240,245);'
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
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'englishName',
    title : '英文名',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'unitPrice',
    title : '单价',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'promotionPrice',
    title : '促销价',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
},{
    id : 'categoryList',
    title : '所属分类',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    resolution:function(value, record, column, grid, dataNo, columnNo){
    	var cName = "";
    	for(var o in value){  
            cName += value[o].categoryName+ "，";  
        }
    	return cName.substring(0, cName.lastIndexOf('，'));
    }
}, {
    id : 'status',
    title : '产品状态',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    resolution:function(value, record, column, grid, dataNo, columnNo){
        if (value == 1) {
            return '<span class="label label-sm label-info arrowed arrowed-righ">上架</span>';
        } else {
            return '<span class="label label-sm label-danger arrowed arrowed-righ">下架</span>';
        }
    }
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
}/* , {
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
} */,{
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
            content += '<button class="btn btn-xs btn-success" onclick="doExcute(\''+record.id+'\',\'1\')"><i class="fa fa-arrow-up"></i>上架</button>';
        }else{
            content += '<button class="btn btn-xs btn-danger" onclick="doExcute(\''+record.id+'\',\'0\')"><i class="fa fa-arrow-down"></i>下架</button>';
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

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    loadURL : sys.rootPath + '/goods/list',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|fastQuery',
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
    grid.parameters['productName'] = $("#searchKey").val();
    grid.parameters['status'] = $("#status").val();
    grid.parameters['recommend'] = $("#recommend").val();
    grid.refresh(true);
}

function doExcute(id,status){
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
</script>
