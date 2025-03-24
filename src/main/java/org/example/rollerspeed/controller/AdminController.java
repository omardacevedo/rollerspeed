package org.example.rollerspeed.controller;


import org.example.rollerspeed.service.AdministradorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdministradorService adminService;

    public AdminController(AdministradorService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("admins", adminService.findAll());
        return "admin_dashboard";
    }

    @GetMapping("/alumnos")
    public String gestionarAlumnos() { return "admin_alumnos"; }
    @GetMapping("/instructores")
    public String gestionarInstructores() { return "admin_instructores"; }
    @GetMapping("/reportes")
    public String generarReportes() { return "admin_reportes"; }
    @GetMapping("/info-institucional")
    public String infoInstitucional() { return "admin_info"; }
    @GetMapping("/horarios")
    public String programarHorarios() { return "admin_horarios"; }
}