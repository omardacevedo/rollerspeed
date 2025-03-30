package org.example.rollerspeed.controller;


import org.example.rollerspeed.service.AdministradorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/admin")
@Tag(name = "Administrador", description = "Operaciones de administración del sistema")
public class AdminController {

    private final AdministradorService adminService;

    public AdminController(AdministradorService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    @Operation(summary = "Mostrar dashboard", description = "Carga la vista principal del administrador con información general.")
    public String dashboard(Model model) {
        model.addAttribute("admins", adminService.findAll());
        return "admin_dashboard";
    }

    @GetMapping("/alumnos")
    @Operation(summary = "Gestionar alumnos", description = "Accede a la vista de gestión de alumnos.")
    public String gestionarAlumnos() { 
        return "admin_alumnos"; 
    }

    @GetMapping("/instructores")
    @Operation(summary = "Gestionar instructores", description = "Accede a la vista de gestión de instructores.")
    public String gestionarInstructores() { 
        return "admin_instructores"; 
    }

    @GetMapping("/reportes")
    @Operation(summary = "Generar Reportes", description = "Accede a la vista de generación de reportes.")
    public String generarReportes() { 
        return "admin_reportes"; 
    }

    @GetMapping("/info-institucional")
    @Operation(summary = "Ver Informacion Institucional", description = "Accede a la sección de información institucional.")
    public String infoInstitucional() {
    return "admin_info"; 
    }

    @GetMapping("/horarios")
    @Operation(summary = "Programar horarios", description = "Accede a la vista para programar horarios.")
    public String programarHorarios() { 
        return "admin_horarios"; 
    }
}