package com.udaharan.cluster.node.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HeartBeatRoute extends RouteBuilder {

	@Value("${node.id}")
	private String nodeId;

	@Override
	public void configure() {
		from("timer:heartBeat?period={{timer.period}}").routeId("heartBeat").log("Node Id " + nodeId);
	}

}
