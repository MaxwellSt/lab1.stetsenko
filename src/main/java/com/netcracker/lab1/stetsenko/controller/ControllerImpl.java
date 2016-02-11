package com.netcracker.lab1.stetsenko.controller;

import com.netcracker.lab1.stetsenko.Task;
import com.netcracker.lab1.stetsenko.model.Model;
import com.netcracker.lab1.stetsenko.model.ModelImpl;
import com.netcracker.lab1.stetsenko.taskException.NullTaskException;
import com.netcracker.lab1.stetsenko.taskException.NullTaskListException;
import com.netcracker.lab1.stetsenko.view.View;
import com.netcracker.lab1.stetsenko.view.ViewImpl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 * Created by ���� on 20.01.2016.
 */
public class ControllerImpl {

    private Model model;
    private View view;
    private Task currentTask = null;

    private static final Logger log = Logger.getLogger(ControllerImpl.class);

    public static void main( String[] args ) {
        log.info("Test log message");
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
                    log.error("test log error message");
                    String pathFile = view.showPathFile();
                    if (checkPathFile(pathFile)) {
                        try {
                            view.showTaskListPage(model.getTaskListFromFile(pathFile));
                        } catch (IOException e) {
                            exit = true;
                        }
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
                    HashMap<String, String> mapDateString = view.getDateInterval();
                    HashMap<String, Date> mapDate = new HashMap<String, Date>();
                    if(checkIntervalDate(mapDateString, mapDate)){
                       view.showCalendar(model.incoming(mapDate.get("dateFrom"), mapDate.get("dateTo")));
                    }
                    try {
                        view.showTaskListPage(model.getTaskList());
                    } catch (NullTaskListException e) {
                        exit = true;
                    }
                    break;
                case TASKLIST2:
                case ERRORADDTASK1://add new task
                    view.showAddTask();
                    break;
                case TASKLIST3: //edit task
                case ERROREDITTASK1:
                    int i = view.showSelectTask();
                    Task editTask= model.getTask(i-1);
                    System.out.println(editTask.toString());
                    HashMap<String, String> mapEditTask = new HashMap<String, String>();
                    if(editTask.isRepeated()){
                       mapEditTask = view.editRepeatedTask(editTask);
                    }else{
                        mapEditTask = view.editUnrepeatedTask(editTask);
                    }
                    if(!checkMapTask(mapEditTask)){
                        view.showErrorAddTask();
                    }else{
                        try {
                            model.getTaskList().remove(editTask);
                            model.getTaskList().add(currentTask);
                            view.showTaskListPage(model.getTaskList());
                        } catch (NullTaskListException e) {
                            exit = true;
                        }
                    }
                    break;
                case TASKLIST4:
                    String pathFile1 = view.showPathFile();
                    if (checkPathFile(pathFile1)) {
                        try {
                            view.showTaskListPage(model.saveTaskListFromFile(pathFile1));
                        } catch (IOException e) {
                            exit = true;
                        }
                    } else {
                        view.showMessage( "File not found" );
                        view.showStartPage();
                    }
                    break;
                case ADDTASK1: //Add repeated task
                    HashMap<String, String> mapTask = view.addRepeatedTask();
                    if(!checkMapTask(mapTask)){
                        view.showErrorAddTask();
                    }else{
                        try {
                            model.addTask(currentTask);
                        } catch (NullTaskException e) {
                            exit = true;
                        }
                        try {
                            view.showTaskListPage(model.getTaskList());
                        } catch (NullTaskListException e) {
                            exit = true;
                        }
                    }
                    break;
                case ADDTASK2: //Add unrepeated task
                    HashMap<String, String> mapTask1 = view.addUnrepeatedTask();
                    if(!checkMapTask(mapTask1)){
                        view.showErrorAddTask();
                    }else{
                        try {
                            model.addTask(currentTask);
                        } catch (NullTaskException e) {
                            exit = true;
                        }
                        try {
                            view.showTaskListPage(model.getTaskList());
                        } catch (NullTaskListException e) {
                            exit = true;
                        }
                    }
                    break;
                case ADDTASK3: //Back to Task list page
                case ERRORADDTASK2:
                case ERROREDITTASK2:
                    try {
                        view.showTaskListPage(model.getTaskList());
                    } catch (NullTaskListException e) {
                        exit = true;
                    }
                    break;
                default:
                    view.showMessage( "You entered wrong action" );
                    break;
            }
        }
    }

    private boolean checkPathFile(String pathFile){
        File file = new File(pathFile);
        return file.exists();
    }

    private boolean checkMapTask(HashMap<String, String> mapTask){

        Boolean result = true;

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd H:m");
        Date time = null;
        Date startTime = null;
        Date endTime = null;
        int interval = 0;
        Boolean active = false;

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

        if (mapTask.containsKey("active")){
            try {
                active = (mapTask.get("active").toLowerCase().equals("y"));
            } catch (NumberFormatException e) {
                System.out.println("wrong active <" + mapTask.get("active") + ">");
                result = false;
            }
        }

        if (result){
            if(time != null) {
                currentTask = new Task(mapTask.get("title"), time);
                currentTask.setActive(active);
            }else{
                currentTask = new Task(mapTask.get("title"), startTime, endTime, interval);
                currentTask.setActive(active);
            }
        }

        return result;
    }

    private boolean checkIntervalDate(HashMap<String, String> mapDateString, HashMap<String, Date> mapDate){

        Boolean result = true;
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd H:m");

        Date dateFrom = null;
        Date dateTo   = null;

        if (mapDateString.containsKey("dateFrom")){
            try {
                dateFrom = format.parse(mapDateString.get("dateFrom"));
                mapDate.put("dateFrom", dateFrom);
            } catch (ParseException e) {
                System.out.println("wrong Date-from format <" + mapDateString.get("dateFrom") + ">");
                result = false;
            }
        }else{
            result = false;
        }
        if (mapDateString.containsKey("dateTo")){
            try {
                dateTo = format.parse(mapDateString.get("dateTo"));
                mapDate.put("dateTo", dateTo);
            } catch (ParseException e) {
                System.out.println("wrong Date-to format <" + mapDateString.get("dateTo") + ">");
                result = false;
            }
        }else{
            result = false;
        }

        return result;
    }
}
