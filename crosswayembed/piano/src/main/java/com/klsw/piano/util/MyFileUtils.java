package com.klsw.piano.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ListBucketsRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.klsw.pianoCommon.api.model.Ret;
import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.zip.Inflater;

@Service
public class MyFileUtils {

    private final Logger logger = LoggerFactory.getLogger(MyFileUtils.class);

    @Resource
    private OSSClient ossClient;

    @Resource
    private FileUploadConfig fileUploadConfig;

    /**
     * @param filePath 文件路径
     * @param file     文件
     * @return 保存结果
     * @throws IOException         文件流错误
     * @throws FileUploadException 文件上传错误
     */
    public Ret uploadfile(String filePath, MultipartFile file, boolean deleteFile)
            throws IOException, FileUploadException {
        //检查目录
        File uploadFile = new File(filePath);
        if (!uploadFile.getParentFile().isDirectory()) {
            uploadFile.getParentFile().mkdirs();
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        byte[] buffer = new byte[1024];
        try {
            if (!uploadFile.exists()) {
                uploadFile.createNewFile();
            }
            bis = new BufferedInputStream(file.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(uploadFile));
            while (bis.available() > 0) {
                bos.write(buffer, 0, bis.read(buffer));
                bos.flush();
            }
            bis.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传文件失败:" + "msg", e);
            return Ret.error("上传文件失败。");
        } finally {
            if (bos != null) {
                bos.close();
            }
            if (bis != null) {
                bis.close();
            }
            if (uploadFile.exists()) {
                logger.info("开始上传文件");
                uploadFileToOss(uploadFile, filePath.substring(filePath.lastIndexOf("static")), Constants.pianoBucket);
                logger.info("上传文件结束");
                if (deleteFile) {
                    uploadFile.delete();
                }
            }

        }

        return Ret.success();
    }

    public Ret multipartFileToFile(String filePath, MultipartFile file) {
        File uploadFile = new File(filePath);
        if (!uploadFile.getParentFile().isDirectory()) {
            uploadFile.getParentFile().mkdirs();
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        byte[] buffer = new byte[1024];
        try {
            if (!uploadFile.exists()) {
                uploadFile.createNewFile();
            }
            bis = new BufferedInputStream(file.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(uploadFile));
            while (bis.available() > 0) {
                bos.write(buffer, 0, bis.read(buffer));
                bos.flush();
            }
            bis.close();
            bos.close();
            return Ret.success(uploadFile);
        } catch (Exception e) {
            e.printStackTrace();
            return Ret.error("fail");
        }

    }

    public boolean deleteFileInOss(String bucketName, String filePath) {
        if (filePath.startsWith("/") || filePath.startsWith("\\")) {
            filePath = filePath.substring(1, filePath.length());
        }
        ossClient.deleteObject(bucketName, filePath);
        return true;
    }

    public static String getMd5ByFile(File file) throws FileNotFoundException {
        String value = null;
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public void deleteFile(StringBuffer sb) {
        if (sb.length() > 0) {
            String[] filePaths = sb.toString().split(";");
            for (String filePath : filePaths) {
                File file = new File(filePath);
                file.delete();
            }
        }
    }

//    public static void main(String[] args) throws Exception {
//        File file = new File("e:/a/d/f/f/s");
//        System.out.println(file.exists());
//        System.out.println(file.getParentFile().isDirectory());
//        file.getParentFile().mkdirs();
//        System.out.println(file.getParentFile().isDirectory());
//        file.createNewFile();
//        System.out.println(file.exists());
//    }

    public int midtoMp3(String params) throws Exception {
        String mid2Mp3 = fileUploadConfig.getMid2Mp3();
        String sf2 = fileUploadConfig.getSf2Path();
        Process process = Runtime.getRuntime().exec(mid2Mp3 + " " + sf2 + " " + params);
        int exitCode = process.waitFor();
        process.destroy();
        return exitCode;
    }

//    public Ret zipDecompress(InputStream zipStream, String dirname, String filename) {
//        try {
//            ZipInputStream zipInputStream = new ZipInputStream(zipStream);
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(zipInputStream);
//            File fout;
//            ZipEntry zipEntry = zipInputStream.getNextEntry();
//            fout = new File(dirname, filename);
//            if (!fout.exists()) {
//                (new File(fout.getParent())).mkdirs();
//            }
//            FileOutputStream out = new FileOutputStream(fout);
//            BufferedOutputStream Bout = new BufferedOutputStream(out);
//            int b;
//            while ((b = bufferedInputStream.read()) != -1) {
//                Bout.write(b);
//            }
//            Bout.close();
//            out.close();
//            return Ret.success();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return Ret.error("解压失败");
//
//    }

    /**
     * 解压缩
     *
     * @param data 待压缩的数据
     * @return byte[] 解压缩后的数据
     */
    public Ret decompress(byte[] data, String dirname, String filename) throws IOException {
        File fout = new File(dirname, filename);
        if (!fout.exists()) {
            (new File(fout.getParent())).mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        try {
            Inflater decompresser = new Inflater();
            decompresser.reset();
            decompresser.setInput(data);
            byte[] buf = new byte[4096];
            while (!decompresser.finished()) {
                int i = decompresser.inflate(buf);
                bos.write(buf, 0, i);
            }
            decompresser.end();
            return Ret.success();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bos.close();
            fos.close();
            if (fout.exists()) {
//                long time1 = System.currentTimeMillis();
                uploadFileToOss(fout, dirname.substring(dirname.lastIndexOf("static")) + "/" + filename, Constants.pianoBucket);
//                System.out.println(System.currentTimeMillis() - time1);

            }
        }
        return Ret.error("错误");
    }

    public boolean uploadFileToOss(File file, String filePath, String bucketName) {
        if (filePath.startsWith("/") || filePath.startsWith("\\")) {
            filePath = filePath.substring(1, filePath.length());
        }
        try {
            ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
            listBucketsRequest.setMaxKeys(500);
            ossClient.putObject(new PutObjectRequest(bucketName, filePath, file));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Ret createQRCode(String content, String savePath, int size) {
        Ret ret = QRcodeUtil.createQRCode(content, savePath, size);
        //如果成功生成二维码
        if ("S".equals(ret.getStatus())) {
            File file = new File(savePath);
            if (file.exists()) {
                uploadFileToOss(file, savePath.substring(savePath.lastIndexOf("static")), Constants.pianoBucket);
            }
        }
        return ret;
    }

}


