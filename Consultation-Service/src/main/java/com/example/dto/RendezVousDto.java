package com.example.dto;

import lombok.Data;

import java.util.Date;
@Data
public class RendezVousDto {
    private Long id;
    private Date dateRDV;
    private Date heureRDV;
}
