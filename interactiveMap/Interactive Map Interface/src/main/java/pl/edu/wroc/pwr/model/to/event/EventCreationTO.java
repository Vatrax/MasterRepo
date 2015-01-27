package pl.edu.wroc.pwr.model.to.event;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by krzaczek on 26.10.14.
 */
public class EventCreationTO implements Serializable {

	private String placeId;
	private String name = "";
	private String description = "";
	private String ownerId;
	private Set<String> tags;
	private Date date;

	public EventCreationTO() {
	}

	public EventCreationTO(String placeId, String name, String description, String ownerId,
		Set<String> tags, Date date) {
		this.placeId = placeId;
		this.name = name;
		this.description = description;
		this.ownerId = ownerId;
		this.tags = tags;
		this.date = date;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
