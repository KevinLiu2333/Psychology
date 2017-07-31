package com.wondersgroup.sh.search.analysis;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.util.Version;

import com.wondersgroup.sh.search.common.ResourceLoader;
import com.wondersgroup.sh.search.lucene.LuceneUtils;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;
import com.wondersgroup.sh.search.util.StringUtil;

/**
 * Simple abstract implementation that handles init arg processing, is not really
 * a factory as it implements no interface, but removes code duplication
 * in its subclasses.
 * 
 */
abstract class BaseTokenStreamFactory {
  /** The init args */
  protected Map<String,String> args;
  
  /** the luceneVersion arg */
  protected Version luceneMatchVersion = null;

  public static final Logger log = Logger.getLogger(BaseTokenStreamFactory.class);

  public void init(Map<String,String> args) {
    this.args = args;
    String matchVersion = args.get(LuceneConfiguration.LUCENE_MATCH_VERSION_PARAM);
    if (matchVersion != null) {
      luceneMatchVersion = LuceneUtils.parseLuceneVersionString(matchVersion);
    }
  }

  public Map<String,String> getArgs() {
    return args;
  }
  
  /** this method can be called in the {@link TokenizerFactory#create(java.io.Reader)} 
   * or {@link TokenFilterFactory#create(org.apache.lucene.analysis.TokenStream)} methods,
   * to inform user, that for this factory a {@link #luceneMatchVersion} is required */
  protected final void assureMatchVersion() {
    if (luceneMatchVersion == null) {
      throw new RuntimeException("Configuration Error: Factory '" + this.getClass().getName() +
        "' needs a 'luceneMatchVersion' parameter");
    } else if (!luceneMatchVersion.onOrAfter(Version.LUCENE_30)) {
      log.warn(getClass().getSimpleName() + " is using deprecated " + luceneMatchVersion + 
        " emulation. You should at some point declare and reindex to at least 3.0, because " +
        "2.x emulation is deprecated and will be removed in 4.0");
    }
  }

  protected final void warnDeprecated(String message) {
    log.warn(getClass().getSimpleName() + " is deprecated. " + message);
  }
  
  // TODO: move these somewhere that tokenizers and others
  // can also use them...
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

  protected boolean getBoolean(String name, boolean defaultVal) {
    return getBoolean(name,defaultVal,true);
  }

  protected boolean getBoolean(String name, boolean defaultVal, boolean useDefault) {
    String s = args.get(name);
    if (s==null) {
      if (useDefault) return defaultVal;
      throw new RuntimeException("Configuration Error: missing parameter '" + name + "'");
    }
    return Boolean.parseBoolean(s);
  }

  protected CharArraySet getWordSet(ResourceLoader loader, String wordFiles, boolean ignoreCase) throws IOException {
    assureMatchVersion();
    List<String> files = StringUtil.splitFileNames(wordFiles);
    CharArraySet words = null;
    if (files.size() > 0) {
      // default stopwords list has 35 or so words, but maybe don't make it that big to start
      words = new CharArraySet(luceneMatchVersion, files.size() * 10, ignoreCase);
      for (String file : files) {
        List<String> wlist = loader.getLines(file.trim());
        words.addAll(StopFilter.makeStopSet(luceneMatchVersion, wlist, ignoreCase));
      }
    }
    return words;
  }
}
