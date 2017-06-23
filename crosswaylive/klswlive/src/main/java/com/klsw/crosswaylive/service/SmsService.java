package com.klsw.crosswaylive.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 短信接口
 * @author liukun
 *
 */
@Service
public class SmsService {
	
	public boolean sendSms(String mobile, String content) throws Exception {
				//发送内容
				String sign="克洛斯威钢琴";
				
				// 创建StringBuffer对象用来操作字符串
				StringBuffer sb = new StringBuffer("http://web.cr6868.com/asmx/smsservice.aspx?");

				// 向StringBuffer追加用户名
				sb.append("name=15222912892");

				// 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
				sb.append("&pwd=643437CB8EFB466997BAEF7CEA69");

				// 向StringBuffer追加手机号码
				sb.append("&mobile=").append(mobile);

				// 向StringBuffer追加消息内容转URL标准码
				sb.append("&content="+URLEncoder.encode(content,"UTF-8"));
				
				//追加发送时间，可为空，为空为及时发送
				sb.append("&stime=");
				
				//加签名
				sb.append("&sign="+URLEncoder.encode(sign,"UTF-8"));
				
				//type为固定值pt  extno为扩展码，必须为数字 可为空
				sb.append("&type=pt&extno=");
				// 创建url对象
				//String temp = new String(sb.toString().getBytes("GBK"),"UTF-8");
//				System.out.println("sb:"+sb.toString());
				URL url = new URL(sb.toString());

				// 打开url连接
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();

				// 设置url请求方式 ‘get’ 或者 ‘post’
				connection.setRequestMethod("POST");

				// 发送
				InputStream is =url.openStream();

				//转换返回值
				String returnStr = convertStreamToString(is);
				
				// 返回结果为‘0，20140009090990,1，提交成功’ 发送成功   具体见说明文档
				// 返回发送结果
				String code = returnStr.split(",")[0];
				
				return "0".equals(code);
	}
	/**
	 * 转换返回值类型为UTF-8格式.
	 * @param is
	 * @return
	 */
	private String convertStreamToString(InputStream is) {    
        StringBuilder sb1 = new StringBuilder();    
        byte[] bytes = new byte[4096];  
        int size = 0;  
        
        try {    
        	while ((size = is.read(bytes)) > 0) {  
                String str = new String(bytes, 0, size, "UTF-8");  
                sb1.append(str);  
            }  
        } catch (IOException e) {    
            e.printStackTrace();    
        } finally {    
            try {    
                is.close();    
            } catch (IOException e) {    
               e.printStackTrace();    
            }    
        }    
        return sb1.toString();    
    }
}
