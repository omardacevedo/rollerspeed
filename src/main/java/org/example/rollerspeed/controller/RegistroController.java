package org.example.rollerspeed.controller;

import org.example.rollerspeed.model.Alumno;
import org.example.rollerspeed.repositiry.AlumnoRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Tag(name = "Registro", description = "Operaciones relacionadas con el registro de alumnos.")
public class RegistroController {
    private final AlumnoRepository alumnoRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistroController(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Método para mostrar el formulario de registro
    @GetMapping("/registro-nuevo")
    @Operation(summary = "Mostrar formulario de registro", description = "Devuelve la vista para registrar un nuevo alumno.")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "registro"; 
    }

    // Método para procesar el registro de alumno
    @PostMapping("/registro-nuevo")
    @Operation(summary = "Registrar nuevo alumno", description = "Procesa los datos del formulario y registra un nuevo alumno.")
    public String registrarAlumno(@ModelAttribute Alumno alumno, Model model) {
        // Verificar si el correo ya está registrado
        if (alumnoRepository.existsByCorreo(alumno.getCorreo())) {
            model.addAttribute("error", "El correo ya está en uso.");
            return "registro"; // Redirigir al formulario con mensaje de error
        }

        // Encriptar la contraseña antes de guardarla
        alumno.setPassword(passwordEncoder.encode(alumno.getPassword()));

        // Generar nombre de usuario y matrícula
        alumno.setUsername(alumno.getCorreo().split("@")[0]);
        alumno.setMatricula("MAT-" + System.currentTimeMillis());

        // Guardar en la base de datos
        alumnoRepository.save(alumno);

        return "redirect:/login"; // Redirigir a la página de login
    }
}
