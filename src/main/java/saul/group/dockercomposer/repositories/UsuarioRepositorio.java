package saul.group.dockercomposer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import saul.group.dockercomposer.entities.Usuario;

import java.util.List;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    List<Usuario> findAllByRoles(String role);

    List<Usuario> findAllByUsername(String username);

    Usuario findById(long id);

    Usuario findByUsername(String username);

    Usuario countUsuariosByIdGreaterThan(long id);

    Usuario findUsuarioByUsernameAndPassword(String username, String password);

}