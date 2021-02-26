public class Task implements java.io.Serializable{


//////////////fields
    private String title;
    private String dueDate;
    private String project;
    boolean isDone; // status


//////////////constructor

    public Task(String title, String project, String dueDate) {
        this.title = title;
        this.project = project;
        this.dueDate = dueDate;
        boolean isDone = false;
    }


//////////////methods


/////////////////////////////////////date
    public String getDueDate(){
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    //////////////////////////////////////label

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//////////////////////////////////////project

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    //////////////////////////////////////achieved
    public String getStatus() {
            if(!isDone){
                return "To do";
            }
        return "Done";

    }

    public void setStatus(boolean done) {
        this.isDone = done;
    }






}
