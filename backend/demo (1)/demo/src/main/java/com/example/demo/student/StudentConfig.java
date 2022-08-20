package com.example.demo.student;

import static java.time.Month.MARCH;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student student1 = new Student("number 1", "1@1.compareTo", LocalDate.of(1999, MARCH, 3));
            Student student2 = new Student("number 2", "1@1.compareTo", LocalDate.of(1998, MARCH, 3));
            Student student3 = new Student("number 3", "1@1.compareTo", LocalDate.of(1997, MARCH, 3));

            repository.saveAll(List.of(student1, student2, student3));
        };

    }
}
