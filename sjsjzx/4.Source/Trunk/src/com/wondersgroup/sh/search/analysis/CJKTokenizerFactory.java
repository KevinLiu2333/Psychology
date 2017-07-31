package com.wondersgroup.sh.search.analysis;

import java.io.Reader;

import org.apache.lucene.analysis.cjk.CJKTokenizer;

public class CJKTokenizerFactory extends BaseTokenizerFactory {
  public CJKTokenizer create(Reader in) {
    return new CJKTokenizer(in);
  }
}

