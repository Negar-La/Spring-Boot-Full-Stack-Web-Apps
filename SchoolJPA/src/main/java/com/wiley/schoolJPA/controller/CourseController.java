package com.wiley.schoolJPA.controller;

import com.wiley.schoolJPA.dao.CourseRepo;
import com.wiley.schoolJPA.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseRepo courseRepo;


    @GetMapping("/courses")
    public ResponseEntity<List<Course>> allCourses() {
        List<Course> courses = courseRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") Integer id) {
        Course course = courseRepo.findById(id).orElse(null);
        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addNewCourse(@RequestBody Course course) {
        courseRepo.save(course);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") Integer id, @RequestBody Course course) {
        courseRepo.save(course);
        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") Integer id) {
        courseRepo.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
