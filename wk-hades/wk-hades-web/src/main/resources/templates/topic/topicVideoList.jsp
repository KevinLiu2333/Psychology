<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css"/>
 <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/uploadify/jquery.uploadify.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/uploadify/uploadify.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/lightbox/lightbox.css">
<script src="${pageContext.request.contextPath}/plugins/lightbox/lightbox.js"></script>

<section class="content">
  <div class="box">
    <div class="box-body">
    	<form class="form-horizontal">
            <div class="form-group">
                <div class="col-sm-1">
                    <button type="button" class="btn btn-success" onclick="addModel('${pageContext.request.contextPath}/topic/addVideoUI?id=${id}','添加专题视频','760px','790px', true)">新增</button>
                </div>
            </div>
        </form>
      	<div id="dtGridContainer" class="dt-grid-container"></div>
          <div id="dtGridToolBarContainer" class="dt-grid-toolbar-container"></div>
    </div>
  </div>
</section>

<script>
var dtGridColumns = [{
    id : 'title',
    title : '标题',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
},{
    id : 'description',
    title : '描述',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
},{
    id : 'videoUrl',
    title : '视频地址',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'createDate',
    title : '创建时间',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);'
}, {
    id : 'operation',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerStyle : 'background:rgb(236,240,245);text-align:center',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        var delHtml='<button class="btn btn-sm btn-default" onclick="deleteCon('+record.id+')">删除</button>';
        return '<button class="btn btn-sm btn-default" onclick="editModel('+record.id+',\'/topic/editVideoUI\',\'修改\',\'760px\',\'790px\', true)">修改</button>&nbsp;'+delHtml;
    }
}
];

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : false,
    loadURL : sys.rootPath + '/topic/topicVideoList?id=${id}',
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
    grid.parameters['desc'] = $("#searchKey").val();
    grid.refresh(true);
}

function deleteCon(id) {
	layer.confirm('确定要删除该专题视频吗?', {icon: 3, title:'提示'}, function(index){
        ajaxPost('/topic/deleteTopicVideoBatch',{ids:id});
        layer.close(index);
    });
}
</script>