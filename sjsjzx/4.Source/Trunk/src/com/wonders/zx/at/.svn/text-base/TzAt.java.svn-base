package com.wonders.zx.at;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.view.ServerRedirectView;

import com.wonders.dp.at.DaPingAt;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.util.ClassReflection;
import com.wonders.zx.entity.CoreCacheBo;
import com.wonders.zx.entity.CoreTaglibsBo;
import com.wonders.zx.entity.DicSjtzBo;
import com.wonders.zx.entity.SjtzCoreCorpBo;
import com.wonders.zx.entity.SjtzRulesBo;
import com.wonders.zx.entity.SjtzViewCorp;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * @author Wanda
 * 松江台账at类
 *
 */
@IocBean
@At("/tz")
public class TzAt { 
	@Inject
	private Dao dao;
	
	/**
	 * 每次查询数量
	 */
	private static int PAGE_SIZE = 1000;
	
	/**
	 * @param session
	 * @return
	 * 标签云管理页面
	 */
	@At
	@Ok("jsp:jsp.zx.tzbq.bqygl")
	public Map<String, Object> toTagManage(HttpServletRequest request,String tagid) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		if(!org.nutz.lang.Strings.isEmpty(tagid)){
			CoreTaglibsBo tag =dao.fetch(CoreTaglibsBo.class,tagid);
			tag.setTagStatus("0");
			dao.update(tag);
		}
		Pager pager = ConUtils.makePaginationPager(request);
		List<CoreTaglibsBo> list = dao.query(CoreTaglibsBo.class, null,pager);
		pager.setRecordCount(dao.count(CoreTaglibsBo.class, null));
		result.put("pager", pager);
		result.put("list", list);
		return result;
		
	}
	/**
	 * @return
	 * @throws Exception
	 * 新增标签
	 */
	@At
	@Ok("jsp:jsp.zx.tzbq.xzbq")
	public Map<String, Object> toAddTag() throws Exception{
		return null;
		
	}
	
	/**
	 * @param session
	 * @return
	 * 规则执行页面
	 */
	@At
	@Ok("jsp:jsp.zx.tzbq.gzzx")
	public Map<String, Object> toRuleExecute(HttpServletRequest request,String ruleId) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		List<SjtzRulesBo> list = dao.query(SjtzRulesBo.class, null,pager);
		pager.setRecordCount(dao.count(SjtzRulesBo.class, null));
		result.put("pager", pager);
		result.put("list", list);
		return result;
	}
	/**
	 * @return
	 * @throws Exception
	 * 规则新增
	 */
	@At
	@Ok("jsp:jsp.zx.tzbq.xzgz")
	public Map<String, Object> toAddRule(String ruleId) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		List<CoreTaglibsBo> tags = dao.query(CoreTaglibsBo.class, Cnd.where("PRE_TAG_ID","=","0"));
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		SjtzRulesBo info = null;
		for (CoreTaglibsBo  tag : tags) {
			Map<String, Object> tagsMap = new HashMap<String, Object>();
			List<CoreTaglibsBo> list = dao.query(CoreTaglibsBo.class, Cnd.where("PRE_TAG_ID","=",tag.getId()));
			tagsMap.put("list", list);
			tagsMap.put("tag", tag);
			dataList.add(tagsMap);
		}
		result.put("dataList", dataList);
		if (ruleId != null) {
			info = dao.fetch(SjtzRulesBo.class,ruleId);
			String rule = info.getRuleids().replace(" ", "");
			String[] ruleArray = (rule.substring(1,rule.length()-1)).split(",");
			List<String> childIdList = new ArrayList<String>();
			for (String str : ruleArray) {
				childIdList.add(str.split("\\|")[1]);
			}
			result.put("childIdList", childIdList.toString());
			result.put("info", info);
		}
		return result;
	}
	
	
	@At
	public void deleteRule(String ruleId) {
		dao.execute(Sqls.create("delete from SJTZ_RULES where RULE_ID='"+ruleId+"'"));
	}
	
	/**
	 * @param ruleName
	 * @param tagId
	 * @return
	 * 规则保存
	 */
	@At
	public View toSaveRule(String ruleName,List<String> tagId,String executeType,String ruleId){
		SjtzRulesBo rulesBo = new SjtzRulesBo();
		if (Strings.isEmpty(ruleId)) {
			rulesBo.setRuleid(UUID.randomUUID().toString().replace("-", ""));
			rulesBo.setRulename(ruleName);
			rulesBo.setRuleids(tagId.toString());
			rulesBo.setExecuteType(executeType);
			dao.insert(rulesBo);
		}else {
			rulesBo = dao.fetch(SjtzRulesBo.class,ruleId);
			rulesBo.setRulename(ruleName);
			rulesBo.setRuleids(tagId.toString());
			rulesBo.setExecuteType(executeType);
			dao.update(rulesBo);
		}
		
		if ("1".equals(executeType)) {
			try {
				executeRule(rulesBo.getRuleid());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ServerRedirectView("/tz/toRuleExecute");
	}
	/**
	 * @param session
	 * @return
	 * 台账目录
	 */
	@At
	@Ok("jsp:jsp.zx.tzbq.tzml")
	public Map<String, Object> toTzList() throws Exception{
		/*
		 * 1.查询规则表，将规则显示
		 * 2.点击规则，显示缓存表信息
		 */
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("RULE_EXECUTE_TYPE","=","1");
		cri.where().andNotIsNull("RULE_DATE");
		List<SjtzRulesBo> list = dao.query(SjtzRulesBo.class,cri);
		List<Integer> countList = new ArrayList<Integer>();
		for (SjtzRulesBo rule : list) {
			countList.add(dao.count(CoreCacheBo.class,Cnd.where("RULE_ID","=",rule.getRuleid())));
		}
		result.put("list", list);
		result.put("countList", countList);
		return result;
	}
	
	/**
	 * 台账目录详情信息
	 * @param ruleId
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.zx.tzbq.tzInfo")
	public Map<String, Object> toTzInfo(String ruleId,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		//分页
		Pager pager = ConUtils.makePaginationPager(request);
		List<CoreCacheBo> list = dao.query(CoreCacheBo.class, Cnd.where("RULE_ID","=",ruleId),pager);
		SjtzRulesBo sjtzRulesBo = dao.fetch(SjtzRulesBo.class,Cnd.where("RULE_ID","=",ruleId));
		pager.setRecordCount(dao.count(CoreCacheBo.class, Cnd.where("RULE_ID","=",ruleId)));
		result.put("pager", pager);
		result.put("list", list);
		result.put("ruleName", sjtzRulesBo.getRulename());
		result.put("ruleId", ruleId);
		return result;
	}
	
	/**
	 * 导出法人目录
	 * @param ruleId
	 * @param response
	 */
	@At
	public void exportData(String ruleId,HttpServletResponse response) {
		List<CoreCacheBo> list = dao.query(CoreCacheBo.class, Cnd.where("RULE_ID","=",ruleId));
		SjtzRulesBo rulesBo = dao.fetch(SjtzRulesBo.class,Cnd.where("RULE_ID","=",ruleId));
		WritableWorkbook workbook = null;  
        WritableSheet sheet = null;
		//导出excel
		try{
			workbook = Workbook.createWorkbook(new File("d:/"+rulesBo.getRulename()+".xls"));
			sheet = workbook.createSheet("Sheet1", 0);  
			jxl.SheetSettings sheetset = sheet.getSettings();  
			sheetset.setProtected(false); 
			/** ************设置单元格字体************** */  
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);  
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);  
			/** ************以下设置三种单元格样式，灵活备用************ */  
			   // 用于标题居中 
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);  
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条  
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐  
			wcf_center.setWrap(false); // 文字是否换行  
			// 用于正文居左  
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);  
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条  
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
			wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐  
			wcf_left.setWrap(false); // 文字是否换行 
			/** ***************以下是EXCEL第一行列标题********************* */
			sheet.mergeCells(0,0,6,0);//表头
	        sheet.addCell(new Label(0, 0, rulesBo.getRulename(),wcf_center)); 
			sheet.addCell(new Label(0, 1,"序号",wcf_center));  
			sheet.addCell(new Label(1, 1,"法人名称",wcf_center));  
			sheet.addCell(new Label(2, 1,"社会统一信用代码",wcf_center));  
			sheet.addCell(new Label(3, 1,"机构代码",wcf_center));  
			sheet.addCell(new Label(4, 1,"注册资金",wcf_center));
			sheet.addCell(new Label(5, 1,"注册币种",wcf_center));
			sheet.addCell(new Label(6, 1,"成立日期",wcf_center));
			/** ***************以下是EXCEL正文数据********************* */ 
			SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
			int i=2;
			for(CoreCacheBo info:list){
				sheet.addCell(new Label(0, i,(i-1)+"",wcf_left));  
				sheet.addCell(new Label(1, i,(String) (info.getCorpname()==null?"":info.getCorpname()),wcf_left));  
				sheet.addCell(new Label(2, i,(String) (info.getUniscid()==null?"":info.getUniscid()),wcf_left));  
				sheet.addCell(new Label(3, i,(String) (info.getOrgancode()==null?"":info.getOrgancode()),wcf_left));  
				sheet.addCell(new Label(4, i,Double.toString(info.getRegcapital()),wcf_left));
				sheet.addCell(new Label(5, i,(String) (info.getCurrency()==null?"":info.getCurrency()),wcf_left));
				sheet.addCell(new Label(6, i,(String) (info.getEstablishdate()==null?"":format.format(info.getEstablishdate())),wcf_left));
				i++;
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */  
			workbook.write();  
			/** *********关闭文件************* */  
			workbook.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 规则立即执行
	 * @param ruleId
	 * @throws Exception
	 */
	@At
	public void executeRule(String ruleId) throws Exception {
		SjtzRulesBo sjtzRulesBo = dao.fetch(SjtzRulesBo.class, ruleId);
		String rule = sjtzRulesBo.getRuleids().replace(" ", "");
		String[] ruleArray = (rule.substring(1,rule.length()-1)).split(",");
		Map<String, List<String>> ruleMap = new LinkedHashMap<String, List<String>>();
		String[] itemArray = null;
		// 将数据库查到的String转换为数组
		for (String item : ruleArray) {
			itemArray = item.split("\\|");
			if (ruleMap.containsKey(itemArray[0])) {
				ruleMap.get(itemArray[0]).add(itemArray[1]);
			}
			else {
				List<String> list = new ArrayList<String>();
				list.add(itemArray[1]);
				ruleMap.put(itemArray[0], list);
			}
		}
		//preTageId为上级ID

		StringBuilder sql = new StringBuilder("1=1");
		int childSize = 0;
		for (String preTagId : ruleMap.keySet()) {
			childSize = ruleMap.get(preTagId).size();
			if ("1".equals(preTagId)) {
				if (childSize == 1) {
					if ("11".equals(ruleMap.get(preTagId).get(0))) {
						sql.append(" and CURRENCY = '人民币'");
					}else {
						sql.append(" and (CURRENCY <> '人民币' and CURRENCY is not null)");
					}
				}
			}else if ("2".equals(preTagId)) {
				sql.append(" and (");
				for(int i=0;i<childSize;i++){
					CoreTaglibsBo coreTaglibsBo = dao.fetch(CoreTaglibsBo.class,ruleMap.get(preTagId).get(i));
					DicSjtzBo dicSjtzBo = dao.fetch(DicSjtzBo.class,Cnd.where("VALUE","=",coreTaglibsBo.getTagName()));
					String[] zjIdArray = dicSjtzBo.getId().split("\\|");
					
					if(0==i){
						sql.append("(").append(dicSjtzBo.getCloumnName()).append(">=").append(zjIdArray[0]);
						sql.append(" and ").append(dicSjtzBo.getCloumnName()).append("<").append(zjIdArray[1]).append(")");
					}else{
						sql.append(" or (").append(dicSjtzBo.getCloumnName()).append(">=").append(zjIdArray[0]);
						sql.append(" and ").append(dicSjtzBo.getCloumnName()).append("<").append(zjIdArray[1]).append(")");
					}	
				}
				sql.append(")");
			}else if (Integer.parseInt(preTagId) >= 6) {
				sql.append(" and (");
				for (int i = 0; i < childSize; i++) {
					if (0==i) {
						sql.append("( LABEL_IDS LIKE '%").append(ruleMap.get(preTagId).get(i)).append("%')");
					}else {
						sql.append(" or ( LABEL_IDS LIKE '%").append(ruleMap.get(preTagId).get(i)).append("%')");
					}
				}
				sql.append(")");
			}else{  //( 2 < preTagId < 6 )
				sql.append(" and (");
				for(int i=0;i<childSize;i++){
					CoreTaglibsBo coreTaglibsBo = dao.fetch(CoreTaglibsBo.class,ruleMap.get(preTagId).get(i));
					DicSjtzBo dicSjtzBo = dao.fetch(DicSjtzBo.class,Cnd.where("VALUE","=",coreTaglibsBo.getTagName()));
					if(0==i){
						sql.append(dicSjtzBo.getCloumnName()).append("= '").append(dicSjtzBo.getId()).append("'");
					}else{
						sql.append(" or ").append(dicSjtzBo.getCloumnName()).append("= '").append(dicSjtzBo.getId()).append("'");
					}
				}
				sql.append(")");
			}
		}
		// 获取查询数据的count然后进行分段查询
		int dataCount = dao.count(SjtzViewCorp.class,Cnd.wrap(sql.toString()));
		int queryNum = (int)Math.ceil((double)dataCount/PAGE_SIZE);
		List<SjtzViewCorp> sjtzViewCorpList = new ArrayList<SjtzViewCorp>();
		SimpleDateFormat currentYear = new SimpleDateFormat("yyyy");
		Date date = new Date();
		int listSize = 0;
		for (int i = 1; i < queryNum + 1; i++) {
			Pager pager = dao.createPager(queryNum, PAGE_SIZE);
			sjtzViewCorpList = dao.query(SjtzViewCorp.class, Cnd.wrap(sql.toString()),pager);
			listSize =  sjtzViewCorpList.size();
			// 插入数据到缓存表
			dao.execute(Sqls.create("delete from SJTZ_CORP_CACHE where RULE_ID='"+ruleId+"' and TZ_YEAR='"+currentYear.format(date)+"'"));
			for (int j = 0; j < listSize; j++) {
				CoreCacheBo cacheBo = new CoreCacheBo();
				// 调用反射实体类，实现实体类之间相同字段的反射赋值
				ClassReflection.reflectionAttr(sjtzViewCorpList.get(j), cacheBo);
				cacheBo.setId(UUID.randomUUID().toString().replace("-", ""));
				cacheBo.setRuleid(ruleId);
				cacheBo.setRulename(sjtzRulesBo.getRulename());
				cacheBo.setTzyear(currentYear.format(date));
				dao.insert(cacheBo);
			}	
		}
		//更改rule表中的执行状态和更新时间
		sjtzRulesBo.setExecuteType("1");
		sjtzRulesBo.setRuledate(date);
		dao.update(sjtzRulesBo);
	}
}
