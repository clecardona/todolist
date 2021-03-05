package todolist;

public interface Commands {


    void displayTasks();

    void addNewTask();

    void editTask();

    void saveToFile();

    void loadFromFile();

    void quit();

}

