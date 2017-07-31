package com.wondersgroup.sh.search.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.lang.StringUtils;

public class IndexExecutor {
	private static IndexExecutor instance = new IndexExecutor();
	private static String INDEX_BAT_FILE = "index.bat";
	private String workingDirName;
	private String configFilename;
	private boolean executable = true;
	private IndexResultHandler resultHandler;
	private ExecuteException executeException;
	private ByteArrayOutputStream processOutputStream; 
	private ByteArrayOutputStream processErrorStream;
	
	private IndexExecutor() {
	}

	public static IndexExecutor getInstance() {
		return instance;
	}
	
	public synchronized void runIndex(String indexName, String fetcherId, boolean isIncrement) throws Exception {
		if( !executable )
			throw new Exception("已经有一个索引进程正在执行，请等待该进程结束...");

		executable = false;
		executeException = null;
		processOutputStream = new ByteArrayOutputStream();
		processErrorStream = new ByteArrayOutputStream();
		
		CommandLine cmdLine = new CommandLine(new File(workingDirName + "/" + INDEX_BAT_FILE));
		cmdLine.addArgument(configFilename);
		cmdLine.addArgument(indexName);
		cmdLine.addArgument(isIncrement ? "-incre" : "-full");
		if( StringUtils.isNotBlank(fetcherId) ) {
			cmdLine.addArgument(fetcherId);
		}
		try {
			System.out.println("命令脚本：" + cmdLine.toString());
			System.out.println("准备开始执行索引脚本...");
			resultHandler = this.execute(cmdLine);
			System.out.println("已成功执行索引脚本 ...");
		}
		catch(Exception ex) {
			System.out.println("执行索引脚本出错！");
			ex.printStackTrace();
			throw ex;
		}
		
		System.out.println("索引进程正在运行，请等待索引结束...");
		resultHandler.waitFor();
		System.out.println("索引进程结束了 ...");
	}
	
	public synchronized void destroyIndex() {
		if( this.resultHandler != null && this.resultHandler.watchdog != null ) {
			this.resultHandler.watchdog.destroyProcess();
			this.executable = true;
		}
	}
	
	public IndexResultHandler execute(CommandLine cmdLine) throws ExecuteException, IOException {
		ExecuteWatchdog watchdog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
		Executor executor = new DefaultExecutor();
		executor.setExitValue(0);
		executor.setWatchdog(watchdog);
		executor.setWorkingDirectory(new File(workingDirName));
		
		PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(processOutputStream, processErrorStream);
		executor.setStreamHandler(pumpStreamHandler);
		
		//ShutdownHookProcessDestroyer processDestroyer = new ShutdownHookProcessDestroyer();
		//executor.setProcessDestroyer(processDestroyer);
		
		IndexResultHandler resultHandler = new IndexResultHandler(watchdog);
		executor.execute(cmdLine, resultHandler);
		return resultHandler;
	}

	private class IndexResultHandler extends DefaultExecuteResultHandler {
		private ExecuteWatchdog watchdog;

		public IndexResultHandler(ExecuteWatchdog watchdog) {
			this.watchdog = watchdog;
		}

		public IndexResultHandler(int exitValue) {
			super.onProcessComplete(exitValue);
		}

		public void onProcessComplete(int exitValue) {
			super.onProcessComplete(exitValue);
			System.out.println("索引成功完成了 ...");
			executable = true;
			executeException = null;
			System.out.println(processOutputStream.toString());
		}

		public void onProcessFailed(ExecuteException e){
			super.onProcessFailed(e);
			if(watchdog != null && watchdog.killedProcess()) {
				System.err.println("索引进程超时了...");
			}
			else {
				System.err.println("索引进程出错: " + e.getMessage());
			}
			
			executable = true;
			executeException = e;
			System.err.println(processOutputStream.toString());
		}
	}
	
	public static void main(String[] args) throws Exception {
		IndexExecutor executor = IndexExecutor.getInstance();
		executor.setConfigFilename("splitHuge.cfg.xml");
		executor.setWorkingDirName("D:/search-core/dev/bin");
		executor.runIndex("ajfieowaqjjfasowefass", null, false);
	}
	
	public String getWorkingDirName() {
		return workingDirName;
	}

	public void setWorkingDirName(String workingDirName) {
		this.workingDirName = workingDirName;
	}

	public String getConfigFilename() {
		return configFilename;
	}

	public void setConfigFilename(String configFilename) {
		this.configFilename = configFilename;
	}

	public boolean isExecutable() {
		return executable;
	}

	public ExecuteException getExecuteException() {
		return executeException;
	}

	public ByteArrayOutputStream getProcessOutputStream() {
		return processOutputStream;
	}

	public ByteArrayOutputStream getProcessErrorStream() {
		return processErrorStream;
	}
}
