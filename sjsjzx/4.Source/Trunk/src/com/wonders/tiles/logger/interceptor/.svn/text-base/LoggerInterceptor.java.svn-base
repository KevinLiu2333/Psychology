package com.wonders.tiles.logger.interceptor;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Stopwatch;
import org.nutz.mvc.Mvcs;

import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.logger.annotation.Logger;
import com.wonders.tiles.logger.entity.LoggerInfo;
import com.wonders.tiles.logger.entity.LoggerPro;

@IocBean
public class LoggerInterceptor implements MethodInterceptor {

	@Inject
	private LoggerInfo loggerInfo;

	public void filter(InterceptorChain chain) throws Throwable {

		//用于测试方法执行的时间
		Stopwatch watch = new Stopwatch();
		watch.start();
		//执行实际的方法之前调用
		Method callingMethod = chain.getCallingMethod();
		Object[] args = null;
		int index = 0;
		boolean findLog = false;
		if (callingMethod.getAnnotation(Logger.class) != null) { // 如果需要记录日志,为方法增加一个LoggerPro类型的参数
			args = chain.getArgs();
			Class<?>[] clazz = callingMethod.getParameterTypes();
			if (clazz != null) {
				for (Class<?> temp : clazz) {
					if (temp.equals(LoggerPro.class)) {
						args[index] = loggerInfo.getLoggerPro();
						findLog = true;
						break;
					}
					index++;
				}
			}
		}
		
		try {
			chain.doChain();
		} catch (Throwable e) {
			loggerInfo.setThrowMsg(e.getMessage());
			throw e;
		} finally {
			//执行完实际的方法后,记录日志
			if (callingMethod.getAnnotation(Logger.class) == null) { // 如果在方法中没有声明Logger注解,则不记录log
				return;
			}

			String description = callingMethod.getAnnotation(Logger.class).msg(); //Logger注解中的msg信息
			User user = (User) Mvcs.getHttpSession().getAttribute("user");
			String displayName = "";
			String userId = "";
			if (null != user) {
				displayName = user.getDisplayName();
				userId = user.getUserId();
			}
			Object returnObj = chain.getReturn();
			if (findLog) {
				args[index] = null; //如果LoggerPro对象存在,将其置为空
			}
			
			Object[] objs = args.clone();
			for (int i = 0; i < objs.length; i++) {
				Object object = objs[i];
				if (object instanceof HttpSession || object instanceof HttpServletRequest || object instanceof HttpServletResponse) {
					objs[i] = object.getClass().toString();
				}
			}
			
			loggerInfo.setArgs(Json.toJson(objs));
			loggerInfo.setCallingMethod(callingMethod.getName());
			loggerInfo.setCallingClass(callingMethod.getDeclaringClass().getName());
			loggerInfo.setRecordTime(new Date());
			loggerInfo.setDescription(description);
			loggerInfo.setReturnObj(returnObj != null ? Json.toJson(returnObj) : null);
			loggerInfo.setUserId(userId);
			loggerInfo.setUserName(displayName);
			loggerInfo.setId(UUID.randomUUID().toString());
			loggerInfo.write();
			loggerInfo.getLoggerPro().clearMsg();
			
			watch.stop();
			System.out.println("用了" + watch.getDuration() + "毫秒");
		}
		
	}

}
