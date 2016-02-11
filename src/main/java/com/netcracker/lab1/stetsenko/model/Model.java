package com.netcracker.lab1.stetsenko.model;

import com.netcracker.lab1.stetsenko.Task;
import com.netcracker.lab1.stetsenko.TaskList;
import com.netcracker.lab1.stetsenko.taskException.NullTaskException;
import com.netcracker.lab1.stetsenko.taskException.NullTaskListException;

import java.io.IOException;
import java.util.Date;

/**
 * Created by ���� on 20.01.2016.
 */
public interface Model {

    TaskList getTaskListFromFile(String pathFile) throws IOException;

    boolean addTask(Task task) throws NullTaskException;

    TaskList getTaskList() throws NullTaskListException;

    TaskList saveTaskListFromFile(String pathFile) throws IOException;

    Task getTask(int i);

    Iterable<Task> incoming(Date from, Date to);

}
