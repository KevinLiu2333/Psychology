package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.el.GreekStemFilter;

/** 
 * Factory for {@link GreekStemFilter}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_gstem" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.GreekLowerCaseFilterFactory"/&gt;
 *     &lt;filter class="solr.GreekStemFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 */
public class GreekStemFilterFactory extends BaseTokenFilterFactory {

  public TokenStream create(TokenStream input) {
    return new GreekStemFilter(input);
  }

}
