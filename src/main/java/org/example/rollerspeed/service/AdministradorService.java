package org.example.rollerspeed.service;


import org.example.rollerspeed.model.Administrador;
import org.example.rollerspeed.repositiry.AdministradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {

    private final AdministradorRepository adminRepository;

    public AdministradorService(AdministradorRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Administrador> findAll() {
        return adminRepository.findAll();
    }
}
