package pl.edu.wroc.pwr.service.manager.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mongodb.WriteResult;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import pl.edu.wroc.pwr.service.manager.DataManager;
import pl.edu.wroc.pwr.service.manager.service.utils.JsonObjectConverter;
import pl.edu.wroc.pwr.service.manager.service.utils.PictureConverter;
import pl.edu.wroc.pwr.service.model.Picture;

import java.util.List;

/**
 * Created by krzaczek on 22.11.14.
 */
public class PictureManager extends DataManager {

	private final PictureConverter pictureConverter;

	public PictureManager() {
		pictureConverter = new PictureConverter();
	}

	public Picture get(String pictureId) {
		return mongoOperation.findById(pictureId, Picture.class);
	}

	public List<Picture> getPicturesFor(String targetId) {
		Query searchUserQuery = new Query(Criteria.where("targetId").is(targetId));
		return mongoOperation.find(searchUserQuery, Picture.class);
	}

	public int remove(String pictureId, Long ownerId) {
		Query searchUserQuery = new Query(
			Criteria.where("id").is(pictureId).andOperator(Criteria.where("ownerId").is(ownerId)));
		WriteResult remove = mongoOperation.remove(searchUserQuery, Picture.class);
		return remove.getN();
	}

	public String createPicture(String ownerId, String targetId, MultipartFormDataInput input) {
		String fileName = pictureConverter.convertAndSave(input);
		Picture picture = new Picture(ownerId, targetId, fileName);
		mongoOperation.save(picture);
		return JsonObjectConverter.convert("id", picture.getId());
	}

}
