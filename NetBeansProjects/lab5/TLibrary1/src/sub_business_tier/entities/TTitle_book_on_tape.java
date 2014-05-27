package sub_business_tier.entities;
import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class TTitle_book_on_tape extends TTitle_book implements Serializable {
        private static final long serialVersionUID = 1L;
	private String actor;

	@Override
	public String toString() {
            return super.toString() + " Actor: " + actor;
	}

	@Override
	public String getActor() {
            return actor;
	}

	@Override
	public void setActor(String actor) {
            this.actor = actor;
	}
}