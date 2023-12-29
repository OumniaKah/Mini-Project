package com.example.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import com.example.entities.RendezVous;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateConsultation;
    private Date rapportConsultation;
    @OneToOne
    @JoinColumn(name = "rendez_vous_id")
    private RendezVous rendezVous;

}