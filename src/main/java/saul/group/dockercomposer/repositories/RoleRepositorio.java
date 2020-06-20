package saul.group.dockercomposer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saul.group.dockercomposer.entities.Role;

public interface RoleRepositorio extends JpaRepository<Role, String> {

}
