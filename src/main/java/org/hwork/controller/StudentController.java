package org.hwork.controller;

import org.hwork.entity.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class StudentController {

    @GetMapping("/get/{student_id}")
    public List<Student> getStudentInfo(@PathVariable("student_id") UUID studentId){
        Student student = new Student();
        student.setId(studentId);
        student.setName("Test Student");
        return List.of(student);
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student){
        // В реальности здесь было бы сохранение в репозиторий: repository.save(student)
        if (student.getId() == null) {
            student.setId(UUID.randomUUID());
        }
        return student;
    }

    @PostMapping("/gpi")
    public boolean isStudentHaveGrant(@RequestBody Student student){
        Double avg_score = student.getAvg_score();
        if(avg_score == null){
            return false;
        }
        return avg_score >= 3.5;
    }
}
