package com.example.repositories;

import com.example.entities.consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface consultationRepository extends JpaRepository<consultation,Long> {
}
