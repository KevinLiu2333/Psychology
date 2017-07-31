package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.hi.HindiNormalizationFilter;

/** 
 * Factory for {@link HindiNormalizationFilter}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_hinormal" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.HindiNormalizationFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 */
public class HindiNormalizationFilterFactory extends BaseTokenFilterFactory {
  public TokenStream create(TokenStream input) {
    return new HindiNormalizationFilter(input);
  }
}
