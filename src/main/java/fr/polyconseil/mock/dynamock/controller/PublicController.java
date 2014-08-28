/*
 * Copyright (c) 2013 SFR. Tous droits résérvés.
 */
package fr.polyconseil.mock.dynamock.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
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
import fr.polyconseil.mock.dynamock.model.Response;
import fr.polyconseil.mock.dynamock.service.MockService;
import fr.polyconseil.mock.dynamock.util.MatcherUtils;

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
		Response mockResponse = mock.getResponse();
		response.setStatus((mockResponse.getStatus() == null) ? 200 : mockResponse.getStatus());
		boolean contentTypeSent = false;
		for (Map.Entry<String, String> h : mockResponse.getHeaders().entrySet()) {
			response.setHeader(h.getKey(), h.getValue());
			if (h.getKey().equalsIgnoreCase("Content-Type")) {
				contentTypeSent = true;
			}
		}
		if (!contentTypeSent) {
			response.setHeader("Content-Type", mockResponse.guessContentType());
		}
		final String body = mockResponse.getBody();
		response.setHeader("Content-length", "" + body.getBytes().length);
		response.getWriter().print(body);
	}

	private Mock matchMock(String namespace, String method, String requestUrl, String requestBody) {
		Collection<Mock> mocks = mockService.mocksByNamespace(namespace);
		List<Mock> matchedMocks = new ArrayList<>();
		for (Mock mock : mocks) {
			Request req = mock.getRequest();
			if (StringUtils.isEmpty(req.getMethod()) || req.getMethod().equals(method)) {
				if (MatcherUtils.match(requestUrl, req.getUrlPattern())
					&& MatcherUtils.match(requestBody, req.getBodyPattern())) {
					matchedMocks.add(mockService.get(mock.getId()));
				}
			}
		}
		if (CollectionUtils.isNotEmpty(matchedMocks)) {
			Collections.sort(matchedMocks, new Comparator<Mock>() {

				@Override public int compare(Mock o1, Mock o2) {
					int result;
					if (o1.getPriority() == null && o2.getPriority() == null) {
						result = 0;
					} else {
						if (o1.getPriority() == null) {
							return 1;
						}
						if (o2.getPriority() == null) {
							return -1;
						}
						result = o1.getPriority().compareTo(o2.getPriority());
					}
					// Si on a la même priorité alors on prend le mock le plus récent
					if (result == 0) {
						result = o2.getUpdate().compareTo(o1.getUpdate());
					}
					return result;
				}
			});
			return matchedMocks.get(0);
		}
		return null;
	}
}
