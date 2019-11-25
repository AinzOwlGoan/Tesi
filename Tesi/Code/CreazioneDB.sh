CREATE KEYSPACE Abbonamento
	... WITH REPLICATION = {
	... 'class' : 'SimpleStrategy',
	... 'replication_factor' : 3};