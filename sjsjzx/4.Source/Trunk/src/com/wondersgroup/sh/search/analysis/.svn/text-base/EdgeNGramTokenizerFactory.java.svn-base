package com.wondersgroup.sh.search.analysis;

import java.io.Reader;
import java.util.Map;

import org.apache.lucene.analysis.ngram.EdgeNGramTokenizer;

/**
 * Creates new instances of {@link EdgeNGramTokenizer}.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_edgngrm" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.EdgeNGramTokenizerFactory" side="front" minGramSize="1" maxGramSize="1"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 */
public class EdgeNGramTokenizerFactory extends BaseTokenizerFactory {
    private int maxGramSize = 0;
    private int minGramSize = 0;
    private String side;

    @Override
    public void init(Map<String, String> args) {
        super.init(args);
        String maxArg = args.get("maxGramSize");
        maxGramSize = (maxArg != null ? Integer.parseInt(maxArg) : EdgeNGramTokenizer.DEFAULT_MAX_GRAM_SIZE);

        String minArg = args.get("minGramSize");
        minGramSize = (minArg != null ? Integer.parseInt(minArg) : EdgeNGramTokenizer.DEFAULT_MIN_GRAM_SIZE);

        side = args.get("side");
        if (side == null) {
            side = EdgeNGramTokenizer.Side.FRONT.getLabel();
        }
    }

    public EdgeNGramTokenizer create(Reader input) {
        return new EdgeNGramTokenizer(input, side, minGramSize, maxGramSize);
    }
}
