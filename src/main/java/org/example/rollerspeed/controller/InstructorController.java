package org.example.rollerspeed.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.rollerspeed.service.InstructorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instructor")
@Tag(name = "Instructor", description = "Controlador para la gestión de instructores y sus actividades")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/dashboard")
    @Operation(summary = "Dashboard del instructor", description = "Muestra el panel principal del instructor con información general.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dashboard mostrado correctamente")
    })
    public String dashboard(Model model) {
        model.addAttribute("instructors", instructorService.findAll());
        return "instructor_dashboard";
    }

    @GetMapping("/horarios")
    @Operation(summary = "Consultar horarios", description = "Muestra los horarios de clases asignadas al instructor.")
    @ApiResponse(responseCode = "200", description = "Página de horarios cargada")
    public String consultarHorarios() {
        return "instructor_horarios";
    }

    @GetMapping("/alumnos")
    @Operation(summary = "Lista de alumnos", description = "Muestra la lista de alumnos asignados al instructor.")
    @ApiResponse(responseCode = "200", description = "Página de alumnos cargada")
    public String listaAlumnos() {
        return "instructor_alumnos";
    }

    @GetMapping("/asistencia")
    @Operation(summary = "Registrar asistencia", description = "Permite al instructor registrar la asistencia de los alumnos.")
    @ApiResponse(responseCode = "200", description = "Página de registro de asistencia cargada")
    public String registrarAsistencia() {
        return "instructor_asistencia";
    }

    @GetMapping("/clases")
    @Operation(summary = "Ver clases", description = "Muestra las clases programadas del instructor.")
    @ApiResponse(responseCode = "200", description = "Página de clases cargada")
    public String verClases() {
        return "instructor_clases";
    }

    @GetMapping("/reportes")
    @Operation(summary = "Generar reportes de asistencia", description = "Permite al instructor ver y generar reportes de asistencia.")
    @ApiResponse(responseCode = "200", description = "Página de reportes cargada")
    public String reportesAsistencia() {
        return "instructor_reportes";
    }
}
