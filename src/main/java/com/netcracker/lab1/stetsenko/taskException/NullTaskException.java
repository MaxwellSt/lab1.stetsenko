package com.netcracker.lab1.stetsenko.taskException;

/**
 * Created by maxim.stetsenko on 11.02.2016.
 */
public class NullTaskException extends Exception {

    public NullTaskException(Exception e){
        super(e);
    }

    public NullTaskException(String message){

        super(message);

    }
}
