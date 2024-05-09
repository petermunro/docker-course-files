package com.demo.services


import javax.ejb.EJB

import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.spock.ArquillianSputnik
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.spec.JavaArchive
import org.junit.runner.RunWith

import spock.lang.Specification

@RunWith(ArquillianSputnik)
class NodeServiceIT extends Specification{
	@Deployment
	def static JavaArchive cratedEPLOYMENT(){
		return ShrinkWrap.create(JavaArchive.class)
				.addClass(NodeService.class);
	}
	
	@EJB
	NodeService nodeService;
	
	def "node service should return zero nodes avialable when service initially created"(){
		when:
			def numberOfNodes = nodeService.numberOfAvailableNodes
		then:
			numberOfNodes == 0
		
	}
}
