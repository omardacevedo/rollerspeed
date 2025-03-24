package org.example.rollerspeed.controller;


import org.example.rollerspeed.service.InstructorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("instructors", instructorService.findAll());
        return "instructor_dashboard";
    }

    @GetMapping("/horarios")
    public String consultarHorarios() { return "instructor_horarios"; }
    @GetMapping("/alumnos")
    public String listaAlumnos() { return "instructor_alumnos"; }
    @GetMapping("/asistencia")
    public String registrarAsistencia() { return "instructor_asistencia"; }
    @GetMapping("/clases")
    public String verClases() { return "instructor_clases"; }
    @GetMapping("/reportes")
    public String reportesAsistencia() { return "instructor_reportes"; }
}