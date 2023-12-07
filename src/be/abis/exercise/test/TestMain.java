package be.abis.exercise.test;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.MemoryCourseRepository;
import be.abis.exercise.repository.MemoryPersonRepository;
import be.abis.exercise.repository.PersonRepository;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

public class TestMain {

    public static void main(String[] args) throws PersonAlreadyExistsException {
        PersonRepository pr = new MemoryPersonRepository();
        List<Person> persons = pr.findAllPersons();

        System.out.println("All persons:");
        persons.forEach(System.out::println);

        CourseRepository cr = new MemoryCourseRepository();
        List<Course> courses = cr.findAllCourses();

        System.out.println("\nAll courses:");
        courses.forEach(System.out::println);

        try {
            System.out.println("Person with id 2: " + pr.findPersonById(2));
        } catch (PersonNotFoundException e) {
        }
        try {
            System.out.println("Person with id 9: " + pr.findPersonById(9));
        } catch (PersonNotFoundException e) {
        }
        try {
            System.out.println("Person with id 300: " + pr.findPersonById(300));
        } catch (PersonNotFoundException e) {
        }

        try {
            System.out.println("Person found by email and password: " + pr.findPersonByEmailAndPassword("sschillebeeckx@abis.be", "somepass1"));
        } catch (PersonNotFoundException e) {
        }
        try {
            System.out.println("Find person by email " + pr.findPersonByEmailAndPassword("errorTest", "errorTest"));
        } catch (PersonNotFoundException e) {
        }

        try {
            System.out.println("Persons in company ABIS: " + pr.findPersonsForCompany("ABIS"));
        } catch (PersonNotFoundException e) {
        }
        try {
            pr.findPersonsForCompany("ABIS").forEach(p -> System.out.println(p));
        } catch (PersonNotFoundException e) {
        }
        try {
            System.out.println("Persons in company: " + pr.findPersonsForCompany("APG"));
        } catch (PersonNotFoundException e) {
        }

        try {
            Person irene = new Person("Irene", "van Dijk", LocalDate.of(1984, 10, 15), "irene84@gmail.com", "12345", "nl");
            pr.addPerson(irene);
        } catch (PersonAlreadyExistsException e) {
        }
        try {
            Person irene2 = new Person("Irene", "van Dijk", LocalDate.of(1984, 10, 15), "irene84@gmail.com", "12345", "nl");
            pr.addPerson(irene2);
        } catch (PersonAlreadyExistsException e) {
        }

        for(Person person : persons) {
            System.out.println(person.getFirstName() + " is " + person.calculateAgeYears() + " years or " + person.calculateAgeDays() + " days old.");
            Locale myLocale = new Locale(person.getLanguageCode());
            System.out.println(person.getFirstName() + " was born on a " + person.getBirthDate().getDayOfWeek().getDisplayName(TextStyle.FULL, myLocale));
        }

        LocalDate newDate = LocalDate.now().plusYears(3).plusMonths(2).plusDays(15);
        System.out.println("In 3 years, 2 months and 15 days the day is: " + newDate.getDayOfWeek());

        LocalDate nextBirthday = LocalDate.of(2024, 10, 15);
        System.out.println("My next birthday will be in " + ChronoUnit.DAYS.between(LocalDate.now(),nextBirthday) + " days.");
    }
}
