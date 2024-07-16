package com.aggarwal.example.studentprofile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentProfileController {

    StudentProfileRepository repository;
    public StudentProfileController(StudentProfileRepository repository) {
        this.repository = repository;
    }
    @PostMapping("/std-profile")
    public StudentProfile saveStudentProfile(
            @RequestBody StudentProfile studentProfile
    ) {
        return repository.save(studentProfile);
    }

    @GetMapping("/std-profile")
    public List<StudentProfile> getStudentProfile() {
        return repository.findAll();
    }

}
