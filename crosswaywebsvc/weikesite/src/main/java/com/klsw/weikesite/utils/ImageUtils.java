package com.klsw.weikesite.utils;

import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
@Service
public class ImageUtils {
    public static File GenerateImage(String imgStr)  
    {   //对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null) //图像数据为空  
            return null;
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + "png";
			File file = new File(newFileName);
            OutputStream out = new FileOutputStream(file);      
            out.write(b);  
            out.flush();  
            out.close();  
            return file;  
        }   
        catch (Exception e)   
        {  
            return null;  
        }
    }

}
