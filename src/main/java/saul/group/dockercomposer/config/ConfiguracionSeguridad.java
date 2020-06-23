package saul.group.dockercomposer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.sql.DataSource;

@EnableGlobalMethodSecurity(securedEnabled = true)
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * La autentificación de los usuarios.
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Clase para encriptar contraseña
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

        //Cargando los usuarios en memoria.
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

    }

    /*
     * Permite configurar las reglas de seguridad.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Marcando las reglas para permitir unicamente los usuarios
        http
                .authorizeRequests()
                .antMatchers("/", "/webjars/**", "/resources/**").permitAll() //permitiendo llamadas a esas urls.
                .antMatchers("/css/**", "/js/**", "/images/**", "/vendor/**").permitAll()//acceso a cualquier estilo/animacion en los archivos
//                .antMatchers("/home").hasAnyRole("ADMIN", "USER")
                .antMatchers("/dbconsole/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/crearParticipante").permitAll()
                .antMatchers("/homeUser/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/usuarios/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().denyAll()//toda llamada debe ser validada
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/homeUser")//redireccion por defecto de autentificacion correcta
                .permitAll()
                .and()
                .logout()
                .permitAll();

        //deshabilitando las seguridad contra los frame internos.
        //Necesario para H2.
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    //this method allows static resources to be neglected by spring security
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**","/webjars/**");
    }
}
