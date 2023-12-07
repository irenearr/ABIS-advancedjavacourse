package be.abis.exercise.exception;

public class PersonNotFoundException extends Exception {

    public PersonNotFoundException(String e) {
        System.out.println(e);
    }
}
