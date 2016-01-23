package com.netcracker.lab1.stetsenko;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by Max on 13.01.2016.
 */
public class TaskIO {


    /**
     * записує задачі із списку у потік у бінарному форматі.
     *
     * @param tasks
     * @param outStreem
     */
    public static void write(TaskList tasks, OutputStream outStreem) {
        if ((tasks.getSize() == 0) || outStreem == null) {
            return;
        }
        DataOutputStream out = new DataOutputStream(outStreem);
        Iterator it = tasks.iterator();
        try {
            out.writeInt(tasks.getSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
        while (it.hasNext()) {
            Task task = (Task) it.next();

                out.writeInt(task.getTitle().length());
                out.writeUTF(task.getTitle());
                out.writeBoolean(task.isActive());
                out.writeInt(task.getRepeatInterval());
                if (task.isRepeated()) {
                    out.writeLong(task.getStartTime().getTime());
                    out.writeLong(task.getEndTime().getTime());
                } else {
                    out.writeLong(task.getTime().getTime());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (out != null){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * зчитує задачі із потоку у даний список задач.
     *
     * @param tasks
     * @param inStreem
     */
    public static void read(TaskList tasks, InputStream inStreem) {

        DataInputStream in = new DataInputStream(inStreem);
        try {
            int i = in.readInt(); //count of tasks
            for (int m = 0; m < i; m++) {

                int lengthTitle = in.readInt();
                String title = in.readUTF();
                Boolean isActive = in.readBoolean();
                int interval = in.readInt();
                Task task;
                if (interval > 0) {
                    Date startTime = new Date(in.readLong());
                    Date endTime = new Date(in.readLong());
                    task = new Task(title, startTime, endTime, interval);
                } else {
                    task = new Task(title, new Date(in.readLong()));
                }
                tasks.add(task);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * записує задачі із списку у файл
     *
     * @param tasks
     * @param file
     */
    public static void writeBinary(TaskList tasks, File file) {

        try {
            write(tasks, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * зчитує задачі із файлу у список задач
     *
     * @param tasks
     * @param file
     */
    public static void readBinary(TaskList tasks, File file) {

        try {
            read(tasks, new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * записує задачі зі списку у потік в текстовому форматі
     *
     * @param tasks
     * @param out
     */
    public static void write(TaskList tasks, Writer out) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:S.sss");
        BufferedWriter writer = new BufferedWriter(out);
        int size = tasks.getSize();
        try {
            writer.write("" + size);
            writer.write(System.getProperty("line.separator"));

            Iterator it = tasks.iterator();
            while (it.hasNext()) {
                Task task = (Task) it.next();
                String result;
                if (task.isRepeated()) {
                    result = "\"" + task.getTitle() + "\"" + " from [" + format.format(task.getStartTime()) + "] to ["
                            + format.format(task.getEndTime()) + "] every [" + intervalToFormat(task.getRepeatInterval()) + "]"
                            + ((task.isActive()) ? "" : " inactive") + (it.hasNext() ? ";" : ".");
                } else {
                    result = "\"" + task.getTitle() + "\"" + " at [" + format.format(task.getTime()) + "]"
                            + ((task.isActive()) ? "" : " inactive") + (it.hasNext() ? ";" : ".");
                }
                writer.write(result);
                writer.write(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * зчитує задачі із потоку у список
     *
     * @param tasks
     * @param in
     */
    public static void read(TaskList tasks, Reader in) {

        BufferedReader reader = new BufferedReader(in);
        try {
            int size = Integer.parseInt(reader.readLine());

            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd H:m:S.sss");

            for (int i = 0; i < size; i++) {
                String stringTask = reader.readLine();
                String[] splitTitle = stringTask.split("\"");
                String title = splitTitle[1];
                String[] splitTime = splitTitle[2].split("]");
                Task task = null;
                if (splitTime.length == 4) { // repeated task
                    String[] splitStart = splitTime[0].split("\\[");
                    Date startTime = format.parse(splitStart[1]);

                    String[] splitEnd = splitTime[1].split("\\[");
                    Date endTime = format.parse(splitEnd[1]);


                    String[] splitInterval = splitTime[2].split("\\[");
                    splitInterval = splitInterval[1].split(" ");
                    int days = Integer.parseInt(splitInterval[0]);
                    int hours = Integer.parseInt(splitInterval[2]);
                    int minutes = Integer.parseInt(splitInterval[4]);
                    int seconds = Integer.parseInt(splitInterval[6]);

                    int interval = seconds + minutes * 60 + hours * 60 * 60 + days * 24 * 60 * 60;

                    Boolean isActive = !splitTime[3].contains("inactive");
                    task = new Task(title, startTime, endTime, interval);
                    task.setActive(isActive);

                } else {
                    //splitTime[0] - time
                    //splitTime[1] - inactive. or ./;
                    String[] splitStart = splitTime[0].split("\\[");
                    Date startTime = format.parse(splitStart[1]);
                    Boolean isActive = !splitTime[1].contains("inactive");
                    task = new Task(title, startTime);
                    task.setActive(isActive);
                }
                tasks.add(task);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * записує задачі у файл у текстовому форматі
     *
     * @param tasks
     * @param file
     */
    public static void writeText(TaskList tasks, File file) {
        try {
            write(tasks, new FileWriter(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * зчитує задачі із файлу у текстовому вигляді
     *
     * @param tasks
     * @param file
     */
    public static void readText(TaskList tasks, File file) {
        try {
            read(tasks, new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param interval in sec
     * @return
     */
    public static String intervalToFormat(int interval) {
        int days = (int) (interval / 86400);
        interval = interval - (days * 86400);
        int hours = (int) (interval / 3600);
        interval = interval - (hours * 3600);
        int minutes = (int) (interval / 60);
        int seconds = interval - (minutes * 60);

        return "" + (days + ((days > 1) ? "days " : " day ")) +
                (hours + ((hours > 1) ? " hours " : " hour ")) +
                (minutes + ((minutes > 1) ? " minutes " : " minute ")) +
                (seconds + ((seconds > 1) ? " seconds" : " second"));
    }

}
