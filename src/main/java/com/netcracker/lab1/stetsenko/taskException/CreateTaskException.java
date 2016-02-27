package com.netcracker.lab1.stetsenko.taskException;

/**
 * Created by Макс on 27.02.2016.
 */
public class CreateTaskException extends Exception {

    public CreateTaskException(String message) {
        super(message);
    }

    public CreateTaskException(Exception e) {
        super(e);
    }
}
