<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<% request.setAttribute("seatStatusMap", com.zmh.coffee.common.Constant.SEAT_STATUS_MAP); %> 
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
                <label class="control-label col-sm-2 no-padding-right" for="attrName">人数</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                       ${seat.peopleNum}
                    </div>
                </div>
                <label class="control-label col-sm-2 no-padding-right" for="attrName">时间</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                       <fmt:formatDate value="${seat.date}" type="both"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2 no-padding-right" for="attrName">姓氏</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                       ${seat.surname}
                    </div>
                </div>
                <label class="control-label col-sm-2 no-padding-right" for="attrName">性别</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                       ${seat.sex == "1" ? "男" : "女"}
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2 no-padding-right" for="attrName">手机号码</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                       ${seat.mobile}
                    </div>
                </div>
                <label class="control-label col-sm-2 no-padding-right" for="attrName">备注</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                       ${seat.note}
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2 no-padding-right" for="attrName">是否帮他人订</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                       ${seat.helpOrder ? "是" : "否"}
                    </div>
                </div>
                <label class="control-label col-sm-2 no-padding-right" for="attrName">用餐人姓氏</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                       ${seat.dinerSurname}
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2 no-padding-right" for="attrName">用餐人性别</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                       ${seat.dinerSex == "1" ? "男" : "女"}
                    </div>
                </div>
                <label class="control-label col-sm-2 no-padding-right" for="attrName">用餐人手机号</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                       ${seat.dinerMobile}
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2 no-padding-right" for="attrName">状态</label>
                <div class="col-sm-10">
                    <div class="clearfix">
                       <c:forEach items="${seatStatusMap}" var="status">
                       		<c:if test="${status.key == seat.status}">
                       			${status.value}
                       		</c:if>
                       </c:forEach>
                    </div>
                </div>
            </div>
          </div>  
        <!-- /.box-body -->
        <div class="box-footer">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                	<c:if test='${seat.status=="1"}'>
	                    <button id="btnAdd" type="button" onclick="setStatus('3');" class="btn btn-success btn-sm">
	                        <i class="fa fa-edit"></i>&nbsp; 接受
	                    </button>
	                    <button id="btnAdd" type="button" onclick="setStatus('4');" class="btn btn-success btn-sm">
	                        <i class="fa fa-edit"></i>&nbsp; 拒绝
	                    </button>
                	</c:if>
                    <button id="btn" type="button" onclick="closeModel()" class="btn btn-info btn-sm">
                        <i class="fa fa-close"></i>&nbsp;取消
                    </button>
                </div>
            </div>
        </div>
        <!-- /.box-footer -->
    </form>
</div>
<script type="text/javascript">
	function setStatus(status) {
		var msg;
		if (status == "3") {
			msg = "确定接受吗？";
		} else {
			msg = "确定拒绝吗？"
		}
		layer.confirm(msg, {icon: 3, title:'提示'}, function(index){
            var data = "id=" + ${seat.id} + "&status=" + status;
			$.ajax({
                type: "POST",
                url: "setStatus",
                data: data,
                success: function(msg){
                	if (msg.success) {
                		alert("成功");
                		layer.close(index);
                	} else {
                		alert("失败");
                	}
                }
            });
        });
	}
</script>