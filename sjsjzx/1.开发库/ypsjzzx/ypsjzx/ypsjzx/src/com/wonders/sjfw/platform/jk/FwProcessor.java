package com.wonders.sjfw.platform.jk;

import com.wonders.sjfw.platform.FwParam;
/**
 * 服务处理接口.
 *
 */
public interface FwProcessor {

	/**
	 * 验证通过的全局变量
	 */
	public final String VAILD_PARAM_PASS = "PASS";
	
	  /**
     * 判断参数是否通过.
     * @param spuParam 参数类
     * @return
     */
    public String validParam(FwParam spuParam);

    /**
     * 获取最终的操作的结果.
     * @param spuParam 参数类
     * @return
     */
    public String opResult(FwParam spuParam);

    /**
     * 获取调用示例.
     * @param spuParam 参数类
     * @return
     */
    public String opExample(FwParam spuParam);
}
