package com.klsw.klswWebService.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/**
 * 图片校验码
 * 
 */
public class ImageCaptcha extends AbstractCaptcha {

	@Override
	public String generator() {		
		String randomString = CodeGenerator.random("123456789", 4);
		return randomString;
	}
	
	 /**
     * 根据要求的数字生成图片,背景为白色,字体大小16,字体颜色黑色粗体
     * @param num 数字
     * @param num 数字长度
     * @param out 输出流
     * @throws IOException
     */
    public void render(String num, int length, OutputStream out) throws IOException {


        //设定宽度和高度
        int width = 23 * length; 

        int height = 39;//28; 
        // 在内存中创建图象
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics2D g = (Graphics2D) bi.getGraphics();
        //画边框
        java.util.Random random = new java.util.Random();
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        //设置字体
        Font mFont = new Font("Tahoma", Font.BOLD, 20);
        g.setFont(mFont);
        g.setColor(Color.BLACK);//设置字体颜色
        //画认证码,每个认证码在不同的水平位置
        String str1[] = new String[length];
        for (int i = 0; i < str1.length; i++) {
            str1[i] = num.substring(i, i + 1);
            int w = 0;
            int x = (i + 1) % 3;

            //随即生成验证码字符的水平偏移量
            if (x == random.nextInt(3)) {
                w = 19 - random.nextInt(7);
            } else {
                w = 19 + random.nextInt(7);
            }

            //随即生成颜色
            Color color1 = new Color(random.nextInt(180), random.nextInt(180), random.nextInt(180));
            g.setColor(color1);
            g.drawString(str1[i], 20 * i + 10, w);
        }

        // 随机产生干扰点,并用不同的颜色表示，使图象中的认证码不易被其它程序探测到
        /*for (int i = 0; i < 100; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Color color1 = new Color(random.nextInt(255), random.nextInt(255),  random.nextInt(255));
            g.setColor(color1); //随即画各种颜色的点
            g.drawOval(x, y, 0, 0);
        }*/
        //画干扰线
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            Color color1 = new Color(random.nextInt(255), random.nextInt(255),  random.nextInt(255));

            g.setColor(color1); //随即画各种颜色的线
            g.drawLine(x, y, x1, y1);
        }
        //图像生效
        g.dispose();
        //输出页面
        ImageIO.write(bi, "jpg", out);
    }
	
}

