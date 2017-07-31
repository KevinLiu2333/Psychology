package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishPossessiveFilter;

/**
 * Factory for {@link EnglishPossessiveFilter}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_enpossessive" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.LowerCaseFilterFactory"/&gt;
 *     &lt;filter class="solr.EnglishPossessiveFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 */
public class EnglishPossessiveFilterFactory extends BaseTokenFilterFactory {
  public TokenStream create(TokenStream input) {
    return new EnglishPossessiveFilter(input);
  }
}
