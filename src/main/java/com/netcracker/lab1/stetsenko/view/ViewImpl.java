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

    public void showStartPage() {
        System.out.println("********* Start page *********");
        System.out.println( "Enter please one of the following actions:" );
        System.out.println( "1. Read TaskList from file" );
        System.out.println( "2. Exit" );
        pageID = "STARTPAGE";
    }

    public Actions getAction() {
        Actions result = null;

        try {
            String s = pageID + scanner.nextInt();
            result = Actions.valueOf(s);
        } catch (IllegalArgumentException e) {
            System.out.println("You entered wrong action. Try again.");
            result = getAction();
        }catch (InputMismatchException e){
            System.out.println("You entered wrong action. Try again.");
            scanner.nextLine();
            result = getAction();
        }
        return result;
    }

    public void showMessage( String message ) {
        System.out.println( "Message : " + message );
    }

    public String showPathFile(){
        return "test3.txt";
    }

    public void showTaskListPage(TaskList taskList) {

        System.out.println("********* Task list *********");
        Iterator it = taskList.iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            Task task = (Task) it.next();
            System.out.println("" + i + ". " + task.toString());
        }
        System.out.println("********* List page *********");
        System.out.println("1. Show Calendar");
        System.out.println("2. Add new task");
        System.out.println("3. Edit task");
        System.out.println("4. Save task list file");
        System.out.println("5. Exit");
        pageID = "TASKLIST";
    }

    public void showAddTask() {
        System.out.println("********* Add task page *********");
        System.out.println("1. Add repeated task");
        System.out.println("2. Add unrepeated task");
        System.out.println("3. Back");
        pageID = "ADDTASK";
    }

    public void showErrorAddTask() {
        System.out.println("********* Error Add task page *********");
        System.out.println("1. Repeat add task");
        System.out.println("2. Back");
        pageID = "ERRORADDTASK";
    }

    public HashMap<String, String> editRepeatedTask(Task task){

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

    public HashMap<String, String> editUnrepeatedTask(Task task){

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

    public HashMap<String, String> addRepeatedTask() {

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

    public HashMap<String, String> addUnrepeatedTask() {

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
        pageID = "ERROREDITTASK";
    }

    public void showCalendar(Iterable<Task> calendar) {



    }

    public HashMap<String, String> getDateInterval() {

        HashMap<String, String> mapDate = new HashMap<String, String>();

        System.out.println("- enter Date-from. <yyyy-MM-dd H:m> (example: 2016-12-30 17:25)");
        scanner.nextLine();
        mapDate.put("dateFrom", scanner.nextLine());
        System.out.println("- enter Date-to. <yyyy-MM-dd H:m> (example: 2016-12-30 17:25)");
        mapDate.put("dateTo", scanner.nextLine());

        return mapDate;
    }
}
