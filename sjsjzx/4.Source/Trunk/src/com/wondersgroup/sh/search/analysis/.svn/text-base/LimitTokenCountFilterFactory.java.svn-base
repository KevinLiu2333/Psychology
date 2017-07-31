package com.wondersgroup.sh.search.analysis;

import java.util.Map;

import org.apache.lucene.analysis.LimitTokenCountFilter;
import org.apache.lucene.analysis.TokenStream;

/**
 * Factory for {@link LimitTokenCountFilter}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_lngthcnt" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.WhitespaceTokenizerFactory"/&gt;
 *     &lt;filter class="solr.LimitTokenCountFilterFactory" maxTokenCount="10"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 */
public class LimitTokenCountFilterFactory extends BaseTokenFilterFactory {

  int maxTokenCount;

  @Override
  public void init(Map<String, String> args) {
    super.init( args );
    maxTokenCount = Integer.parseInt( args.get( "maxTokenCount" ) );
  }

  public TokenStream create(TokenStream input) {
    return new LimitTokenCountFilter( input, maxTokenCount );
  }

}
