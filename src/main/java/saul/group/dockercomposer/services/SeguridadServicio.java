package saul.group.dockercomposer.services;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saul.group.dockercomposer.entities.Role;
import saul.group.dockercomposer.entities.Usuario;
import saul.group.dockercomposer.repositories.RoleRepositorio;
import saul.group.dockercomposer.repositories.UsuarioRepositorio;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class SeguridadServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RoleRepositorio roleRepositorio;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public boolean crearUsuarioPorDefecto(){
        try {
            if(usuarioRepositorio.count() == 0){
                Role roleAdmin = new Role("ROLE_ADMIN");
                roleRepositorio.save(roleAdmin);

                Role roleUser = new Role("ROLE_USER");
                roleRepositorio.save(roleUser);

                Usuario usuario = new Usuario("admin",  bCryptPasswordEncoder.encode("admin"), new HashSet<>(Arrays.asList(roleAdmin)), "admin", "admin");
                usuarioRepositorio.save(usuario);
            }

        }catch (NullPointerException e)
        {


            return true;
        }

        return false;
    }

    /**
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Usuario user = usuarioRepositorio.findByUsername(username);

        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Role role : user.getRoles()) {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isActivo(), true, true, true, grantedAuthorities);
    }

}
