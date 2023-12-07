package be.abis.exercise.test;

import be.abis.exercise.model.Course;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.FileCourseRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class TestCourses {
    public static void main(String[] args) throws IOException {
        CourseRepository cr = new FileCourseRepository();
        List<Course> allCourses = cr.findAllCourses();

        System.out.println(allCourses);

        Course testCourse = new Course("test",5,350.50, LocalDate.of(2024, 01,01));
        cr.addCourse(testCourse);
    }
}
