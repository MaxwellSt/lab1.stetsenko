package com.netcracker.lab1.stetsenko;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Stetsenko Maxim
 * @since 1.7
 */


public class ArrayTaskList extends TaskList {

    private int size = 0;

    private int maxLenght = 10;

    private Task[] arrayTask = new Task[maxLenght];

    public ArrayTaskList() {

    }

    public ArrayTaskList(Task... tasks) {
        for (Task task : tasks) {
            try {
                add(task);
            } catch (NullPointerException e) {
                System.out.println("Can't add null element");
            }
        }
    }

    public boolean add(Task task) throws NullPointerException {
        if (task == null) {
            throw new NullPointerException();
        }
        if (getSize() < maxLenght) {
            arrayTask[size] = task;
            size += 1;
            return true;
        } else {
            maxLenght = maxLenght * 2;
            Task[] arrayTask2 = new Task[maxLenght];
            int i = 0;
            for (Task iter : arrayTask) {
                arrayTask2[i] = iter;
                i += 1;
            }
            arrayTask = arrayTask2;
            add(task);
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public Task getTask(int i) {
        if (i <= getSize()) {
            return arrayTask[i];
        } else {
            System.out.println("Error index!");
            return null;
        }
    }

    public void printTask(int i) {
        if (i <= getSize()) {
            System.out.println(arrayTask[i].getTitle());
        } else {
            System.out.println("Error index!");
        }
    }

    public boolean remove(Task task) {
        if (task == null) {
            for (int index = 0; index < size; index++)
                if (arrayTask[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (task.equals(arrayTask[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(arrayTask, index + 1, arrayTask, index,
                    numMoved);
        arrayTask[--size] = null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for (Iterator it = iterator(); it.hasNext(); ) {
            result = result * prime + it.next().hashCode();
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (size != ((ArrayTaskList) obj).size)
            return false;
        ArrayTaskList other = (ArrayTaskList) obj;
        for(int i = 0; i < size; i++){
            if(! other.getTask(i).equals(other.getTask(i)))
                return  false;
        }
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ArrayTaskList newArayTaskList = new ArrayTaskList();
        for (Iterator it = iterator(); it.hasNext(); ) {
            newArayTaskList.add((Task) it.next());
        }
        return newArayTaskList;
    }

    @Override
    public String toString() {

        String result = "";

        for (Iterator it = iterator(); it.hasNext(); ) {
            result = result + "[" + it.next().toString() + "]";
        }
        return result;
    }

    public Iterator iterator() {
        Iterator it = new Iterator() {
            int cursor;       // index of next element to return
            int lastRet = -1; // index of last element returned; -1 if no such

            public boolean hasNext() {
                return cursor != size;
            }

            public Task next() {
                int i = cursor;
                if (i >= size)
                    throw new NoSuchElementException();
                Task[] elementData = ArrayTaskList.this.arrayTask;
                if (i >= elementData.length)
                    throw new ConcurrentModificationException();
                cursor = i + 1;
                return elementData[lastRet = i];
            }

            public void remove() {
                if (lastRet < 0)
                    throw new IllegalStateException();

                try {
                    ArrayTaskList.this.fastRemove(lastRet);
                    cursor = lastRet;
                    lastRet = -1;
                } catch (IndexOutOfBoundsException ex) {
                    throw new ConcurrentModificationException();
                }
            }
        };

        return it;
    }
}
