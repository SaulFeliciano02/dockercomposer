package saul.group.dockercomposer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import saul.group.dockercomposer.entities.Respuesta;
import saul.group.dockercomposer.entities.Usuario;
import saul.group.dockercomposer.services.RespuestasServicio;
import saul.group.dockercomposer.services.RoleServicio;
import saul.group.dockercomposer.services.UsuarioServicio;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

@Controller
public class UsuarioController {

    @Autowired public UsuarioServicio usuarioServicio;
    @Autowired public RoleServicio roleServicio;
    @Autowired public RespuestasServicio respuestasServicio;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @RequestMapping("/")
    public String inicio(){
        return "thymeleaf/index";
    }

    @RequestMapping("/usuarios")
    public String listaUsuarios(Model model){
        model.addAttribute("listaUsuarios", usuarioServicio.getUsuario());

        respuestasServicio.createIfFirstRespuesta();
        Respuesta respuesta = respuestasServicio.getRespuesta();
        model.addAttribute("respuestas_1", respuesta.getQuestion_rating_1());
        model.addAttribute("respuestas_2", respuesta.getQuestion_rating_2());
        model.addAttribute("respuestas_3", respuesta.getQuestion_rating_3());

        return "thymeleaf/users";
    }

    @RequestMapping("/usuarios/crear")
    public String crearUsuario(Model model, @RequestParam(name = "username") String username,
                               @RequestParam(name = "contrasena") String password,
                               @RequestParam(name = "role") String role){
        Usuario usuario = new Usuario(username, password, new HashSet<>(Arrays.asList(roleServicio.getRoleByName(role))), "", "");
        if(usuarioServicio.checkUser(username)){
            usuarioServicio.crearUsuario(usuario);
        }
        return "redirect:/usuarios";
    }

    @RequestMapping(path = "/usuarios/eliminar/{id}")
    public String eliminarUsuario(Model model, @PathVariable(name = "id") long id){
        usuarioServicio.eliminarUsuario(id);
        return "redirect:/usuarios";
    }

    @RequestMapping("/crearParticipante")
    public RedirectView registrarParticipante(@RequestParam(name = "first_name") String nombre, @RequestParam(name = "last_name") String apellido,
                                              HttpServletRequest request){
        Random random = new Random();
        int correo_num = 1000 + (int)usuarioServicio.getCount();
        String correo = nombre.toLowerCase() + "_" + apellido.replace(" ", "").toLowerCase() + Integer.toHexString(correo_num) + "@barcamp.com";
        int num1 = random.nextInt(9000-1000) + 1000;
        int num2 = random.nextInt(9000-1000) + 1000;
        String password = Integer.toHexString(num1) + nombre.toLowerCase();

        Usuario usuario = new Usuario(correo, bCryptPasswordEncoder.encode(password), new HashSet<>(Arrays.asList(roleServicio.getRoleByName("ROLE_USER"))), nombre, apellido);
        if(!nombre.equalsIgnoreCase("") && !apellido.equalsIgnoreCase("")){
            usuarioServicio.crearUsuario(usuario);
        }
        request.getSession().setAttribute("correo", correo);
        request.getSession().setAttribute("contrasena", password);

        return new RedirectView("/login");
    }

}
