package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.ASCIIFoldingFilter;
import org.apache.lucene.analysis.TokenStream;

/** 
 * Factory for {@link ASCIIFoldingFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_ascii" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.WhitespaceTokenizerFactory"/&gt;
 *     &lt;filter class="solr.ASCIIFoldingFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 */
public class ASCIIFoldingFilterFactory extends BaseTokenFilterFactory {
  public ASCIIFoldingFilter create(TokenStream input) {
    return new ASCIIFoldingFilter(input);
  }
}

