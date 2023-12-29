package com.example.services;

import com.example.dto.consultationDto;

import java.util.Date;

public interface ConsultationService {
    consultationDto ajouterConsultation(Long rendezVousId, Date dateConsultation, Date rapportConsultation);

    boolean supprimerConsultation(Long consultationId);
}
