package pl.edu.wroc.pwr.model.to.picture;

/**
 * Created by krzaczek on 22.11.14.
 */
public class PictureTO {

	private String id;
	private String ownerId;
	private String targetId;
	private String filePath;

	public PictureTO() {
	}

	public PictureTO(String id, String ownerId, String targetId, String filePath) {
		this.id = id;
		this.ownerId = ownerId;
		this.targetId = targetId;
		this.filePath = filePath;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
