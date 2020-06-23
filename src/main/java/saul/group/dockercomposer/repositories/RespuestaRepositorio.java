package saul.group.dockercomposer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import saul.group.dockercomposer.entities.Respuesta;

public interface RespuestaRepositorio extends JpaRepository<Respuesta, Long> {
    Respuesta findById(long id);

    Respuesta findFirstBy();
}
