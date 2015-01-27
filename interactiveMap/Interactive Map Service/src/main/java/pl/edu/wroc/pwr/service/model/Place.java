package pl.edu.wroc.pwr.service.model;

import org.springframework.data.mongodb.core.mapping.Document;
import pl.edu.wroc.pwr.model.util.Coordinates;

import java.util.Set;

/**
 * Created by krzaczek on 25.10.14.
 */
@Document(collection = "places")
public class Place extends Model {

	private Coordinates coordinates;

	public Place() {
	}

	public Place(Coordinates coordinates, String name, String description, String ownerId, Set<String> tags) {
		super(name, description, ownerId, tags);
		this.coordinates = coordinates;

	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

}