package com.netcracker.lab1.stetsenko.controller;

import com.netcracker.lab1.stetsenko.Task;
import com.netcracker.lab1.stetsenko.model.Model;
import com.netcracker.lab1.stetsenko.model.ModelImpl;
import com.netcracker.lab1.stetsenko.view.View;
import com.netcracker.lab1.stetsenko.view.ViewImpl;

import java.io.File;
import java.util.HashMap;

/**
 * Created by ���� on 20.01.2016.
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
                    break;
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
                case ADDTASK1: //Add repeated task
                    HashMap<String, String> mapTask = view.addRepeatedTask();
                    if(validateMapTask(mapTask)){
                        model.addTask(createTask(mapTask));
                    }
                    break;
                case ADDTASK2: //Add unrepeated task
                    HashMap<String, String> mapTask1 = view.addUnrepeatedTask();
                    if(validateMapTask(mapTask1)){
                       model.addTask(createTask(mapTask1));
                    }
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

    private boolean validateMapTask(HashMap<String, String> mapTask){

        Boolean result = false;
        return result;
    }

    private Task createTask(HashMap<String, String> mapTask){
        return null;
    }
}
