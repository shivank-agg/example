package com.aggarwal.example.student;

import com.aggarwal.example.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public StudentResponseDto toStudentDto(Student student) {
        return new StudentResponseDto(
                student.getFirstname(),
                student.getLastname(),
                student.getEmail()
        );
    }

    public Student toStudent(StudentDto Dto) {
        if(Dto == null){
            throw new NullPointerException("School is null");
        }
        var Student = new Student();
        Student.setFirstname(Dto.firstname());
        Student.setLastname(Dto.lastname());
        Student.setEmail(Dto.email());
        var School = new School();
        School.setId(Dto.schoolId());
        Student.setSchool(School);
        return Student;
    }
}
