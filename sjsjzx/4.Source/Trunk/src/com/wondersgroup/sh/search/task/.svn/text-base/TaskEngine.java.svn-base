package com.wondersgroup.sh.search.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wondersgroup.sh.search.lucene.config.FetcherInfo;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;
import com.wondersgroup.sh.search.lucene.config.TaskInfo;
import com.wondersgroup.sh.search.util.ReflectHelper;

public class TaskEngine {
	private static final Logger logger = Logger.getLogger(TaskEngine.class);
	
	public static String PARAM_Configuration = "configuration";
	public static String PARAM_Fetcher = "fetcher";
	private static TaskEngine instance = new TaskEngine();
	
	private TaskEngine() {
	}

	public static TaskEngine getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		TaskEngine.getInstance().run(args[0]);
	}
	
	public void run(String configFilename) {
		LuceneConfiguration configuration = new LuceneConfiguration(configFilename);
		configuration.readConfiguration();
		Map<String, Object> taskContext = new HashMap<String, Object>();
		taskContext.put(PARAM_Configuration, configuration);
		
		List<FetcherInfo> fetchers = configuration.getFetchers();
		for(FetcherInfo fetcher : fetchers) {
			if( fetcher.getTask() != null ) {
				try {
					TaskInfo taskInfo = fetcher.getTask();
					logger.info("执行" + fetcher.getName() + "的task。参数：" + fetcher.getTask().getParams());
					Class taskClass = ReflectHelper.classForName(taskInfo.getImplClass());
					Task task = (Task)taskClass.newInstance();			
					if( ParameterizedTask.class.isAssignableFrom(taskClass) && taskInfo.getParams() != null) {
						((ParameterizedTask)task).setParameterValues(taskInfo.getParams());
					}
					
					taskContext.put(PARAM_Fetcher, fetcher);
					task.execute(taskContext);
				}
				catch(Throwable t) {
					logger.error("执行" + fetcher.getId() + "中的task错误。", t);
				}
			}
		}
	}
}
