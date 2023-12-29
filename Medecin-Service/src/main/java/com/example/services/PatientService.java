package com.example.services;

import com.example.dto.PatientDto;
import com.example.entities.Patient;
import com.example.mappers.PatientConverter;
import com.example.repositories.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private PatientRepository patientRepository;
    private PatientConverter patientConverter;
    @Autowired
    public PatientService(PatientRepository patientRepository,PatientConverter patientConverter){
        this.patientRepository = patientRepository;
        this.patientConverter=patientConverter;
    }
//return all patients
public List<PatientDto> patients() {
   List<Patient>  patients = patientRepository.findAll();
    return patientConverter.convertListEntityToDto(patients);
}
    // Enregistrer un nouveau patient
    public PatientDto enregistrerPatient(Patient patient) {
        Patient savedPatient = patientRepository.save(patient);
        return patientConverter.convertPatientToPatientDto(savedPatient);
    }
    //modifier
    public PatientDto modifierPatient(Patient patient) {
        // Vérifiez si le patient existe
        Optional<Patient> optionalPatient = patientRepository.findById(patient.getId());
        if (optionalPatient.isPresent()) {
            Patient existingPatient = optionalPatient.get();
            existingPatient.setNom(patient.getNom());
            existingPatient.setEmail(patient.getEmail());
            Patient updatedPatient = patientRepository.save(existingPatient);
            return patientConverter.convertPatientToPatientDto(updatedPatient);
        } else {
            throw new EntityNotFoundException("Patient not found with ID: " + patient.getId());
        }
    }

    //supprimer
    public void supprimerPatient(Long patientId) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isPresent()) {
            patientRepository.deleteById(patientId);
            System.out.println("Patient supprimé avec succès !");
        } else {

            System.out.println("Patient non trouvé.");

        }
    }

    // Rechercher un patient par son ID
    public PatientDto rechercherPatientParId(Long patientId) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        return optionalPatient.map(patientConverter::convertPatientToPatientDto).orElse(null);
    }
    // Rechercher des patients par nom
    public List<PatientDto> rechercherParNom(String nom) {
        List<Patient> patients = patientRepository.findByNom(nom);
        return patientConverter.convertListEntityToDto(patients);
    }
    // Rechercher des patients par email
    public List<PatientDto> rechercherParEmail(String email) {
        List<Patient> patients = patientRepository.findByEmail(email);
        return patientConverter.convertListEntityToDto(patients);
    }
}

