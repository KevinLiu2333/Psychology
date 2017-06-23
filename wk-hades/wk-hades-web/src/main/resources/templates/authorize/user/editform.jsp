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
  <form id="userForm" name="userForm" class="form-horizontal" role="form" method="post"
        data-fv-message="This value is not valid"
        data-fv-feedbackicons-valid="glyphicon glyphicon-ok"
        data-fv-feedbackicons-invalid="glyphicon glyphicon-remove"
        data-fv-feedbackicons-validating="glyphicon glyphicon-refresh">
    <input type="hidden" name="id" id="userId" value="${userEny.id }">
    <input type="hidden" name="userInfo.id" value="${userEny.id }">
    <div class="box-body">
      <div class="form-group">
        <label class="control-label col-sm-2 no-padding-right" for="accountName">登录名</label>
        <div class="col-sm-4">
            <input readonly class="form-control" value="${userEny.accountName }" name="accountName" id="accountName" type="text" placeholder="登录名做为用户登录系统的账户..."
                   data-fv-notempty="true"
                   data-fv-message="登录名不能为空"/>
        </div>
        <label class="control-label col-sm-2 no-padding-right" for="userName">真实姓名</label>
        <div class="col-sm-4">
            <input class="form-control" value="${userEny.userName }" name="userName" id="userName" type="text" placeholder="真实姓名..."
                   data-fv-notempty="true"
                   data-fv-message="真实姓名不能为空"/>
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-2 no-padding-right" for="userName">所属角色</label>
        <div class="col-sm-10">
            <select class="form-control" name="role.id" id="roleId" style="width: 100%">
              <option value="">请选择角色...</option>
              <c:forEach var="role" items="${roleList }">
               		<option value="${role.id }" <c:if test="${userEny.role.id eq role.id}">selected="selected"</c:if>>${role.name }</option>
              </c:forEach>
            </select>
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-2 no-padding-right" for="description">用户描述</label>
        <div class="col-sm-10">
          <textarea class="form-control" name="description" id="description" rows="3">${userEny.description }</textarea>
        </div>
      </div>
    </div>
    <!-- /.box-body -->
    <div class="box-footer">
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button id="btnAdd" type="button" onclick="commit('userForm','/user/edit');" class="btn btn-success btn-sm">
            <i class="fa fa-user-plus"></i>&nbsp;保存
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
    $("#userForm").formValidation();
  });
</script>