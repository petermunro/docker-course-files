package com.demo.rest;


import javax.ws.rs.core.MediaType;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;


import com.demo.domain.Node;


@RunWith(Arquillian.class)
public class NodeRestServiceIT {

	@Deployment
	public static WebArchive createDeployment(){
		return ShrinkWrap.create(WebArchive.class, "rest.war")
				.addClass(NodeService.class)
				.addClass(NodeApplication.class)
				.addClass(Node.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		
	}

	private String deploymentURL = "http://192.168.99.100:8080/rest/api/node";
	
	@Test
	@RunAsClient
	public void testPathNodeReturnsNode(){
		given()
			.accept(MediaType.APPLICATION_JSON)
		.when()
			.get(deploymentURL)
		.then()
			.statusCode(200)
		.and()
			.body("id", equalTo(10))
		.and()
			.body("name", equalTo("Athlone"));
	}
}
