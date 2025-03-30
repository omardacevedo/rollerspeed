package org.example.rollerspeed.repositiry;


import org.example.rollerspeed.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    Optional<Alumno> findByUsername(String username);
    Optional<Alumno> findByCorreo(String correo);
    boolean existsByCorreo(String correo);  // ← 
}
