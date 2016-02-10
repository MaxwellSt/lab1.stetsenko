package com.netcracker.lab1.stetsenko.view;

import com.netcracker.lab1.stetsenko.Task;
import com.netcracker.lab1.stetsenko.TaskList;
import com.netcracker.lab1.stetsenko.controller.Actions;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by ���� on 20.01.2016.
 */
public interface View {

    void showStartPage();

    Actions getAction();

    void showMessage( String message );

    String showPathFile();

    void showTaskListPage(TaskList taskList);

    void showAddTask();

    void showErrorAddTask();

    HashMap<String, String> editRepeatedTask(Task task);

    HashMap<String, String> editUnrepeatedTask(Task task);

    HashMap<String, String> addRepeatedTask();

    HashMap<String, String> addUnrepeatedTask();

    int showSelectTask();

    void showErrorEditTask();

    void showCalendar(Iterable<Task> calendar);

    HashMap<String, String> getDateInterval();
}
