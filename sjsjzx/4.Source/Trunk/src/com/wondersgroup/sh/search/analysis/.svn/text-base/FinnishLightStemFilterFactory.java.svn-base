package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.fi.FinnishLightStemFilter;

/** 
 * Factory for {@link FinnishLightStemFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_filgtstem" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.LowerCaseFilterFactory"/&gt;
 *     &lt;filter class="solr.FinnishLightStemFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 */
public class FinnishLightStemFilterFactory extends BaseTokenFilterFactory {
  public TokenStream create(TokenStream input) {
    return new FinnishLightStemFilter(input);
  }
}
