package saul.group.dockercomposer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saul.group.dockercomposer.entities.Participante;
import saul.group.dockercomposer.repositories.ParticipanteRepositorio;

import java.util.List;

@Service
public class ParticipanteServicio {
    @Autowired
    public ParticipanteRepositorio participanteRepositorio;

    public List<Participante> getParticipantes(){
        return participanteRepositorio.findAll();
    }

    public long getCount(){
        return participanteRepositorio.count();
    }
}
