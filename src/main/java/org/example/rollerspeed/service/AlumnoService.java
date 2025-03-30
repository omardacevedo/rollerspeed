package org.example.rollerspeed.service;


import org.example.rollerspeed.model.Alumno;
import org.example.rollerspeed.repositiry.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public List<Alumno> findAll() {
        return alumnoRepository.findAll();
    }
     // Verificar si el correo electrónico ya está registrado
     public boolean existsByCorreo(String correo) {
        Optional<Alumno> alumno = alumnoRepository.findByCorreo(correo);
        return alumno.isPresent();
    }
}
