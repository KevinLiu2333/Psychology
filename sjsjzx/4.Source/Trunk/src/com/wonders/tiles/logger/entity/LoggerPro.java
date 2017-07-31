package com.wonders.tiles.logger.entity;

public class LoggerPro {
	
	private StringBuffer buffer;

	/**
	 * 设置方法执行过程中的信息
	 * @param processMsg
	 */
	public void appendProcessMsg(String processMsg) {
		if (null == buffer)
			buffer = new StringBuffer();
		buffer.append(processMsg);
	}

	/**
	 * 获取方法执行过程中的信息
	 * @return
	 */
	public String getProcessMsg() {
		if (buffer != null)
			return buffer.toString();
		return null;
	}
	
	/**
	 * 清除方法执行过程中的信息
	 */
	public void clearMsg() {
		buffer = new StringBuffer();
	}
	
}
