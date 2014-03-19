package entities;
import java.io.Serializable; 

public class TTitle_book_on_tape extends TTitle_book implements Serializable {

    private static final long serialVersionUID = 1L;
    private String actor;

    public String toString () {
        return super.toString() +  " Actor: " + actor;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

}

