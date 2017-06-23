<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/uploadify/jquery.uploadify.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/uploadify/uploadify.css" />
<!-- Horizontal Form -->
<div class="box" style="padding-top:0px;">
    <!-- /.box-header -->
    <!-- form start -->
    <form id="goodsForm" name="goodsForm" class="form-horizontal" role="form" method="post"
          data-fv-message="This value is not valid"
          data-fv-feedbackicons-valid="glyphicon glyphicon-ok"
          data-fv-feedbackicons-invalid="glyphicon glyphicon-remove"
          data-fv-feedbackicons-validating="glyphicon glyphicon-refresh">
        <input type="hidden" id="id" name="id" value="${productEntity.id}"/>
        <input type="hidden" id="createTime" name="createTime" value="${productEntity.createTime}"/>
        <div class="box-body"  style="height:550px">
            <div class="nav-tabs-custom">
                <!-- <ul class="nav nav-tabs">
                    <li class="active"><a href="#tab_1" data-toggle="tab">商品基本信息</a></li>
                    <li><a href="#tab_2" data-toggle="tab">商品规格</a></li>
                </ul> -->
                <div class="tab-content">
                    <div class="tab-pane active" id="tab_1">
                        <div class="row">
                            <div class="col-sm-7">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 no-padding-right" for="productName">商品名称</label>
                                    <div class="col-sm-8">
                                        <div class="clearfix">
                                            <input class="form-control" name="productName" id="productName" type="text" placeholder="商品名称..." value="${productEntity.productName}"
                                                   data-fv-notempty="true"
                                                   data-fv-message="商品名称不能为空"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4 no-padding-right" for="englishName">英文名</label>
                                    <div class="col-sm-8">
                                        <div class="clearfix">
                                            <input class="form-control" name="englishName" id="englishName" rows="3" placeholder="英文名称..." data-fv-notempty="true" value="${productEntity.englishName}"
                                                   data-fv-message="商品英文名不能为空"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4 no-padding-right" for="categoryId">商品分类</label>
                                    <div class="col-sm-8">
                                        <div class="clearfix">
                                            <select multiple="multiple" id="categoryId" name="categoryIds" class="form-control">
                                                <c:forEach items="${categoryEntity}" var="val">
                                                    <option value="${val.id}" <c:forEach items="${productEntity.categoryList}" var="cl"><c:if test="${cl.id eq val.id }">selected=selected</c:if></c:forEach>>${val.categoryName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-5">
                                <div class="form-group">
                                    <img src="${productEntity.imageUrl}" id="showImg" name="showImg" class="col-sm-12 img-rounded" height="170px" alt="商品展示图片">
                                    <input type="hidden" id="imageUrl" value="${productEntity.imageUrl}" name="imageUrl">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-7">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 no-padding-right" for="fileUpload">上传图片</label>
                                    <div class="col-sm-8">
                                        <div class="clearfix">
                                            <input name="fileUpload" id="fileUpload" type="file" class="form-control">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-5">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 no-padding-right" for="merchantId">所属商家</label>
                                    <div class="col-sm-8">
                                        <div class="clearfix">
                                            <select id="merchantId" name="merchantId" class="form-control">
                                                <option value="">请选择...</option>
                                                <c:forEach items="${merchantEntity}" var="val">
                                                    <option value="${val.id}" ${productEntity.merchantId eq val.id ?'selected':''}>${val.merchant_name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-7">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 no-padding-right" for="productIndex">展示顺序</label>
                                    <div class="col-sm-8">
                                        <div class="clearfix">
                                            <input name="productIndex" id="productIndex" type="text" class="form-control" value="${productEntity.productIndex}" data-fv-notempty="true"
                                                   data-fv-message="商品展示顺序不能为空">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-5">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 no-padding-right">是否推荐</label>
                                    <div class="col-sm-8">
                                        <div class="clearfix">
                                            <c:choose>
                                                <c:when test="${productEntity.recommend eq 0}">
                                                    <label><input type="radio" value="0" name="recommend" class="flat-red" checked>&nbsp;否</label>&nbsp;&nbsp;
                                                    <label> <input type="radio" value="1" name="recommend" class="flat-red">&nbsp;是</label>
                                                </c:when>
                                                <c:otherwise>
                                                    <label><input type="radio" value="0" name="recommend" class="flat-red">&nbsp;否</label>&nbsp;&nbsp;
                                                    <label> <input type="radio" value="1" name="recommend" class="flat-red" checked>&nbsp;是</label>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-7">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 no-padding-right">新品上市标识</label>
                                    <div class="col-sm-8">
                                        <c:choose>
                                            <c:when test="${productEntity.newProductFlag eq 0}">
                                                <label><input type="radio" value="0" name="newProductFlag" class="flat-red" checked>&nbsp;否</label>&nbsp;&nbsp;
                                                <label> <input type="radio" value="1" name="newProductFlag" class="flat-red">&nbsp;是</label>
                                            </c:when>
                                            <c:otherwise>
                                                <label><input type="radio" value="0" name="newProductFlag" class="flat-red">&nbsp;否</label>&nbsp;&nbsp;
                                                <label><input type="radio" value="1" name="newProductFlag" class="flat-red" checked>&nbsp;是</label>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                            <%-- <div class="col-sm-5">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 no-padding-right">规格</label>
                                    <div class="col-sm-8">
                                        <c:choose>
                                            <c:when test="${productEntity.specification eq 0}">
                                                <label><input type="radio" value="0" name="specification" class="flat-red" checked>&nbsp;无规格</label>&nbsp;&nbsp;
                                                <label> <input type="radio" value="1" name="specification" class="flat-red" >&nbsp;有规格</label>
                                            </c:when>
                                            <c:otherwise>
                                                <label><input type="radio" value="0" name="specification" class="flat-red">&nbsp;无规格</label>&nbsp;&nbsp;
                                                <label> <input type="radio" value="1" name="specification" class="flat-red" checked>&nbsp;有规格</label>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div> --%>
                        </div>
                        <div class="row">
                            <div class="col-sm-7">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 no-padding-right" for="unitPrice">单价</label>
                                    <div class="col-sm-8">
                                        <input name="unitPrice" id="unitPrice" type="text" class="form-control" value="${productEntity.unitPrice}" data-fv-notempty="true"
                                               data-fv-message="商品展示单价不能为空">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-5">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 no-padding-right" for="promotionPrice">促销价</label>
                                    <div class="col-sm-8">
                                        <input name="promotionPrice" id="promotionPrice" type="text" class="form-control" value="${productEntity.promotionPrice}" placeholder="默认同单价...">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label class="control-label col-sm-2 no-padding-right" for="productIntroduce" style="width:19.3333333%">简介</label>
                                    <div class="col-sm-8" style="width:80.6666%">
                                        <textarea name="productIntroduce" id="productIntroduce" type="text" row="2" class="form-control">${productEntity.productIntroduce}</textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label class="control-label col-sm-2 no-padding-right" for="attention" style="width:19.3333333%">注意事项</label>
                                    <div class="col-sm-8" style="width:80.6666%">
                                        <textarea name="attention" id="attention" type="text" row="2" class="form-control">${productEntity.attention}</textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                       </div>
                    <%-- <div class="tab-pane disabled" id="tab_2">
                        <div class="form-group">
                            <label class="control-label col-sm-2 no-padding-right" for="spec">规格</label>
                            <div class="col-sm-10">
                                <select id="spec" name="spec" class="form-control" disabled>
                                    <option value="">请选择</option>
                                    <c:forEach items="${specList}" var="val">
                                        <option value="${val.id}" ${keyId eq val.id?'selected':''}>${val.attrName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <c:forEach items="${repoEndity}" var="val" varStatus="sta">
                            <div class="form-group" name="selfControl">
                                <label class="control-label col-sm-2 no-padding-right">${sta.index == 0 ?'规格值':''}</label>
                                <div class="col-sm-2">
                                    <input type="hidden" name="attrIds" value="${val.id}">
                                    <input type="hidden" name="repoIds" value="${val.repoId}">
                                    <input name="attrValues" type="text" value="${val.attrValue}" readonly class="form-control">
                                </div>
                                <label class="control-label col-sm-2 no-padding-right">价格</label>
                                <div class="col-sm-2">
                                    <input name="prices" type="text" class="form-control" value="${val.price}">
                                </div>
                                <label class="control-label col-sm-2 no-padding-right">库存</label>
                                <div class="col-sm-2">
                                    <input name="repos" type="text" class="form-control" value="${val.stock}">
                                </div>
                            </div>
                        </c:forEach>
                        
                        
                    </div>
                </div> --%>
            </div></div>
        </div><br/><br/>
        <!-- /.box-body -->
        <div class="box-footer" style="padding:0px">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button id="btnAdd" type="button" class="btn btn-success btn-sm">
                        <i class="fa fa-plus"></i>&nbsp;
                       		 保存
                    </button>
                    <button id="btn" type="button" onclick="closeModel()" class="btn btn-info btn-sm">
                        <i class="fa fa-close"></i>&nbsp;取消
                    </button>
                </div>
            </div>
        </div>
        <!-- /.box-footer -->
    </form>
</div>
<script>
    $(function(){
        $("#goodsForm").formValidation();
        /* $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass: 'iradio_flat-green'
        }); */
        $("#spec").change(function(){
            var $this=this;
            var id=this.value;
            if(id==""){
                $.each($("div[name='selfControl']"),function(i,item){
                    $(item).remove();
                });
                return;
            }
            $.each($("div[name='selfControl']"),function(i,item){
                $(item).remove();
            });
            $.ajax({
                method:'get',
                url:'${pageContext.request.contextPath}/goods/getAttrVal?id='+id,
                success:function(data){
                    var rs=JSON.parse(JSON.stringify(data));
                    $.each(rs,function(i,item){
                        var html='<div class="form-group" name="selfControl">';
                        if(i==0){
                            html+='<label class="control-label col-sm-2 no-padding-right">规格值</label>';
                        }else{
                            html+='<label class="control-label col-sm-2 no-padding-right"></label>';
                        }
                        html+='<div class="col-sm-2"><input type="hidden" name="attrIds" value="'+item.id+'"><input name="attrValues" type="text" value="'+item.attrValue+'" readonly class="form-control"></div>';
                        html+='<label class="control-label col-sm-2 no-padding-right">价格</label><div class="col-sm-2"><input name="prices" type="text" class="form-control"></div><label class="control-label col-sm-2 no-padding-right">库存</label><div class="col-sm-2"><input name="repos" type="text" class="form-control"></div>';
                        html+='</div>';
                        $($this).parent().parent().parent().append(html);
                    });
                }
            })
        });

        $('#fileUpload').uploadify({
            'debug'         : false,
            'auto'          : true,             //是否自动上传,
            'buttonText'    : '上传商品图片',       //按钮文字
            'height'        : 25,               //按钮高度
            'width'         : 100,              //按钮宽度
            'checkExisting' : 'false', //是否检测图片存在,不检测:false
            'fileObjName'   : 'files',           //默认 Filedata, $_FILES控件名称
            'fileSizeLimit' : '1024KB',          //文件大小限制 0为无限制 默认KB
            'fileTypeDesc'  : '*.jpg;*.jpeg;*.gif;*.png',       //图片选择描述
            'fileTypeExts'  : '*.gif; *.jpg; *.png',//文件后缀限制 默认：'*.*'
            'formData'      : {'someKey' : 'someValue', 'someOtherKey' : 1},//传输数据JSON格式
            //'overrideEvents': ['onUploadProgress'],  // The progress will not be updated
            //'progressData' : 'speed',             //默认percentage 进度显示方式
            'queueID'       : 'queue',              //默认队列ID
            'queueSizeLimit': 20,                   //一个队列上传文件数限制
            'removeCompleted' : true,               //完成时是否清除队列 默认true
            'removeTimeout'   : 3,                  //完成时清除队列显示秒数,默认3秒
            'requeueErrors'   : false,              //队列上传出错，是否继续回滚队列
            'successTimeout'  : 5,                  //上传超时
            'uploadLimit'     : 5,                 //允许上传的最多张数
            'swf'  : '${pageContext.request.contextPath}/plugins/uploadify/uploadify.swf', //swfUpload
            'uploader': '${pageContext.request.contextPath}/merchant/uploadProductFile', //服务器端脚本
            //修改formData数据
            'onUploadStart' : function(file) { },
            //删除时触发
            'onCancel' : function(file) { },
            //清除队列
            'onClearQueue' : function(queueItemCount) { },
            //调用destroy是触发
            'onDestroy' : function() {  },
            //每次初始化一个队列是触发
            'onInit' : function(instance){ },
            //上传成功
            'onUploadSuccess' : function(file, data, response) {
                var response=JSON.parse(data);
                if(response.status == '1'){
                    $('#showImg').attr('src',response.url)
                    $('#imageUrl').val(response.url);
                }else{
                    alert(response.msg);
                }
            },
            //上传错误
            'onUploadError' : function(file, errorCode, errorMsg, errorString) {
                alert(errorMsg);
            },
            //上传汇总
            'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
                /*   $('#progress').html(totalBytesUploaded + ' bytes uploaded of ' + totalBytesTotal + ' bytes.');  */
            },
            //上传完成
            'onUploadComplete' : function(file) {
            },
        });

        $("#btnAdd").click(function(){
            //before validate
            if($("input[name='specification']:checked").val()==0){
                commit('goodsForm', '/goods/add');
            }else {
                var flag = true;
                $.each($("input[name='prices']"), function (i, item) {
                    if ($(this).val() == "") {
                        layer.msg("规格对应的价格不能为空", {icon: 5});
                        $(this).focus();
                        flag = false;
                        return false;
                    }
                });
                if (!flag) {
                    return;
                }
                $.each($("input[name='repos']"), function (i, item) {
                    if ($(this).val() == "") {
                        layer.msg("规格对应的库存不能为空", {icon: 5});
                        $(this).focus();
                        flag = false;
                        return false;
                    }
                });
                if (flag) {
                    commit('goodsForm', '/goods/add');
                }
            }
        });
    });
</script>