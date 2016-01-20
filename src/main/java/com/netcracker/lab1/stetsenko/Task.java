package com.netcracker.lab1.stetsenko;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Max on 19.09.2015.
 */

public class Task implements Serializable {

    private String title;
    private Date time;
    private Date end;
    private int interval; //час повторення в секундах
    private boolean active;
    private Date start;
    private boolean repeated;

    /**
     * Constructs inactive task, that
     performed in a given time without repeating with a given title.
     *
     * @param title the initial title for task.
     * @param time the initial time for task,
     */
    public Task(String title, Date time){
        this.title = title;
        setTime(time);
        this.repeated = false;
    }

    /**
     * Constructs inactive task that is performed in a given period of time
     * (the start and end inclusive) of set intervals and has given a title.
     *
     * @param title the initial title for task.
     * @param start the initial start-time for task.
     * @param end the initial end-time for task.
     * @param interval the initial interval for task.
     */
    public Task(String title, Date start, Date end, int interval){
        setTime(start, end, interval);
        this.title = title;
    }

    /**
     * Returns start-time of the task
     */
    public Date getStartTime(){
        if (this.repeated) {
            return this.start;
        }
        return this.time;
    }

    /**
     * Returns interval of the task
     */
    public int getRepeatInterval(){
        if (this.repeated) {
            return this.interval;
        }else{return 0;
        }
    }

    /**
     * Sets start, end-times and interval of the task. If task wasn't repeated - it becomes so.
     *
     * @param start
     * @param end
     * @param interval
     */
    public void setTime(Date start, Date end, int interval){
        if (start == null){
            throw new RuntimeException("Error param (start) can't be = null");
        }
        if (end == null){
            throw new RuntimeException("Error param (end) can't be = null");
        }
        if (interval <= 0){
            throw new RuntimeException("Error param (interval) can't be <= 0");
        }
        if (start.after(end)){
            throw new RuntimeException("Error! Start-time can't be > end-time");
        }

        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
    }

    /**
     * Returns sign repeated
     */
    public boolean isRepeated(){
        return this.repeated;
    }

    /**
     * Returns sign Active or InActive of task
     */
    public boolean isActive() {
        return active;
    }

    /**
     *
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        if (this.repeated) {
            return this.start;
        }
        return time;
    }

    public void setTime(Date time) {

        if (time == null){
            throw new RuntimeException("Error param (time) can't be = null");
        }
        this.time = time;

        if (this.repeated) {
            this.repeated = false;
        }
    }

    public Date getEndTime() {
        if (this.repeated) {
            return this.end;
        }
        return this.time;
    }

    public void setEndTime(Date end) {
        this.end = end;
    }

    public void setInterval(int interval) {
        if (interval <= 0){
            throw new RuntimeException("Error param (interval) can't be <= 0");
        }
        this.interval = interval;
    }

    @Override
    public int hashCode() {
        return ((time == null) ? 1 : time.hashCode()) + ((end == null) ? 1 : end.hashCode()) + interval + ((start == null) ? 1 : start.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if ((time != null && other.time != null && !time.equals(other.time))
                || (end != null && other.end != null && !end.equals(other.end))
                || interval != other.interval
                || (start != null && other.start != null && !start.equals(other.start))
                || !title.equals(other.title))
            return false;
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Date nextTimeAfter(Date current){

        if(!this.active){
            return null;
        }

        if(!this.repeated) {
            return this.time;
        }else if (current.after(this.end)){
            return null;
        }else if(current.before(this.start)) {
            return this.start;
        }else{
            int numbInterval = (int) Math.floor((current.getTime() - this.start.getTime())/this.interval) + 1;
            return new Date (this.start.getTime()+(numbInterval*this.interval));
        }

    }
    public String toString(){
        return "title:\n" + this.title +
                "time:\n" + this.time +
                "end:\n" + this.end +
                "interval:\n" + this.interval +
                "active:" + this.active;
    }
}
