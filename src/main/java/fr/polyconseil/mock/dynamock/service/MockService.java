package fr.polyconseil.mock.dynamock.service;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import fr.polyconseil.mock.dynamock.model.Mock;

@Repository
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
		q.fields().include("_id").include("namespace").include("owner").include("name").include("description").include("update").include("request.urlPattern").include("request.bodyPattern");
		return mongoTemplate.find(q, Mock.class);
	}

	public Collection<Mock> mocksByNamespace(String namespace) {
		// On limite le nombre de champs récupérés de la base
		Query q = new Query();
		q.addCriteria(Criteria.where("namespace").is(namespace));
		q.fields().include("_id").include("namespace").include("name").include("priority").include("update").include("request");
		return mongoTemplate.find(q, Mock.class);
	}

	public Mock get(String id) {
		return mongoTemplate.findById(id, Mock.class);
	}

	public void delete(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		mongoTemplate.remove(query, Mock.class);
	}
	
	
	public Mock duplicate(String id){
		Mock mock = get(id);
		Mock newMock=(Mock) mock.clone();
		//new id 
		//TODO voir avec Henri la stratégie de génération des Id.
		DecimalFormat decimalFormat=new DecimalFormat("#.#");
		String newId= String.valueOf(Calendar.getInstance().getTimeInMillis()-1392854400000l)+'-'+String.valueOf(decimalFormat.format(Math.ceil(Math.random()*1000)));
		newMock.setId(newId);
		save(newMock);
		return newMock;
	}
}
