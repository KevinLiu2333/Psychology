package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.br.BrazilianStemFilter;

/** 
 * Factory for {@link BrazilianStemFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_brstem" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.LowerCaseFilterFactory"/&gt;
 *     &lt;filter class="solr.BrazilianStemFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 */
public class BrazilianStemFilterFactory extends BaseTokenFilterFactory {
  public BrazilianStemFilter create(TokenStream in) {
    return new BrazilianStemFilter(in);
  }
}

