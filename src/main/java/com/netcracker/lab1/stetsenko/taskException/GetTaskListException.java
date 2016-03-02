package com.netcracker.lab1.stetsenko.taskException;

/**
 * Created by maxim.stetsenko on 02.03.2016.
 */
public class GetTaskListException extends Exception {

    public GetTaskListException(Exception e) {
        super(e);
    }

    public GetTaskListException(String message) {
        super(message);
    }
}
