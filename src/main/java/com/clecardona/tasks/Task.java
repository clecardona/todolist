package com.clecardona.tasks;

import com.clecardona.menu.Menu;

import java.io.Serial;
import java.io.Serializable;

public class Task extends Menu implements Serializable {

    //////////////fields
    @Serial
    private static final long serialVersionUID = 0L;

    private String title;
    private String dueDate;
    private String project;
    private boolean isDone;

    //////////////constructor
    public Task(String title, String project, String dueDate) {
        this.title = title;
        this.project = project;
        this.dueDate = dueDate;
        boolean isDone = false;
    }

//////////////methods

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStatusString() {
        if (!isDone) {
            return "to do";
        }
        return "done";

    }

    public boolean getStatusBoolean() {
        return isDone;

    }

    public void setStatus(boolean done) {
        this.isDone = done;
    }


    @Override
    public String toString() {
        String space = " ";// used to format the displayed result
        String alignTitle = space.repeat(40 - getTitle().length());
        String alignProject = space.repeat(40 - getProject().length());

        return getTitle() + alignTitle + getProject() + alignProject + getDueDate() + space.repeat(25) + getStatusString();
    }

}
