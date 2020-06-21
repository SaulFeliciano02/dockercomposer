package saul.group.dockercomposer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import saul.group.dockercomposer.entities.Role;
import saul.group.dockercomposer.entities.Usuario;
import saul.group.dockercomposer.services.UsuarioServicio;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

@Controller
public class LoginController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String logUser(){
        return "";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String logUser(String username, String password, HttpServletRequest request){
        Usuario usuario = (Usuario) request.getSession().getAttribute("logged_user");
        if(usuario == null){
            usuario = usuarioServicio.getUsuarioByUsernameAndPassword(username, password);
            if(usuario != null){
                request.getSession().setAttribute("logged_user", usuario);
            }
            else{
                return "redirect:/login";
            }
        }
        for (Iterator<Role> it = usuario.getRoles().iterator(); it.hasNext(); ) {
            Role r = it.next();
            if (r.equals(new Role("ROLE_USER"))) return "thymeleaf/index";
        }
        return "thymeleaf/users";
    }

}
