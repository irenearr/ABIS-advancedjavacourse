package be.abis.exercise.exception;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PersonNotFoundException extends Exception {
    static Logger log = LogManager.getLogger("exceptionLogger");

    public PersonNotFoundException(String e) {
        log.error(e);
        System.out.println(e);
    }
}
