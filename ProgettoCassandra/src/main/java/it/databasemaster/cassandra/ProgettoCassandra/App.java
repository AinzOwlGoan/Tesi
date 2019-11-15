package it.databasemaster.cassandra.ProgettoCassandra;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.utils.UUIDs;
 
public class App {
	
	public static void main(String[] args) throws ParseException {
 
		final CassandraConnector client = new CassandraConnector();
		final String ipAddress = args.length > 0 ? args[0] : "localhost";
		final int port = args.length > 1 ? Integer.parseInt(args[1]) : 9042;
		System.out.println("Connecting to IP Address " + ipAddress + ":" + port + "...");
		client.connect(ipAddress, port);
			
		/*EmployeePersistence ep = new EmployeePersistence(client);
 
		ep.persistEmployee(
				UUIDs.timeBased(),
				new String("Priviet"), 
				new String("Crosho"), 
				new String("IT_PROG"),
				LocalDate.fromYearMonthDay(2018, 01, 16),
				12500, 
				new String("no mail"), 
				new String("ok"));
		 */
		Abbonamento ab = new Abbonamento(client);
		ab.Abbonato( UUIDs.timeBased(),
				new String("12212"), 
				new String("Daniele"), 
				new String("Ravasio"), 
				new String("Casa mia") 
				
				);
		
		
		System.out.println(client.esegui());
		System.out.println(client.esegui());		
		client.close();
	}
}