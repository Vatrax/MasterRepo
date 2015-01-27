package pl.edu.wroc.pwr.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by krzaczek on 22.11.14.
 */
@Document
public class User {

	@Id
	private String id;

	public User() {
	}

	public User(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
