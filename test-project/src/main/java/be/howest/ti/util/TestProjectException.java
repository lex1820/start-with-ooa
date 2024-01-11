package be.howest.ti.util;

import java.io.FileNotFoundException;

public class TestProjectException extends RuntimeException {
    //This is a custom exception class that can be used to throw customized exceptions in the program
    public TestProjectException(FileNotFoundException e) {
        super();
    }
    public TestProjectException(String message) {
        super(message);
    }
    public TestProjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
