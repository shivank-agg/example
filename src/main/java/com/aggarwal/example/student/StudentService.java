package com.aggarwal.example.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final StudentMapper studentmapper;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository repository, StudentMapper studentmapper, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentmapper = studentmapper;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent(
            StudentDto studentDto
    ){
        var student = studentmapper.toStudent(studentDto);
        var savedStudent = repository.save(student);
        return studentmapper.toStudentDto(savedStudent);
    }

    public List<StudentResponseDto> findAllStudents() {
        return repository.findAll().stream().map(studentMapper::toStudentDto).collect(Collectors.toList());
    }

    public StudentResponseDto findstdbyId(Integer id){
        var student = repository.findById(id).orElse(new Student());
        return studentMapper.toStudentDto(student);

    }

    public List<StudentResponseDto> findbyName(String name){
        return repository
                .findAllByFirstnameContaining(name)
                .stream().
                map(studentMapper::toStudentDto)
                .collect(Collectors.toList());
    }

    public void deleteStudent(Integer id) {
        repository.deleteById(id);
    }
}
