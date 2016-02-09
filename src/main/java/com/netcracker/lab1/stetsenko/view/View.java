package com.netcracker.lab1.stetsenko.view;

import com.netcracker.lab1.stetsenko.TaskList;
import com.netcracker.lab1.stetsenko.controller.Actions;

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

    HashMap<String, String> editRepeatedTask();

    HashMap<String, String> editUnrepeatedTask();

    HashMap<String, String> addRepeatedTask();

    HashMap<String, String> addUnrepeatedTask();

    void showEditTask();

    int showSelectTask();
}
