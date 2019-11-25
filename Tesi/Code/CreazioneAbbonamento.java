zoneatt = scritturaZone.getBytes();
         try {
						
		String inputLine=input.readLine();

		output.write(zoneatt);
			
		[...]
				
	final CassandraConnector client = new CassandraConnector();
				 
	final String ipAddress = "localhost";
	final int port = 9042;
				
		client.connect(ipAddress, port);
	Abbonamento ab = new Abbonamento(client);
	
	ab.Abbonato(
			UUIDs.timeBased(), 
			scritturaNome.toString(), 
			scritturaCognome.toString(), 
			scritturaData.toString(),
			scritturaZone.toString()
					
		);