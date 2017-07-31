package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.KStemFilter;

/**
 * Factory for {@link KStemFilter}
 */
public class KStemFilterFactory extends BaseTokenFilterFactory {

  public TokenFilter create(TokenStream input) {
    return new KStemFilter(input);
  }
}
