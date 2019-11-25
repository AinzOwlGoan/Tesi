public void connect(final String node, final int port) {
		CodecRegistry codecRegistry = new CodecRegistry();
		codecRegistry.register(new DateCodec(TypeCodec.date(),
		 Date.class));
		cluster = Cluster.builder().addContactPoint(node).
		withPort(port).withCodecRegistry(codecRegistry).build();
 
		final Metadata metadata = cluster.getMetadata();
	 [...]
		session = cluster.connect();
	}
