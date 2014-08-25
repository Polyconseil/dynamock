package fr.polyconseil.mock.dynamock.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.polyconseil.mock.dynamock.model.Mock;
import fr.polyconseil.mock.dynamock.service.MockService;

@RestController
public class BackController {

	@Autowired private MockService mockService;

	@RequestMapping(value = "supervision.jsp", method = RequestMethod.GET) public String supervision() {
		return "ok";
	}

	@RequestMapping(value = "/api/mock", method = RequestMethod.GET) public Collection<Mock> list() {
		return mockService.list();
	}

	@RequestMapping(value = "/api/mock/{id:.+}", method = RequestMethod.GET) public Mock get(@PathVariable String id) {
		Mock mock = mockService.get(id);
		return mock;
	}

	@RequestMapping(value = "/api/mock/{id:.+}", method = RequestMethod.DELETE) public void delete(@PathVariable String id) {
		mockService.delete(id);
	}

	@RequestMapping(value = "/api/mock", method = RequestMethod.POST) public void save(@RequestBody Mock mock) {
		mockService.save(mock);
	}
	
	@RequestMapping(value = "/api/mock/duplicate/{id:.+}", method = RequestMethod.GET) public Mock duplicate(@PathVariable String id) {
		return mockService.duplicate(id);
	}
}