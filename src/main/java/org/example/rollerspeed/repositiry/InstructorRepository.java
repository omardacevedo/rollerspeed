package org.example.rollerspeed.repositiry;


import org.example.rollerspeed.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    Optional<Instructor> findByUsername(String username);
}
