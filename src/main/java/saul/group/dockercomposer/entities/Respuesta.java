package saul.group.dockercomposer.entities;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Respuesta {
    @Id
    @GeneratedValue
    private long id;
    private ArrayList<Integer> question_rating_1 = new ArrayList<>(5);
    private ArrayList<Integer> question_rating_2 = new ArrayList<>(5);
    private ArrayList<Integer> question_rating_3 = new ArrayList<>(5);

    public Respuesta(){}

    public Respuesta(ArrayList<Integer> question_rating_1, ArrayList<Integer> question_rating_2, ArrayList<Integer> question_rating_3){
        this.question_rating_1 = question_rating_1;
        this.question_rating_2 = question_rating_2;
        this.question_rating_3 = question_rating_3;
    }

    public long getId() {
        return id;
    }

    public ArrayList<Integer> getQuestion_rating_1() {
        return question_rating_1;
    }

    public ArrayList<Integer> getQuestion_rating_2() {
        return question_rating_2;
    }

    public ArrayList<Integer> getQuestion_rating_3() {
        return question_rating_3;
    }


    public void setQuestion_rating_1(ArrayList<Integer> question_rating_1) {
        this.question_rating_1 = question_rating_1;
    }

    public void setQuestion_rating_2(ArrayList<Integer> question_rating_2) {
        this.question_rating_2 = question_rating_2;
    }

    public void setQuestion_rating_3(ArrayList<Integer> question_rating_3) {
        this.question_rating_3 = question_rating_3;
    }

}
