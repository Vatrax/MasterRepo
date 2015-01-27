package pl.edu.wroc.pwr.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by krzaczek on 26.10.14.
 */
@Document(collection = "rates")
public class Rate {

	@Id
	private String id;
	private Integer rate;
	private String ownerId;
	private String targetId;

	public Rate() {
	}

	public Rate(Integer rate, String ownerId, String targetId) {
		this.rate = rate;
		this.ownerId = ownerId;
		this.targetId = targetId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
}
