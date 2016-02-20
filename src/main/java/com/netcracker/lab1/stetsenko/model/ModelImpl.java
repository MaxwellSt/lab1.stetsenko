package com.netcracker.lab1.stetsenko.model;

import com.netcracker.lab1.stetsenko.*;
import com.netcracker.lab1.stetsenko.taskException.NullTaskException;
import com.netcracker.lab1.stetsenko.taskException.NullTaskListException;
import com.netcracker.lab1.stetsenko.taskException.TaskNotFoundException;

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
    private String pathFile = "test3.txt";

    public void readTaskListFromFile() throws IOException{

        try (FileReader in = new FileReader(pathFile)){
            this.taskList = new LinkedTaskList();
            TaskIO.read(this.taskList, in);
        } catch (IOException e) {
            throw e;
        }

    }

    public boolean addTask(Task task) throws NullTaskException {

        try{
            return this.taskList.add(task);
        } catch (IllegalArgumentException e) {
            throw new NullTaskException("Task is null!");
        }

    }

    public TaskList getTaskList() throws NullTaskListException {

        if(this.taskList == null){
            try {
                readTaskListFromFile();
            } catch (IOException e) {
                throw new NullTaskListException(e);
            }
        }

        return this.taskList;
    }

    public void saveTaskList() throws IOException{

        try (FileWriter out = new FileWriter(pathFile)){
            TaskIO.write(this.taskList, out);
        } catch (IOException e) {
            throw e;
        }
    }

    public Task getTask(int i) throws TaskNotFoundException {
        try {
            return this.taskList.getTask(i);
        }catch (IndexOutOfBoundsException e){
            throw new TaskNotFoundException(e);
        }
    }

    public Iterable<Task> incoming(Date from, Date to){
        return Tasks.incoming(taskList, from, to);
    }

    public Task createUnrepeatedTask(String title, Date time, boolean active) {
        Task task = new Task(title, time);
        task.setActive(active);
        return task;
    }

    public Task createRepeatedTask(String title, Date startTime, Date endTime, int interval, boolean active) {
        Task task = new Task(title, startTime, endTime, interval);
        task.setActive(active);
        return task;
    }

    public void removeTask(Task task) {
        taskList.remove(task);
    }
}
