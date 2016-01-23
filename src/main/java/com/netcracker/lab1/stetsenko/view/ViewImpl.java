package com.netcracker.lab1.stetsenko.view;

import com.netcracker.lab1.stetsenko.TaskList;

import java.util.Scanner;

/**
 * Created by Макс on 20.01.2016.
 */
public class ViewImpl implements View{

    private Scanner scanner;
    private int pageID = 0;

    public ViewImpl() {
        scanner = new Scanner( System.in );
    }

    public void showStartPage() {
        System.out.println( "Enter please one of the following actions:" );
        System.out.println( "1. Read TaskList from file" );
        System.out.println( "2. Exit" );
        pageID = 1;
    }

    public int getAction() {
        return Integer.parseInt("" + pageID + scanner.nextInt());
    }

    public void showMessage( String message ) {
        System.out.println( "Message : " + message );
    }

    public String showPathFile(){
        System.out.println("Enter path to file:");
        return scanner.next();
    }

    public void showTaskListPage(String taskList) {
        System.out.println(taskList);
        System.out.println("**************************");
        System.out.println("1. Show Calendar");
        System.out.println("2. Add new task");
        System.out.println("3. Edit task");
        System.out.println("4. Exit");
        pageID = 2;
    }
}
