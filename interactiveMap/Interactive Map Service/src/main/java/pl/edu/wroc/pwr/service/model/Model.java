package pl.edu.wroc.pwr.service.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by krzaczek on 29.10.14.
 */
public abstract class Model implements Serializable {

	@Id
	private String id;
	private String name;
	private String description;
	private String ownerId;
	private Set<String> tags;

	protected Model(String name, String description, String ownerId, Set<String> tags) {
		this.name = name;
		this.description = description;
		this.ownerId = ownerId;
		this.tags = tags;
	}

	protected Model() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Model model = (Model) o;
		if (id != null ? !id.equals(model.id) : model.id != null) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
