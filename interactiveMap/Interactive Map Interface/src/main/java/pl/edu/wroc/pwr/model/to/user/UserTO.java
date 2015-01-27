package pl.edu.wroc.pwr.model.to.user;

/**
 * Created by krzaczek on 22.11.14.
 */
public class UserTO {

	private String id;

	public UserTO() {}
	public UserTO(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
