package com.netcracker.lab1.stetsenko.controller;

import com.netcracker.lab1.stetsenko.Task;
import com.netcracker.lab1.stetsenko.model.Model;
import com.netcracker.lab1.stetsenko.model.ModelImpl;
import com.netcracker.lab1.stetsenko.view.View;
import com.netcracker.lab1.stetsenko.view.ViewImpl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                case TASKLIST5:
                    exit = true;
                    break;
                case TASKLIST1:
                    System.out.println("** calendar **");
                    break;
                case TASKLIST2:
                case ERRORADDTASK1://add new task
                    view.showAddTask();
                    break;
                case TASKLIST3: //edit task
                    view.showEditTask();
                    break;
                case TASKLIST4:
                    break;
                case ADDTASK1: //Add repeated task
                    HashMap<String, String> mapTask = view.addRepeatedTask();
                    if(!validateMapTask(mapTask)){
                        view.showErrorAddTask();
                    }else{
                        view.showTaskListPage(model.getTaskList());
                    }
                    break;
                case ADDTASK2: //Add unrepeated task
                    HashMap<String, String> mapTask1 = view.addUnrepeatedTask();
                    if(!validateMapTask(mapTask1)){
                        view.showErrorAddTask();
                    }else{
                        view.showTaskListPage(model.getTaskList());
                    }
                    break;
                case ADDTASK3: //Back to Task list page
                case ERRORADDTASK2:
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

        Boolean result = true;

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd H:m");
        Date time = null;
        Date startTime = null;
        Date endTime = null;
        int interval = 0;

        if(mapTask.containsKey("time")) {
            try {
                time = format.parse(mapTask.get("time"));
            } catch (ParseException e) {
                System.out.println("wrong time format <" + mapTask.get("time") + ">");
                result = false;
            }
        }
        if (mapTask.containsKey("startTime")){
            try {
                startTime = format.parse(mapTask.get("startTime"));
            } catch (ParseException e) {
                System.out.println("wrong start time format <" + mapTask.get("startTime") + ">");
                result = false;
            }
        }
        if (mapTask.containsKey("endTime")){
            try {
                endTime = format.parse(mapTask.get("endTime"));
            } catch (ParseException e) {
                System.out.println("wrong end time format <" + mapTask.get("endTime") + ">");
                result = false;
            }
        }
        if (mapTask.containsKey("interval")){
            try {
                interval = Integer.parseInt(mapTask.get("interval"));
            } catch (NumberFormatException e) {
                System.out.println("wrong interval <" + mapTask.get("interval") + ">");
                result = false;
            }
        }

        if (result){
            if(time != null) {
                result = model.addTask(new Task(mapTask.get("title"), time));
            }else{
                result = model.addTask(new Task(mapTask.get("title"), startTime, endTime, interval));
            }
        }

        return result;
    }
}
