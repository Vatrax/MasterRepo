package pl.edu.wroc.pwr.model.util;

import java.io.Serializable;

/**
 * Created by krzaczek on 25.10.14.
 */
public class Coordinates implements Serializable {

	private Double longitude;
	private Double latitude;

	public Coordinates() {

	}

	public Coordinates(Double longitude, Double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public Double getLatitude() {
		return latitude;
	}
}