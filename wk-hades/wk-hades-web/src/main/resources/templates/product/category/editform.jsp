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
    <form id="categoryForm" name="categoryForm" class="form-horizontal" role="form" method="post"
          data-fv-message="This value is not valid"
          data-fv-feedbackicons-valid="glyphicon glyphicon-ok"
          data-fv-feedbackicons-invalid="glyphicon glyphicon-remove"
          data-fv-feedbackicons-validating="glyphicon glyphicon-refresh">
        <input type="hidden" name="id" id="roleId" value="${categoryEntity.id }">
        <div class="box-body">
            <div class="form-group">
                <label class="control-label col-sm-2 no-padding-right" for="categoryName">类别名称</label>
                <div class="col-sm-10">
                    <div class="clearfix">
                        <input class="form-control" name="categoryName" value="${categoryEntity.categoryName}" id="categoryName" type="text" placeholder="类别名称..."
                               data-fv-notempty="true"
                               data-fv-message="类别名称不能为空"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2 no-padding-right" for="categoryDesc">类别描述</label>
                <div class="col-sm-10">
                    <div class="clearfix">
                        <textarea class="form-control" name="categoryDesc" id="categoryDesc" rows="3" placeholder="类别描述...">${categoryEntity.categoryDesc}</textarea>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.box-body -->
        <div class="box-footer">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button id="btnAdd" type="button" onclick="commit('categoryForm','/category/edit');" class="btn btn-success btn-sm">
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
    $(function(){
        $("#categoryForm").formValidation();
    });
</script>