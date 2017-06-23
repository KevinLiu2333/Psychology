package com.klsw.website.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadUtils {

	@Autowired
	private FileUploadConfig fileUploadConfig;
	
	public Ret uploadFile(List<MultipartFile> fileList,String dirName,String packagePath) 
			throws IOException, FileUploadException {
		
		//定义文件保存目录路径
		String savePath = fileUploadConfig.getSavePath() + "/" + packagePath;
		
		//定义文件保存目录url
		String saveUrl = "/" + packagePath;
		
		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String,String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4,mov");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
		
		//定义允许上传的最大文件大小
		long maxSize = Long.parseLong(fileUploadConfig.getMaxSize()); 
		
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			uploadDir.mkdir();
		}
		if (dirName == null) {
			dirName = "image";
		}
		if(!extMap.containsKey(dirName)){
			return Ret.warn("目录名不正确。");
		}
		
		//创建文件夹
		savePath += "/" + dirName + "/";
		saveUrl += "/" + dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		
		//检查上传的文件
		//1.定义三个StringBuffer对象，方便进行字符串操作,保存文件路径
				StringBuffer sBuffer = new StringBuffer();
				StringBuffer sBuffer2 = new StringBuffer();
				StringBuffer sBuffer3 = new StringBuffer();
		
		//2.判断上传文件是否为空：如果为空，接着判断目录名是否为text，若是，则表明不需要上传文件；否则提示需要上传文件
		if(fileList.isEmpty()) {
			if("text".equals(dirName)){
				return Ret.success("");
			}
			return Ret.warn("请选择文件");
		}
		
		//3.遍历上传的文件集合
		for(MultipartFile file : fileList) {
			String oldFileName = file.getOriginalFilename();//获取原文件名
			
			//(1)检查上传文件大小
			if(file.getSize() > maxSize){
				deleteFile(sBuffer);
				return Ret.warn("上传文件大小超过限制");
			}
			
			//（2）检查上传文件扩展名
			String fileSuffix = oldFileName.substring(
					oldFileName.lastIndexOf(".")+1).toLowerCase(); //获取文件名后缀
			if(!Arrays.asList(extMap.get(dirName).split(",")).contains(fileSuffix)) {
				deleteFile(sBuffer);
				return Ret.warn("上传文件扩展名是不允许的扩展名,只允许" + extMap.get(dirName) + "格式。");
			}
			
			//(3)生成唯一文件名
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String newFileName = simpleDateFormat.format(new Date()) + "_" +
									new Random().nextInt(1000) + "." + fileSuffix;//生成新文件名
			
			//（4）保存文件
			File uploadFile = new File(savePath,newFileName);//创建文件
			try {
				BufferedOutputStream bufferedOutputStream = 
								new BufferedOutputStream(new FileOutputStream(uploadFile));
				byte[] bytes = file.getBytes();//获取原文件的二进制信息
				//写到要保存的文件中
				bufferedOutputStream.write(bytes);
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
				
				//判断是否为图片，若是，生成图片的缩略图
				if("image".equals(dirName)){
                	sBuffer2.append(savePath+newFileName).insert(sBuffer2.lastIndexOf("."),"_small");
                	String thumbnailPath = sBuffer2.toString();
                	ImageUtils.zoomImageScale(uploadFile, thumbnailPath);
                	sBuffer2.delete(0, sBuffer2.length());
                }
				sBuffer.append(savePath).append(newFileName).append(";");
				sBuffer3.append(saveUrl).append(newFileName).append(";");
			} catch (Exception exception) {
				deleteFile(sBuffer);
				exception.printStackTrace();
				return Ret.error("上传文件失败");
			}
		}
		return Ret.success(sBuffer3.toString());
	}
	
	public static void deleteFile(StringBuffer sb){
		if(sb.length()>0){
			String[] filePaths = sb.toString().split(";");
			for(String filePath:filePaths){
				File file = new File(filePath);
				file.delete();
			}
		}
	}
}
