package com.wondersgroup.sh.search.analysis;

import java.util.Map;

import org.apache.lucene.analysis.LengthFilter;
import org.apache.lucene.analysis.TokenStream;

/**
 * Factory for {@link LengthFilter}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_lngth" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.WhitespaceTokenizerFactory"/&gt;
 *     &lt;filter class="solr.LengthFilterFactory" min="0" max="1" enablePositionIncrements="false"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 */
public class LengthFilterFactory extends BaseTokenFilterFactory {
  int min,max;
  boolean enablePositionIncrements;
  public static final String MIN_KEY = "min";
  public static final String MAX_KEY = "max";

  @Override
  public void init(Map<String, String> args) {
    super.init(args);
    min=Integer.parseInt(args.get(MIN_KEY));
    max=Integer.parseInt(args.get(MAX_KEY));
    enablePositionIncrements = getBoolean("enablePositionIncrements",false);
  }
  
  public LengthFilter create(TokenStream input) {
    return new LengthFilter(enablePositionIncrements, input,min,max);
  }
}
