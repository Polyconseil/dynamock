package fr.polyconseil.mock.dynamock.model;

import java.util.HashMap;
import java.util.Map;

public class Response {

	private Integer status;

	private Map<String, String> headers;

	private String body;
	
	private String responseType;

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

	public String guessContentType() {
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
	
	public String getResponseType(){
		if (body==null){
			responseType= "text/html";
		}
		responseType= guessContentType().split(";")[0];
		return responseType;
	}
}
