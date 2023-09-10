package com.Proyecto.Avanzar.Security;

import com.Proyecto.Avanzar.Services.implement.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/login/generartoken","/api/login/usuarioActual/**","/api/login/signInWithToken",
                        "/api/persona/registrar/**","/api/persona/listar/**", "/api/persona/**",  "/api/persona/findByCedula/{cedula}/**", "/api/persona/findByCorreo/{correo}/**","/api/persona/listarResponsable/**","/api/detalleSubscripcion/**",
                        "/api/usuarios/**","/api/usuarios/registrar/{rolId}/**","/api/usuarios/registrarConFoto/{rolId}/**",
                        "/api/usuarios/buscaruser/**","/api/usuarios/buscar/**","/api/usuariorol/listarol/**","/api/usuariorol/nombreRol/{usuarioId}/**",
                        "/swagger-ui/**","api/usuarios/actualizarUsuarioConFoto/{usuarioId}/**",
                        "/api/categoria/**", "/api/categoriaProducto/**", "/api/categoriaServicio/registrar/**",
                        "/api/comentarios/registrar/**", "/api/likes/registrar/**", "/api/productos/**","/api/publicaciones/**",
                        "/api/servicios/**", "/api/subscripcion/registrar/**", "/api/tipoLike/registrar/**", "/api/vendedor/**","/api/usuarios/upload/**","/api/usuarios/**","/api/email/sentCodeVerification",
                        "/api/categoria/registrar/**", "/api/categoriaProducto/registrar/**", "/api/categoriaServicio/registrar/**",
                        "/api/comentarios/registrar/**", "/api/likes/registrar/**", "/api/productos/registrar/**","/api/publicaciones/registrar/**",
                        "/api/servicios/registrar/**", "/api/subscripcion/registrar/**", "/api/tipoLike/registrar/**", "/api/vendedor/registrar/**","/api/usuarios/upload/**","/api/usuarios/**","/api/email/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
