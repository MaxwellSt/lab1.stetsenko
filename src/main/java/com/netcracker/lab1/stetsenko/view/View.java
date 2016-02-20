package com.netcracker.lab1.stetsenko.view;

import com.netcracker.lab1.stetsenko.Task;
import com.netcracker.lab1.stetsenko.TaskList;
import com.netcracker.lab1.stetsenko.controller.Actions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ���� on 20.01.2016.
 */
public interface View {

    Actions.EnumActions getAction();

    void showMessage( String message );

    void showTaskListPage(TaskList taskList);

    void showAddTask();

    void showErrorAddTask();

    void showErrorEditTask();

    Map<String, String> editRepeatedTask(Task task);

    Map<String, String> editUnrepeatedTask(Task task);

    Map<String, String> addRepeatedTask();

    Map<String, String> addUnrepeatedTask();

    int showSelectTask();

    void showCalendar(Iterable<Task> calendar);

    Map<String, String> getDateInterval();
}
