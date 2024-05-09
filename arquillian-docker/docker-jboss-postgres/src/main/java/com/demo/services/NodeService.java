package com.demo.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.demo.domain.Node;



@Stateless
public class NodeService {
	
	@PersistenceContext(unitName="node_db")
	private EntityManager em;
	
	public void saveNode(Node node){
		em.persist(node);
	}

	public List<Node> getAllNodes(){
		Query query = em.createQuery("select n from Node n");
		List<Node> nodes = query.getResultList();
		return nodes;
	}
}
