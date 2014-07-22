package com.sfr.bol.dynamock.model;


import java.util.HashMap;
import java.util.Map;

public class Response {

	private Integer status;

	private Map<String, String> headers;

	private String body;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Map<String, String> getHeaders() {
		if (headers == null) {
			headers = new HashMap<>();
		}
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
