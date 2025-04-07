package org.example.rollerspeed.service;


import org.example.rollerspeed.model.Alumno;
import org.example.rollerspeed.repositiry.AlumnoRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class AlumnoService implements UserDetailsService {

    private final AlumnoRepository alumnoRepository;

    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        System.out.println("Buscando usuario por correo: " + correo);
        Alumno alumno = alumnoRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + correo));
        System.out.println("Usuario encontrado: " + alumno.getCorreo() + ", Rol: " + alumno.getRole());
        return new org.springframework.security.core.userdetails.User(
                alumno.getCorreo(), // Usamos correo como identificador
                alumno.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + alumno.getRole()))
        );
    }

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public List<Alumno> findAll() {
        return alumnoRepository.findAll();
    }

     public boolean existsByCorreo(String correo) {
        Optional<Alumno> alumno = alumnoRepository.findByCorreo(correo);

        return alumno.isPresent();
    }
}
