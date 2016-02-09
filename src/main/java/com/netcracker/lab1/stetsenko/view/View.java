package com.netcracker.lab1.stetsenko.view;

import com.netcracker.lab1.stetsenko.TaskList;
import com.netcracker.lab1.stetsenko.controller.Actions;

/**
 * Created by Макс on 20.01.2016.
 */
public interface View {

    void showStartPage();

    Actions getAction();

    void showMessage( String message );

    String showPathFile();

    void showTaskListPage(TaskList taskList);

    void showAddTask();

    String editRepeatedTask();

    String editUnrepeatedTask();

    void showEditTask();

    int showSelectTask();
}
