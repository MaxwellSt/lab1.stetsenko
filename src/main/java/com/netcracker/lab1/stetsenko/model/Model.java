package com.netcracker.lab1.stetsenko.model;

import com.netcracker.lab1.stetsenko.Task;
import com.netcracker.lab1.stetsenko.TaskList;
import com.netcracker.lab1.stetsenko.taskException.*;

import java.util.Date;
import java.util.Map;

public interface Model {

    void loadTaskList() throws LoadTaskException;

    TaskList getTaskList() throws GetTaskListException;

    void saveTasks() throws SaveTasksException;

    Task getTask(int i) throws TaskNotFoundException;

    Iterable<Task> incoming(Date from, Date to);

    Task createTask(Map<String, String> mapTask) throws CreateTaskException;

    Task editTask(Map<String, String> mapTask, Task currentTask) throws EditTaskException;

    boolean removeTask(int i) throws RemoveTaskException;

}
