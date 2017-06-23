//package com.klsw.piano.util;
//
//import com.aliyun.oss.OSSClient;
//import com.aliyun.oss.model.ListBucketsRequest;
//import com.aliyun.oss.model.PutObjectRequest;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//
///**
// * Created by liukun on 2016/12/5.
// * oss相关工具
// */
//@Service
//public class OssUtils {
//
//    @Autowired
//    private OSSClient ossClient;
//
//    private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
//    private static String accessKeyId = "LTAIksw4QT0KaoXY";
//    private static String accessKeySecret = "PHXKpCu6FlM5aIJFinaCxUrMSkCp79";
//
//    public String uploadDir(File uploadDir, String dirName) {
//        if (!uploadDir.isDirectory() || StringUtils.isBlank(dirName)) {
//            return "fail";
//        }
//        File listFiles[] = uploadDir.listFiles();
//        for (File file : listFiles) {
//            if (file.isDirectory()) {
//                uploadDir(file, dirName + "/" + file.getName());
//                continue;
//            }
//            String savePath = dirName + "/" + file.getName();
//            uploadFile(file, savePath, Constants.pianoBucket);
//        }
//        return null;
//    }
//
//    public String uploadFile(File file, String filePath, String bucketName) {
//        if (filePath.startsWith("/") || filePath.startsWith("\\")) {
//            filePath = filePath.substring(1, filePath.length());
//        }
//        try {
//            ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
//            listBucketsRequest.setMaxKeys(500);
//            ossClient.putObject(new PutObjectRequest(bucketName, filePath, file));
//            return "success";
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            ossClient.shutdown();
//        }
//        return "fail";
//    }
//}
