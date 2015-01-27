package pl.edu.wroc.pwr.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by krzaczek on 26.10.14.
 */
@Document(collection = "comments")
public class Comment {

	@Id
	private String id;
	private String targetId;
	private String comment;
	private String ownerId;

	public Comment() {
	}

	public Comment(String comment, String ownerId, String targetId) {
		this.comment = comment;
		this.ownerId = ownerId;
		this.targetId = targetId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
