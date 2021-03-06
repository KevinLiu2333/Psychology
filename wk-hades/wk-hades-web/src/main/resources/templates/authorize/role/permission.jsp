<%--
  Created by IntelliJ IDEA.
  User: miaoto1
  Date: 2016/5/25
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/plugins/jstree/themes/default/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/plugins/jstree/jstree.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/plugins/jstree/jstree.checkbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/underscore-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/role/permission.js"></script>


<div class="box box-info">
    <div class="box-header with-border">

    </div>
    <form id="permissionForm" name="permissionForm" class="form-horizontal" role="form" method="post">
        <div class="box-body">
                <input type="hidden" id="id" name="id" value="${roleEntity.id }">
                <!-- 资源树 -->
                <div class="form-group">
                    <label class="control-label col-sm-2 no-padding-right">资源</label>
                    <div class="col-sm-4">
                        <div id="tree" style="border:1px solid rgb(166,169,172);"></div>
                    </div>
                    <label class="control-label col-sm-2 no-padding-right">已有资源</label>
                    <div class="col-sm-4">
                        <select multiple="multiple" class="form-control" name="alChooseRes" style="height: 400px"></select>
                    </div>
                </div>
        </div>
        <!-- /.box-body -->
        <div class="box-footer">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button id="btnAdd" type="button" onclick="commitPerm('permissionForm','/role/permission');" class="btn btn-success btn-sm">
                        <i class="fa fa-user-plus"></i>&nbsp;
                        保存
                    </button>
                    <button id="btn" type="button" onclick="closeModel()" class="btn btn-info btn-sm">
                        <i class="fa fa-close"></i>&nbsp;取消
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>


