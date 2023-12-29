package com.example.services;

import com.example.dto.consultationDto;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class ConsultationServiceImpl implements ConsultationService{
    @Override
    public consultationDto ajouterConsultation(Long rendezVousId, Date dateConsultation, Date rapportConsultation) {
        return null;
    }

    @Override
    public boolean supprimerConsultation(Long consultationId) {
        return false;
    }
}
