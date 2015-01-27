package pl.edu.wroc.pwr.model.to.place;

import pl.edu.wroc.pwr.model.util.Coordinates;

import java.util.Set;

/**
 * Created by krzaczek on 26.10.14.
 */
public class PlaceTO extends PlaceCreationTO {

	private String id;

	public PlaceTO() {
	}

	public PlaceTO(Coordinates coordinates, String name, String description,
		String ownerId, String id, Set<String> tags) {
		super(coordinates, name, description, ownerId, tags);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
