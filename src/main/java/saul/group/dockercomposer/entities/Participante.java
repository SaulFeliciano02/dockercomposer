package saul.group.dockercomposer.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Participante {
    @Id
    @GeneratedValue
    long id;
    @Column(length = 2000)
    private String nombre;
    @Column(length = 2000)
    private String apellido;
    @Column(length = 2000)
    private String correo;
    @Column(length = 2000)
    private String generated_password;

    public Participante(){}

    public Participante(String nombre, String apellido, String correo, String generated_password){
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.generated_password = generated_password;
    }

    public long getId(){
        return this.id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getGenerated_password() {
        return generated_password;
    }

    public void setGenerated_password(String generated_password) {
        this.generated_password = generated_password;
    }
}
