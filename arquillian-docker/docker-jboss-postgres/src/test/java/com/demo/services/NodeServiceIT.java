package com.demo.services;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.demo.domain.Node;

import static org.hamcrest.CoreMatchers.*;

@RunWith(Arquillian.class)
public class NodeServiceIT {

	@Deployment
	public static JavaArchive cratedEPLOYMENT(){
		return ShrinkWrap.create(JavaArchive.class)
				.addClasses(NodeService.class, Node.class)
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml");
	}
	
	@EJB
	NodeService nodeService;
	
	@Test
	public void beanIsInjected(){
		assertThat(nodeService, is(notNullValue()));
	}
	
	@Test
	public void testSingleNodePersisted(){
		Node node = new Node();
		nodeService.saveNode(node);
		assertThat(nodeService.getAllNodes().size(), is(1));	
	}
}
