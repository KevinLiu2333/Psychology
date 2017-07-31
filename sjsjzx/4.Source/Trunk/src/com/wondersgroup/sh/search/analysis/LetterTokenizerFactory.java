package com.wondersgroup.sh.search.analysis;

import java.io.Reader;
import java.util.Map;

import org.apache.lucene.analysis.LetterTokenizer;

/**
 * Factory for {@link LetterTokenizer}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_letter" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.LetterTokenizerFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 */
public class LetterTokenizerFactory extends BaseTokenizerFactory {

  @Override
  public void init(Map<String,String> args) {
    super.init(args);
    assureMatchVersion();
  }

  public LetterTokenizer create(Reader input) {
    return new LetterTokenizer(luceneMatchVersion, input);
  }
}
