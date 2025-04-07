package org.example.rollerspeed.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String role = authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .findFirst()
                .orElse("");
        System.out.println("AuthenticationSuccessHandler ejecutado. Rol: " + role);

        if (role.equals("ROLE_ALUMNO")) {
            response.sendRedirect("/alumno/dashboard");
        } else if (role.equals("ROLE_INSTRUCTOR")) {
            response.sendRedirect("/instructor/dashboard");
        } else if (role.equals("ROLE_ADMIN")) {
            response.sendRedirect("/admin/dashboard");
        } else {
            response.sendRedirect("/home");
        }
    }
}