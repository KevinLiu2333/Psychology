package com.wonders.zymlgl.service;

import java.io.File;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.util.PropertyUtils;
import com.wonders.zymlgl.entity.ResourceDetails;

/**
 * 根据资源编目格式生成excel模板.
 */
@IocBean
public class CreateExcelService {
	@Inject
	private Dao	dao;

	public void createExcelTemplet(String resourceId) {
		List<ResourceDetails> resourceDetailsList = dao.query(ResourceDetails.class, Cnd.where("resourceId", "=", resourceId));
		String path = getProviderDownLoadPath() + "/" + resourceId + ".xls";
		try {
			WritableWorkbook workBook = Workbook.createWorkbook(new File(path));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = workBook.createSheet("数据模板", 0);
			sheet.addCell(new Label(0, 0, "ID"));
			for (int i = 0; i < resourceDetailsList.size() ; i++) {
				sheet.addCell(new Label(i+1, 0, resourceDetailsList.get(i).getFieldCode().toUpperCase()));
			}

			// 写入数据
			workBook.write();
			// 关闭文件
			workBook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取提供下载模板的路径.
	 * 
	 * @return
	 */
	private String getProviderDownLoadPath() {
		return getBasePath() + PropertyUtils.getProperty("downloadResourceTemplet");
	}

	private String getBasePath() {
		String path = PropertyUtils.getProperty("app.path");
		return path;
	}
}
