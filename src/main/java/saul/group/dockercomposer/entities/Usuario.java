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

    public Usuario(){}

    public Usuario(String username, String password, Set<Role> role){
        this.username = username;
        this.password = password;
        this.roles = role;
        this.activo = true;
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
}
