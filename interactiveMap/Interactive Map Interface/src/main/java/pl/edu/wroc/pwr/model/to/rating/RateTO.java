package pl.edu.wroc.pwr.model.to.rating;

/**
 * Created by krzaczek on 01.11.14.
 */
public class RateTO extends  RateCreationTO {

	private String id;

	public RateTO() {
	}

	public RateTO(Integer rate, String ownerId, String targetId, String id) {
		super(rate, ownerId, targetId);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
