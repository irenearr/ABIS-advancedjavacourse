package be.abis.exercise.exception;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class PersonAlreadyExistsException extends Exception {
    static Logger exceptionLogger = LogManager.getLogger("exceptionLogger");
    public PersonAlreadyExistsException(String e) {
        exceptionLogger.error(e);
        System.out.println(e);
    }
}
