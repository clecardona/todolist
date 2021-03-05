package todolist;

import java.io.Serial;
import java.io.Serializable;

public class Task extends Menu implements Serializable {

    //////////////fields
    @Serial
    private static final long serialVersionUID = 0L;

    private String title;
    private String dueDate;
    private String project;
    boolean isDone;

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

    public String getStatus() {
        if (!isDone) {
            return "to do";
        }
        return "done";

    }

    public void setStatus(boolean done) {
        this.isDone = done;
    }

}
