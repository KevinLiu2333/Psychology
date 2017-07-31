package com.wondersgroup.sh.search.analysis;

import org.apache.log4j.Logger;

public abstract class BaseTokenFilterFactory extends BaseTokenStreamFactory implements TokenFilterFactory {
  public static final Logger log = Logger.getLogger(BaseTokenFilterFactory.class);
}
