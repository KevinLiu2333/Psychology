package com.wondersgroup.sh.search.analysis;

import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.id.IndonesianStemFilter;

/** 
 * Factory for {@link IndonesianStemFilter}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_idstem" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.LowerCaseFilterFactory"/&gt;
 *     &lt;filter class="solr.IndonesianStemFilterFactory" stemDerivational="true"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 */
public class IndonesianStemFilterFactory extends BaseTokenFilterFactory {
  private boolean stemDerivational = true;

  @Override
  public void init(Map<String, String> args) {
    super.init(args);
    stemDerivational = getBoolean("stemDerivational", true);
  }

  public TokenStream create(TokenStream input) {
    return new IndonesianStemFilter(input, stemDerivational);
  }
}
