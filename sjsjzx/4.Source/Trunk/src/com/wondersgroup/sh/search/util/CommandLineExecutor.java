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

public class CommandLineExecutor {
	protected String workingDirName;
	protected MyResultHandler resultHandler;
	protected ExecuteException executeException;
	protected ByteArrayOutputStream processOutputStream; 
	protected ByteArrayOutputStream processErrorStream;
	protected boolean executable = true;
	
	public synchronized void run(String executeFilename, String workingDirName, String[] arguments) throws Exception {
		if( !executable )
			throw new Exception("已经有一个进程正在执行，请等待该进程结束...");

		executable = false;		
		this.executeException = null;
		this.processOutputStream = new ByteArrayOutputStream();
		this.processErrorStream = new ByteArrayOutputStream();
		this.workingDirName = workingDirName;
		
		CommandLine cmdLine = new CommandLine(new File(executeFilename));
		cmdLine.addArguments(arguments);
		try {
			System.out.println("命令脚本：" + cmdLine.toString());
			System.out.println("准备开始执行命令脚本...");
			resultHandler = this.execute(cmdLine);
			System.out.println("已成功执行命令脚本 ...");
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
	
	private MyResultHandler execute(CommandLine cmdLine) throws ExecuteException, IOException {
		ExecuteWatchdog watchdog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
		Executor executor = new DefaultExecutor();
		executor.setExitValue(0);
		executor.setWatchdog(watchdog);
		executor.setWorkingDirectory(new File(workingDirName));
		
		PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(processOutputStream, processErrorStream);
		executor.setStreamHandler(pumpStreamHandler);
		
		MyResultHandler resultHandler = new MyResultHandler(watchdog);
		executor.execute(cmdLine, resultHandler);
		return resultHandler;
	}

	private class MyResultHandler extends DefaultExecuteResultHandler {
		private ExecuteWatchdog watchdog;

		public MyResultHandler(ExecuteWatchdog watchdog) {
			this.watchdog = watchdog;
		}

		public MyResultHandler(int exitValue) {
			super.onProcessComplete(exitValue);
		}

		public void onProcessComplete(int exitValue) {
			super.onProcessComplete(exitValue);
			System.out.println("命令成功完成了 ...");
			executable = true;
			executeException = null;
			System.out.println(processOutputStream.toString());
		}

		public void onProcessFailed(ExecuteException e){
			super.onProcessFailed(e);
			if(watchdog != null && watchdog.killedProcess()) {
				System.err.println("命令执行超时了...");
			}
			else {
				System.err.println("命令执行出错: " + e.getMessage());
			}

			executable = true;
			executeException = e;
			System.err.println(processOutputStream.toString());
		}
	}
	
	public static void main(String[] args) throws Exception {
		CommandLineExecutor executor = new CommandLineExecutor();
		String[] arguments = {"splitHuge.cfg.xml", "ajfieowaqjjfasowefass", "-full"};
		executor.run("D:/search-core/dev/bin/index.bat", "D:/search-core/dev/bin", arguments);
	}
	
	public String getWorkingDirName() {
		return workingDirName;
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
