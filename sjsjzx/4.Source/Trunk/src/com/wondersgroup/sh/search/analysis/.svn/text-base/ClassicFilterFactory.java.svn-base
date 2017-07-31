package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.ClassicFilter;

/**
 * Factory for {@link ClassicFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_clssc" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.ClassicTokenizerFactory"/&gt;
 *     &lt;filter class="solr.ClassicFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 *
 */
public class ClassicFilterFactory extends BaseTokenFilterFactory {
  public TokenFilter create(TokenStream input) {
    return new ClassicFilter(input);
  }
}
