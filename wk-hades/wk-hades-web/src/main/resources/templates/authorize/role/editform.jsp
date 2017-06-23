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
  <form id="roleForm" name="roleForm" class="form-horizontal" role="form" method="post"
        data-fv-message="This value is not valid"
        data-fv-feedbackicons-valid="glyphicon glyphicon-ok"
        data-fv-feedbackicons-invalid="glyphicon glyphicon-remove"
        data-fv-feedbackicons-validating="glyphicon glyphicon-refresh">
    <input type="hidden" name="id" id="roleId" value="${roleEntity.id }">
    <div class="box-body">
      <div class="form-group">
        <label class="control-label col-sm-2 no-padding-right" for="name">角色名称</label>
        <div class="col-sm-4">
            <input class="form-control" value="${roleEntity.name }" name="name" id="name" type="text" placeholder="角色名称..."
                   data-fv-notempty="true"
                   data-fv-message="角色名称不能为空"/>
        </div>
        <label class="control-label col-sm-2 no-padding-right" for="key">角色标识</label>
        <div class="col-sm-4">
          <div class="clearfix">
            <input class="form-control" value="${roleEntity.key }" name="key" id="key" type="text" placeholder="角色标识..."
                   data-fv-notempty="true"
                   data-fv-message="角色标识不能为空"/>
          </div>
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-2 no-padding-right" for="description">角色描述</label>
        <div class="col-sm-10">
          <div class="clearfix">
            <textarea class="form-control" name="description" id="description" rows="3" placeholder="角色描述...">${roleEntity.description }</textarea>
          </div>
        </div>
      </div>
    </div>
    <!-- /.box-body -->
    <div class="box-footer">
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button id="btnAdd" type="button" onclick="commit('roleForm','/role/edit');" class="btn btn-success btn-sm">
            <i class="fa fa-user-plus"></i>&nbsp;
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
    $("#roleForm").formValidation();
  });
</script>