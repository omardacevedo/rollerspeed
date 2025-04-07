package org.example.rollerspeed.service;

import org.example.rollerspeed.model.Administrador;
import org.example.rollerspeed.model.Alumno;
import org.example.rollerspeed.model.Instructor;
import org.example.rollerspeed.repositiry.AdministradorRepository;
import org.example.rollerspeed.repositiry.InstructorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.rollerspeed.repositiry.AlumnoRepository;

import java.util.Optional;

@Service
public class UserService {

    private final AlumnoRepository alumnoRepository;
    private final PasswordEncoder passwordEncoder;
    private  final AdministradorRepository adminRepository;
    private final InstructorRepository instructorRepository;
    public UserService(AlumnoRepository alumnoRepository, PasswordEncoder passwordEncoder, AdministradorRepository adminRepository, InstructorRepository instructorRepository) {
        this.alumnoRepository = alumnoRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
        this.instructorRepository = instructorRepository;
    }

    public String validateUser(String correo, String password) {
        System.out.println("Validando usuario por correo: " + correo);


        Optional<Alumno> alumno = alumnoRepository.findByCorreo(correo);
        if (alumno.isPresent() && alumno.get().getPassword().equals(password)) {
            return "ALUMNO";
        }

        System.out.println("Usuario no encontrado por correo: " + correo);
        return null;
    }



    public boolean existsByCorreo(String correo) {
        return alumnoRepository.findByCorreo(correo).isPresent();
    }
}