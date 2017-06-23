<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1 id="firstTitle">
    	APP用户管理
    	<small id="secondTitle">用户列表</small>
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
    <li><a href="#">首页</a></li>
    <li class="active">APP用户管理</li>
  </ol>
</section>
  <section class="content" id="page-content">
  <div class="">
  <div class="page-header">
    <shiro:hasPermission name="user:resetPassword"><button onclick="updateStatus('2')" class="btn btn-warning">禁用</button></shiro:hasPermission>
    <shiro:hasPermission name="user:resetPassword"><button onclick="updateStatus('1')" class="btn btn-primary">启用</button></shiro:hasPermission>
  </div>
  <section>
        <div class="box">
            <!-- /.box-header -->
            <div class="box-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-1 col-sm-offset-0"  for="searchKey">账户名</label>
                        <div class="col-sm-1">
                            <input class="form-control" type="text" name="searchKey" id="searchKey" placeholder="账户名">
                        </div>
                        <label class="control-label col-sm-1 col-sm-offset-0"  for="status">状态</label>
                        <div class="col-sm-1" style="padding-top:8px;">
                            <select name="status" id="status">
                            	<option value="">==请选择==</option>
                            	<option value="1">启用</option>
                            	<option value="2">禁用</option>
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
<input type="hidden" id="menuId" value="77">
<script type="text/javascript">
var dtGridColumns = [{
    id : 'userName',
    title : '账户名',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'portrait.url',
    title : '头像',
    type : 'string',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        var img = "";
        if (record.portrait != null) {
        	img = '<a target="_blank" href="'+record.portrait.url+'" data-lightbox="activity_image"><img width="30px" height="30px" src="'+record.portrait.url+'" ></a>';
        }
        return img;
    }
}, {
    id : 'mobile',
    title : '手机号',
    type : 'string',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center'
}, {
    id : 'wxKey',
    title : '微信OpenID',
    type : 'string',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center'
}, {
    id : 'regDate',
    title : '注册时间',
    type : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center'
}, {
    id : 'sex',
    title : '性别',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value=="1") {
        	return '男';
        } else {
        	return '女';
        }
    }
}, {
    id : 'status',
    title : '状态',
    type : 'string',
    headerStyle : 'background:rgb(236,240,245);',
    columnClass : 'text-center',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == 1) {
            return '<span class="label label-sm label-success arrowed arrowed-righ">正常</span>';
        } else {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">已禁用</span>';
        }
    }
}];

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    loadURL : sys.rootPath + '/appUser/list',
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
    grid.parameters['status'] = $("#status").val();
    grid.refresh(true);
}

// 禁用或启用
function updateStatus(status) {
    var rows = grid.getCheckedRecords();
    //alert(rows)
    var ids = '';
    for(var i=0; i<rows.length; i++){
   		ids = ids + rows[i].id + ","; 
   	}
    if (rows.length > 0) {
        var index;
        $.ajax({
            type : "POST",
            url : sys.rootPath + '/appUser/updateStatus',
            data : {
                "userIds" : ids,
                "status" : status
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
                
                grid.reload({allReload:true});
            },
            error : function(data, errorMsg) {
                layer.close(index);
                layer.msg(data.responseText, {icon : 2});
            }
        });
    } else {
        layer.msg("你还没有选择行数据", {
            icon : 0
        });
    }
}
</script>

