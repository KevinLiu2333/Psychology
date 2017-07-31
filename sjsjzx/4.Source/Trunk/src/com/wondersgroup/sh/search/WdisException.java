package com.wondersgroup.sh.search;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

public class WdisException extends RuntimeException {
	private static final long serialVersionUID = 4470520359127960683L;

	public enum ErrorCode {
    BAD_REQUEST( 400 ),
    UNAUTHORIZED( 401 ),
    FORBIDDEN( 403 ),
    NOT_FOUND( 404 ),
    SERVER_ERROR( 500 ),
    SERVICE_UNAVAILABLE( 503 ),
    UNKNOWN(0),
    CREATE_ANALYZER_ERROR(100),
    CREATE_SEARCHER_ERROR(110),
    READ_CONFIG_ERROR(120),
    INDEX_NOT_EXIST(200),
    REOPEN_INDEX_READER_ERROR(210),
    INDEX_NOT_IN_SITE(220);
    
    final int code;
    
    private ErrorCode( int c )
    {
      code = c;
    }
    public static ErrorCode getErrorCode(int c){
      for (ErrorCode err : values()) {
        if(err.code == c) return err;
      }
      return UNKNOWN;
    }
  };
  
  public boolean logged=false;

  public WdisException(ErrorCode code, String msg) {
    super(msg);
    this.code=code.code;
  }
  
  public WdisException(ErrorCode code, String msg, boolean alreadyLogged) {
    super(msg);
    this.code=code.code;
    this.logged=alreadyLogged;
  }

  public WdisException(ErrorCode code, String msg, Throwable th, boolean alreadyLogged) {
    super(msg,th);
    this.code=code.code;
    logged=alreadyLogged;
  }

  public WdisException(ErrorCode code, String msg, Throwable th) {
    this(code,msg,th,true);
  }

  public WdisException(ErrorCode code, Throwable th) {
    super(th);
    this.code=code.code;
    logged=true;
  }
  
  int code=0;
  public int code() { return code; }

  public void log(Logger log) { log(log,this); }
  public static void log(Logger log, Throwable e) {
    log.error(toStr(e));
    if (e instanceof WdisException) {
      ((WdisException)e).logged = true;
    }
  }

  public static void log(Logger log, String msg, Throwable e) {
    log.error(msg + ':' + toStr(e));
    if (e instanceof WdisException) {
      ((WdisException)e).logged = true;
    }
  }

  public static void logOnce(Logger log, String msg, Throwable e) {
    if (e instanceof WdisException) {
      if(((WdisException)e).logged) return;
    }
    if (msg!=null) log(log,msg,e);
    else log(log,e);
  }


  // public String toString() { return toStr(this); }  // oops, inf loop
  @Override
  public String toString() { return super.toString(); }

  public static String toStr(Throwable e) {
    CharArrayWriter cw = new CharArrayWriter();
    PrintWriter pw = new PrintWriter(cw);
    e.printStackTrace(pw);
    pw.flush();
    return cw.toString();
  }
}
