package com.wondersgroup.sh.search.analysis;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.Tokenizer;

import com.wondersgroup.sh.search.WdisException;

/**
 * Factory for {@link PatternTokenizer}.
 * This tokenizer uses regex pattern matching to construct distinct tokens
 * for the input stream.  It takes two arguments:  "pattern" and "group".
 * <p/>
 * <ul>
 * <li>"pattern" is the regular expression.</li>
 * <li>"group" says which group to extract into tokens.</li>
 *  </ul>
 * <p>
 * group=-1 (the default) is equivalent to "split".  In this case, the tokens will
 * be equivalent to the output from (without empty tokens):
 * {@link String#split(java.lang.String)}
 * </p>
 * <p>
 * Using group >= 0 selects the matching group as the token.  For example, if you have:<br/>
 * <pre>
 *  pattern = \'([^\']+)\'
 *  group = 0
 *  input = aaa 'bbb' 'ccc'
 *</pre>
 * the output will be two tokens: 'bbb' and 'ccc' (including the ' marks).  With the same input
 * but using group=1, the output would be: bbb and ccc (no ' marks)
 * </p>
 * <p>NOTE: This Tokenizer does not output tokens that are of zero length.</p>
 *
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_ptn" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.PatternTokenizerFactory" pattern="\'([^\']+)\'" group="1"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 * 
 * @see PatternTokenizer
 */
public class PatternTokenizerFactory extends BaseTokenizerFactory 
{
  public static final String PATTERN = "pattern";
  public static final String GROUP = "group";
 
  protected Pattern pattern;
  protected int group;
  
  /**
   * Require a configured pattern
   */
  @Override
  public void init(Map<String,String> args) 
  {
    this.args = args;
    String regex = args.get( PATTERN );
    if( regex == null ) {
      throw new WdisException( WdisException.ErrorCode.SERVER_ERROR, "missing required argument: "+PATTERN );
    }
    int flags = 0; // TODO? -- read flags from config CASE_INSENSITIVE, etc
    pattern = Pattern.compile( regex, flags );
    
    group = -1;  // use 'split'
    String g = args.get( GROUP );
    if( g != null ) {
      try {
        group = Integer.parseInt( g );
      }
      catch( Exception ex ) {
        throw new WdisException( WdisException.ErrorCode.SERVER_ERROR, "invalid group argument: "+g );
      }
    }
  }
  
  /**
   * Split the input using configured pattern
   */
  public Tokenizer create(final Reader in) {
    try {
      return new PatternTokenizer(in, pattern, group);
    } catch( IOException ex ) {
      throw new WdisException( WdisException.ErrorCode.SERVER_ERROR, ex );
    }
  }
  
  /**
   * This behaves just like String.split( ), but returns a list of Tokens
   * rather then an array of strings
   * NOTE: This method is not used in 1.4.
   * @deprecated
   */
  @Deprecated
  public static List<Token> split( Matcher matcher, String input )
  {
    int index = 0;
    int lastNonEmptySize = Integer.MAX_VALUE;
    ArrayList<Token> matchList = new ArrayList<Token>();

    // Add segments before each match found
    while(matcher.find()) {
      String match = input.subSequence(index, matcher.start()).toString();
      matchList.add( new Token( match, index, matcher.start()) );
      index = matcher.end();
      if( match.length() > 0 ) {
        lastNonEmptySize = matchList.size();
      }
    }

    // If no match is found, return the full string
    if (index == 0) {
      matchList.add( new Token( input, 0, input.length()) );
    }
    else { 
      String match = input.subSequence(index, input.length()).toString();
      matchList.add( new Token( match, index, input.length()) );
      if( match.length() > 0 ) {
        lastNonEmptySize = matchList.size();
      }
    }
    
    // Don't use trailing empty strings.  This behavior matches String.split();
    if( lastNonEmptySize < matchList.size() ) {
      return matchList.subList( 0, lastNonEmptySize );
    }
    return matchList;
  }
  
  /**
   * Create tokens from the matches in a matcher 
   * NOTE: This method is not used in 1.4.
   * @deprecated
   */
  @Deprecated
  public static List<Token> group( Matcher matcher, String input, int group )
  {
    ArrayList<Token> matchList = new ArrayList<Token>();
    while(matcher.find()) {
      Token t = new Token( 
        matcher.group(group), 
        matcher.start(group), 
        matcher.end(group) );
      matchList.add( t );
    }
    return matchList;
  }
}
