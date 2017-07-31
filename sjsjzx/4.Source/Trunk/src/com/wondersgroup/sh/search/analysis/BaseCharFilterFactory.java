package com.wondersgroup.sh.search.analysis;

import java.util.Map;

import org.apache.log4j.Logger;

public abstract class BaseCharFilterFactory implements CharFilterFactory {
  public static final Logger log = Logger.getLogger(BaseCharFilterFactory.class);

  /** The init args */
  protected Map<String,String> args;

  public Map<String, String> getArgs() {
    return args;
  }

  public void init(Map<String, String> args) {
    this.args = args;
  }

  protected int getInt(String name) {
    return getInt(name,-1,false);
  }

  protected int getInt(String name, int defaultVal) {
    return getInt(name,defaultVal,true);
  }

  protected int getInt(String name, int defaultVal, boolean useDefault) {
    String s = args.get(name);
    if (s==null) {
      if (useDefault) return defaultVal;
      throw new RuntimeException("Configuration Error: missing parameter '" + name + "'");
    }
    return Integer.parseInt(s);
  }
}
