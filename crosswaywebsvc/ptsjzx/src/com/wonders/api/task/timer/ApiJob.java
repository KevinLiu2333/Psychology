package com.wonders.api.task.timer;

import java.util.List;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.api.entity.ApiService;
import com.wonders.api.task.ApiWarning;
import com.wonders.sms.common.SmsMould;
import com.wonders.util.PropertyUtils;
import com.wonders.utils.sendMailUtils;
@IocBean
public class ApiJob implements Job{
	
	@Override
	public void execute(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {
		ApiWarning apiWarning  = Mvcs.ctx.getDefaultIoc().get(ApiWarning.class);
		List<ApiService> apilist = apiWarning.getApi();
		if(apilist.size()>0){
			String user = PropertyUtils.getProperty("ApiWarnEmails");
			String[] users = user.split(";");
			StringBuffer buffer = new StringBuffer();
			for(ApiService apiService : apilist){
				buffer.append(apiService.getServiceName()).append(";");
			}
			final String text = "以下接口本月无调用记录：\n"+buffer.toString();
			for(final String email:users){
				if(!Strings.isEmpty(email)){
					new Thread(new Runnable() {
						public void run() {
							sendMailUtils.sendMail("ptqmail.ptq.sh.gov.cn", SmsMould.E_Mail, email, "接口调用预警", text);
						}
					}).start();
				}
				
			}
			
		
		}
		
		
	}

}
