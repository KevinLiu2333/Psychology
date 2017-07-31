package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.CharStream;

/**
* Factory for {@link HTMLStripCharFilter}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_html" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;charFilter class="solr.HTMLStripCharFilterFactory"/&gt;
 *     &lt;tokenizer class="solr.WhitespaceTokenizerFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre
 */
 public class HTMLStripCharFilterFactory extends BaseCharFilterFactory {

  public HTMLStripCharFilter create(CharStream input) {
    return new HTMLStripCharFilter(input);
  }

}
