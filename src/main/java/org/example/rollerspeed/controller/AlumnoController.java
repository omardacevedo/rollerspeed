package org.example.rollerspeed.controller;


import org.example.rollerspeed.service.AlumnoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/alumno")
@Tag(name = "Alumno", description = "Operaciones del alumno")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }


    @GetMapping("/dashboard")
    @Operation(summary = "Mostrar dashboard del alumno", description = "Carga la vista del dashboard con información del alumno.")
    public String dashboard(Model model) {
        model.addAttribute("alumnos", alumnoService.findAll());
        return "alumno_dashboard";
    }

    @GetMapping("/perfil")
    @Operation(summary = "Editar perfil", description = "Muestra la página de edición del perfil del alumno.")
    public String editarPerfil() { 
        return "alumno_perfil"; 
    }
    
    @GetMapping("/pagos")
    @Operation(summary = "Estado de pagos", description = "Muestra el estado de pagos del alumno.")
    public String estadoPagos() { 
        return "alumno_pagos";
    }

    @GetMapping("/horarios")
    @Operation(summary = "Ver horarios", description = "Muestra los horarios de clases del alumno.")
    public String verHorarios() { 
        return "alumno_horarios"; 
    }

    @GetMapping("/notificaciones")
    @Operation(summary = "Ver notificaciones", description = "Muestra las notificaciones del alumno.")
    public String notificaciones() { 
        return "alumno_notificaciones"; 
    }

    @GetMapping("/metodo-pago")
    @Operation(summary = "Método de pago", description = "Permite al alumno gestionar su método de pago.")
    public String metodoPago() { 
        return "alumno_metodo_pago"; 
    }
}
