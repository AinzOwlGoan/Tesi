package it.databasemaster.cassandra.ProgettoCassandra;

import com.datastax.driver.core.LocalDate;
import java.util.UUID;
 
public class EmployeePersistence {
 
	private CassandraConnector client;
 
	public EmployeePersistence(final CassandraConnector client) {
		this.client = client;
	}
	
	public void persistEmployee(
			final UUID id,
			final String first_name,
			final String last_name,
			final String job_id,
			final LocalDate hire_date,
			final int salary,
			final String email,
			final String phone
	) {
		client.getSession().execute(
				"INSERT INTO HR.employees (id, first_name, last_name, job_id, hire_date, salary, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
				id, first_name, last_name, job_id, hire_date, salary, email, phone
		);
	}
}