package com.wonders.job;

import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.fr.ImportXml2Table;
import com.wonders.utils.FtpUtils;
import com.wonders.utils.PropertyUtils;

public class ImpFrkDataJob implements Job {

	private Logger	logger	= Logger.getLogger(ImpFrkDataJob.class);
	private String	bakPath	= PropertyUtils.getProperty("bakPath");

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// 1. 登录ftp
		// 2. 循环得到xml文件名{
		// 3. 根据文件名下载文件
		// 4. 解析xml到高配数据库
		// 5. xml文件移动到高配服务器 "D:\xmlbak\" 目录下
		// 6. 从ftp上删除该文件
		// 7. }
		// 8. 关闭ftp
		try {
			FtpUtils df = new FtpUtils();
			List<String> xmlFiles = df.getFileNameList("");
			for (String s : xmlFiles) {
				// 下载文件,同时完成移动到bak目录下
				df.download(s, bakPath + s);
			}
			new ImportXml2Table().readXmlsByPath(bakPath);

			// 从ftp上删除文件
			for (String s : xmlFiles) {
				df.deleteFile(s);
				logger.info("*******************从FTP删除文件：" + s + "成功！*******************");
			}

			df.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}