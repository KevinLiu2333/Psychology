<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@page import="java.security.MessageDigest"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%! private String byteArr2HexStr(byte[] arrB)
{
    int iLen = arrB.length;
    //每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
    StringBuffer sb = new StringBuffer(iLen * 2);
    for (int i = 0; i < iLen; i++)
    {
        int intTmp = arrB[i];
        //把负数转换为正数
        while (intTmp < 0){
            intTmp = intTmp + 256;
        }
        //小于0F的数需要在前面补0
        if (intTmp < 16){
            sb.append("0");
        }
        sb.append(Integer.toString(intTmp, 16));
    }
    return sb.toString();
} %>
<%
	MessageDigest md = MessageDigest.getInstance("md5");
	md.update(session.getId().getBytes());
	String winName="cayth"+byteArr2HexStr(md.digest());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>无标题文档</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="JavaScript">
	function CloseWin(){	
		if(window.name=='member') {
			return;
		}
		var ua=navigator.userAgent;
		var ie=navigator.appName=="Microsoft Internet Explorer"?true:false;
		if(ie){
			var IEversion=parseFloat(ua.substring(ua.indexOf("MSIE ")+5,ua.indexOf(";",ua.indexOf("MSIE "))));
			if(IEversion< 5.5){
				var str = '<object id=noTipClose classid="clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11">';
				str += '<param name="Command" value="Close"></object>';
				document.body.insertAdjacentHTML("beforeEnd", str);
				document.all.noTipClose.Click();
			}
			else{
				var isMinIE6 = IEversion >= 6;  
			    var isMinIE7 = IEversion >= 7;
			    if(isMinIE7){//IE7以上
			    	window.open('','_top');
					window.close();
					return ;
		        } else if(isMinIE6){//IE6 
					window.opener=null;
					window.close();
					return ;
		        } else {
		        	window.close();
					return;
		        }
			}
		} else{
			window.close();
			return;
		}
	}
	
	function loadCenter(){
		var winName='<%=winName%>';
		if(window.name!=winName&&(window.name==""||(window.name.length>5&&window.name.substr(0,5)!="cayth"))){
			var win=window.open('<c:url value="/blank.jsp"/>',winName,'width=600,height=480,scrollbars=no,status=yes,resizable=no');
			wForm.submit();
			win.moveTo(0, 0);
			win.resizeTo(screen.availWidth,screen.availHeight);
			CloseWin();	
		}else{
			location.replace('<c:url value="/redirect.jsp"/>');
		}
	}
</script>  	
</head>
<body onload="loadCenter();" bgcolor="#FFFFFF" text="#000000">
 	<form  name="wForm" action='<c:url value="/redirect.jsp"/>' target='<%=winName%>'></form>
</body>
</html>
