package com.netcracker.lab1.stetsenko.model;

import com.netcracker.lab1.stetsenko.*;
import com.netcracker.lab1.stetsenko.taskException.*;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by ���� on 20.01.2016.
 */
public class ModelImpl implements Model {

    private TaskList taskList;
    private static final String PATH_FILE = "test3.txt";

    private static final Logger log = Logger.getLogger(ModelImpl.class);

    private TaskList readTaskListFromFile() throws IOException{

        log.info("readTaskListFromFile");
        TaskList resTaskList = new ArrayTaskList();

        try (FileReader in = new FileReader(PATH_FILE)) {

            TaskIO.read(resTaskList, in);

        }

        return resTaskList;
    }

    public void loadTaskList() throws LoadTaskException {

        try {
            this.taskList = readTaskListFromFile();
        } catch (IOException e) {
            throw new LoadTaskException("Error load task list");
        }
    }

    public TaskList getTaskList() throws GetTaskListException{

        if (this.taskList == null) {

                try {
                    loadTaskList();
                } catch (LoadTaskException e) {
                   throw new GetTaskListException("Error get task list!");
                }
        }

        return this.taskList;
    }

    public void saveTasks() throws SaveTasksException {
        log.info("saveTaskList");
        try (FileWriter out = new FileWriter(PATH_FILE)) {
            TaskIO.write(this.taskList, out);
        }catch (Exception e){
            throw new SaveTasksException(e);
        }
    }

    public Task getTask(int i) throws TaskNotFoundException {
        log.info("getTask");
        if (i < 0 || i >= this.taskList.getSize())
            throw new TaskNotFoundException("Task not found!");
        return this.taskList.getTask(i);
    }

    public Iterable<Task> incoming(Date from, Date to) {
        return Tasks.incoming(taskList, from, to);
    }

    public Task createTask(Map<String, String> mapTask) throws CreateTaskException {
        log.info("createTask");
        try {
            Task newTask = checkMapTask(mapTask, null);
            this.taskList.add(newTask);
            return newTask;
        } catch (ValidateTaskException e) {
            throw new CreateTaskException(e);
        }
    }

    public Task editTask(Map<String, String> mapTask, Task currentTask) throws EditTaskException {
        log.info("editTask");
        try {
            return checkMapTask(mapTask, currentTask);
        } catch (ValidateTaskException e) {
            throw new EditTaskException(e);
        }
    }

    public boolean removeTask(int i) throws RemoveTaskException{
        log.info("removeTask");
        try {
            return taskList.remove(getTask(i));
        } catch (Exception e) {
            throw new RemoveTaskException("Remove error");
        }
    }

    private Task checkMapTask(Map<String, String> mapTask, Task tempTask) throws ValidateTaskException {

        Boolean result = true;

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd H:m");
        Date time = null;
        Date startTime = null;
        Date endTime = null;
        int interval = 0;
        Boolean active = false;

        if (mapTask.containsKey("time")) {
            try {
                time = format.parse(mapTask.get("time"));
            } catch (ParseException e) {
                System.out.println("wrong time format <" + mapTask.get("time") + ">");
                result = false;
            }
        }
        if (mapTask.containsKey("startTime")) {
            try {
                startTime = format.parse(mapTask.get("startTime"));
            } catch (ParseException e) {
                System.out.println("wrong start time format <" + mapTask.get("startTime") + ">");
                result = false;
            }
        }
        if (mapTask.containsKey("endTime")) {
            try {
                endTime = format.parse(mapTask.get("endTime"));
            } catch (ParseException e) {
                System.out.println("wrong end time format <" + mapTask.get("endTime") + ">");
                result = false;
            }
        }
        if (mapTask.containsKey("interval")) {
            try {
                interval = Integer.parseInt(mapTask.get("interval"));
            } catch (NumberFormatException e) {
                System.out.println("wrong interval <" + mapTask.get("interval") + ">");
                result = false;
            }
        }

        if (mapTask.containsKey("active")) {
            try {
                active = (mapTask.get("active").toLowerCase().equals("y"));
            } catch (NumberFormatException e) {
                System.out.println("wrong active <" + mapTask.get("active") + ">");
                result = false;
            }
        }

        if (result) {
            if (time != null) {
                if (tempTask == null) {
                    tempTask = new Task(mapTask.get("title"), time);
                } else {
                    tempTask.setTitle(mapTask.get("title"));
                    tempTask.setTime(time);
                }
                tempTask.setActive(active);
            } else {
                if (tempTask == null) {
                    tempTask = new Task(mapTask.get("title"), startTime, endTime, interval);
                } else {
                    tempTask.setTitle(mapTask.get("title"));
                    tempTask.setTime(startTime, endTime, interval);
                }
                tempTask.setActive(active);
            }
        } else {

                throw new ValidateTaskException("Error validate params");
        }
        return tempTask;
    }
}
