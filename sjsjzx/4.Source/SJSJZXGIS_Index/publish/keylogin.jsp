<%@ page contentType="text/html;charset=GBK"%>
<%@ page import="com.wonders.ca.ValidHelper"%>
<%
ValidHelper vh	=	new ValidHelper();
String strpassword	=	request.getParameter("securitykey");
String strRandom	=	vh.getValidKey();
%>
<script language="javascript" src="jquery-1.4.2.min.js"></script>
<script languang="javascript">	

	function GetErrCode(safeEngine){
	var errcode	=	safeEngine.ErrorCode;
	var result = 'usb key认证失败，请与管理员联系！';
	switch (errcode)
	{
		case -2113667072 :
		    result = "装载动态库错误(-2113667072)";
			break;
		case -2113667071 :
		    result = "内存分配错误(-2113667071)";
			break;
		case -2113667070 :
		    result = "读私钥设备错误(-2113667070)";
			break;
		case -2113667069 :
		    result = "私钥密码错误(-2113667069)";
			break;
		case -2113667068 :
		    result = "读证书链设备错误(-2113667068)";
			break;
		case -2113667067 :
		    result = "证书链密码错误(-2113667067)";
			break;
		case -2113667066 :
		    result = "读证书设备错误(-2113667066)";
			break;
		case -2113667065 :
		    result = "证书密码错误(-2113667065)";
			break;
		case -2113667064 :
		    result = "私钥超时(-2113667064)";
			break;
		case -2113667063 :
		    result = "缓冲区太小(-2113667063)";
			break;
		case -2113667062 :
		    result = "初始化环境错误(-2113667062)";
			break;
		case -2113667061 :
		    result = "清除环境错误(-2113667061)";
			break;
		case -2113667060 :
		    result = "数字签名错误(-2113667060)";
			break;
		case -2113667059 :
		    result = "验证签名错误(-2113667059)";
			break;
		case -2113667058 :
		    result = "摘要错误(-2113667058)";
			break;
		case -2113667057 :
		    result = "证书格式错误(-2113667057)";
			break;
		case -2113667056 :
		    result = "数字信封错误(-2113667056)";
			break;
		case -2113667055 :
		    result = "从LDAP获取证书错误(-2113667055)";
			break;
		case -2113667054 :
		    result = "证书已过期(-2113667054)";
			break;
		case -2113667053 :
		    result = "获取证书链错误(-2113667053)";
			break;
		case -2113667052 :
		    result = "证书链格式错误(-2113667052)";
			break;
		case -2113667051 :
		    result = "验证证书链错误(-2113667051)";
			break;
		case -2113667050 :
		    result = "证书已废除(-2113667050)";
			break;
		case -2113667049 :
		    result = "CRL格式错误(-2113667049)";
			break;
		case -2113667048 :
		    result = "连接OCSP服务器错误(-2113667048)";
			break;
		case -2113667047 :
		    result = "OCSP请求编码错误(-2113667047)";
			break;
		case -2113667046 :
		    result = "OCSP回包错误(-2113667046)";
			break;
		case -2113667045 :
		    result = "OCSP回包格式错误(-2113667045)";
			break;
		case -2113667044 :
		    result = "OCSP回包过期(-2113667044)";
			break;
		case -2113667043 :
		    result = "OCSP回包验证签名错误(-2113667043)";
			break;
		case -2113667042 :
		    result = "证书状态未知(-2113667042)";
			break;
		case -2113667041 :
		    result = "对称加解密错误(-2113667041)";
			break;
		case -2113667040 :
		    result = "获取证书信息错误(-2113667040)";
			break;
		case -2113667039 :
		    result = "获取证书细目错误(-2113667039)";
			break;
		case -2113667038 :
		    result = "获取证书唯一标识错误(-2113667038)";
			break;
		case -2113667037 :
		    result = "获取证书扩展项错误(-2113667037)";
			break;
		case -2113667036 :
		    result = "PEM编码错误(-2113667036)";
			break;
		case -2113667035 :
		    result = "PEM解码错误(-2113667035)";
			break;
		case -2113667034 :
		    result = "产生随机数错误(-2113667034)";
			break;
		case -2113667033 :
		    result = "PKCS12参数错误(-2113667033)";
			break;m
		case -2113667032 :
		    result = "私钥格式错误(-2113667032)";
			break;
		case -2113667031 :
		    result = "公私钥不匹配(-2113667031)";
			break;
		case -2113667030 :
		    result = "PKCS12编码错误(-2113667030)";
			break;
		case -2113667029 :
		    result = "PKCS12格式错误(-2113667029)";
			break;
		case -2113667028 :
		    result = "PKCS12解码错误(-2113667028)";
			break;
		case -2113667027 :
		    result = "非对称加解密错误(-2113667027)";
			break;
		case -2113667026 :
		    result = "OID格式错误(-2113667026)";
			break;
		case -2113667025 :
		    result = "LDAP地址格式错误(-2113667025)";
			break;
		case -2113667024 :
		    result = "LDAP地址错误(-2113667024)";
			break;
		case -2113667023 :
		    result = "连接LDAP服务器错误(-2113667023)";
			break;m
		case -2113667022 :
		    result = "LDAP绑定错误(-2113667022)";
			break;
		case -2113667021 :
		    result = "没有OID对应的扩展项(-2113667021)";
			break;
		case -2113667020 :
		    result = "获取证书级别错误(-2113667020)";
			break;
		case -2113667019 :
		    result = "读取配置文件错误(-2113667019)";
			break;
		case -2113667018 :
		    result = "私钥未载入(-2113667018)";
			break;
	// 以下错误用于登录
		case -2113666824 :
		    result = "无效的登录凭证(-2113666824)";
			break;
		case -2113666823 :
		    result = "参数错误(-2113666823)";
			break;
		case -2113666822 :
		    result = "不是服务器证书(-2113666822)";
			break;
		case -2113666821 :
		    result = "登录错误(-2113666821)";
			break;
		case -2113666820 :
		    result = "证书验证方式错误(-2113666820)";
			break;
		case -2113666819 :
		    result = "随机数验证错误(-2113666819)";
			break;
		case -2113666818 :
		    result = "与单点登录客户端代理通信(-2113666818)";
		break;
		case -2113666991:
				result = "无法读取usb key中的信息(-2113666991)";
		break;
	}
	 
  /*  result = "数字证书错误：" + result;*/
	 safeEngine.SEH_ClearSession();
	 alert("usb key认证发生错误:\n" + result);
	return result;
}


