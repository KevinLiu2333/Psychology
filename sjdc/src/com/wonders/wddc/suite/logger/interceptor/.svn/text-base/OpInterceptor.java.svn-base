package com.wonders.wddc.suite.logger.interceptor;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Stopwatch;
import org.nutz.mvc.Mvcs;

import com.wonders.wddc.suite.logger.annotation.OpLogger;
import com.wonders.wddc.suite.logger.entity.LogOpBo;
import com.wonders.wddc.tiles.jk.entity.User;


@IocBean
public class OpInterceptor  implements MethodInterceptor {

	@Inject
	private LogOpBo logOpBo;

	public void filter(InterceptorChain chain) throws Throwable {

		//用于测试方法执行的时间
		Stopwatch watch = new Stopwatch();
		watch.start();
		//执行实际的方法之前调用
		Method callingMethod = chain.getCallingMethod();
		try {
			chain.doChain();
		} catch (Throwable e) {
			throw e;
		} finally {
			//执行完实际的方法后,记录日志
			if (callingMethod.getAnnotation(OpLogger.class) == null) { // 如果在方法中没有声明Logger注解,则不记录log
				return;
			}
			//获取用户信息
			User user = (User)Mvcs.getHttpSession().getAttribute("user");
			if (user == null) { // 如果用户为空,则不记录log
				return;
			}
			//获取注解信息
			String msg = callingMethod.getAnnotation(OpLogger.class).msg(); //Logger注解中的msg信息
			String opType = callingMethod.getAnnotation(OpLogger.class).type(); //Logger注解中的msg信息
			//获取返回值
			Object returnObj = chain.getReturn();
			if(returnObj !=null){
				Map<String, Object> returnMap = (Map<String, Object>)returnObj;
				Map<String, Object> logMap = (Map<String, Object>) returnMap.get("logMap");
				//获取busId
				if(logMap !=null){
					logOpBo.setOpBusId((String) logMap.get("busId"));
				}
			}
			//设置固定属性
			logOpBo.setOpTime(new Date());
			logOpBo.setOpUserId(user.getUserId());
			logOpBo.setOpUserName(user.getDisplayName());
			logOpBo.setUnitId(user.getUnitId());
			logOpBo.setUnitName(user.getUnitName());
			logOpBo.setOpType(opType);
			logOpBo.setOpDesc(msg);
			logOpBo.writeLog();
			
			watch.stop();
			System.out.println("用了" + watch.getDuration() + "毫秒");
		}
		
	}

}

