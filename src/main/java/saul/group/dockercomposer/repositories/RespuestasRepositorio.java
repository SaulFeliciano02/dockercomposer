package saul.group.dockercomposer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saul.group.dockercomposer.entities.Respuestas;

public interface RespuestasRepositorio extends JpaRepository<Respuestas, Long> {
}
