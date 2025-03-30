package org.example.rollerspeed.controller;

import org.example.rollerspeed.config.JwtTokenProvider;
import org.example.rollerspeed.dto.LoginRequest;
import org.example.rollerspeed.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/auth")
@Tag(name = "Autenticación", description = "Endpoints relacionados con la autenticación y generación de tokens")
public class AuthController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Autentica a un usuario y redirige según su rol.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Redirige al dashboard correspondiente"),
        @ApiResponse(responseCode = "400", description = "Credenciales inválidas, retorna a la página de login")
    })
    
    public String login(@ModelAttribute LoginRequest loginRequest, Model model) {
        String role = userService.validateUser(loginRequest.getUsername(), loginRequest.getPassword());
        if (role != null) {
            String token = jwtTokenProvider.createToken(loginRequest.getUsername());
            model.addAttribute("token", token);
            switch (role) {
                case "ROLE_ADMIN": return "redirect:/admin/dashboard";
                case "ROLE_INSTRUCTOR": return "redirect:/instructor/dashboard";
                case "ROLE_ALUMNO": return "redirect:/alumno/dashboard";
                default: return "home";
            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

}
