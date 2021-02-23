import java.util.Date;

public class Task {


//////////////fields
    private String label;
    private long date;
    boolean achieved;


//////////////constructor



//////////////methods



/////////////////////////////////////date
    public long getDate(){
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    //////////////////////////////////////label

    public String getLabel(){
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }



    //////////////////////////////////////achieved
    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }
}
