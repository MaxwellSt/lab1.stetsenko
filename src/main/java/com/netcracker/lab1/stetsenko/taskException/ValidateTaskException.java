package com.netcracker.lab1.stetsenko.taskException;

/**
 * Created by maxim.stetsenko on 02.03.2016.
 */
public class ValidateTaskException extends Exception {

    public ValidateTaskException(Exception e){
        super(e);
    }
    public ValidateTaskException(String message){

        super(message);

    }
}
