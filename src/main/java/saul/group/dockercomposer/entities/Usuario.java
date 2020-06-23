package saul.group.dockercomposer.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Usuario implements Serializable{
    @Id
    @GeneratedValue
    private long id;
    @Column(length = 2000)
    private String username;
    @Column(length = 2000)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @Column(columnDefinition = "boolean default true")
    private boolean activo;
    @Transient
    private String passwordConfirm;
    private String nombre;
    private String apellido;
    @Column(length = 2000)
    private String comentario;

    public Usuario(){}

    public Usuario(String username, String password, Set<Role> role, String nombre, String apellido){
        this.username = username;
        this.password = password;
        this.roles = role;
        this.activo = true;
        this.nombre = nombre;
        this.apellido = apellido;
        this.comentario = "";
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
