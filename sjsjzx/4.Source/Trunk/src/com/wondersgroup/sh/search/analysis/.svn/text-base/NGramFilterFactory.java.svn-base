package com.wondersgroup.sh.search.analysis;

import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.ngram.NGramTokenFilter;

/**
 * Factory for {@link NGramTokenFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_ngrm" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.WhitespaceTokenizerFactory"/&gt;
 *     &lt;filter class="solr.NGramFilterFactory" minGramSize="1" maxGramSize="2"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 */
public class NGramFilterFactory extends BaseTokenFilterFactory {
  private int maxGramSize = 0;

  private int minGramSize = 0;

  /** Initialize the n-gram min and max sizes and the side from which one should start tokenizing. */
  @Override
  public void init(Map<String, String> args) {
    super.init(args);
    String maxArg = args.get("maxGramSize");
    maxGramSize = (maxArg != null ? Integer.parseInt(maxArg)
        : NGramTokenFilter.DEFAULT_MAX_NGRAM_SIZE);

    String minArg = args.get("minGramSize");
    minGramSize = (minArg != null ? Integer.parseInt(minArg)
        : NGramTokenFilter.DEFAULT_MIN_NGRAM_SIZE);
  }

  public NGramTokenFilter create(TokenStream input) {
    return new NGramTokenFilter(input, minGramSize, maxGramSize);
  }
}
