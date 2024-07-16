package com.aggarwal.example.student;

import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
        @NotEmpty(message = "FirstName should not be empty")
        String firstname,
        @NotEmpty(message = "LastName should not be empty")
        String lastname,
        String email,
        Integer schoolId
) {
}
