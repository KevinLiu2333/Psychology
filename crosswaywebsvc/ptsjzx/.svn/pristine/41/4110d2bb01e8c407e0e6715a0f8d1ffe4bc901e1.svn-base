package com.wonders.mlgl.service;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.service.EntityService;

import com.wonders.mlgl.entity.TSequence;


@IocBean(fields = "dao")
@At("/service/commonService")
public class CommonService extends EntityService<Object> {

	/**
	 * 根据传入的sequenceId 如果存在则返回最大序列
	 * @param sequenceId
	 * @param length  返回序列号的长度
	 * @return
	 */
	public String getSequence(String sequenceId,int length){
		String returnNum = "";
		if(!Strings.isEmpty(sequenceId)){
			TSequence tSequence = dao().fetch(TSequence.class,sequenceId);
			if (null!=tSequence){
				String nowNum = tSequence.getSequenceNum();
				Integer num = Integer.valueOf(nowNum);
				Integer newNum = num+1;
				
				String nextNum = String.valueOf(newNum);
				returnNum = String.valueOf(newNum);
				tSequence.setSequenceNum(nextNum);
				dao().update(tSequence);
				while(returnNum.length()<length){
					returnNum="0"+returnNum;
				}
			}else{
				returnNum="1";
				tSequence = new TSequence();
				tSequence.setSequenceId(sequenceId);
				tSequence.setSequenceNum("1");
				dao().insert(tSequence);
				while(returnNum.length()<length){
					returnNum="0"+returnNum;
				}
			}
		}
		return returnNum;
	}
}
