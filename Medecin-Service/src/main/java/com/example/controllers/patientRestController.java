package com.example.controllers;

import com.example.dto.PatientDto;
import com.example.entities.Patient;
import com.example.services.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class patientRestController {

    @Autowired
    private ModelMapper modelMapper;  @Autowired
    private PatientService patientService;

    //getAll
    @Operation(summary = "get patients", description = "recuperer liste des patients")
    @GetMapping(value="/patients",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<PatientDto> getPatients() {
        return patientService.patients();
    }
    //PatientbyId
    @Operation(summary = "get patient by id", description = "recuperer patient par id")
    @GetMapping(value="/patient/Id/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PatientDto getPatientById(@PathVariable Long id) {
        return patientService.rechercherPatientParId(id);
    }

    //ListPatientByNom
    @Operation(summary = "get patients by nom", description = "recuperer patient par nom")
    @GetMapping(value="/patient/nom/{nom}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<PatientDto>> getPatientByNom(@PathVariable String nom) {
        List<PatientDto> patients = patientService.rechercherParNom(nom);
        return ResponseEntity.ok(patients);
    }

    // ListPatientByEmail
    @Operation(summary = "get patients by email", description = "recuperer patient par email")
    @GetMapping(value="/patient/email/{email}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<PatientDto>> getPatientByEmail(@PathVariable String email) {
        List<PatientDto> patients = patientService.rechercherParEmail(email);
        return ResponseEntity.ok(patients);
    }

    //enregistrer
    @Operation(summary = "postPatient", description = "enregistrer patient")
    @PostMapping(value="/post/patient",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PatientDto> enregistrerPatient(@RequestBody Patient patient) {
        PatientDto savedPatientDto = patientService.enregistrerPatient(patient);
        return ResponseEntity.ok(savedPatientDto);
    }

    //supprimer
    @Operation(summary = "deletePatient", description = "supprimer patient")
    @DeleteMapping(value="/supprime/patient/{id}"
           )
    public ResponseEntity<Void> supprimerPatient(@PathVariable Long id) {
        patientService.supprimerPatient(id);
        return ResponseEntity.noContent().build();
    }

    // modifier
    @Operation(summary = "updatePatient", description = "modifier patient")
    @PutMapping("/modifie/patient/{id}")
    public ResponseEntity<PatientDto> modifierPatient(@PathVariable Long id, @RequestBody Patient patient) {
        patient.setId(id);
        PatientDto updatedPatientDto = patientService.modifierPatient(patient);
        return ResponseEntity.ok(updatedPatientDto);
    }



}
