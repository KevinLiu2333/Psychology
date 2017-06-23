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
						<label class="control-label col-sm-5 no-padding-right">用户名称</label>
						<div class="col-sm-2" style="margin-top:7px;">
							${note.userName}
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="contact">发表时间</label>
						<div class="col-sm-3" style="margin-top:7px; margin-left:-10px;">
							<fmt:formatDate value="${note.publishDate}"  type="both" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="telephone">点赞数</label>
						<div class="col-sm-1">
							${note.likeCount}
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="shop_address">收藏数</label>
						<div class="col-sm-1">
							${note.collectCount}
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="shop_address">评论数</label>
						<div class="col-sm-1">
							${note.commentCount}
						</div>
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
                <div class="col-md-6" style="text-align:left;">
                	<table class="table table-striped">
						<caption>评论列表</caption>
						<thead>
							<tr>
								<th>评论来源</th>
								<th>评论内容</th>
								<th>评论时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${commentList}" var="comment">
							<tr id="${comment.id}">
								<td>
								    <c:if test="${comment.type == '1' }">
								    	${comment.userName}
								    </c:if>
								    <c:if test="${comment.type == '2'}">
								    	${comment.userName} <p style="color:green">回复</p>${comment.repliedUserName}
								    </c:if>
							    </td>
							    <td>
							    	${comment.comment}
							    </td>
							    <td>
							    	<fmt:formatDate value="${comment.commentDate}"  type="both" />
							    </td>
							    <td>
							    	<a href="javascript:deleteComment(${comment.id})">删除</a>
							    </td>
							 </tr>
							</c:forEach>
						</tbody>
					</table>
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