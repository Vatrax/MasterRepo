package pl.edu.wroc.pwr.service.manager.service.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Created by krzaczek on 17.01.15.
 */
public class JsonObjectConverter {

	public static String convert(String propertyName, String value) {
		JsonObject object = new JsonObject();
		object.addProperty(propertyName, value);
		return new Gson().toJson(object);
	}
}
