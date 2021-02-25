public class Task {


//////////////fields
    private String title;
    private long dueDate;
    private String project;
    boolean achieved; // status


//////////////constructor

    public Task(String title, String project, long dueDate) {
        this.title = title;
        this.project = project;
        this.dueDate = dueDate;
        boolean achieved = false;
    }


//////////////methods



/////////////////////////////////////date
    public long getDueDate(){
        return dueDate;
    }

    public void setDueDate(long dueDate) {
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
    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }






}
