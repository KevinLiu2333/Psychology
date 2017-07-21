package com.wonders.wddc.suite.csrq.generator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;

import com.wonders.wddc.suite.csrq.entity.ReportHeaderBo;
import com.wonders.wddc.suite.csrq.entity.ReportInfoBo;
import com.wonders.wddc.suite.csrq.entity.ReportRowBo;
import com.wonders.wddc.tiles.dic.entity.DicConfigBo;
import com.wonders.wddc.tiles.tools.PropertyUtils;
import com.wonders.wddc.tiles.tools.VelocityUtils;
/**
 * 报表页面处理工具
 * @author vcixp 1.4
 *
 */
public class ReportHtmlGenerator {
	public static String classUrl = VelocityUtils.class.getResource("/")+"";
	public static String appPath = (classUrl.replace("/WEB-INF/classes/","")).replace("file:/", "");
	public static String TEMPLATE_PATH = appPath + PropertyUtils.getProperty("report.vm.mapping.path");
	private static Log log = Logs.get();
	/**
	 * 报表初始化生成
	 * @param info
	 * @return
	 */
	public static String creatTable(ReportInfoBo info) {
		Dao dao = Mvcs.ctx().getDefaultIoc().get(Dao.class,"dao");
		String html = "";
		Map<String, Object> result = new HashMap<String, Object>();
		List<ReportHeaderBo> headers = info.getHeaders();
		List<ReportRowBo> rows = info.getRows();
		for (ReportHeaderBo header : headers) {
			header.init();
			DicConfigBo dic = dao.fetch(DicConfigBo.class,header.getDicid());
			if(dic!=null){
				header.setDictitle(dic.getDicName());
			}
		}
		for (ReportRowBo row : rows) {
			row.init();
			DicConfigBo dic = dao.fetch(DicConfigBo.class,row.getDicid());
			if(dic!=null){
				row.setDictitle(dic.getDicName());
			}else {
				row.setDictitle(" ");
			}
		}
		result.put("colsum", info.getColsum());
		result.put("rowsum", info.getRowsum());
		result.put("hashead", info.getHashead());
		result.put("hasrow", info.getHasrow());
		result.put("headers", headers);
		result.put("rows", rows);
		try {
			html = VelocityUtils.merge(result, TEMPLATE_PATH, Strings.isEmpty(info.getTempletfile())?"report":info.getTempletfile().trim());
		} catch (Exception e) {
			log.error(e);
		}
		return html;
	}
	/**
	 * 根据动态字典生成模板
	 * @param html 原始模板
	 * @param dic  字典
	 * @param dao  dao
	 * @param id   报表id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Document generateHtmlByDic(String html,String dic,Dao dao,String id){
		Document doc = Jsoup.parse(html);
		Element htmlbody = doc.body();
		//找html中最后一行的序号
		Elements tdrows = htmlbody.select("td[data=\"data\"]");
		int maxrowindex=0;
		for(Element td : tdrows){
			int index =Integer.parseInt(td.attr("rowindex"));
			if(index>maxrowindex){
				maxrowindex= index;
			}
		}
		JSONObject dicjson = JSONObject.fromObject(dic);
		Iterator it =  dicjson.keys();
		while(it.hasNext()){
			String key = (String) it.next();
			Elements trs = htmlbody.select("tr[dynamics=\""+key+"\"]");
			if(trs!=null&&trs.size()>0){
				Element tr = trs.get(0);
				Element trclone = tr.clone();
				trclone.select("td[type=\"title\"]").remove();
				JSONObject subdic = JSONObject.fromObject(dicjson.getString(key));
				Iterator it2 =  subdic.keys();
				int rowsum=0;
				StringBuffer trstr = new StringBuffer();
				while (it2.hasNext()) {
					String key2 = (String) it2.next();
					Element currenttr=null;
					if(rowsum==0){
						currenttr = tr;
						Elements td = currenttr.select("td[data=\"data\"]");
						if(td!=null&&td.size()>0){
							Element t = td.get(0);
							t.attr("id","row#"+key+"*"+key2);
							t.html(subdic.getString(key2));
						}
					}else{
						currenttr = trclone.clone();
						maxrowindex++;
						Elements head = currenttr.select("td[data=\"data\"]");
						if(head!=null&&head.size()>0){
							Element td = head.get(0);
							td.attr("rowindex",""+maxrowindex);
							td.attr("id","row#"+key+"*"+key2);
							td.html(subdic.getString(key2));
						}
						Elements datatd = currenttr.select("td[type=\"data\"]");
						for(Element td:datatd){
							String[] split = td.attr("id").split("#");
							if(split.length>=3)
							{
								td.attr("id",split[0]+"#"+maxrowindex+"#"+split[2]);
							}
						}
						trstr.append(currenttr.toString());
					}
					rowsum++;
				}
				ReportRowBo rowbo=dao.fetch(ReportRowBo.class,Cnd.where("report_info_id","=",id).and("column_name","=",key));
				if(rowbo!=null&&"1".equals(rowbo.getIssum())){
					rowsum++;
				}
				tr.after(trstr.toString());
				tr.select("td[type=\"title\"]").attr("rowspan",rowsum+"");
			}
		}
		return doc;
	}
	
	/**
	 * 将数据插入html生成最终的html
	 * @return 
	 */
	public static Document GenerateFinalHtml(ReportInfoBo info,Document doc,List<Map<String, String>> resultList){
		Elements headertds = doc.select("td[header=\"header\"]"); // 数据列
		Elements rowtds = doc.select("td[data=\"data\"]"); // 数据行
		Map<String, Map<String,String>> dicmap = new HashMap<String, Map<String,String>>(); //字典集
		for(Element element:headertds){
			String[] s1 = element.attr("id").split("#");
			if(s1!=null&&s1.length>1){
				String[] s2 = s1[1].split("\\*");
				if(s2!=null&&s2.length>1){
					if("sum".equals(s2[1])){
						continue;
					}else {
						Map<String,String> map = dicmap.get(s2[0].toLowerCase());
						if(map == null){
							map = new HashMap<String,String>();
						}
						map.put(s2[1],"1");
						dicmap.put(s2[0].toLowerCase(), map);
					}
				}
			}
		}
		for(Element element:rowtds){
			String[] s1 = element.attr("id").split("#");
			if(s1!=null&&s1.length>1){
				String[] s2 = s1[1].split("\\*");
				if(s2!=null&&s2.length>1){
					if("sum".equals(s2[1])){
						continue;
					}else {
						Map<String,String> map = dicmap.get(s2[0].toLowerCase());
						if(map == null){
							map = new HashMap<String,String>();
						}
						map.put(s2[1],"1");
						dicmap.put(s2[0].toLowerCase(), map);
					}
				}
			}
		}
		for (Element headertd : headertds) {
			// headerstds数据列
			String[] headerinfo = headertd.attr("id").split("#");
			String colindex = headertd.attr("colindex");
			for (Element rowtd : rowtds) {
				String[] rowinfo = rowtd.attr("id").split("#");
				String rowindex = rowtd.attr("rowindex");
				long value = 0;
				Map<String, String> conditionmap = createconditionmap(rowinfo,
						headerinfo, info.getRows(), info.getHeaders());
				for (Map<String, String> data : resultList) {
					boolean getvalue = true;// 本条数据是否有效
					for (String key : conditionmap.keySet()) {
						Map<String, String> map = dicmap.get(key);
						if("row#sum".equals(rowtd.attr("id"))&&"head#sum".equals(headertd.attr("id"))){
							if(Strings.isEmpty(data.get(key))||(data.get(key)!=null&&map!=null&&!"1".equals(map.get(data.get(key))))){
								getvalue = false;
								break;
							}
						}else if("row#sum".equals(rowtd.attr("id"))&&(map==null||!"1".equals(map.get(data.get(key))))){
							getvalue = false;
							break;
						}
						if (conditionmap.get(key).equals("sum")) { // 求和
							if (!"head#sum".equals(headertd.attr("id"))&&data.get(key) == null) {
								getvalue = false;
								break;
							}
							if(data.get(key) != null&&map!=null&&!"1".equals(map.get(data.get(key)))){	
								getvalue = false;
								break;
							}
						} else { // 非求和
							if (Strings.isEmpty(data.get(key))
									|| !data.get(key).equals(
											conditionmap.get(key))) {
								getvalue = false;
								break;
							}
						}
					}
					if (getvalue) {
						String valuetemp = data.get("value");
						if (!Strings.isEmpty(valuetemp)
								&& StringUtils.isNumeric(valuetemp)) {
							value += Integer.parseInt(valuetemp);
						}
					}
				}
				Elements datatds = doc.select("td[id=\"data#" + rowindex + "#"
						+ colindex + "\"]");
				if (datatds != null && datatds.size() > 0) {
					String url = null;
					for(ReportHeaderBo header : info.getHeaders()){
						if(header.getColumnname().equalsIgnoreCase(headerinfo[1].split("\\*")[0])&&!Strings.isEmpty(header.getUrl())){
							url = header.getUrl().trim();
							if(!Strings.isEmpty(url)&&!url.startsWith("http")){
								url="http://"+url;
							}
							break;
						}
					}
					if(Strings.isEmpty(url)){
						datatds.get(0).html(value+"");
					}else {
						String colDM = headerinfo[1].replace("*", "=");   
						String rowDM = rowinfo[1].replace("*", "=");
						datatds.get(0).html("<a href=\""+url+"?"+colDM+"&"+rowDM+"\" target=\"_blank\">"+value+"<a>");
					}
				}
			}
		}
		return doc;
	}
	
