package com.example.services;

import com.example.dto.RendezVousDto;
import com.example.entities.Medecin;
import com.example.entities.Patient;
import com.example.entities.RendezVous;
import com.example.entities.consultation;
import com.example.mappers.ConverterRendezVous;
import com.example.repositories.MedecinRepository;
import com.example.repositories.PatientRepository;
import com.example.repositories.RendezVousRepository;
import com.example.repositories.consultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
@Service
public class RendezVousServiceImpl implements RendezVousService{

    @Autowired
    private RendezVousRepository rendezVousRepository;
    @Autowired
    private consultationRepository consultationRepository;

    @Autowired
    private ConverterRendezVous converterRendezVous;
    /*@Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private RestTemplate restTemplate;

    //@Value("${patient-service.url}")
    private String patientServiceUrl;

    @Override
    public RendezVousDto planifierRendezVous(Long patientId, Long medecinId, Date dateRDV, Date heureRDV) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        Medecin medecin = medecinRepository.findById(medecinId).orElse(null);
        if (patient != null && medecin != null) {
            RendezVous rendezVous = new RendezVous();
            rendezVous.setPatient(patient);
            rendezVous.setMedecin(medecin);
            rendezVous.setDateRDV(dateRDV);
            rendezVous.setHeureRDV(heureRDV);
            RendezVous nouveauRendezVous = rendezVousRepository.save(rendezVous);
            return converterRendezVous.convertRendezVousToRendezVousDto(nouveauRendezVous);
        } else {
            return null;
        }
    }*/

    @Override
    public RendezVousDto afficherRendezVous(Long rendezVousId) {
        RendezVous rendezVous = rendezVousRepository.findById(rendezVousId).orElse(null);
        return (rendezVous != null) ? converterRendezVous.convertRendezVousToRendezVousDto(rendezVous) : null;
    }

    @Override
    public List<RendezVousDto> afficherTousLesRendezVous() {
        List<RendezVous> tousLesRendezVous = rendezVousRepository.findAll();
        return converterRendezVous.convertListEntityToDto(tousLesRendezVous);
    }

    @Override
    public RendezVousDto modifierRendezVous(Long rendezVousId, RendezVousDto nouveauRendezVousDto) {
        RendezVous rendezVousExist = rendezVousRepository.findById(rendezVousId).orElse(null);
        if (rendezVousExist != null) {
            RendezVous nouveauRendezVous = converterRendezVous.convertRendezVousDtoToRendezVous(nouveauRendezVousDto);
            rendezVousExist.setDateRDV(nouveauRendezVous.getDateRDV());
            rendezVousExist.setHeureRDV(nouveauRendezVous.getHeureRDV());
            return converterRendezVous.convertRendezVousToRendezVousDto(rendezVousRepository.save(rendezVousExist));
        } else {
            return null;
        }
    }

    @Override
    public boolean annulerRendezVous(Long rendezVousId) {
        RendezVous rendezVous = rendezVousRepository.findById(rendezVousId).orElse(null);
        if (rendezVous != null) {
            rendezVousRepository.delete(rendezVous);
            return true;
        } else {
            return false;
        }
    }
    @Override
    public RendezVousDto associerRendezVousAConsultation(Long rendezVousId, Long consultationId) {
        RendezVous rendezVous = rendezVousRepository.findById(rendezVousId).orElse(null);
        consultation consultation = consultationRepository.findById(consultationId).orElse(null);
        if (rendezVous != null && consultation != null) {
            rendezVous.setConsultation(consultation);
            RendezVous rendezVousAssocie = rendezVousRepository.save(rendezVous);
            consultation.setRendezVous(rendezVousAssocie);
            consultationRepository.save(consultation);
            return converterRendezVous.convertRendezVousToRendezVousDto(rendezVousAssocie);
        } else {
            return null;
        }
    }
}
