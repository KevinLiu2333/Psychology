$(function() {
    $('#tree').jstree({
        "core" : {
            'data' : {
                "url" : "/resource/resourceTree?roleId="+$("#id").val(),
                "dataType" : "json"
            },
            "themes" : {
                "responsive" : true
            },
            "multiple" : true,
            "animation" : 200,
            "dblclick_toggle" : true,
            "expand_selected_onload" : true
        },
        "checkbox" : {
            "keep_selected_style" : true,
            "three_state" : false,
            "cascade" : "up"
        },
        "plugins" : ["checkbox"]
    });
    $("#tree").slimScroll({
        width: 'auto', //可滚动区域宽度
        height: '400px', //可滚动区域高度
        size: '10px', //组件宽度
        color: '#000', //滚动条颜色
        position: 'right', //组件位置：left/right
        distance: '0px', //组件与侧边之间的距离
        start: 'top', //默认滚动位置：top/bottom
        opacity: .4, //滚动条透明度
        alwaysVisible: true, //是否 始终显示组件
        disableFadeOut: false, //是否 鼠标经过可滚动区域时显示组件，离开时隐藏组件
        railVisible: true, //是否 显示轨道
        railColor: '#333', //轨道颜色
        railOpacity: .2, //轨道透明度
        railDraggable: true, //是否 滚动条可拖动
        railClass: 'slimScrollRail', //轨道div类名
        barClass: 'slimScrollBar', //滚动条div类名
        wrapperClass: 'slimScrollDiv', //外包div类名
        allowPageScroll: true, //是否 使用滚轮到达顶端/底端时，滚动窗口
        wheelStep: 20, //滚轮滚动量
        touchScrollStep: 200, //滚动量当用户使用手势
        borderRadius: '7px', //滚动条圆角
        railBorderRadius: '7px' //轨道圆角
    });
});

var ids = [];

/**
 * 提交表单
 * 适用场景：form表单的提交，主要用在用户、角色、资源等表单的添加、修改等
 * @param {Object} commitUrl 表单提交地址
 * @param {Object} listUrl 表单提交成功后转向的列表页地址
 */
function commitPerm(formid,commitUrl) {
    ids = [];
    var tree = $('#tree').jstree();
    //获取所有选中节点id
    var selectedIds = tree.get_checked();
    //获取所有选中节点
    var selected = tree.get_checked(true);
    //遍历节点，获取选中节点的所有父节点
    for(var i = 0; i<selected.length; i++){
        getParents(tree,selected[i]);
    }
    var index;
    $.ajax({
        type : "POST",
        url : sys.rootPath + commitUrl,
        data : {
            "roleId" : $("#id").val(),
            "resourceIds" : _.union(ids,selectedIds).join(',')
        },
        dataType : "json",
        beforeSend : function()
        {
            index = layer.load();
        },
        success : function(resultdata) {
             layer.close(index);
            if (resultdata.success) {
                layer.msg(resultdata.message,{icon:1});
                closeModel();
            } else {
                layer.msg(resultdata.message, {icon : 5});
            }
        },
        error : function(errorMsg) {
            layer.close(index);
            layer.msg('服务器未响应,请稍后再试', {
                icon : 2
            });
        }
    });
}

/**
 * 递归遍历节点，获取该节点的所有父节点
 * @param {Object} treeObj  jstree对象
 * @param {Object} nodeObj  jstree node节点对象
 */
function getParents(treeObj,nodeObj)
{
    var parentId = treeObj.get_parent(nodeObj);
    if(parentId != "#")
    {
        ids.push(parentId);
        getParents(treeObj,treeObj.get_node(parentId));
    }
}
