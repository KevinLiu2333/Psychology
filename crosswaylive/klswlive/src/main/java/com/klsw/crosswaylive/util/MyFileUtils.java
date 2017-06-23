package com.klsw.crosswaylive.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ListBucketsRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.mts.model.v20140618.SubmitSnapshotJobRequest;
import com.aliyuncs.mts.model.v20140618.SubmitSnapshotJobResponse;
import com.aliyuncs.mts.model.v20140618.SubmitSnapshotJobResponse.SnapshotJob;
import com.klsw.common.live.model.Ret;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

@Component
public class MyFileUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyFileUtils.class);
    @Value("${file.upload.savePath}")
    private String savePath;

    @Value("${file.upload.maxSize}")
    private long maxSize;

    @Resource
    private OSSClient ossClient;

    @Resource
    private DefaultAcsClient aliyunClient;

    @Value("${spring.http.multipart.location}")
    private String tempPath;

    @Value("${aliyun.oss.region}")
    private String OSS_REGION;

    private static Map<String, String> extMap;

    static {
        //定义允许上传的文件扩展名
        extMap = new HashMap<>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4,mov,m4a");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
    }

    /**
     * @param fileList    文件列表
     * @param fileType    文件类型
     * @param packagepath 文件保存路径(url路径)
     * @return 保存结果
     */
    public Ret uploadfile(MultipartFile[] fileList, String fileType, String packagepath) {
        if (!packagepath.endsWith("/")) {
            packagepath += "/";
        }
        //最大文件大小
        if (fileList.length == 0) {
            return Ret.warn("请选择文件");
        }
        if (fileType == null) {
            fileType = "image";
        }
        if (!extMap.containsKey(fileType)) {
            return Ret.warn("目录名不正确。");
        }
        File temp = new File(tempPath);
        if (!temp.isDirectory()) {
            temp.mkdirs();
        }
//        //文件保存目录URL
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
//        //文件夹加上时间参数
//        packagepath += "/" + df.format(new Date()) + "/";
        StringBuffer sb = new StringBuffer();
        for (MultipartFile multipartFile : fileList) {
            if (multipartFile.getSize() > maxSize) {
                return Ret.warn("上传文件大小超过限制。");
            }
            String fileName = (new Random().nextInt(1000) + 1) + multipartFile.getOriginalFilename().replace(" ", "");
            //检查扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (!Arrays.asList(extMap.get(fileType).split(",")).contains(fileExt)) {
                deleteFile(sb);
                return Ret.warn("上传文件扩展名是不允许的扩展名。只允许" + extMap.get(fileType) + "格式。");
            }
            //服务器缓存文件.便于操作
            String tempFilePath = tempPath + "/" + +new Random().nextInt(1000) + fileName;
            File file = new File(tempFilePath);
            //写入图片
            try {
                multipartFile.transferTo(file);
                String fileUrl = packagepath + fileName;
                uploadFileToOss(file, fileUrl, Constants.liveBucket);
                file.delete();
                //文件访问相对路径
                //如果为图片，则生成缩略图
                if ("image".equals(fileType)) {
                    StringBuilder sb1 = new StringBuilder();
                    String thumbnailPath =
                            sb1.append(
                                    tempFilePath).insert(sb1.lastIndexOf("."), "_small").toString();
                    sb1.delete(0, sb1.length());
                    String thumbnailUrl = sb1.append(
                            fileUrl).insert(sb1.lastIndexOf("."), "_small").toString();
                    ImageUtils.zoomImageScale(file, thumbnailPath);
                    File thumbnail = new File(thumbnailPath);
                    System.out.println(thumbnail.exists());
                    uploadFileToOss(thumbnail, thumbnailUrl, Constants.liveBucket);
                    thumbnail.delete();
                }
                sb.append(fileUrl).append(";");
            } catch (Exception e) {
                LOGGER.info("msg", e);
                return Ret.error("上传文件失败。");
            }
        }
        if (sb.toString().split(";").length == 1) {
            return Ret.success(sb.toString().trim().replace(";", ""));
        }
        return Ret.success(sb.toString().trim());
    }

    public void zoomImageScale(File imageFile, String thumbnailPath) throws IOException {
        ImageUtils.zoomImageScale(imageFile, thumbnailPath);
        File file = new File(thumbnailPath);
        if (file.exists()) {
            String path = thumbnailPath.substring(thumbnailPath.indexOf("static") + 7);
            //上传OSS
            uploadFileToOss(file, path, Constants.wkBucket);
            //删除服务器文件
            file.delete();
        }

    }

    public String getSnapShot(String bucket, String object) throws Exception {
        try {
            object = object.startsWith("/") ? object.substring(1) : object;
            String encodedObjectName = URLEncoder.encode(object, "utf-8");
            OSSFileDO fileDO = new OSSFileDO(OSS_REGION, bucket, encodedObjectName);
            String snapShotUrl = snapshotJobFlow(fileDO);
            return snapShotUrl;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String snapshotJobFlow(OSSFileDO inputFile) throws Exception {
        SnapshotJob job = submitSnapshotJob(inputFile);
        String outputObject = job.getSnapshotConfig().getOutputFile().getObject();
        return "/" + URLDecoder.decode(outputObject, "utf-8");
    }

    private SnapshotJob submitSnapshotJob(OSSFileDO inputFile) {
        OSSFileDO outputSnapshotFile = new OSSFileDO();
        outputSnapshotFile.setBucket(Constants.wkBucket);
        outputSnapshotFile.setLocation(OSS_REGION);
        try {
            outputSnapshotFile.setObject(URLEncoder.encode("upload/snapshots/" + UUID.randomUUID() + ".jpg",
                    "utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("submitTranscodeJob URL encode failed");
        }


        JSONObject jobConfig = new JSONObject();
        jobConfig.put("OutputFile", outputSnapshotFile.toJson());
        jobConfig.put("Time", 1000);   //snapshot time by ms

        SubmitSnapshotJobRequest request = new SubmitSnapshotJobRequest();
        request.setSnapshotConfig(jobConfig.toJSONString());
        request.setInput(inputFile.toJsonString());

        SubmitSnapshotJobResponse response = null;
        try {
            response = aliyunClient.getAcsResponse(request);
            return response.getSnapshotJob();
        } catch (ServerException e) {
            throw new RuntimeException("submitSnapshotJob Server failed");
        } catch (ClientException e) {
            throw new RuntimeException("submitSnapshotJob Client failed");
        }
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

    public boolean deleteFileInOss(String bucketName, String filePath) {
        if (filePath.startsWith("/") || filePath.startsWith("\\")) {
            filePath = filePath.substring(1, filePath.length());
        }
        ossClient.deleteObject(bucketName, filePath);
        return true;
    }

    private static void deleteFile(StringBuffer sb) {
        if (sb.length() > 0) {
            String[] filePaths = sb.toString().split(";");
            for (String filePath : filePaths) {
                File file = new File(filePath);
                file.delete();
            }
        }
    }

}


