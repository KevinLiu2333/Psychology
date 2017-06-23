<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<section class="content-header">
  <h1 id="firstTitle">
     APP管理<small id="secondTitle">话题列表</small>
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
    <li><a href="#">首页</a></li>
    <li class="active">APP管理</li>
  </ol>
</section>
<section class="content" id="page-content">
<div class="">
  <div class="page-header">
    <shiro:hasPermission name="theme:add"><button class="btn btn-primary" onclick="addModel('${pageContext.request.contextPath}/theme/addUI','添加话题','600px')">添加</button></shiro:hasPermission>
    <shiro:hasPermission name="theme:edit"><button class="btn btn-success" onclick="editModel1('${pageContext.request.contextPath}/theme/editUI','修改话题','600px')">编辑</button></shiro:hasPermission>
    <shiro:hasPermission name="theme:deleteBatch"><button class="btn btn-danger" onclick="delModel('${pageContext.request.contextPath}/theme/deleteBatch')">删除</button></shiro:hasPermission>
  </div>

  <section>
        <div class="box">
            <!-- /.box-header -->
            <div class="box-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-1 col-sm-offset-0"  for="title">话题标题</label>
                        <div class="col-sm-2">
                            <input class="form-control" type="text" name="title" id="title" placeholder="话题标题...">
                        </div>
                        <label class="control-label col-sm-1 col-sm-offset-0"  for="status">状态</label>
                        <div class="col-sm-1" style="padding-top:8px;">
                            <select name="status" id="status">
                            	<option value="">==请选择==</option>
                            	<option value="0">正常</option>
                            	<option value="1">删除</option>
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
<input type="hidden" id="menuId" value="86">
<script type="text/javascript">

	function editContent(id,type,nav,title,width,height,max) {
		if (type == "2") {
			nav = "/topic/editTopicHtmlUI";
		}
	    //获取选择的行
	    if (height == "" || height == undefined) {
	        height = "790px";
	    }
	    if (id == undefined||id.length<=0) {
	        var rows = grid.getCheckedRecords();
	        if (rows.length == 1) {
	            layer.open({
	                type: 2,
	                area: [width, height],
	                fix: false, //不固定
	                maxmin: true,
	                content: nav + '?id=' + rows[0].id,
	                title:title
	            });
	        } else {
	            layer.msg("你没有选择行或选择了多行数据", {
	                icon : 0
	            });
	        }
	    } else {
	        var index = layer.open({
	            type: 2,
	            area: [height, width],
	            fix: false, //不固定
	            maxmin: true,
	            content: sys.rootPath + nav + '?id=' + id,
	            title: title
	        });
	        if (max) {
	            layer.full(index);
	        }
	    }
	}

	function updateStatus(ids, status)	{
		var msg;
		if (status == "1") {
			msg = "确定要恢复当前记录吗？";
		} else {
			msg = "确定要删除当前记录吗？";
		}
	    layer.confirm(msg, {icon: 3, title:'提示'}, function(index){
	        ajaxPost('/topic/updateTopicStatus',{ids:ids,status:status});
	        layer.close(index);
    	});
	}
	
	var dtGridColumns = [{
	    id : 'title',
	    title : '标题',
	    type : 'string',
	    columnClass : 'text-center',
	    headerStyle : 'background:rgb(236,240,245);'
	}, {
	    id : 'picUrl',
	    title : '话题图片',
	    type : 'string',
	    columnClass : 'text-center',
	    headerStyle : 'background:rgb(236,240,245);',
	    resolution : function(value, record, column, grid, dataNo, columnNo) {
	        return '<a href="'+record.picUrl+'" data-lightbox="activity_image"><img width="50px" height="50px" src="'+record.picUrl+'" ></a>';
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
	    id : 'lastModifyTime',
	    title : '最后修改时间',
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
	        if(value=='0'){
	            return '<span class="label label-sm label-success arrowed arrowed-righ">生效</span>';
	        }else{
	            return '<span class="label label-sm label-warning arrowed arrowed-righ">失效</span>';
	        }
	    }
	}
	];

	var dtGridOption = {
	    lang : 'zh-cn',
	    ajaxLoad : true,
	    check : true,
	    loadURL : sys.rootPath + '/theme/list',
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
	    grid.parameters['title'] = $("#title").val();
	    grid.parameters['status'] = $("#status").val();
	    grid.refresh(true);
	}
</script>