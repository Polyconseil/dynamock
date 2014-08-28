package fr.polyconseil.mock.dynamock.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mock")
public class Mock implements Cloneable {

	@Id private String id;

	private String namespace;

	private String name;

	private String description;

	private String longDescription;

	private Date update;

	private Request request;

	private Response response;

	private String owner;
	
	private Integer priority;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	
	public Integer getPriority() {
		return priority;
	}

	
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Object clone() {
		Mock mock = null;
		try {
			mock = (Mock) super.clone();
		} catch (CloneNotSupportedException ex) {
			// Ne devrait jamais arriver car nous implémentons
			// l'interface Cloneable
			return null;
		}
		if (request != null) {
			mock.request = (Request) request.clone();
		}
		if (response != null) {
			mock.response = (Response) response.clone();
		}
		return mock;
	}
}
