package com.clecardona.tools;

import com.clecardona.tasks.Task;

public class Comparator implements java.util.Comparator<Task> {

    ///fields

    private final char COMPARE_MODE;

    //// constructor

    public Comparator(char sortModeSelected) {
        this.COMPARE_MODE = sortModeSelected;
    }

    /// methods

    /**
     * compare method
     *
     * @param a task to compare
     * @param b other task to compare
     * @return the personalized comparator in order to sort the tasks list
     * type of sorting mode -> d: date , p: project , s: status .
     */
    public int compare(Task a, Task b) {
        int result = 0;
        switch (COMPARE_MODE) {
            case 'p' -> {
                result = a.getProject().compareTo(b.getProject());
            }
            case 'd' -> {
                result = a.getDueDate().compareTo(b.getDueDate());
            }
            case 's' -> {
                result = a.getStatusString().compareTo(b.getStatusString());
            }
        }
        return result;
    }


}