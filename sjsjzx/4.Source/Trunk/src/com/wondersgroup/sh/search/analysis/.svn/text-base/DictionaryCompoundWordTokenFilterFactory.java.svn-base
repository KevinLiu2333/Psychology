package com.wondersgroup.sh.search.analysis;

import java.io.IOException;
import java.util.Map;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.compound.CompoundWordTokenFilterBase;
import org.apache.lucene.analysis.compound.DictionaryCompoundWordTokenFilter;

import com.wondersgroup.sh.search.WdisException;
import com.wondersgroup.sh.search.common.ResourceLoader;
import com.wondersgroup.sh.search.common.ResourceLoaderAware;

/** 
 * Factory for {@link DictionaryCompoundWordTokenFilter}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_dictcomp" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.WhitespaceTokenizerFactory"/&gt;
 *     &lt;filter class="solr.DictionaryCompoundWordTokenFilterFactory" dictionary="dictionary.txt"
 *     	     minWordSize="5" minSubwordSize="2" maxSubwordSize="15" onlyLongestMatch="true"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 */
public class DictionaryCompoundWordTokenFilterFactory extends BaseTokenFilterFactory  implements ResourceLoaderAware {
  private CharArraySet dictionary;
  private String dictFile;
  private int minWordSize;
  private int minSubwordSize;
  private int maxSubwordSize;
  private boolean onlyLongestMatch;
  @Override
  public void init(Map<String, String> args) {
    super.init(args);
    assureMatchVersion();
    dictFile = args.get("dictionary");
    if (null == dictFile) {
      throw new WdisException( WdisException.ErrorCode.SERVER_ERROR, 
                               "Missing required parameter: dictionary");
    }

    minWordSize= getInt("minWordSize",CompoundWordTokenFilterBase.DEFAULT_MIN_WORD_SIZE);
    minSubwordSize= getInt("minSubwordSize",CompoundWordTokenFilterBase.DEFAULT_MIN_SUBWORD_SIZE);
    maxSubwordSize= getInt("maxSubwordSize",CompoundWordTokenFilterBase.DEFAULT_MAX_SUBWORD_SIZE);
    onlyLongestMatch = getBoolean("onlyLongestMatch",true);
  }
  public void inform(ResourceLoader loader) {
    try {
      dictionary = super.getWordSet(loader, dictFile, false);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  public DictionaryCompoundWordTokenFilter create(TokenStream input) {
    return new DictionaryCompoundWordTokenFilter(luceneMatchVersion,input,dictionary,minWordSize,minSubwordSize,maxSubwordSize,onlyLongestMatch);
  }
}

