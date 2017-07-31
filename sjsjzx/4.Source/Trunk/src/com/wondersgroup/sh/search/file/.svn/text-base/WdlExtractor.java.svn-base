package com.wondersgroup.sh.search.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jawin.COMException;
import org.jawin.FuncPtr;
import org.jawin.ReturnFlags;

public class WdlExtractor extends AbstractExtractor {//modified by fengjia
	private static final Logger logger = Logger.getLogger(WdlExtractor.class);
	private static Map errors = new HashMap();
	static {
		errors.put(new Integer(-1), "打开WDL文件出错");
		errors.put(new Integer(-2), "打开结果文件写出错");
		errors.put(new Integer(-3), "传入文件参数为空");
	}

	public String extract(InputStream stream) throws FileExtractException {
		FuncPtr wdl = null;
		String srcFile = "wdlsrc.wdl";
		String destFile = "wdldest.txt";
		OutputStream os = null;
		InputStream is = null;
		try {
			os = new FileOutputStream(srcFile);
			IOUtils.copy(stream, os);
			os.close();
			os = null;
			
			wdl = new FuncPtr("ExtractWDL.dll", "ExtractWDLFile2TextWithCRLF");
			int ret = wdl.invoke_I(srcFile, destFile, ReturnFlags.CHECK_W32);
			if( ret != 0 ) {
				throw new FileExtractException((String)errors.get(new Integer(ret)));
			}
			
			is = new FileInputStream(destFile);
			return IOUtils.toString(is);
		} 
		catch (Throwable ex) {
			throw new FileExtractException("抽取WDL文件内容失败", ex);
		}
		finally {
		  if (wdl != null) {
		    try {
		    	wdl.close();
		    } 
		    catch (COMException e) {
		    	logger.error("关闭Jawin失败。", e);
		    }
		  }
		  
			if( os != null ) {
				IOUtils.closeQuietly(os);
			}
			
			if( is != null ) {
				IOUtils.closeQuietly(is);
			}

			try {
				FileUtils.forceDelete(new File(srcFile));
				FileUtils.forceDelete(new File(destFile));
			}
			catch(Exception ex) {
				//logger.error("删除临时文件失败。", ex);
			}
		} 
	}
	
	public static void main(String[] args) {
		InputStream is = null;
		try {
			is = new FileInputStream("d:/test.WDL");
			Extractor extractor = new WdlExtractor();
			String text = extractor.extract(is);
			logger.info(text);
		}
		catch(Exception ex) {
			logger.error("error!", ex);
		}
		finally {
			IOUtils.closeQuietly(is);
		}
	}
}
