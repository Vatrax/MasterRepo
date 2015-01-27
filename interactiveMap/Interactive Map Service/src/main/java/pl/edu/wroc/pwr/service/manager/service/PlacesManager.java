package pl.edu.wroc.pwr.service.manager.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import pl.edu.wroc.pwr.model.to.place.PlaceCreationTO;
import pl.edu.wroc.pwr.model.to.place.PlaceTO;
import pl.edu.wroc.pwr.service.manager.service.utils.JsonObjectConverter;
import pl.edu.wroc.pwr.service.model.Place;

/**
 * Created by krzaczek on 25.10.14.
 */
public class PlacesManager extends ModelManager<Place> {

	public PlacesManager(Class<Place> type) {
		super(type);
	}

	public int update(PlaceTO placeTO, Long ownerId) {
		Query searchQuery = new Query(
			Criteria.where("id").is(placeTO.getId()).andOperator(Criteria.where("ownerId").is(ownerId)));
		Update update = new Update();
		update.set("ownerId", placeTO.getOwnerId());
		update.set("tags", placeTO.getTags());
		update.set("name", placeTO.getName());
		update.set("description", placeTO.getDescription());
		update.set("coordinates", placeTO.getCoordinates());
		WriteResult writeResult = mongoOperation.updateFirst(searchQuery, update, Place.class);
		return writeResult.getN();
	}

	public String create(PlaceCreationTO placeTO) {
		Place place = createPlaceFromCreationTO(placeTO);
		mongoOperation.save(place);
		return JsonObjectConverter.convert("id", place.getId());
	}

	private Place createPlaceFromCreationTO(PlaceCreationTO placeTO) {
		Place place = new Place();
		place.setCoordinates(placeTO.getCoordinates());
		place.setDescription(placeTO.getDescription());
		place.setName(placeTO.getName());
		place.setOwnerId(placeTO.getOwnerId());
		place.setTags(placeTO.getTags());
		return place;
	}

}
