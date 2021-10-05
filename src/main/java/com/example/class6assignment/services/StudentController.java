package com.example.class6assignment.services;

import com.example.class6assignment.usecases.StudentsCsvUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
    private final StudentsCsvUseCase studentsCsvUseCase;


    @Autowired
    public StudentController(StudentsCsvUseCase studentsCsvUseCase) {
        this.studentsCsvUseCase = studentsCsvUseCase;

    }


    @GetMapping(path = "/students")
    public ResponseEntity getStudentsAsCsv() {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=students.csv")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(studentsCsvUseCase.getStudentsAsCsv());
    }

    @GetMapping(path = "/courses")
    public ResponseEntity getCoursesAsCsv() {

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=courses.csv")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(studentsCsvUseCase.getCoursesAsCsv());
    }

}
