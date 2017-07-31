package com.wondersgroup.sh.search.analysis;

import java.util.Map;

import org.apache.lucene.analysis.TokenStream;

import com.wondersgroup.sh.search.WdisException;

/**
 * Factory for {@link TrimFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_trm" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.NGramTokenizerFactory"/&gt;
 *     &lt;filter class="solr.TrimFilterFactory" updateOffsets="false"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 * @version $Id$
 * @see TrimFilter
 */
public class TrimFilterFactory extends BaseTokenFilterFactory {
  
  protected boolean updateOffsets = false;
  
  @Override
  public void init(Map<String,String> args) {
    super.init( args );
    
    String v = args.get( "updateOffsets" );
    if( v != null ) {
      try {
        updateOffsets = Boolean.valueOf( v );
      }
      catch( Exception ex ) {
        throw new WdisException( WdisException.ErrorCode.BAD_REQUEST, "Error reading updateOffsets value.  Must be true or false.", ex );
      }
    }
  }
  
  public TrimFilter create(TokenStream input) {
    return new TrimFilter(input, updateOffsets);
  }
}
