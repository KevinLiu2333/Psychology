package com.klsw.wk.hades.exception;

public class AjaxException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AjaxException() {
		super();
	}

	public AjaxException(String message) {
		super(message);
	}

	public AjaxException(Throwable cause) {
		super(cause);
	}

	public AjaxException(String message, Throwable cause) {
		super(message, cause);
	}

	public AjaxException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
    	super(message, cause, enableSuppression, writableStackTrace);
    }
}
