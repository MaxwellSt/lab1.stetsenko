package com.netcracker.lab1.stetsenko.taskException;

/**
 * Created by Макс on 27.02.2016.
 */
public class EditTaskException extends Exception {

    public EditTaskException(String message) {
        super(message);
    }

    public EditTaskException(Exception e) {
        super(e);
    }
}
