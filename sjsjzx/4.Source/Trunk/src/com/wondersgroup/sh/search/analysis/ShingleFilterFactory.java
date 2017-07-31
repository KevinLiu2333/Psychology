package com.wondersgroup.sh.search.analysis;

import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.shingle.ShingleFilter;

import com.wondersgroup.sh.search.WdisException;
import com.wondersgroup.sh.search.WdisException.ErrorCode;

/** 
 * Factory for {@link ShingleFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_shingle" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.WhitespaceTokenizerFactory"/&gt;
 *     &lt;filter class="solr.ShingleFilterFactory" minShingleSize="2" maxShingleSize="2"
 *             outputUnigrams="true" outputUnigramsIfNoShingles="false" tokenSeparator=" "/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 */
public class ShingleFilterFactory extends BaseTokenFilterFactory {
  private int minShingleSize;
  private int maxShingleSize;
  private boolean outputUnigrams;
  private boolean outputUnigramsIfNoShingles;
  private String tokenSeparator;

  @Override
  public void init(Map<String, String> args) {
    super.init(args);
    maxShingleSize = getInt("maxShingleSize", 
                            ShingleFilter.DEFAULT_MAX_SHINGLE_SIZE);
    if (maxShingleSize < 2) {
      throw new WdisException(ErrorCode.SERVER_ERROR,
                              "Invalid maxShingleSize (" + maxShingleSize
                              + ") - must be at least 2");
    }
    minShingleSize = getInt("minShingleSize",
                            ShingleFilter.DEFAULT_MIN_SHINGLE_SIZE);
    if (minShingleSize < 2) {
      throw new WdisException(ErrorCode.SERVER_ERROR,
                              "Invalid minShingleSize (" + minShingleSize
                              + ") - must be at least 2");
    }
    if (minShingleSize > maxShingleSize) {
      throw new WdisException(ErrorCode.SERVER_ERROR,
                              "Invalid minShingleSize (" + minShingleSize
                              + ") - must be no greater than maxShingleSize ("
                              + maxShingleSize + ")");
    }
    outputUnigrams = getBoolean("outputUnigrams", true);
    outputUnigramsIfNoShingles = getBoolean("outputUnigramsIfNoShingles", false);
    tokenSeparator = args.containsKey("tokenSeparator")
                     ? args.get("tokenSeparator")
                     : ShingleFilter.TOKEN_SEPARATOR;
  }
  public ShingleFilter create(TokenStream input) {
    ShingleFilter r = new ShingleFilter(input, minShingleSize, maxShingleSize);
    r.setOutputUnigrams(outputUnigrams);
    r.setOutputUnigramsIfNoShingles(outputUnigramsIfNoShingles);
    r.setTokenSeparator(tokenSeparator);
    return r;
  }
}

