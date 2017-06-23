<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css"/>

<div class="box box-info">
	<div class="box-header with-border"></div>
	<!-- /.box-header -->
	<!-- form start -->
	<form id="noteForm" name="noteForm" class="form-horizontal"
		role="form" method="post"
		data-fv-message="This value is not valid"
        data-fv-feedbackicons-valid="glyphicon glyphicon-ok"
        data-fv-feedbackicons-invalid="glyphicon glyphicon-remove"
        data-fv-feedbackicons-validating="glyphicon glyphicon-refresh">
        <input type="hidden" name="id" value="${note.id}"/>
        <div class="box-body" style="margin-left:2rem;margin-right:2rem;">
			<div class="row">
                <div class="col-md-6">
        			<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right"
							for="userName">用户名称</label>
						<div class="col-sm-3" style="margin-top:7px;">
							<input class="form-control" name="userName" id="userName" type="text" placeholder="用户名称..." value="${note.userName}"
							data-fv-notempty="true"
		                    data-fv-message="用户名称不能为空"/> &nbsp;&nbsp;<a href="javascript:selectUser()">选择</a>
						</div>
						<input type="hidden" name="userId" id="userId"/>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="shop_address">随笔文字内容</label>
						<div class="col-sm-6">
							<textarea id="content" name="content" rows="7" cols="60" maxlength="140"  data-fv-notempty="true" data-fv-message="随笔内容不能为空">${note.content}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="shop_address">随笔图片</label>
						<div class="col-sm-6" style="text-aling:left;">
							<c:forEach items="${picList}" var="pic">
							    <div style="float:left" id="pic_${pic.id}"><img src="${pic.atta_url}" style="width:150px; height=100px;"/> <a href="javascript:deleteAtta(${pic.id})">删除</a></div>
							</c:forEach>
						</div>
					</div>
                </div>
            </div>
            <!-- /.box-body -->
		<div class="box-footer">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button id="btnAdd" type="button" class="btn btn-success btn-sm" onclick="commit('noteForm','/note/saveNoteCon');">
						<i class="fa fa-user-plus"></i>&nbsp;保存
					</button>
					<!-- <button id="btnCopy" type="button" class="btn btn-danger btn-sm"><i class="fa fa-eye"></i>&nbsp;预览</button> -->
					<button id="btn" type="button" onclick="closeModel()" class="btn btn-info btn-sm">
						<i class="fa fa-undo"></i>&nbsp;返回
					</button>
				</div>
			</div>
		</div>
		</div>
	</form>		
</div>
<script type="text/javascript">
	$("#noteForm").formValidation();
	
	function selectUser() {
		alert(3)
	}

	function deleteComment(commentId) {
		layer.confirm('确认删除该评论吗？', {icon:3,title:'删除提示'},function(index, layero) {
			$.ajax({
	            type : "POST",
	            url : sys.rootPath + '/note/deleteComment',
	            data : {
	                "commentId" : commentId,
	                "noteId" : "${note.id}"
	            },
	            dataType : "json",
	            success : function(resultdata) {
	                if (resultdata.success) {
	                    layer.msg(resultdata.message, {
	                        icon : 1
	                    });
	                    $("#"+commentId).hide();
	                } else {
	                    layer.msg(resultdata.message, {
	                        icon : 5
	                    });
	                }
	            },
	            error : function(data, errorMsg) {
	                layer.msg(data.responseText, {icon : 2});
	            }
	        });
			
        });
	}
	
	function deleteAtta(attaId) {
		layer.confirm('确认删除该图片吗？', {icon:3,title:'删除提示'},function(index, layero) {
			$.ajax({
	            type : "POST",
	            url : sys.rootPath + '/note/deleteAtta',
	            data : {
	                "attaId" : attaId,
	                "noteId" : "${note.id}"
	            },
	            dataType : "json",
	            success : function(resultdata) {
	                if (resultdata.success) {
	                    layer.msg(resultdata.message, {
	                        icon : 1
	                    });
	                    $("#pic_"+attaId).hide();
	                } else {
	                    layer.msg(resultdata.message, {
	                        icon : 5
	                    });
	                }
	            },
	            error : function(data, errorMsg) {
	                layer.msg(data.responseText, {icon : 2});
	            }
	        });
			
        });
	}
</script>