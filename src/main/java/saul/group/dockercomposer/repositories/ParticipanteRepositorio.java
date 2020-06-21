package saul.group.dockercomposer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saul.group.dockercomposer.entities.Participante;

public interface ParticipanteRepositorio extends JpaRepository<Participante, Long> {
}
