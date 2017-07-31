package com.wondersgroup.sh.search.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.KeywordAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.RandomIndexWriter;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.queryParser.QueryParser.Operator;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.LuceneTestCase;
import org.junit.Test;

import com.wondersgroup.sh.search.analysis.CJKDigitAnalyzer;

public class TestLuceneSearch extends LuceneTestCase {
	@Test
  public void testFunctionName() throws Exception {
  	Analyzer analyzer = null;
  	//analyzer = new CJKDigitAnalyzer(TEST_VERSION_CURRENT);
    analyzer = new KeywordAnalyzer();
  	Directory dir = newDirectory();
    RandomIndexWriter w = new RandomIndexWriter(
                               random,
                               dir,
                               newIndexWriterConfig(TEST_VERSION_CURRENT, analyzer));
    // 0
    Document doc = new Document();
    doc.add(new Field("FUNCTION_NAME", "abcd-1024", Field.Store.YES, Field.Index.ANALYZED));
    doc.add(new Field("CONTENT", "\"迎世博600天行动计划\"北蔡绿川地区污水纳管工程", Field.Store.YES, Field.Index.ANALYZED));
    w.addDocument(doc);

    // 1
    doc = new Document();
    doc.add(new Field("FUNCTION_NAME", "abcd-10240", Field.Store.YES, Field.Index.ANALYZED));
    doc.add(new Field("CONTENT", "\"迎世博600天行动计划\"北蔡绿川地区污水纳管工程", Field.Store.YES, Field.Index.ANALYZED));
    w.addDocument(doc);
    
    IndexSearcher indexSearcher = new IndexSearcher(w.getReader());
    w.close();
    
    // creating the query
    QueryParser parser = new MultiFieldQueryParser(TEST_VERSION_CURRENT, new String[]{"FUNCTION_NAME", "CONTENT"}, analyzer);
    parser.setDefaultOperator(Operator.AND);
    Query query = parser.parse("FUNCTION_NAME:\"abcd-10240\"");
    System.out.println(query.toString());
    ScoreDoc[] hits = indexSearcher.search(query, null, 10).scoreDocs;
    //assertEquals(1, hits.length);

    // iterating over the hit documents
    for (int i = 0; i < hits.length; i++) {
    	Document document = indexSearcher.doc(hits[i].doc);
    	System.out.println(document.getFieldable("FUNCTION_NAME").stringValue());
    }
    indexSearcher.getIndexReader().close();
    dir.close();
  }
}
