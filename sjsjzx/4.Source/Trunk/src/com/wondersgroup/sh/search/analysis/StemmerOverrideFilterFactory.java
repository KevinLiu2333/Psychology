package com.wondersgroup.sh.search.analysis;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.CharArrayMap;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.miscellaneous.StemmerOverrideFilter;

import com.wondersgroup.sh.search.common.ResourceLoader;
import com.wondersgroup.sh.search.common.ResourceLoaderAware;
import com.wondersgroup.sh.search.util.StringUtil;

/**
 * Factory for {@link StemmerOverrideFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_dicstem" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.WhitespaceTokenizerFactory"/&gt;
 *     &lt;filter class="solr.StemmerOverrideFilterFactory" dictionary="dictionary.txt" ignoreCase="false"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 * @version $Id
 */
public class StemmerOverrideFilterFactory extends BaseTokenFilterFactory implements ResourceLoaderAware {
  private CharArrayMap<String> dictionary = null;
  private boolean ignoreCase;

  public void inform(ResourceLoader loader) {
    String dictionaryFiles = args.get("dictionary");
    ignoreCase = getBoolean("ignoreCase", false);
    if (dictionaryFiles != null) {
      assureMatchVersion();
      List<String> files = StringUtil.splitFileNames(dictionaryFiles);
      try {
        if (files.size() > 0) {
          dictionary = new CharArrayMap<String>(luceneMatchVersion, 
              files.size() * 10, ignoreCase);
          for (String file : files) {
            List<String> list = loader.getLines(file.trim());
            for (String line : list) {
              String[] mapping = line.split("\t", 2);
              dictionary.put(mapping[0], mapping[1]);
            }
          }
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public boolean isIgnoreCase() {
    return ignoreCase;
  }

  public TokenStream create(TokenStream input) {
    return dictionary == null ? input : new StemmerOverrideFilter(luceneMatchVersion, input, dictionary);
  }
}
