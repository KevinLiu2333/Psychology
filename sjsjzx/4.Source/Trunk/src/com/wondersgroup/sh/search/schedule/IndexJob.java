package com.wondersgroup.sh.search.schedule;

import org.apache.log4j.Logger;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.wondersgroup.sh.search.IndexPortal;

/**
 * <bean name="indexJob" class="org.springframework.scheduling.quartz.JobDetailBean">
 * 	<property name="jobClass" value="com.wondersgroup.sh.search.schedule.IndexJob"/>
 * 	<property name="jobDataAsMap">
 *  	<map>
 *    	<entry key="indexArgument" value="-configfile pdfgw-search-cfg.xml -index article -full"/>
 *   </map>
 * 	</property>
 * </bean>
 * <bean id="indexTrigger" class="org.springframework.scheduling.quartz.CronTriggerBean">
 * 	<property name="jobDetail" ref="indexJob"/>
 * 	<property name="cronExpression" value="0 0 22 * * ?"/>
 * </bean>
 *   
 *	<bean class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
 *	  <property name="triggers">
 *	    <list>
 *	      <ref bean="indexTrigger"/>
 *	    </list>
 *	  </property>
 *	</bean>
 * 
 */
public class IndexJob extends QuartzJobBean {
	private static final Logger logger = Logger.getLogger(IndexJob.class);

	private String indexArgument;
	
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("开始创建索引。参数=" + this.indexArgument);
		String[] args = indexArgument.split("\\s");
		try {
			IndexPortal.main(args);
		}
		catch(Exception ex) {
			throw new JobExecutionException("创建索引出错。", ex);
		}
		logger.info("完成创建索引。");
	}

	public String getIndexArgument() {
		return indexArgument;
	}

	public void setIndexArgument(String indexArgument) {
		this.indexArgument = indexArgument;
	}
}
