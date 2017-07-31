package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.TokenStream;

/**
 * Factory for {@link HyphenatedWordsFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_hyphn" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.WhitespaceTokenizerFactory"/&gt;
 *     &lt;filter class="solr.HyphenatedWordsFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 */
public class HyphenatedWordsFilterFactory extends BaseTokenFilterFactory {
	public HyphenatedWordsFilter create(TokenStream input) {
		return new HyphenatedWordsFilter(input);
	}
}
