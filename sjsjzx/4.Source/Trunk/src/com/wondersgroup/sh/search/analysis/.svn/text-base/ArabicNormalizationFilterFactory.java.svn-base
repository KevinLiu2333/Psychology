package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.ar.ArabicNormalizationFilter;

/**
 * Factory for {@link ArabicNormalizationFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_arnormal" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.ArabicNormalizationFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 * @version $Id$
 */
public class ArabicNormalizationFilterFactory extends BaseTokenFilterFactory{

  public ArabicNormalizationFilter create(TokenStream input) {
    return new ArabicNormalizationFilter(input);
  }
}
