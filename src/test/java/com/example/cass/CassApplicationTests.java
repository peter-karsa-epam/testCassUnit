package com.example.cass;

import static org.cassandraunit.utils.EmbeddedCassandraServerHelper.DEFAULT_CASSANDRA_YML_FILE;

import java.io.IOException;

import org.apache.cassandra.config.DatabaseDescriptor;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.springframework.core.io.ClassPathResource;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CassApplicationTests {

	private int dbPort;
	private Long timeout = 3000L;

	@BeforeTest
	public void setUp() throws ConfigurationException, TTransportException, IOException {
		// HEK
		String cassandraConfigPath = new ClassPathResource(DEFAULT_CASSANDRA_YML_FILE).getFile().getAbsolutePath();
		System.out.println(cassandraConfigPath);
		System.setProperty("cassandra.config", "file:///" + cassandraConfigPath);
		DatabaseDescriptor.daemonInitialization();
		// END HEK
		EmbeddedCassandraServerHelper.startEmbeddedCassandra(DEFAULT_CASSANDRA_YML_FILE);
		dbPort = EmbeddedCassandraServerHelper.getNativeTransportPort();
		// final Session session = EmbeddedCassandraServerHelper.getCluster().connect();
		// final CQLDataLoader loader = new CQLDataLoader(session);
		// loader.load(new ClassPathCQLDataSet("init.cql"));
	}

	@Test
	public void contextLoads() {
		Assert.assertTrue(dbPort != 0);
	}

}
