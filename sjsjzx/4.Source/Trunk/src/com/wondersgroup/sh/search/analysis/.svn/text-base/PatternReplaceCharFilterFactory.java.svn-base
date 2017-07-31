package com.wondersgroup.sh.search.analysis;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.lucene.analysis.CharStream;

/**
 * Factory for {@link PatternReplaceCharFilter}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_ptnreplace" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;charFilter class="solr.PatternReplaceCharFilterFactory" pattern="([^a-z])" replacement=""
 *                 maxBlockChars="10000" blockDelimiters="|"/&gt;
 *     &lt;tokenizer class="solr.KeywordTokenizerFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 * 
 */
public class PatternReplaceCharFilterFactory extends BaseCharFilterFactory {
  private Pattern p;
  private String replacement;
  private int maxBlockChars;
  private String blockDelimiters;

  @Override
  public void init(Map<String, String> args) {
    super.init( args );
    try {
      p = Pattern.compile(args.get("pattern"));
    } catch (PatternSyntaxException e) {
      throw new RuntimeException
        ("Configuration Error: 'pattern' can not be parsed in " +
         this.getClass().getName(), e);
    }
    replacement = args.get( "replacement" );
    if( replacement == null )
      replacement = "";
    maxBlockChars = getInt( "maxBlockChars", PatternReplaceCharFilter.DEFAULT_MAX_BLOCK_CHARS );
    blockDelimiters = args.get( "blockDelimiters" );
  }

  public CharStream create(CharStream input) {
    return new PatternReplaceCharFilter( p, replacement, maxBlockChars, blockDelimiters, input );
  }
}
