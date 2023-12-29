package com.example.mappers;

import com.example.dto.MedecinDto;
import com.example.entities.Medecin;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class MedecinConverter {
    @Autowired
    private ModelMapper modelMapper;
    public MedecinDto convertMedecinToMedecinDto(Medecin medecin){
        MedecinDto medecinDto=modelMapper.map(medecin,MedecinDto.class);
        return medecinDto;
    }
    public Medecin convertMedecinDtoToMedecin(MedecinDto medecindto){
        Medecin medecin=modelMapper.map(medecindto,Medecin.class);
        return medecin;
    }
    public List<MedecinDto> convertListEntityToDto(List<Medecin> medecins) {
        return medecins.stream()
                .map(this::convertMedecinToMedecinDto)
                .collect(Collectors.toList());
    }

}
