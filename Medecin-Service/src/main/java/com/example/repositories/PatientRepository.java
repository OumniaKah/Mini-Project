package com.example.repositories;

import com.example.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.List;
@RepositoryRestResource
public interface PatientRepository extends JpaRepository<Patient,Long> {
    List<Patient> findByNom(String nom);

    List<Patient> findByEmail(String email);
}
