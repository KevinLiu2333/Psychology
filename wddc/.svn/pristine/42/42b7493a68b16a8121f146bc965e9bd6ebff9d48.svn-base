package com.wonders.wddc.suite.logger.outlet;

import com.wonders.wddc.suite.logger.entity.LogChangeBo;
import com.wonders.wddc.suite.logger.entity.LogErrorBo;
import com.wonders.wddc.suite.logger.entity.LogLoginBo;
import com.wonders.wddc.suite.logger.entity.LogOpBo;
import com.wonders.wddc.suite.logger.entity.LogOpDetailBo;

public interface LoggerOutlet {
	/**
	 * 记录建议操作日志.
	 * @param logOpBo
	 */
	void logOp(LogOpBo logOpBo);
	/**
	 * 记录详细操作日志.
	 * @param logOpBo
	 */
	void logOpDetail(LogOpDetailBo logOpDetailBo);
	/**
	 * 记录错误信息.
	 * @param logErrorBo
	 */
	void logError(LogErrorBo logErrorBo);
	/**
	 * 记录用户登录登出.
	 * @param logLoginBo
	 */
	void logLogin(LogLoginBo logLoginBo);
	/**
	 * 记录字段字段修改变化.
	 * @param logChangeBo
	 */
	void logChange(LogChangeBo logChangeBo);
}
