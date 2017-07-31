package com.wondersgroup.sh.search.analysis;

import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;

public abstract class SolrAnalyzer extends Analyzer {
  int posIncGap=0;
  
  public void setPositionIncrementGap(int gap) {
    posIncGap=gap;
  }

  @Override
  public int getPositionIncrementGap(String fieldName) {
    return posIncGap;
  }

  /** wrap the reader in a CharStream, if appropriate */
  public Reader charStream(Reader reader){
    return reader;
  }

  @Override
  public TokenStream tokenStream(String fieldName, Reader reader) {
    return getStream(fieldName, reader).getTokenStream();
  }

  public static class TokenStreamInfo {
    private final Tokenizer tokenizer;
    private final TokenStream tokenStream;
    public TokenStreamInfo(Tokenizer tokenizer, TokenStream tokenStream) {
      this.tokenizer = tokenizer;
      this.tokenStream = tokenStream;
    }
    public Tokenizer getTokenizer() { return tokenizer; }
    public TokenStream getTokenStream() { return tokenStream; }
  }


  public abstract TokenStreamInfo getStream(String fieldName, Reader reader);

  @Override
  public TokenStream reusableTokenStream(String fieldName, Reader reader) throws IOException {
    // if (true) return tokenStream(fieldName, reader);
    TokenStreamInfo tsi = (TokenStreamInfo)getPreviousTokenStream();
    if (tsi != null) {
      tsi.getTokenizer().reset(charStream(reader));
      // the consumer will currently call reset() on the TokenStream to hit all the filters.
      // this isn't necessarily guaranteed by the APIs... but is currently done
      // by lucene indexing in DocInverterPerField, and in the QueryParser
      return tsi.getTokenStream();
    } else {
      tsi = getStream(fieldName, reader);
      setPreviousTokenStream(tsi);
      return tsi.getTokenStream();
    }
  }
}
