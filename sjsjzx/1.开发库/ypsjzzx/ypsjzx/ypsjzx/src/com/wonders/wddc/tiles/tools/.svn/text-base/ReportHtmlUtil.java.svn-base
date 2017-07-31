package com.wonders.wddc.tiles.tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.wonders.wddc.suite.csrq.entity.ReportHeaderBo;
import com.wonders.wddc.suite.csrq.entity.ReportInfoBo;
import com.wonders.wddc.suite.csrq.entity.ReportRowBo;
/**
 * 报表页面初始化工具
 * @author vcixp
 *
 */
public class ReportHtmlUtil {
	private static String VMFILE = "report";

	private static Log log = Logs.get();

	public static String creatTable(ReportInfoBo info) {
		String html = "";
		Map<String, Object> result = new HashMap<String, Object>();
		List<ReportHeaderBo> headers = info.getHeaders();
		List<ReportRowBo> rows = info.getRows();
		for (ReportHeaderBo header : headers) {
			header.init();
		}
		for (ReportRowBo row : rows) {
			row.init();
		}
		result.put("colsum", info.getColsum());
		result.put("rowsum", info.getRowsum());
		result.put("hashead", info.getHashead());
		result.put("hasrow", info.getHasrow());
		result.put("headers", headers);
		result.put("rows", rows);
		try {
			html = VelocityUtils.merge(result, VMFILE);
		} catch (Exception e) {
			log.error(e);
		}
		return html;
	}
}
