package be.howest.ti.util;

import java.io.FileNotFoundException;

public class TestProjectException extends RuntimeException {
    //THIS IS A CUSTOM EXCEPTION
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
