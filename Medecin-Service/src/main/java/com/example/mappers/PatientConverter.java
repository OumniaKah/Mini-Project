package com.example.mappers;

import com.example.dto.PatientDto;
import com.example.entities.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class PatientConverter {
    @Autowired
    private ModelMapper modelMapper;
    public PatientDto convertPatientToPatientDto(Patient patient){
        PatientDto patientDto=modelMapper.map(patient,PatientDto.class);
        return patientDto;
    }
    public Patient convertPatientDtoToPatient(PatientDto patientdto){
        Patient patient=modelMapper.map(patientdto,Patient.class);
        return patient;
    }
    public List<PatientDto> convertListEntityToDto(List<Patient> patients) {
        return patients.stream()
                .map(this::convertPatientToPatientDto)
                .collect(Collectors.toList());
    }

}
