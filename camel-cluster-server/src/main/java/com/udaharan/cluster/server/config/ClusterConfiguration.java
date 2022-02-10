package com.udaharan.cluster.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.atomix.AtomixReplica;
import io.atomix.catalyst.transport.Address;
import io.atomix.catalyst.transport.netty.NettyTransport;
import io.atomix.copycat.server.storage.Storage;
import io.atomix.copycat.server.storage.StorageLevel;

@Configuration
public class ClusterConfiguration {
	
	@Value("${cluster.address}")
	private String address;

	@Bean(destroyMethod = "shutdown")
	public AtomixReplica atomix() {

		return AtomixReplica.builder(new Address(address))
				.withTransport(new NettyTransport())
				.withStorage(Storage.builder().withStorageLevel(StorageLevel.MEMORY).build())
				.build().bootstrap()
				.join();
	}
}
