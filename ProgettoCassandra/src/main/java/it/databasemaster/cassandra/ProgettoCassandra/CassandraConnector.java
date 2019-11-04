package it.databasemaster.cassandra.ProgettoCassandra;

import java.util.Date;
import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.CodecRegistry;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TypeCodec;

public class CassandraConnector {
	 
	private Cluster cluster;
	private Session session;
 
	public void connect(final String node, final int port) {
		CodecRegistry codecRegistry = new CodecRegistry();
		codecRegistry.register(new DateCodec(TypeCodec.date(), Date.class));
		cluster = Cluster.builder().addContactPoint(node).withPort(port).withCodecRegistry(codecRegistry).build();
 
		final Metadata metadata = cluster.getMetadata();
		System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());
		for (final Host host : metadata.getAllHosts()) {
			System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n",
					host.getDatacenter(), host.getAddress(), host.getRack());
		}
		session = cluster.connect();
	}
 
	public String esegui() {
	
		String out = "ciao";
		
		Row result = session.execute("SELECT email FROM HR.employees").one();
		
		out = result.toString();
			      
		return out;
	}
	public Session getSession() {
		return this.session;
	}

 
    public void close() {        
    	session.close();
        cluster.close();
    }
}