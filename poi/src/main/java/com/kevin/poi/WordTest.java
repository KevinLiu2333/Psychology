package com.kevin.poi;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/9/20
 * Time: 9:45
 */
public class WordTest {

    @Test
    public void testWord() throws IOException {
        Map<String, String> label = new HashMap<>();
        label.put("${unitName}", "ddd");
        label.put("${year}", "2017");
        label.put("${month}", "12");
        label.put("${day}", "14");
        String srcPath = "d:\\test.docx";
        String destPath = "d:\\write.docx";
        try {
            InputStream is = new FileInputStream("D://test2.doc");
            HWPFDocument doc = new HWPFDocument(is);
            Range range = doc.getRange();
//        输出word文档的所有文本
            range.replaceText("意见", "民政局");
            OutputStream os = new FileOutputStream("D:\\write.doc");
            //把doc输出到输出流中
            doc.write(os);
            closeStream(is);
            closeStream(os);
        } catch (Exception e) {

        }
    }

    /**
     * 关闭输出流
     *
     * @param os
     */
    private void closeStream(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭输入流
     *
     * @param is
     */
    private void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
