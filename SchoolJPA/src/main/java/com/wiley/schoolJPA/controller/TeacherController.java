package com.wiley.schoolJPA.controller;

import com.wiley.schoolJPA.dao.TeacherRepo;
import com.wiley.schoolJPA.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {
    @Autowired
    private TeacherRepo teacherRepo;

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> allTeachers() {
        List<Teacher> teachers = teacherRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeachersById(@PathVariable("id") Integer id) {
        Teacher teacher = teacherRepo.findById(id).orElse(null);
        if (teacher == null) {
            return new ResponseEntity<Teacher>(teacher, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addNewTeacher(@RequestBody Teacher teacher) {
        teacherRepo.save(teacher);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable("id") Integer id) {
        teacherRepo.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable("id") Integer id, @RequestBody Teacher teacher) {
        teacherRepo.save(teacher);
        return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
    }
}
