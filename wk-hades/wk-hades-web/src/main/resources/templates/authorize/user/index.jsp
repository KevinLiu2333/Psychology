<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1 id="firstTitle">
    	用户管理
    	<small id="secondTitle">用户列表</small>
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
    <li><a href="#">首页</a></li>
    <li class="active">用户管理</li>
  </ol>
</section>
  <section class="content" id="page-content">
  <div class="">
  <div class="page-header">
    <shiro:hasPermission name="user:add"><button onclick="addModel('${pageContext.request.contextPath}/user/addUI','添加用户','410px')" class="btn btn-primary">添加</button></shiro:hasPermission>
    <shiro:hasPermission name="user:edit"><button onclick="editModel1('${pageContext.request.contextPath}/user/editUI','编辑用户','410px')" class="btn btn-success">编辑</button></shiro:hasPermission>
    <shiro:hasPermission name="user:deleteBatch "><button onclick="delModel('${pageContext.request.contextPath}/user/deleteBatch')" class="btn btn-danger">删除</button></shiro:hasPermission>
    <shiro:hasPermission name="user:resetPassword"><button onclick="resetPWDModel()" class="btn btn-warning">重置密码</button></shiro:hasPermission>
  </div>
  <section>
        <div class="box">
            <!-- /.box-header -->
            <div class="box-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-1 col-sm-offset-0"  for="searchKey">名称</label>
                        <div class="col-sm-2">
                            <input class="form-control" type="text" name="searchKey" id="searchKey" placeholder="姓名、账户名称...">
                        </div>
                        <div class="col-sm-6">
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
<input type="hidden" id="menuId" value="3">
<script type="text/javascript">
var dtGridColumns = [{
    id : 'id',
    title : '编号',
    type : 'number',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'xs'
}, {
    id : 'accountName',
    title : '账户名',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'userName',
    title : '姓名',
    type : 'string',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center'
}, {
    id : 'role',
    title : '所属角色',
    type : 'string',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (typeof(value) == "undefined") {
            return '未指定';
        } else {
            return value.name;
        }
    }
}, {
    id : 'deleteStatus',
    title : '是否删除',
    type : 'string',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == 0) {
            return '<span class="label label-sm label-success arrowed arrowed-righ">正常</span>';
        } else {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">已删除</span>';
        }
    }
}, {
    id : 'locked',
    title : '是否锁定',
    type : 'string',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == 0) {
            return '<span class="label label-sm label-success arrowed arrowed-righ">正常</span>';
        } else {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">已删除</span>';
        }
    }
}, {
    id : 'creatorName',
    title : '创建者',
    type : 'string',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center',
    hideType : 'sm|xs'
}, {
    id : 'createTime',
    title : '创建时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'sm|xs'
}, {
    id : 'updateTime',
    title : '更新时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);',
    hideType : 'sm|xs|md',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == null) {
            return '';
        } else {
            return value;
        }
    }
}];

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    loadURL : sys.rootPath + '/user/list',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : '',
    pageSize : sys.pageNum,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.DtGrid.init(dtGridOption);
grid.load();
$("#btnSearch").click(customSearch);
//注册回车键事件
document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
    if(ev.keyCode==13) {
        customSearch();
    }
};

function customSearch() {
    grid.parameters = new Object();
    grid.parameters['userName'] = $("#searchKey").val();
    grid.refresh(true);
}


/**
 *重置密码
 */
function resetPWDModel() {
    var rows = grid.getCheckedRecords();
    if (rows.length == 1) {
        var index;
        $.ajax({
            type : "POST",
            url : sys.rootPath + '/user/resetPassword',
            data : {
                "id" : rows[0].id,
                "accountName" : rows[0].accountName,
                "userName" : rows[0].userName
            },
            dataType : "json",
            beforeSend : function()
            {
                index = layer.load();
            },
            success : function(resultdata) {
                layer.close(index);
                if (resultdata.success) {
                    layer.msg(resultdata.message, {
                        icon : 1
                    });
                } else {
                    layer.msg(resultdata.message, {
                        icon : 5
                    });
                }
            },
            error : function(data, errorMsg) {
                layer.close(index);
                layer.msg(data.responseText, {icon : 2});
            }
        });
    } else {
        layer.msg("你没有选择行或选择了多行数据", {
            icon : 0
        });
    }
}
</script>

