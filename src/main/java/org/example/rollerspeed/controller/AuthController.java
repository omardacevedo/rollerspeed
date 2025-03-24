package org.example.rollerspeed.controller;

import org.example.rollerspeed.config.JwtTokenProvider;
import org.example.rollerspeed.dto.LoginRequest;
import org.example.rollerspeed.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")

public class AuthController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
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
