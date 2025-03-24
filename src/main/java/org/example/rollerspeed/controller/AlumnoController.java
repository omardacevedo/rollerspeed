package org.example.rollerspeed.controller;



import org.example.rollerspeed.service.AlumnoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("alumnos", alumnoService.findAll());
        return "alumno_dashboard";
    }

    @GetMapping("/perfil")
    public String editarPerfil() { return "alumno_perfil"; }
    @GetMapping("/pagos")
    public String estadoPagos() { return "alumno_pagos"; }
    @GetMapping("/horarios")
    public String verHorarios() { return "alumno_horarios"; }
    @GetMapping("/notificaciones")
    public String notificaciones() { return "alumno_notificaciones"; }
    @GetMapping("/metodo-pago")
    public String metodoPago() { return "alumno_metodo_pago"; }
}
