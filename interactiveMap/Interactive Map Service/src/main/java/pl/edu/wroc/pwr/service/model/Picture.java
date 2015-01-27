package pl.edu.wroc.pwr.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by krzaczek on 22.11.14.
 */

@Document
public class Picture {

	@Id
	private String id;
	private String ownerId;
	private String targetId;
	private String fileName;

	public Picture() {
	}

	public Picture(String ownerId, String targetId, String fileName) {
		this.ownerId = ownerId;
		this.targetId = targetId;
		this.fileName = fileName;
	}

	public Picture(String id, String ownerId, String targetId, String fileName) {
		this.id = id;
		this.ownerId = ownerId;
		this.targetId = targetId;
		this.fileName = fileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
}
