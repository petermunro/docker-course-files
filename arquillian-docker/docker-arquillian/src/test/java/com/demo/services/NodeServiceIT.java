package com.demo.services;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.CoreMatchers.*;

@RunWith(Arquillian.class)
public class NodeServiceIT {

	@Deployment
	public static JavaArchive createDeployment(){
		return ShrinkWrap.create(JavaArchive.class)
				.addClass(NodeService.class);
	}
	
	@EJB
	NodeService nodeService;
	
	@Test
	public void nodeServiceCreatedAndNumberOfNodesIsZero(){
		assertThat(nodeService.getNumberOfAvailableNodes(), is(0));
	}
}
