package com.klsw.weikesite.utils;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;

import com.klsw.apiCommon.api.model.Ret;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
public class Base64Utils {
	
	public static Ret base64ImageString(String imgUrl){
		
		try {
			URL url = new URL(imgUrl);
			BufferedImage bufferedImage = ImageIO.read(url);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", outputStream);
			BASE64Encoder encoder = new BASE64Encoder();
			String base64String = encoder.encode(outputStream.toByteArray());
			return Ret.success(base64String);
		} catch (IOException e) {
			e.printStackTrace();
			return Ret.error("图片转换失败");
		}
		
	}

}
