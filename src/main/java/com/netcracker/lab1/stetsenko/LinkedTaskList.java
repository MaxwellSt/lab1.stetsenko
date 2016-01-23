package com.netcracker.lab1.stetsenko;
import java.util.*;

/**
 * Created by Max on 07.11.2015.
 */
public class LinkedTaskList extends TaskList {

    private int size = 0;
    private Node first;
    private Node last;

    private static class Node {
        Task item;
        Node next;
        Node prev;

        Node(Node prev, Task element, Node next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public LinkedTaskList() {

    }

    public LinkedTaskList(Task... tasks) {
        for (Task task : tasks) {
            try {
                add(task);
            } catch (NullPointerException ex) {
                System.out.println("Can't add null element");
            }
        }
    }

    public boolean add(Task task) throws NullPointerException {
        if (task == null) {
            throw new NullPointerException();
        }
        linkLast(task);
        return true;
    }

    void linkLast(Task task) {
        final Node l = last;
        final Node newNode = new Node(l, task, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    public int getSize() {
        return size;
    }

    /**
     * Returns the (non-null) Node at the specified element index.
     */
    Node node(int index) {
        // assert isElementIndex(index);

        if (index < size) {
            Node x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    public Task getTask(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    public void printTask(int i) {
        if (isElementIndex(i)) {
            System.out.println(getTask(i).getTitle());
        } else {
            System.out.println("Error index!");
        }
    }

    public boolean remove(Task task) {
        if (task == null) {
            for (Node x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node x = first; x != null; x = x.next) {
                if (task.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    private void fastRemove(int index) {
        checkElementIndex(index);
        unlink(node(index));
    }

    /**
     * Unlinks non-null node x.
     */
    Task unlink(Node x) {
        // assert x != null;
        final Task element = x.item;
        final Node next = x.next;
        final Node prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    //@Override
    public Iterator iterator() {
        Iterator it = new Iterator() {
            private Node lastReturned;
            private Node next;
            private int nextIndex = size;

            //@Override
            public boolean hasNext() {
                return nextIndex > 0;
            }

            //@Override
            public Task next() {
                if (!hasNext())
                    throw new NoSuchElementException();

                lastReturned = next = (next == null) ? last : next.prev;
                nextIndex--;
                return lastReturned.item;
            }

            public void remove() {
                if (lastReturned == null)
                    throw new IllegalStateException();

                Node lastNext = lastReturned.next;
                unlink(lastReturned);
                if (next == lastReturned)
                    next = lastNext;
                else
                    nextIndex--;
                lastReturned = null;
            }
        };
        return it;
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
        if (size != ((LinkedTaskList) obj).size)
            return false;
        LinkedTaskList other = (LinkedTaskList) obj;
        for (int i = 0; i < size; i++) {
            if (!other.getTask(i).equals(other.getTask(i)))
                return false;
        }
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        LinkedTaskList newLinkedTaskList = new LinkedTaskList();
        for (Iterator it = iterator(); it.hasNext(); ) {
            newLinkedTaskList.add((Task) it.next());
        }
        return newLinkedTaskList;
    }

    @Override
    public String toString() {

        String result = "";

        for (Iterator it = iterator(); it.hasNext(); ) {
            result = result + it.next().toString() + '\n';
        }
        return result;
    }
}

