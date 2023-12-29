package com.example.controllers;

import com.example.dto.MedecinDto;
import com.example.entities.Medecin;
import com.example.services.MedecinService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedecinController {
    private MedecinService medecinService;

    public MedecinController(MedecinService medecinService) {
        this.medecinService = medecinService;
    }


    @Autowired
    private ModelMapper modelMapper;


    //getAll
    @Operation(summary = "get medecins", description = "recuperer liste des medecins")
    @GetMapping(value="/medecins",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<MedecinDto> getMedecins() {
        return medecinService.medecins();
    }
    //PatientbyId
    @Operation(summary = "get patient by id", description = "recuperer patient par id")
    @GetMapping(value="/patient/Id/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public MedecinDto getMedecinById(@PathVariable Long id) {
        return medecinService.rechercherMedecinParId(id);
    }

    //ListPatientByNom
    @Operation(summary = "get patients by nom", description = "recuperer patient par nom")
    @GetMapping(value="/patient/nom/{nom}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<MedecinDto>> getMedecinByNom(@PathVariable String nom) {
        List<MedecinDto> patients = medecinService.rechercherParNom(nom);
        return ResponseEntity.ok(patients);
    }

    // ListPatientByEmail
    @Operation(summary = "get patients by email", description = "recuperer patient par email")
    @GetMapping(value="/patient/email/{email}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<MedecinDto>> getMedecinByEmail(@PathVariable String email) {
        List<MedecinDto> medecins = medecinService.rechercherParEmail(email);
        return ResponseEntity.ok(medecins);
    }

    //listParSpecialite
    @Operation(summary = "get patients by specialite", description = "recuperer patient par specialite")
    @GetMapping(value="/patient/specialite/{specialite}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<MedecinDto>> getMedecinBySpecialite(@PathVariable String specialite) {
        List<MedecinDto> medecins = medecinService.rechercherParSpecialie(specialite);
        return ResponseEntity.ok(medecins);
    }

    //enregistrer
    @Operation(summary = "postPatient", description = "enregistrer patient")
    @PostMapping(value="/post/patient",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<MedecinDto> enregistrerMedecin(@RequestBody Medecin medecin) {
        MedecinDto savedMedecinDto = medecinService.enregistrerMedecin(medecin);
        return ResponseEntity.ok(savedMedecinDto);
    }

    //supprimer
    @Operation(summary = "deleteMedecin", description = "supprimer medecin")
    @DeleteMapping(value="/supprime/medecin/{id}"
    )
    public ResponseEntity<Void> supprimerMedecin(@PathVariable Long id) {
        medecinService.supprimerMedecin(id);
        return ResponseEntity.noContent().build();
    }

    // modifier

    @Operation(summary = "updatePatient", description = "modifier patient")
    @PutMapping("/modifie/medecin/{id}")
    public ResponseEntity<MedecinDto> modifierMedecin(@PathVariable Long id, @RequestBody Medecin medecin) {
        // Assurez-vous que l'ID est défini dans le medecin
        medecin.setId(id);
        MedecinDto updatedMedecinDto = medecinService.modifierPatient(medecin);

        return ResponseEntity.ok(updatedMedecinDto);
    }




}
