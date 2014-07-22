package com.sfr.bol.dynamock.controller;

import com.sfr.bol.dynamock.model.Mock;
import com.sfr.bol.dynamock.service.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class BackController {

	@Autowired
	private MockService mockService;

	@RequestMapping(value = "supervision.jsp", method = RequestMethod.GET)
	@ResponseBody
	public String supervision() {
		return "ok";
	}

	@RequestMapping(value = "api/mock", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Mock> list() {
		return mockService.list();
	}

	@RequestMapping(value = "api/mock/{id:.+}", method = RequestMethod.GET)
	@ResponseBody
	public Mock get(@PathVariable String id) {
		return mockService.get(id);
	}

	@RequestMapping(value = "api/mock/{id:.+}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable String labelId) {
		mockService.delete(labelId);
	}

	@RequestMapping(value = "api/mock", method = RequestMethod.POST)
	@ResponseBody
	public void save(@RequestBody Mock mock) {
		mockService.save(mock);
	}

}