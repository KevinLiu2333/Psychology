package com.wondersgroup.sh.search.analysis;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.lucene.analysis.TokenStream;

/**
 * Factory for {@link PatternReplaceFilter}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_ptnreplace" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.KeywordTokenizerFactory"/&gt;
 *     &lt;filter class="solr.PatternReplaceFilterFactory" pattern="([^a-z])" replacement=""
 *             replace="all"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 * @see PatternReplaceFilter
 */
public class PatternReplaceFilterFactory extends BaseTokenFilterFactory {
  Pattern p;
  String replacement;
  boolean all = true;
  
  @Override
  public void init(Map<String, String> args) {
    super.init(args);
    try {
      p = Pattern.compile(args.get("pattern"));
    } catch (PatternSyntaxException e) {
      throw new RuntimeException
        ("Configuration Error: 'pattern' can not be parsed in " +
         this.getClass().getName(), e);
    }
    
    replacement = args.get("replacement");
    
    String r = args.get("replace");
    if (null != r) {
      if (r.equals("all")) {
        all = true;
      } else {
        if (r.equals("first")) {
          all = false;
        } else {
          throw new RuntimeException
            ("Configuration Error: 'replace' must be 'first' or 'all' in "
             + this.getClass().getName());
        }
      }
    }

  }
  public PatternReplaceFilter create(TokenStream input) {
    return new PatternReplaceFilter(input, p, replacement, all);
  }
}
