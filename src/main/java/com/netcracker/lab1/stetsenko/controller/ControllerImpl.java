package com.netcracker.lab1.stetsenko.controller;

import com.netcracker.lab1.stetsenko.model.Model;
import com.netcracker.lab1.stetsenko.model.ModelImpl;
import com.netcracker.lab1.stetsenko.view.View;
import com.netcracker.lab1.stetsenko.view.ViewImpl;

import java.io.File;

/**
 * Created by Макс on 20.01.2016.
 */
public class ControllerImpl {

    private Model model;
    private View view;

    public static void main( String[] args ) {
        new ControllerImpl( new ModelImpl(), new ViewImpl() ).start();
    }

    public ControllerImpl( Model model, View view ) {
        this.model = model;
        this.view = view;
    }

    public void start() {
        int action;
        boolean exit = false;
        view.showStartPage();

        while(!exit){

            action = view.getAction();

            switch (action) {
                case 11:
                    String pathFile = view.showPathFile();
                    if (validatePathFile(pathFile)) {
                        view.showTaskListPage(model.getTaskListFromFile(pathFile));
                    } else {
                        view.showMessage( "File not found" );
                        view.showStartPage();
                    }
                    break;
                case 12:
                case 24:
                    exit = true;
                    break;
                case 22: //add new task
                    view.showAddTask();
                case 23: //edit task
                    view.showEditTask();
                case 31: //Add/edit repeated task
                    String repTask = view.editRepeatedTask();
                case 32: //Add/edit unrepeated task
                    String unrepTask = view.editUnrepeatedTask();
                case 33: //Back to Task list page
                case 42:
                    view.showTaskListPage(model.getTaskList());
                case 41:
                    int i = view.showSelectTask();
                default:
                    view.showMessage( "You entered wrong action" );
            }
        }
    }

    private boolean validatePathFile(String pathFile){
        File file = new File(pathFile);
        return file.exists();
    }
}
