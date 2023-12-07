package be.abis.exercise.repository;

import be.abis.exercise.model.Course;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MemoryCourseRepository implements CourseRepository{

    private List<Course> courses = new ArrayList<>();

    public MemoryCourseRepository(){
        courses.add(new Course("DB2, an overview",5,550.0, LocalDate.of(1986,4,30)));
        courses.add(new Course("Workshop SQL",2,475.0, LocalDate.of(1990,1,9)));
        courses.add(new Course("Java Programming",5,500.0, LocalDate.of(1997,5,27)));
        courses.add(new Course("Maven",1,450.0, LocalDate.of(2007,6,11)));
        courses.add(new Course("Programming with Spring",3,525.0, LocalDate.of(2008,3,21)));

    }

    @Override
    public List<Course> findAllCourses() {
        return courses;
    }

    @Override
    public void addCourse(Course c) {
        courses.add(c);
    }

    @Override
    public String formatCourse(Course c) {
        StringBuilder sb = new StringBuilder();
        String formattedReleaseDate = DateTimeFormatter.ofPattern("d/M/yyyy").format(c.getReleaseDate());
        sb.append(c.getTitle()).append(";").append(c.getDays()).append(";").append(c.getDailyPrice()).append(";").append(formattedReleaseDate);
        return sb.toString();
    }
}
