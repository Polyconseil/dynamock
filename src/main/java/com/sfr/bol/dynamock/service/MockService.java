package com.sfr.bol.dynamock.service;

import com.sfr.bol.dynamock.model.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class MockService {

	@Autowired
	private MongoTemplate mongoTemplate;

	public void save(Mock mock) {
		mock.setUpdate(new Date());
		mongoTemplate.save(mock);
	}

	public Collection<Mock> list() {
		// On limite le nombre de champs récupérés de la base
		Query q = new Query();
		q.fields().include("_id").include("namespace").include("name").include("description").include("update");
		return mongoTemplate.find(q, Mock.class);
	}

	public Collection<Mock> mocksByNamespace(String namespace) {
		// On limite le nombre de champs récupérés de la base
		Query q = new Query();
		q.addCriteria(Criteria.where("namespace").is(namespace));
		q.fields().include("_id").include("namespace").include("name").include("request");
		return mongoTemplate.find(q, Mock.class);
	}

	public Mock get(String id) {
		return mongoTemplate.findById(id, Mock.class);
	}

	public void delete(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		mongoTemplate.remove(query, Mock.class);
	}
}
