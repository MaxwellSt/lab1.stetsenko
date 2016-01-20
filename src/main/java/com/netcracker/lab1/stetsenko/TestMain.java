package com.netcracker.lab1.stetsenko;

import java.io.*;
import java.util.*;

/**
 * Created by Max on 05.09.2015.
 */
public class TestMain {


    public static void main(String[] args) {

        Task task1 = new Task("Task1", new Date(115, 11, 15));
        Task task2 = new Task("Task_new", new Date(115, 11, 15), new Date(115, 11, 28), 1000);

        TaskList arrayTaskList = new LinkedTaskList(task1, task2);

        task1.setActive(true);
        //task2.setActive(true);


        ArrayList arrayTaskFromTo = (ArrayList) Tasks.incoming(arrayTaskList, new Date(115, 10, 1), new Date(115, 11, 30));
        //System.out.println(arrayTaskFromTo.size());

        SortedMap sortedMap = Tasks.calendar(arrayTaskList, new Date(115, 10, 1), new Date(115, 11, 30));
        System.out.println(sortedMap.size());

        //чтение-запись в/из потока
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("D:\\java-cours\\test.d");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        TaskIO.write(arrayTaskList, out);

        FileInputStream in = null;
        try {
            in = new FileInputStream("D:\\java-cours\\test.d");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        TaskList arrayTaskList2 = new LinkedTaskList();
        TaskIO.read(arrayTaskList2, in);

        //чтение-запись в/из файла
        File file = new File("D:\\java-cours\\test2.d");
        TaskIO.writeBinary(arrayTaskList, file);

        TaskList arrayTaskList3 = new LinkedTaskList();
        TaskIO.readBinary(arrayTaskList3, file);

        FileWriter out2 = null;
        try {
            out2 = new FileWriter("D:\\java-cours\\test3.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        TaskIO.write(arrayTaskList, out2);

        FileReader in2 = null;
        try {
            in2 = new FileReader("D:\\java-cours\\test3.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        TaskList arrayTaskList4 = new LinkedTaskList();
        TaskIO.read(arrayTaskList4, in2);

        System.out.println(arrayTaskList4.getSize());

        File fileText = new File("D:\\java-cours\\test4.txt");
        TaskIO.writeText(arrayTaskList, fileText);

        TaskList arrayTaskList5 = new LinkedTaskList();
        TaskIO.readText(arrayTaskList5, fileText);
    }
}
