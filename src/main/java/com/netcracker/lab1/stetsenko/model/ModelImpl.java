package com.netcracker.lab1.stetsenko.model;

import com.netcracker.lab1.stetsenko.*;
import com.netcracker.lab1.stetsenko.taskException.NullTaskException;
import com.netcracker.lab1.stetsenko.taskException.NullTaskListException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by ���� on 20.01.2016.
 */
public class ModelImpl implements Model {

    private TaskList taskList;

    public TaskList getTaskListFromFile(String pathFile) throws IOException{

        try (FileReader in = new FileReader(pathFile)){
            this.taskList = new LinkedTaskList();
            TaskIO.read(this.taskList, in);
        } catch (IOException e) {
            throw e;
        }

        try {
            return getTaskList();
        } catch (NullTaskListException e) {
            return new LinkedTaskList();
        }
    }

    public boolean addTask(Task task) throws NullTaskException {

        try{
            return this.taskList.add(task);
        } catch (NullPointerException e) {
            throw new NullTaskException("Task is null!");
        }

    }

    public TaskList getTaskList() throws NullTaskListException {

        if(this.taskList == null){
            throw new NullTaskListException("Task list is empty!");
        }

        return this.taskList;
    }

    public TaskList saveTaskListFromFile(String pathFile) throws IOException{

        try (FileWriter out = new FileWriter(pathFile)){
            TaskIO.write(this.taskList, out);
        } catch (IOException e) {
            throw e;
        }

        try {
            return getTaskList();
        } catch (NullTaskListException e) {
            return new LinkedTaskList();
        }
    }

    public Task getTask(int i) {
        return this.taskList.getTask(i);
    }

    public Iterable<Task> incoming(Date from, Date to){
        return Tasks.incoming(taskList, from, to);
    }
}
