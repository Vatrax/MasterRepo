package pl.edu.wroc.pwr.service.manager.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import pl.edu.wroc.pwr.model.to.rating.RateCreationTO;
import pl.edu.wroc.pwr.model.to.rating.RateTO;
import pl.edu.wroc.pwr.service.manager.DataManager;
import pl.edu.wroc.pwr.service.manager.service.utils.JsonObjectConverter;
import pl.edu.wroc.pwr.service.manager.service.utils.RateCalculator;
import pl.edu.wroc.pwr.service.model.Rate;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by krzaczek on 26.10.14.
 */
public class RateManager extends DataManager {

	private static final int RATE_MIN = 1;
	private static final int RATE_MAX = 5;

	private final RateCalculator calculator;

	public RateManager() {
		calculator = new RateCalculator();
	}

	public Rate get(String rateId) {
		return mongoOperation.findById(rateId, Rate.class);
	}

	public int remove(String rateId, Long ownerId) {
		Query searchUserQuery = new Query(
				Criteria.where("id").is(rateId).andOperator(Criteria.where("ownerId").is(ownerId)));
		WriteResult remove = mongoOperation.remove(searchUserQuery, Rate.class);
		return remove.getN();
	}

	public String createRate(RateCreationTO rateCreationTO) {
		if (!isValid(rateCreationTO)) {
			return null;
		}
		Rate rate = createRateFromTO(rateCreationTO);
		mongoOperation.save(rate);
		return JsonObjectConverter.convert("id", rate.getId());
	}

	public Double getAverage(String targetId) {
		List<Integer> rateValues = new LinkedList<Integer>();
		Query searchUserQuery = new Query(Criteria.where("targetId").is(targetId));
		List<Rate> rates = mongoOperation.find(searchUserQuery, Rate.class);
		for (Rate rate : rates) {
			rateValues.add(rate.getRate());
		}
		return calculator.avg(rateValues);
	}

	private boolean isValid(RateCreationTO rateCreationTO) {
		Integer rate = rateCreationTO.getRate();
		return rate >= RATE_MIN && rate <= RATE_MAX;
	}

	private Rate createRateFromTO(RateCreationTO rateCreationTO) {
		Rate rate = new Rate();
		rate.setOwnerId(rateCreationTO.getOwnerId());
		rate.setRate(rateCreationTO.getRate());
		rate.setTargetId(rateCreationTO.getTargetId());
		return rate;
	}

	public int update(Long ownerId, RateTO rateTO) {
		Query searchQuery = new Query(
				Criteria.where("id").is(rateTO.getId()).andOperator(Criteria.where("ownerId").is(ownerId)));
		Update update = new Update();
		update.set("ownerId", rateTO.getOwnerId());
		update.set("rate", rateTO.getRate());
		update.set("targetId", rateTO.getTargetId());
		WriteResult writeResult = mongoOperation.updateFirst(searchQuery, update, Rate.class);
		return writeResult.getN();
	}

}
