package org.example.rollerspeed.controller;

import org.example.rollerspeed.dto.LoginRequest;
import org.example.rollerspeed.model.Alumno;
import org.example.rollerspeed.repositiry.AlumnoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {

    private final AlumnoRepository alumnoRepository;

    public HomeController(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @GetMapping("/registro")
    public String registroForm(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarAlumno(@ModelAttribute Alumno alumno, Model model) {
        String username = alumno.getCorreo().split("@")[0];
        alumno.setUsername(username);
        alumno.setPassword(username);
        alumno.setMatricula("MAT-" + System.currentTimeMillis());
        alumnoRepository.save(alumno);
        return "redirect:/login";
    }

    @GetMapping("/mision")
    public String mision(Model model) {
        model.addAttribute("titulo", "Misión");
        return "mision";
    }

    @GetMapping("/vision")
    public String vision(Model model) {
        model.addAttribute("titulo", "Visión");
        return "vision";
    }

    @GetMapping("/valores")
    public String valores(Model model) {
        model.addAttribute("titulo", "Valores");
        return "valores";
    }

    @GetMapping("/servicios")
    public String servicios(Model model) {
        model.addAttribute("titulo", "Servicios");
        return "servicios";
    }

    @GetMapping("/eventos")
    public String eventos(Model model) {
        model.addAttribute("titulo", "Eventos");
        return "eventos";
    }
}