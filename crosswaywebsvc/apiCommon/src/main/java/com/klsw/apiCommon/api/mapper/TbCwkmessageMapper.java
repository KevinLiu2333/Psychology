package com.klsw.apiCommon.api.mapper;

import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.TbCwkmessage;

import java.util.List;

public interface TbCwkmessageMapper extends MyMapper<TbCwkmessage> {
	/****
	 * 我的消息
	 * 
	 * @param messageType
	 * @return
	 */
	List<TbCwkmessage> messageList(Integer messageType, Integer cwkId);

	/****
	 * 系統消息詳情
	 * 
	 * @param messageId
	 * @return
	 */
	public TbCwkmessage selectByMessageId(Integer messageId);
}