package com.netcracker.lab1.stetsenko.view;

import com.netcracker.lab1.stetsenko.TaskList;

/**
 * Created by Макс on 20.01.2016.
 */
public interface View {

    public void showStartPage();

    public int getAction();

    public void showMessage( String message );

    public String showPathFile();

    public void showTaskListPage(TaskList taskList);

    public void showAddTask();

    public String editRepeatedTask();

    public String editUnrepeatedTask();

    public void showEditTask();

    public int showSelectTask();
}
