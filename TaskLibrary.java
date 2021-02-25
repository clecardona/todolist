import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;


public class TaskLibrary {


/////fields

      LinkedList<Task> al;


//////constructor

    public  TaskLibrary() {
        this.al = new LinkedList<Task>();
          }



/////methods

    public void addOneTask(Task task) {

     al.add(task);// TODO - differenciate objects added to the library ( they actually have the same name)

    }

    public void print() {
        for ( int i=0 ; i< al.size() ;i++){
            System.out.println( al.get(i).getTitle() + " - " + al.get(i).getProject()+ " - " + al.get(i).getDueDate());
        }
    }

   /**
    *TODO - implement methods
    *
    * update()
    *
    * markasdone()
    *
    *  remove()
    *
    *
    *  treemap to be able to sort by date/project
    *
    */



}

