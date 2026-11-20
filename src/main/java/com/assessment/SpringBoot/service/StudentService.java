package com.assessment.SpringBoot.service;

import com.assessment.SpringBoot.DTO.StudentRequest;
import com.assessment.SpringBoot.DTO.GlobalResponse;
import com.assessment.SpringBoot.model.Student;
import com.assessment.SpringBoot.repository.StudentRepository;
import com.assessment.SpringBoot.util.GlobalMessage;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final GlobalMessage globalMessage;

    public StudentService(StudentRepository studentRepository,
                          GlobalMessage globalMessage) {
        this.studentRepository = studentRepository;
        this.globalMessage = globalMessage;
    }

    @Transactional
    public ResponseEntity<GlobalResponse> createStudent(StudentRequest request){
        Student student = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .age(request.getAge())
                .course(request.getCourse())
                .build();

        this.studentRepository.save(student);
        return this.globalMessage.GlobalMessageHandler(true, "Student has been created!", HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<List<StudentRequest>> getAllStudent(){
        List<Student> students = this.studentRepository.findAll();

        List<StudentRequest> studentResponses = students.stream()
                .map(student -> StudentRequest.builder()
                        .name(student.getName())
                        .email(student.getEmail())
                        .age(student.getAge())
                        .course(student.getCourse())
                        .build())
                .toList();

        return ResponseEntity.ok(studentResponses);
    }

    @Transactional
    public ResponseEntity<StudentRequest> getStudentById(Long id){
        boolean exist = this.studentRepository.existsById(id);

        if (!exist){
            throw new RuntimeException("Student not Found!");
        }
        Student student = this.studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found!"));
        StudentRequest studentRequest = StudentRequest.builder()
                .name(student.getName())
                .email(student.getEmail())
                .age(student.getAge())
                .course(student.getCourse())
                .build();

        return ResponseEntity.ok(studentRequest);
    }

    @Transactional
    public ResponseEntity<GlobalResponse> updateStudentById(Long id, StudentRequest request){
        boolean exist = this.studentRepository.existsById(id);

        if (!exist){
            return this.globalMessage.GlobalMessageHandler(false, "Student Not Found!", HttpStatus.BAD_REQUEST);
        }

        Student student = this.studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found!"));

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setAge(request.getAge());
        student.setCourse(request.getCourse());

        this.studentRepository.save(student);
        return this.globalMessage.GlobalMessageHandler(true, "Student has been updated!", HttpStatus.ACCEPTED);
    }

    @Transactional
    public ResponseEntity<GlobalResponse> deleteById(Long id){
        boolean exist = this.studentRepository.existsById(id);

        if (!exist){
            return this.globalMessage.GlobalMessageHandler(false, "Student Not Found!", HttpStatus.BAD_REQUEST);
        }

        this.studentRepository.deleteById(id);
        return this.globalMessage.GlobalMessageHandler(true, "Student has been deleted!", HttpStatus.ACCEPTED);
    }

    @Transactional
    public ResponseEntity<List<StudentRequest>> getStudentByCourse(String course){
        List<Student> students = this.studentRepository.findByCourse(course);

        List<StudentRequest> studentResponses = students.stream()
                .map(student -> StudentRequest.builder()
                        .name(student.getName())
                        .email(student.getEmail())
                        .age(student.getAge())
                        .course(student.getCourse())
                        .build())
                .toList();

        return ResponseEntity.ok(studentResponses);
    }
}
