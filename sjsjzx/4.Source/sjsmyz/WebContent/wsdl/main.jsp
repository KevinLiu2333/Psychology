<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>WEBSERVICE</title>
</head>

<frameset rows="48,*" cols="*" marginwidth="1" marginheight="1" scrolling="yes" frameborder="1">  
  <frame src="wsdlurl.jsp" name="topFrame" marginwidth="1" marginheight="1" scrolling="yes" frameborder="1"/>
  <frameset rows="*" cols="300,*" marginwidth="1" marginheight="1" scrolling="yes" frameborder="1">
    <frame src="operation.jsp" name="leftFrame" marginwidth="1" marginheight="1" scrolling="yes" frameborder="1"/>
    <frameset rows="200,*" cols="*" marginwidth="1" marginheight="1" scrolling="yes" frameborder="1">
	  <frame src="parameter.jsp" name="upFrame" marginwidth="1" marginheight="1" scrolling="yes" frameborder="1" />
	  <frame src="result.jsp" name="downFrame" marginwidth="1" marginheight="1" scrolling="yes" frameborder="1" />
	</frameset>
  </frameset>
</frameset>
<noframes>
<body></body>
</noframes>
</html>
