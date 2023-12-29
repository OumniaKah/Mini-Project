package com.example.controllers;


import com.example.dto.RendezVousDto;
import com.example.services.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.util.Date;
import java.util.List;

@RestController
public class RendezVousController {
    @Autowired
    private RendezVousService rendezVousService;
/*
    @PostMapping("/planifier")
    public ResponseEntity<RendezVousDto> planifierRendezVous(@RequestParam Long patientId,
                                                             @RequestParam Long medecinId,
                                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateRDV,
                                                             @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") Date heureRDV) {
        RendezVousDto rendezVousDto = rendezVousService.planifierRendezVous(patientId, medecinId, dateRDV, heureRDV);
        if (rendezVousDto != null) {
            return new ResponseEntity<>(rendezVousDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }*/

    @GetMapping("/{rendezVousId}")
    public ResponseEntity<RendezVousDto> afficherRendezVous(@PathVariable Long rendezVousId) {
        RendezVousDto rendezVousDto = rendezVousService.afficherRendezVous(rendezVousId);
        if (rendezVousDto != null) {
            return new ResponseEntity<>(rendezVousDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tous")
    public ResponseEntity<List<RendezVousDto>> afficherTousLesRendezVous() {
        List<RendezVousDto> tousLesRendezVous = rendezVousService.afficherTousLesRendezVous();
        return new ResponseEntity<>(tousLesRendezVous, HttpStatus.OK);
    }

    @PutMapping("/{rendezVousId}/modifier")
    public ResponseEntity<RendezVousDto> modifierRendezVous(@PathVariable Long rendezVousId,
                                                            @RequestBody RendezVousDto nouveauRendezVousDto) {
        RendezVousDto rendezVousDto = rendezVousService.modifierRendezVous(rendezVousId, nouveauRendezVousDto);
        if (rendezVousDto != null) {
            return new ResponseEntity<>(rendezVousDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{rendezVousId}/annuler")
    public ResponseEntity<String> annulerRendezVous(@PathVariable Long rendezVousId) {
        boolean success = rendezVousService.annulerRendezVous(rendezVousId);
        if (success) {
            return new ResponseEntity<>("Rendez-vous annulé avec succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Rendez-vous non trouvé", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/associer-a-consultation")
    public ResponseEntity<RendezVousDto> associerRendezVousAConsultation(@RequestParam Long rendezVousId,
            @RequestParam Long consultationId) {
        RendezVousDto rendezVousAssocie = rendezVousService.associerRendezVousAConsultation(rendezVousId, consultationId);
        if (rendezVousAssocie != null) {
            return ResponseEntity.ok(rendezVousAssocie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
