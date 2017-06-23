$(function(){
    $("[nav-menu]").each(function() {
        $(this).bind("click", function() {
            var nav = $(this).attr("nav-menu");
            window.location.href=sys.rootPath+nav;
        });
    });
});

/**
 * 新增
 * @param {Object} nav  提交url
 */
function addModel(nav,title,height) {
    //加载新增页面
    layer.open({
        type: 2,
        area: ['790px',height],
        fix: true, //不固定
        maxmin: true,
        content: nav,
        title:title
    });
}

/**
 * 新增
 * @param {Object} nav  提交url
 */
function addModel(nav,title,width,height,max) {
    if(height==""||height==undefined){
        height="790px";
    }
    //加载新增页面
    var index=layer.open({
        type: 2,
        area: [height,width],
        fix: true, //不固定
        maxmin: true,
        content: nav,
        title:title,
    });
    if(max){
        layer.full(index);
    }
}

function closeModel(){
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index);
}

function resetModel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.iframeAuto(index);
}

/**
 * 编辑
 * @param {Object} nav  提交url
 */
function editModel(nav,title,height) {
    //获取选择的行
    var rows = grid.getCheckedRecords();
    if (rows.length == 1) {
        layer.open({
            type: 2,
            area: ['790px', height],
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
}

/**
 * 编辑
 * @param {Object} nav  提交url
 */
function editModel2(nav,title,width,height,max) {
    //获取选择的行
    var rows = grid.getCheckedRecords();
    if (rows.length == 1) {
        var index = layer.open({
            type: 2,
            area: ['790px', height],
            fix: false, //不固定
            maxmin: true,
            content: nav + '?id=' + rows[0].id,
            title:title
        });
        
        if(max){
            layer.full(index);
        }
    } else {
        layer.msg("你没有选择行或选择了多行数据", {
            icon : 0
        });
    }
}

/**
 * 编辑
 * @param {Object} nav  提交url
 */
function editModel3(id,nav,title,width,height,max) {
	alert(4)
    //获取选择的行
    if (height == "" || height == undefined) {
        height = "790px";
    }
    if (id == undefined||id.length<=0) {
        var rows = grid.getCheckedRecords();
        if (rows.length == 1) {
            layer.open({
                type: 2,
                area: ['790px', width],
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

/**
 * 删除
 * @param {Object} nav  提交url
 * @param callback 主函数执行完毕后调用的回调函数名称
 */
function delModel(nav, callback) {
    var rows = grid.getCheckedRecords();
    if (rows.length >= 1) {
        if(nav == '/user/deleteBatch'){
            if(rows[0].role.name == '超级管理员'){
                layer.msg('该用户为超级管理员,不能删除!',{icon:0});
                return false;
            }
            if(rows[0].name == '超级管理员'){
                layer.msg('该角色为基础角色,不能删除!',{icon:0});
                return false;
            }
        }
        layer.confirm('确认删除吗？', {icon:3,title:'删除提示'},function(index, layero) {
            var ids = [];
             $.each(rows, function(index, value) {
             ids.push(this.id);
             });
             
            $.ajax({
                type : "POST",
                url : nav,
                data : {
                    "ids" : ids.join(',')
                },
                dataType : "json",
                success : function(resultdata) {
                    if (resultdata.success) {
                        layer.msg(resultdata.message,{icon:1});
                        if (callback) {
                            callback();
                        }
                    } else {
                        layer.msg(resultdata.message,{icon:5});
                    }
                    parent.grid.reload({allReload:true});
                },
                error : function(errorMsg) {
                    layer.msg('服务器未响应,请稍后再试',{icon:3});
                }
            });
            layer.close(index);
        });
    }else
    {
        layer.msg("你没有选择行",{icon:0});
    }
}


/**
 * 提交表单
 * 适用场景：form表单的提交，主要用在用户、角色、资源等表单的添加、修改等
 * @param {Object} commitUrl 表单提交地址
 * @param {Object} listUrl 表单提交成功后转向的列表页地址
 */
function commit(formId, commitUrl) {
    //表单验证
   var valid = $("#"+formId).data('formValidation');
    valid.validate();
    resetModel();
    if(!valid.isValid()){
        return;
    }
    //组装表单数据
    var data = $("#"+ formId).serialize().replace(/\+/g," ");
    data = decodeURIComponent(data,true);
    $.ajax({
        type : "POST",
        url : sys.rootPath + commitUrl,
        data : data,
        dataType : "json",
        success : function(resultdata) {
            /*if (resultdata.success) {
                layer.msg(resultdata.message,{icon:1});
            } else {
                layer.msg(resultdata.message,{icon:5});
            }*/
            parent.grid.reload({allReload:true});
            closeModel();
        },
        error : function(data, errorMsg) {
        	alert(JSON.stringify(data));
        	alert(errorMsg);
            layer.msg(data.responseText ,{icon:2});
            //closeModel();
        }
    });
}

/**
 * 提交表单 拼接成json对象提交
 * 适用场景：form表单的提交，主要用在用户、角色、资源等表单的添加、修改等
 * @param {Object} commitUrl 表单提交地址
 * @param {Object} listUrl 表单提交成功后转向的列表页地址
 */
function commit1(formId, commitUrl) {
    //表单验证
    var valid = $("#"+formId).data('formValidation');
    valid.validate();
    resetModel();
    if(!valid.isValid()){
        return;
    }
    //组装表单数据
    var data = $("#"+ formId).serializeArray();
    var obj = {};
   $.each(data, function(index, node) {
    	obj[node.name] = node.value;
      });   
   $.ajax({
        type : "POST",
        url : sys.rootPath + commitUrl,
        data : JSON.stringify(obj),
        dataType : "json",
        contentType: "application/json; charset=utf-8",
        success : function(resultdata) {
            if (resultdata.success) {
                layer.msg(resultdata.message,{icon:1});
            } else {
                layer.msg(resultdata.message,{icon:5});
            }
            parent.grid.reload({allReload:true});
            closeModel();
        },
        error : function(data, errorMsg) {
            layer.msg(data.responseText ,{icon:2});
            //closeModel();
        }
    });
}