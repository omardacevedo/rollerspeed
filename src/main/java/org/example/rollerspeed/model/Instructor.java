package org.example.rollerspeed.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "instructores")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String especialidad;

    @Column(nullable = false)
    private String role = "ROLE_INSTRUCTOR";
}