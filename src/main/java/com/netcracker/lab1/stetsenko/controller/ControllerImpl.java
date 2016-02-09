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
        Actions action;
        boolean exit = false;
        view.showStartPage();

        while(!exit){

            action = view.getAction();

            switch (action) {
                case STARTPAGE1:
                    String pathFile = view.showPathFile();
                    if (validatePathFile(pathFile)) {
                        view.showTaskListPage(model.getTaskListFromFile(pathFile));
                    } else {
                        view.showMessage( "File not found" );
                        view.showStartPage();
                    }
                    break;
                case STARTPAGE2:
                case TASKLIST4:
                    exit = true;
                    break;
                case TASKLIST1:
                    System.out.println("** calendar **");
                    break;
                case TASKLIST2: //add new task
                    view.showAddTask();
                    break;
                case TASKLIST3: //edit task
                    view.showEditTask();
                    break;
                case ADDTASK1: //Add/edit repeated task
                    String repTask = view.editRepeatedTask();
                    break;
                case ADDTASK2: //Add/edit unrepeated task
                    String unrepTask = view.editUnrepeatedTask();
                    break;
                case ADDTASK3: //Back to Task list page
                    view.showTaskListPage(model.getTaskList());
                    break;
                case EDITTASK1:
                    int i = view.showSelectTask();
                    break;
                default:
                    view.showMessage( "You entered wrong action" );
                    break;
            }
        }
    }

    private boolean validatePathFile(String pathFile){
        File file = new File(pathFile);
        return file.exists();
    }
}
