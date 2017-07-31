package com.wondersgroup.sh.search.analysis;

import java.io.IOException;
import java.util.Set;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.FilteringTokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

/**
 * A TokenFilter that only keeps tokens with text contained in the
 * required words.  This filter behaves like the inverse of StopFilter.
 * 
 */
public final class KeepWordFilter extends FilteringTokenFilter {
  private final CharArraySet words;
  private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);

  /** @deprecated Use {@link #KeepWordFilter(boolean, TokenStream, CharArraySet)} instead */
  @Deprecated
  public KeepWordFilter(TokenStream in, Set<String> words, boolean ignoreCase ) {
    this(false, in, new CharArraySet(words, ignoreCase));
  }

  /** The words set passed to this constructor will be directly used by this filter
   * and should not be modified, */
  public KeepWordFilter(boolean enablePositionIncrements, TokenStream in, CharArraySet words) {
    super(enablePositionIncrements, in);
    this.words = words;
  }

  @Override
  public boolean accept() throws IOException {
    return words.contains(termAtt.buffer(), 0, termAtt.length());
  }
}
