package com.kevin.springboot.utils.poi;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/10/18
 * Time: 17:12
 */
public class WordTemplate {
    /**
     * @param templetPath 模板路径
     * @param params      参数
     * @param outputPath  输出文件路径
     * @return  目标文件
     */
    public static File loadTemplet(String templetPath, Map<String, String> params, String outputPath) {
        InputStream is = null;
        OutputStream os = null;
        File out = null;
        try {
            is = new FileInputStream(templetPath);
            HWPFDocument doc = new HWPFDocument(is);
            Range range = doc.getRange();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                range.replaceText(entry.getKey(), entry.getValue());
            }
            out = new File(outputPath);
            os = new FileOutputStream(out);
            doc.write(os);
        } catch (Exception e) {
            return null;
        } finally {
            closeStream(is);
            closeStream(os);
        }

        return out;
    }

    /**
     * 关闭流
     *
     * @param stream 流
     */
    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
