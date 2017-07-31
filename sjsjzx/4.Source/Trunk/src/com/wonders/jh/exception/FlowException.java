package com.wonders.jh.exception;

/**
 * 工作流异常
 *
 */
public class FlowException extends RuntimeException {
	
	public FlowException() {
		super();
	}

	public FlowException(Throwable ex) {
		super(ex);
	}

	public FlowException(String msg) {
		super(msg);
	}

	public FlowException(String msg, Throwable ex) {
		super(msg, ex);
	}

}
