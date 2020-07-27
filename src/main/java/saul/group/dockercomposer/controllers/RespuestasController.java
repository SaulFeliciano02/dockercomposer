package saul.group.dockercomposer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import saul.group.dockercomposer.entities.Respuesta;
import saul.group.dockercomposer.entities.Role;
import saul.group.dockercomposer.entities.Usuario;
import saul.group.dockercomposer.services.RespuestasServicio;
import saul.group.dockercomposer.services.RoleServicio;
import saul.group.dockercomposer.services.UsuarioServicio;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;

@Controller
@RequestMapping("/homeUser")
public class RespuestasController {

    @Autowired public RespuestasServicio respuestaServicio;
    @Autowired public UsuarioServicio usuarioServicio;
    @Autowired public RoleServicio roleServicio;

    @RequestMapping("")
    public String display(Principal principal){
       Usuario usuario = usuarioServicio.getUsuarioByUsername(principal.getName());
       for (Iterator<Role> it = usuario.getRoles().iterator(); it.hasNext(); ) {
           Role r = it.next();
           if (r.equals(roleServicio.getRoleByName("ROLE_ADMIN"))) return "redirect:/usuarios";
       }
       return "thymeleaf/homeUser";
   }

   @RequestMapping("/guardarRespuestas")
   public String saveAnswers(@RequestParam("respuesta1_score") int answer1,
                             @RequestParam("respuesta2_score") int answer2,
                             @RequestParam("respuesta3_score") int answer3,
                             @RequestParam("comentario") String comentario,
                             Principal principal){
       respuestaServicio.createIfFirstRespuesta();
       respuestaServicio.updateRespuestas(answer1, answer2, answer3);
       Usuario usuario = usuarioServicio.getUsuarioByUsername(principal.getName());
       usuario.setComentario(comentario);
       usuarioServicio.editarUsuario(usuario);
       return "redirect:/";
   }

}
