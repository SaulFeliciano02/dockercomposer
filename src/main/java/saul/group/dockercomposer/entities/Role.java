package saul.group.dockercomposer.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Role implements Serializable {
    @Id
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}