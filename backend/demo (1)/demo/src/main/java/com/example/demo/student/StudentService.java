package com.example.demo.student;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        validateEmail(student.getEmail());
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        validateAndGetStudentById(studentId);
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, HashMap<String, String> changes) {
        Student student = validateAndGetStudentById(studentId);
        if (changes.containsKey("email")) {
            String email = changes.get("email");
            updateEmail(student, email);
        }

        if (changes.containsKey("name")) {
            String name = changes.get("name");
            updateName(student, name);
        }
    }

    private void validateEmail(String email) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
    }

    private void updateEmail(Student student, String email) {
        validateEmail(email);
        if (email != student.getEmail()) {
            student.setEmail(email);
        }
    }

    private void updateName(Student student, String name) {
        if (name != student.getName()) {
            student.setName(name);
        }
    }

    private Student validateAndGetStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student ID not found"));
    }
}
