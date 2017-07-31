package com.wondersgroup.sh.search.analysis;

import java.io.Reader;
import java.util.Map;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.path.PathHierarchyTokenizer;
import org.apache.lucene.analysis.path.ReversePathHierarchyTokenizer;

/**
 * Factory for {@link PathHierarchyTokenizer}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_path" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.PathHierarchyTokenizerFactory" delimiter="\" replace="/"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 */
public class PathHierarchyTokenizerFactory extends BaseTokenizerFactory {
  private char delimiter;
  private char replacement;
  private boolean reverse = false;
  private int skip =  PathHierarchyTokenizer.DEFAULT_SKIP;
  
  /**
   * Require a configured pattern
   */
  @Override
  public void init(Map<String,String> args){
    super.init( args );
    
    String v = args.get( "delimiter" );
    if( v != null ){
      if( v.length() != 1 ){
        throw new IllegalArgumentException( "delimiter should be a char. \"" + v + "\" is invalid" );
      }
      else{
        delimiter = v.charAt(0);
      }
    }
    else{
      delimiter = PathHierarchyTokenizer.DEFAULT_DELIMITER;
    }
    
    v = args.get( "replace" );
    if( v != null ){
      if( v.length() != 1 ){
        throw new IllegalArgumentException( "replace should be a char. \"" + v + "\" is invalid" );
      }
      else{
        replacement = v.charAt(0);
      }
    }
    else{
      replacement = delimiter;
    }
    
    v = args.get( "reverse" );
    if( v != null ){
      reverse = "true".equals( v );
    }

    v = args.get( "skip" );
    if( v != null ){
      skip = Integer.parseInt( v );
    }
  }

  public Tokenizer create(Reader input) {
    if( reverse ) {
      return new ReversePathHierarchyTokenizer(input, delimiter, replacement, skip);
    }
    return new PathHierarchyTokenizer(input, delimiter, replacement, skip);
  }
}


