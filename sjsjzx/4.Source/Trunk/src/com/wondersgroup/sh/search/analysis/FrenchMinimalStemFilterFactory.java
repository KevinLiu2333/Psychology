package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.fr.FrenchMinimalStemFilter;

/** 
 * Factory for {@link FrenchMinimalStemFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_frminstem" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.LowerCaseFilterFactory"/&gt;
 *     &lt;filter class="solr.ElisionFilterFactory"/&gt;
 *     &lt;filter class="solr.FrenchMinimalStemFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 */
public class FrenchMinimalStemFilterFactory extends BaseTokenFilterFactory {
  public TokenStream create(TokenStream input) {
    return new FrenchMinimalStemFilter(input);
  }
}
