<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/22
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-iconpicker/css/bootstrap-iconpicker.min.css"/>
<!-- Horizontal Form -->
<div class="box box-info">
    <div class="box-header with-border">
    </div>
    <!-- /.box-header -->
    <!-- form start -->
    <form id="resourceForm" name="resourceForm" class="form-horizontal" role="form" method="post"
          data-fv-message="This value is not valid"
          data-fv-feedbackicons-valid="glyphicon glyphicon-ok"
          data-fv-feedbackicons-invalid="glyphicon glyphicon-remove"
          data-fv-feedbackicons-validating="glyphicon glyphicon-refresh">
        <input type="hidden" name="id" id="resourceId" value="${resourceEntity.id }">
        <input type="hidden" name="isHide" value="${resourceEntity.isHide }">
        <div class="box-body">
            <div class="form-group">
                <label class="control-label col-sm-2 no-padding-right" for="type">资源类型</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                        <select class="form-control" id="type" name="type"style="width: 100%">
                            <option value="0"
                                    <c:if test="${resourceEntity.type eq 0 }">selected="selected"</c:if>>菜单</option>
                            <option value="1"
                                    <c:if test="${resourceEntity.type eq 1 }">selected="selected"</c:if>>按钮</option>
                        </select>
                    </div>
                </div>
                <label class="control-label col-sm-2 no-padding-right" for="name">上级名称</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                        <select class="form-control" name="parentId" id="parentId"
                                style="width: 100%">
                            <option value="">请选择...</option>
                            <option value=" " <c:if test="${resourceEntity.parentId eq null}">selected="selected"</c:if>>目录菜单</option>
                            <c:forEach var="item" items="${select2List }">
                                <option value="${item.id }"
                                        <c:if test="${resourceEntity.parentId eq item.id}">selected="selected"</c:if>>${item.text }</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2 no-padding-right" for="name">资源名称</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                        <input class="form-control" value="${resourceEntity.name }" name="name" id="name" type="text"  placeholder="资源名称..."
                               data-fv-notempty="true"
                               data-fv-message="资源名称不能为空"/>
                    </div>
                </div>
                <label class="control-label col-sm-2 no-padding-right" for="sourceKey">资源标识</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                        <input class="form-control" name="sourceKey" id="sourceKey" value="${resourceEntity.sourceKey }"
                               type="text"  placeholder="资源标识..."
                               data-fv-notempty="true"
                               data-fv-message="资源标识不能为空"/>
                    </div>
                </div>
            </div>
            <div class="form-group" id="sourceUrlDiv" <c:if test="${resourceEntity.parentId eq null}">style="display: none;"</c:if>>
                <label class="control-label col-sm-2 no-padding-right"
                       for="sourceUrl">资源URL</label>
                <div class="col-sm-10">
                    <div class="clearfix">
                        <input class="form-control" name="sourceUrl" id="sourceUrl"
                               type="text" placeholder="菜单URL..."
                               data-fv-notempty="true"
                               data-fv-message="菜单URL不能为空"/>
                    </div>
                </div>
            </div>
            <div id="iconDiv" class="form-group">
                <label class="control-label col-sm-2 no-padding-right" for="icon">菜单图标</label>
                <div class="col-sm-10">
                    <div class="input-group">
                        <input type="text" class="form-control" name="icon" value="${resourceEntity.icon }" id="icon" placeholder="请选择菜单图标...">
                        <span class="input-group-btn">
                            <button type="button" class="btn btn-default" name="iconpicker" id="iconpicker" data-iconset="fontawesome" data-icon="${resourceEntity.icon }" role="iconpicker"/>
                        </span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2 no-padding-right"
                       for="description">资源描述</label>
                <div class="col-sm-10">
                    <div class="clearfix">
                        <textarea class="form-control" name="description" id="description" rows="3" placeholder="资源描述...">${resourceEntity.description }</textarea>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.box-body -->
        <div class="box-footer">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button id="btnAdd" type="button" onclick="commit('resourceForm','/resource/edit');" class="btn btn-success btn-sm">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-iconpicker/js/iconset/iconset-all.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-iconpicker/js/bootstrap-iconpicker.min.js"></script>
<script>
    $(function(){
        $("#resourceForm").formValidation();
    });
</script>