package com.example.services;

import com.example.dto.RendezVousDto;
import java.util.Date;
import java.util.List;

public interface RendezVousService {
    //public RendezVousDto planifierRendezVous(Long patientId, Long medecinId, Date dateRDV, Date heureRDV);
    RendezVousDto afficherRendezVous(Long rendezVousId);
    List<RendezVousDto> afficherTousLesRendezVous();
    RendezVousDto modifierRendezVous(Long rendezVousId, RendezVousDto nouveauRendezVousDTO);
    public boolean annulerRendezVous(Long rendezVousId);
    RendezVousDto associerRendezVousAConsultation(Long rendezVousId, Long consultationId);
}
