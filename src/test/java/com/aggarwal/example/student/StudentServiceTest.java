package com.aggarwal.example.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentRepository repository;
    @Mock
    private StudentMapper studentMapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_saveStudent(){
        StudentDto dto = new StudentDto(
                "John",
                "Doe",
                "john@gmail.com",
                1);
        Student student = new Student(
                "John",
                "Doe",
                "john@gmail.com",
                1
        );
        Student savedStudent = new Student(
                "John",
                "Doe",
                "john@gmail.com",
                1
        );
        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(repository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toStudentDto(savedStudent)).thenReturn(new StudentResponseDto("John","Doe","john@gmail.com"));

        StudentResponseDto responseDto = studentService.saveStudent(dto);
        assertEquals(dto.firstname(),responseDto.firstname());
        assertEquals(dto.lastname(),responseDto.lastname());
        assertEquals(dto.email(),responseDto.email());

        Mockito.verify(studentMapper,times(1)).toStudent(dto);
        Mockito.verify(repository,times(1)).save(student);
        Mockito.verify(studentMapper,times(1)).toStudentDto(savedStudent);
    }
    @Test
    public void should_retuen_all_students(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "John",
                "Doe",
                "john@gmail.com",
                1
        ));

        when(repository.findAll()).thenReturn(students);
        when(studentMapper.toStudentDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Doe",
                        "john@gmail.com"));

        List<StudentResponseDto> responseDtos = studentService.findAllStudents();
        assertEquals(students.size(),responseDtos.size());
        assertEquals(students.get(0).getFirstname(),responseDtos.get(0).firstname());
        assertEquals(students.get(0).getLastname(),responseDtos.get(0).lastname());
        assertEquals(students.get(0).getEmail(),responseDtos.get(0).email());
        Mockito.verify(repository,times(1)).findAll();
    }
    @Test
    public void find_by_id_test(){
        Student student = new Student("John", "Doe", "john@gmail.com", 1);


        when(repository.findById(any(Integer.class))).thenReturn(Optional.of(student));
        when(studentMapper.toStudentDto(any(Student.class))).thenReturn(new StudentResponseDto("John","Doe","john@gmail.com"));

        StudentResponseDto dto = studentService.findstdbyId(1);
        assertEquals(dto.firstname(),student.getFirstname());
        assertEquals(dto.lastname(),student.getLastname());
        assertEquals(dto.email(),student.getEmail());
        Mockito.verify(repository,times(1)).findById(any(Integer.class));
    }

    @Test
    public void find_by_name(){
        String name = "John";
        List<Student> students = new ArrayList<>();
        Student student = new Student("John", "Doe", "john@gmail.com", 1);
        students.add(student);

        when(repository.findAllByFirstnameContaining(any(String.class))).thenReturn(students);
        when(studentMapper.toStudentDto(any(Student.class))).thenReturn(new StudentResponseDto("John","Doe","john@gmail.com"));

        List<StudentResponseDto> dto = studentService.findbyName(name);

        assertEquals(students.size(),dto.size());
        assertEquals(students.get(0).getFirstname(),dto.get(0).firstname());
        assertEquals(students.get(0).getLastname(),dto.get(0).lastname());
        assertEquals(students.get(0).getEmail(),dto.get(0).email());
        Mockito.verify(repository,times(1)).findAllByFirstnameContaining(any(String.class));
    }
}