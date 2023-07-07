package com.sg.classroster.dao;

import com.sg.classroster.dto.Course;
import com.sg.classroster.dto.Student;
import com.sg.classroster.dto.Teacher;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class TeacherDaoDBTest {
    @Autowired
    TeacherDao teacherDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    CourseDao courseDao;

    public TeacherDaoDBTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Teacher> teachers = teacherDao.getAllTeachers();
        for(Teacher teacher : teachers) {
            teacherDao.deleteTeacherById(teacher.getId());
        }

        List<Student> students = studentDao.getAllStudents();
        for(Student student : students) {
            studentDao.deleteStudentById(student.getId());
        }

        List<Course> courses = courseDao.getAllCourses();
        for(Course course : courses) {
            courseDao.deleteCourseById(course.getId());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddAndGetTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpecialty("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);

        Teacher fromDao = teacherDao.getTeacherById(teacher.getId());

        assertEquals(teacher, fromDao);
    }

    @Test
    public void testGetAllTeachers() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpecialty("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);

        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Test First 2");
        teacher2.setLastName("Test Last 2");
        teacher2.setSpecialty("Test Specialty 2");
        teacher2 = teacherDao.addTeacher(teacher2);

        List<Teacher> teachers = teacherDao.getAllTeachers();

        assertEquals(2, teachers.size());
        assertTrue(teachers.contains(teacher));
        assertTrue(teachers.contains(teacher2));
    }

    @Test
    public void testUpdateTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpecialty("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);

        Teacher fromDao = teacherDao.getTeacherById(teacher.getId());
        assertEquals(teacher, fromDao);

        teacher.setFirstName("New Test First");
        teacherDao.updateTeacher(teacher);

        assertNotEquals(teacher, fromDao);

        fromDao = teacherDao.getTeacherById(teacher.getId());

        assertEquals(teacher, fromDao);
    }

    @Test
    public void testDeleteTeacherById() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpecialty("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);

        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);
        List<Student> students = new ArrayList<>();
        students.add(student);

        Course course = new Course();
        course.setName("Test Course");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);

        Teacher fromDao = teacherDao.getTeacherById(teacher.getId());
        assertEquals(teacher, fromDao);

        teacherDao.deleteTeacherById(teacher.getId());

        fromDao = teacherDao.getTeacherById(teacher.getId());
        assertNull(fromDao);
    }
}