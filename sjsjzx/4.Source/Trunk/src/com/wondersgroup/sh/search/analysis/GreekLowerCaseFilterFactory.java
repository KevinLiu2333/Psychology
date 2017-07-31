package com.wondersgroup.sh.search.analysis;

import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.el.GreekLowerCaseFilter;

import com.wondersgroup.sh.search.WdisException;
import com.wondersgroup.sh.search.WdisException.ErrorCode;

/** 
 * Factory for {@link GreekLowerCaseFilter}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_glc" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.GreekLowerCaseFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 */
public class GreekLowerCaseFilterFactory extends BaseTokenFilterFactory {
  @Override
  public void init(Map<String, String> args) {
    super.init(args);
    assureMatchVersion();
    if (args.containsKey("charset"))
      throw new WdisException(ErrorCode.SERVER_ERROR,
          "The charset parameter is no longer supported.  "
          + "Please process your documents as Unicode instead.");
  }

  public GreekLowerCaseFilter create(TokenStream in) {
    return new GreekLowerCaseFilter(luceneMatchVersion, in);
  }
}

