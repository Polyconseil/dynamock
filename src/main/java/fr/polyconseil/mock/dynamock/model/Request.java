package fr.polyconseil.mock.dynamock.model;

public class Request implements Cloneable{

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
	
	public Object clone(){
		Request request =null;
		try{
			request=(Request) super.clone();
		}catch(CloneNotSupportedException ex) {
	      	// Ne devrait jamais arriver car nous implémentons 
	      	// l'interface Cloneable
	      	return null;
	    }
		return request;
	}
}
