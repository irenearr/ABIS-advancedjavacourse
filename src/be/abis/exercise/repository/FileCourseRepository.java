package be.abis.exercise.repository;

import be.abis.exercise.model.Course;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class FileCourseRepository implements CourseRepository {
    private List<Course> courses;
    String fileLocation = "/temp/javacourses/courses.csv";
    Path path = Path.of(fileLocation);

    public FileCourseRepository() throws IOException {
        courses = Files.lines(path).map(s -> this.parseCourse(s)).collect(Collectors.toList());
    }

    @Override
    public List<Course> findAllCourses() {
        return courses;
    }

    @Override
    public void addCourse(Course c) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        writer.newLine();
        writer.write(formatCourse(c));
        writer.close();
    }

    @Override
    public String formatCourse(Course c) {
        StringBuilder sb = new StringBuilder();
        String formattedReleaseDate = DateTimeFormatter.ofPattern("d/M/yyyy").format(c.getReleaseDate());
        sb.append(c.getTitle()).append(";").append(c.getDays()).append(";").append(c.getDailyPrice()).append(";").append(formattedReleaseDate);
        return sb.toString();
    }

    public Course parseCourse(String s) {
        String[] values = s.split(";");
        String name = values[0];
        int days = Integer.parseInt(values[1]);
        double dailyPrice = Double.parseDouble(values[2]);
        String[] dayMonthYear = values[3].split("/");
        int day = Integer.parseInt(dayMonthYear[0]);
        int month = Integer.parseInt(dayMonthYear[1]);
        int year = Integer.parseInt(dayMonthYear[2]);
        LocalDate releaseDate = LocalDate.of(year, month,day);

        return new Course(name,days,dailyPrice,releaseDate);
    }
}
