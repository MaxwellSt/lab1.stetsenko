package com.netcracker.lab1.stetsenko.model;

import com.netcracker.lab1.stetsenko.Task;
import com.netcracker.lab1.stetsenko.TaskList;

/**
 * Created by ���� on 20.01.2016.
 */
public interface Model {

    TaskList getTaskListFromFile(String pathFile);

    boolean addTask(Task task);

    TaskList getTaskList();

}
