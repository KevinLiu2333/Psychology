/**
 * Created by miaoto1 on 2016/6/1.
 */

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

function addArticleModel(nav,title,width,height,action){
    var index=layer.open({
        type:2,
        area:[height,width],
        fix:true,
        maxmin:true,
        content:nav,
        title:title,
        cancel: function(){
            var flag=localData.get("saveFlag");
            if(action=="add"){
                if(flag){
                    localData.remove("mdataId");
                    localData.remove("materialData");
                    localData.remove("saveFlag");
                    grid.reload({allReload:true});
                    layer.close(indx);
                    layer.close(index);
                }else{
                    var indx=layer.confirm("当前数据未保存，关闭后内容会丢失,请谨慎操作!",{icon:3,title:"提示"},function(){
                        layer.close(indx);
                        layer.close(index);
                    });
                }
            }else{
                if(flag){
                    localData.remove("mdataId");
                    localData.remove("materialData");
                    localData.remove("saveFlag");
                    grid.reload({allReload:true});
                    layer.close(indx);
                    layer.close(index);
                }else{
                    var indx=layer.confirm("当前数据未保存，关闭前是否保留当前内容?",{icon:3,title:"提示"},function(){
                        layer.close(indx);
                        layer.close(index);
                    },function(){
                        localData.remove("mdataId");
                        localData.remove("materialData");
                        layer.close(indx);
                        layer.close(index);
                    });
                }
            }
            return false;
        }
    });
    layer.full(index);
}

function editModel1(nav,title,height) {
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
function editModel(id,nav,title,width,height,max) {
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

function resetModel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.iframeAuto(index);
}

function closeModel(){
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index);
}

function reValidate(formId){
    var $input=$("#"+formId).find("div > input");
    for(var i=0;i<$input.size();i++){
        $('#activityForm').formValidation('revalidateField',$($input[i]).attr("id"));
    }
}

/**
 * 提交表单
 * 适用场景：form表单的提交，主要用在用户、角色、资源等表单的添加、修改等
 * @param {Object} commitUrl 表单提交地址
 * @param {Object} listUrl 表单提交成功后转向的列表页地址
 */
function commit(formId, commitUrl,params) {
    //表单验证
    var valid = $("#"+formId).data('formValidation');
    if(valid != undefined) {
    	reValidate(formId);
    	valid.validate();
    	if(!valid.isValid()){
    		return;
    	}
    }
    //组装表单数据
    var data = $("#"+ formId).serialize();
    if(params){
        data=params;
    }
    $.ajax({
        type : "POST",
        url : sys.rootPath + commitUrl,
        data : data,
        dataType : "json",
        success : function(resultdata) {
            if(resultdata.success){
                parent.grid.reload({allReload:true});
                closeModel();
            }else{
                layer.msg(resultdata.message,{icon:5});
            }

        },
        error : function(data, errorMsg) {
            layer.msg("系统繁忙,请稍后重试" ,{icon:2});
        }
    });
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
 * post提交请求，适用于DTGrid
 * @param url
 * @param param
 */
function ajaxPost(url,param){
    $.ajax({
        type:"post",
        url:sys.rootPath+url,
        data:param,
        dataType : "json",
        success:function(data){
            if(data.success){
                layer.msg(data.message,{icon:1});
                grid.reload({allReload:true});
            }else{
                layer.msg(data.message,{icon:5});
            }
        },
        error : function(data, errorMsg) {
            layer.msg("系统繁忙,请稍后重试" ,{icon:2});
        }
    });
}
/**
 * 执行批量操作，适用于DTGrid
 * @param url
 * @param param
 */
function processorWithBatch(url,ids){
    if(ids.length<1){
        layer.msg("你没有选择行",{icon:0});
        return;
    }
    layer.confirm('该操作为批量操作,请确认是否需要执行？', {icon:3,title:'提示'},function(index, layero) {
        $.ajax({
            type : "POST",
            url : sys.rootPath+url,
            data : {
                "ids" : ids.join(',')
            },
            dataType : "json",
            success : function(resultdata) {
                if (resultdata.success) {
                    layer.msg(resultdata.message,{icon:1});
                    grid.reload({allReload:true});
                } else {
                    layer.msg(resultdata.message,{icon:5});
                }
            },
            error : function(errorMsg) {
                layer.msg('服务器未响应,请稍后再试',{icon:3});
            }
        });
        layer.close(index);
    });
}
function reverseModel(id,url){
    if(id==undefined||id.length<=0){
        layer.msg("当前话题未被推荐,无法撤销",{icon:5});
        return;
    }
    layer.confirm("撤消后不可回复,请谨慎操作!",{icon:3,title:'提示'},function(index,layero){
        $.ajax({
            type:"post",
            url:sys.rootPath+url,
            data:{id:id},
            dataType:"json",
            success:function(data){
                if (data.success) {
                    layer.msg(data.message,{icon:1});
                } else {
                    layer.msg(data.message,{icon:5});
                }
            },
            error:function(error){
                layer.msg('服务器未响应,请稍后再试',{icon:3});
            }
        });
    });
}