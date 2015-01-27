package pl.edu.wroc.pwr.model.to.event;

import java.util.Date;
import java.util.Set;

/**
 * Created by krzaczek on 26.10.14.
 */
public class EventTO extends EventCreationTO {

	private String id;

	public EventTO() {
	}

	public EventTO(String placeId, String name, String description, String ownerId, Set<String> tags,
		String id, Date date) {
		super(placeId, name, description, ownerId, tags, date);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
