package com.netcracker.lab1.stetsenko.view;

import com.netcracker.lab1.stetsenko.Task;
import com.netcracker.lab1.stetsenko.TaskList;
import com.netcracker.lab1.stetsenko.controller.Actions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

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
        Actions result;
        String s = pageID + scanner.nextInt();
        return Actions.valueOf(s);
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
        System.out.println("4. Exit");
        pageID = "TASKLIST";
    }

    public void showAddTask() {
        System.out.println("********* Add task page *********");
        System.out.println("1. Add repeated task");
        System.out.println("2. Add unrepeated task");
        System.out.println("3. Back");
        pageID = "ADDTASK";
    }

    public HashMap<String, String> editRepeatedTask(){

        return null;
    }

    public HashMap<String, String> editUnrepeatedTask(){

        return null;
    }

    public HashMap<String, String> addRepeatedTask() {

        HashMap<String, String> mapTask = new HashMap<String, String>();
        System.out.println("- enter title of task.");
        mapTask.put("title", scanner.next());
        System.out.println("- enter start-time of task. <yyyy-MM-dd H:m> (example: 2016-12-30 17:25)");
        mapTask.put("startTime", scanner.next());
        System.out.println("- enter end-time of task. <yyyy-MM-dd H:m> (example: 2016-12-30 17:25)");
        mapTask.put("endTime", scanner.next());
        System.out.println("- enter interval of task, in sec.");
        mapTask.put("interval", scanner.next());

        return mapTask;
    }

    public HashMap<String, String> addUnrepeatedTask() {

        HashMap<String, String> mapTask = new HashMap<String, String>();
        System.out.println("- enter title of task.");
        mapTask.put("title", scanner.next());
        System.out.println("- enter time of task. <yyyy-MM-dd H:m> (example: 2016-12-30 17:25)");
        mapTask.put("time", scanner.next());

        return mapTask;
    }

    public void showEditTask() {
        System.out.println("********* Edit task page *********");
        System.out.println("1. Enter number of task.");
        System.out.println("2. Back");
        pageID = "EDITTASK";
    }

    public int showSelectTask() {
        return scanner.nextInt();
    }
}