function keyLogin(){
	var safeEngine	=	document.getElementById("SafeEngineCtl");
	var strpripath="com1";
	var DevNumber	=	"9";
	var strpassword='<%=strpassword%>';//document.getElementById("prikeypwd").value;
	var strcertchainpath="com1";
	var strcertpath="com1";

	if(strpassword==""){
		alert("数字证书（usb key）保护密码无效！");
		return;
	}
	
	//初始化函数
	safeEngine.SEH_InitialSession(DevNumber,strpripath, strpassword, 0, DevNumber, strcertchainpath, "");
	if(safeEngine.ErrorCode!=0)
	{
		GetErrCode(safeEngine);
		return;
	}
	
	//获取随机数
	var strRandom = '<%=strRandom%>';
	document.getElementById("strRandom").value	=	strRandom;
	
	//使用数字证书对随机数进行签名
	var strSigned = safeEngine.SEH_SignData(strRandom, 3);	
	if(safeEngine.ErrorCode!=0)
	{
		GetErrCode(safeEngine);
		return;
	}
	document.getElementById("strSigned").value	=	strSigned;
	//获取证书内容
	var strCert = safeEngine.SEH_GetSelfCertificate(DevNumber, strcertpath, "");	
	if(safeEngine.ErrorCode!=0)
	{
		GetErrCode(safeEngine);
		return;
	}
	document.getElementById("strCert").value	=	strCert;
	
	//使用证书内容或去证书序列号，作为证书的唯一标识
	var strItemVal = safeEngine.SEH_GetCertDetail(strCert, 2);
	if(safeEngine.ErrorCode!=0)
	{
		GetErrCode(safeEngine);
		return;
	}
	document.getElementById("strItemVal").value	=	strItemVal;
	document.getElementById("loginType").value = "caLogin";
	document.getElementById("keyLoginForm").submit();
	
}

  $(document).ready(function() 
    {
        keyLogin();  
    }
 );	

</script>

<p align="center" style="display:none">
<OBJECT ID="SafeEngineCtl" CLASSID="CLSID:B48B9648-E9F0-48A3-90A5-8C588CE0898F" width="300" height="50" border=0 ></OBJECT>
</p>
<p align="center" style="display:none">
<form name="keyLoginForm" id="keyLoginForm" action="./servlet/Login" method="post">
<input name="strRandom" type="hidden" id="strRandom" value="" />
<input name="strSigned" type="hidden" id="strSigned" value="" />
<input name="strItemVal" type="hidden" id="strItemVal" value="" />
<input name="strCert" type="hidden" id="strCert" value="" />
<input type="hidden" id="loginType" name="loginType" value="">
</form>
</p>



