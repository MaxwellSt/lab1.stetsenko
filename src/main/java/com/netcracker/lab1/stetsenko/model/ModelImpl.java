package com.netcracker.lab1.stetsenko.model;

import com.netcracker.lab1.stetsenko.LinkedTaskList;
import com.netcracker.lab1.stetsenko.TaskIO;
import com.netcracker.lab1.stetsenko.TaskList;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ���� on 20.01.2016.
 */
public class ModelImpl implements Model {

    public String getFirstMessage() {
        return "First message";
    }

    public String getTaskListFromFile(String pathFile) {
        FileReader in = null;
        try {
            in = new FileReader(pathFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TaskList taskList = new LinkedTaskList();
        TaskIO.read(taskList, in);

        return taskList.toString();
    }

    public String getSecondMessage() {
        return "Second message";
    }

    public String getThirdMessage() {
        return "Third message";
    }
}
