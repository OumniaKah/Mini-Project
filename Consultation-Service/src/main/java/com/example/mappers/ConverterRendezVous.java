package com.example.mappers;

import com.example.dto.RendezVousDto;
import com.example.entities.RendezVous;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ConverterRendezVous {
    @Autowired
    private ModelMapper modelMapper;
    public RendezVousDto convertRendezVousToRendezVousDto(RendezVous rendezVous){
        RendezVousDto rendezVousDto=modelMapper.map(rendezVous,RendezVousDto.class);
        return rendezVousDto;
    }
    public RendezVous convertRendezVousDtoToRendezVous(RendezVousDto rendezVousdto){
        RendezVous rendezVous=modelMapper.map(rendezVousdto,RendezVous.class);
        return rendezVous;
    }
    public List<RendezVousDto> convertListEntityToDto(List<RendezVous> RendezVous) {
        return RendezVous.stream()
                .map(this::convertRendezVousToRendezVousDto)
                .collect(Collectors.toList());
    }
}
