package saul.group.dockercomposer.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saul.group.dockercomposer.entities.Role;
import saul.group.dockercomposer.entities.Usuario;
import saul.group.dockercomposer.repositories.UsuarioRepositorio;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    public UsuarioRepositorio usuarioRepositorio;

    public List<Usuario> getUsuario(){
        return usuarioRepositorio.findAll();
    }

    public long getCount(){
        return usuarioRepositorio.count();
    }

    public Usuario getUsuarioByUsernameAndPassword(String username, String password){
        return usuarioRepositorio.findUsuarioByUsernameAndPassword(username, password);
    }

    public Usuario getUsuarioByUsername(String username){
        return usuarioRepositorio.findByUsername(username);
    }

    public boolean checkUser(String username){
        if(usuarioRepositorio.findByUsername(username) != null){
            return false;
        }
        return true;
    }

    @Transactional
    public boolean crearUsuario(Usuario usuario){
        if(usuarioRepositorio.findById(usuario.getId()) != null){
            return false;
        }
        //usuario.setPassword(DigestUtils.md5Hex(usuario.getPassword()));
        usuarioRepositorio.save(usuario);
        return true;
    }

    @Transactional
    public boolean eliminarUsuario(long id){
        Usuario usuario = usuarioRepositorio.findById(id);
        if(usuario != null){
            usuarioRepositorio.delete(usuario);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean editarUsuario(Usuario usuario){
        Optional<Usuario> e = Optional.ofNullable(usuarioRepositorio.findById(usuario.getId()));
        if(e.isPresent()){
            Usuario nuevoUsuario = e.get();
            nuevoUsuario.setUsername(usuario.getUsername());
            nuevoUsuario.setRoles(usuario.getRoles());
            return true;
        }
        return false;
    }
}
