package com.assessment.SpringBoot.controller;

import com.assessment.SpringBoot.DTO.StudentRequest;
import com.assessment.SpringBoot.DTO.GlobalResponse;
import com.assessment.SpringBoot.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<GlobalResponse> createStudent(@RequestBody StudentRequest request){
        return  this.studentService.createStudent(request);
    }

    @GetMapping
    public ResponseEntity<List<StudentRequest>> getAllStudent(){
        return this.studentService.getAllStudent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentRequest> getStudentById(@PathVariable Long id){
        return this.studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse> updateStudentById(@PathVariable Long id, @RequestBody StudentRequest request){
        return this.studentService.updateStudentById(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse> deleteStudentById(@PathVariable Long id){
        return this.studentService.deleteById(id);
    }

    @GetMapping("/course/{course}")
    public ResponseEntity<List<StudentRequest>> getStudentByCourse(@PathVariable String course){
        return this.studentService.getStudentByCourse(course);
    }
}


