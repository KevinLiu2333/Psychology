<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Content Header (Page header) -->
 <section class="content-header">
     <h1 id="firstTitle">订单管理
         <small id="secondTitle">订单列表</small>
     </h1>
     <ol class="breadcrumb">
         <li><a href="#"><i class="fa fa-dashboard"></i>订单管理</a></li>
         <li class="active">订单列表</li>
     </ol>
 </section>
 <!-- Main content -->
<section class="content" id="page-content">
<!-- 编辑器源码文件 -->
<div class="">
    <!--search-->
    <section>
        <div class="box">
            <!-- /.box-header -->
            <div class="box-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-1 col-sm-offset-0"  for="surname">联系人姓氏</label>
                        <div class="col-sm-2">
                            <input class="form-control" type="text" name="surname" id="surname">
                        </div>
                        <label class="control-label col-sm-1  col-sm-offset-0" for="number">人数</label>
                        <div class="col-sm-2">
                            <input type="text" id="number" class="form-control" name="number">
                        </div>
                        <label class="control-label col-sm-1  col-sm-offset-0" for="status">状态</label>
                        <div class="col-sm-2">
                            <select name="status" id="status" class="form-control">
                                <option value=" ">--状态--</option>
                                <option value="1">预定</option>
                                <option value="2">取消</option>
                                <option value="3">订单确认-成功</option>
                                <option value="4">订单确认-拒绝</option>
                                <option value="5">已支付</option>
                                <option value="6">已完成</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-1  col-sm-offset-0" for="starTime">开始时间</label>
                        <div class="col-sm-2">
                            <input type="text" id="starTime" class="form-control" name="starTime" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd'})">
                        </div>
                        <label class="control-label col-sm-1  col-sm-offset-0" for="endTime">结束时间</label>
                        <div class="col-sm-2">
                            <input type="text" id="endTime" class="form-control" name="endTime" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'starTime\')}'})">
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
    <!--grid-->
    <section>
        <div class="box">
            <div class="box-body">
                <div id="dtGridContainer" class="dt-grid-container"></div>
                <div id="dtGridToolBarContainer" class="dt-grid-toolbar-container"></div>
            </div>
            <!-- /.box-body -->
        </div>
    </section>
</div>
</section>
 <!-- /.content -->
<input type="hidden" id="menuId" value="38">
<script>
    var dtGridColumns = [{
        id : 'peopleNum',
        title : '人数',
        type : 'number',
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
        title : '姓氏',
        type : 'string',
        headerStyle : 'background:rgb(236,240,245);text-align:center',
        columnClass : 'text-center'
    }, {
        id : 'sex',
        title : '称呼',
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
    	    	case '5':
    	    		content = '已支付';
    	    		break;
    	    	case '6':
    	    		content = '已完成';
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
            return '<center><button class="btn btn-success" onclick="editModel('+record.id+',\'/order/editUI\',\'订单查看\',\'630px\')">查看</button></center>';
        }
    }];

    var dtGridOption = {
   	    lang : 'zh-cn',
   	    ajaxLoad : true,
   	    loadURL : sys.rootPath + '/order/list',
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
    /**
     * 自定义查询
     * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
     */
    function customSearch() {
        grid.parameters = new Object();
        grid.parameters['surname'] = $("#surname").val();
        grid.parameters['number'] = $("#number").val();
        grid.parameters['starTime'] = $("#starTime").val();
        grid.parameters['endTime'] = $("#endTime").val();
        grid.parameters['status'] = $("#status").val();
        grid.refresh(true);
    }
</script>