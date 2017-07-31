package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.de.GermanStemFilter;

/** 
 * Factory for {@link GermanStemFilter}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_destem" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.LowerCaseFilterFactory"/&gt;
 *     &lt;filter class="solr.GermanStemFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 */
public class GermanStemFilterFactory extends BaseTokenFilterFactory {
  public GermanStemFilter create(TokenStream in) {
    return new GermanStemFilter(in);
  }
}

