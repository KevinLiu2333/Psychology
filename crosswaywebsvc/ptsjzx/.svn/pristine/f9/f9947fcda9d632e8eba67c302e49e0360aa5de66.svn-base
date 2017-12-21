package com.wonders.db.at;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.upload.FieldMeta;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import com.wonders.db.entity.Xdb_files;
import com.wonders.util.FileMd5Utils;
import com.wonders.util.PropertyUtils;


@At("/db/file")
@IocBean(fields = "dao")
public class FileAt {
	private Dao dao;
	private String getFilePath()
	{
		String path=PropertyUtils.getProperty("app.path")+PropertyUtils.getProperty("file.upload.path");
		return path;
	}
	private Xdb_files upload(TempFile tempFile)
	{
		FieldMeta fieldMeta = tempFile.getMeta();
		File file = tempFile.getFile();
		Xdb_files newfile=new Xdb_files();
		String fileId = UUID.randomUUID().toString();
		newfile.setFid(fileId);
		newfile.setFilenamelocal(fieldMeta.getFileLocalName());
		newfile.setFilenameremote(fileId+fieldMeta.getFileExtension());
		newfile.setFilepathremote(getFilePath()+"/"+fileId+fieldMeta.getFileExtension());
		newfile.setFilepathrelative("/"+PropertyUtils.getProperty("file.upload.path")+"/"+fileId+fieldMeta.getFileExtension());
		newfile.setPostedtime(new Date());
		newfile.setPostedlength(file.length()+"");
		newfile.setFilepos("0");
		newfile.setFilelength(""+file.length());
		newfile.setIsdeleted(0);
		newfile.setPostcomplete(1);
		newfile.setPostedpercent("100%");
		try {
			newfile.setFilemd5(FileMd5Utils.getFileMD5String(file));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		File dirFile = new File(getFilePath());
		if (!dirFile.exists()){
			dirFile.mkdirs();
		}
		File resultFile = new File(newfile.getFilepathremote());
		try {
			FileUtils.copyFile(file, resultFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.dao.insert(newfile);
		return newfile;
	}
	/**
	 * 文件上传
	 * @param tempFile
	 * @param req
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@At
	@Ok("jsp:jsp.db.doc")
	@AdaptBy(type = UploadAdaptor.class, args = { "${app.root}/upload/" })
	public Object uploadFile(TempFile tempFile,HttpServletRequest req,HttpSession session) throws IOException{
		Xdb_files newFile =upload(tempFile);
		Map<String,Object> result= new HashMap<String,Object>();
		result.put("file", newFile);
		return result;
	}
	
	/**
	 * api申请上传附件.
	 */
	@At
	@Ok("json")
	@AdaptBy(type = UploadAdaptor.class, args = { "${app.root}/upload/" })
	public Object uploadApiFile(TempFile tempFile,HttpServletRequest req,HttpSession session) throws IOException{
		Xdb_files newFile =upload(tempFile);
		Map<String,Object> result= new HashMap<String,Object>();
		result.put("file", newFile);
		return result;
	}
	
	/**
	 * 根据文件主键下载文件.
	 * @param fileId 文件主键
	 * @param response
	 */
	@At
	public void fileDownById(String fid, HttpServletResponse response) {
		Xdb_files xdb_files = dao.fetch(Xdb_files.class, Cnd.where("FID", "=",fid));
		download(xdb_files, response);
	}
	public void download(Xdb_files xdb_files, HttpServletResponse response){
		try {
			File file=new File(xdb_files.getFilepathremote());
			InputStream inStream = new FileInputStream(file);
			OutputStream outStream = response.getOutputStream(); 
			response.reset();
			String fileName = new String(xdb_files.getFilenamelocal().getBytes("GBK"), "ISO-8859-1");
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			int tempbyte;
			while ((tempbyte = inStream.read()) != -1) {
				outStream.write(tempbyte);
            	outStream.flush();
			}
			outStream.close();
            inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
