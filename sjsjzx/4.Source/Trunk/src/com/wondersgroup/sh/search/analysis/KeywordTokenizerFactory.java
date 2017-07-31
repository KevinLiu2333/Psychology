package com.wondersgroup.sh.search.analysis;

import java.io.Reader;

import org.apache.lucene.analysis.KeywordTokenizer;

/**
 * Factory for {@link KeywordTokenizer}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_keyword" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.KeywordTokenizerFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 */
public class KeywordTokenizerFactory extends BaseTokenizerFactory {
  public KeywordTokenizer create(Reader input) {
    return new KeywordTokenizer(input);
  }
}
