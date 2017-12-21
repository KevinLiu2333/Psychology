package com.wonders.ca;

import com.sheca.safeengine.javasafeengine;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.util.UUID;


@At("/ca")
public class CaAt {

    /**
     * 用于处理CA认证,给予客户端一个随机值.
     */
    @At("/getId")
    @Ok("JSON")
    public String getId(HttpSession session,String password){
        UUID uuid = UUID.randomUUID();
        String strId = uuid.toString();
        session.setAttribute("UUID", strId);
        session.setAttribute("keypsd", password);
        return strId;
    }

    /**
     * 检查用户是否符合要求.
     * @param req 请求.
     * @param cert 证书.
     * @param signed 签名随机数.
     * @param itemVal 签名.
     * @return String 成功返回任意值.
     */
    @At("/checker")
    @Ok("JSON")
    public String checker(HttpServletRequest req,HttpSession session,String cert,String signed,String itemVal){
        if(signed == null || signed.isEmpty()){
            return null;
        }
        if(cert == null || cert.isEmpty()){
            return null;
        }
        javasafeengine oSE = new javasafeengine();
        byte[] bSigned = oSE.base64Decode(signed);
        byte[] bCert = oSE.base64Decode(cert);
        //byte[] bItemVal = oSE.base64Decode(itemVal);
        FileInputStream oFile = null;
        byte[] bChain = null;
        int iFile = 0;
        try{
            String path = req.getSession().getServletContext().getRealPath("") + "/WEB-INF/ca/CertChain.spc";
            oFile = new FileInputStream(path);
            iFile = oFile.available();
            bChain = new byte[iFile];
            iFile = oFile.read(bChain);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }finally{
            try{
                if(oFile != null){
                    oFile.close();
                }
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
            }
        }
        int iRet=oSE.verifyCert(bCert,bChain,0);
        if(iRet==1)
        {
            String s = (String) req.getSession().getAttribute("UUID");
            boolean is=oSE.verifySign(s.getBytes(),bSigned,"SHA1withRSA",bCert,"SunRsaSign");
            if (!is){
                System.out.println("客户端证书非法.");
                return null;
            }
            //////////////////////////////
            //////////////////////////////
            // 底下的"普陀区政府测试"需要进行修改为当前session用户,如:
            // User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
            //////////////////////////////
            //////////////////////////////
	    	/*User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
	    	boolean ib = oSE.verifySign("姜道荣".getBytes(),bItemVal,"SHA1withRSA",bCert,"SunRsaSign");
	    	if (!ib){
	    		System.out.println("用户和key不一致.");
	    		return null;
	    	}*/
        }
        //////////////////////////////
        /////////////////////////////
        // 以上的所有代码均为验证代码,如果全部通过则表示通过验证
        // 下面可以进行查询的代码操作或者跳转。
        /////////////////////////////
        /////////////////////////////
        return "成功";
    }
}
