package com.netcracker.lab1.stetsenko.taskException;

/**
 * Created by maxim.stetsenko on 02.03.2016.
 */
public class RemoveTaskException extends Exception {

    public RemoveTaskException(Exception e) {
        super(e);
    }

    public RemoveTaskException(String message) {
        super(message);
    }
}
