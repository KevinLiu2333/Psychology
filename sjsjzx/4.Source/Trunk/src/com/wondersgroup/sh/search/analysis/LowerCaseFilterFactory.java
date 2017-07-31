package com.wondersgroup.sh.search.analysis;

import java.util.Map;

import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;

/**
 * Factory for {@link LowerCaseFilter}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_lwrcase" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.WhitespaceTokenizerFactory"/&gt;
 *     &lt;filter class="solr.LowerCaseFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 */
public class LowerCaseFilterFactory extends BaseTokenFilterFactory {
  @Override
  public void init(Map<String,String> args) {
    super.init(args);
    assureMatchVersion();
  }

  public LowerCaseFilter create(TokenStream input) {
    return new LowerCaseFilter(luceneMatchVersion,input);
  }
}
