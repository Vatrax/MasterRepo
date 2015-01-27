package pl.edu.wroc.pwr.service.manager.service;

import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import pl.edu.wroc.pwr.service.manager.DataManager;
import pl.edu.wroc.pwr.service.model.Model;

import java.util.List;
import java.util.Set;

/**
 * Created by krzaczek on 29.10.14.
 */
public abstract class ModelManager<T extends Model>  extends DataManager {

	private final Class<T> type;

	protected ModelManager(Class<T> type) {
		this.type = type;
	}

	public List<T> getAll() {
		return mongoOperation.findAll(type);
	}

	public T get(String id) {
		return mongoOperation.findById(id, type);
	}

	public int remove(String id, Long ownerId) {
		Query searchQuery = new Query(Criteria.where("id").is(id).andOperator(Criteria.where("ownerId").is(ownerId)));
		WriteResult removed = mongoOperation.remove(searchQuery, type);
		return removed.getN();
	}

	public List<T> getFiltered(Set<String> tags) {
		Query searchQuery = new Query(Criteria.where("tags").in(tags));
		return mongoOperation.find(searchQuery, type);
	}

}
