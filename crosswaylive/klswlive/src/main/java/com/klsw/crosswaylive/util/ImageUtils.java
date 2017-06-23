package com.klsw.crosswaylive.util;

import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils {

    //	public static void main(String[] args) {
//		StringBuffer sb = new StringBuffer();
//		sb.append("dashdkjsahksajdh.jpg");
//		sb.insert(sb.lastIndexOf("."), "_small");
//		System.out.println(sb.toString());
//		
//	}
    private final static int newWidth = 150;

    public static void zoomImageScale(File imageFile, String thumbnailPath) throws IOException {
        //如果读不到
        if (!imageFile.canRead())
            return;
        //读取图片
        BufferedImage bufferedImage = ImageIO.read(imageFile);
        //如果是空的
        if (null == bufferedImage)
            return;
        //得到宽
        int originalWidth = bufferedImage.getWidth();
        //得到高
        int originalHeight = bufferedImage.getHeight();
        double scale = (double) originalWidth / (double) newWidth;    // 缩放的比例

        int newHeight = (int) (originalHeight / scale);

        zoomImageUtils(imageFile, thumbnailPath, bufferedImage, newWidth, newHeight);
//        File thumbnail = new File(thumbnailPath);
//        //如果成功创建缩略图
//        if (thumbnail.exists()) {
//            String path = thumbnailPath.substring(thumbnailPath.indexOf("static") + 7);
//            //上传OSS
//            MyFileUtils.uploadFileToOss(thumbnail, path, Constants.wkBucket);
//            //删除服务器文件
//            thumbnail.delete();
//        }

    }

    //    public static void main(String[] args) {
//        String a = "klsw/home/static/dah/dsahk/dsa";
//        System.out.println(a.substring(a.indexOf("static")));
//    }
    private static void zoomImageUtils(File imageFile, String newPath, BufferedImage bufferedImage, int width, int height)
            throws IOException {

        String suffix = StringUtils.substringAfterLast(imageFile.getName(), ".");

        // 处理 png 背景变黑的问题
        if (suffix != null && (suffix.trim().toLowerCase().endsWith("png") || suffix.trim().toLowerCase().endsWith("gif"))) {
            BufferedImage to = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = to.createGraphics();
            to = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
            g2d.dispose();

            g2d = to.createGraphics();
            Image from = bufferedImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
            g2d.drawImage(from, 0, 0, null);
            g2d.dispose();

            ImageIO.write(to, suffix, new File(newPath));
        } else {
            // 高质量压缩，其实对清晰度而言没有太多的帮助
//            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//            tag.getGraphics().drawImage(bufferedImage, 0, 0, width, height, null);
//
//            FileOutputStream out = new FileOutputStream(newPath);    // 将图片写入 newPath
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
//            jep.setQuality(1f, true);    //压缩质量, 1 是最高值
//            encoder.encode(tag, jep);
//            out.close();

            BufferedImage newImage = new BufferedImage(width, height, bufferedImage.getType());
            Graphics g = newImage.getGraphics();
            g.drawImage(bufferedImage, 0, 0, width, height, null);
            g.dispose();
            ImageIO.write(newImage, suffix, new File(newPath));
        }
    }

}
