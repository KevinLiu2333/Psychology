package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.bg.BulgarianStemFilter;

/** 
 * Factory for {@link BulgarianStemFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_bgstem" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.LowerCaseFilterFactory"/&gt;
 *     &lt;filter class="solr.BulgarianStemFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 * @version $Id$
 */
public class BulgarianStemFilterFactory extends BaseTokenFilterFactory {
  public TokenStream create(TokenStream input) {
    return new BulgarianStemFilter(input);
  }
}
