package org.example.rollerspeed.service;

import org.example.rollerspeed.model.Administrador;
import org.example.rollerspeed.model.Alumno;
import org.example.rollerspeed.model.Instructor;
import org.example.rollerspeed.repositiry.AdministradorRepository;
import org.example.rollerspeed.repositiry.AlumnoRepository;
import org.example.rollerspeed.repositiry.InstructorRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service


public class UserService {

    private final AdministradorRepository adminRepository;
    private final InstructorRepository instructorRepository;
    private final AlumnoRepository alumnoRepository;

    public UserService(AdministradorRepository adminRepository, InstructorRepository instructorRepository, AlumnoRepository alumnoRepository) {
        this.adminRepository = adminRepository;
        this.instructorRepository = instructorRepository;
        this.alumnoRepository = alumnoRepository;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepository.findByUsername(username)
                .map(admin -> new User(admin.getUsername(), admin.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority(admin.getRole()))))
                .or(() -> instructorRepository.findByUsername(username)
                        .map(instructor -> new User(instructor.getUsername(), instructor.getPassword(),
                                Collections.singletonList(new SimpleGrantedAuthority(instructor.getRole())))))
                .or(() -> alumnoRepository.findByUsername(username)
                        .map(alumno -> new User(alumno.getUsername(), alumno.getPassword(),
                                Collections.singletonList(new SimpleGrantedAuthority(alumno.getRole())))))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public String validateUser(String username, String password) {
        if (adminRepository.findByUsername(username).map(a -> a.getPassword().equals(password)).orElse(false)) {
            return "ROLE_ADMIN";
        } else if (instructorRepository.findByUsername(username).map(i -> i.getPassword().equals(password)).orElse(false)) {
            return "ROLE_INSTRUCTOR";
        } else if (alumnoRepository.findByUsername(username).map(a -> a.getPassword().equals(password)).orElse(false)) {
            return "ROLE_ALUMNO";
        }
        return null;
    }

    public String getUserRole(String username) {
        return adminRepository.findByUsername(username).map(Administrador::getRole)
                .or(() -> instructorRepository.findByUsername(username).map(Instructor::getRole))
                .or(() -> alumnoRepository.findByUsername(username).map(Alumno::getRole))
                .orElse(null);
    }
}
