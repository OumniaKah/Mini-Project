package com.example.services;

import com.example.dto.MedecinDto;
import com.example.entities.Medecin;

import com.example.mappers.MedecinConverter;
import com.example.repositories.MedecinRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedecinService {
    private MedecinRepository medecinRepository;
private MedecinConverter medecinConverter;
    public MedecinService(MedecinRepository medecinRepository,MedecinConverter medecinConverter) {
        this.medecinRepository = medecinRepository;
        this.medecinConverter=medecinConverter;
    }

    //return all patients
    public List<MedecinDto> medecins() {
        List<Medecin> medecins = medecinRepository.findAll();
        return medecinConverter.convertListEntityToDto(medecins);
    }
    // Enregistrer un nouveau patient
    public MedecinDto enregistrerMedecin(Medecin medecin) {
        Medecin savedMedecin = medecinRepository.save(medecin);
        return medecinConverter.convertMedecinToMedecinDto(savedMedecin);
    }
    //modifier
    public MedecinDto modifierPatient(Medecin medecin) {
        // Vérifiez si le patient existe
        Optional<Medecin> optionalPatient = medecinRepository.findById(medecin.getId());
        if (optionalPatient.isPresent()) {
            Medecin existingPatient = optionalPatient.get();
            existingPatient.setNom(medecin.getNom());
            existingPatient.setEmail(medecin.getEmail());
            existingPatient.setSpecialite(medecin.getSpecialite());
            Medecin updatedPatient = medecinRepository.save(existingPatient);
            return medecinConverter.convertMedecinToMedecinDto(updatedPatient);
        } else {
            throw new EntityNotFoundException("Patient not found with ID: " + medecin.getId());
        }
    }

    //supprimer
    public void supprimerMedecin(Long patientId) {
        Optional<Medecin> optionalPatient = medecinRepository.findById(patientId);
        if (optionalPatient.isPresent()) {
            medecinRepository.deleteById(patientId);
            System.out.println("Patient supprimé avec succès !");
        } else {

            System.out.println("Patient non trouvé.");

        }
    }

    // Rechercher un patient par son ID
    public MedecinDto rechercherMedecinParId(Long medecinId) {
        Optional<Medecin> optionalMedecin = medecinRepository.findById(medecinId);
        return optionalMedecin.map(medecinConverter::convertMedecinToMedecinDto).orElse(null);
    }
    // Rechercher des patients par nom
    public List<MedecinDto> rechercherParNom(String nom) {
        List<Medecin> medecins = medecinRepository.findByNom(nom);
        return medecinConverter.convertListEntityToDto(medecins);
    }
    // Rechercher des patients par email
    public List<MedecinDto> rechercherParEmail(String email) {
        List<Medecin> medecins = medecinRepository.findByEmail(email);
        return medecinConverter.convertListEntityToDto(medecins);
    }

    // Rechercher des patients par specialite
    public List<MedecinDto> rechercherParSpecialie(String specialite) {
        List<Medecin> medecins = medecinRepository.findBySpecialite(specialite);
        return medecinConverter.convertListEntityToDto(medecins);
    }
}
