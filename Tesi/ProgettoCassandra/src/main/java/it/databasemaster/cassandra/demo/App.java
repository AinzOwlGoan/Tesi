package it.databasemaster.cassandra.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.utils.UUIDs;

public class App {
	
	public static void main(String[] args) throws ParseException {

		final CassandraConnector client = new CassandraConnector();
		final String ipAddress = args.length > 0 ? args[0] : "localhost";
		final int port = args.length > 1 ? Integer.parseInt(args[1]) : 9042;
		System.out.println("Connecting to IP Address " + ipAddress + ":" + port + "...");
		client.connect(ipAddress, port);
			
		EmployeePersistence ep = new EmployeePersistence(client);

		ep.persistEmployee(
				UUIDs.timeBased(),
				new String("Frodo"), 
				new String("Baggins"), 
				new String("IT_PROG"),
				LocalDate.fromYearMonthDay(2018, 01, 16),
				12500, 
				new String("frodo@databasemaster.it"), 
				new String("033-4511"));

		client.close();
	}	
}
