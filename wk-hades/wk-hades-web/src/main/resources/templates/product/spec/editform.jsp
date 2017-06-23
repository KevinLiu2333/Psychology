<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/22
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Horizontal Form -->
<div class="box box-info">
    <div class="box-header with-border">
    </div>
    <!-- /.box-header -->
    <!-- form start -->
    <form id="specifyForm" name="specifyForm" class="form-horizontal" role="form" method="post"
          data-fv-message="This value is not valid"
          data-fv-feedbackicons-valid="glyphicon glyphicon-ok"
          data-fv-feedbackicons-invalid="glyphicon glyphicon-remove"
          data-fv-feedbackicons-validating="glyphicon glyphicon-refresh">
        <input type="hidden" name="id" id="id" value="${specifyEntity.id }">
        <div class="box-body">
            <div class="form-group">
                <label class="control-label col-sm-2 no-padding-right" for="attrName">规格名称</label>
                <div class="col-sm-10">
                    <div class="clearfix">
                        <input class="form-control" name="attrName" value="${specifyEntity.attrName}" id="attrName" type="text" placeholder="规格名称..."
                               data-fv-notempty="true"
                               data-fv-message="规格名称不能为空"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2 no-padding-right" for="attrDesc">规格描述</label>
                <div class="col-sm-10">
                    <div class="clearfix">
                        <textarea class="form-control" name="attrDesc" id="attrDesc" rows="3" placeholder="规格描述...">${specifyEntity.attrDesc}</textarea>
                    </div>
                </div>
            </div>
            <c:forEach var="item" items="${attrValueEntity}" varStatus="st">
                <div class="form-group">
                    <label class="control-label col-sm-2 no-padding-right">${st.index==0?'规格值名称':''}</label>
                    <div class="col-sm-1">
                        <c:if test="${st.index==0}"><button type="button" class="btn btn-info btn-sm" onclick="addInput(this)">添加</button></c:if>
                    </div>
                    <div class="col-sm-9">
                        <input type="hidden" name="valIds" id="valIds" value="${item.id}" />
                        <input class="form-control" name="attrNames" value="${item.attrValue}" type="text" placeholder="规格值名称..."
                               data-fv-notempty="true"
                               data-fv-message="规格值名称不能为空"/>
                    </div>
                </div>
            </c:forEach>
        </div>
        <!-- /.box-body -->
        <div class="box-footer">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button id="btnAdd" type="button" onclick="commit('specifyForm','/spec/edit');" class="btn btn-success btn-sm">
                        <i class="fa fa-edit"></i>&nbsp;
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
    function addInput(obj){
        var length=$("input[name='attrNames']").length;
        var len=$("input[name='attrNameNew']").length;
        if(length+len>3){
            layer.msg('最多只能添加4个!',{icon:0});
            return;
        }
        var html='<div class="form-group"><label class="control-label col-sm-2 no-padding-right"></label><div class="col-sm-1"><button type="button" class="btn btn-danger btn-sm" onclick="deleteInput(this)">删除</button></div><div class="col-sm-9"><input class="form-control" name="attrNameNew" type="text" placeholder="规格值名称..."/></div></div>';
        $(obj).parent().parent().parent().append(html);
        resetModel();
    }
    function deleteInput(obj){
        $(obj).parent().parent().remove();
    }
    $(function(){
        resetModel();
        $("#specifyForm").formValidation();
    })
</script>