package org.example.rollerspeed.controller;

import org.springframework.security.core.Authentication;
import org.example.rollerspeed.dto.LoginRequest;
import org.example.rollerspeed.model.Alumno;
import org.example.rollerspeed.repositiry.AlumnoRepository;
import org.example.rollerspeed.service.AlumnoService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name = "Home", description = "Controlador principal con páginas de inicio, autenticación y registro")
public class HomeController {

    private final AlumnoRepository alumnoRepository;
    private final AlumnoService alumnoService;

    public HomeController(AlumnoRepository alumnoRepository,AlumnoService alumnoService) {
        this.alumnoRepository = alumnoRepository;
        this.alumnoService = alumnoService;
    }

    @GetMapping({"/home"})
    @Operation(summary = "Página de inicio", description = "Redirige a la página de inicio si el usuario está autenticado, de lo contrario lo envía al login.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Página de inicio mostrada correctamente"),
        @ApiResponse(responseCode = "302", description = "Redirige al login si el usuario no está autenticado")
    })
    public String home(Model model) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();  // Obtener el nombre de usuario autenticado
            model.addAttribute("username", username);
            return "home";  // Puedes devolver la vista de inicio
        }
        
        return "redirect:/login";  // Si no está autenticado, redirigir al login
    }


    @GetMapping("/login")
    @Operation(summary = "Formulario de login", description = "Muestra el formulario de inicio de sesión.")
    @ApiResponse(responseCode = "200", description = "Página de login cargada")
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @GetMapping("/registro")
    @Operation(summary = "Formulario de registro", description = "Muestra el formulario para el registro de nuevos alumnos.")
    @ApiResponse(responseCode = "200", description = "Página de registro cargada")
    public String registroForm(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "registro";
    }

    @PostMapping("/registro")
    @Operation(summary = "Registrar alumno", description = "Procesa el formulario de registro y guarda el alumno en la base de datos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Redirige al login después del registro exitoso"),
        @ApiResponse(responseCode = "400", description = "Muestra error si el correo ya está registrado")
    })
    public String registrarAlumno(@ModelAttribute Alumno alumno, Model model) {
        // Verificar si el correo electrónico ya existe
        if (alumnoService.existsByCorreo(alumno.getCorreo())) {
        // Si el correo ya está registrado, mostrar un mensaje de error
        model.addAttribute("error", "El correo electrónico ya está registrado.");
        return "registro"; // Volver al formulario de registro
        }

         // Encriptar la contraseña
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(alumno.getPassword());
        alumno.setPassword(encodedPassword); 

        //Usar la contraseña inscrita por el usuario:
        String username = alumno.getCorreo().split("@")[0];
        alumno.setUsername(username); //Nombre de usuario basado en el correo 
        alumno.setMatricula("MAT-" + System.currentTimeMillis());//asignar matricula
        alumnoRepository.save(alumno);
        return "redirect:/login";
    }

    @GetMapping("/mision")
    @Operation(summary = "Página de misión", description = "Muestra la sección sobre la misión de la institución.")
    @ApiResponse(responseCode = "200", description = "Página de misión cargada")
    public String mision(Model model) {
        model.addAttribute("titulo", "Misión");
        return "mision";
    }

    @GetMapping("/vision")
    @Operation(summary = "Página de visión", description = "Muestra la sección sobre la visión de la institución.")
    @ApiResponse(responseCode = "200", description = "Página de visión cargada")
    public String vision(Model model) {
        model.addAttribute("titulo", "Visión");
        return "vision";
    }

    @GetMapping("/valores")
    @Operation(summary = "Página de valores", description = "Muestra la sección sobre los valores de la institución.")
    @ApiResponse(responseCode = "200", description = "Página de valores cargada")
    public String valores(Model model) {
        model.addAttribute("titulo", "Valores");
        return "valores";
    }

    @GetMapping("/servicios")
    @Operation(summary = "Página de servicios", description = "Muestra la sección sobre los servicios que ofrece la institución.")
    @ApiResponse(responseCode = "200", description = "Página de servicios cargada")
    public String servicios(Model model) {
        model.addAttribute("titulo", "Servicios");
        return "servicios";
    }

    @GetMapping("/eventos")
    @Operation(summary = "Página de eventos", description = "Muestra la sección sobre los eventos organizados por la institución.")
    @ApiResponse(responseCode = "200", description = "Página de eventos cargada")
    public String eventos(Model model) {
        model.addAttribute("titulo", "Eventos");
        return "eventos";
    }
}
