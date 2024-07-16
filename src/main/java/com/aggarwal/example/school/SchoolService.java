package com.aggarwal.example.school;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public SchoolDto saveSchool(SchoolDto dto) {
        var School = schoolMapper.toSchool(dto);
        schoolRepository.save(School);
        return dto;
    }

    public List<SchoolDto> findAllSchool() {
        return schoolRepository.findAll().stream().map(schoolMapper::toSchoolDto).collect(Collectors.toList());
    }
}
