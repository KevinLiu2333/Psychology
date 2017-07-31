package com.wondersgroup.sh.search.analysis;

import java.io.Reader;
import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.ngram.NGramTokenizer;

/**
 * Factory for {@link NGramTokenizer}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_ngrm" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.NGramTokenizerFactory" minGramSize="1" maxGramSize="2"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 */
public class NGramTokenizerFactory extends BaseTokenizerFactory {
    private int maxGramSize = 0;
    private int minGramSize = 0;
    
    /** Initializes the n-gram min and max sizes and the side from which one should start tokenizing. */
    @Override
    public void init(Map<String, String> args) {
        super.init(args);
        String maxArg = args.get("maxGramSize");
        maxGramSize = (maxArg != null ? Integer.parseInt(maxArg) : NGramTokenizer.DEFAULT_MAX_NGRAM_SIZE);

        String minArg = args.get("minGramSize");
        minGramSize = (minArg != null ? Integer.parseInt(minArg) : NGramTokenizer.DEFAULT_MIN_NGRAM_SIZE);
    }

    /** Creates the {@link TokenStream} of n-grams from the given {@link Reader}. */
    public NGramTokenizer create(Reader input) {
        return new NGramTokenizer(input, minGramSize, maxGramSize);
    }
}
