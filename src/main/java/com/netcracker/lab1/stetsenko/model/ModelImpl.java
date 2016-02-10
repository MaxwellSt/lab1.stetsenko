package com.netcracker.lab1.stetsenko.model;

import com.netcracker.lab1.stetsenko.*;

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

    public TaskList getTaskListFromFile(String pathFile) {
        FileReader in = null;
        try {
            in = new FileReader(pathFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.taskList = new LinkedTaskList();
        TaskIO.read(this.taskList, in);

        return getTaskList();
    }

    public boolean addTask(Task task) {
        return this.taskList.add(task);
    }

    public TaskList getTaskList(){
        return this.taskList;
    }

    public TaskList saveTaskListFromFile(String pathFile) {

        FileWriter out = null;
        try {
            out = new FileWriter(pathFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TaskIO.write(this.taskList, out);
        return getTaskList();
    }

    public Task getTask(int i) {
        return this.taskList.getTask(i);
    }

    public Iterable<Task> incoming(Date from, Date to){
        return Tasks.incoming(taskList, from, to);
    }
}
