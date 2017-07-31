package com.wonders.db.at;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.db.entity.Xdb_files;

@IocBean
@At("/pt")
public class PtAt {
	@At
	@Ok("jsp:jsp.zx.pt.people")
	public void toPeople(){
		
	}
	@At
	@Ok("jsp:jsp.zx.pt.serve_apply")
	public void toServeApply(){
		
	}
	@At
	@Ok("jsp:jsp.zx.pt.data_apply")
	public void toDataApply(){
		
	}
	@At
	@Ok("jsp:jsp.zx.pt.interface_apply")
	public void toInterfaceApply(){
		
	}
	@At
	@Ok("jsp:jsp.zx.pt.apply_check")
	public void toApplyCheck(){
		
	}
	@At
	@Ok("jsp:jsp.zx.pt.interface_info")
	public void toInterfaceInfo(){
		
	}
	@At
	public void fileDownById(String fid, HttpServletResponse response) {
		download(response);
	}
	public void download(HttpServletResponse response){
		try {
			File file=new File("D:\\myproject\\calileo\\sjsjzx\\WebContent\\upload\\house.xlsx");
			InputStream inStream = new FileInputStream(file);
			OutputStream outStream = response.getOutputStream(); 
			response.reset();
			String fileName = new String("house-ISO-8859-1");
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
