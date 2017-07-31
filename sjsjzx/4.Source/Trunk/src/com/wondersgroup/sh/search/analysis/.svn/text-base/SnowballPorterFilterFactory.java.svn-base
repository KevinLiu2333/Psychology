package com.wondersgroup.sh.search.analysis;

import java.io.IOException;
import java.util.Map;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.KeywordMarkerFilter;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.tartarus.snowball.SnowballProgram;

import com.wondersgroup.sh.search.common.ResourceLoader;
import com.wondersgroup.sh.search.common.ResourceLoaderAware;

/**
 * Factory for {@link SnowballFilter}, with configurable language
 * <p>
 * Note: Use of the "Lovins" stemmer is not recommended, as it is implemented with reflection.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_snowballstem" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.LowerCaseFilterFactory"/&gt;
 *     &lt;filter class="solr.SnowballPorterFilterFactory" protected="protectedkeyword.txt" language="English"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 * 
 * @version $Id: SnowballPorterFilterFactory.java 1074243 2011-02-24 18:07:16Z rmuir $
 */
public class SnowballPorterFilterFactory extends BaseTokenFilterFactory implements ResourceLoaderAware {
  public static final String PROTECTED_TOKENS = "protected";

  private String language = "English";
  private Class<?> stemClass;


  public void inform(ResourceLoader loader) {
    String wordFiles = args.get(PROTECTED_TOKENS);
    if (wordFiles != null) {
      try {
        protectedWords = getWordSet(loader, wordFiles, false);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private CharArraySet protectedWords = null;

  @Override
  public void init(Map<String, String> args) {
    super.init(args);
    final String cfgLanguage = args.get("language");
    if(cfgLanguage!=null) language = cfgLanguage;

    try {
      stemClass = Class.forName("org.tartarus.snowball.ext." + language + "Stemmer");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Can't find class for stemmer language " + language, e);
    }
  }
  
  public TokenFilter create(TokenStream input) {
    SnowballProgram program;
    try {
      program = (SnowballProgram)stemClass.newInstance();
    } catch (Exception e) {
      throw new RuntimeException("Error instantiating stemmer for language " + language + "from class " +stemClass, e);
    }

    if (protectedWords != null)
      input = new KeywordMarkerFilter(input, protectedWords);
    return new SnowballFilter(input, program);
  }
}

