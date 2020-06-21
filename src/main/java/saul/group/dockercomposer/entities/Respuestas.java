package saul.group.dockercomposer.entities;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Respuestas {
    @Id
    @GeneratedValue
    private long id;
    public ArrayList<Integer> rating_1 = new ArrayList<>(4);
    public ArrayList<Integer> rating_2 = new ArrayList<>(4);
    public ArrayList<Integer> rating_3 = new ArrayList<>(4);
    public ArrayList<Integer> rating_4 = new ArrayList<>(4);
    public ArrayList<Integer> rating_5 = new ArrayList<>(4);

    public Respuestas(){}

    public ArrayList<Integer> getRating_1() {
        return rating_1;
    }

    public ArrayList<Integer> getRating_2() {
        return rating_2;
    }

    public ArrayList<Integer> getRating_3() {
        return rating_3;
    }

    public ArrayList<Integer> getRating_4() {
        return rating_4;
    }

    public ArrayList<Integer> getRating_5() {
        return rating_5;
    }

    public void setRating_1(ArrayList<Integer> rating_1) {
        this.rating_1 = rating_1;
    }

    public void setRating_2(ArrayList<Integer> rating_2) {
        this.rating_2 = rating_2;
    }

    public void setRating_3(ArrayList<Integer> rating_3) {
        this.rating_3 = rating_3;
    }

    public void setRating_4(ArrayList<Integer> rating_4) {
        this.rating_4 = rating_4;
    }

    public void setRating_5(ArrayList<Integer> rating_5) {
        this.rating_5 = rating_5;
    }
}
