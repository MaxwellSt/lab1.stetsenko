package com.netcracker.lab1.stetsenko;
import java.util.*;

/**
 * Created by Max on 26.12.2015.
 */
public class Tasks {

    public static Iterable<Task> incoming(Iterable<Task> taskList, Date from, Date to){

        ArrayList arrayTask = new ArrayList();

        Iterator it = taskList.iterator();
        while(it.hasNext()){
            Task task = (Task) it.next();
            if ((task != null) && (task.nextTimeAfter(from) != null) && (task.nextTimeAfter(from).before(to)) && (task.nextTimeAfter(from).getTime() >= from.getTime())) {
                arrayTask.add(task);
            }
        }
        return arrayTask;
    }

    public static SortedMap<Date, Set<Task>> calendar(Iterable<Task> tasks, Date start, Date end){
        SortedMap<Date, Set<Task>> calendarMap = new TreeMap<Date, Set<Task>>();

        ArrayList<Task> arrayTask = (ArrayList) incoming(tasks, start, end);

        for(Task iter: arrayTask){
            if (calendarMap.get(iter.nextTimeAfter(start)) != null){
                Set<Task> setTask = calendarMap.get(iter.nextTimeAfter(start));
                setTask.add(iter);
                calendarMap.put(iter.nextTimeAfter(start), setTask);
            }else{
                Set<Task> setTask = new HashSet<Task>();
                setTask.add(iter);
                calendarMap.put(iter.nextTimeAfter(start), setTask);
            }
        }
        return  calendarMap;
    }
}
