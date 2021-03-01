import java.util.Comparator;

public class TaskComparator<compareMode> implements Comparator<Task> {

    ///fields

    private final char compareMode;

    //// constructor

    public TaskComparator(char sortModeSelected) {
        this.compareMode = sortModeSelected;
    }

    /// methods

    /**
     * compare method
     *
     * @param a task to compare
     * @param b next task to compare
     * @return the personalized comparator in order to sort the tasks list
     */
    public int compare(Task a, Task b) {
        int result = 0;
        switch (compareMode) {
            case 'd' -> {
                result = a.getProject().compareTo(b.getProject());
            }
            case 'p' -> {
                result = a.getDueDate().compareTo(b.getDueDate());
            }
            case 's' -> {
                result = a.getStatus().compareTo(b.getStatus());
            }
        }
        return result;
    }

}