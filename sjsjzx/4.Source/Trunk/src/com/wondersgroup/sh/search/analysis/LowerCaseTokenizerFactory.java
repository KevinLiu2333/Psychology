package com.wondersgroup.sh.search.analysis;

import java.io.Reader;
import java.util.Map;

import org.apache.lucene.analysis.LowerCaseTokenizer;

/**
 * Factory for {@link LowerCaseTokenizer}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_lwrcase" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.LowerCaseTokenizerFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 */
public class LowerCaseTokenizerFactory extends BaseTokenizerFactory {
  @Override
  public void init(Map<String,String> args) {
    super.init(args);
    assureMatchVersion();
  }

  public LowerCaseTokenizer create(Reader input) {
    return new LowerCaseTokenizer(luceneMatchVersion,input);
  }
}
