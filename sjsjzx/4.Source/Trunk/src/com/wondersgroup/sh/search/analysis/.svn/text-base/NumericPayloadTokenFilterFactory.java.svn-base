package com.wondersgroup.sh.search.analysis;

import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.payloads.NumericPayloadTokenFilter;

/** 
 * Factory for {@link NumericPayloadTokenFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_numpayload" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.WhitespaceTokenizerFactory"/&gt;
 *     &lt;filter class="solr.NumericPayloadTokenFilterFactory" payload="24" typeMatch="word"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 */
public class NumericPayloadTokenFilterFactory extends BaseTokenFilterFactory {
  private float payload;
  private String typeMatch;
  @Override
  public void init(Map<String, String> args) {
    super.init(args);
    payload = Float.parseFloat(args.get("payload"));
    typeMatch = args.get("typeMatch");
  }
  public NumericPayloadTokenFilter create(TokenStream input) {
    return new NumericPayloadTokenFilter(input,payload,typeMatch);
  }
}

