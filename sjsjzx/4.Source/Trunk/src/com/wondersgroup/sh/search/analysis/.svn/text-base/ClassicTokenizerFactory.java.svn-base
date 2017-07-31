package com.wondersgroup.sh.search.analysis;

import java.io.Reader;
import java.util.Map;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.ClassicTokenizer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

/**
 * Factory for {@link ClassicTokenizer}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_clssc" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.ClassicTokenizerFactory" maxTokenLength="120"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 *
 */
public class ClassicTokenizerFactory extends BaseTokenizerFactory {
  private int maxTokenLength;

  @Override
  public void init(Map<String,String> args) {
    super.init(args);
    assureMatchVersion();
    maxTokenLength = getInt("maxTokenLength", 
                            StandardAnalyzer.DEFAULT_MAX_TOKEN_LENGTH);
  }

  public Tokenizer create(Reader input) {
    ClassicTokenizer tokenizer = new ClassicTokenizer(luceneMatchVersion, input); 
    tokenizer.setMaxTokenLength(maxTokenLength);
    return tokenizer;
  }
}
