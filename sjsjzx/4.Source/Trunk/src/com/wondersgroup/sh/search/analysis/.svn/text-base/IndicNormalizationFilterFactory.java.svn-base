package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.in.IndicNormalizationFilter;

/** 
 * Factory for {@link IndicNormalizationFilter}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_innormal" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.IndicNormalizationFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 */
public class IndicNormalizationFilterFactory extends BaseTokenFilterFactory {
  public TokenStream create(TokenStream input) {
    return new IndicNormalizationFilter(input);
  }
}
