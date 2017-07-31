package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.de.GermanLightStemFilter;

/**
 * Factory for {@link GermanLightStemFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_delgtstem" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.LowerCaseFilterFactory"/&gt;
 *     &lt;filter class="solr.GermanLightStemFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 */
public class GermanLightStemFilterFactory extends BaseTokenFilterFactory {
  public TokenStream create(TokenStream input) {
    return new GermanLightStemFilter(input);
  }
}
