package com.wondersgroup.sh.search.lucene;

import java.rmi.RemoteException;

import org.apache.lucene.document.Document;

import com.wondersgroup.sh.search.Searcher;
import com.wondersgroup.sh.search.params.WdisParams;

public interface LuceneSearchable extends Searcher {
	public Document[] moreLikeThis(int docId, int num, String[] fields, WdisParams params) throws RemoteException;
}
