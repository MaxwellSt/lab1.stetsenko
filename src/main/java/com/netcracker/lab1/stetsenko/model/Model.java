package com.netcracker.lab1.stetsenko.model;

import com.netcracker.lab1.stetsenko.Task;
import com.netcracker.lab1.stetsenko.TaskList;
import com.netcracker.lab1.stetsenko.taskException.NullTaskException;
import com.netcracker.lab1.stetsenko.taskException.NullTaskListException;
import com.netcracker.lab1.stetsenko.taskException.TaskNotFoundException;

import java.io.IOException;
import java.util.Date;

/**
 * Created by ���� on 20.01.2016.
 */
public interface Model {

    boolean addTask(Task task) throws NullTaskException;

    TaskList getTaskList() throws NullTaskListException;

    void saveTaskList() throws IOException;

    Task getTask(int i) throws TaskNotFoundException;

    Iterable<Task> incoming(Date from, Date to);

    Task createUnrepeatedTask(String title, Date time, boolean active);

    Task createRepeatedTask(String title, Date startTime, Date endTime, int interval, boolean active);

    void removeTask(Task task);
}
