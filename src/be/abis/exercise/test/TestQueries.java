package be.abis.exercise.test;

import be.abis.exercise.model.Person;
import be.abis.exercise.repository.MemoryPersonRepository;
import be.abis.exercise.repository.PersonRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestQueries {
    public static void main(String[] args) {
        PersonRepository pr = new MemoryPersonRepository();

            pr.findAllPersons().stream()
                    .filter(p -> p.getLastName().startsWith("S"))
                    .sorted((p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()))
                    .forEach(p -> System.out.println(p));

            pr.findAllPersons().stream()
                    .filter(p -> p.getCompany() != null)
                    .map(p -> p.getCompany().getName())
                    .distinct().forEach(s -> System.out.println(s));

        long count =
            pr.findAllPersons().stream()
                    .filter(p -> p.getCompany() != null)
                    .filter(p -> p.getCompany().getAddress().getTown().equals("Leuven")).count();
        System.out.println("The number of people that work in Leuven is: " + count);

Person youngestPerson = pr.findAllPersons().stream()
        .max(Comparator.comparing(Person::getBirthDate)).get();
        System.out.println(youngestPerson + " birthdate: " + youngestPerson.getBirthDate());

        Map<String, List<Person>> personsPerCompany = pr.findAllPersons().stream()
                .filter(p -> p.getCompany() != null)
                .collect(Collectors.groupingBy(p -> p.getCompany().getName()));
        System.out.println(personsPerCompany);

        personsPerCompany.forEach((s,p) -> System.out.println(s + " has " + p.stream().count() + " persons working here."));

        List<Long> listOfNumberOfPersons = new ArrayList<>();
        Long sum = Long.valueOf(0);
        Long average = Long.valueOf(0);
        for(String s : personsPerCompany.keySet()) {
            long numberOfPersons = personsPerCompany.get(s).stream().count();
            listOfNumberOfPersons.add(numberOfPersons);
            sum = sum + numberOfPersons;
        }
        average = sum / listOfNumberOfPersons.size();
        System.out.println("The average number of people per company is " + average + ".");

        Person youngest = pr.findAllPersons().stream()
                .min(Comparator.comparing(Person::calculateAgeDays)).get();
        System.out.println(youngest.getFirstName() + "is the youngest person in the group, with age: " + youngestPerson.calculateAgeYears());
    }
}
