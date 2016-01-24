package com.netcracker.lab1.stetsenko.model;

import com.netcracker.lab1.stetsenko.LinkedTaskList;
import com.netcracker.lab1.stetsenko.Task;
import com.netcracker.lab1.stetsenko.TaskIO;
import com.netcracker.lab1.stetsenko.TaskList;

import java.io.FileReader;
import java.io.IOException;

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

    public boolean addTask() {
        return false;
    }

    public TaskList getTaskList(){
        return this.taskList;
    }
}
