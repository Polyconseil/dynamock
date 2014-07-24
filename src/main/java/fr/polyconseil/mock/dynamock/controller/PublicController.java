/*
 * Copyright (c) 2013 SFR. Tous droits résérvés.
 */
package fr.polyconseil.mock.dynamock.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.polyconseil.mock.dynamock.exception.MockNotFoundException;
import fr.polyconseil.mock.dynamock.model.Mock;
import fr.polyconseil.mock.dynamock.model.Request;
import fr.polyconseil.mock.dynamock.service.MockService;

@Controller
public class PublicController {

	private static final Logger logger = LoggerFactory.getLogger(PublicController.class);

	@Autowired private MockService mockService;

	@RequestMapping(value = "/public/{namespace:.+}/**", method = {
			RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE
	}) public void mock(@PathVariable String namespace,
						@RequestBody String requestBody,
						HttpServletRequest request,
						HttpServletResponse response) throws IOException, MockNotFoundException {
		String url = request.getRequestURI().substring(8 + namespace.length());
		String method = request.getMethod();
		logger.info("Mock " + namespace + " : " + method + " " + url);
		Mock mock = matchMock(namespace, method, url, requestBody);
		if (mock == null) {
			throw new MockNotFoundException("Pas de mock trouvé");
		}
		response.setStatus((mock.getResponse().getStatus() == null) ? 200 : mock.getResponse().getStatus());
		boolean contentTypeSent = false;
		for (Map.Entry<String, String> h : mock.getResponse().getHeaders().entrySet()) {
			response.setHeader(h.getKey(), h.getValue());
			if (h.getKey().equalsIgnoreCase("Content-Type")) {
				contentTypeSent = true;
			}
		}
		final String body = mock.getResponse().getBody();
		if (!contentTypeSent) {
			response.setHeader("Content-Type", guessContentType(body));
		}
		response.setHeader("Content-length", "" + body.getBytes().length);
		response.getWriter().print(body);
	}

	private String guessContentType(final String body) {
		for (int idx = 0 ; idx < body.length() ; idx++) {
			char c = body.charAt(idx);
			switch (c) {
				case ' ':
					continue;
				case '<':
					return "application/xml;charset=UTF-8";
				case '{':
					return "application/json;charset=UTF-8";
				case '[':
					return "application/json;charset=UTF-8";
			}
		}
		return "text/html;charset=UTF-8";
	}

	private Mock matchMock(String namespace, String method, String requestUrl, String requestBody) {
		Collection<Mock> mocks = mockService.mocksByNamespace(namespace);
		for (Mock mock : mocks) {
			Request req = mock.getRequest();
			if (StringUtils.isEmpty(req.getMethod()) || req.getMethod().equals(method)) {
				if (StringUtils.isNotEmpty(req.getBodyPattern())) {
					Pattern regex = Pattern.compile(req.getBodyPattern(), Pattern.DOTALL);
					Matcher m = regex.matcher(requestBody);
					if (m.find()) {
						return mockService.get(mock.getId());
					}
				}
			}
		}
		return null;
	}
}