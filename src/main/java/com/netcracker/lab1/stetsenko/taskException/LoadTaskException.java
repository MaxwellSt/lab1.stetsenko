package com.netcracker.lab1.stetsenko.taskException;

/**
 * Created by maxim.stetsenko on 02.03.2016.
 */
public class LoadTaskException extends Exception {

    public LoadTaskException(Exception e){
        super(e);
    }
    public LoadTaskException(String message){

        super(message);

    }

}
