package com.netcracker.lab1.stetsenko.taskException;

import java.io.IOException;

/**
 * Created by Макс on 27.02.2016.
 */
public class IOTasksException extends IOException {

    public IOTasksException(Exception e) {
        super(e);
    }

    public IOTasksException(String message) {
        super(message);
    }
}
