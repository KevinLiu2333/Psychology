package com.klsw.piano.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.klsw.pianoCommon.api.model.Ret;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liukun on 2016/11/2.
 * 二维码
 */
public class QRcodeUtil {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

    public static Ret createQRCode(String content, String savePath, int size) {
        File outputFile = new File(savePath);
        File dir = outputFile.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map hints = new HashMap();
            //内容所使用编码
            hints.put(EncodeHintType.CHARACTER_SET, "gb2312");
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, size, size, hints);
            //生成二维码
            outputFile.createNewFile();
            QRcodeUtil.writeToFile(bitMatrix, "png", outputFile);
            return Ret.success("生成二维码成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Ret.error("生成二维码失败");
        }
    }

//    public static void main(String[] args) throws Exception {
//        Ret ret = createQRCode(WxUtils.shareUrl("http://piano.klsw.com"), "d:/a.jpg", 200);
//
//    }
}