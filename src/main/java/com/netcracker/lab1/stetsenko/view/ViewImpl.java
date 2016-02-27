package com.netcracker.lab1.stetsenko.view;

import com.netcracker.lab1.stetsenko.Task;
import com.netcracker.lab1.stetsenko.TaskList;
import com.netcracker.lab1.stetsenko.controller.Actions;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ���� on 20.01.2016.
 */
public class ViewImpl implements View{

    private Scanner scanner;
    private String pageID = "";

    public ViewImpl() {
        scanner = new Scanner( System.in );
    }

    public Actions.EnumActions getAction() {

        String s = pageID + "_" + scanner.nextInt();
        Actions.EnumActions result = Actions.MAP_ENUM.get(s);

        if (result == null) {

            System.out.println("You entered wrong action. Try again.");
            result = getAction();

        }
        return result;
    }

    public void showMessage( String message ) {
        System.out.println( "Message : " + message );
    }

    public void showTaskListPage(Iterable<Task> taskList) {

        System.out.println("********* Task list *********");
        int i = 0;
        for (Task t : taskList) {
            i++;
            System.out.println("" + i + ". " + t.toString());
        }

        System.out.println("********* List page *********");
        System.out.println("1. Show Calendar");
        System.out.println("2. Add new task");
        System.out.println("3. Edit task");
        System.out.println("4. Save task list file");
        System.out.println("5. Delete task");
        System.out.println("6. Exit");
        pageID = "TASK_LIST";
    }

    public void showAddTask() {
        System.out.println("********* Add task page *********");
        System.out.println("1. Add repeated task");
        System.out.println("2. Add unrepeated task");
        System.out.println("3. Back");
        pageID = "ADD_TASK";
    }

    public void showErrorAddTask() {
        System.out.println("********* Error Add task page *********");
        System.out.println("1. Repeat add task");
        System.out.println("2. Back");
        pageID = "ERROR_ADD_TASK";
    }

    public Map<String, String> editRepeatedTask(Task task){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m");

        HashMap<String, String> mapTask = new HashMap<String, String>();
        System.out.println("- enter title of task. Current: <"+ task.getTitle() + ">");
        scanner.nextLine();
        mapTask.put("title", scanner.nextLine());
        System.out.println("- enter start-time of task. <yyyy-MM-dd H:m>. Current: <" + format.format(task.getStartTime()) + ">");
        mapTask.put("startTime", scanner.nextLine());
        System.out.println("- enter end-time of task. <yyyy-MM-dd H:m> Current: <" + format.format(task.getEndTime()) + ">");
        mapTask.put("endTime", scanner.nextLine());
        System.out.println("- enter interval of task, in sec. Current: <" + task.getRepeatInterval());
        mapTask.put("interval", scanner.nextLine());
        System.out.println("- Activated task (Y/N)? Current: <" + task.isActive() + ">");
        mapTask.put("active", scanner.nextLine());

        return mapTask;
    }

    public Map<String, String> editUnrepeatedTask(Task task){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m");

        HashMap<String, String> mapTask = new HashMap<String, String>();
        System.out.println("- enter title of task. Current: <"+ task.getTitle() + ">");
        scanner.nextLine();
        mapTask.put("title", scanner.nextLine());
        System.out.println("- enter time of task. <yyyy-MM-dd H:m> Current: <" + format.format(task.getTime()) + ">");
        mapTask.put("time", scanner.nextLine());
        System.out.println("- Activated task (Y/N)? Current: <" + task.isActive() + ">");
        mapTask.put("active", scanner.nextLine());

        return mapTask;
    }

    public Map<String, String> addRepeatedTask() {

        HashMap<String, String> mapTask = new HashMap<String, String>();
        System.out.println("- enter title of task.");
        scanner.nextLine();
        mapTask.put("title", scanner.nextLine());
        System.out.println("- enter start-time of task. <yyyy-MM-dd H:m> (example: 2016-12-30 17:25)");
        mapTask.put("startTime", scanner.nextLine());
        System.out.println("- enter end-time of task. <yyyy-MM-dd H:m> (example: 2016-12-30 17:25)");
        mapTask.put("endTime", scanner.nextLine());
        System.out.println("- enter interval of task, in sec.");
        mapTask.put("interval", scanner.nextLine());
        System.out.println("- Activated task (Y/N)?");
        mapTask.put("active", scanner.nextLine());

        return mapTask;
    }

    public Map<String, String> addUnrepeatedTask() {

        HashMap<String, String> mapTask = new HashMap<String, String>();
        System.out.println("- enter title of task.");
        scanner.nextLine();
        mapTask.put("title", scanner.nextLine());
        System.out.println("- enter time of task. <yyyy-MM-dd H:m> (example: 2016-12-30 17:25)");
        mapTask.put("time", scanner.nextLine());

        return mapTask;
    }

    public int showSelectTask() {
        System.out.println("Enter number of task.");
        return scanner.nextInt();
    }

    public void showErrorEditTask() {
        System.out.println("********* Error Add task page *********");
        System.out.println("1. Repeat edit task");
        System.out.println("2. Back");
        pageID = "ERROR_EDIT_TASK";
    }

    public void showCalendar(Iterable<Task> calendar) {

        System.out.println("********* Calendar *********");
        int i = 0;
        for (Task t : calendar) {
            i++;
            System.out.println("" + i + ". " + t.toString());
        }
    }

    public Map<String, String> getDateInterval() {

        HashMap<String, String> mapDate = new HashMap<String, String>();

        System.out.println("- enter Date-from. <yyyy-MM-dd H:m> (example: 2016-12-30 17:25)");
        scanner.nextLine();
        mapDate.put("dateFrom", scanner.nextLine());
        System.out.println("- enter Date-to. <yyyy-MM-dd H:m> (example: 2016-12-30 17:25)");
        mapDate.put("dateTo", scanner.nextLine());

        System.out.println(mapDate.get("dateFrom"));
        System.out.println(mapDate.get("dateTo"));
        return mapDate;
    }
}
