package com.netcracker.lab1.stetsenko;
/**
 * Created by Max on 14.11.2015.
 */
public abstract class TaskList implements Iterable, java.io.Serializable{

    public abstract boolean add(Task task) throws NullPointerException;
    public abstract int getSize();
    public abstract void printTask(int i);
    public abstract boolean remove(Task task);
    public abstract Task getTask(int i);

}
