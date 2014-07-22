package fr.polyconseil.mock.dynamock.model;

public class Request {

	private String method;

	private String urlPattern;

	private String bodyPattern;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	public String getBodyPattern() {
		return bodyPattern;
	}

	public void setBodyPattern(String bodyPattern) {
		this.bodyPattern = bodyPattern;
	}
}
