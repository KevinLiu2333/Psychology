package com.wondersgroup.sh.search.analysis;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenStream;

import com.wondersgroup.sh.search.common.ResourceLoader;
import com.wondersgroup.sh.search.common.ResourceLoaderAware;

/**
 * Factory for {@link KeepWordFilter}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_keepword" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.WhitespaceTokenizerFactory"/&gt;
 *     &lt;filter class="solr.KeepWordFilterFactory" words="keepwords.txt" ignoreCase="false" enablePositionIncrements="false"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 */
public class KeepWordFilterFactory extends BaseTokenFilterFactory implements ResourceLoaderAware {

  @Override
  public void init(Map<String,String> args) {
    super.init(args);
    assureMatchVersion();
  }

  public void inform(ResourceLoader loader) {
    String wordFiles = args.get("words");
    ignoreCase = getBoolean("ignoreCase", false);
    enablePositionIncrements = getBoolean("enablePositionIncrements",false);

    if (wordFiles != null) {
      try {
        words = getWordSet(loader, wordFiles, ignoreCase);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private CharArraySet words;
  private boolean ignoreCase;
  private boolean enablePositionIncrements;

  /**
   * Set the keep word list.
   * NOTE: if ignoreCase==true, the words are expected to be lowercase
   */
  public void setWords(Set<String> words) {
    this.words = new CharArraySet(luceneMatchVersion, words, ignoreCase);
  }

  public void setIgnoreCase(boolean ignoreCase) {    
    if (words != null && this.ignoreCase != ignoreCase) {
      words = new CharArraySet(luceneMatchVersion, words, ignoreCase);
    }
    this.ignoreCase = ignoreCase;
  }

  public boolean isEnablePositionIncrements() {
    return enablePositionIncrements;
  }

  public boolean isIgnoreCase() {
    return ignoreCase;
  }

  public CharArraySet getWords() {
    return words;
  }

  public KeepWordFilter create(TokenStream input) {
    return new KeepWordFilter(enablePositionIncrements, input, words);
  }
}
