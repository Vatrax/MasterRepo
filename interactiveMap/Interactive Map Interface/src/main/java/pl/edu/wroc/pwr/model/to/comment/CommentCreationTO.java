package pl.edu.wroc.pwr.model.to.comment;

/**
 * Created by krzaczek on 26.10.14.
 */
public class CommentCreationTO {

	private String comment;
	private String ownerId;
	private String targetId;

	public CommentCreationTO() {
	}

	public CommentCreationTO(String comment, String ownerId, String targetId) {
		this.comment = comment;
		this.ownerId = ownerId;
		this.targetId = targetId;
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
