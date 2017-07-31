package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.ar.ArabicStemFilter;

/**
 * Factory for {@link ArabicStemFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_arstem" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.ArabicNormalizationFilterFactory"/&gt;
 *     &lt;filter class="solr.ArabicStemFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 * @version $Id$
 */
public class ArabicStemFilterFactory extends BaseTokenFilterFactory{
  public ArabicStemFilter create(TokenStream input) {
    return new ArabicStemFilter(input);
  }
}
