package com.netcracker.lab1.stetsenko.view;

/**
 * Created by Макс on 20.01.2016.
 */
public interface View {

    public void showStartPage();

    public int getAction();

    public void showMessage( String message );

    public String showPathFile();

    public void showTaskListPage(String taskList);
}
