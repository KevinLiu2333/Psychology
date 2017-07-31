package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.fr.FrenchLightStemFilter;

/**
 * Factory for {@link FrenchLightStemFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_frlgtstem" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.LowerCaseFilterFactory"/&gt;
 *     &lt;filter class="solr.ElisionFilterFactory"/&gt;
 *     &lt;filter class="solr.FrenchLightStemFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 */
public class FrenchLightStemFilterFactory extends BaseTokenFilterFactory {
  public TokenStream create(TokenStream input) {
    return new FrenchLightStemFilter(input);
  }
}
