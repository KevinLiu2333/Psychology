<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>${sys_title}</title>
  <%@ include file="/cj/meta.jsp" %>
  <!-- Loading Bootstrap -->
  <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Loading Flat UI -->
  <link href="${ctx}/wddc/tiles/flatui/css/flat-ui.css" rel="stylesheet">

  <!-- Loading md5 -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/md5.js"></script>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <script type="text/javascript">
        function login(){
            var password = $("#login-pass").val();
            $("#password").val(hex_md5(password));
            $("#login-forms").submit();
        }

    </script>
</head>
<body style="background-color:#3E607B">
<div class="container" >

  <div class="login">
    <div class="login-screen">
      <div class="login-icon">
        <img src="${ctx}/wddc/skins/images/icon11.png" width="485px" height="155px" alt="" />
      </div>
        <form method="post" action="${ctx}/login"  id="login-forms">
        <input type="hidden" name="password" id="password" value=""/>
          <div class="login-form">
            <div class="form-group">
              <input type="text" class="form-control login-field" value="" placeholder="请输入用户名" id="login-name" name="logonName"/>
              <label class="login-field-icon fui-user" for="login-name"></label>
            </div>

            <div class="form-group">
              <input type="password" class="form-control login-field" value="" placeholder="请输入密码" id="login-pass" />
              <label class="login-field-icon fui-lock" for="login-pass"></label>
            </div>

            <a class="btn btn-primary btn-lg btn-block" href="javascript:login();">登  录</a>
            <a class="login-link" href="#">忘记密码?</a>
            <c:if test="${ not empty obj.errMsg}">
              <span class="login-link" ><font color="red">${obj.errMsg}</font></span>
        	  <input type="hidden" name="jumpUrl" id="jumpUrl" value="${obj.jumpUrl}"/>
            </c:if>
          </div>
        </form>
    </div>
  </div>
</div>
</body>
</html>
