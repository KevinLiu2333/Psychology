package com.wondersgroup.sh.search.analysis;

import java.io.IOException;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.fr.ElisionFilter;

import com.wondersgroup.sh.search.common.ResourceLoader;
import com.wondersgroup.sh.search.common.ResourceLoaderAware;

/**
 * Factory for {@link ElisionFilter}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_elsn" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.LowerCaseFilterFactory"/&gt;
 *     &lt;filter class="solr.ElisionFilterFactory" articles="stopwordarticles.txt"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 */
public class ElisionFilterFactory extends BaseTokenFilterFactory implements ResourceLoaderAware {
  private CharArraySet articles;

  public void inform(ResourceLoader loader) {
    String articlesFile = args.get("articles");

    if (articlesFile != null) {
      try {
        articles = getWordSet(loader, articlesFile, false);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public ElisionFilter create(TokenStream input) {
    assureMatchVersion();
    return articles == null ? new ElisionFilter(luceneMatchVersion,input) : 
        new ElisionFilter(luceneMatchVersion,input,articles);
  }
}

