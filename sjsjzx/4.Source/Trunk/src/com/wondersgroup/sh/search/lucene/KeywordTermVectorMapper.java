package com.wondersgroup.sh.search.lucene;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.lucene.index.TermVectorEntry;
import org.apache.lucene.index.TermVectorMapper;
import org.apache.lucene.index.TermVectorOffsetInfo;

public class KeywordTermVectorMapper extends TermVectorMapper {
  private SortedSet currentSet;
  private Map termToTVE = new HashMap();
  private List keywords;
  public static final String ALL = "_ALL_";
  
  public KeywordTermVectorMapper(List keywords, Comparator comparator) {
  	super(true, true);
  	this.currentSet = new TreeSet(comparator);
  	this.keywords = keywords;
  }

	public void map(String term, int frequency, TermVectorOffsetInfo[] offsets, int[] positions) {
		if( !keywords.contains(term) )
			return;
		
		int myFreq = frequency;
		TermVectorEntry entry = (TermVectorEntry) termToTVE.get(term);
		if( entry != null ) {
			myFreq += entry.getFrequency();
			currentSet.remove(entry);
		}
    
		entry = new TermVectorEntry(ALL, term, myFreq, null, null);
    termToTVE.put(term, entry);
    currentSet.add(entry);
	}

	public void setExpectations(String field, int numTerms, boolean storeOffsets, boolean storePositions) {
	}
	
  public SortedSet getTermVectorEntrySet() {
    return currentSet;
  }
}
