package pl.edu.wroc.pwr.model.to.place;

import pl.edu.wroc.pwr.model.util.Coordinates;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by krzaczek on 26.10.14.
 */
public class PlaceCreationTO implements Serializable {

	private Coordinates coordinates;
	private String name = "";
	private String description = "";
	private String ownerId;
	private Set<String> tags;

	public PlaceCreationTO() {
	}

	public PlaceCreationTO(Coordinates coordinates, String name, String description, String ownerId, Set<String> tags) {
		this.coordinates = coordinates;
		this.name = name;
		this.description = description;
		this.ownerId = ownerId;
		this.tags = tags;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
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
}
