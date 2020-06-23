package saul.group.dockercomposer.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saul.group.dockercomposer.entities.Respuesta;
import saul.group.dockercomposer.entities.Usuario;
import saul.group.dockercomposer.repositories.RespuestaRepositorio;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RespuestasServicio {
    @Autowired public RespuestaRepositorio respuestaRepositorio;

    public long getCount(){
        return respuestaRepositorio.count();
    }

    public Respuesta getRespuestaById(long id){
        return respuestaRepositorio.findById(id);
    }

    public Respuesta getRespuesta(){
        return respuestaRepositorio.findFirstBy();
    }

    public ArrayList<Integer> create0List(){
        ArrayList<Integer> question_rating = new ArrayList<>(5);
        for(int i = 0; i < 5; i++){
            question_rating.add(0);
        }
        return question_rating;
    }

    public void updateRespuestas(int answer1, int answer2, int answer3){
        Respuesta respuesta = getRespuesta();
        respuesta.getQuestion_rating_1().set(answer1 - 1, respuesta.getQuestion_rating_1().get(answer1 - 1) + 1);
        respuesta.getQuestion_rating_2().set(answer2 - 1, respuesta.getQuestion_rating_2().get(answer2 - 1) + 1);
        respuesta.getQuestion_rating_3().set(answer3 - 1, respuesta.getQuestion_rating_3().get(answer3 - 1) + 1);
        editarRespuesta(respuesta);
    }

    public void createIfFirstRespuesta(){
        if(getCount() == 0){
            ArrayList<Integer> question_rating_1 = create0List();
            ArrayList<Integer> question_rating_2 = create0List();
            ArrayList<Integer> question_rating_3 = create0List();
            Respuesta respuesta = new Respuesta(question_rating_1, question_rating_2, question_rating_3);
            crearRespuesta(respuesta);
        }
    }

    @Transactional
    public boolean crearRespuesta(Respuesta respuesta){
        if(respuestaRepositorio.findById(respuesta.getId()) != null){
            return false;
        }
        respuestaRepositorio.save(respuesta);
        return true;
    }

    @Transactional
    public boolean editarRespuesta(Respuesta respuesta){
        Optional<Respuesta> e = Optional.ofNullable(respuestaRepositorio.findById(respuesta.getId()));
        if(e.isPresent()){
            Respuesta nuevaRespuesta = e.get();
            nuevaRespuesta.setQuestion_rating_1(respuesta.getQuestion_rating_1());
            nuevaRespuesta.setQuestion_rating_2(respuesta.getQuestion_rating_2());
            nuevaRespuesta.setQuestion_rating_3(respuesta.getQuestion_rating_3());
            return true;
        }
        return false;
    }

}
