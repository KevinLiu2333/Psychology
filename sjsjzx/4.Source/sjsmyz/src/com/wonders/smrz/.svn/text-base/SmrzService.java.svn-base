package com.wonders.smrz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;

import com.wonders.smrz.entity.Question;
import com.wonders.smrz.entity.QuestionResult;
import com.wonders.smrz.entity.RkkInfo;
import com.wonders.smrz.entity.SmrzItem;
import com.wonders.smrz.entity.SmrzItemBank;
import com.wonders.smrz.entity.SmrzLog;

@IocBean
public class SmrzService {
	
	public Dao getDao() {
		return Mvcs.ctx.getDefaultIoc().get(Dao.class);
	}
	/**
	 * 验证第一步： 验证人员信息是否存在，并且生成2个问题
	 * @param zjhm 身份照
	 * @param xm 姓名
	 * @return
	 */
	public String validPeople(String zjhm,String xm){
		//结果map
		Map<String, Object> result = new HashMap<String, Object>();
		//日志
		SmrzLog logSmrz = new SmrzLog();
		logSmrz.setStartTime(new Date());
		long startTime = System.currentTimeMillis();
		logSmrz.setOpParam("zjhm:"+zjhm+";xm:"+xm);
		try{
			//简单验证参数
			if(Strings.isBlank(zjhm)){
				result.put("result", "0");
				result.put("msg", "身份证信息有误");
			}
			if(Strings.isBlank(xm)){
				result.put("result", "0");
				result.put("msg", "姓名信息有误");
			}
			//查询人员
			Criteria cri = Cnd.cri();
			cri.where().and("zjhm", "=", zjhm.toUpperCase());
			cri.where().and("xm", "=", xm.trim());
			RkkInfo rkkInfo = getDao().fetch(RkkInfo.class,cri); 
			//人员不存在
			if(rkkInfo == null){
				result.put("result", "0");
				result.put("msg", "无人员信息");
			}else{//人员存在
				result.put("result", "1");
				Map<String, String> infoMap = new HashMap<String, String>();
				String colNames="";
				//数据处理
				//配偶
				String po = "";
				if(!Strings.isBlank(rkkInfo.getPoxm())){
					po = rkkInfo.getPoxm();
					colNames = getcolNames(colNames, "poxm");
				}
				infoMap.put("poxm", po);
				//门牌
				String mp = "";
				if(!Strings.isBlank(rkkInfo.getHjdyjmp())){
					mp = rkkInfo.getHjdyjmp();
					colNames = getcolNames(colNames, "hjdyjmp");
				}
				infoMap.put("hjdyjmp", mp);
				 //文化程度
				String whcd = "";
				if(!Strings.isBlank(rkkInfo.getWhcdhz())){
					whcd = rkkInfo.getWhcdhz();
					colNames = getcolNames(colNames, "whcdhz");
				}
				infoMap.put("whcdhz", whcd);
				//民族
				String mz = "";
				if(!Strings.isBlank(rkkInfo.getMzhz())){
					mz = rkkInfo.getMzhz().replaceAll("族","");
					colNames = getcolNames(colNames, "mzhz");
				}
				infoMap.put("mzhz", mz);
				//血型
				String xx = "";
				if(!Strings.isBlank(rkkInfo.getXxhz())){
					xx = rkkInfo.getXxhz().replaceAll("型","");
					colNames = getcolNames(colNames, "xxhz");
				}
				infoMap.put("xxhz", xx);
				List<SmrzItemBank> itemBankList = this.getItemBank(colNames);
				List<Question> questionList = this.getItem(zjhm, xm, itemBankList, infoMap);
				
				result.put("questions", questionList);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		String opResult = Json.toJson(result);
		logSmrz.setEndTime(new Date());
		long endTime = System.currentTimeMillis();
		logSmrz.setUsedTime(Integer.valueOf((endTime-startTime)+""));
		logSmrz.setOpResult(opResult);
		logSmrz.setStep("step1");
		getDao().insert(logSmrz);
		return opResult;
	}
	/**
	 * 验证第二步，验证两个问题的答案
	 * @param questionId1 问题1主键
	 * @param answer1 答案
	 * @param questionId2 问题2主键
	 * @param answer2 答案
	 * @return
	 */
	public String validAnswer(String questionId1,String answer1,String questionId2,String answer2){
		//结果map
		Map<String, Object> result = new HashMap<String, Object>();
		long startTime = System.currentTimeMillis();
		SmrzLog logSmrz = new SmrzLog();
		logSmrz.setStartTime(new Date());
		logSmrz.setOpParam("itemId1:"+questionId1+";answer1:"+answer1+";itemId2:"+questionId2+";answer2:"+answer2);
		//简单验证参数
		if(Strings.isBlank(questionId1)){
			result.put("result", "0");
			result.put("msg", "itemId1参数有误");
		}
		if(Strings.isBlank(answer1)){
			result.put("result", "0");
			result.put("msg", "answer1参数有误");
		}
		//简单验证参数
		if(Strings.isBlank(questionId2)){
			result.put("result", "0");
			result.put("msg", "itemId2参数有误");
		}
		if(Strings.isBlank(answer2)){
			result.put("result", "0");
			result.put("msg", "answer2参数有误");
		}
		//总的结果，两道题目如果全对为1，否则为0
		result.put("result", "1");
		//第一个问题
		List<QuestionResult> questionResultList = new ArrayList<QuestionResult>();
		QuestionResult questionResult1 = this.validAnswer(result,questionId1, answer1);
		questionResultList.add(questionResult1);
		//第二个问题
		QuestionResult questionResult2 = this.validAnswer(result,questionId2, answer2);
		questionResultList.add(questionResult2);
		result.put("questionResults", questionResultList);
		//写日志
		String opResult = Json.toJson(result);
		logSmrz.setEndTime(new Date());
		long endTime = System.currentTimeMillis();
		logSmrz.setUsedTime(Integer.valueOf((endTime-startTime)+""));
		logSmrz.setOpResult(opResult);
		logSmrz.setStep("step2");
		getDao().insert(logSmrz);
		return opResult;
		
	}
	
	
	/**
	 * 验证问题是否存在
	 * @param result 总的结果
	 * @param questionId 问题主键
	 * @param answer 问题答案
	 * @return
	 */
	private QuestionResult validAnswer(Map<String, Object> result,String questionId,String answer){
		SmrzItem smrzItem = getDao().fetch(SmrzItem.class,questionId); 
		QuestionResult questionResult = null;
		if(answer.equals(smrzItem.getAnswer())){
			questionResult = new QuestionResult(questionId,smrzItem.getQuestion(),"1");
		}else if("1".equals(smrzItem.getBankId()) && smrzItem.getAnswer().startsWith(answer)){//特殊处理姓氏
			questionResult = new QuestionResult(questionId,smrzItem.getQuestion(),"1");
		}
		else{
			questionResult = new QuestionResult(questionId,smrzItem.getQuestion(),"0");
			result.put("result", "0");
		}
		return questionResult;
	}
	/**
	 * 组装可以出题的列项
	 * @param colNames
	 * @param addColName
	 * @return
	 */
	private String getcolNames(String colNames,String addColName){
		if("".equals(colNames)){
			return addColName;
		}else{
			return colNames+","+addColName;
		}
	}
	/**
	 * 从题库查询题目
	 * @param colNames 可以出题的列项
	 * @return
	 */
	private List<SmrzItemBank> getItemBank(String colNames){
		Criteria cri = Cnd.cri();
		cri.where().and("colName", "in", colNames.split(","));
		List<SmrzItemBank> itemBankList = getDao().query(SmrzItemBank.class,cri); 
		return itemBankList;
	}
	
	/**
	 * 随机生成两道题目
	 * @param zjhm 身份证
	 * @param xm 姓名
	 * @param itemBankList 题库
	 * @param infoMap 答案map
	 * @return
	 */
	private List<Question> getItem(String zjhm,String xm,List<SmrzItemBank> itemBankList,Map<String, String> infoMap){
		List<Question> questionList = new ArrayList<Question>();
		long createNum = System.currentTimeMillis();
		int no1 = -1;
		int no2 = -1;
		Random ra =new Random();
		for (int i=0;i<30;i++){
			int index = ra.nextInt(itemBankList.size());
			System.out.println("index:"+index);
			if(no1 == -1){
				no1 = index;
				SmrzItem mrzItem1 = this.genSmrzItem(createNum, index, zjhm, xm, itemBankList, infoMap);
				mrzItem1 = getDao().insert(mrzItem1);
				Question question1 = new Question(mrzItem1.getItemId(),mrzItem1.getQuestion());
				questionList.add(question1);
			}else if(no1 != index){
				no2 = index;
				SmrzItem mrzItem2 = this.genSmrzItem(createNum, index, zjhm, xm, itemBankList, infoMap);
				mrzItem2 = getDao().insert(mrzItem2);
				Question question2 = new Question(mrzItem2.getItemId(),mrzItem2.getQuestion());
				questionList.add(question2);
			}
			if(no2 != -1){
				break;
			}
		}
		return questionList;
	}
	
	/**
	 * 生成题目
	 * @param createNum 生成批次
	 * @param index 随机位置
	 * @param zjhm 身份证
	 * @param xm 姓名
	 * @param itemBankList 题库
	 * @param infoMap 答案map
	 * @return
	 */
	private SmrzItem genSmrzItem(long createNum,int index,String zjhm,String xm,List<SmrzItemBank> itemBankList,Map<String, String> infoMap){
		SmrzItemBank itemBank2 = itemBankList.get(index);
		SmrzItem mrzItem2 = new SmrzItem();
		mrzItem2.setCreateTime(new Date());
		mrzItem2.setCreateNum(createNum+"");
		mrzItem2.setBankId(itemBank2.getBankId());
		mrzItem2.setQuestion(itemBank2.getQuestion());
		mrzItem2.setAnswer(infoMap.get(itemBank2.getColName()));
		mrzItem2.setXm(xm);
		mrzItem2.setZjhm(zjhm);
		return mrzItem2;
	}
	
	

}
