package com.example.repositories;

import com.example.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource
public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    List<Medecin> findByNom(String nom);

    List<Medecin> findByEmail(String email);

    List<Medecin> findBySpecialite(String specialitie);
}
