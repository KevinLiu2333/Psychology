package com.wondersgroup.sh.search.analysis;

import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.payloads.DelimitedPayloadTokenFilter;
import org.apache.lucene.analysis.payloads.FloatEncoder;
import org.apache.lucene.analysis.payloads.IdentityEncoder;
import org.apache.lucene.analysis.payloads.IntegerEncoder;
import org.apache.lucene.analysis.payloads.PayloadEncoder;

import com.wondersgroup.sh.search.WdisException;
import com.wondersgroup.sh.search.common.ResourceLoader;
import com.wondersgroup.sh.search.common.ResourceLoaderAware;

/**
 *
 * Factory for {@link DelimitedPayloadTokenFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_dlmtd" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.WhitespaceTokenizerFactory"/&gt;
 *     &lt;filter class="solr.DelimitedPayloadTokenFilterFactory" encoder="float" delimiter="|"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 * 
 */
public class DelimitedPayloadTokenFilterFactory extends BaseTokenFilterFactory implements ResourceLoaderAware {
  public static final String ENCODER_ATTR = "encoder";
  public static final String DELIMITER_ATTR = "delimiter";

  private PayloadEncoder encoder;
  private char delimiter = '|';

  public DelimitedPayloadTokenFilter create(TokenStream input) {
    return new DelimitedPayloadTokenFilter(input, delimiter, encoder);
  }

  @Override
  public void init(Map<String, String> args) {
    super.init(args);
  }

  public void inform(ResourceLoader loader) {
    String encoderClass = args.get(ENCODER_ATTR);
    if (encoderClass.equals("float")){
      encoder = new FloatEncoder();
    } else if (encoderClass.equals("integer")){
      encoder = new IntegerEncoder();
    } else if (encoderClass.equals("identity")){
      encoder = new IdentityEncoder();
    } else {
      encoder = (PayloadEncoder) loader.newInstance(encoderClass);
    }

    String delim = args.get(DELIMITER_ATTR);
    if (delim != null){
      if (delim.length() == 1) {
        delimiter = delim.charAt(0);
      } else{
        throw new WdisException(WdisException.ErrorCode.SERVER_ERROR, "Delimiter must be one character only");
      }
    }
  }
}
