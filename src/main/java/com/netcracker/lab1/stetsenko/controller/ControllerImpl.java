package com.netcracker.lab1.stetsenko.controller;

import com.netcracker.lab1.stetsenko.Task;
import com.netcracker.lab1.stetsenko.model.Model;
import com.netcracker.lab1.stetsenko.model.ModelImpl;
import com.netcracker.lab1.stetsenko.taskException.NullTaskException;
import com.netcracker.lab1.stetsenko.taskException.NullTaskListException;
import com.netcracker.lab1.stetsenko.taskException.TaskNotFoundException;
import com.netcracker.lab1.stetsenko.view.View;
import com.netcracker.lab1.stetsenko.view.ViewImpl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        Actions.EnumActions action;
        boolean exit = false;
        showStartPage(exit);

        while(!exit){

            action = view.getAction();

            switch (action) {

                case TASK_LIST_1:
                    showCalendar(exit);
                    break;
                case TASK_LIST_2:
                    view.showAddTask();
                    break;
                case TASK_LIST_3: //edit task
                    editTask(exit);
                    break;
                case TASK_LIST_4:
                    saveToFile(exit);
                    break;
                case TASK_LIST_5:
                    removeTask(exit);
                    break;
                case TASK_LIST_6:
                    exit = true;
                    break;
                case ERROR_ADD_TASK_1://add new task
                    view.showAddTask();
                    break;
                case ERROR_EDIT_TASK_1:
                    editTask(exit);
                    break;
                case ADD_TASK_1: //Add repeated task
                    addRepeatedTask(exit);
                    break;
                case ADD_TASK_2: //Add unrepeated task
                    addUnrepeatedTask(exit);
                    break;
                case ADD_TASK_3: //Back to Task list page
                    showTaskListPage(exit);
                    break;
                case ERROR_ADD_TASK_2:
                    showTaskListPage(exit);
                    break;
                case ERROR_EDIT_TASK_2:
                    showTaskListPage(exit);
                    break;
                default:
                    view.showMessage( "You entered wrong action" );
                    break;
            }
        }
    }

    private boolean checkMapTask(Map<String, String> mapTask){

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
                currentTask = model.createUnrepeatedTask(mapTask.get("title"), time, active);
            }else{
                currentTask = model.createRepeatedTask(mapTask.get("title"), startTime, endTime, interval, active);
            }
        }

        return result;
    }

    private boolean checkIntervalDate(Map<String, String> mapDateString, Map<String, Date> mapDate){

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

    private void showStartPage(boolean exit) {

        try {
            view.showTaskListPage(model.getTaskList());
        } catch (NullTaskListException e) {
            exit = true;
        }

    }

    private void showCalendar(boolean exit){
        Map<String, String> mapDateString = view.getDateInterval();
        Map<String, Date> mapDate = new HashMap<String, Date>();
        if(checkIntervalDate(mapDateString, mapDate)){
            view.showCalendar(model.incoming(mapDate.get("dateFrom"), mapDate.get("dateTo")));
        }
        showStartPage(exit);
    }

    private void editTask(boolean exit){
        int i = view.showSelectTask();
        Task editTask= null;
        try {
            editTask = model.getTask(i-1);
        } catch (TaskNotFoundException e) {
            showStartPage(exit);
            return;
        }
        System.out.println(editTask.toString());
        Map<String, String> mapEditTask = new HashMap<String, String>();
        if(editTask.isRepeated()){
            mapEditTask = view.editRepeatedTask(editTask);
        }else{
            mapEditTask = view.editUnrepeatedTask(editTask);
        }
        if(!checkMapTask(mapEditTask)){
            view.showErrorEditTask();
        }else{
            try {
                model.removeTask(editTask);
                model.addTask(currentTask);
                showStartPage(exit);
            } catch (NullTaskException e) {
                exit = true;
            }
        }
    }

    private void saveToFile(boolean exit) {
        try {
            model.saveTaskList();
        } catch (IOException e) {
            exit = true;
        }
        showStartPage(exit);

    }

    private void addRepeatedTask(boolean exit){
        Map<String, String> mapTask = view.addRepeatedTask();
        if(!checkMapTask(mapTask)){
            view.showErrorAddTask();
        }else{
            try {
                model.addTask(currentTask);
            } catch (NullTaskException e) {
                exit = true;
            }
            showStartPage(exit);
        }
    }

    private void addUnrepeatedTask(boolean exit){
        Map<String, String> mapTask1 = view.addUnrepeatedTask();
        if(!checkMapTask(mapTask1)){
            view.showErrorAddTask();
        }else{
            try {
                model.addTask(currentTask);
            } catch (NullTaskException e) {
                exit = true;
            }
            showStartPage(exit);
        }
    }

    private void showTaskListPage(boolean exit){
        showStartPage(exit);
    }

    private void removeTask(boolean exit){
        int i = view.showSelectTask();
        Task delTask= null;
        try {
            delTask = model.getTask(i-1);
        } catch (TaskNotFoundException e) {
            showStartPage(exit);
            return;
        }

        model.removeTask(delTask);
        showStartPage(exit);
    }

}
