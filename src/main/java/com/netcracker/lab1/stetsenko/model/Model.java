package com.netcracker.lab1.stetsenko.model;

import com.netcracker.lab1.stetsenko.TaskList;

/**
 * Created by ���� on 20.01.2016.
 */
public interface Model {

    public TaskList getTaskListFromFile(String pathFile);

    public boolean addTask();

    public TaskList getTaskList();

}
