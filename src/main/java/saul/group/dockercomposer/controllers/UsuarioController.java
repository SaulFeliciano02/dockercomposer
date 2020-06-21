package saul.group.dockercomposer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import saul.group.dockercomposer.entities.Usuario;
import saul.group.dockercomposer.services.ParticipanteServicio;
import saul.group.dockercomposer.services.RoleServicio;
import saul.group.dockercomposer.services.UsuarioServicio;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

@Controller
public class UsuarioController {

    @Autowired public UsuarioServicio usuarioServicio;
    @Autowired public RoleServicio roleServicio;

    @RequestMapping("/")
    public String registrarParticipante(@RequestParam(name = "first_name") String nombre, @RequestParam(name = "last_name") String apellido){
        Random random = new Random();
        int correo_num = 1000 + (int)usuarioServicio.getCount();
        String correo = nombre.toLowerCase() + "_" + apellido.replace(" ", "").toLowerCase() + Integer.toHexString(correo_num) + "@barcamp.com";
        System.out.println(correo);
        int num1 = random.nextInt(9000-1000) + 1000;
        int num2 = random.nextInt(9000-1000) + 1000;
        String password = Integer.toHexString(num1) + nombre.toLowerCase() + Integer.toHexString(num2);

        Usuario usuario = new Usuario(correo, password, new HashSet<>(Arrays.asList(roleServicio.getRoleByName("ROLE_USER"))), nombre, apellido);
        usuarioServicio.crearUsuario(usuario);
        return "thymeleaf/index";
    }

}
