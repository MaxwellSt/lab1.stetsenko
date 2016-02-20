package com.netcracker.lab1.stetsenko.taskException;

/**
 * Created by maxim.stetsenko on 20.02.2016.
 */
public class TaskNotFoundException extends Exception {

    public TaskNotFoundException(Exception e){
        super(e);
    }
    public TaskNotFoundException(String message){

        super(message);

    }
}
