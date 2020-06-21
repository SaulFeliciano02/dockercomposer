package saul.group.dockercomposer.services;

import org.springframework.beans.factory.annotation.Autowired;
import saul.group.dockercomposer.repositories.RespuestasRepositorio;

public class RespuestasServicio {
    @Autowired public RespuestasRepositorio respuestasRepositorio;

    public long getCount(){
        return respuestasRepositorio.count();
    }
}
