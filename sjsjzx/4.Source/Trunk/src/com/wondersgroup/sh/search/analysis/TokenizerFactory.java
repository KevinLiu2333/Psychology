package com.wondersgroup.sh.search.analysis;

import java.io.Reader;
import java.util.Map;

import org.apache.lucene.analysis.Tokenizer;

public interface TokenizerFactory {
  /** <code>init</code> will be called just once, immediately after creation.
   * <p>The args are user-level initialization parameters that
   * may be specified when declaring a the factory in the configuration file.
   */
  public void init(Map<String,String> args);
  
  /**
   * Accessor method for reporting the args used to initialize this factory.
   * <p>
   * Implementations are <strong>strongly</strong> encouraged to return 
   * the contents of the Map passed to to the init method
   * </p>
   */
  public Map<String,String> getArgs();
  
  /** Creates a TokenStream of the specified input */
  public Tokenizer create(Reader input);
}

