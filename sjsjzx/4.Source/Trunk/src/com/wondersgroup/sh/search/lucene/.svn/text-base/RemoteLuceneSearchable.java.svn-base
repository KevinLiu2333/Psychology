package com.wondersgroup.sh.search.lucene;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.lucene.document.Document;

import com.wondersgroup.sh.search.Query;
import com.wondersgroup.sh.search.SearchResultSet;
import com.wondersgroup.sh.search.params.WdisParams;

public class RemoteLuceneSearchable extends UnicastRemoteObject implements LuceneSearchable {
	private static final long serialVersionUID = 7494162207310092748L;
	private LuceneSearchable local;
  
	public RemoteLuceneSearchable(LuceneSearchable local) throws RemoteException {
		super();
		this.local = local;
	}

	public Document[] moreLikeThis(int docId, int num, String[] fields, WdisParams params) throws RemoteException {
		return local.moreLikeThis(docId, num, fields, params);
	}

	public SearchResultSet search(Query query) throws RemoteException {
		return local.search(query);
	}
	
	public static void main(String[] args) {
		
	}

	public LuceneSearchable getLocal() {
		return local;
	}

	public void setLocal(LuceneSearchable local) {
		this.local = local;
	}
}