	/*
	 * 生成判断条件的map
	 */
	private static Map<String, String> createconditionmap(String[] rowinfo,
			String[] headerinfo, List<ReportRowBo> rows,
			List<ReportHeaderBo> headers) {
		Map<String, String> result = new HashMap<String, String>();
		if (rowinfo != null && rowinfo.length > 1) {
			if (rowinfo[1].equals("sum")) {
				for (ReportRowBo row : rows) {
					result.put(row.getColumnname().toLowerCase(), "sum");
				}
			} else {
				for (int i = 1; i < rowinfo.length; i++) {
					String[] temp = rowinfo[i].split("\\*");
					if (temp != null && temp.length > 1) {
						result.put(temp[0].toLowerCase(), temp[1]);
					}
				}
			}
		}
		if (headerinfo != null && headerinfo.length > 1) {
			if (headerinfo[1].equals("sum")) {
				for (ReportHeaderBo header : headers) {
					result.put(header.getColumnname().toLowerCase(), "sum");
				}
			} else {
				for (int i = 1; i < headerinfo.length; i++) {
					String[] temp = headerinfo[i].split("\\*");
					if (temp != null && temp.length > 1) {
						result.put(temp[0].toLowerCase(), temp[1]);
					}
				}
			}
		}
		return result;
	}
}
