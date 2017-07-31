package com.wondersgroup.sh.search.lucene;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import org.apache.log4j.Logger;

import com.wondersgroup.sh.search.common.HighLighterConfig;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;

public class SearchServer {
	private static final Logger logger = Logger.getLogger(SearchServer.class);
	private RemoteLuceneSearchable remoteSearchable;
	
	public void stop(String name) throws Exception {
    // Unregister ourself 
    Naming.unbind(name); 

    // Unexport; this will also remove us from the RMI runtime 
    //UnicastRemoteObject.unexportObject(remoteSearchable, true); 
    System.exit(0);
    //System.out.println("SearchServer exiting."); 
	}

	public void start(String configFile, String[] indexIds, int port, String name) throws Exception {
		// create searcher
		LuceneConfiguration configuration = new LuceneConfiguration(configFile);
		configuration.readConfiguration();
		LuceneSearcher searcher = new LuceneSearcher(configuration, indexIds);
		HighLighterConfig highLighterConfig = new HighLighterConfig();
		searcher.setHighLighterConfig(highLighterConfig);
		
		// bind to rmi
		LocateRegistry.createRegistry(port);
		remoteSearchable = new RemoteLuceneSearchable(searcher);
		Naming.rebind(name, remoteSearchable);
		
	}
	
	public static void main(String[] args) {
		SearchServer server = new SearchServer();
		try {
			String name = "//localhost/" + args[3];
			server.start(args[0], new String[]{args[1]}, Integer.valueOf(args[2]).intValue(), name);
			//server.stop(name);
		}
		catch (Exception e) {
			logger.error("Create searcher server fail!", e);
		}
	}

}
