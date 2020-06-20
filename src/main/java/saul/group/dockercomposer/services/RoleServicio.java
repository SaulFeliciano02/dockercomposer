package saul.group.dockercomposer.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saul.group.dockercomposer.entities.Role;
import saul.group.dockercomposer.entities.Usuario;
import saul.group.dockercomposer.repositories.RoleRepositorio;

import javax.annotation.PostConstruct;

@Service
public class RoleServicio {

    @Autowired
    private RoleRepositorio roleRepositorio;

    @Transactional
    public boolean crearRole(Role role){
        if(roleRepositorio.findById(role.getName()).equals(null)){
            return false;
        }
        roleRepositorio.save(role);
        return true;
    }

//    @PostConstruct
//    public boolean crearRoleAdminPorDefecto(){
//        if(roleRepositorio.findById("ROLE_ADMIN") == null){
//            Role role = new Role("ROLE_ADMIN");
//            crearRole(role);
//            return true;
//        }
//        return false;
//    }
//
//    @PostConstruct
//    public boolean crearRoleUserPorDefecto(){
//        if(roleRepositorio.findById("ROLE_USER") == null){
//            Role role = new Role("ROLE_USER");
//            crearRole(role);
//            return true;
//        }
////        return false;
////    }
}
