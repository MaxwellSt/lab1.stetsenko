package com.netcracker.lab1.stetsenko.controller;

import com.netcracker.lab1.stetsenko.Task;
import com.netcracker.lab1.stetsenko.model.Model;
import com.netcracker.lab1.stetsenko.model.ModelImpl;
import com.netcracker.lab1.stetsenko.taskException.*;
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

    private static final Logger log = Logger.getLogger(ControllerImpl.class);

    public static void main(String[] args) {
        log.info("Start program");
        new ControllerImpl(new ModelImpl(), new ViewImpl()).start();
    }

    public ControllerImpl(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void start() {
        Actions.EnumActions action;
        boolean exit = false;
        showStartPage(exit);

        while (!exit) {

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
                    view.showMessage("You entered wrong action");
                    break;
            }
        }
    }

    private boolean checkIntervalDate(Map<String, String> mapDateString, Map<String, Date> mapDate) {

        Boolean result = true;
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd H:m");

        Date dateFrom = null;
        Date dateTo = null;

        if (mapDateString.containsKey("dateFrom")) {
            try {
                dateFrom = format.parse(mapDateString.get("dateFrom"));
                mapDate.put("dateFrom", dateFrom);
            } catch (ParseException e) {
                log.warn("dateFrom not valid");
                System.out.println("wrong Date-from format <" + mapDateString.get("dateFrom") + ">");
                result = false;
            }
        } else {
            result = false;
        }
        if (mapDateString.containsKey("dateTo")) {
            try {
                dateTo = format.parse(mapDateString.get("dateTo"));
                mapDate.put("dateTo", dateTo);
            } catch (ParseException e) {
                log.warn("dateTo not valid");
                System.out.println("wrong Date-to format <" + mapDateString.get("dateTo") + ">");
                result = false;
            }
        } else {
            result = false;
        }

        return result;
    }

    private void showStartPage(boolean exit) {

        view.showTaskListPage(model.getTaskList());

    }

    private void showCalendar(boolean exit) {
        log.info("showCalendar");
        Map<String, String> mapDateString = view.getDateInterval();
        Map<String, Date> mapDate = new HashMap<String, Date>();
        if (checkIntervalDate(mapDateString, mapDate)) {
            view.showCalendar(model.incoming(mapDate.get("dateFrom"), mapDate.get("dateTo")));
        }
        showStartPage(exit);
    }

    private void editTask(boolean exit) {
        int i = view.showSelectTask();
        log.info("editTask (#" + i + ")");
        Task editTask = null;
        try {
            editTask = model.getTask(i - 1);
        } catch (TaskNotFoundException e) {
            showStartPage(exit);
            return;
        }
        System.out.println(editTask.toString());
        Map<String, String> mapEditTask = new HashMap<String, String>();
        if (editTask.isRepeated()) {
            mapEditTask = view.editRepeatedTask(editTask);
        } else {
            mapEditTask = view.editUnrepeatedTask(editTask);
        }
        try {
            model.editTask(mapEditTask, editTask);
        } catch (EditTaskException e) {
            log.warn("EditTaskException");
            view.showErrorEditTask();
        }
    }

    private void saveToFile(boolean exit) {
        try {
            model.saveTaskList();
        } catch (IOException e) {
            exit = true;
        }
        log.info("saveToFile (" + exit + ")");
        showStartPage(exit);

    }

    private void addRepeatedTask(boolean exit) {
        Map<String, String> mapTask = view.addRepeatedTask();
        try {
            model.createTask(mapTask);
        } catch (CreateTaskException e) {
            view.showErrorAddTask();
        }
        log.info("addRepeatedTask (" + exit + ")");
        showStartPage(exit);
    }

    private void addUnrepeatedTask(boolean exit) {
        Map<String, String> mapTask = view.addUnrepeatedTask();
        try {
            model.createTask(mapTask);
        } catch (CreateTaskException e) {
            view.showErrorAddTask();
        }
        log.info("addUnrepeatedTask (" + exit + ")");
        showStartPage(exit);
    }

    private void showTaskListPage(boolean exit) {
        showStartPage(exit);
    }

    private void removeTask(boolean exit) {
        int i = view.showSelectTask();
        exit = !model.removeTask(i-1);
        log.info("removeTask (" + exit + ")");
        showStartPage(exit);
    }

}
