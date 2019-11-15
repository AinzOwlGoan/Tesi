package it.databasemaster.cassandra.ProgettoCassandra;

import java.util.UUID;

public class Abbonamento {

	 
		private CassandraConnector client;
	 
		public Abbonamento(final CassandraConnector client) {
			this.client = client;
		}
		
		public void Abbonato(
				final UUID reg,
				final String nome,
				final String cognome,
				final String indirizzo,
				final String abbonamento

		) {
			
			client.getSession().execute(
					"INSERT INTO abbonamento.abb (reg, nome, cognome, indirizzo, abbonamento) VALUES (?, ?, ?, ?, ?)",
					reg,nome,cognome,indirizzo,abbonamento);
		}
	}