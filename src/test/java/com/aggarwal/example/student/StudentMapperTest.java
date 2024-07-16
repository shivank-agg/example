package com.aggarwal.example.student;

import com.aggarwal.example.school.School;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapDtotoStudent(){
        StudentDto dto = new StudentDto(
                "John",
                "Doe",
                "john@gmail.com",
                1);
        Student student = mapper.toStudent(dto);

        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(),student.getSchool().getId());
    }
    @Test
    public void throw_null() {
        var exp = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals(exp.getMessage(), "School is null");
    }

    @Test
    public void shouldMapStudentToDto(){
        Student student = new Student("John", "Doe", "john@gmail.com", 20);

        StudentResponseDto response = mapper.toStudentDto(student);

        assertEquals(student.getFirstname(),response.firstname());
        assertEquals(student.getLastname(),response.lastname());
        assertEquals(student.getEmail(),response.email());
    }
}