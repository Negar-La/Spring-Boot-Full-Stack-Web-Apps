package com.sg.classroster.controller;

import com.sg.classroster.dao.CourseDao;
import com.sg.classroster.dao.StudentDao;
import com.sg.classroster.dao.TeacherDao;
import com.sg.classroster.dto.Course;
import com.sg.classroster.dto.Student;
import com.sg.classroster.dto.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {
    @Autowired
    TeacherDao teacherDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    CourseDao courseDao;

    @GetMapping("courses")
    public String displayCourses(Model model) {
        List<Course> courses = courseDao.getAllCourses();
        List<Teacher> teachers = teacherDao.getAllTeachers();
        List<Student> students = studentDao.getAllStudents();
        model.addAttribute("courses", courses);
        model.addAttribute("teachers", teachers);
        model.addAttribute("students", students);
        return "courses";
    }

    @PostMapping("addCourse")
    public String addCourse(Course course, HttpServletRequest request) {
        String teacherId = request.getParameter("teacherId");
        String[] studentIds = request.getParameterValues("studentId");

        course.setTeacher(teacherDao.getTeacherById(Integer.parseInt(teacherId)));

        List<Student> students = new ArrayList<>();
        for(String studentId : studentIds) {
            students.add(studentDao.getStudentById(Integer.parseInt(studentId)));
        }
        course.setStudents(students);
        courseDao.addCourse(course);

        return "redirect:/courses";
    }

    @GetMapping("courseDetail")
    public String courseDetail(Integer id, Model model) {
        Course course = courseDao.getCourseById(id);
        model.addAttribute("course", course);
        return "courseDetail";
    }

    @GetMapping("deleteCourse")
    public String deleteCourse(Integer id) {
        courseDao.deleteCourseById(id);
        return "redirect:/courses";
    }

    @GetMapping("editCourse")
    public String editCourse(Integer id, Model model) {
        Course course = courseDao.getCourseById(id);
        List<Student> students = studentDao.getAllStudents();
        List<Teacher> teachers = teacherDao.getAllTeachers();
        model.addAttribute("course", course);
        model.addAttribute("students", students);
        model.addAttribute("teachers", teachers);
        return "editCourse";
    }

    @PostMapping("editCourse")
    public String performEditCourse(@Valid Course course, BindingResult result, HttpServletRequest request, Model model) {
        String teacherId = request.getParameter("teacherId");
        String[] studentIds = request.getParameterValues("studentId");

        course.setTeacher(teacherDao.getTeacherById(Integer.parseInt(teacherId)));

        List<Student> students = new ArrayList<>();
        if(studentIds != null) {
            for(String studentId : studentIds) {
                students.add(studentDao.getStudentById(Integer.parseInt(studentId)));
            }
        } else {
            FieldError error = new FieldError("course", "students", "Must include one student");
            result.addError(error);
        }

        course.setStudents(students);

        if(result.hasErrors()) {
            model.addAttribute("teachers", teacherDao.getAllTeachers());
            model.addAttribute("students", studentDao.getAllStudents());
            model.addAttribute("course", course);
            return "editCourse";
        }

        courseDao.updateCourse(course);

        return "redirect:/courses";
    }

}
