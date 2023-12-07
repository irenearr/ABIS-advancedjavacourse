package be.abis.exercise.test;

import be.abis.exercise.model.Course;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.MemoryCourseRepository;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;


public class TestCollectionLambda {
    public static void main(String[] args) {
        CourseRepository cr = new MemoryCourseRepository();
        List<Course> allCourses = cr.findAllCourses();

        allCourses.sort(Comparator.comparing(Course::getTitle));
        System.out.println("All Courses sorted by title: " + allCourses);

        // use reverseOrder() if you want to sort from high to low price
        allCourses.sort(Comparator.comparingInt(Course::getDays).thenComparing(Course::getDailyPrice, Comparator.reverseOrder()));
        System.out.println("All Courses sorted by duration and price: " + allCourses);

//        allCourses.removeIf(c -> c.getDays()<3);
        System.out.println("List of courses when the courses that take less than 3 days are removed " + allCourses);

        allCourses.forEach(c -> {
            if (c.getTitle().equalsIgnoreCase("java programming")) {
                c.setDailyPrice(1.1*c.getDailyPrice());
            }});
        Course javaProgramming = allCourses.stream()
                        .filter(c -> c.getTitle().equalsIgnoreCase("java programming")).findFirst().get();
        System.out.println("The price of the java programming course is now: " + javaProgramming.getDailyPrice());

        System.out.println("All courses formatted: ");
        for (Course c:allCourses) {
            System.out.println(cr.formatCourse(c));
        }

        System.out.println("Course Title        Total Price         Release Date");
        for (Course c:allCourses) {
            System.out.println(c.getTitle() + "     " + c.getDailyPrice() + "   " +DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(c.getReleaseDate()));
        }

        double VAT = 1.21;
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("%35s%-10s%35s\n", " ", "Courses", " ");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("%-30s %-30s %-20s\n", "Course Title", "Total Price with VAT","Release Date");
        System.out.println("-------------------------------------------------------------------------------");
        NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(new Locale("nl","NL"));
        for (Course c:allCourses) {
            double totalPrice = c.getDailyPrice() * c.getDays() * VAT;
            String numberFormat = numberFormatter.format(totalPrice);
            String dateTimeFormatted = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(c.getReleaseDate());
            System.out.printf("%-30s%-30s%-20s\n", c.getTitle(), numberFormat, dateTimeFormatted);
        }

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("nl", "BE"));
        System.out.println(nf.format(34.75));
    }
}