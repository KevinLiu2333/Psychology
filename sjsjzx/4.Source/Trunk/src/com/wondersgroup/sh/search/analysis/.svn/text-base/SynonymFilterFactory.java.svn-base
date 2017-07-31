package com.wondersgroup.sh.search.analysis;

import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.synonym.SynonymFilter;

import com.wondersgroup.sh.search.common.ResourceLoader;
import com.wondersgroup.sh.search.common.ResourceLoaderAware;

/**
 * Factory for {@link SynonymFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_synonym" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.WhitespaceTokenizerFactory"/&gt;
 *     &lt;filter class="solr.SynonymFilterFactory" synonyms="synonyms.txt" 
 *             format="solr" ignoreCase="false" expand="true" 
 *             tokenizerFactory="solr.WhitespaceTokenizerFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 */
public class SynonymFilterFactory extends BaseTokenFilterFactory implements ResourceLoaderAware {
  private BaseTokenFilterFactory delegator;

  @Override
  public void init(Map<String,String> args) {
    super.init(args);
    assureMatchVersion();
    delegator.init(args);
  }

  public TokenStream create(TokenStream input) {
    assert delegator != null : "init() was not called!";
    return delegator.create(input);
  }

  public void inform(ResourceLoader loader) {
    assert delegator != null : "init() was not called!";
    ((ResourceLoaderAware) delegator).inform(loader);
  }
}
