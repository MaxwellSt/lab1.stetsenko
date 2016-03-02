package com.netcracker.lab1.stetsenko.taskException;

import java.io.IOException;

/**
 * Created by Макс on 27.02.2016.
 */
public class SaveTasksException extends Exception {

    public SaveTasksException(Exception e) {
        super(e);
    }

    public SaveTasksException(String message) {
        super(message);
    }
}
