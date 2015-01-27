package pl.edu.wroc.pwr.service.manager.service;

import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import pl.edu.wroc.pwr.model.to.event.EventCreationTO;
import pl.edu.wroc.pwr.model.to.event.EventTO;
import pl.edu.wroc.pwr.service.manager.service.utils.JsonObjectConverter;
import pl.edu.wroc.pwr.service.model.Comment;
import pl.edu.wroc.pwr.service.model.Event;

import java.util.List;

/**
 * Created by krzaczek on 26.10.14.
 */
public class EventsManager extends ModelManager<Event> {

	public EventsManager(Class<Event> type) {
		super(type);
	}

	public int update(EventTO eventTO, Long ownerId) {
		Query searchQuery = new Query(
			Criteria.where("id").is(eventTO.getId()).andOperator(Criteria.where("ownerId").is(ownerId)));
		Update update = new Update();
		update.set("date", eventTO.getDate());
		update.set("placeId", eventTO.getPlaceId());
		update.set("ownerId", eventTO.getOwnerId());
		update.set("tags", eventTO.getTags());
		update.set("name", eventTO.getName());
		update.set("description", eventTO.getDescription());
		WriteResult writeResult = mongoOperation.updateFirst(searchQuery, update, Event.class);
		return writeResult.getN();
	}

	public List<Event> getEventsFor(String placeId) {
		Query searchUserQuery = new Query(Criteria.where("placeId").is(placeId));
		return mongoOperation.find(searchUserQuery, Event.class);
	}


	public String create(EventCreationTO eventTO) {
		Event event = createEventFromCreationTO(eventTO);
		mongoOperation.save(event);
		return JsonObjectConverter.convert("id", event.getId());
	}

	private Event createEventFromCreationTO(EventCreationTO eventTO) {
		Event event = new Event();
		event.setPlaceId(eventTO.getPlaceId());
		event.setDescription(eventTO.getDescription());
		event.setName(eventTO.getName());
		event.setOwnerId(eventTO.getOwnerId());
		event.setTags(eventTO.getTags());
		event.setDate(eventTO.getDate());
		return event;
	}

}
